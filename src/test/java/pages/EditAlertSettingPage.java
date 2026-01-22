package pages;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;
//import org.testng.Assert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.BaseTest;
import utils.BasePage;
//import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static tests.BaseTest.deviceType;
import static tests.PageOjectManager.*;

public class EditAlertSettingPage extends BasePage {


    private By presentValueTab;
    private By currentPrice;
    private By description_difference_percent;
    private By priceDownTextField;
    private By newsToggleButton;
    private By priceUpToggleButton;
    private By priceUpInputField;
    private By priceDownToggleButton;
    private By registerButton;
    private By previousDayRatioTab;
    private By addSymbolOption;
    private By marketIndex;
    private By alertSettingTtl;
    private By previousDayRatioUPToggleButton;
    private By previousDayRatioUPInputField;
    private By previousDayRatioDownToggleButton;
    private By previousDayRatioDownInputField;
    private By domesticStockName;
    private By saveButton;
    private By firstAlertStock;
    private By isAlertSet;
    private By losscutEnable;
    private By losscutOn;
    private By usAndJpalert;
    private By setAlertBellIcon;
    private By deleteIconInStatus;
    private By deleteButtonAlertSetting;
    private By setAlertHeading;
    private By marketIndexSelection;
    private By currentPriceIndex;
    private By checkAlertCount;

    // below locators for screen navigation
    private By alertIcon;
    private By menuIcon2;


