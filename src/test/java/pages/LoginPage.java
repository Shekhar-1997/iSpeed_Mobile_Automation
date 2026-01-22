package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tests.BaseTest;
import utils.BasePage;
import org.testng.Assert;

import static tests.BaseTest.deviceType;
import static tests.PageOjectManager.basePage;

public class LoginPage extends BasePage {
    BaseTest baseTest = new BaseTest();
    public By loginIdField;
    public By pushNotifcation;
    public By allowPushNotication;
    private By menuIcon2;
    private By loginPasswordField;
    public By loginFooter;
    private By loginButton;
    private By orderIcon;
    private By loginButton1;
    public static By noticeOkBtn;
    private By proceedToSetupBtn;
    private By widgetsPopUpCloseBtn;
    private By nisaTradeCloseBtn;
    private By cfdDialogueBox;
    private By loggedInUsername;
    private By ckickOnnext;
    private By startIspeed;
    public By allowNotification;
    public By checkNotification;
    public By cancelButton;
    public By confirmButton1;
    public By logout;
    public By logoutAccept;

    public void locators() {
        if (deviceType.equalsIgnoreCase("android")) {
            loginIdField = By.id("jp.co.rakuten_sec.ispeed:id/login_id_edit");
            pushNotifcation = By.id("android:id/alertTitle");
            allowPushNotication = By.id("android:id/button1");
            //Dont allow pushNotification id
            loginPasswordField = By.id("jp.co.rakuten_sec.ispeed:id/login_password_edit");
            loginButton = By.id("jp.co.rakuten_sec.ispeed:id/login_button");
            ////*[text()='ログイン']"
            orderIcon = By.xpath("//android.widget.TextView[@text='注文']");
            loginButton1 = By.id("jp.co.rakuten_sec.ispeed:id/login_main_button");
            noticeOkBtn = By.id("android:id/button1");
            proceedToSetupBtn = By.id("jp.co.rakuten_sec.ispeed:id/next_button");
            widgetsPopUpCloseBtn = By.id("android:id/button2");
            nisaTradeCloseBtn = By.id("jp.co.rakuten_sec.ispeed:id/button2");
            cfdDialogueBox = By.id("jp.co.rakuten_sec.ispeed:id/cfd_dialog_content");
            loggedInUsername = By.id("jp.co.rakuten_sec.ispeed:id/login_user_name");
            //private By selectTextViewBtn = By.id("jp.co.rakuten_sec.ispeed:id/select_textview");
            menuIcon2 = By.xpath("//android.widget.TextView[@text='メニュー']");
            allowNotification = By.id("com.android.permissioncontroller:id/permission_allow_button");
            loginFooter = By.xpath("//android.widget.Button[@text='ログイン']");
            checkNotification = By.id("jp.co.rakuten_sec.ispeed:id/information_notification");
            cancelButton = By.xpath("//*[@text='許可しない']");
            logout = By.xpath("//android.widget.Button[@text='ログアウト']");
            logoutAccept = By.id("android:id/button1");
        } else if (deviceType.equalsIgnoreCase("ios")) {
// iphone login locators
            menuIcon2 = By.xpath("//XCUIElementTypeButton[@name='メニュー']");
            loginFooter = By.xpath("//XCUIElementTypeStaticText[@name='ログイン']");
            loginIdField = By.xpath("//XCUIElementTypeTextField[@value='ログインID']");
            loginPasswordField = By.xpath("//XCUIElementTypeSecureTextField[@value='パスワード']");
            confirmButton1 = By.xpath("//XCUIElementTypeButton[@name='確定']");
            loginButton = By.xpath("//XCUIElementTypeButton[@name='ログイン']");
            orderIcon = By.xpath("//XCUIElementTypeImage[@name='tab_order_b']");
            widgetsPopUpCloseBtn = By.xpath("//XCUIElementTypeButton[@name='閉じる']");
            proceedToSetupBtn = By.xpath("//XCUIElementTypeButton[@name='セットアップへすすむ']");
            ckickOnnext = By.xpath("//XCUIElementTypeButton[@name='次へ']");
            startIspeed = By.xpath("//XCUIElementTypeButton[@name='iSPEEDをはじめる']");
            noticeOkBtn = By.xpath("//XCUIElementTypeButton[@name='OK']");
            allowPushNotication = By.xpath("//XCUIElementTypeButton[@name='Allow']");
            nisaTradeCloseBtn = By.xpath("//XCUIElementTypeButton[@name='閉じる']");
            ;
            cfdDialogueBox = By.xpath("//*[contains(@text, '新NISAで取引するには')]");
//            loggedInUsername=;
//            checkNotification=;
            cancelButton = By.xpath("//XCUIElementTypeButton[@name='Don’t Allow']");
            logout = By.xpath("//android.widget.Button[@text='ログアウト']");
            logoutAccept = By.id("android:id/button1");
        }
    }

