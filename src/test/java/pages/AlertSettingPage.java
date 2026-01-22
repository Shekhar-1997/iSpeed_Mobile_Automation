package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.asserts.SoftAssert;
import tests.BaseTest;
import utils.BasePage;

import static tests.PageOjectManager.basePage;
import static tests.PageOjectManager.menuPage;

public class AlertSettingPage extends BasePage {
    BaseTest baseTest=new BaseTest();
    private By alertSettingTtl;
    public By editSettingIcon;
    private By addSymbolOption;


    public AlertSettingPage(AppiumDriver driver) {
        super(driver);
        locators();
    }
    private void locators(){
        if (driver.getPlatformName().equalsIgnoreCase("android")) {
            alertSettingTtl = By.id("jp.co.rakuten_sec.ispeed:id/header_title");
            editSettingIcon = By.id("jp.co.rakuten_sec.ispeed:id/edit_button");
            addSymbolOption = By.xpath("//android.widget.TextView[@text='銘柄を追加する']");
        } else if (driver.getPlatformName().equalsIgnoreCase("ios")) {
            alertSettingTtl = By.xpath("//XCUIElementTypeNavigationBar//XCUIElementTypeStaticText");////XCUIElementTypeStaticText[@name="アラート設定"]
            editSettingIcon = By.xpath( "//XCUIElementTypeButton[@name='edit icon']");
            addSymbolOption=By.xpath("//XCUIElementTypeTable");
            //XCUIElementTypeButton[@name="edit icon"]
        }
    }

    public void verifyAlertSettingPage(SoftAssert softAssert) {
        waitForElementToBeVisible(alertSettingTtl);
        String actualTitle = getTextFromElement(alertSettingTtl);
        softAssert.assertEquals(actualTitle, "アラート設定", "Alert setting title does not match expected.");
    }
    public boolean goToEditAlertSettingPage(SoftAssert softAssert) {
        boolean res=false;
      //if(baseTest.deviceType.equalsIgnoreCase("android")) {
    EditAlertSettingPage edit = new EditAlertSettingPage(driver);
    try {
        edit.isAlertEnabledInsetting(softAssert);
    } catch (NoSuchElementException | TimeoutException e) {
        menuPage.goToMenuPage();
        driver.navigate().back();
        edit.isAlertEnabledInsetting(softAssert);
    }
    waitForSeconds(4);
     res = isSettingEnabled();
    if (res) {
        clickElement(editSettingIcon);
        String header = getTextFromElement(alertSettingTtl);
        if (header.equals("アラート設定")) {
            res = false;
            basePage.waitForSeconds(2);
            if (!isElementDisplayed(addSymbolOption)) {
                menuPage.goToMenuPage();
                driver.navigate().back();
                clickElement(editSettingIcon);
            }
        }
    }
        return res;
    }
//}
//else{
//    res=true;
//    waitForSeconds(3);
//    waitForElementToBeVisible(editSettingIcon);
//    clickElement(editSettingIcon);
//
//}

    public void goToAlertSettingPage() {
        if(baseTest.deviceType.equalsIgnoreCase("android")) {
    BasePage basepage = new BasePage(driver);
    int MAX_ATTEMPTS = 3;

    int attempts = 0;
    boolean isSettingVisible = false;
    basepage.waitForSeconds(2);

    while (attempts < MAX_ATTEMPTS && !isSettingVisible) {
        try {
            boolean editSetting = waitForElementToBeVisible(alertSettingTtl).isDisplayed();

            if (editSetting) {
                //clickElement(editSettingIcon);
                isSettingVisible = true;

            }
        } catch (Exception e) {
            // If the menu icon is not found, go back and try again
            driver.navigate().back();
            attempts++;
        }
    }

    if (!isSettingVisible) {
        System.out.println("setting icon not found after " + MAX_ATTEMPTS + " attempts");
    }
}

    }
    public boolean isSettingEnabled(){
        waitForSeconds(3);
       return isEnabled(editSettingIcon);
    }

    public void clickOnSettingIcon(){
        waitForElementToBeClickable(editSettingIcon);
        clickElement(editSettingIcon);
    }

}
