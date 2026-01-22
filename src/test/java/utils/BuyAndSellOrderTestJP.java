package utils;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import tests.BaseTest;

import java.io.IOException;
import java.util.List;

import static tests.PageOjectManager.*;


public class BuyAndSellOrderTestJP extends BaseTest {

    private String pin;
    private String quantity;
    public String buyCurrentPrice;

    private String JPstockName;


    private String sellCurrentPrice;

    SoftAssert softAssert;
    String account;


    public String buyOrder(SoftAssert softAssert) throws IOException {


        String stockCode = testData.getStockCodeBuySell();
        pin = testData.getPassPin();
        quantity = testData.getQuantity();
        basePage.waitForSeconds(2);
        buyDomestic.goToDomesticBuyOrderPage();
        myOptionPage.goToSearchOption();
        searchPage.searchStock(stockCode);
        basePage.waitForSeconds(3);
        String StockName = buyDomestic.getStockName();
        JPstockName = basePage.getStockName(StockName);

        handlePopup.checkIfErrorPopup();
        boolean value = buyDomestic.checkAmount();
        if (!value) {
            System.out.print("put money");
            Assert.fail("amount is null");
        }
        buyDomestic.closeImageWidget(softAssert);
        buyDomestic.verifyBuyOrderPage(softAssert);
        buyCurrentPrice = buyDomestic.getCurrentPrice();
        buyDomestic.disableSOR();
        buyDomestic.setQuantity(quantity);
        basePage.waitForSeconds(2);
        buyDomestic.selectMarketPriceTab();
       // basePage.hideKeyboard();
        captureScreenshot("BuyOrder_SetQuantity.png");
        if(deviceType.equalsIgnoreCase("ios")){
        basePage.scrollToBottomIos();}else{
        basePage.scrollToView("確認画面へ");}
        buyDomestic.passPin(pin);

        captureScreenshot("Confirm_Order.png");
        buyDomestic.confirmOrder();
        basePage.waitForSeconds(3);
        handlePopup.checkIfErrorPopup();
       // buyDomestic.verifySummuryScreen(softAssert);
        basePage.waitForSeconds(3);
        captureScreenshot("Summary_screen.png");
        if(deviceType.equalsIgnoreCase("ios")){
            basePage.scrollToBottomIos();}else{
        basePage.scrollToView("注文する");}
        basePage.waitForSeconds(2);
        captureScreenshot("placeOrder_screen.png");
        buyDomestic.placeOrder();
        basePage.waitForSeconds(2);
        handlePopup.checkIfErrorPopup();
        captureScreenshot("close_sceen.png");
        buyDomestic.clickOnClose();
        basePage.waitForSeconds(4);
        buyDomestic.verifyPlacedOrderScreen(softAssert);
        if(deviceType.equalsIgnoreCase("ios")){
            sellDomestic.goToHoldingScreen();
            buyDomestic.goToOrderEnquiry();
        }
        buyDomestic.verifyOrderPlacedScreenBeforeOMS(JPstockName, quantity, softAssert,"JP");
        captureScreenshot("orderPlacedScreenBeforeOms.png");
        sellDomestic.goToHoldingScreen();
        basePage.waitForSeconds(2);
        handlePopup.checkIfErrorPopup();
        List<String> quant = orderSummary.verifyStockInHoldingScreen(JPstockName);
        captureScreenshot("holding_screen_before_oms.png");
        String heldquantity = null; // need to look
        if (quant != null) {
            heldquantity = quant.get(0);
            System.out.println(heldquantity);
        }
        String buyPrice = buyDomestic.removeNumberAfterDecimal(buyCurrentPrice);

        coreDBEvidenceCapture("jp", stockCode, "BuyJP");
        basePage.waitForSeconds(14);
        omscommand.executeOMS( testData.getClientCode(), buyPrice, testData.getQuantity(), "buy", "");
        basePage.waitForSeconds(14);
        coreDBEvidenceCapture("jp", stockCode, "BuyJP");
        basePage.waitForSeconds(14);

        buyDomestic.goToOrderEnquiry();
        basePage.waitForSeconds(2);
        buyDomestic.verifyOrderPlacedAfterOMS(JPstockName, quantity, softAssert);
        captureScreenshot("Order_Enquiry_screen_ater_oms.png");
        sellDomestic.goToHoldingScreen();
        basePage.waitForSeconds(2);
        handlePopup.checkIfErrorPopup();
        orderSummary.verifyStockInHoldingScreenafterOMS(JPstockName, softAssert, heldquantity, null, quantity, "buy");
        captureScreenshot("verifyStockInHoldingScreenafterOMS.png");
        sellDomestic.goToExecutionInquiryScreen();
        basePage.waitForSeconds(3);
        buyDomestic.verifyExecutionInquiryScreenAfterOms(JPstockName, quantity, softAssert, "buy");
        captureScreenshot("verifyExecutionInquiryScreenAfterOms.png");
        boolean isAlertTriggered = verificationBuySell.verifyTriggeredAlertIndevice(softAssert, "buy", quantity, buyCurrentPrice, JPstockName, "JPbuyExecutionAlert.png");
        driver.navigate().back();
        if (isAlertTriggered) {
            verificationBuySell.verifyTriggeredAlertInstatusList(softAssert, "buy", quantity, buyCurrentPrice, JPstockName);
        }
        return JPstockName;
    }