    public LoginPage(AppiumDriver driver) {
        super(driver);
        locators(); // Ensure locators are initialized
    }


    public void enterUsername(String username) {
        waitForElementToBeClickable(loginIdField);
        clickElement(loginIdField);
        sendKeysToElement(loginIdField, username);
    }

    public void enterPassword(String password) {
        sendKeysToElement(loginPasswordField, password);
    }

    public void clickLoginButton() {
        clickElement(loginButton);
    }

    public void agrementPage() {
        boolean okButtonClicked = false;
        while (!okButtonClicked) {
            if (isLoginElementVisible(noticeOkBtn)) {
                WebElement noticeOkButton = driver.findElement(noticeOkBtn);
                noticeOkButton.click();
            } else {
                okButtonClicked = true; // Exit the loop if the button is not visible
            }
        }
    }

    public void performLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
    }

    public void performLoginFromMenu(String username, String password) {
        waitForElementToBeClickable(menuIcon2);
        clickElement(menuIcon2);
        waitForElementToBeClickable(loginFooter);
        clickElement(loginFooter);
        waitForSeconds(2);
        baseTest.captureScreenshot("LoginPage.png");
        enterUsername(username);
        enterPassword(password);
        if (deviceType.equalsIgnoreCase("android")) {
            scrollToElement("ログイン");
        } else if (deviceType.equalsIgnoreCase("ios")) {
            waitForSeconds(2);
            clickElement(confirmButton1);
            waitForSeconds(10);
            waitForElementToBeClickable(loginButton);
            waitForSeconds(3);
            clickElement(loginButton);
        }

        //scrollToElement("ログイン");
    }

    public void closePopupsAfterLogin() {
        closeNisaPopup();
        allowPushNotification();
        noticeOfagreementDocumentPopup();
        checkNotificationScreen();
        noticeOfagreementDocumentPopup();
    }

    public void allowPushNotification() {
        waitForSeconds(3);
        try {
            if (getTextFromElement(pushNotifcation).contains("iSPEEDはあなたにプッシュ通知を送信します")) {
                waitForSeconds(2);
                waitForElementToBeClickable(allowPushNotication);
                clickElement(allowPushNotication);
            }
        } catch (Exception e) {
            System.out.print("NOT FOUND");
        }
    }

    public void dontAllowPushNotification() {
        waitForSeconds(3);
        try {
            waitForSeconds(2);
            waitForElementToBeClickable(allowPushNotication);

            waitForElementToBeClickable(cancelButton);
            clickElement(cancelButton);
            waitForSeconds(2);
            baseTest.captureScreenshot("AfterclickOnCancel");
        } catch (Exception e) {
            System.out.print("NOT FOUND");
        }
    }
