package tests;

import io.qameta.allure.Description;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.CreateAllAlertUtil;

import static tests.PageOjectManager.*;

public class SetAllAlert extends BaseTest {

    private SoftAssert softAssert;
  //  private createAllAlertUtil createAlertUtil;


    @BeforeMethod
    public void setSoftAssert() {
        softAssert = new SoftAssert();
       createAlertUtil.prerequisiteForAlertSettingScreen();



    }

    @Description("Create priceUp alert for JP and Verify triggered alert")
    @Test(priority = 1)
    public void CreateJPPriceUpAlert() {
        //demoPageSourse.alertSettingPage();
        alertSettingPage.goToAlertSettingPage();
        handlePopup.checkIfErrorPopup();

        createAlertUtil.createAlert(softAssert, testData.getJpStockpriceUp(), "JP", "set", "up", String.valueOf(testData.getDomesticIncrementAmount()), "JPPriceUpAlert",false,false,"priceUp");

        softAssert.assertAll();

    }

    @Description("Create priceDown alert for JP and Verify triggered alert")
    @Test(priority = 2)
    public void createJPPriceDownAlert() {

        alertSettingPage.goToAlertSettingPage();
       // demoPageSourse.alertSettingPage();
        createAlertUtil.  createAlert(softAssert, testData.getJpStockpriceDown(), "JP", "set", "down", String.valueOf(testData.getDomesticDecrementAmount()), "JPPriceDownAlert",false,false,"priceDown");

        softAssert.assertAll();

    }

    @Description("Create previousDayUp alert for JP and Verify triggered alert")
    @Test(priority = 3)
    public void createJPPreviousUpAlert() {

        alertSettingPage.goToAlertSettingPage();
        createAlertUtil. createAlert(softAssert, testData.getJpStockpreviousUp(), "JP", "set", "up", String.valueOf(testData.getPreviousDayRatioIncrementAmount1()), "JPPreviousDayDownAlert",true,false,"previousDayDown");

        softAssert.assertAll();

    }

    @Description("Create previousDayDown alert for JP and Verify triggered alert")
    @Test(priority = 4)
    public void createJPPreviousDownAlert() {

        alertSettingPage.goToAlertSettingPage();
        createAlertUtil.createAlert(softAssert, testData.getJpStockpreviousDown(), "JP", "set", "down", String.valueOf(testData.getPreviousDayRatioDecrementAmount1()), "JPPreviousDayDownAlert",true,false,"previousDayDown");

        softAssert.assertAll();

    }

    @Description("Create priceUp alert for US and Verify triggered alert")
    @Test(priority = 5)
    public void createUSPriceUpAlert() {
        alertSettingPage.goToAlertSettingPage();
        createAlertUtil. createAlert(softAssert, testData.getTickerCodePriceUp(), "US", "set", "up", String.valueOf(testData.getUsIncrementAmount()), "USPriceUPAlert",false,false,"priceUp");

        softAssert.assertAll();
    }

    @Description("Create priceDown alert for US and Verify triggered alert")
    @Test(priority = 6)
    public void createUSPriceDownAlert() {
        alertSettingPage.goToAlertSettingPage();
        createAlertUtil.createAlert(softAssert, testData.getTickerCodePriceDown(), "US", "set", "down", String.valueOf(testData.getUsDecrementAmount()), "USPriceDownAlert",false,false,"priceDown");;
        softAssert.assertAll();
    }

    @Description("Create previousDayUp alert for US and Verify triggered alert")
    @Test(priority = 7)
    public void createUSpreviousDayUpAlert() {
        alertSettingPage.goToAlertSettingPage();
        createAlertUtil.createAlert(softAssert, testData.getTickerCodePreviousDayUp(), "US", "set", "up",  String.valueOf(testData.getPreviousDayRatioIncrementAmount1()), "USPreviousDayUpAlert",true,false,"previousDayUp");
        softAssert.assertAll();
    }

    @Description("Create previousDayDown alert for US and Verify triggered alert")
    @Test(priority = 8)
    public void createUSpreviousDayDownAlert() {
        alertSettingPage.goToAlertSettingPage();
        createAlertUtil.createAlert(softAssert, testData.getTickerCodePreviousDayDown(), "US", "set", "down", String.valueOf(testData.getPreviousDayRatioDecrementAmount1()), "USPreviousDayDownAlert",true,false,"previousDayDown");
        softAssert.assertAll();
    }

