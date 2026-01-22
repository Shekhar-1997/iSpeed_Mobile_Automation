package tests;

import io.appium.java_client.AppiumDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.DeviceRegistrationUtil;
import utils.*;

import java.io.IOException;

import static tests.BaseTest.testData;
import static tests.PageOjectManager.deviceRegisteration;

public class DeviceRegistration {

    private DemoAPI demoAPI = new DemoAPI();
    BaseTest baseTest = new BaseTest();
    CustomSoftAssert softAssert = new CustomSoftAssert();
    private DeviceRegistrationUtil deviceReg = new DeviceRegistrationUtil();

    @Test(priority = 1)
    public void deviceRegistrationOn() throws IOException {
        AppiumDriver driver = baseTest.setUp1();
        DeviceRegistationLogin login = new DeviceRegistationLogin();
        // call the count
        login.login(driver, "ON");
        checkEndpointArn();
    }

    public void checkEndpointArn() {
        String endPointArn = deviceRegisteration.queryToFetchEndPointArn();
        System.out.println(endPointArn);
        deviceReg.enterEndpointArn(testData.getAccount(), testData.getUserNameAWS(), testData.getPasswordAWS(), endPointArn);

    }

    @Test(priority = 3)
    public void deviceRegistrationOff() throws IOException {
        BaseTest basetest = new BaseTest();
        AppiumDriver driver = basetest.setUp1();
        DeviceRegistationLogin login = new DeviceRegistationLogin();
        // call the count
        login.login(driver, "OFF");
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        // Attach all captured screenshots to Allure in teardown
        baseTest.afterMethodSteps(softAssert, result);
    }
}
