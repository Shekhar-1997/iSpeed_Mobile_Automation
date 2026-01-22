package tests;

import io.qameta.allure.Feature;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.*;
import utils.BuyAndSellOrderTestJP;
import utils.BuyAndSellOrderUS;

import java.io.IOException;

import static tests.PageOjectManager.*;


public class OrderExecutionAlert extends BaseTest {
    String JPstockName;
    String buyUsName;

    SoftAssert softAssert;

    @BeforeMethod
    public void initializeSoftAssert() {
        softAssert = new SoftAssert();
        menuPage.goToMenuPage();
        myOptionPage.gotToOrderPage();
        if (!buyDomestic.isOrderScreen()) {
            buyDomestic.goBackToOrderScreen(softAssert);
            menuPage.goToMenuPage();
            myOptionPage.gotToOrderPage();
        }

    }

    @Test(priority = 1 )
    public void buyOrderJP() throws IOException {
        JPstockName=buyAndSellOrderTestJP.buyOrder(softAssert);
        softAssert.assertAll();
    }

    @Feature("Sell Feature")
    @Test(priority = 2 )
    //Sell JP order
    public void sellOrderJP() throws IOException {
        buyAndSellOrderTestJP.sellOrder(JPstockName, softAssert);
        softAssert.assertAll();
    }

    @Feature("buy US order")
    @Test(priority = 4 )
    public void buyOrderUS() throws IOException {

        buyUsName= buyAndSellOrderUS.buyUS(softAssert);
        softAssert.assertAll();
    }

    @Feature("Sell US order")
    @Test(priority = 5 )
    public void sellOrderUS() throws IOException {
        buyAndSellOrderUS.sellUS(buyUsName, softAssert);
        softAssert.assertAll();
    }


    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(result.getName() + "_failure.png");
        }
        menuPage.goToMenuPage();
    }

}
