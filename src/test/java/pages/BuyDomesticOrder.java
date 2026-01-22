package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.BasePage;

import static tests.BaseTest.deviceType;

public class BuyDomesticOrder extends BasePage {
    private By DomesticBuyTab;
    private By BuyOrder;
    private By CurrentPrice;
    private By ActionItems;
    private By searchOption;
    private By SORtoggle;
    private By quantityDomestic;
    private By processButtonDomestic;
    private By Plusbutton;
    private By priceUpperButton;
    private By tradingPassword;
    private By confirm_Order_Button;
    private By placeOrderButton;
    private By closeButton;
    private By fund;
    private By closeWidget;
    private By orderScreenHeader;
    private By domesticStockName;
    private By orderTab;
    private By stockCodeInHistory;
    private By buyOrderHeader;
    private By confirmScreen;
    private By orderState;
    private By tradeType;
    private By quantityInHistory;
    private By aquisitionPrice;
    private By executionTradeType;
    private By executionQuantity;
    private By executionStockName;
    private By okButton;
    private By quantityInHistoryJP;
    private By orderStateJP;
    private By stockCodeInhistoryJP;
    private By orderHistoryTabJP;
    private By orderEnquiryTab;
    private By orderHistoryTab;
    private By orderHoldingsTab;
    private By JPorderSellTab;
    private By USorderSellTab;


    private void locators() {
        if (deviceType.equalsIgnoreCase("ios")) {
            DomesticBuyTab = By.xpath("//XCUIElementTypeButton[@name='国内株式']");
            BuyOrder = By.xpath("//XCUIElementTypeButton[@name='現物買い']");
            CurrentPrice = MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[9]");
//            ActionItems;
//            searchOption;
            SORtoggle = By.xpath("(//XCUIElementTypeSwitch)[1]");
            quantityDomestic = By.xpath("(//XCUIElementTypeTextField)[1]");
            processButtonDomestic = By.xpath("//XCUIElementTypeButton[@name='成行']");
            Plusbutton = By.xpath("(//XCUIElementTypeStaticText[@name='＋'])[1]");
//            priceUpperButton;
            tradingPassword = By.xpath("//XCUIElementTypeSecureTextField[@value='文字列四桁']");
            confirm_Order_Button = By.xpath("//XCUIElementTypeButton[@name='確認画面へ']");
            placeOrderButton = By.xpath("//XCUIElementTypeButton[@name='注文する']");
            closeButton = By.xpath("//XCUIElementTypeButton[@name='閉じる']");
            fund = By.xpath("(//XCUIElementTypeStaticText)[11]");
//            closeWidget;
            orderScreenHeader = By.xpath("//XCUIElementTypeStaticText[@name='注文']");
            domesticStockName = By.xpath("(//XCUIElementTypeStaticText)[3]");
            orderTab = By.xpath("//XCUIElementTypeSegmentedControl//XCUIElementTypeButton[2]");
            stockCodeInHistory = By.xpath("//XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]");
            stockCodeInhistoryJP = By.xpath("(//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText)[1]");
            buyOrderHeader = By.xpath("(//XCUIElementTypeStaticText)[1]");
//            confirmScreen;
            orderState = By.xpath("//XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeTable[2]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[14]");
            orderStateJP = By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[12]");
            tradeType = By.xpath("(//XCUIElementTypeTable[2]//XCUIElementTypeCell[1]//XCUIElementTypeStaticText)[2]");
            executionTradeType = By.xpath("(//XCUIElementTypeTable[2]//XCUIElementTypeCell[1]//XCUIElementTypeStaticText)[9]");
            quantityInHistory = By.xpath("(//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[4])[3]");
            quantityInHistoryJP = By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[4]");
//            aquisitionPrice;
            executionQuantity = By.xpath("(//XCUIElementTypeTable//XCUIElementTypeCell[1])[1]//XCUIElementTypeStaticText[2]");
            executionStockName = By.xpath("(//XCUIElementTypeTable//XCUIElementTypeCell[1])[2]//XCUIElementTypeStaticText[1]");
            okButton = By.xpath("//XCUIElementTypeButton[@name='OK']");
            JPorderSellTab= By.id("jp.co.rakuten_sec.ispeed:id/cash_order_sell_normal_input_button");
            USorderSellTab= By.id("jp.co.rakuten_sec.ispeed:id/us_cash_order_sell_normal_input_button");

        } else {
            DomesticBuyTab = By.id("jp.co.rakuten_sec.ispeed:id/domestic_stocks_button");
            BuyOrder = By.id("jp.co.rakuten_sec.ispeed:id/cash_order_buy_normal_input_button");
            CurrentPrice = By.id("jp.co.rakuten_sec.ispeed:id/description_price_text");
            ActionItems = By.id("jp.co.rakuten_sec.ispeed:id/items");
            searchOption = By.xpath("//android.widget.LinearLayout[2]//[@resource-id='jp.co.rakuten_sec.ispeed:id/action_item']");
            SORtoggle = By.id("jp.co.rakuten_sec.ispeed:id/sor_switch");
            quantityDomestic = By.id("jp.co.rakuten_sec.ispeed:id/number_orders_edit_text");
            processButtonDomestic = By.id("jp.co.rakuten_sec.ispeed:id/process_button");
            Plusbutton = By.id("jp.co.rakuten_sec.ispeed:id/upper_button");
            priceUpperButton = By.id("jp.co.rakuten_sec.ispeed:id/price_upper_button");
            tradingPassword = By.id("jp.co.rakuten_sec.ispeed:id/trading_password_text");
            confirm_Order_Button = By.id("jp.co.rakuten_sec.ispeed:id/confirm_button");
            placeOrderButton = By.id("jp.co.rakuten_sec.ispeed:id/execute_button");
            closeButton = By.id("jp.co.rakuten_sec.ispeed:id/close_button");
            fund = By.id("jp.co.rakuten_sec.ispeed:id/open_to_buy_price");
            closeWidget = By.id("jp.co.rakuten_sec.ispeed:id/close_button");
            orderScreenHeader = By.id("jp.co.rakuten_sec.ispeed:id/search_header_text");
            domesticStockName = By.id("jp.co.rakuten_sec.ispeed:id/desc_info_corporate_name_text");
            orderTab = By.id("jp.co.rakuten_sec.ispeed:id/assets_tab_center_button");
            stockCodeInHistory = By.xpath("(//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/description'])[1]");
            buyOrderHeader = By.id("jp.co.rakuten_sec.ispeed:id/cash_order_buy_normal_input_global_header_title_text");
            confirmScreen = By.id("jp.co.rakuten_sec.ispeed:id/label_order_normal_title_text");
            orderState = By.xpath("(//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/order_state'])[1]");
            tradeType = By.xpath("(//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/trade_type'])[1]");
            quantityInHistory = By.xpath("(//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/amount_stock_mouth'])[1]");
            aquisitionPrice = By.xpath("(//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/acquisition_price_yen'])[1]");
            executionQuantity = By.xpath("(//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/execution_amount'])[1]");
            executionStockName = By.xpath("(//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/description_name'])[1]");
            okButton = By.xpath("//android.widget.Button[@text='OK']");
            orderHistoryTabJP = By.id("jp.co.rakuten_sec.ispeed:id/cash_order_show_correction_button");
            orderEnquiryTab = By.xpath("//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/assets_tab_center_button']");
            orderHistoryTab = By.xpath("//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/assets_tab_right_button']");
            orderHoldingsTab = By.xpath("//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/assets_tab_left_button']");
            JPorderSellTab= By.id("jp.co.rakuten_sec.ispeed:id/cash_order_sell_normal_input_button");
            USorderSellTab= By.id("jp.co.rakuten_sec.ispeed:id/us_cash_order_sell_normal_input_button");
        }
    }

