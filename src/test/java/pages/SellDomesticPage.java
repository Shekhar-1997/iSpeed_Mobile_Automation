package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import tests.PageOjectManager;
import utils.BasePage;

import static tests.BaseTest.deviceType;


public class SellDomesticPage extends BasePage {
    private By domesticSellTab;
    private By selectStock;
    private By sORtoggle;
    private By quantityDomestic;
    private By processButtonDomestic;
    private By tradingPassword;
    private By confirm_Sell_Button;
    private By placeSellOrderButton;
    private By closeButton;
    private By stockCodeInHistory;
    private By buyOrderHeader;
    private By confirmScreen;
    private By orderState;
    private By tradeType;
    private By quantityInHistory;
    private By aquisitionPrice;
    private By holdingScreenHeader;
    private By errorPopup;
    private By holdingScreen;
    private By executionhistorytab;
    private By popupOkButton;
    private By soldQuantityField;

    private void locators() {
        if (deviceType.equalsIgnoreCase("ios")) {
            domesticSellTab = By.xpath("//XCUIElementTypeButton[@name='現物売り']");
//        selectStock;
            sORtoggle = By.xpath("(//XCUIElementTypeSwitch)[1]");
            ;
            quantityDomestic = By.xpath("//XCUIElementTypeTextField)[1]");
            processButtonDomestic = By.xpath("//XCUIElementTypeButton[@name='成行']");
            tradingPassword = By.xpath("//XCUIElementTypeSecureTextField[@value='文字列四桁']");
            confirm_Sell_Button = By.xpath("//XCUIElementTypeButton[@name='確認画面へ']");
            placeSellOrderButton = By.xpath("//XCUIElementTypeButton[@name='注文する']");
            closeButton = By.xpath("//XCUIElementTypeButton[@name='閉じる']");
//        stockCodeInHistory;
//        buyOrderHeader;
//        confirmScreen;
//        orderState;
//        tradeType;
//        quantityInHistory;
//        aquisitionPrice;
//        holdingScreenHeader;
//        errorPopup;
            holdingScreen = By.xpath("//XCUIElementTypeButton[@name='保有銘柄']");
            executionhistorytab = By.xpath("//XCUIElementTypeButton[@name='約定照会']");
//        popupOkButton;
//        soldQuantityField;


        } else {
            domesticSellTab = By.id("jp.co.rakuten_sec.ispeed:id/cash_order_sell_normal_input_button");
            selectStock = By.xpath("//*[@resource-id='jp.co.rakuten_sec.ispeed:id/assets_holding_description_list_item_pattern1'][1]");
            sORtoggle = By.id("jp.co.rakuten_sec.ispeed:id/sor_switch");
            quantityDomestic = By.id("jp.co.rakuten_sec.ispeed:id/number_orders_edit_text");
            processButtonDomestic = By.id("jp.co.rakuten_sec.ispeed:id/process_button");
            tradingPassword = By.id("jp.co.rakuten_sec.ispeed:id/trading_password_text");
            confirm_Sell_Button = By.id("jp.co.rakuten_sec.ispeed:id/confirm_button");
            placeSellOrderButton = By.id("jp.co.rakuten_sec.ispeed:id/execute_button");
            closeButton = By.id("jp.co.rakuten_sec.ispeed:id/close_button");
            stockCodeInHistory = By.xpath("(//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/description'])[1]");
            buyOrderHeader = By.id("jp.co.rakuten_sec.ispeed:id/cash_order_buy_normal_input_global_header_title_text");
            confirmScreen = By.id("jp.co.rakuten_sec.ispeed:id/label_order_normal_title_text");
            orderState = By.xpath("(//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/order_state'])[1]");
            tradeType = By.xpath("(//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/trade_type'])[1]");
            quantityInHistory = By.xpath("(//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/amount_stock_mouth'])[1]");
            aquisitionPrice = By.xpath("(//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/acquisition_price_yen'])[1]");
            holdingScreenHeader = By.id("jp.co.rakuten_sec.ispeed:id/center_title");
            errorPopup = By.id("android:id/message");
            holdingScreen = By.id("jp.co.rakuten_sec.ispeed:id/assets_tab_left_button");
            executionhistorytab = By.id("jp.co.rakuten_sec.ispeed:id/assets_tab_right_button");
            popupOkButton = By.id("android:id/button1");
            soldQuantityField = By.id("");
        }
    }

