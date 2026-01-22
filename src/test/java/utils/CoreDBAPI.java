package utils;

import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.json.JSONArray;
//import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONObject;
import tests.BaseTest;

import java.io.FileOutputStream;
import java.io.IOException;

import static tests.BaseTest.testData;

public class CoreDBAPI extends BaseTest {

    private static final String EXCEL_FILE_PATH = "OrderExecutionData.xlsx";
    private static final DemoAPI demoAPI = new DemoAPI();
    public static TestDataProvider testDataProvider = new TestDataProvider();

    public static void orderExecution(String type, String stockCode, String methodName) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(methodName);

        int rowNum = 0;

        if (type.equals("jp")) {
            rowNum = orderExecutionJP(sheet, rowNum, stockCode);
        } else if (type.equals("us")) {
            rowNum = orderExecutionUS(sheet, rowNum);
        }

        try (FileOutputStream fileOut = new FileOutputStream(EXCEL_FILE_PATH)) {
            workbook.write(fileOut);
        }
        workbook.close();
        System.out.println("Output written to " + EXCEL_FILE_PATH);
    }

    public static int orderExecutionJP(Sheet sheet, int rowNum, String stockCode) {
        String requestBody = "{\n" +
                "  \"query\": \"select ord_no, dscr_cd, ord_sts_cd, TO_CHAR(APPL_DT, 'YYYY-MM-DD HH24:MI:SS') AS APPL_DT " +
                "from stock_order where client_cd='" + testData.getClientCode() + "' and dscr_cd='" + stockCode + "' order by INP_TM desc\"\n" +
                "}";
        Response response = demoAPI.getValueFromGetAPI(requestBody);
        String responseBody = response.getBody().asString();
        System.out.println("Query 1: " + responseBody);

        JSONObject jsonResponse = new JSONObject(responseBody);
        if (jsonResponse.getInt("status") == 200) {
            JSONArray dataArray = jsonResponse.getJSONArray("data");

            // Create header row if it's the first query
            if (rowNum == 0) {
                Row headerRow = sheet.createRow(rowNum++);
                headerRow.createCell(0).setCellValue("ORD_NO");
                headerRow.createCell(1).setCellValue("DSCR_CD");
                headerRow.createCell(2).setCellValue("ORD_STS_CD");
                headerRow.createCell(3).setCellValue("APPL_DT");
                rowNum++; // Add a blank row before starting the data rows
            }

            // Write data rows
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject dataObject = dataArray.getJSONObject(i);
                Row row = sheet.createRow(rowNum++);
                Cell cell1 = row.createCell(0);
                Cell cell2 = row.createCell(1);
                Cell cell3 = row.createCell(2);
                Cell cell4 = row.createCell(3);

                cell1.setCellValue(dataObject.getInt("ORD_NO"));
                cell2.setCellValue(dataObject.getString("DSCR_CD"));
                cell3.setCellValue(dataObject.getInt("ORD_STS_CD"));
                cell4.setCellValue(dataObject.getString("APPL_DT"));
            }

            // Add a 2-row gap after each query result
            rowNum += 2;
        }
        return rowNum;
    }

    public static int orderExecutionUS(Sheet sheet, int rowNum) {
        String requestBody = "{\n" +
                "  \"query\": \"select ord_no, ord_sts_cd, TO_CHAR(INP_TM, 'YYYY-MM-DD HH24:MI:SS') AS INP_TM " +
                "from us_stock_order where client_cd='" + testData.getClientCode() + "' order by INP_TM desc\"\n" +
                "}";
        Response response = demoAPI.getValueFromGetAPI(requestBody);
        String responseBody = response.getBody().asString();
        System.out.println("Query 2: " + responseBody);

        JSONObject jsonResponse = new JSONObject(responseBody);
        if (jsonResponse.getInt("status") == 200) {
            JSONArray dataArray = jsonResponse.getJSONArray("data");

            // Create header row if it's the first query
            if (rowNum == 0) {
                Row headerRow = sheet.createRow(rowNum++);
                headerRow.createCell(0).setCellValue("ORD_NO");
                headerRow.createCell(1).setCellValue("ORD_STS_CD");
                headerRow.createCell(2).setCellValue("INP_TM");
                rowNum++; // Add a blank row before starting the data rows
            }

            // Write data rows
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject dataObject = dataArray.getJSONObject(i);
                Row row = sheet.createRow(rowNum++);
                Cell cell1 = row.createCell(0);
                Cell cell2 = row.createCell(1);
                Cell cell3 = row.createCell(2);

                cell1.setCellValue(dataObject.getInt("ORD_NO"));
                cell2.setCellValue(dataObject.getInt("ORD_STS_CD"));
                cell3.setCellValue(dataObject.getString("INP_TM"));
            }

            // Add a 2-row gap after each query result
            rowNum += 2;
        }
        return rowNum;
    }
}
