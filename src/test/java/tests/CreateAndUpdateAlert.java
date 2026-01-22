package tests;

import io.qameta.allure.Feature;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.*;

import static tests.PageOjectManager.*;


public class CreateAndUpdateAlert extends BaseTest {

    public CustomSoftAssert softAssert;

    @BeforeMethod
    public void setSoftAssert() {
        softAssert = new CustomSoftAssert();
        menuPage.goToNotificationSetting();
    }


    @Feature("Create and Validate JP Alert")
    @Test(priority = 1)
    public void jpAlertSetting() {

        String JPstockName = jpAlert.createJPAlert(softAssert);
        dbEvidenceCapture("jpAlertSetting", "setJPalert", 4, false);
        jpAlert.updateJPAlert(JPstockName, softAssert, "jpAlertSetting", "updateAlert");
        softAssert.assertAll();

    }

    @Feature("Create and Validate US Alert")
    @Test(priority = 4)
    public void usAlert() {

        String USstockName = usalert.createUSAlert(softAssert);
        dbEvidenceCapture("USCreateAndUpdate", "setUsAlert", 4, false);
        usalert.updateUSAlert(USstockName, softAssert, "USCreateAndUpdate", "updateAlert");
        softAssert.assertAll();
    }

    @Feature("Create and Validate Index Alert Nippon")
    @Test(priority = 2)
    public void indexAlertJP() {
        indexAlert.createIndexAlert(testData.getIndexNippon(), "create", softAssert,"indexAlertJP");
        dbEvidenceCapture("JPindexAlertSetting", "setIndexAlert", 4, false);
        indexAlert.updateIndexAlert(testData.getIndexNippon(), softAssert, "JPindexAlertSetting", "updateIndexAlert");
        basePage.waitForSeconds(3);
        softAssert.assertAll();

    }

    @Feature("Create and Validate Index Alert US")
    @Test(priority = 5)
    public void indexAlertUS() {
        indexAlert.createIndexAlert(testData.getIndexUS(), "create", softAssert,"indexAlertUS");
        dbEvidenceCapture("USindexAlertSetting", "setIndexAlertUS", 4, false);
        indexAlert.updateIndexAlert(testData.getIndexUS(), softAssert, "USindexAlertSetting", "updateIndexAlertUS");
        basePage.waitForSeconds(3);
        softAssert.assertAll();
    }

    @Feature("Create and Validate Index Alert Sakimono")
    @Test(priority = 3)
    public void indexAlertFuture() {
        indexAlert.createIndexAlert(testData.getIndexSakimono(), "create", softAssert,"indexAlertFuture");
        dbEvidenceCapture("FutureindexAlertSetting", "setIndexAlertUS", 4, false);
        indexAlert.updateIndexAlert(testData.getIndexUS(), softAssert, "FutureindexAlertSetting", "updateIndexAlertFuture");
        basePage.waitForSeconds(3);
        softAssert.assertAll();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        afterMethodSteps(softAssert,result);
    }


}
