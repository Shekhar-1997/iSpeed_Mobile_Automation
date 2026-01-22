package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tests.BaseTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestDataAPI extends BaseTest {

    private static DemoAPI demoAPI = new DemoAPI();
    private static List<String> accDeviceCtrlNos = new ArrayList<>();
    private static List<Integer> noticeIds = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        executeQueries();
    }

    public static void executeQueries() throws IOException {
        query1();
        query2();
        query3();
    }

    public static void query1() throws IOException {
        String requestBody = "{\n" +
                "  \"query\": \"select acc_device_ctrl_no from push_device_info A order by A.last_login_dt desc\"\n" +
                "}";

        Response response = demoAPI.getValueFromPushDB(requestBody);
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
// Extracting platform_kbn from the JSON response
        JsonPath jsonPath = new JsonPath(responseBody);
        String acc_device_ctrl_no = jsonPath.getString("data[0].acc_device_ctrl_no");
        System.out.println("acc_device_ctrl_no: " + acc_device_ctrl_no);

        if (acc_device_ctrl_no != null) {
            accDeviceCtrlNos.add(acc_device_ctrl_no);
        } else {
            System.out.println("No notice_id found in query2 response.");
        }
    }

    public static void query2() throws IOException {
        String requestBody = "{\n" +
                "  \"query\": \"select C.notice_id from push_notice_set_info C order by C.create_dt desc\"\n" +
                "}";
        Response response = demoAPI.getValueFromPushDB(requestBody);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);

        // Check if data array is not empty
        if (jsonPath.getList("data").size() > 0) {
            Integer noticeId = jsonPath.getInt("data[0].notice_id"); // Assuming first result
            System.out.println("noticeId :" + noticeId);
            if (noticeId != null) {
                noticeIds.add(noticeId);
            } else {
                System.out.println("No notice_id found in query2 response.");
            }
        } else {
            System.out.println("No data found in query2 response.");
        }
    }

    public static void query3() throws IOException {
        String accDeviceCtrlNosStr = accDeviceCtrlNos.toString().replace("[", "(").replace("]", ")");
        String noticeIdsStr = noticeIds.toString().replace("[", "(").replace("]", ")");

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
                "and A.acc_device_ctrl_no in " + accDeviceCtrlNosStr +
                " and B.notice_id in " + noticeIdsStr +
                " order by A.platform_kbn, A.acc_device_ctrl_no, B.create_dt desc\"\n" +
                "}";


        Response response = demoAPI.getValueFromPushDB(requestBody);
        String responseBody = response.getBody().asString();
        System.out.println("Response Body:");
        System.out.println("responseBody of 3rd query :" + responseBody);

        // Parse the JSON response
        JsonPath jsonPath = new JsonPath(responseBody);
        List<Map<String, Object>> data = jsonPath.getList("data");

        // Create an Excel workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Query Results");

        // Write the header row
        Row headerRow = sheet.createRow(0);
        if (!data.isEmpty()) {
            Map<String, Object> firstRow = data.get(0);
            int cellIndex = 0;
            for (String key : firstRow.keySet()) {
                Cell cell = headerRow.createCell(cellIndex++);
                cell.setCellValue(key);
            }

            // Write data rows
            int rowIndex = 1;
            for (Map<String, Object> rowData : data) {
                Row row = sheet.createRow(rowIndex++);
                cellIndex = 0;
                for (Object value : rowData.values()) {
                    Cell cell = row.createCell(cellIndex++);
                    cell.setCellValue(value != null ? value.toString() : "");
                }
            }
        }

        // Write the Excel file to disk
        String filePath = "output.xlsx";
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
        workbook.close();
        System.out.println("Response body written to " + filePath);
        // Further processing if needed
        if (!data.isEmpty()) {
            String notice_occurence_kbn = data.get(0).get("notice_occurence_kbn").toString();
            System.out.println("notice_occurence_kbn: " + notice_occurence_kbn);
        }
    }
}
