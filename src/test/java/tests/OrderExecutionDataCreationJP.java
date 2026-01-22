package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.MenuPage;
import utils.BasePage;
import utils.DemoAPI;
import java.io.IOException;
import java.util.*;

public class OrderExecutionDataCreationJP extends BaseTest {

    private static DemoAPI demoAPI = new DemoAPI();
    private static BasePage basePage;
    private static SoftAssert softAssert;
    private static MenuPage menuPage;
    private static final List<String> clientCode = Arrays.asList("660803", "660804", "660805");

    @Test
    public static void executionData() throws IOException {
        basePage = new BasePage(driver);
        softAssert = new SoftAssert();
        menuPage = new MenuPage(driver);
        basePage.waitForSeconds(10);
        executeQueries();
        softAssert.assertAll();
    }

    public static void executeQueries() throws IOException {
        for (int i = 0; i < 5; i++) {
            for (String clientCodes : clientCode) {
                String orderNum = insertJP_StockOrderData(clientCodes);
                insertJP_Execution(orderNum);
            }
        }
    }

    public static String insertJP_StockOrderData(String clientCodes) {
        System.out.println("Inserting into STOCK_ORDER...");

        String newOrderNum = selectMax_ORD_NO();

        String requestBody = "{ \"query\": \"INSERT INTO ACCOUNT.STOCK_ORDER (ORD_SUB_NO, ORD_NO, CLIENT_CD, DSCR_CD, DSCR_NM, SONAR_TRD_CD, TRD_TYP_CD, MKT_CD) " +
                "VALUES (1, '" + newOrderNum + "', '" + clientCodes + "', '47450', '楽天グループ', 0, 1, 1)\" }";

        executeInsertQuery(requestBody);

        return newOrderNum;
    }

    public static void insertJP_Execution(String orderNum) {
        System.out.println("Inserting into EXECUTION...");

        String newExecNum = selectMax_Execution_NO();

        String requestBody = "{ \"query\": \"INSERT INTO ACCOUNT.EXECUTION (EXEC_NO, ORD_NO, SONAR_TRD_CD, EXEC_PRC, EXEC_NOMINAL, EXEC_TM) " +
                "VALUES ('" + newExecNum + "', '" + orderNum + "', 0, 123.45, 1000, TO_DATE('2025/01/08 10:00:00', 'YYYY/MM/DD HH24:MI:SS'))\" }";

        executeInsertQuery(requestBody);
    }

    public static String selectMax_ORD_NO() {
        String requestBody = "{ \"query\": \"SELECT MAX(ORD_NO) AS ORD_NO FROM ACCOUNT.STOCK_ORDER\" }";
        Response response = demoAPI.getValueFromGetAPI(requestBody);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);

        String maxOrderNum;
        try {
            String maxOrderNumber = jsonPath.getString("data[0].ORD_NO");
            maxOrderNum = (maxOrderNumber != null) ? String.valueOf(Long.parseLong(maxOrderNumber) + 1) : "1";
            System.out.println("Next maxOrderNum: " + maxOrderNum);
        } catch (Exception e) {
            System.err.println("Error fetching max ORD_NO: " + e.getMessage());
            maxOrderNum = "1";
        }
        return maxOrderNum;
    }

    public static String selectMax_Execution_NO() {
        String requestBody = "{ \"query\": \"SELECT MAX(EXEC_NO) AS EXEC_NO FROM ACCOUNT.EXECUTION\" }";
        Response response = demoAPI.getValueFromGetAPI(requestBody);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);

        String maxExecNum;
        try {
            String maxExecutionNumber = jsonPath.getString("data[0].EXEC_NO");
            maxExecNum = (maxExecutionNumber != null) ? String.valueOf(Long.parseLong(maxExecutionNumber) + 1) : "1";
            System.out.println("Next maxExecNum: " + maxExecNum);
        } catch (Exception e) {
            System.err.println("Error fetching max EXEC_NO: " + e.getMessage());
            maxExecNum = "1";
        }
        return maxExecNum;
    }

    private static void executeInsertQuery(String requestBody) {
        System.out.println("Executing query: " + requestBody);

        Response response = demoAPI.getValueFromGetAPI(requestBody);
        System.out.println("Response Body: " + response.getBody().asString());

        if (response.getStatusCode() == 200) {
            System.out.println("Insert query executed successfully.");
        } else {
            System.err.println("Failed to execute insert query. Status code: " + response.getStatusCode());
        }
    }
}