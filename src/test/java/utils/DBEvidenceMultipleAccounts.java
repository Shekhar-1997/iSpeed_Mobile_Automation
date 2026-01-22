package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.*;
import tests.BaseTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static tests.PageOjectManager.demoAPI;

public class DBEvidenceMultipleAccounts extends BaseTest {

    // private static List<String> accDeviceCtrlNos = new ArrayList<>();
    private static List<Integer> noticeIds = new ArrayList<>();


    public static void captureDBEvidenceMultipleAccounts(String nameOfMethod,String alertType,int limit) {
        // getAccDeviceCtrlNo();
        getNoticeIds(limit);
        getAlertDbDBData(nameOfMethod, alertType);

    }
    public static void getNoticeIds(int limit) {
//        String requestBody = "{\n" +
//                "  \"query\": \"select C.notice_id from push_notice_set_info C order by C.create_dt desc limit "+limit+"\"\n" +
//                "}";
        String requestBody = "{\n" +
                "  \"query\": \"select c.notice_id from push_device_info a, push_notice_service_info b, push_notice_set_info c where a.branch_cd = b.branch_cd and a.client_cd = b.client_cd and a.acc_device_ctrl_no in('" +  testData.getaccDeviceCtrlNo() + "', '" +  testData.getAccDeviceCtrlNoTwo() + "') and b.notice_id = c.notice_id " +
                "order by c.create_dt desc limit "+limit+"\"\n" +
                "}";
        Response response = demoAPI.getValueFromPushDB(requestBody);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        System.out.println("Request body of query2:" +requestBody);
        System.out.println("the response of query2:" +responseBody);
        System.out.println("the Json value:"+jsonPath.getList("data").size());
        if (((int) jsonPath.getList("data").size()) > 0) {
            List<Integer> noticeIdsList = jsonPath.getList("data.notice_id");
            System.out.println("Notice IDs: " + noticeIdsList);

            for (Integer noticeId : noticeIdsList) {
                System.out.println("noticeId: " + noticeId);
                noticeIds.add(noticeId);
            }
        } else {
            System.out.println("No data found in query2 response.");
        }
    }

    public static void getAlertDbDBData(String sheetName, String alertType) {
        String noticeIdsStr = noticeIds.toString().replace("[", "(").replace("]", ")");
        System.out.println("The noticeids: " + noticeIdsStr);
        String requestBody = "{\n" +
                "  \"query\": \"select " +
                "A.acc_device_ctrl_no, " +
                "A.branch_cd, " +
                "A.client_cd, " +
                "A.device_id, " +
                "A.endpoint_arn, " +
                "A.platform_kbn, " +
                "A.last_login_kbn, " +
                "A.last_login_dt, " +
                "A.badge_count, " +
                "B.notice_ctrl_no, " +
                "B.notice_id, " +
                "B.notice_input_dt, " +
                "B.expire_dt, " +
                "B.notice_occurence_kbn, " +
                "B.notice_set_del_kbn, " +
                "B.create_dt, " +
                "B.create_user, " +
                "B.update_dt, " +
                "B.update_user, " +
                "C.reuter_notice_id, " +
                "C.notice_kbn, " +
                "C.dscr_cd, " +
                "C.dscr_nm, " +
                "C.market_cd, " +
                "C.prdct_cd, " +
                "C.indices_cd, " +
                "C.price_kbn, " +
                "C.value, " +
                "C.value_kbn, " +
                "C.notice_del_kbn, " +
                "C.ca_info_kbn, " +
                "C.ticker, " +
                "C.sec_typ_cd, " +
                "C.contract_cd, " +
                "C.paid_info_kbn " +
                "from push_device_info A, push_notice_service_info B, push_notice_set_info C " +
                "where " +
                "A.branch_cd = B.branch_cd " +
                "and A.client_cd = B.client_cd " +
                "and B.notice_id = C.notice_id " +
                "and A.acc_device_ctrl_no in ('" +  testData.getaccDeviceCtrlNo() + "', '" +  testData.getAccDeviceCtrlNoTwo() + "') " +
                "and B.notice_id in " + noticeIdsStr +
                " order by A.platform_kbn, A.acc_device_ctrl_no, B.create_dt desc\"\n" +
                "}";
        System.out.println("the request body:" + requestBody);
        noticeIds.clear();

        try {
            Response response = demoAPI.getValueFromPushDB(requestBody);
            String responseBody = response.getBody().asString();
            JsonPath jsonPath = new JsonPath(responseBody);

            System.out.println("Response Body: " + responseBody);

            List<Map<String, Object>> dataList = jsonPath.getList("data");
            if (dataList == null) {
                System.out.println("Data list is null. Response body:");
                System.out.println(responseBody);
                return;
            }

            // Get or create the sheet
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }

            // Calculate row number for new data
            int rowNum = sheet.getLastRowNum() + 3;

            // Insert the sheet name above the headers
            Row sheetNameRow = sheet.createRow(rowNum++);
            Cell sheetNameCell = sheetNameRow.createCell(0);
            sheetNameCell.setCellValue(alertType);

            // Add headers
            String[] headers = {
                    "acc_device_ctrl_no", "branch_cd", "client_cd", "device_id", "endpoint_arn", "platform_kbn",
                    "last_login_kbn", "last_login_dt", "badge_count", "notice_ctrl_no", "notice_id", "notice_input_dt",
                    "expire_dt", "notice_occurence_kbn", "notice_set_del_kbn", "create_dt", "create_user", "update_dt",
                    "update_user", "reuter_notice_id", "notice_kbn", "dscr_cd", "dscr_nm", "market_cd", "prdct_cd",
                    "indices_cd", "price_kbn", "value", "value_kbn", "notice_del_kbn", "ca_info_kbn", "ticker",
                    "sec_typ_cd", "contract_cd", "paid_info_kbn"
            };

            Row headerRow = sheet.createRow(rowNum++);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Add data rows and check notice_occurence_kbn
            for (Map<String, Object> data : dataList) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = row.createCell(i);
                    Object value = data.get(headers[i]);
                    if (value != null) {
                        cell.setCellValue(value.toString());
                    } else {
                        cell.setCellValue("");
                    }
                }

                // Compare notice_occurence_kbn and print message
                String noticeOccurenceKbn = data.get("notice_occurence_kbn").toString();
                if ("0".equals(noticeOccurenceKbn)) {
                    Row messageRow = sheet.createRow(rowNum++);
                    Cell messageCell = messageRow.createCell(0);
                    messageCell.setCellValue("");
                } else if ("1".equals(noticeOccurenceKbn)) {
                    Row messageRow = sheet.createRow(rowNum++);
                    Cell messageCell = messageRow.createCell(0);
                    messageCell.setCellValue("");
                }
            }

            // Write changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the Excel file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }


}



//    public static void getAccDeviceCtrlNo() {
//        String requestBody = "{\n" +
//                "  \"query\": \"select acc_device_ctrl_no from push_device_info A order by A.last_login_dt desc limit 1\"\n" +
//                "}";
//
//        Response response = demoAPI.getValueFromPushDB(requestBody);
//        String responseBody = response.getBody().asString();
//        JsonPath jsonPath = new JsonPath(responseBody);
//        String acc_device_ctrl_no = jsonPath.getString("data[0].acc_device_ctrl_no");
//        System.out.println("acc_device_ctrl_no: " + acc_device_ctrl_no);
//
//        if (acc_device_ctrl_no != null) {
//            accDeviceCtrlNos.add(acc_device_ctrl_no);
//        } else {
//            System.out.println("No acc_device_ctrl_no found in query1 response.");
//        }
//    }
