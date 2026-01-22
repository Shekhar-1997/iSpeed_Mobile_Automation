package tests;

import io.qameta.allure.Feature;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.CustomSoftAssert;

import static tests.PageOjectManager.*;

public class DisableAllAlert extends BaseTest {

    public CustomSoftAssert softAssert;

    @BeforeMethod
    public void setSoftAssert() {
        softAssert = new CustomSoftAssert();
        menuPage.goToNotificationSetting();

    }

    @Feature("disable and validate disable alert")
    @Test(priority = 1)
    public void disableJPAlert() {
        basePage.waitForSeconds(3);
        String JPstockName = jpAlert.createJPAlert(softAssert);//db
        dbEvidenceCapture("disableJPAlert", "setAlert", 4, false);
        alertSettingPage.goToAlertSettingPage();
        disable.disableAlert(softAssert, "jp", JPstockName);
        dbEvidenceCapture("disableJPAlert", "disableAlert", 4, false);
        softAssert.assertAll();

    }

    @Feature("disable and validate disable alert")
    @Test(priority = 4)
    public void disableUSAlert() {
        String usStockName = usalert.createUSAlert(softAssert);
        dbEvidenceCapture("disableUSAlert", "setAlert", 4, false);
        alertSettingPage.goToAlertSettingPage();
        basePage.waitForSeconds(3);
        disable.disableAlert(softAssert, "us", usStockName);
        dbEvidenceCapture("disableUSAlert", "disableAlert", 4, false);
        softAssert.assertAll();

    }

    @Feature("disable and validate disable alert")
    @Test(priority = 2)
    public void disableIndexJPAlert() {
        indexAlert.createIndexAlert(testData.getIndexNippon(), "create", softAssert, "indexAlertJP");
        dbEvidenceCapture("disableIndexJPAlert", "setAlert", 4, false);
        alertSettingPage.goToAlertSettingPage();
        basePage.waitForSeconds(3);
        disable.disableAlert(softAssert, "index", testData.getIndexNippon());
        dbEvidenceCapture("disableIndexJPAlert", "disableAlert", 4, false);
        softAssert.assertAll();

    }

    @Feature("disable and validate disable alert")
    @Test(priority = 5)
    public void disableIndexUsAlert() {
        indexAlert.createIndexAlert(testData.getIndexUS(), "create", softAssert, "indexAlertUS");
        dbEvidenceCapture("disableIndexUsAlert", "setAlert", 4, false);
        alertSettingPage.goToAlertSettingPage();
        basePage.waitForSeconds(3);
        disable.disableAlert(softAssert, "index", testData.getIndexUS());
        dbEvidenceCapture("disableIndexUsAlert", "disableAlert", 4, false);
        softAssert.assertAll();

    }

    @Feature("disable and validate disable alert")
    @Test(priority = 3)
    public void disableIndexFutureAlert() {
        indexAlert.createIndexAlert(testData.getIndexSakimono(), "create", softAssert, "indexAlertFuture");
        dbEvidenceCapture("disableIndexFutureAlert", "setAlert", 4, false);
        alertSettingPage.goToAlertSettingPage();
        basePage.waitForSeconds(3);
        disable.disableAlert(softAssert, "index", testData.getIndexSakimono());
        dbEvidenceCapture("disableIndexFutureAlert", "disableAlert", 4, false);
        softAssert.assertAll();

    }

    @AfterMethod
    public void afterMethod(ITestResult result) {

        afterMethodSteps( softAssert,result);
    }

}
