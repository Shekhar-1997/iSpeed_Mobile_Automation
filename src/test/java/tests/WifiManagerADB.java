package tests;
//WifiManagerADB

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.VerifyPushNotification;
import utils.CreateAllAlertUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import static tests.PageOjectManager.*;

public class WifiManagerADB extends BaseTest {
    private static SoftAssert softAssert;

    @BeforeMethod
    public void setSoftAssert() {
        softAssert = new SoftAssert();
        createAlertUtil.prerequisiteForAlertSettingScreen();
    }

    @Test(priority = 1)
    public static void checkAlertWithInternetOff() {
        // Example usage
        System.out.println("WiFi Status: " + wifiManager.checkWifiStatus());
        wifiManager.enableWifi();
        System.out.println("WiFi enabled.");
        List<String> data = wifiManager.createAlertAndDisableWifi(softAssert);
        String stockName = data.get(0);
        String price = data.get(1);
        basePage.waitForSeconds(10);
        softAssert.assertFalse(createAlertUtil.verifyPushNotificationForWifi(stockName, price, "priceDown", "JPpriceDown", softAssert));
        // System.out.println("WiFi disabled.");
        wifiManager.enableWifi();
        basePage.waitForSeconds(10);
        softAssert.assertTrue(createAlertUtil.verifyPushNotification(stockName, price, "priceDown", "JPpriceDown", softAssert));
        System.out.println("WiFi enabled");
    }



}


