package tests;

import io.qameta.allure.Feature;
import org.apache.log4j.Priority;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import java.util.Arrays;
import java.util.List;


import static tests.PageOjectManager.*;

public class Navigation extends BaseTest {
    static String val;
    private static final List<String> clientCode = Arrays.asList("SL750092");
    // private static final List<String> stockCode = Arrays.asList("9401", "5423", "1945");

    @DataProvider(name = "clientCodeProvider")
    public Object[][] clientCodeProvider() {
        Object[][] data = new Object[clientCode.size()][1];
        for (int i = 0; i < clientCode.size(); i++) {
            data[i][0] = clientCode.get(i);
        }
        return data;
    }

    // Test to validate the login functionality with valid credentials
    @Feature("Login feature")
    //@Test(dataProvider = "clientCodeProvider")
    public static void loginTest(String code) {
        System.out.println("Logging in with client code: " + code);
        login(code, testData.getPassword());
        editAlertSettingPage.screenNavigation();
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixMainMenu(), testData.getUsername(), "Menu", testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixMainMenu(), testData.getUsername(), "Menu", testData.getPrefix(), testData.getImageFileExtension());
        selectStocksInSetting();
        statusList();
        basePage.waitForSeconds(3);
        driver.navigate().back();
        menuPage.clickOnMenu();
        basePage.waitForSeconds(2);
        menuPage.logout();


    }

//    @DataProvider(name = "stockCodeProvider")
//    public Object[][] stockCodeProvider() {
//        Object[][] data = new Object[stockCode.size()][1];
//        for (int i = 0; i < stockCode.size(); i++) {
//            data[i][0] = stockCode.get(i);
//        }
//        return data;
//    }

//    @Feature("Select Stocks")
//    @Test(dataProvider = "stockCodeProvider")

    //     "jpStock1": "1945",
//             "jpStock2": "5423",
//             "jpStock3": "9401",
//             "jpStock4": "4635",
//             "jpStock5": "4745",

    //missing prefix: EASL, OS, OS_U,
    @Test
    public static void selectStocksInSetting() {
        String userNameValue = userName();
        val = userNameValue.trim();
        basePage.waitForSeconds(2);
        editAlertSettingPage.clickOnAlertIcon();
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixAlertSettingScreen(), testData.getUsername(), "Settings", testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixAlertSettingScreen(), testData.getUsername(), "Settings", testData.getPrefix(), testData.getImageFileExtension());
        basePage.waitForSeconds(2);
        editAlertSettingPage.selectStockFromSetting(testData.getJpStock1());
        editAlertSettingPage.selectPresentValueTab();
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getJpStock1(), testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getJpStock1(), testData.getPrefix(), testData.getImageFileExtension());
        editAlertSettingPage.selectPreviousDayRatioTab(new SoftAssert());
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getJpStock1(), testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getJpStock1(), testData.getPrefix(), testData.getImageFileExtension());
        basePage.waitForSeconds(2);
        driver.navigate().back();

        basePage.waitForSeconds(2);
        editAlertSettingPage.selectStockFromSetting(testData.getJpStock2());
        editAlertSettingPage.selectPresentValueTab();
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getJpStock2(), testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getJpStock2(), testData.getPrefix(), testData.getImageFileExtension());
        editAlertSettingPage.selectPreviousDayRatioTab(new SoftAssert());
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getJpStock2(), testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getJpStock2(), testData.getPrefix(), testData.getImageFileExtension());
        basePage.waitForSeconds(2);
        driver.navigate().back();