    public SellDomesticPage(AppiumDriver driver) {
        super(driver);
        locators();
    }

    public void goToDomesticSellOrderPage() {

        waitForElementToBeClickable(domesticSellTab);
        clickElement(domesticSellTab);


    }

    public void selectStock() {

        waitForElementToBeClickable(selectStock);
        clickElement(selectStock);//search based on stockname.
    }

    public void disableSOR() {
        waitForElementToBeClickable(sORtoggle);
        Boolean value = isEnabled(sORtoggle);
        if (value) {
            disable(sORtoggle);
        }


    }

    public void setQuantity(String value) {
        waitForElementToBeClickable(quantityDomestic);
        // clickElement(quantityDomestic);
        sendKeysToElement(quantityDomestic, value);

    }

    public void selectMarketPriceTab() {
        waitForElementToBeClickable(processButtonDomestic);
        clickElement(processButtonDomestic);


    }

    public void passPin(String value) {

        waitForElementToBeClickable(tradingPassword);
        sendKeysToElement(tradingPassword, value);

    }

    public void confirmSellOrder() {
        waitForElementToBeClickable(confirm_Sell_Button);
        clickElement(confirm_Sell_Button);

    }

    public void placeSellOrder() {
        waitForElementToBeClickable(placeSellOrderButton);
        clickElement(placeSellOrderButton);
    }

    public void clickOnClose() {
        waitForElementToBeClickable(closeButton);
        clickElement(closeButton);
    }

    public String getCurrentQuantity(String JPstockName) {
        //WebElement element= scrollToView(JPstockName);
        WebElement stockElement = driver.findElement(By.xpath("//*[@text='" + JPstockName + "']/../preceding-sibling::*[2]/android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/stock_name']"));
        return stockElement.getText();
    }

    public void verifySellOrder(String ExpectedQuantity, String JPstockName) {

        String actualQuantity = getCurrentQuantity(JPstockName);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualQuantity, ExpectedQuantity);


    }

    public void verifyOrderScreenBeforeOms(String stockCode, String quantity, SoftAssert softAssert) {
        String actualtradeType = getTextFromElement(tradeType);
        Assert.assertEquals(actualtradeType, "売付");
        if (actualtradeType.equals("売付")) {
            String actualStockCode = getTextFromElement(stockCodeInHistory);
            Assert.assertEquals(actualStockCode, stockCode, "stockCodeNotFound");
            String actualOrderState = getTextFromElement(orderState);
            Assert.assertEquals(actualOrderState, "執行待ち");
            String actualQuantity = getTextFromElement(quantityInHistory);
            Assert.assertEquals(actualQuantity, quantity);

        }
    }

    public void verifyOrderScreenAfteroms(String stockCode, String quantity, SoftAssert softAssert) {
        String actualtradeType = getTextFromElement(tradeType);
        softAssert.assertEquals(actualtradeType, "売付");
        if (actualtradeType.equals("売付")) {
            String actualStockCode = getTextFromElement(stockCodeInHistory);
            Assert.assertEquals(actualStockCode, stockCode, "stockCodeNotFound");
            String actualOrderState = getTextFromElement(orderState);
            Assert.assertEquals(actualOrderState, "約定");
            String actualQuantity = getTextFromElement(quantityInHistory);
            Assert.assertEquals(actualQuantity, quantity);
            String actualAquisitionPrice = getTextFromElement(aquisitionPrice);
            Assert.assertNotEquals(actualAquisitionPrice, "-");
        }
    }


    public void verifyHoldingScreen(SoftAssert softAssert) {
        String holdingScreen = getTextFromElement(holdingScreenHeader);
        softAssert.assertEquals(holdingScreen, "保有銘柄一覧");

    }

    public void verifyOrderHistory() {

    }

//    public String getSellingQuant() {
//        return getTextFromElement(sellingQuntity);
//    }

    public void goToHoldingScreen() {
        waitForElementToBeClickable(holdingScreen);
        clickElement(holdingScreen);
    }

    public String errorPopup() {
        boolean value = isElementDisplayed(errorPopup);
        if (value) {
            String popupMessage = getTextFromElement(errorPopup);
            clickElement(popupOkButton);
            return popupMessage;
        }

        return null;
    }


    public void goToExecutionInquiryScreen() {
        waitForElementToBeClickable(executionhistorytab);
        clickElement(executionhistorytab);
    }

    public void goToHistoryScreen() {

    }
}
