package utils;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import tests.BaseTest;

import static tests.PageOjectManager.*;


public class LogoutTest extends BaseTest {
    private By logout;
    private By logoutAccept;
    private By menuIcon;

    private void locators() {
        if (deviceType.equalsIgnoreCase("ios")) {
            logout = By.xpath("//XCUIElementTypeStaticText[@name='ログアウト']");
            logoutAccept = By.xpath("(//XCUIElementTypeButton[@name='ログアウト'])[2]");
        } else {
            logout = By.xpath("//android.widget.Button[@text='ログアウト']");
            logoutAccept = By.id("android:id/button1");
            menuIcon = By.xpath("//XCUIElementTypeButton[@name='メニュー']");
        }
    }

    public void logout(String methodName, String alertType) {
        locators();
        try {
            if (!editAlertSettingPage.isEnabled(menuIcon)) {
                editAlertSettingPage.waitForElementToBeVisible(menuIcon);
                editAlertSettingPage.clickElement(menuIcon);
            }
        } catch (Exception e) {
            System.out.println("menu icon not visible");
        }
        basePage.waitForElementToBeVisible(logout);
        basePage.clickElement(logout);
        basePage.waitForSeconds(2);
        new BaseTest().captureScreenshot("LogoutPopup.png");
        basePage.waitForSeconds(3);
        basePage.waitForElementToBeVisible(logoutAccept);
        basePage.clickElement(logoutAccept);
        basePage.waitForSeconds(3);
        new BaseTest().captureScreenshot("AfterLogout.png");

        // commented because not requird in screen navigation
        //new BaseTest().inActiveUserDbData(methodName,alertType);

    }

    //for navigation

    public void logoutTest() {
        locators();
        basePage.waitForSeconds(2);
        editAlertSettingPage.waitForElementToBeClickable(menuIcon);
        basePage.clickElement(menuIcon);
        basePage.waitForElementToBeVisible(logout);
        basePage.clickElement(logout);
        basePage.waitForSeconds(2);
        new BaseTest().captureScreenshot("LogoutPopup.png");
        basePage.waitForSeconds(3);
        basePage.waitForElementToBeVisible(logoutAccept);
        basePage.clickElement(logoutAccept);
        basePage.waitForSeconds(3);
        new BaseTest().captureScreenshot("AfterLogout.png");

        // commented because not requird in screen navigation
        //new BaseTest().inActiveUserDbData(methodName,alertType);

    }

}