    public BuyDomesticOrder(AppiumDriver driver) {
        super(driver);
        locators();
    }

    public void goToDomesticBuyOrderPage() {

        waitForElementToBeClickable(DomesticBuyTab);
        clickElement(DomesticBuyTab);
        waitForElementToBeClickable(BuyOrder);
        clickElement(BuyOrder);
    }

    public void goToJpSellTab() {
        waitForSeconds(2);
        waitForElementToBeClickable(JPorderSellTab);
        clickElement(JPorderSellTab);
    }
    public void goToUSSellTab() {
        waitForElementToBeClickable(USorderSellTab);
        clickElement(USorderSellTab);
    }

    public void goToJPBuyOrderHistoryTab() {

        waitForElementToBeClickable(DomesticBuyTab);
        clickElement(DomesticBuyTab);
        waitForSeconds(2);
        waitForElementToBeClickable(orderHistoryTabJP);
        clickElement(orderHistoryTabJP);
        waitForSeconds(2);
        waitForElementToBeClickable(orderEnquiryTab);
        clickElement(orderEnquiryTab);
        waitForSeconds(2);

    }

    public void orderHistoryTab() {
        waitForSeconds(2);
        waitForElementToBeClickable(orderHistoryTab);
        clickElement(orderHistoryTab);

    }

    public void orderHoldingsTab() {
        waitForSeconds(2);
        waitForElementToBeClickable(orderHoldingsTab);
        clickElement(orderHoldingsTab);
    }

