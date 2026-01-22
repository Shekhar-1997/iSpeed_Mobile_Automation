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

import static tests.PageOjectManager.demoAPI;

public class KeepAliveUtil extends BaseTest {
    public void KeepAlivequery() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("KeepAlive Evidence");
        int rowCount = 0;

        for (int i = 0; i < 2; i++) {
            // Query to get the last_receive_dt converted to the JST timezone
            String requestBody = "{\n" +
                    "  \"query\": \"SELECT DATE_FORMAT(last_receive_dt, '%Y-%m-%d %H:%i:%s') AS last_receive_dt FROM heartbeat_rechieve_info\"\n" +
                    "}";

            Response response = demoAPI.getValueFromPushDB(requestBody);
            String responseBody = response.getBody().asString();
            System.out.println("Response Body from query1 (iteration " + (i + 1) + "):");
            System.out.println(responseBody);

            // Write the response body to the Excel file
            Row responseRow = sheet.createRow(rowCount++);
            Cell responseCell = responseRow.createCell(0);
            responseCell.setCellValue("Response Body from query1 (iteration " + (i + 1) + "):");
            responseRow = sheet.createRow(rowCount++);
            responseCell = responseRow.createCell(0);
            responseCell.setCellValue(responseBody);

            JsonPath jsonPath = new JsonPath(responseBody);
            String lastReceiveDt = jsonPath.getString("data[0].last_receive_dt");

            // Print the last_receive_dt in JST
            System.out.println("Last Receive Date-Time (JST): " + lastReceiveDt);

            // Write the last_receive_dt to the Excel file
            Row dateTimeRow = sheet.createRow(rowCount++);
            Cell dateTimeCell = dateTimeRow.createCell(0);
            dateTimeCell.setCellValue("Last Receive Date-Time (JST): " + lastReceiveDt);

            // Add a blank row for separation
            rowCount++;

            if (i == 0) {
                // Sleep for 30 seconds before the next iteration
                try {
                    Thread.sleep(10000);  // As per spec 30s
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        // Write the evidence to an Excel file with name keepAliveEvidence.xlsx
        try (FileOutputStream outputStream = new FileOutputStream("keepAliveEvidence.xlsx")) {
            workbook.write(outputStream);
        }
        // Close the workbook
        workbook.close();
    }
}
