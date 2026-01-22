package utils;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import tests.BaseTest;

import java.io.IOException;
import java.util.List;

import static tests.PageOjectManager.*;


public class BuyAndSellOrderUS extends BaseTest {
    private String sellingQantity;
    private String sellCurrentPrice;
    private String buyCurrentPrice;
    private String buyUsName;
    private String quantity;
    private String tickerCode;
    private VerificationForBuyAndSell verification;

    public String buyUS( SoftAssert softAssert) throws IOException {
        tickerCode = testData.getStockNameUS();
        if (!buyDomestic.isOrderScreen()) {
            buyDomestic.goBackToOrderScreen(softAssert);
            menuPage.goToMenuPage();
            buyUsOrder.clickOnOrderIcon();
            captureScreenshot("orderScreen.png");
        }
        buyUsOrder.verifyOrderPage();
        buyUsOrder.clickOnUsOrderTab();
        captureScreenshot("USbuyScreen.png");
        buyUsOrder.selectUsBuy();
        myOptionPage.goToSearchOption();
        searchPage.searchStock(tickerCode);
//        buyUsOrder.clickOnSearchButton();
//        buyUsOrder.clickOnInputTextField(tickerCode);
//        buyUsOrder.clickOnCancelandSearchButton();
//        basePage.waitForSeconds(3);
        boolean value = buyUsOrder.checkAmountUS();
        if (!value) {
            System.out.print("put money");
            Assert.fail("amount is null");
        }
        String buyUsName1 = buyUsOrder.getStockNameUs();
        String buyUsName2 = basePage.removeSpaces(buyUsName1);
        buyUsName = basePage.getStockName(buyUsName2);
        buyCurrentPrice = buyUsOrder.getCurrentPriceUs();
        sellUSOrder.clickplusbutton();
        quantity = sellUSOrder.getQuantity();
        basePage.waitForSeconds(2);
        //basePage.hideKeyboard();
        buyUsOrder.selectMarketTab();
        captureScreenshot("selectMarketTab.png");
        if(deviceType.equalsIgnoreCase("ios")){
            basePage.scrollToBottomIos();}else{
            basePage.scrollToView("確認画面へ");}
        basePage.waitForSeconds(3);
        buyUsOrder.clickOnTradingPasswordInputField(testData.getPassPin());
        captureScreenshot("clickOnTradingPasswordInputField.png");
        buyDomestic.confirmOrder();
        handlePopup.checkIfErrorPopup();
       // buyUsOrder.verifyOrderReviewScreen(softAssert);//implementation pending
        captureScreenshot("verifyOrderReviewScreen.png");
        if(deviceType.equalsIgnoreCase("ios")){
            basePage.scrollToBottomIos();}else{
            basePage.scrollToView("注文する");}      // basePage.scrollDownToBottom();
        basePage.waitForSeconds(3);
        buyDomestic.placeOrder();
        basePage.waitForSeconds(3);
        handlePopup.checkIfErrorPopup();
        basePage.waitForSeconds(3);
        captureScreenshot("close.png");
        buyDomestic.clickOnClose();
        basePage.waitForSeconds(3);
        buyDomestic.verifyPlacedOrderScreen(softAssert);
        if(deviceType.equalsIgnoreCase("ios")){
         sellDomestic.goToHoldingScreen();
            buyDomestic.goToOrderEnquiry();
        }
        buyDomestic.verifyOrderPlacedScreenBeforeOMS(buyUsName, quantity, softAssert,"US");
        captureScreenshot("verifyOrderPlacedScreenBeforeOMS.png");
        sellDomestic.goToHoldingScreen();
        basePage.waitForSeconds(3);
        handlePopup.checkIfErrorPopup();
        List<String> quant = orderSummary.verifyStockInHoldingScreen(buyUsName);//from here need to implement in ios
        captureScreenshot("verifyStockInHoldingScreen.png");
        String heldquantity = null;
        if (quant != null) {
            heldquantity = quant.get(0);
        }
        coreDBEvidenceCapture("us", tickerCode, "buyUS");
        basePage.waitForSeconds(4);
        omscommand.executeOMS( testData.getClientCode(), buyCurrentPrice, quantity, "us buy", testData.getDate());
        basePage.waitForSeconds(14);
        coreDBEvidenceCapture("us", tickerCode, "buyUS");
        buyDomestic.goToOrderEnquiry();
        basePage.waitForSeconds(2);
        buyDomestic.verifyOrderPlacedAfterOMS(buyUsName, quantity, softAssert);
        captureScreenshot("verifyOrderPlacedAfterOMS.png");
        sellDomestic.goToHoldingScreen();
        basePage.waitForSeconds(2);
        orderSummary.verifyStockInHoldingScreenafterOMS(buyUsName, softAssert, heldquantity, null, quantity, "buy");
        captureScreenshot("verifyStockInHoldingScreenafterOMS.png");
        sellDomestic.goToExecutionInquiryScreen();
        basePage.waitForSeconds(2);
        buyDomestic.verifyExecutionInquiryScreenAfterOms(buyUsName, quantity, softAssert, "buy");
        captureScreenshot("verifyExecutionInquiryScreenAfterOms.png");
        verification.verifyTriggeredAlertIndevice(softAssert, "buy", quantity, buyCurrentPrice, buyUsName, "USbuyExecutionAlert.png");
        driver.navigate().back();
        verification.verifyTriggeredAlertInstatusList(softAssert, "buy", quantity, buyCurrentPrice, buyUsName);
        return buyUsName;
    }

