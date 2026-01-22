package tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.CustomSoftAssert;
import utils.TestDataObject;
import utils.TestDataProvider;

import static tests.PageOjectManager.*;

public class MultipleAccountSingleUserCreateAlert extends BaseTest {
    /*Login with user1
     * create all 4 Alert with nearest values
     * Logout from user 1
     * Login with user2
     * check whether created alert is present if yes then check its set prices that should not be same.

single device multiple user
login from user1
set one alerts
take db data
logout from user1

login from user2
set one alerts
take db data
logout from user2               */


    CustomSoftAssert softAssert = new CustomSoftAssert();


    @Test(priority = 1)
    public void mutipleAccountJP() {
        multiAccountCreateAlert("JP", testData.getStockCode(), testData.getMultipleAccountStockCode2());


    }

    @Test(priority = 2)
    public void mutipleAccountUS() {

        multiAccountCreateAlert("US", testData.getStockNameUS(), testData.getMultipleAccountUSstockName());

    }

    public void multiAccountCreateAlert(String region, String stockName, String stockName2) {
        logoutTest.logout("logoutFromCurrentUser", "multipleAccountsCreate");
        login.login(testData.getUsername(), testData.getPassword(), testData.getaccDeviceCtrlNo(), "LoginFromUser1", "multipleAccountsCreate");
        createAlertUtil.verifyMenuPage();
        createAlertUtil.prerequisiteForAlertSettingScreen();
        createAlertUtil.preRequisite(stockName, softAssert);
        String JPstockName = createAlert(region, "SetAlertInMultiAccount" + region + "", "multiAccountCreateAlert");
        menuPage.goToMenuPage();
        logoutTest.logout("logoutFromUser1", "multipleAccountsCreate");
        login.login(testData.getUsernameTwo(), testData.getPasswordTwo(), testData.getAccDeviceCtrlNoTwo(), "LoginFromUser2", "multipleAccountsCreate");
        createAlertUtil.verifyMenuPage();
        createAlertUtil.prerequisiteForAlertSettingScreen();
        createAlertUtil.preRequisite(stockName2, softAssert);
        String JPstockNameUser2 = createAlert(region, "SetAlertInMultiAccount" + region + "", "multiAccountUpdateAlert");
        menuPage.goToMenuPage();
        logoutTest.logout("logoutFromUser2", "multipleAccountsCreate");
        login.login(testData.getUsername(), testData.getPassword(), testData.getaccDeviceCtrlNo(), "LoginFromUser1", "multipleAccountsCreate");
        preRequisiteForVerification();
        softAssert.assertFalse(editAlertSettingPage.isAlertCreated(JPstockNameUser2, softAssert));

    }

    public String createAlert(String region, String alertType, String sheetName) {
        String JPstockName = createAlertUtil.getStockName(region);
        editAlertSettingPage.setPriceAlert(region, "up", testData.getIncrementAmount(), softAssert, "create", testData.getIncrementPercent());
        captureScreenshot("" + alertType + "_price_up.png");
        editAlertSettingPage.selectPreviousDayRatioTab(softAssert);
        editAlertSettingPage.setPreviousDayAlert("up", testData.getIncrementAmount(), softAssert, "create", testData.getIncrementPercent());
        createAlertUtil.createAlertWithSnapshot(softAssert, sheetName, "create");
        return JPstockName;
    }

    public void preRequisiteForVerification() {
        menuPage.goToMenuPage();
        boolean menu = menuPage.verifyMenuPage();
        if (!menu) {
            menuPage.goToMenuPage();
        }
        loginPage.noticeOfagreementDocumentPopup();
        captureScreenshot("menuPage.png");
        basePage.waitForSeconds(2);
        menuPage.goToNotificationSetting();
        basePage.waitForSeconds(2);
        handlePopup.checkIfErrorPopup();
        basePage.waitForSeconds(5);
        boolean res = alertSettingPage.goToEditAlertSettingPage(softAssert);
        basePage.waitForSeconds(2);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        // Attach all captured screenshots to Allure in teardown
        afterMethodSteps(softAssert, result);
    }

}
