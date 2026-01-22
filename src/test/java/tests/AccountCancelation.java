package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static tests.PageOjectManager.*;


public class AccountCancelation extends BaseTest {

    private static DemoAPI demoAPI = new DemoAPI();

    public static SoftAssert softAssert ;

    public void executeQueries() {
        insertAccCancelationCondition();
        selectAccCancelationData();
    }

    @Test(priority = 1)
    public void CreateAlert() {
        softAssert = new SoftAssert();
        createAlertUtil.prerequisiteForAlertSettingScreen();
        createAlertUtil.createAlert(softAssert, testData.getJpStockpreviousDown(), "JP", "create", "down", String.valueOf(testData.getPreviousDayRatioDecrementAmount()), "account cancellation previousday down", true, false, "previousDayDown");
        executeQueries();
        softAssert.assertAll();
    }

    public static void insertAccCancelationCondition() {
        // Get the current system date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = now.format(formatter);

        // We need to pass client_cd dynamically
        String sqlQuery = "insert into ORDER_CTL_BY_CLNT_DETAIL " +
                "(ORDER_CTL_NO, UPD_CNT, LOCK_DT, CLIENT_CD, LOGIN_LOCK_FLG, TRADE_LOCK_FLG, TARGET, CTL_REASON_CD, " +
                "ORDER_CTL_COMMENT_WS, ORDER_CTL_MESSAGE_WEB, CHECK_FLG, INPUT_ROUTE, INP_TM, AGENT, RELEASE_KEY, LST_UPD_DT) " +
                "values (ORDER_CTL_NO.NEXTVAL, '0',SYSDATE, '" + testData.getClientCode() + "', '1', '1', " +
                "'1111111101111111111111111111111111000110100000111111111110011110000011000111111', '12', '口座解約の為', " +
                "'お客様の口座は、解約のご依頼があった為ログインが制限されています。', '0', '8', " +
                "TO_DATE('2019/07/26 15:00:11','YYYY-MM-DD HH24:MI:SS'), 'Batch', NULL, SYSDATE)";

        System.out.println("Executing query: " + sqlQuery);
        String requestBody = "{\n" +
                "  \"query\": \"" + sqlQuery + "\"\n" +
                "}";

        // Print the query to verify its format
        System.out.println("Executing query1:");
        System.out.println(sqlQuery);

        Response response = demoAPI.getValueFromGetAPI(requestBody);
        String responseBody = response.getBody().asString();
        System.out.println("Response Body from query1:");
        System.out.println(responseBody);

        // Check if the insert was successful
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            System.out.println("Insert query executed successfully.");
        }


    }

    public void selectAccCancelationData() {
        // Construct the SQL query for query2
        String sqlQuery = "select TO_CHAR(LOCK_DT,'YYYY-MM-DD HH24:MI:SS') AS LOCK_DT,LOGIN_LOCK_FLG,CTL_REASON_CD FROM ORDER_CTL_BY_CLNT_DETAIL A WHERE A.LOCK_DT >= SYSDATE-1 AND LOGIN_LOCK_FLG = 1 and CTL_REASON_CD = 12 order by LOCK_DT desc";

        String requestBody = "{\n" +
                "  \"query\": \"" + sqlQuery + "\"\n" +
                "}";

        Response response = demoAPI.getValueFromGetAPI(requestBody);
        String responseBody = response.getBody().asString();
        System.out.println("Response Of SelectAccCancelationData:" + responseBody);
        //dbEvidenceCapture("AccountCancelation","BeforeAccountCancelation",1);
        Response response1 = demoAPI.cronJobAccCancelation();
        // dbEvidenceCapture("AccountCancelation","AfterAccountCancelation",1);
        basePage.waitForSeconds(3);
        System.out.println(response1);

        //loginTest.login( testData.getUsername(), testData.getPassword(), testData.getaccDeviceCtrlNo());
        menuPage.clickOnMenu();
        menuPage.goToNotificationSetting();
        basePage.waitForSeconds(4);
        accountCancel.verifyAccountCancelation(softAssert);
        captureScreenshot("Alert_setting_page_after account cancel");
        menuPage.goToMenuPage();
        menuPage.gotoAlertStatusList();
        accountCancel.verifyAccountCancelationInStatusList(softAssert);
        captureScreenshot("alert status list after account cancel");
    }


}