//        basePage.waitForSeconds(2);
//        editAlertSettingPage.selectStockFromSetting(testData.getJpStock3());
//        editAlertSettingPage.selectPresentValueTab();
//        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getJpStock3(), testData.getPrefix(), testData.getTextFileExtension());
//        pageSourceUtil.captureScreenshot(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getJpStock3(), testData.getPrefix(), testData.getImageFileExtension());
//        editAlertSettingPage.selectPreviousDayRatioTab(new SoftAssert());
//        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getJpStock3(), testData.getPrefix(), testData.getTextFileExtension());
//        pageSourceUtil.captureScreenshot(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getJpStock3(), testData.getPrefix(), testData.getImageFileExtension());
//        basePage.waitForSeconds(2);
//        driver.navigate().back();
//
//
//        basePage.waitForSeconds(2);
//        editAlertSettingPage.selectStockFromSetting(testData.getJpStock4());
//        editAlertSettingPage.selectPresentValueTab();
//        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getJpStock4(), testData.getPrefix(), testData.getTextFileExtension());
//        pageSourceUtil.captureScreenshot(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getJpStock4(), testData.getPrefix(), testData.getImageFileExtension());
//        editAlertSettingPage.selectPreviousDayRatioTab(new SoftAssert());
//        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getJpStock4(), testData.getPrefix(), testData.getTextFileExtension());
//        pageSourceUtil.captureScreenshot(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getJpStock4(), testData.getPrefix(), testData.getImageFileExtension());
//        basePage.waitForSeconds(2);
//        driver.navigate().back();
//
//
//        basePage.waitForSeconds(2);
//        editAlertSettingPage.selectStockFromSetting(testData.getJpStock5());
//        editAlertSettingPage.selectPresentValueTab();
//        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getJpStock5(), testData.getPrefix(), testData.getTextFileExtension());
//        pageSourceUtil.captureScreenshot(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getJpStock5(), testData.getPrefix(), testData.getImageFileExtension());
//        editAlertSettingPage.selectPreviousDayRatioTab(new SoftAssert());
//        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getJpStock5(), testData.getPrefix(), testData.getTextFileExtension());
//        pageSourceUtil.captureScreenshot(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getJpStock5(), testData.getPrefix(), testData.getImageFileExtension());
//        basePage.waitForSeconds(2);
//        driver.navigate().back();

        basePage.waitForSeconds(2);
        editAlertSettingPage.selectStockFromSetting(testData.getUsStock1());
        editAlertSettingPage.selectPresentValueTab();
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getUsStock1(), testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getUsStock1(), testData.getPrefix(), testData.getImageFileExtension());
        editAlertSettingPage.selectPreviousDayRatioTab(new SoftAssert());
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getUsStock1(), testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getUsStock1(), testData.getPrefix(), testData.getImageFileExtension());
        basePage.waitForSeconds(2);
        driver.navigate().back();

        basePage.waitForSeconds(2); //usStock3
        editAlertSettingPage.selectStockFromSetting(testData.getUsStock2());
        editAlertSettingPage.selectPresentValueTab();
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getUsStock2(), testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getUsStock2(), testData.getPrefix(), testData.getImageFileExtension());
        editAlertSettingPage.selectPreviousDayRatioTab(new SoftAssert());
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getUsStock2(), testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getUsStock2(), testData.getPrefix(), testData.getImageFileExtension());
        basePage.waitForSeconds(2);
        driver.navigate().back();