    public void sellUS( String buyUsName, SoftAssert softAssert) throws IOException {
        if (!buyDomestic.isOrderScreen()) {
            buyDomestic.goBackToOrderScreen(softAssert);
            menuPage.goToMenuPage();
            buyUsOrder.clickOnOrderIcon();
            captureScreenshot("orderScreen.png");
        }
        buyUsOrder.verifyOrderPage();
        captureScreenshot("orderscreen.png");
        buyUsOrder.clickOnUsOrderTab();
        basePage.waitForSeconds(1);
        captureScreenshot("select_order_screenTab.png");
        basePage.waitForSeconds(1);
        sellUSOrder.sellOrderUs();
        basePage.waitForSeconds(1);
        captureScreenshot("select_sell_orderscreen.png");
        sellUSOrder.verifyHoldingScreen(softAssert);
        captureScreenshot("verifyHoldingScreen.png");
        basePage.waitForSeconds(2);
        handlePopup.checkIfErrorPopup();
        List<String> initialquantity = filter.scrollAndSelectStock(buyUsName);
        captureScreenshot("scrollAndSelectStock.png");
        String initialHeldQuant = initialquantity.get(0);
        String initialSoldQuant = initialquantity.get(1);
        basePage.waitForSeconds(1);
        sellUSOrder.verifyUSsellPage(softAssert);
        sellCurrentPrice = buyUsOrder.getCurrentPriceUs();
        basePage.waitForSeconds(2);
        sellUSOrder.clickplusbutton();
        String sellQuantity = sellUSOrder.getQuantity();
        basePage.waitForSeconds(2);
        buyDomestic.hideKeyBoardInExecution();
        buyUsOrder.selectMarketTab();
        captureScreenshot("selectMarketTab.png");
        basePage.scrollToView("確認画面へ");
        basePage.waitForSeconds(3);
        buyUsOrder.clickOnTradingPasswordInputField(testData.getPassPin());
        captureScreenshot("clickOnTradingPasswordInputField.png");
        buyUsOrder.clickOnOrderConfirmBtn();
        basePage.waitForSeconds(1);
        captureScreenshot("checkif_any_error_popup.png");
        basePage.waitForSeconds(2);
        handlePopup.checkIfErrorPopup();
        buyUsOrder.verifyOrderReviewScreen(softAssert);
        captureScreenshot("verifyOrderReviewScreen.png");
        basePage.scrollToView("注文する");
        buyDomestic.placeOrder();
        handlePopup.checkIfErrorPopup();
        basePage.waitForSeconds(2);
        captureScreenshot("close.png");
        buyDomestic.clickOnClose();
        basePage.waitForSeconds(2);
        handlePopup.checkIfErrorPopup();
        sellDomestic.verifyOrderScreenBeforeOms(buyUsName, sellQuantity, softAssert);
        captureScreenshot("verifyOrderScreenBeforeOms.png");
        sellDomestic.goToHoldingScreen();
        orderSummary.verifyStockInHoldingScreenSell(buyUsName, sellQuantity, initialHeldQuant, initialSoldQuant, softAssert);
        captureScreenshot("verifyStockInHoldingScreenSell.png");
        coreDBEvidenceCapture("us", tickerCode, "BeforeSellUs");
        basePage.waitForSeconds(4);
        omscommand.executeOMS( testData.getClientCode(), sellCurrentPrice, sellQuantity, "us sell", testData.getDate());
        basePage.waitForSeconds(10);
        coreDBEvidenceCapture("us", tickerCode, "AfterSellUs");
        buyDomestic.goToOrderEnquiry();
        basePage.waitForSeconds(2);
        sellDomestic.verifyOrderScreenAfteroms(buyUsName, sellQuantity, softAssert);
        captureScreenshot("verifyOrderScreenAfteroms.png");
        sellDomestic.goToHoldingScreen();
        basePage.waitForSeconds(3);
        handlePopup.checkIfErrorPopup();
        orderSummary.verifyStockInHoldingScreenSellafterOms(buyUsName, sellQuantity, initialHeldQuant, initialSoldQuant, softAssert);
        captureScreenshot("verifyStockInHoldingScreenSellafterOms.png");
        sellDomestic.goToExecutionInquiryScreen();
        basePage.waitForSeconds(2);
        buyDomestic.verifyExecutionInquiryScreenAfterOms(testData.getStockNameUS(), sellQuantity, softAssert, "sell");
        captureScreenshot("executionhistory.png");
        verification.verifyTriggeredAlertIndevice(softAssert, "sell", sellQuantity, sellCurrentPrice, buyUsName, "USexecutionAlert.png");
        verification.verifyTriggeredAlertInstatusList(softAssert, "sell", sellQuantity, sellCurrentPrice, buyUsName);
    }

}