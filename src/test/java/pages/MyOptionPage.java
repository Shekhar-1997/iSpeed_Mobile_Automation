package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import utils.BasePage;

import static tests.BaseTest.deviceType;


public class MyOptionPage extends BasePage {
    public MyOptionPage(AppiumDriver driver) {
        super(driver);
        locators();
    }

    private By menuIcon;
    public By menuIcon1;
    private By orderIcon;
    private By orderIcon1;
    public By searchIcon;
    private By searchIcon2;
    private By favoriteIcon;
    private By assets;

    private void locators() {

        if (deviceType.equalsIgnoreCase("ios")) {

            orderIcon = By.xpath("//XCUIElementTypeButton[@name='注文']");
            searchIcon = By.xpath("(//XCUIElementTypeButton[@name='検索'])[2]");
            searchIcon2 = By.xpath("//XCUIElementTypeButton[@name='検索']");
        } else {
            menuIcon = By.xpath("//android.widget.TextView[@text='メニュー']");
            menuIcon1 = By.id("jp.co.rakuten_sec.ispeed:id/menu_icon");
            orderIcon = By.xpath("//android.widget.TextView[@text='注文']");
            orderIcon1 = By.id("jp.co.rakuten_sec.ispeed:id/global_menu_layout_3");
            searchIcon = By.xpath("//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/label']");
            // private By searchIcon=By.id("//android.widget.TextView[@resource-id=\"jp.co.rakuten_sec.ispeed:id/label\" and @text=\"検索\"]");
            favoriteIcon = By.id("//android.widget.TextView[@text='お気に入り']");
            assets = By.id("//android.widget.TextView[@text='資産・照会']");

        }
    }

    public void goToSearchOption() {
        waitForSeconds(1);

        boolean search = isElementDisplayed(searchIcon);
        if (search) {
            clickElement(searchIcon);
        } else {
            waitForElementToBeVisible(searchIcon2);
            clickElement(searchIcon2);
        }
    }

    public void gotToOrderPage() {
        waitForSeconds(2);
        waitForElementToBeVisible(orderIcon);
        clickElement(orderIcon);
    }


}