    public void disableSOR() {
        waitForElementToBeClickable(SORtoggle);
        Boolean value = isEnabled(SORtoggle);
        if (value) {
            disable(SORtoggle);
        }


    }

    //    public void setQuantity(String value) {
//        waitForElementToBeClickable(quantityDomestic);
//        sendKeysToElement(quantityDomestic, value);
//
//    }
    public void setQuantity(String value) {
        waitForElementToBeClickable(quantityDomestic);
        clickElement(quantityDomestic);
        sendKeysToElement(quantityDomestic, value);
        waitForElementToBeVisible(okButton);
        clickElement(okButton);
        //  hideKeyboard();

    }

    public void hideKeyBoardInExecution() {
        waitForElementToBeVisible(okButton);
        clickElement(okButton);
    }

    public void selectMarketPriceTab() {
        boolean value = isElementVisible(processButtonDomestic);
        if (value) {
            clickElement(processButtonDomestic);
        } else {
            scrollDownToBottom();
            waitForElementToBeClickable(processButtonDomestic);
            clickElement(processButtonDomestic);
        }


    }

    public void passPin(String value) {

        waitForElementToBeClickable(tradingPassword);
        sendKeysToElement(tradingPassword, value);
        try {
            waitForElementToBeVisible(okButton);
            clickElement(okButton);
        } catch (Exception e) {
        }
    }

    public void confirmOrder() {
        waitForElementToBeClickable(confirm_Order_Button);
        clickElement(confirm_Order_Button);

    }

    public void placeOrder() {
        waitForElementToBeClickable(placeOrderButton);
        clickElement(placeOrderButton);
    }

    public void clickOnClose() {
        waitForElementToBeClickable(closeButton);
        clickElement(closeButton);
    }

    public boolean checkAmount() {
        boolean amount;
        waitForElementToBeVisible(fund);
        String currentFund = getTextFromElement(fund);
        if (currentFund.equals("0") | currentFund.equals("-")) {
            amount = false;
        } else {
            amount = true;
        }
        return amount;

    }

    public void closeImageWidget(SoftAssert softAssert) {
        try {
            waitForElementToBeVisible(closeWidget);
            clickElement(closeWidget);
            softAssert.assertFalse(isElementVisible(closeWidget));
        } catch (Exception ignored) {
        }
    }

    public String getCurrentPrice() {
        waitForElementToBeVisible(CurrentPrice);
        String currentPrice = getTextFromElement(CurrentPrice);
        return currentPrice;
    }

    public void goBackToOrderScreen(SoftAssert softAssert) {
        BasePage basepage = new BasePage(driver);
        int MAX_ATTEMPTS = 5;


        int attempts = 0;
        boolean isOrderScreenVisible = false;
        basepage.waitForSeconds(2);

        while (attempts < MAX_ATTEMPTS && !isOrderScreenVisible) {
            try {
                boolean orderScreenTitle = waitForElementToBeClickable(orderScreenHeader).isDisplayed();//ashwini


                if (orderScreenTitle) {

                    isOrderScreenVisible = true;
                }
            } catch (Exception e) {
                // If the menu icon is not found, go back and try again
                driver.navigate().back();
                attempts++;
            }
        }

        if (!isOrderScreenVisible) {
            System.out.println("Menu icon not found after " + MAX_ATTEMPTS + " attempts");
        }
        softAssert.assertEquals(getTextFromElement(orderScreenHeader), "注文");
    }

    public String getStockName() {
        return getTextFromElement(domesticStockName);
    }

    public boolean isOrderScreen() {
        return isElementVisible(orderScreenHeader);
    }

    public void verifySummuryScreen(SoftAssert softAssert) {
        String actualString = getTextFromElement(confirmScreen);
        softAssert.assertEquals(actualString, "通常注文", "confirmation screen not found");


    }

    public void verifyPlacedOrderScreen(SoftAssert softAssert) {
        waitForElementToBeVisible(orderTab);
        String actualscreen;
        if (deviceType.equalsIgnoreCase("ios")) {
            waitForElementToBeVisible(orderTab);
            WebElement element = driver.findElement(orderTab);
            actualscreen = element.getAttribute("name");
        } else {
            actualscreen = getTextFromElement(orderTab);
        }
        boolean value = isEnabled(orderTab);
        Assert.assertEquals(actualscreen, "注文照会", "history screen not found");
        Assert.assertTrue(value);


    }

    public void goToOrderEnquiry() {
        waitForElementToBeClickable(orderTab);
        clickElement(orderTab);

    }