//        basePage.waitForSeconds(2); //usStock3
//        editAlertSettingPage.selectStockFromSetting(testData.getUsStock3());
//        editAlertSettingPage.selectPresentValueTab();
//        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getUsStock3(), testData.getPrefix(), testData.getTextFileExtension());
//        pageSourceUtil.captureScreenshot(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getUsStock3(), testData.getPrefix(), testData.getImageFileExtension());
//        editAlertSettingPage.selectPreviousDayRatioTab(new SoftAssert());
//        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getUsStock3(), testData.getPrefix(), testData.getTextFileExtension());
//        pageSourceUtil.captureScreenshot(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getUsStock3(), testData.getPrefix(), testData.getImageFileExtension());
//        basePage.waitForSeconds(2);
//        driver.navigate().back();

        basePage.waitForSeconds(2);
        editAlertSettingPage.selectStockFromSetting(testData.getIndexSakimono1());
        editAlertSettingPage.selectPresentValueTab();
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getIndexSakimono1(), testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getIndexSakimono1(), testData.getPrefix(), testData.getImageFileExtension());
        editAlertSettingPage.selectPreviousDayRatioTab(new SoftAssert());
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getIndexSakimono1(), testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getIndexSakimono1(), testData.getPrefix(), testData.getImageFileExtension());
        basePage.waitForSeconds(2);
        driver.navigate().back();


        basePage.waitForSeconds(2);
        editAlertSettingPage.selectStockFromSetting(testData.getIndexSakimono2());
        editAlertSettingPage.selectPresentValueTab();
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getIndexSakimono2(), testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getIndexSakimono2(), testData.getPrefix(), testData.getImageFileExtension());
        editAlertSettingPage.selectPreviousDayRatioTab(new SoftAssert());
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getIndexSakimono2(), testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getIndexSakimono2(), testData.getPrefix(), testData.getImageFileExtension());
        basePage.waitForSeconds(2);
        driver.navigate().back();

        basePage.waitForSeconds(2);
        editAlertSettingPage.selectStockFromSetting(testData.getIndexAlertUS1());
        editAlertSettingPage.selectPresentValueTab();
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getIndexAlertUS1(), testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getIndexAlertUS1(), testData.getPrefix(), testData.getImageFileExtension());
        editAlertSettingPage.selectPreviousDayRatioTab(new SoftAssert());
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getIndexAlertUS1(), testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getIndexAlertUS1(), testData.getPrefix(), testData.getImageFileExtension());
        basePage.waitForSeconds(2);
        driver.navigate().back();
