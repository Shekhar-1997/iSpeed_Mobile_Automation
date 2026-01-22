package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import utils.BasePage;
import static tests.BaseTest.deviceType;

public class Logout extends BasePage {

    private By logout;
    private By logoutAccept;
    private By menuIcon;

    public Logout(AppiumDriver driver) {
        super(driver);
    }

    private void locators() {
        if (deviceType.equalsIgnoreCase("ios")) {
            logout = By.xpath("//XCUIElementTypeStaticText[@name='ログアウト']");
            logoutAccept = By.xpath("(//XCUIElementTypeButton[@name='ログアウト'])[2]");
        } else {
            logout = By.xpath("//android.widget.Button[@text='ログアウト']");
            logoutAccept = By.id("android:id/button1");
            menuIcon = By.xpath("//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/select_textview']");
        }
    }

    public void logOutTest(){
        waitForElementToBeClickable(menuIcon);
        clickElement(menuIcon);
        waitForSeconds(2);
        waitForElementToBeClickable(logout);
        clickElement(logout);
        waitForElementToBeClickable(logoutAccept);
        clickElement(logoutAccept);
        waitForSeconds(2);
    }
}
