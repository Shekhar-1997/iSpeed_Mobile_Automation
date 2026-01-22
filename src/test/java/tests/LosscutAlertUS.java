package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.MenuPage;
import utils.BasePage;
import utils.DemoAPI;
import utils.VerifyInAlertStatusList;
import pages.VerifyPushNotification;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LosscutAlertUS extends BaseTest {

    private static DemoAPI demoAPI = new DemoAPI();

    private static MenuPage menuPage;
    private static VerifyInAlertStatusList verifLosscutAlertInStatusList;
    private static VerifyPushNotification verifyPushNotification;
    private static SoftAssert softAssert;

    private static BasePage basePage;
    private static List<String> clientCds = new ArrayList<>();
    public static String losscutDt;
    public static String losscutCnt;
    public static String clientCd;
    private static final List<String> clientCode = Arrays.asList("660803");

    @Test
    public static void losscutAlertUS() throws IOException {
        basePage = new BasePage(driver);
        verifyPushNotification = new VerifyPushNotification(driver);
        softAssert = new SoftAssert();
        menuPage = new MenuPage(driver);
        executeQueries();
    }

    public static void executeQueries() throws IOException {
        insertLosscutDataForMigration(false);
        //query2();
    }


//Optimized code which will work for single record creation and bulk data creation
    public static void insertLosscutDataForMigration(boolean executeLosscutVerification) throws IOException {
        System.out.println("Method Invoked: " + new Object() {}.getClass().getEnclosingMethod().getName());
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); // Get the method name

        // Get the current system date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
        String currentDateTime = now.format(formatter).toUpperCase();

        System.out.println("Inserting losscut condition with query1");

        // Construct the SQL query with the current date and time
        for (String clientCodes : clientCode) { // Using enhanced for-loop for readability
            for (int j = 0; j < 5; j++) {
                String requestBody = "{\n" + " \"query\": \"INSERT INTO ACCOUNT.US_MG_LCT_ORD_MNG (LOSSCUT_DT, LOSSCUT_CNT, CLIENT_CD, PROCESS_KBN, LOSSCUT_ORDER_TRADE_DT, BATCH_KBN, BATCH_BASE_DT, CREATE_DT, CREATE_PRG_ID, UPDATE_DT, UPDATE_PRG_ID) " + "VALUES(SYSDATE, '1','" + testData.getLossMarginClientCd() + "', 1, TO_DATE('2024/03/07 00:00:00', 'YYYY/MM/DD HH24:MI:SS'), '16', TO_DATE('2024/03/07 11:55:04', 'YYYY/MM/DD HH24:MI:SS'), " + "SYSDATE, 'test', TO_TIMESTAMP('2024/03/07 11:55:26.850000', 'YYYY/MM/DD HH24:MI:SS.FF6'), 'test by shekhar')\"\n" + "}";


                Response response = demoAPI.getValueFromGetAPI(requestBody);
                String responseBody = response.getBody().asString();
                System.out.println("Response Body from query1:");
                System.out.println(responseBody);

                // Check if the insert was successful
                int statusCode = response.getStatusCode();
                if (statusCode == 200) {
                    System.out.println("Insert query executed successfully for client: " + clientCodes);
                } else {
                    System.out.println("Failed to execute insert query. Status code: " + statusCode);
                }

                // Execute the losscut verification logic only if the flag is 1
                if (executeLosscutVerification) {
                    Response responseLosscut = demoAPI.cronJobLosscutAlert();
                    System.out.println("Response of margin alert: " + responseLosscut);

                    basePage.waitForSeconds(60);
                    verifyPushNotification.verifyMarginLossCutAlert("ロスカットを執行しました。詳しくはお知らせをご確認ください。", softAssert, "LosscutPush.png");
                    driver.navigate().back();
                    menuPage.goToMenuPage();
                    menuPage.gotoAlertStatusList();
                    verifLosscutAlertInStatusList.verifyMarginLosscutAlertInStatusList("ロスカットを執行しました。詳しくはお知らせをご確認ください。", softAssert, "LosscutStatusList.png");
                    softAssert.assertAll();
                }
            }
        }
    }




    // below code is for insert a record instead of bulk data
//    public static void insertLosscutData() throws IOException {
//        System.out.println("Method Invoked: " + new Object() {
//        }.getClass().getEnclosingMethod().getName());
//        String methodName = new Object() {
//        }.getClass().getEnclosingMethod().getName(); // Get the method name
//        // Get the current system date and time
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
//        String currentDateTime = now.format(formatter).toUpperCase();
//
//        System.out.println("Inserting losscut condition with query1");
//        // Construct the SQL query with the current date and time// we need to pass clientcd dynamically
//        String requestBody = "{\n" + " \"query\": \"INSERT INTO ACCOUNT.US_MG_LCT_ORD_MNG (LOSSCUT_DT, LOSSCUT_CNT, CLIENT_CD, PROCESS_KBN, LOSSCUT_ORDER_TRADE_DT, BATCH_KBN, BATCH_BASE_DT, CREATE_DT, CREATE_PRG_ID, UPDATE_DT, UPDATE_PRG_ID) " + "VALUES(SYSDATE, '1','" + testData.getClientCode() + "', 1, TO_DATE('2024/03/07 00:00:00', 'YYYY/MM/DD HH24:MI:SS'), '16', TO_DATE('2024/03/07 11:55:04', 'YYYY/MM/DD HH24:MI:SS'), " + "SYSDATE, 'test', TO_TIMESTAMP('2024/03/07 11:55:26.850000', 'YYYY/MM/DD HH24:MI:SS.FF6'), 'test by shekhar')\"\n" + "}";
//
////        System.out.println("Select Inserted losscut record from query2");
////        query2();
//
//        Response response = demoAPI.getValueFromGetAPI(requestBody);
//        String responseBody = response.getBody().asString();
//        System.out.println("Response Body from query1:");
//        System.out.println(responseBody);
//
//        // Check if the insert was successful
//        int statusCode = response.getStatusCode();
//        if (statusCode == 200) {
//            System.out.println("Insert query executed successfully.");
//        } else {
//            System.out.println("Failed to execute insert query. Status code: " + statusCode);
//        }
//        Response responseLosscut = demoAPI.cronJobLosscutAlert();
//        System.out.println("Response of margin alert" + responseLosscut);
//
//        basePage.waitForSeconds(60);
//        verifyPushNotification.verifyMarginLossCutAlert("ロスカットを執行しました。詳しくはお知らせをご確認ください。", softAssert, "LosscutPush.png");
//        driver.navigate().back();
//        menuPage.goToMenuPage();
//        menuPage.gotoAlertStatusList();
//        verifLosscutAlertInStatusList.verifyMarginLosscutAlertInStatusList("ロスカットを執行しました。詳しくはお知らせをご確認ください。", softAssert, "LosscutStatusList.png");
//        softAssert.assertAll();
//
//    }
}
//


