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

public class OrderExecutionDataCreationUS extends BaseTest {

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
        for (int i = 0; i < 5; i++) {  // 5 iterations
            for (String clientCodes : clientCode) {  // Each client
                String orderNum = insertUS_StockOrderData(clientCodes);  // Insert order first
                insertUS_Execution(orderNum);  // Insert execution using latest order number
            }
        }
    }

    public static String insertUS_StockOrderData(String clientCodes) {
        System.out.println("Inserting into US_STOCK_ORDER...");

        String newOrderNum = selectMax_ORD_NO();  // Get latest ORD_NO and increment

        String requestBody = "{ \"query\": \"INSERT INTO ACCOUNT.US_STOCK_ORDER (ORD_NO, ORD_SUB_NO, CLIENT_CD, TICKER, DSCR_NM, MKT_CD, SONAR_TRD_CD, TRD_TYP_CD, SLIP_NO) " +
                "VALUES ('" + newOrderNum + "', 1, '"+clientCodes+"', 'AIRR', 'ファースト・トラスト・RBAアメリカン・インダストリアル・ルネサンス・ETF', 'A9', 1, 3, 1)\" }";

        executeInsertQuery(requestBody);

        return newOrderNum; // Return the new order number for execution insertion
    }

    public static void insertUS_Execution(String orderNum) {
        System.out.println("Inserting into US_EXECUTION...");

        String newExecNum = selectMax_Execution_NO();  // Get latest EXEC_NO and increment

        String requestBody = "{ \"query\": \"INSERT INTO ACCOUNT.US_EXECUTION (EXEC_NO, ORD_NO, PRICE_INT, PRICE_DECML, EXEC_NOMINAL, EXEC_TM) " +
                "VALUES ('" + newExecNum + "', '" + orderNum + "', 7, 0.41, 766, TO_DATE('20250107 22:01:00', 'YYYYMMDD HH24:MI:SS'))\" }";

        executeInsertQuery(requestBody);
    }

    public static String selectMax_ORD_NO() {
        String requestBody = "{ \"query\": \"SELECT MAX(ORD_NO) AS ORD_NO FROM ACCOUNT.US_STOCK_ORDER\" }";
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
        return maxOrderNum; // Return new incremented order number
    }

    public static String selectMax_Execution_NO() {
        String requestBody = "{ \"query\": \"SELECT MAX(EXEC_NO) AS EXEC_NO FROM ACCOUNT.US_EXECUTION\" }";
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
        return maxExecNum; // Return new incremented execution number
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