    private void locators() {
        if (deviceType.equalsIgnoreCase("ios")) {
            marketIndex = By.xpath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable[1]");
            presentValueTab = By.xpath("//XCUIElementTypeButton[@name='現在値']");
            currentPrice = By.xpath("(//XCUIElementTypeOther/XCUIElementTypeStaticText)[3]");
            currentPriceIndex = By.xpath("(//XCUIElementTypeOther/XCUIElementTypeStaticText)[2]");
            description_difference_percent = By.xpath("//XCUIElementTypeOther/XCUIElementTypeStaticText[4]");
            priceDownTextField = By.xpath("(//XCUIElementTypeOther//XCUIElementTypeTextField)[2]");
            newsToggleButton = By.xpath("(//XCUIElementTypeSwitch)[3]");
            priceUpToggleButton = By.xpath("(//XCUIElementTypeSwitch)[1]");
            priceUpInputField = By.xpath("(//XCUIElementTypeOther//XCUIElementTypeTextField)[1]");
            priceDownToggleButton = By.xpath("(//XCUIElementTypeSwitch)[2]");
            registerButton = By.xpath("//XCUIElementTypeButton[@name='登録']");
            previousDayRatioTab = By.xpath("//XCUIElementTypeButton[@name='前日比']");
            previousDayRatioUPToggleButton = By.xpath("(//XCUIElementTypeSwitch)[1]");
            previousDayRatioUPInputField = By.xpath("(//XCUIElementTypeOther/XCUIElementTypeTextField)[1]");
            previousDayRatioDownToggleButton = By.xpath("(//XCUIElementTypeSwitch)[2]");
            previousDayRatioDownInputField = By.xpath("(//XCUIElementTypeOther/XCUIElementTypeTextField)[2]");
            domesticStockName = By.xpath("(//XCUIElementTypeOther/XCUIElementTypeStaticText)[1]");
            saveButton = By.xpath("//XCUIElementTypeButton[@name='完了']");
            setAlertBellIcon = By.xpath("//*[contains(@name, '約定アラート')]");
            firstAlertStock = By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]");
            isAlertSet = By.xpath("//*[contains(@label, 'アラート設定がありません。') or contains(@value, 'アラート設定がありません。')]");
//            losscutEnable=;
            usAndJpalert = By.xpath("//XCUIElementTypeButton[@name='約定アラートを受け取る（国内＋米国）']");
//            setAlertBellIcon=;
            deleteIconInStatus = By.xpath("//XCUIElementTypeStaticText[@name='全削除']");
            setAlertHeading = By.xpath("//XCUIElementTypeStaticText[@name='アラート設定編集']");
            marketIndexSelection = By.xpath("//XCUIElementTypeStaticText[@name='市況選択']");
            checkAlertCount = By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell");
        } else {
            presentValueTab = By.id("jp.co.rakuten_sec.ispeed:id/cond_type_price");
            currentPrice = By.id("jp.co.rakuten_sec.ispeed:id/current_price");
            currentPriceIndex = By.id("jp.co.rakuten_sec.ispeed:id/desc_header_current_price");
            description_difference_percent = By.id("jp.co.rakuten_sec.ispeed:id/description_difference_percent");
            priceDownTextField = By.id("jp.co.rakuten_sec.ispeed:id/threshold_under");
            newsToggleButton = By.id("jp.co.rakuten_sec.ispeed:id/news_toggle");
            priceUpToggleButton = By.id("jp.co.rakuten_sec.ispeed:id/over_toggle_button");
            priceUpInputField = By.id("jp.co.rakuten_sec.ispeed:id/threshold_over");
            priceDownToggleButton = By.xpath("//*[@resource-id='jp.co.rakuten_sec.ispeed:id/under_toggle_button'][1]");
            registerButton = By.id("jp.co.rakuten_sec.ispeed:id/register_button");
            previousDayRatioTab = By.xpath("//android.widget.RadioButton[@text='前日比']");
            addSymbolOption = By.xpath("//android.widget.TextView[@text='銘柄を追加する']");
            marketIndex = By.xpath("//android.widget.TextView[@text='市況を追加する']");
            alertSettingTtl = By.id("jp.co.rakuten_sec.ispeed:id/header_title");
            previousDayRatioUPToggleButton = By.id("jp.co.rakuten_sec.ispeed:id/over_toggle_button");
            previousDayRatioUPInputField = By.id("jp.co.rakuten_sec.ispeed:id/threshold_over");
            previousDayRatioDownToggleButton = By.id("jp.co.rakuten_sec.ispeed:id/under_toggle_button");
            previousDayRatioDownInputField = By.id("jp.co.rakuten_sec.ispeed:id/threshold_under");
            domesticStockName = By.id("jp.co.rakuten_sec.ispeed:id/description_name_text");
            saveButton = By.id("jp.co.rakuten_sec.ispeed:id/close_button");
            firstAlertStock = By.xpath("(//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/stock_name'])[1]");
            isAlertSet = By.xpath("//*[@text='アラート設定がありません。']");
            losscutEnable = By.xpath("//android.widget.Button[@resource-id='jp.co.rakuten_sec.ispeed:id/notification_stop_loss']");
            losscutOn = By.xpath("//*[@text='追証/ロスカットアラートを受け取る（米国株式）']");
            usAndJpalert = By.xpath("//*[@text='約定アラートを受け取る(国内＋米国)']");
            setAlertBellIcon = By.xpath("//android.widget.Button[@resource-id='jp.co.rakuten_sec.ispeed:id/notification_bell']");
            deleteIconInStatus = By.id("jp.co.rakuten_sec.ispeed:id/delete_all_button");
            deleteButtonAlertSetting = By.xpath("(//android.widget.ImageView[@resource-id='jp.co.rakuten_sec.ispeed:id/condition_delete_image'])[1]");
            setAlertHeading = By.id("jp.co.rakuten_sec.ispeed:id/title");
            marketIndexSelection = By.xpath("//*[@text='市況選択']");
            alertIcon = By.id("jp.co.rakuten_sec.ispeed:id/menu_notification_stocks_setting");
            menuIcon2 = By.xpath("//android.widget.TextView[@text='メニュー']");

        }
    }

    public EditAlertSettingPage(AppiumDriver driver) {
        super(driver);
        locators();
    }

    public void verifyEditAlertSettingPage(SoftAssert softAssert) {
        waitForElementToBeVisible(alertSettingTtl);
        String actualTitle = getTextFromElement(alertSettingTtl);
        softAssert.assertEquals(actualTitle, "アラート設定編集", "Edit Alert setting title does not match expected.");
    }

    public void clickOnAddSymbolOption() {
        if (deviceType.equalsIgnoreCase("ios")) {
            boolean value = isAlertNotPresent();
            if (value) {
                driver.executeScript("mobile: tap", ImmutableMap.of(
                        "x", 52,
                        "y", 378
                ));
            } else {
                driver.executeScript("mobile: tap", ImmutableMap.of(
                        "x", 21,
                        "y", 119
                ));
            }

        } else {
            // pageSourceUtil.capturePageSource("Edit_Alert_Setting_Page_Source.txt");
            waitForElementToBeClickable(addSymbolOption);
            clickElement(addSymbolOption);
        }

    }

    public void selectPresentValueTab() {
        waitForSeconds(3);
        waitForElementToBeVisible(presentValueTab);
        waitForElementToBeClickable(presentValueTab);
        clickElement(presentValueTab);
    }

    public String getCurrentPrice(SoftAssert softAssert, String region) {
        By currentPrice1 = null;
        String price = null;
        if (deviceType.equalsIgnoreCase("ios")) {
            if (!region.equalsIgnoreCase("JP")) {
                currentPrice1 = currentPriceIndex;
            } else {
                currentPrice1 = currentPrice;
            }
        } else {
            if (region.equalsIgnoreCase("Index")) {
                currentPrice1 = currentPriceIndex;
            } else {
                currentPrice1 = currentPrice;
            }
        }
        waitForSeconds(3);
        waitForElementToBeVisible(currentPrice1);
        softAssert.assertTrue(isElementVisible(currentPrice1), "current price is not displayed.");//.ass!isElementVisible(CurrentPrice))
        if (isElementVisible(currentPrice1)) {
            price = getTextFromElement(currentPrice1);
            if (price.equals("0.00")) {
                softAssert.fail();
            } else if (price.contains("-")) {
                softAssert.fail();

            }
        }
        return price;
    }

    public void enablePriceUpAlertToggle() {
        waitForElementToBeVisible(priceUpToggleButton);
        enableToggle(priceUpToggleButton);
    }

    public void selectPreviousDayRatioTab(SoftAssert softAssert) {
        // waitForElementToBeClickable(previousDayRatioTab);
        waitForSeconds(2);
        boolean value = isElementVisible(previousDayRatioTab);
        if (value) {
            clickElement(previousDayRatioTab);
        } else {
            try {
                scrollUpToTop();
                clickElement(previousDayRatioTab);
            } catch (Exception e) {
                scrollToElement("前日比");
                clickElement(previousDayRatioTab);
            }
            boolean selectElement = isElementSlected(previousDayRatioTab);
            softAssert.assertTrue(selectElement);
        }
    }

    public void enablePreviousDayRatioDownToggle() {
        enableToggle(previousDayRatioDownToggleButton);
    }

    public String previousRatioPriceDownValue(double decrementamount, SoftAssert softAssert, String alertType, double decrementPercent) {
        waitForElementToBeVisible(description_difference_percent);
        String price = getTextFromElement(description_difference_percent);

        String decreasedAmount = getAmountPreviousDayDown(price, decrementamount, alertType, decrementPercent);
        waitForSeconds(2);
        if (decreasedAmount == null) {
            Assert.fail("Previous Day ratio is not reflecting.");
        }
        //waitForElementToBeClickable(preciousDayRatioDownInputField);
        clearTextField(previousDayRatioDownInputField);
        waitForSeconds(2);
        // clickElement(preciousDayRatioDownInputField);
        sendKeysToElement(previousDayRatioDownInputField, decreasedAmount);
        waitForElementToBeClickable(previousDayRatioDownInputField);
        // clickElement(previousDayRatioDownInputField);
        String actualPrice = getTextFromElement(previousDayRatioDownInputField);
        softAssert.assertEquals(actualPrice, decreasedAmount);
        return decreasedAmount;


    }

    public void clickOnRegisterBtn() {
        waitForElementToBeClickable(registerButton);
        clickElement(registerButton);
    }

    public void setCAAlert(String corporateActionPrice, SoftAssert softAssert, String alertType) {
        enablePriceUpAlertToggle();
        corporateActionPrice = corporateActionPrice + getCurrentPrice(softAssert, alertType);
        waitForElementToBeClickable(priceUpInputField);
        clearTextField(priceUpInputField);
        sendKeysToElement(priceUpInputField, corporateActionPrice);
    }


    public String setPreviousDayDownAlert(double decrementamount, SoftAssert softAssert, String alertType, double decrementPercent) {
        enablePreviousDayRatioDownToggle();
        return previousRatioPriceDownValue(decrementamount, softAssert, alertType, decrementPercent);
    }

    public void setNewsAlert(SoftAssert softAssert) {
        waitForSeconds(3);
        enableToggle(newsToggleButton);
    }

    public void selectMarketIndex(SoftAssert softAssert) {
        waitForElementToBeClickable(marketIndex);
        clickElement(marketIndex);
    }

    public void clickOnSaveBtn() {
        boolean saveBottonPresent = isElementVisible(saveButton);
        if (saveBottonPresent) {
            waitForElementToBeClickable(saveButton);
            clickElement(saveButton);
        }
    }


    // for navigation it is usefull
    public void selectStockFromSetting(String stockName) {
        waitForSeconds(2);
        System.out.println(stockName);
        findAndClickElementContainingText(stockName);
        scrollToText(stockName);


    }

    public void disablePriceUpAlertToggle(SoftAssert softAssert) {
        waitForSeconds(2);
        waitForElementToBeVisible(priceUpToggleButton);
        disableToggle(priceUpToggleButton);
        verifyDisableAlert(softAssert, priceUpToggleButton);


    }

    public void verifyDisableAlert(SoftAssert softAssert, By locator) {
        if (deviceType.equalsIgnoreCase("ios")) {
            String value = getElementValue(locator);
            softAssert.assertEquals(value, "0");
        } else {
            String status = getTextFromElement(locator);
            softAssert.assertEquals(status, "Off");
        }

    }

    public void disablePriceDownAlertToggle(SoftAssert softAssert) {
        waitForSeconds(2);
        disableToggle(priceDownToggleButton);
        verifyDisableAlert(softAssert, priceDownToggleButton);
    }

    public String isPriceUpAlertTriggered(SoftAssert softAssert) {
        String status;
        status = isAlertTriggered(priceUpToggleButton);

        return status;

    }

    public String isAlertTriggered(By locator) {
        String status;
        waitForElementToBeVisible(locator);
        if (deviceType.equalsIgnoreCase("ios")) {
            status = isToggleDisabled(locator);

        } else {
            status = getTextFromElement(locator);
        }

        return status;
    }

    public String isPriceDownAlertTriggered(SoftAssert softAssert) {
        String status = isAlertTriggered(priceDownToggleButton);
        return status;
    }

    public String isPreviousDayUpAlertTriggered(SoftAssert softAssert) {

        String status = isAlertTriggered(previousDayRatioUPToggleButton);
        return status;
    }

    public String isPreviousdayDownAlertTriggered(SoftAssert softAssert) {
        String status = isAlertTriggered(previousDayRatioDownToggleButton);
        return status;
    }

    public void isNewsAlertSet() {
        String status;
        waitForElementToBeVisible(newsToggleButton);
        if (deviceType.equalsIgnoreCase("ios")) {
            status = isToggleDisabled(newsToggleButton);
        } else {
            status = getTextFromElement(newsToggleButton);
        }
        Assert.assertEquals(status, "On", "News alert not set");

    }

    public void disablePreviousDayRatioUpToggle(SoftAssert softAssert) {
        waitForSeconds(2);
        waitForElementToBeVisible(previousDayRatioUPToggleButton);
        disableToggle(previousDayRatioUPToggleButton);
        verifyDisableAlert(softAssert, previousDayRatioUPToggleButton);
    }

    public void disableNewsToggle(SoftAssert softAssert) {
        waitForSeconds(2);
        waitForElementToBeVisible(newsToggleButton);
        disableToggle(newsToggleButton);
        verifyDisableAlert(softAssert, newsToggleButton);

    }

    public void disablePreviousDayRatioDownToggle(SoftAssert softAssert) {
        waitForSeconds(2);
        waitForElementToBeVisible(previousDayRatioDownToggleButton);
        disableToggle(previousDayRatioDownToggleButton);
        verifyDisableAlert(softAssert, previousDayRatioDownToggleButton);
    }

    public String getStockName() {
        String name = getTextFromElement(domesticStockName);//
        return name;
    }


    public void verifyAlertCreation(String stockName, SoftAssert softAssert) {
        String actualStockName;
        waitForSeconds(6);
        waitForElementToBeVisible(firstAlertStock);
        actualStockName = getTextFromElement(firstAlertStock);
        if (deviceType.equalsIgnoreCase("ios")) {
            actualStockName = extractCompanyName(actualStockName);
        }
        softAssert.assertEquals(actualStockName, stockName);

    }

    public boolean isAlertCreated(String stockName, SoftAssert softAssert) {
        waitForElementToBeVisible(firstAlertStock);
        return getTextFromElement(firstAlertStock).contains(stockName);
    }

    public String statusOfToggle(By toggle) {
        //softAssert.assertEquals(statusOfToggleBtn,"Off","toggle status is off");
        waitForElementToBeVisible(toggle);
        return getTextFromElement(toggle);


    }

    public HashMap<String, String> verifyPriceUpAlertTriggered() {
        HashMap<String, String> alertDetails = new HashMap<>();
        String price = getTextFromElement(priceDownTextField);
        String toggleStatus = statusOfToggle(priceDownToggleButton);
        alertDetails.put("priceUpValue", price);
        alertDetails.put("toggleStatus", toggleStatus);
        return alertDetails;
    }

    public String setPriceAlert(String region, String direction, double amount, SoftAssert softAssert, String alertType, double percent) {
        By toggleButton, inputField;
        String increasedAmount = null;

//        waitForSeconds(3);
        String currentPrice = getCurrentPrice(softAssert, region);
        if (currentPrice.equals("0.00") | currentPrice.equals("0") | currentPrice.equals("-")) {
            Assert.fail("Current price is not reflecting.");
        }

        // Determine toggleButton and inputField based on alert type
        switch (direction) {
            case "up":
                toggleButton = priceUpToggleButton;
                inputField = priceUpInputField;
                increasedAmount = (region.equals("JP"))
                        ? increaseAmount(currentPrice, (int) amount, alertType, percent)
                        : increaseAmountUS(currentPrice, amount, alertType, percent);
                break;
            case "down":
                toggleButton = priceDownToggleButton;
                inputField = priceDownTextField;
                increasedAmount = (region.equals("JP"))
                        ? decreaseAmount(currentPrice, (int) amount, alertType, percent)
                        : decreaseAmountUS(currentPrice, amount, alertType, percent);
                break;
            default:
                throw new IllegalArgumentException("Invalid alert type: " + direction);
        }
        waitForSeconds(4);
        // Apply the toggle and set the alert value
        enableToggle(toggleButton);
        setAlertValue(inputField, increasedAmount, softAssert);

        return increasedAmount;
    }

    // Setting Previous Day Alerts
    public String setPreviousDayAlert(String direction, double amount, SoftAssert softAssert, String alertType, double percent) {
        By toggleButton, inputField;
        String updatedAmount = null;
        if (direction.equals("up")) {
            toggleButton = previousDayRatioUPToggleButton;
            inputField = previousDayRatioUPInputField;
            updatedAmount = getAmountPrevousDayUp(getTextFromElement(description_difference_percent), amount, alertType, percent);
            if (updatedAmount == null) {
                Assert.fail("Previous day ratio is not reflecting");
            }
        } else if (direction.equals("down")) {
            toggleButton = previousDayRatioDownToggleButton;
            inputField = previousDayRatioDownInputField;
            updatedAmount = getAmountPreviousDayDown(getTextFromElement(description_difference_percent), amount, alertType, percent);
            if (updatedAmount == null) {
                Assert.fail("Previous day ratio is not reflecting");
            }
        } else {
            throw new IllegalArgumentException("Invalid direction: " + direction);
        }
        waitForSeconds(3);
        enableToggle(toggleButton);
        setAlertValue(inputField, updatedAmount, softAssert);
        return updatedAmount;
    }

    public void setAlertValue(By inputField, String value, SoftAssert softAssert) {
        if (value == null) {
            Assert.fail();
        }
        waitForElementToBeClickable(inputField);
        clearTextField(inputField);
        sendKeysToElement(inputField, value);
        String actualValue = getTextFromElement(inputField);
        softAssert.assertEquals(actualValue, value);
    }

    public boolean isAlertNotPresent() {
        boolean value = false;
        try {
            value = isElementDisplayed(isAlertSet);
        } catch (Exception e) {
        }
        return value;
    }

    public boolean isAlertPresentInStatusList() {
        waitForSeconds(4);
        boolean value;
        value = isEnabled(deleteIconInStatus);
        return value;
    }

    public void isLosscutEnabled(SoftAssert softAssert) {
        softAssert.assertTrue(isElementDisplayed(losscutEnable));

        String lossCutText = getTextFromElement(losscutEnable);
        softAssert.assertEquals(lossCutText, "追証/ロスカットアラート：オン");
        if (!lossCutText.equals("追証/ロスカットアラート：オン")) {
            clickElement(losscutEnable);
            waitForElementToBeClickable(losscutOn);
            clickElement(losscutOn);
        }

    }

    public void isAlertEnabledInsetting(SoftAssert softAssert) {
        softAssert.assertTrue(isElementDisplayed(setAlertBellIcon));
        String bellIconText = getTextFromElement(setAlertBellIcon);
        System.out.println(bellIconText);
        if (deviceType.equalsIgnoreCase("ios")) {
            softAssert.assertEquals(bellIconText, "約定アラート:国内株式＋米国株式");
        } else {
            softAssert.assertEquals(bellIconText, "約定アラート：国内株式＋米国株式");
        }
        if (!bellIconText.contains("約定アラート：国内株式＋米国株式")) { // old -> 国内株式＋米国株式
            clickElement(setAlertBellIcon);

            waitForElementToBeClickable(usAndJpalert);
            clickElement(usAndJpalert);
        }


    }

    public boolean waitFordeleteElementToBeVisible(SoftAssert softAssert) {
        return (isElementVisible(saveButton));
    }

    public void waitForSettingScreenTobeVisible() {
        isElementVisible(setAlertBellIcon);
    }


    public void setCAalert() {

        enablePriceUpAlertToggle();
    }

    public void verifyNewsAlertCreation(String stockName) {
        boolean count = checkCountOfAlerts();
        if (count) {
            scrollToText(stockName);
        } else {
            driver.executeScript("mobile: tap", ImmutableMap.of(
                    "x", 95,
                    "y", 214
            ));
        }
        System.out.println(stockName);
        isNewsAlertSet();

    }

    public boolean checkCountOfAlerts() {
        boolean count = false;
        if (deviceType.equalsIgnoreCase("ios")) {
            boolean count1 = isElementDisplayed(checkAlertCount);
            if (count1) {
                count = true;

            }
        }
        return count;
    }

    public void selectMarketIndexList(String stockNameIndex) {
        scrollToElement(stockNameIndex);
        waitForSeconds(4);
        verifySelectedMarketIndex(stockNameIndex);
    }

    private void verifySelectedMarketIndex(String stockNameIndex) {
        if (!isElementDisplayed(setAlertHeading)) {
            if (isElementDisplayed(marketIndexSelection)) {
                scrollToElementIndex(stockNameIndex);
                waitForSeconds(3);
            }
        }
    }

    public void screenNavigation() {
        waitForElementToBeVisible(menuIcon2);
        clickElement(menuIcon2);
        waitForSeconds(2);
    }

    public void clickOnAlertIcon() {
        waitForElementToBeVisible(alertIcon);
        clickElement(alertIcon);
        waitForSeconds(2);

    }
}