    //Sell JP order
    public void sellOrder(String JPstockName, SoftAssert softAssert) throws IOException {
        String quantity = testData.getQuantity();
        String pin = testData.getPassPin();
        String stockCode = testData.getStockCodeBuySell();
        sellDomestic.goToDomesticSellOrderPage();
        basePage.waitForSeconds(2);
        handlePopup.checkIfErrorPopup();
        List<String> initialQuantityList = filter.scrollAndSelectStock(JPstockName);
        String initialHeldQuantity = initialQuantityList.get(0);
        String initialSoldQuantity = initialQuantityList.get(1);
        handlePopup.checkIfErrorPopup();
        sellCurrentPrice = buyDomestic.getCurrentPrice();
        sellDomestic.disableSOR();
        sellDomestic.setQuantity(quantity);
        basePage.hideKeyboard();
        basePage.waitForSeconds(3);
        sellDomestic.selectMarketPriceTab();
        basePage.waitForSeconds(2);
        captureScreenshot("SellOrder_SetQuantity.png");
        basePage.scrollToView("確認画面へ");
        sellDomestic.passPin(pin);
        sellDomestic.confirmSellOrder();
        basePage.waitForSeconds(3);
        captureScreenshot("SellOrder_ConfirmOrder.png");
        handlePopup.checkIfErrorPopup();
        basePage.scrollToView("注文する");
        captureScreenshot("SellOrder_ScrollToPlaceOrder.png");
        sellDomestic.placeSellOrder();
        basePage.waitForSeconds(2);
        handlePopup.checkIfErrorPopup();
        captureScreenshot("SellOrder_PlaceOrder.png");
        sellDomestic.clickOnClose();
        basePage.waitForSeconds(2);
        handlePopup.checkIfErrorPopup();
        buyDomestic.verifyPlacedOrderScreen(softAssert);
        captureScreenshot("verifyOrderPlacedScreen.png");
        sellDomestic.verifyOrderScreenBeforeOms(JPstockName, quantity, softAssert);
        captureScreenshot("SellOrder_VerifyBeforeOMS.png");
        sellDomestic.goToHoldingScreen();
        captureScreenshot("holdingScreen.png");
        handlePopup.checkIfErrorPopup();
        orderSummary.verifyStockInHoldingScreenSell(JPstockName, quantity, initialHeldQuantity, initialSoldQuantity, softAssert);
        captureScreenshot("Holding_screen_VerifyBeforeOMS.png");
        coreDBEvidenceCapture("jp", stockCode, "sell");
        String sellPrice = buyDomestic.removeNumberAfterDecimal(sellCurrentPrice);
        omscommand.executeOMS( testData.getClientCode(), sellPrice, quantity, "sell", "");
        basePage.waitForSeconds(10);
        coreDBEvidenceCapture("jp", stockCode, "SellJP");
        basePage.waitForSeconds(8);

        buyDomestic.goToOrderEnquiry();
        captureScreenshot("goToOrderEnquiry.png");
        sellDomestic.verifyOrderScreenAfteroms(JPstockName, quantity, softAssert);
        captureScreenshot("SellOrder_VerifyAfterOMS.png");
        sellDomestic.goToHoldingScreen();
        captureScreenshot("SellOrder_GoToHoldingScreen.png");
        orderSummary.verifyStockInHoldingScreenSellafterOms(JPstockName, quantity, initialHeldQuantity, initialSoldQuantity, softAssert);
        sellDomestic.goToExecutionInquiryScreen();
        captureScreenshot("SellOrder_GoToExecutionInquiry.png");
        buyDomestic.verifyExecutionInquiryScreenAfterOms(JPstockName, quantity, softAssert, "sell");
        captureScreenshot("verifyExecutionInquiryScreenAfterOms.png");
        verificationBuySell.verifyTriggeredAlertIndevice(softAssert, "sell", quantity, sellCurrentPrice, JPstockName, "JPsellExecutionAlert.png");
        driver.navigate().back();
        verificationBuySell.verifyTriggeredAlertInstatusList(softAssert, "sell", quantity, sellCurrentPrice, JPstockName);
    }


}