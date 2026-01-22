package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.BaseTest;
import utils.BasePage;

import static tests.PageOjectManager.*;

import utils.BuyAndSellOrderTestJP;
import utils.CustomSoftAssert;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class CreateAlertsForMigration extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    BuyAndSellOrderTestJP buy = new BuyAndSellOrderTestJP();

    // Sample input list for dscrCD1
    private final List<String> dscrCD = Arrays.asList("2760","3067", "1945"); // "3067","3415","4186", "2760", "4635", "5423","5445");
    private final List<String> tickerCD = Arrays.asList("AA", "A"); //,"ABEO","GBLI","AAL","AAP"
    private final List<String> indexAlertNippon = Arrays.asList("NASDAQ");// "S&P100指数"); //, "S&P通信サービス株", "Gold先物(COMEX)"
    private final List<String> indexAlertSakimono = Arrays.asList("グロース先物(期近)","JPX日経400", "日経225"); //,"Copper先物(COMEX)"
    private final List<String> indexAlertUS = Arrays.asList("NQ米国大型株", "NQ米国中型株");// "NQ米国小型株", "NQ工業株", "NQ銀行株", "NQ保険株");
    private final List<String> stockOrderJP = Arrays.asList("2760", "3067");// "NQ米国小型株", "NQ工業株", "NQ銀行株", "NQ保険株");
    private final List<String> stockOrderUS = Arrays.asList("NQ米国大型株", "NQ米国中型株");// "NQ米国小型株", "NQ工業株", "NQ銀行株", "NQ保険株");

    @Test(priority = 1)
    public void createJPStockAlert() {

        createAlertUtil.prerequisiteForAlertSettingScreen();
        // Iterate through the dscrCD list for jp STOCKS
        for (int i = 0; i < dscrCD.size(); i++) {
            String code = dscrCD.get(i); // Get value at index i
            System.out.println("Processing Code JP: " + code); // Example action with the code

            // You can call any method that requires this code here
            createAlertUtil.createAllAlert(softAssert, code, "JP", "set", "preiceAlert", "preiceAlert1", false, false);
            // Example of using the code in another method, if applicable
            // editAlertSettingPage.setPriceAlert(region, "up", testData.getIncrementAmount(), softAssert, alertType, code);
        }
    }

    @Test(priority = 2)
    public void createUSStockAlert() {
        // driver.navigate().back();
        //menuPage.clickOnClose();
        menuPage.clickOnMenu();
        createAlertUtil.prerequisiteForAlertSettingScreen();

        // Iterate through the dscrCD1 list for jp STOCKS
        for (int i = 0; i < tickerCD.size(); i++) {
            String code = tickerCD.get(i); // Get value at index i
            System.out.println("Processing Code US: " + code); // Example action with the code

            // You can call any method that requires this code here
            createAlertUtil.createAllAlert(softAssert, code, "US", "set", "preiceAlert", "preiceAlert1", false, false);
            // Example of using the code in another method, if applicable
            // editAlertSettingPage.setPriceAlert(region, "up", testData.getIncrementAmount(), softAssert, alertType, code);
        }
    }

    @Test(priority = 3)
    public void createIndexAlertNippon() {
//        driver.navigate().back();
//        menuPage.clickOnClose();
        menuPage.clickOnMenu();
        createAlertUtil.prerequisiteForAlertSettingScreen();

        // Iterate through the dscrCD1 list for jp STOCKS
        for (int i = 0; i < indexAlertNippon.size(); i++) {
            String code = indexAlertNippon.get(i); // Get value at index i
            System.out.println("Processing Code JPIndex: " + code); // Example action with the code

            // You can call any method that requires this code here
            createAlertUtil.createAllAlert(softAssert, code, "Index", "set", "preiceAlert", "preiceAlert1", true, false);
            // Example of using the code in another method, if applicable
            // editAlertSettingPage.setPriceAlert(region, "up", testData.getIncrementAmount(), softAssert, alertType, code);
        }
    }

   // @Test(priority = 4)
    public void createIndexAlertSakimono() {
        driver.navigate().back();
        //menuPage.clickOnClose();
        menuPage.clickOnMenu();
        createAlertUtil.prerequisiteForAlertSettingScreen();

        // Iterate through the dscrCD1 list for jp STOCKS
        for (int i = 0; i < indexAlertSakimono.size(); i++) {
            String code = indexAlertSakimono.get(i); // Get value at index i
            System.out.println("Processing Code JPIndexSakimono: " + code); // Example action with the code

            // You can call any method that requires this code here
            createAlertUtil.createAllAlert(softAssert, code, "Index", "set", "preiceAlert", "preiceAlert1", true, false);
            // Example of using the code in another method, if applicable
            // editAlertSettingPage.setPriceAlert(region, "up", testData.getIncrementAmount(), softAssert, alertType, code);
        }
    }

    @Test(priority = 5)
    public void createIndexAlertUS() {
        driver.navigate().back();
       // menuPage.clickOnClose();
        //menuPage.clickOnMenu();
        createAlertUtil.prerequisiteForAlertSettingScreen();

        // Iterate through the dscrCD1 list for jp STOCKS
        for (int i = 0; i < indexAlertUS.size(); i++) {
            String code = indexAlertUS.get(i); // Get value at index i
            System.out.println("Processing Code IndexUS: " + code); // Example action with the code

            // You can call any method that requires this code here
            createAlertUtil.createAllAlert(softAssert, code, "Index", "set", "preiceAlert", "preiceAlert1", true, false);
            // Example of using the code in another method, if applicable
            // editAlertSettingPage.setPriceAlert(region, "up", testData.getIncrementAmount(), softAssert, alertType, code);
        }
    }

    // below is for order data creationJP
  // @Test(priority = 6)
    public void createOrderData() throws IOException {
        driver.navigate().back();
        // menuPage.clickOnClose();
        menuPage.clickOnMenu();
        for (int i = 0; i < stockOrderJP.size(); i++) {
            String code = stockOrderJP.get(i); // Get value at index i
            System.out.println("Processing Code IndexUS: " + code); // Example action with the code

            // buyAndSellOrderTestJP.buyOrder(softAssert);
            //String stockCode = testData.getStockCodeBuySell();
//            pin = testData.getPassPin();
//            quantity = testData.getQuantity();
            basePage.waitForSeconds(2);
            myOptionPage.gotToOrderPage();
            buyDomestic.goToDomesticBuyOrderPage();
            myOptionPage.goToSearchOption();
            searchPage.searchStock(code);
            basePage.waitForSeconds(3);
            // String StockName = buyDomestic.getStockName();
            //JPstockName = basePage.getStockName(StockName);

            handlePopup.checkIfErrorPopup();
            boolean value = buyDomestic.checkAmount();
            if (!value) {
                System.out.print("put money");
                Assert.fail("amount is null");
            }
            buyDomestic.closeImageWidget(softAssert);
            buyDomestic.verifyBuyOrderPage(softAssert);
            String buyCurrentPrice = buyDomestic.getCurrentPrice();
            buyDomestic.disableSOR();
            buyDomestic.setQuantity("200");
            basePage.waitForSeconds(2);
            buyDomestic.selectMarketPriceTab();
            // basePage.hideKeyboard();
            captureScreenshot("BuyOrder_SetQuantity.png");
            if (deviceType.equalsIgnoreCase("ios")) {
                basePage.scrollToBottomIos();
            } else {
                basePage.scrollToView("確認画面へ");
            }
            buyDomestic.passPin("1234");

            captureScreenshot("Confirm_Order.png");
            buyDomestic.confirmOrder();
            basePage.waitForSeconds(3);
            handlePopup.checkIfErrorPopup();
            // buyDomestic.verifySummuryScreen(softAssert);
            basePage.waitForSeconds(3);
            captureScreenshot("Summary_screen.png");
            if (deviceType.equalsIgnoreCase("ios")) {
                basePage.scrollToBottomIos();
            } else {
                basePage.scrollToView("注文する");
            }
            basePage.waitForSeconds(2);
            captureScreenshot("placeOrder_screen.png");
            buyDomestic.placeOrder();
            basePage.waitForSeconds(2);
            handlePopup.checkIfErrorPopup();
            captureScreenshot("close_sceen.png");
            buyDomestic.clickOnClose();
            basePage.waitForSeconds(4);
            driver.navigate().back();
          //  menuPage.clickOnClose();
            //menuPage.clickOnMenu();
            // Example of using the code in another method, if applicable
            // editAlertSettingPage.setPriceAlert(region, "up", testData.getIncrementAmount(), softAssert, alertType, code);
        }

    }

}