// checking all the popups after login and close them if necessary
    public void closePopupAfterLogin() {
        // allowNotification();
        noticeOfagreementDocumentPopup();
        checkNotificationScreen();
        noticeOfagreementDocumentPopup();



    }

    public void checkNotificationScreen() {
        try {
            waitForSeconds(1);
            if (isLoginElementVisible(checkNotification)) {
                driver.navigate().back();
            }
        } catch (Exception e) {
            System.out.println("Not a notification screen");
        }

    }

    public boolean isNoticeOfAgreementDocumentPopup() {
        boolean closewidget;
        closewidget = isLoginElementVisible(widgetsPopUpCloseBtn);
        return closewidget;
    }

    public void proceedForSetup() {
        if (deviceType.equalsIgnoreCase("ios")) {
            waitForSeconds(2);
            baseTest.captureScreenshot("setupScreen1.png");
            waitForElementToBeClickable(proceedToSetupBtn);
            clickElement(proceedToSetupBtn);
            waitForSeconds(2);
            baseTest.captureScreenshot("setupScreen2.png");
            waitForElementToBeClickable(ckickOnnext);
            clickElement(ckickOnnext);
            waitForSeconds(2);
            baseTest.captureScreenshot("setupScreen3.png");
            waitForElementToBeClickable(startIspeed);
            clickElement(startIspeed);
            waitForSeconds(3);
        } else {
            waitForSeconds(2);
            baseTest.captureScreenshot("setupScreen1.png");
            waitForElementToBeClickable(proceedToSetupBtn);
            clickElement(proceedToSetupBtn);
            waitForSeconds(2);
            baseTest.captureScreenshot("setupScreen2.png");
            waitForElementToBeClickable(proceedToSetupBtn);
            clickElement(proceedToSetupBtn);
            waitForSeconds(2);
            baseTest.captureScreenshot("setupScreen3.png");
            waitForElementToBeClickable(proceedToSetupBtn);
            clickElement(proceedToSetupBtn);
            waitForSeconds(3);
        }

    }

    public void allowNotification() {
        try {
            if (isLoginElementVisible(allowNotification)) {
                waitForElementToBeVisible(allowNotification);
                clickElement(allowNotification);
            }
        } catch (Exception e) {
            System.out.print("NOT FOUND");
        }
    }

    public void closeNisaPopup() {
        waitForSeconds(2);
        try {
            if (isLoginElementVisible(cfdDialogueBox)) {
                waitForElementToBeClickable(nisaTradeCloseBtn);
                clickElement(nisaTradeCloseBtn);
            }
            waitForSeconds(2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // pop up screen close hajiru
    public void noticeOfagreementDocumentPopup() {
        try {
            if (isLoginElementVisible(widgetsPopUpCloseBtn)) {
                waitForElementToBeClickable(widgetsPopUpCloseBtn);
                clickElement(widgetsPopUpCloseBtn);
            }
        } catch (Exception e) {

        }
    }

    public static String transformToFullWidthDigits(String input) {
        StringBuilder output = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                output.append((char) (c - '0' + '０'));
            } else {
                output.append(c);
            }
        }
        return output.toString();
    }

    public void verifyAccountIsLoggedIn(String loginId) {
        waitForElementToBeClickable(menuIcon2);
        clickElement(menuIcon2);
        String actualAccount = getTextFromElement(loggedInUsername);
        String expectedAccount = transformToFullWidthDigits(loginId);
        Assert.assertEquals(actualAccount, expectedAccount, "Logged in account does not match expected account.");
    }

    public boolean isHomePage() {
        try {
            waitForElementToBeVisible(orderIcon);
        } catch (Exception e) {
            System.out.println("ordericonnotfound");
        }
        boolean value = isElementVisible(orderIcon);
        return value;
    }

    public void setBasicSetup() {
        proceedForSetup();
    }

    public boolean isSetup() {
        boolean setUp = false;
        try {
            setUp = isLoginElementVisible(proceedToSetupBtn);
        } catch (Exception e) {
            System.out.println("proceedToSetupBtn not found");
        }
        return setUp;
    }
    public void logout(){
        waitForSeconds(3);
        waitForElementToBeVisible(menuIcon2);
        clickElement(menuIcon2);
        waitForSeconds(2);
        waitForElementToBeClickable(logout);
        clickElement(logout);
        waitForSeconds(2);
        waitForElementToBeVisible(logoutAccept);
        clickElement(logoutAccept);
    }
}
