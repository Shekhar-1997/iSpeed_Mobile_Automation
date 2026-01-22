package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import tests.BaseTest;
import utils.BasePage;


public class AccountCancel extends BasePage {
    private By  alertSettings;
    private By selectToDelete;
    private By deleteAll;
    private By list;




    public AccountCancel(AppiumDriver driver) {
        super(driver);
        locators();
    }

    private void locators() {
        if (driver.getPlatformName().equalsIgnoreCase("ios")) {
            alertSettings = By.xpath("//*[contains(@label, 'アラート設定がありません。') or contains(@value, 'アラート設定がありません。')]");
            selectToDelete = By.xpath("//XCUIElementTypeStaticText[@name='選択削除']");
            deleteAll = By.xpath("//XCUIElementTypeStaticText[@name='全削除']");

        } else {

            alertSettings = By.xpath("//android.widget.TextView[@text='アラート設定がありません。']");
            selectToDelete = By.id("jp.co.rakuten_sec.ispeed:id/delete_select_button");
            deleteAll = By.id("jp.co.rakuten_sec.ispeed:id/delete_all_button");
            list = By.id("android:id/list");
        }
    }

    public void verifyAccountCancelation(SoftAssert softAssert) {
        waitForSeconds(2);
        boolean value = isElementVisible(alertSettings);
        waitForSeconds(1);
        softAssert.assertTrue(value);

    }

    public void verifyAccountCancelationInStatusList(SoftAssert softAssert) {
        waitForSeconds(3);
        softAssert.assertFalse(isEnabled(selectToDelete));
        waitForSeconds(2);
        softAssert.assertFalse(isEnabled(deleteAll));
        waitForSeconds(2);

    }

}