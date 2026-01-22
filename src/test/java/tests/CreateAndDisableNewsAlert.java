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

public class CreateAndDisableNewsAlert extends BaseTest {

    public CustomSoftAssert softAssert;


    @BeforeMethod
    public void setSoftAssert() {

        softAssert = new CustomSoftAssert();
        menuPage.goToNotificationSetting();
    }

    @Feature("Create and Validate Index Alert Sakimono")
    @Test(priority = 1)
    public void createJPNewsAlert() {
        createAlertUtil.createAllAlert(softAssert,testData.getStockCode(),"JP","create","setJP News Alert", "setJP News Alert",false,true);
        dbEvidenceCapture("createJPNewsAlert", "setAlert", 1, false);
        softAssert.assertAll();
    }

    @Feature("disable and validate disable alert")
    @Test(priority = 2)
    public void disableJPNewsAlert() {
        String JPstockName=createAlertUtil.createAllAlert(softAssert,testData.getStockCode(),"JP","create","setJP News Alert", "setJP News Alert",false,true);
        System.out.println(JPstockName);
        dbEvidenceCapture("disableJPNewsAlert", "setAlert", 1, false);
        alertSettingPage.goToAlertSettingPage();
        basePage.waitForSeconds(3);
        disable.disableNewsAlert(softAssert, JPstockName, "JP");
        dbEvidenceCapture("disableJPNewsAlert", "disableAlert", 1, false);
        softAssert.assertAll();
    }

    @Feature("Create and Validate Index Alert Sakimono")
    @Test(priority = 3)
    public void createUSNewsAlert() {
        createAlertUtil.createAllAlert(softAssert,testData.getStockNameUS(),"US","create","set US News Alert", "set US News Alert",false,true);
        dbEvidenceCapture("createUSNewsAlert", "setAlert", 1, false);
        softAssert.assertAll();
    }

    @Feature("disable and validate disable alert")
    @Test(priority = 4)
    public void disableUSNewsAlert() {
       String USstockName= createAlertUtil.createAllAlert(softAssert,testData.getStockNameUS(),"US","create","set US News Alert", "set US News Alert",false,true);
        dbEvidenceCapture("disableUSNewsAlert", "setAlert", 1, false);
        alertSettingPage.goToAlertSettingPage();
        basePage.waitForSeconds(2);
        disable.disableNewsAlert(softAssert, USstockName, "US");
        dbEvidenceCapture("disableUSNewsAlert", "disableAlert", 1, false);
        softAssert.assertAll();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {

        afterMethodSteps(softAssert,result);
    }
}
