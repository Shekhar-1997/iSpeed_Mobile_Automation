package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import utils.BasePage;
//import tests.BaseTest;


public class MenuPage extends BasePage {
    //BaseTest  baseTest = new BaseTest();
    private By alertIcon;
    private By alertStatusList;
    private By menuIcon2;
    private By logoutAccept;
    private By userCode;
    //jp.co.rakuten_sec.ispeed:id/login_user_name

    private By userName;
    private By logoutButton;

    public By loOut;
    public By loOutAccept;
    public By closeButton;


    public MenuPage(AppiumDriver driver) {
        super(driver);
        locators();
    }

    public void locators() {
        if (driver.getPlatformName().equalsIgnoreCase("android")) {//(baseTest.deviceType.equalsIgnoreCase("android")) {
            alertIcon = By.id("jp.co.rakuten_sec.ispeed:id/menu_notification_stocks_setting");
            alertStatusList = By.xpath("//android.widget.TextView[@text='状況一覧']");
// selectTextViewBtn = By.id("jp.co.rakuten_sec.ispeed:id/select_textview");
            menuIcon2 = By.xpath("//android.widget.TextView[@text='メニュー']");
            logoutAccept = By.id("//android.widget.TextView/../../..//android.widget.Button[@text='ログアウト']");
            userCode = By.xpath("//android.widget.TextView[@text='お客様コード']");
            logoutButton = By.id("jp.co.rakuten_sec.ispeed:id/footer_logout_button");
            loOut= By.id("jp.co.rakuten_sec.ispeed:id/header_logout_button");
            loOutAccept= By.xpath("//android.widget.Button[@resource-id='android:id/button1']");
            userName = By.id("jp.co.rakuten_sec.ispeed:id/login_user_name");
            closeButton = By.id("jp.co.rakuten_sec.ispeed:id/close_button");
        } else if (driver.getPlatformName().equalsIgnoreCase("ios")) {
            alertIcon = By.xpath("//XCUIElementTypeImage[@name='icon_bell']");
            menuIcon2 = By.xpath("//XCUIElementTypeButton[@name='メニュー']");
            logoutButton = By.xpath("//XCUIElementTypeStaticText[@name='ログアウト']");
            logoutAccept = By.xpath("(//XCUIElementTypeButton[@name='ログアウト'])[2]");
            userCode = By.xpath("//XCUIElementTypeStaticText[@name='部店 - お客様コード']");
            alertStatusList = By.xpath("//android.widget.TextView[@text='状況一覧']");
            closeButton = By.id("jp.co.rakuten_sec.ispeed:id/close_button");
        }
    }


    public void goToNotificationSetting() {
        waitForSeconds(3);
        boolean alertIconPresent = isElementDisplayed(alertIcon);
        if (alertIconPresent) {
            waitForElementToBeClickable(alertIcon);
            clickElement(alertIcon);


        }
    }

    public void logout() {
        waitForElementToBeClickable(loOut);
        clickElement(loOut);
        waitForElementToBeClickable(loOutAccept);
        clickElement(loOutAccept);
    }

    public String getValue() {
        waitForSeconds(2);
        String value;
        value = getTextFromElement(userName);
        return value;
    }

    public void clickOnMenu() {

        waitForElementToBeClickable(menuIcon2);
        clickElement(menuIcon2);
//        waitForElementToBeClickable(alertIcon);
//        clickElement(alertIcon);
    }

    public void gotoAlertStatusList() {
        waitForElementToBeClickable(alertStatusList);
        clickElement(alertStatusList);

    }

    public void alertStatusList() {
        waitForSeconds(2);
        if (isElementVisible(menuIcon2)) {
            waitForElementToBeClickable(menuIcon2);
            clickElement(menuIcon2);
            waitForSeconds(2);
            waitForElementToBeClickable(alertStatusList);
            clickElement(alertStatusList);
        } else {
            waitForElementToBeClickable(menuIcon2);
            clickElement(menuIcon2);
            clickElement(alertStatusList);
        }

    }


    public void goToMenuPage() {

        BasePage basepage = new BasePage(driver);
        int MAX_ATTEMPTS = 5;


        int attempts = 0;
        boolean isMenuVisible = false;
        basepage.waitForSeconds(2);

        while (attempts < MAX_ATTEMPTS && !isMenuVisible) {
            try {
                boolean menuIcon = waitForElementToBeClickable(menuIcon2).isDisplayed();//ashwini


                if (menuIcon) {
                    clickElement(menuIcon2);

                    boolean value = verifyMenuPage();
                    if (value) {
                        isMenuVisible = true;
                    }
                }
            } catch (Exception e) {
                // If the menu icon is not found, go back and try again
                driver.navigate().back();
                attempts++;
            }
        }
        basepage.waitForSeconds(2);

        if (!isMenuVisible) {
            System.out.println("Menu icon not found after " + MAX_ATTEMPTS + " attempts");
        }

    }

    public boolean verifyMenuPage() {
        return isElementDisplayed(userCode);
    }

    public void clickOnClose() {
        waitForElementToBeClickable(closeButton);
    }

}

