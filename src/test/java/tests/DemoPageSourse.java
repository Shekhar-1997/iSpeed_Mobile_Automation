//package tests; //DemoPageSourse
//
//
//
//import io.qameta.allure.Feature;
//import org.testng.ITestResult;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Factory;
//import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;
//import utils.*;
//
//import static tests.AccountCancelation.softAssert;
//import static tests.PageOjectManager.*;
//
//public class DemoPageSourse extends BaseTest {
//
//    public void menuPage() {
//        pageSourceUtil.capturePageSource("Menu_page.txt");
//        System.out.println("Navigating to Menu Page...");
//    }
//
//    public void alertSettingPage() {
//        createAlertUtil.prerequisiteForAlertSettingScreen();
//        pageSourceUtil.capturePageSource("Alert_Setting_Page.txt");
//        System.out.println("Navigating to Alert Setting Page...");
//    }
//
//    public void editAlertSettingPage() {
//        pageSourceUtil.capturePageSource("Edit_Alert_Setting_Page.txt");
//        System.out.println("Navigating to Edit_Alert_Setting_Page.txt...");
//    }
//
//    public void searchTabPage() {
//        pageSourceUtil.capturePageSource("Search_Tab_Page.txt");
//        System.out.println("Navigating to Search_Tab_Page.txt...");
//    }
//
//    public void searchStocksPage() {
//        pageSourceUtil.capturePageSource("Search_Stocks_Page.txt");
//        System.out.println("Navigating to Search_Stocks_Page.txt..");
//    }
//
//    public void japanStockPage() {
//        pageSourceUtil.capturePageSource("Japan_Stock_Page.txt");
//        System.out.println("Navigating to Japan_Stock_Page.txt...");
//    }
//
//    public void japanStockCurrentDayPricePage() {
//        pageSourceUtil.capturePageSource("Japan_Stock_Current_Day_Price_Page.txt");
//        System.out.println("Navigating to Japan_Stock_Current_Day_Price_Page.txt...");
//    }
//
//    public void japanStockPreviousDayRatioPage() {
//        pageSourceUtil.capturePageSource("Japan_Stock_Previous_Day_Ratio_Page.txt");
//        System.out.println("Navigating to Japan_Stock_Previous_Day_Ratio_Page.txt...");
//    }
//
//    public void orderHomePage() {
//        pageSourceUtil.capturePageSource("Order_Home_Page.txt");
//        System.out.println("Navigating to Order_Home_Page.txt..");
//    }
//
//    public void orderJpPurchasePage() {
//        pageSourceUtil.capturePageSource("Order_Jp_Purchase_Page.txt");
//        System.out.println("Navigating to Order_Jp_Purchase_Page.txt..");
//    }
//
//    public void orderJpSummaryPage() {
//        pageSourceUtil.capturePageSource("Order_Jp_Summary_Page.txt");
//        System.out.println("Navigating to Order_Jp_Summary_Page.txt...");
//    }
//
//    public void orderJpSuccessPage() {
//        pageSourceUtil.capturePageSource("Order_Jp_Success_Page.txt");
//        System.out.println("Navigating to Order_Jp_Success_Page.txt...");
//    }
//
//    public void orderUsPurchasePage() {
//        pageSourceUtil.capturePageSource("Order_Us_Purchase_Page.txt");
//        System.out.println("Navigating to Order_Us_Purchase_Page.txt...");
//    }
//
//    public void orderUsSummaryPage() {
//        pageSourceUtil.capturePageSource("Order_Us_Summary_Page.txt");
//        System.out.println("Navigating to Order_Us_Summary_Page.txt..");
//    }
//
//    public void orderUsSuccessPage() {
//        pageSourceUtil.capturePageSource("Order_Us_Success_Page.txt");
//        System.out.println("Navigating to Order_Us_Success_Page.txt...");
//    }
//
//    public void orderInquiryPage() {
//        pageSourceUtil.capturePageSource("Order_Inquiry_Page.txt");
//        System.out.println("Navigating to Order_Inquiry_Page.txt..");
//    }
//
//    public void orderContractInquiryPage() {
//        pageSourceUtil.capturePageSource("Order_Contract_Inquiry_Page.txt");
//        System.out.println("Navigating to Order_Contract_Inquiry_Page.txt..");
//    }
//
//    public void orderStockHeldPage() {
//        pageSourceUtil.capturePageSource("Order_Stock_Held_Page.txt");
//        System.out.println("Navigating to Order_Stock_Held_Page.txt..");
//    }
//
//    // Main method for testing
//    public static void main(String[] args) {
//        DemoPageSourse demo = new DemoPageSourse();
//
//        demo.menuPage();
//        demo.alertSettingPage();
//        demo.editAlertSettingPage();
//        demo.searchTabPage();
//        demo.searchStocksPage();
//        demo.japanStockPage();
//        demo.japanStockCurrentDayPricePage();
//        demo.japanStockPreviousDayRatioPage();
//        demo.orderHomePage();
//        demo.orderJpPurchasePage();
//        demo.orderJpSummaryPage();
//        demo.orderJpSuccessPage();
//        demo.orderUsPurchasePage();
//        demo.orderUsSummaryPage();
//        demo.orderUsSuccessPage();
//        demo.orderInquiryPage();
//        demo.orderContractInquiryPage();
//        demo.orderStockHeldPage();
//    }
//}
//