//
//        basePage.waitForSeconds(2);
//        editAlertSettingPage.selectStockFromSetting(testData.getIndexAlertUS2());
//        editAlertSettingPage.selectPresentValueTab();
//        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getIndexAlertUS2(), testData.getPrefix(), testData.getTextFileExtension());
//        pageSourceUtil.captureScreenshot(testData.getSuffixCurrentDay(), testData.getUsername(), testData.getIndexAlertUS2(), testData.getPrefix(), testData.getImageFileExtension());
//        editAlertSettingPage.selectPreviousDayRatioTab(new SoftAssert());
//        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getIndexAlertUS2(), testData.getPrefix(), testData.getTextFileExtension());
//        pageSourceUtil.captureScreenshot(testData.getSuffixPreviousDay(), testData.getUsername(), testData.getIndexAlertUS2(), testData.getPrefix(), testData.getImageFileExtension());
        basePage.waitForSeconds(2);
        driver.navigate().back();
       editAlertSettingListPage();
        statusList();
        OrderScreensJP();
        JPOrderSellScreen();
        OrderScreensUS();

    }

    public static void editAlertSettingListPage() {
        alertSettingPage.clickOnSettingIcon();
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixEditAlertSettingScreen(), testData.getUsername(), "EditList", testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixEditAlertSettingScreen(), testData.getUsername(), "EditList", testData.getPrefix(), testData.getImageFileExtension());
        driver.navigate().back();
        menuPage.goToMenuPage();
    }

    public static void statusList() {
        menuPage.alertStatusList();
        basePage.waitForSeconds(4);
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixAlertStatusList(), testData.getUsername(), "Slist", testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixAlertStatusList(), testData.getUsername(), "Slist", testData.getPrefix(), testData.getImageFileExtension());
        driver.navigate().back();
        basePage.waitForSeconds(2);

    }


    public static void OrderScreensJP() {
        basePage.waitForSeconds(2);
        buyUsOrder.clickOnOrderIcon();
        buyDomestic.goToJPBuyOrderHistoryTab();
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixJapanOrderEnquiryTab(), testData.getUsername(), "Enquiry", testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixJapanOrderEnquiryTab(), testData.getUsername(), "Enquiry", testData.getPrefix(), testData.getImageFileExtension());
        buyDomestic.orderHistoryTab();
        basePage.waitForSeconds(2);
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixJapanOrderContractEnquiryTab(), testData.getUsername(), "ContactEnquiry", testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixJapanOrderContractEnquiryTab(), testData.getUsername(), "ContactEnquiry", testData.getPrefix(), testData.getImageFileExtension());
        buyDomestic.orderHoldingsTab();
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixJapanOrderHoldingsTab(), testData.getUsername(), "Holding", testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixJapanOrderHoldingsTab(), testData.getUsername(), "Holding", testData.getPrefix(), testData.getImageFileExtension());
        driver.navigate().back();
    }

    public static void JPOrderSellScreen() {
        basePage.waitForSeconds(2);
        buyDomestic.goToJpSellTab();
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixOrderSellJP(), testData.getUsername(), "Sell", testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixOrderSellJP(), testData.getUsername(), "Sell", testData.getPrefix(), testData.getImageFileExtension());
        driver.navigate().back();
    }

    public static void OrderScreensUS() {
        basePage.waitForSeconds(2);
        // buyUsOrder.clickOnOrderIcon();
        buyUsOrder.clickOnUsOrderTab();
        buyUsOrder.orderHistoryTabUS();
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixUSOrderEnquiryTab(), testData.getUsername(), "Enquiry", testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixUSOrderEnquiryTab(), testData.getUsername(), "Enquiry", testData.getPrefix(), testData.getImageFileExtension());
        buyDomestic.orderHistoryTab();
        basePage.waitForSeconds(2);
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixUSOrderContractEnquiryTab(), testData.getUsername(), "ContactEnquiry", testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixUSOrderContractEnquiryTab(), testData.getUsername(), "ContactEnquiry", testData.getPrefix(), testData.getImageFileExtension());
        buyDomestic.orderHoldingsTab();
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixUSOrderHoldingsTab(), testData.getUsername(), "Holding", testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixUSOrderHoldingsTab(), testData.getUsername(), "Holding", testData.getPrefix(), testData.getImageFileExtension());
        driver.navigate().back();
        buyDomestic.goToUSSellTab();
        pageSourceUtil.capturePageSourceStockCode(testData.getSuffixOrderSellUS(), testData.getUsername(), "Sell", testData.getPrefix(), testData.getTextFileExtension());
        pageSourceUtil.captureScreenshot(testData.getSuffixOrderSellUS(), testData.getUsername(), "Sell", testData.getPrefix(), testData.getImageFileExtension());
    }

    public static String userName() {
        String userName;
        userName = menuPage.getValue();
        return userName;

    }


    // Method to perform login, handle post-login setups, popups, and log inactive user data
    public static void login(String userName, String password) {
        loginPage.agrementPage();

        if (loginPage.isHomePage()) {
            loginPage.performLoginFromMenu(userName, password);

            if (loginPage.isNoticeOfAgreementDocumentPopup()) {
                //captureScreenshot("popupScreen.png");
                loginPage.closePopupAfterLogin();
                //captureScreenshot("menuScreen.png");
            }

            if (loginPage.isSetup()) {
                //captureScreenshot("setupScreen.png");
                loginPage.setBasicSetup();
                //captureScreenshot("popups.png");
                loginPage.closePopupsAfterLogin();
            }

        } else {
            loginPage.performLogin(testData.getUsername(), testData.getPassword());
            // captureScreenshot("loginPage.png");
            basePage.waitForSeconds(2);
            // System.out.println("am above screenshotUtil ");
            loginPage.clickLoginButton();
            System.out.println("am below screenshotUtil ");
            loginPage.proceedForSetup();
            //captureScreenshot("setupScreen.png");

            loginPage.allowNotification();
            //captureScreenshot("popupscreen.png");

            loginPage.closeNisaPopup();
            loginPage.allowPushNotification();
            loginPage.noticeOfagreementDocumentPopup();
            //captureScreenshot("noticePopup.png");


        }

        //inActiveUserDbData(methodName, alertType);
    }

    // write logic to do logout and call login method


}
