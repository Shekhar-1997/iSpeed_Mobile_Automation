package utils;

import io.appium.java_client.AppiumDriver;
import pages.DeviceRegistrationUtil;
import pages.LoginPage;
import tests.BaseTest;

import java.io.IOException;

import static tests.BaseTest.testData;

public class DeviceRegistationLogin {
    private LoginPage loginPage;
    DeviceRegistrationUtil deviceRegistrationUtil = new DeviceRegistrationUtil();
    private BaseTest baseTest = new BaseTest();

    public void login(AppiumDriver driver, String status) throws IOException {
        loginPage = new LoginPage(driver);
        loginPage.agrementPage();
        loginPage.performLogin(testData.getUsername(), testData.getPassword());//.performLoginWithBasicSetup( testData.getUsername(),  testData.getPassword());
        baseTest.captureScreenshot("LoginPage.png");
        loginPage.clickLoginButton();
        loginPage.proceedForSetup();
        baseTest.captureScreenshot("menuScreen.png");
        loginPage.closeNisaPopup();

        baseTest.captureScreenshot("Allow pushNotification.png");
        if (status.equals("ON")) {
            deviceRegistrationUtil.queryToFetchCountOfsDevice("DeviceRegisterationON", "Device Count Before");
            loginPage.allowPushNotification();
            deviceRegistrationUtil.queryToFetchCountOfsDevice("DeviceRegisterationON", "Device Count After");
        } else if (status.equals("OFF")) {
            deviceRegistrationUtil.queryToFetchCountOfsDevice("DeviceRegisterationOff", "Device Count Before");
            loginPage.dontAllowPushNotification();
            deviceRegistrationUtil.queryToFetchCountOfsDevice("DeviceRegisterationOff", "Device Count After");
            return;
        }

        loginPage.noticeOfagreementDocumentPopup();

    }

}




