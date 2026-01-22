package tests;

import io.restassured.response.Response;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.VerifyPushNotification;
import utils.*;

import static tests.PageOjectManager.*;
import static tests.PageOjectManager.createAlertUtil;

public class ExpiryAlertPreviousday extends BaseTest{

    private static CustomSoftAssert softAssert;
    private static DemoAPI demoAPI = new DemoAPI();



    @Test(priority = 1)
    public void createExpiryAlert(){

        softAssert=new CustomSoftAssert();
        createAlertUtil.prerequisiteForAlertSettingScreen();
        String JPstokName=createAlertUtil.createAlert(softAssert,testData.getJpStockpreviousDown(),"JP","create","down",String.valueOf(testData.getPreviousDayRatioDecrementAmount()),"account cancellation previousday down",true,false,"previousDayDown");
        dbEvidenceCapture("ExpiryAlert","setExpiryAlertBefore",1,false);
        expiryAlertUtils.expiryAlert(1);
        basePage.waitForSeconds(2);
        Response response = demoAPI.cronJobAccCancelation();
        basePage.waitForSeconds(8);
        dbEvidenceCapture("ExpiryAlert","setExpiryAlertafter",1,false);
        createAlertUtil.checkStatusOfToggleForTriggeredAlertsExpiry(JPstokName,softAssert,"setExpiry","Expiry",1);
        setCorporationAlert.verifyExpiryAlertPush(softAssert);
        //String stockName,SoftAssert softAssert,String alertType,String alertName,int waiTime
    }
    @AfterMethod
    public void afterMethod(ITestResult result) {
        afterMethodSteps(softAssert,result);
    }
}

