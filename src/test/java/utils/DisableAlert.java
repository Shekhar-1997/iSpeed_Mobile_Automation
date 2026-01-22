package utils;

import org.testng.asserts.SoftAssert;
import tests.BaseTest;

import static tests.PageOjectManager.*;


public class DisableAlert extends BaseTest {


    public void disableAlert(SoftAssert softAssert,String alertType,String stockName) {

        basePage.waitForSeconds(3);
        if(alertType.equalsIgnoreCase("index")) {
            disableAlertForIndex(stockName, softAssert);
        }else {
            disableAlertForStock(stockName, softAssert);
        }

    }

    private void disableAlertForStock(String stockName,SoftAssert softAssert) {
        basePage.waitForSeconds(4);
        editAlertSettingPage.selectStockFromSetting(stockName);
        basePage.waitForSeconds(3);
        disableAllAlertToggles(softAssert);
        editAlertSettingPage.clickOnRegisterBtn();
        basePage.waitForSeconds(8);
        captureScreenshot("after_disable_all4_alerts.png");

    }

    private void disableAlertForIndex(String indexStockName,SoftAssert softAssert) {
        editAlertSettingPage.selectStockFromSetting(indexStockName);
        basePage.waitForSeconds(3);
        allToggle(softAssert);
        editAlertSettingPage.clickOnRegisterBtn();
        editAlertSettingPage.waitForSettingScreenTobeVisible();
        basePage.waitForSeconds(6);
        captureScreenshot("after_disable_all4_alerts.png");


    }

    private void allToggle(SoftAssert softAssert) {
        editAlertSettingPage.disablePriceUpAlertToggle(softAssert);
        editAlertSettingPage.disablePriceDownAlertToggle(softAssert);
        captureScreenshot("disable_priceUp_priceDown_alert.png");
        editAlertSettingPage.selectPreviousDayRatioTab(softAssert);
        editAlertSettingPage.disablePreviousDayRatioUpToggle(softAssert);
        editAlertSettingPage.disablePreviousDayRatioDownToggle(softAssert);
        captureScreenshot("disablePreviousDayRatioUp_and_DownTogglealert.png");

    }

    private void disableAllAlertToggles(SoftAssert softAssert) {
        allToggle(softAssert);
        //editAlertSettingPage.disableNewsToggle();

    }
    public void disableNewsAlert(SoftAssert softAssert,String stockName,String type){

        basePage.waitForSeconds(5);
        editAlertSettingPage.selectStockFromSetting(stockName);
        basePage.waitForSeconds(5);
        editAlertSettingPage.selectPresentValueTab();
        editAlertSettingPage.disableNewsToggle(softAssert);
        captureScreenshot("after_disableNewsToggle.png");
        basePage.waitForSeconds(2);
        editAlertSettingPage.clickOnRegisterBtn();
        basePage.waitForSeconds(2);
        handlePopup.checkIfErrorPopup();
        editAlertSettingPage.waitForSettingScreenTobeVisible();
        basePage.waitForSeconds(4);
        captureScreenshot("alert_setting_list_for_disable_news.png");
      //  dbEvidenceCapture("disable"+type+"NewsAlert","disabele"+type+"NewsAlert",1);
    }


}