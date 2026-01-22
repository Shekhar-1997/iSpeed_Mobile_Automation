package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.BasePage;

import static tests.BaseTest.deviceType;

public class SearchPage extends BasePage {
    private By search;
    private By searchInputField;
    private By searchButton;
    private By stockDescCode;
    private By cancellButton;


    private void locators() {
        if (deviceType.equalsIgnoreCase("ios")) {
            searchButton = MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Search\"`]");
            searchInputField = MobileBy.iOSNsPredicateString("name == \"銘柄名 or 銘柄コード\"");
            search = MobileBy.iOSClassChain("**/XCUIElementTypeImage[`name == \"magnifyingglass\"`]");
            stockDescCode = MobileBy.xpath("//XCUIElementTypeCollectionView/XCUIElementTypeCell[1]");
            cancellButton=By.xpath("//android.widget.Button[@resource-id='jp.co.rakuten_sec.ispeed:id/search_cancel_button']");
        } else {
            search = By.xpath("//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/label']");
            searchInputField = By.id("jp.co.rakuten_sec.ispeed:id/search_keyword");
            searchButton = By.id("jp.co.rakuten_sec.ispeed:id/search_button");
            stockDescCode = By.id("jp.co.rakuten_sec.ispeed:id/description_search_result_description_code");
            cancellButton=By.xpath("//android.widget.Button[@resource-id='jp.co.rakuten_sec.ispeed:id/search_cancel_button']");

        }
    }

    public SearchPage(AppiumDriver driver) {
        super(driver);
        locators();
    }// re

    public void searchStock(String stockName) {
        waitForElementToBeClickable(searchInputField);
        clearTextField(searchInputField);
        clickElement(searchInputField);
        System.out.print(stockName);
        sendKeysToElement(searchInputField, stockName);
        if (deviceType.equalsIgnoreCase("ios")) {
            waitForElementToBeClickable(searchButton);
            clickElement(searchButton);
        } else {
           // hideKeyboard();
            waitForElementToBeClickable(cancellButton);
            clickElement(cancellButton);
            waitForElementToBeClickable(searchButton);
            clickElement(searchButton);
            if (isElementVisible(searchButton)) {
                clickElement(searchButton);
            }
            waitForSeconds(2);
        }

    }
    public void verifySearchResult(String stockCode) {
        waitForElementToBeVisible(stockDescCode);
        String actualCode = getTextFromElement(stockDescCode);
        Assert.assertEquals(actualCode, stockCode, "Stock code does not match expected code.");
    }

    public void goToEditAlertStockPage(SoftAssert softAssert) {
        waitForSeconds(3);
        Assert.assertTrue(isElementDisplayed(stockDescCode), "stock is not showing in search result");
        waitForElementToBeClickable(stockDescCode);
        clickElement(stockDescCode);
    }
    public void goToEditAlertStockPageForCA(SoftAssert softAssert ) {
        waitForSeconds(3);
        softAssert.assertTrue(isElementDisplayed(stockDescCode), "stock is not showing in search result");
        waitForElementToBeClickable(stockDescCode);
        clickElement(stockDescCode);
    }


}
