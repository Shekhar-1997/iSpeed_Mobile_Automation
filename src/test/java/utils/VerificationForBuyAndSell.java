package utils;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.VerifyPushNotification;
import tests.BaseTest;
import utils.BasePage;

import static tests.PageOjectManager.*;

public class VerificationForBuyAndSell extends BaseTest {

    public boolean verifyTriggeredAlertIndevice(SoftAssert softAssert, String alertType, String quantity, String currentPrice, String stockDetail, String fileName) {
        boolean alert = false;
        VerifyPushNotification pushNotification = new VerifyPushNotification(driver);
        String pushMessage;
        if (deviceType.equalsIgnoreCase("ios")) {
            pushMessage = pushNotificationIOS.getSinglePushNotification(stockDetail, fileName);
        } else {
            pushMessage = pushNotification.verifyExcecutionAlert(stockDetail, fileName);
        }
        if (pushMessage != null && !pushMessage.isEmpty()) {

            if (pushMessage.contains(quantity) && pushMessage.contains(currentPrice)) {

                if (alertType.equals("buy")) {
                    alert = true;
                    logToAllure("execution alert got triggered.");
                    Assert.assertEquals(pushMessage, "現物買い " + quantity + "株 " + currentPrice + "円で約定しました。");//現物売り 200株 509円で約定しました。
                } else {
                    alert = true;
                    logToAllure("execution alert got triggered.");
                    Assert.assertEquals(pushMessage, "現物売り " + quantity + "株 " + currentPrice + "円で約定しました。");//現物買い 200株 509円で約定しました。
                }

            }
        } else {

            logToAllure("execution alert not triggered.");
        }

        driver.navigate().back();
        return alert;
    }

    public void verifyTriggeredAlertInstatusList(SoftAssert softAssert, String alertType, String quantity, String currentPrice, String stockName) {
        menuPage.goToMenuPage();
        menuPage.gotoAlertStatusList();
        if (alertType.equals("buy")) {
            alert.verifyBuySellInAlertStatusList(quantity, currentPrice, "buy", stockName, softAssert);
        } else if (alertType.equals("sell")) {
            alert.verifyBuySellInAlertStatusList(quantity, currentPrice, "sell", stockName, softAssert);
        }
    }

    @Step("{0}")
    public void logToAllure(String message) {
        // This method logs messages to Allure report
        System.out.println(message); // Optional: Also print to console
    }
}