    public void verifyStatus(String stockName, SoftAssert softAssert) {
        String firstResultOfList = getTextFromElement(stockCodeInHistory);
        softAssert.assertEquals(firstResultOfList, "status");

    }

    public void verifyStatusafteroms(String stockName, SoftAssert softAssert) {
        String firstResultOfList = getTextFromElement(stockCodeInHistory);
        softAssert.assertEquals(firstResultOfList, "status");

    }

    public void verifyBuyOrderPage(SoftAssert softAssert) {
        String actualHeader = getTextFromElement(buyOrderHeader);
        softAssert.assertEquals(actualHeader, "現物買い", "Order page not found");
    }

    public void verifyOrderPlacedScreenBeforeOMS(String stockName, String quantity, SoftAssert softAssert, String stockType) {

        String actualtradeType = getTextFromElement(tradeType);
        Assert.assertEquals(actualtradeType, "買付");
        if (actualtradeType.equals("買付")) {
            String actualStockName;
            String actualOrderState;
            String actualQuantity;
            if (stockType.equalsIgnoreCase("JP")) {
                actualStockName = getTextFromElement(stockCodeInhistoryJP);
                actualOrderState = getTextFromElement(orderStateJP);
                actualQuantity = getTextFromElement(quantityInHistoryJP);
            } else {
                actualStockName = getTextFromElement(stockCodeInHistory);

                actualOrderState = getTextFromElement(orderState);

                actualQuantity = getTextFromElement(quantityInHistory);
            }
            Assert.assertEquals(actualStockName, stockName, "stockCodeNotFound");
            Assert.assertEquals(actualOrderState, "執行待ち");
            boolean value = actualQuantity.contains(quantity);
            Assert.assertTrue(value);
        }


    }

    public void verifyOrderPlacedAfterOMS(String stockName, String quantity, SoftAssert softAssert) {
        String actualtradeType = getTextFromElement(tradeType);
        Assert.assertEquals(actualtradeType, "買付");
        if (actualtradeType.equals("買付")) {
            String actualStockName = getTextFromElement(stockCodeInHistory);
            Assert.assertEquals(actualStockName, stockName, "stockCodeNotFound");
            String actualOrderState = getTextFromElement(orderState);
            Assert.assertEquals(actualOrderState, "約定");
            String actualQuantity = getTextFromElement(quantityInHistory);
            Assert.assertEquals(actualQuantity, quantity);
            String actualAquisitionPrice = getTextFromElement(aquisitionPrice);
            Assert.assertNotEquals(actualAquisitionPrice, "-");
        }


    }

    public void scrollToGoConfirmButton() {
        scrollToView("確認画面へ");


    }

    public void scrollToOrderSummaryButton() {
        scrollToView("注文する");
    }

    //    public void verifyHoldingScreenBeforeOms(String stockCode, String quantity, SoftAssert softAssert) {
//
//    }
//
    public void verifyExecutionInquiryScreenAfterOms(String stockName, String quantity, SoftAssert softAssert, String alertType) {
        String actualtradeType = null;
        if (driver.getPlatformName().equalsIgnoreCase("ios")) {
            actualtradeType = getTextFromElement(executionTradeType);
        } else {
            actualtradeType = getTextFromElement(tradeType);
        }
        if (alertType.equals("buy")) {
            Assert.assertEquals(actualtradeType, "買付");
            if (actualtradeType.equals("買付")) {
                String actualStockName = getTextFromElement(executionStockName);

                Assert.assertEquals(actualStockName, stockName, "stockCodeNotFound in ExecutionInquiry screen");
                String actualQuantity = getTextFromElement(executionQuantity);
                softAssert.assertEquals(actualQuantity, quantity);
            }
        } else {
            // String actualtradeType = getTextFromElement(tradeType);
            softAssert.assertEquals(actualtradeType, "売付");
            if (actualtradeType.equals("売付")) {
                String actualStockName = getTextFromElement(executionStockName);

                softAssert.assertEquals(actualStockName, stockName, "stock name not found");
                String actualQuantity = getTextFromElement(executionQuantity);
                softAssert.assertEquals(actualQuantity, quantity);

            }
        }
    }

    public String removeNumberAfterDecimal(String buyCurrentPrice) {


        if (buyCurrentPrice.contains(".")) {


            // Split the input at the decimal point
            String[] parts = buyCurrentPrice.split("\\.");

            // Remove any characters that are not digits or commas from the integer part
            String integerPart = parts[0].replaceAll("[^\\d,]", "");
            String newPrice = removeComma(integerPart);
            return newPrice;
        } else {
            return removeComma(buyCurrentPrice);
        }

    }


}