    @Description("Create priceUp alert for  IndexAlertNippon and Verify triggered alert")
    @Test(priority = 9)
    public void createPriceUpIndexAlertNippon() {
        alertSettingPage.goToAlertSettingPage();
        createAlertUtil.createPriceUpIndexAlert(testData.getIndexJPstockpriceUp(), softAssert,"nippon");
        softAssert.assertAll();
    }

    @Description("Create priceDown alert for  IndexAlertNippon and Verify triggered alert")
    @Test(priority = 10)
    public void createPriceDownIndexAlertNippon() {
       alertSettingPage.goToAlertSettingPage();
        createAlertUtil.createPriceDownIndexAlert(testData.getIndexJPstockpricedown(), softAssert,"nippon");
        softAssert.assertAll();
    }

    @Description("Create previousDayUp alert for  IndexAlertNippon and Verify triggered alert")
    @Test(priority = 11)
    public void createPrevoiusDayUpIndexAlertNippon() {
        alertSettingPage.goToAlertSettingPage();
        createAlertUtil.createPreviousDayUpIndexAlert(testData.getIndexJPstockpreviousUp(), softAssert,"nippon");
        softAssert.assertAll();
    }

    @Description("Create previousDayDown alert for  IndexAlertNippon and Verify triggered alert")
    @Test(priority = 12)
    public void createPreviousDayDownIndexAlertNippon() {
        alertSettingPage.goToAlertSettingPage();
        createAlertUtil.createPreviousDayDownIndexAlert(testData.getIndexJPstockpreviousDown(), softAssert,"nippon");
        softAssert.assertAll();
    }

    @Description("Create previousDayDown alert for  IndexAlertNippon and Verify triggered alert")
    @Test(priority = 13)
    public void createPriceUpIndexAlertUS() {
        alertSettingPage.goToAlertSettingPage();
        createAlertUtil.createPriceUpIndexAlert(testData.getIndexUSstockpriceUp(), softAssert,"US");
        softAssert.assertAll();
    }

    @Description("Create previousDayDown alert for  IndexAlertNippon and Verify triggered alert")
    @Test(priority = 14)
    public void createPriceDownIndexAlertUS() {
        alertSettingPage.goToAlertSettingPage();
        createAlertUtil.createPriceDownIndexAlert(testData.getIndexUSstockpriceDown(), softAssert,"US");
        softAssert.assertAll();
    }

    @Test(priority = 15)
    public void createPrevoiusDayUpIndexAlertUS() {
        alertSettingPage.goToAlertSettingPage();
        createAlertUtil.createPreviousDayUpIndexAlert(testData.getIndexUSstockpreviousUp(), softAssert,"US");
        softAssert.assertAll();
    }

    @Test(priority = 16)
    public void createPreviousDayDownIndexAlertUS() {
        alertSettingPage.goToAlertSettingPage();
        createAlertUtil.createPreviousDayDownIndexAlert(testData.getIndexUSstockpreviousDown(), softAssert,"US");
        softAssert.assertAll();
    }

    @Test(priority = 17)
    public void createPriceUpIndexAlertSakimono() {
        alertSettingPage.goToAlertSettingPage();
        createAlertUtil.createPriceUpIndexAlert(testData.getIndexFutureStockPriceUp(), softAssert,"sakimono");
        softAssert.assertAll();
    }

    @Test(priority = 18)
    public void createPriceDownIndexAlertSakimono() {
        alertSettingPage.goToAlertSettingPage();
        createAlertUtil.createPriceDownIndexAlert(testData.getIndexFutureStockPriceDown(), softAssert,"sakimono");
        softAssert.assertAll();
    }

    @Test(priority = 19)
    public void createPrevoiusDayUpIndexAlertSakimono() {
        alertSettingPage.goToAlertSettingPage();
        createAlertUtil.createPreviousDayUpIndexAlert(testData.getIndexFutureStockPreviousUp(), softAssert,"sakimono");
        softAssert.assertAll();
    }

    @Test(priority = 20)
    public void createPreviousDayDownIndexAlertSakimono() {
        alertSettingPage.goToAlertSettingPage();
        createAlertUtil.createPreviousDayDownIndexAlert(testData.getIndexFutureStockPreviousDown(), softAssert,"sakimono");
        softAssert.assertAll();
    }

    @AfterMethod
    public void afterMethod(ITestResult  result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(result.getName() + "_failure.png");
        }
        menuPage.goToMenuPage();
    }



}
