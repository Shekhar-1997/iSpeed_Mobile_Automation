package utils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tests.BaseTest;

import java.io.*;
import java.util.List;
import java.util.Map;

public class PushDBAPICreateUpdate extends BaseTest {

    private static DemoAPI demoAPI = new DemoAPI();

    public static void captureDBEvidence(String nameOfMethod, String alertType) {
        inactiveDbData(nameOfMethod,alertType);
    }

    public static void inactiveDbData(String sheetName,String alertType) {
        String requestBody = "{\n" +
                "  \"query\": \"select * from push_device_info A where A.acc_device_ctrl_no = '" +  testData.getaccDeviceCtrlNo() + "' OR A.acc_device_ctrl_no = '" +  testData.getAccDeviceCtrlNoTwo() + "'\"\n" +
                "}";
        Response response = demoAPI.getValueFromPushDB(requestBody);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);

        System.out.println("Request Body: " + requestBody);
        System.out.println("Response: " + responseBody);  // Print the full response for debugging

        List<Map<String, Object>> dataList = jsonPath.getList("data");

        if (dataList == null || dataList.isEmpty()) {
            System.out.println("No data found in response.");
            return;
        }



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


        // Create headers every time before writing data
        String[] headers = {
                "acc_device_ctrl_no", "branch_cd", "client_cd", "device_id",
                "platform_kbn", "endpoint_arn", "last_login_kbn", "last_login_dt",
                "badge_count", "create_dt", "create_user", "update_dt", "update_user"
        };

        Row headerRow = sheet.createRow(rowNum++);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Populate the data rows
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
        }

        // Write the workbook to the file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Data written to excel file");
        } catch (IOException e) {
            System.out.println("Error writing Excel file: " + e.getMessage());

        }
    }
}
