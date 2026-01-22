package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.MenuPage;
import utils.BasePage;
import utils.DemoAPI;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;

import static tests.PageOjectManager.pushNotification;
import static tests.PageOjectManager.verificationInStatusList;

public class MarginAlertUS extends BaseTest {

    private static DemoAPI demoAPI = new DemoAPI();
    private static MenuPage menuPage;
    private static BasePage basePage;
    private static SoftAssert softAssert;
    private static List<String> clientCds = new ArrayList<>();
    private static List<String> branchCds = new ArrayList<>();
    private static List<String> margcMngNos = new ArrayList<>();


    public static String maxMargcMngNo;

    private static final List<String> clientCode = Arrays.asList("660803", "660804", "660805");

    @Test(invocationCount = 5)
    public static void marginAlertUS() throws IOException {
        System.out.println( testData.getClientCode());
        basePage = new BasePage(driver);
        softAssert = new SoftAssert();
        menuPage = new MenuPage(driver);
        basePage.waitForSeconds(10);
        executeQueries();
        softAssert.assertAll();
    }

    public static void executeQueries() throws IOException {
        selectMax_MARGC_MNG_NO(); // Fetch and increment MARGC_MNG_NO first
        insertMarginData(false); // Not verify margin alert if argumet is false
        //query2();
    }
    //Optimized code which will work for single record creation and bulk data creation
    public static void insertMarginData(boolean isVerifyALert) {
        // Get the current system date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String currentDateTime = now.format(formatter);

        System.out.println("Inserting margin condition with query1");

        long currentMargcMngNo = Long.parseLong(maxMargcMngNo); // Convert maxMargcMngNo to long

        for (String clientCodes : clientCode) { // Loop through each client code
            for (int j = 0; j < 5; j++) { // Insert 5 records per client
                currentMargcMngNo++; // Increment MARGC_MNG_NO for each record

                String sqlQuery = "INSERT INTO US_MG_CALL_MNG (MARGC_MNG_NO, BRANCH_CD, CLIENT_CD, MARGC_KBN, MARGC_STS, " +
                        "MARGC_DT, MARGC_T_DT, MARGC_T1_DT, MARGC_T2_DT, FIX_FX_RATE, YEN_RATE, MARGIN_QUOT_AMT, DEPOSIT_RATE, " +
                        "MARGC_AMT, MARGC_AMT_YEN, MARGC_BAL, MARGC_BAL_YEN, MARGC_CLEAR_AMT, MARGC_CLEAR_AMT_YEN, " +
                        "MARGC_CLEAR_RECEIPT, MARGC_CLEAR_RECEIPT_YEN, MARGC_CLEAR_RECEIPT_APRX, MARGC_CLEAR_RFND, " +
                        "MARGC_CLEAR_COL, MARGC_CLEAR_REASON, CLIENT_MARGC_CLEAR_REASON, MARGC_CLEAR_DT, MARGC_LOCK_CLEAR_DT, " +
                        "MARGC_T_FLG, MARGC_T1_FLG, MARGC_T2_FLG, FORCE_CNT, FORCE_CREATE_FIRST_DT, FORCE_CREATE_NEW_DT, " +
                        "SETTLE_RESULT_KBN, NOTICE_READ_FLG, NOTICE_READ_NEXT_FLG, FIXED_USER, FIXED_DT, EXECUTE_USER, EXECUTE_DT, " +
                        "BATCH_KBN, BATCH_BASE_DT, CREATE_DT, CREATE_PRG_ID, UPDATE_DT, UPDATE_PRG_ID) VALUES (" +
                        currentMargcMngNo + ", '702','" + clientCodes + "', '1', '1', " +
                        "TO_DATE('2023/04/21 00:00:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2023/04/24 00:00:00', 'YYYY/MM/DD HH24:MI:SS'), " +
                        "TO_DATE('2023/04/25 00:00:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2023/04/26 00:00:00', 'YYYY/MM/DD HH24:MI:SS'), " +
                        "'107.368', '95', '18201', '6.7', '5459.3', '617005', '5459.03', '616974', '.27', '31', '0', '31', '.27', '0', '0', '0', " +
                        "'1', TO_DATE('2022/08/25 00:00:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2022/08/25 00:00:00', 'YYYY/MM/DD HH24:MI:SS'), " +
                        "'1', '1', '1', '1', TO_DATE('2023/04/27 00:00:00', 'YYYY/MM/DD HH24:MI:SS'), NULL, '1', NULL, NULL, 'ETR', " +
                        "TO_DATE('2022/08/25 00:00:00', 'YYYY/MM/DD HH24:MI:SS'), 'ETR', TO_DATE('2022/08/25 00:00:00', 'YYYY/MM/DD HH24:MI:SS'), " +
                        "' ', NULL, SYSDATE, 'NIS', SYSDATE, 'NIS')";

                String requestBody = "{\n" +
                        "  \"query\": \"" + sqlQuery + "\"\n" +
                        "}";

                System.out.println("Executing query for client: " + clientCodes + " | Record: " + (j + 1) + " | MARGC_MNG_NO: " + currentMargcMngNo);

                Response response = demoAPI.getValueFromGetAPI(requestBody);
                String responseBody = response.getBody().asString();
                System.out.println("Response Body from query:");
                System.out.println(responseBody);

                // Check if the insert was successful
                int statusCode = response.getStatusCode();
                if (statusCode == 200) {
                    System.out.println("Insert successful for client: " + clientCodes + " | Record: " + (j + 1) + " | MARGC_MNG_NO: " + currentMargcMngNo);
                } else {
                    System.out.println("Failed insert for client: " + clientCodes + " | Record: " + (j + 1) + " | Status code: " + statusCode);
                }

            }

        }
        if (isVerifyALert) {
            basePage.waitForSeconds(3);
            Response responseMargin = demoAPI.cronJobMarginAlert();
            System.out.println("Response of margin alert: " + responseMargin);

            basePage.waitForSeconds(20);
            pushNotification.verifyMarginLossCutAlert("追証が発生しました。詳しくはお知らせをご確認ください。", softAssert, "marginPush.png");
            driver.navigate().back();
            menuPage.goToMenuPage();
            menuPage.gotoAlertStatusList();
            verificationInStatusList.verifyMarginLosscutAlertInStatusList("追証が発生しました。詳しくはお知らせをご確認ください。", softAssert, "marginStaruList.png");
        }
    }

    public static void selectMax_MARGC_MNG_NO() {
        String requestBody = "{\n" +
                "  \"query\": \"SELECT MAX(MARGC_MNG_NO) AS MAX_MARGC_MNG_NO FROM US_MG_CALL_MNG\"\n" +
                "}";

        Response response = demoAPI.getValueFromGetAPI(requestBody);
        String responseBody = response.getBody().asString();
        System.out.println("Response Body from query3:");
        System.out.println(responseBody);

        JsonPath jsonPath = new JsonPath(responseBody);
        String maxMargcMngNoStr = jsonPath.getString("data[0].MAX_MARGC_MNG_NO");

        if (maxMargcMngNoStr != null) {
            long maxMargcMngNoLong = Long.parseLong(maxMargcMngNoStr);
            maxMargcMngNo = String.valueOf(maxMargcMngNoLong); // Store the latest max value
            System.out.println("Fetched max_margc_mng_no: " + maxMargcMngNo);
        } else {
            System.out.println("No max_margc_mng_no found in query3 response.");
        }
    }

}
