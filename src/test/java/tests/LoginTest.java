package tests;

import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pages.*;

import static tests.PageOjectManager.basePage;
import static tests.PageOjectManager.loginPage;
import static tests.PageOjectManager.pageSourceUtil;
//import static tests.PageOjectManager.demoPageSourse;



//import utils.PushDBAPICreateUpdate;

public class LoginTest extends BaseTest {

    // Test to validate the login functionality with valid credentials
    @Feature("Login feature")
    @Test(priority = 1)
    public void loginTest() {
        login(testData.getUsername(), testData.getPassword(), testData.getaccDeviceCtrlNo(), "loginUser", "login");
    }

    // Method to perform login, handle post-login setups, popups, and log inactive user data
    public void login(String userName, String password, String accountDeviceCntrlNo, String alertType, String methodName) {
      loginPage.agrementPage();

        if (loginPage.isHomePage()) {
            loginPage.performLoginFromMenu(userName, password);

            if (loginPage.isNoticeOfAgreementDocumentPopup()) {
                captureScreenshot("popupScreen.png");
                loginPage.closePopupAfterLogin();
                //screenshotUtil.captureFullPageScreenshot("menuPage_Screen_Capture.png");
               // pageSourceUtil.capturePageSource("menupage_Source.txt");
                //demoPageSourse.alertSettingPage();
                captureScreenshot("menuScreen.png");
            }

            if (loginPage.isSetup()) {
                captureScreenshot("setupScreen.png");
                loginPage.setBasicSetup();
                captureScreenshot("popups.png");
                loginPage.closePopupsAfterLogin();
            }

        } else {
            loginPage.performLogin(testData.getUsername(), testData.getPassword());
            captureScreenshot("loginPage.png");
            basePage.waitForSeconds(2);
            System.out.println("am above screenshotUtil ");
            loginPage.clickLoginButton();
            System.out.println("am below screenshotUtil ");
            loginPage.proceedForSetup();
            captureScreenshot("setupScreen.png");

            loginPage.allowNotification();
            captureScreenshot("popupscreen.png");

            loginPage.closeNisaPopup();
            loginPage.allowPushNotification();
            loginPage.noticeOfagreementDocumentPopup();
            captureScreenshot("noticePopup.png");


        }

        //inActiveUserDbData(methodName, alertType);
    }
}
