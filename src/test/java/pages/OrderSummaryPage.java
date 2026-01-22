package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.BasePage;

import java.util.ArrayList;
import java.util.List;

public class OrderSummaryPage extends BasePage {
    //    orderhistory tab=jp.co.rakuten_sec.ispeed:id/assets_tab_center_button
    private By holdTab = By.id("jp.co.rakuten_sec.ispeed:id/assets_tab_left_button");
    private By holdingQuantity = By.id("jp.co.rakuten_sec.ispeed:id/assets_holding_description_amount_stock_mouth_text");
    private By soldQuantity = By.id("jp.co.rakuten_sec.ispeed:id/assets_holding_description_execution_text");

    //
//   historytab= jp.co.rakuten_sec.ispeed:id/assets_tab_right_button
    private By holdingdescription = By.id("jp.co.rakuten_sec.ispeed:id/assets_holding_description_contents_layout");
//    orderdesciption="jp.co.rakuten_sec.ispeed:id/order_list_contents_layout"
    //cashExecution=jp.co.rakuten_sec.ispeed:id/cash_execution_list_contents_layout

    public OrderSummaryPage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isStockInhold(String stockName) {
        System.out.println("stockName:" + stockName);
        boolean value = false;
        WebElement element = scrollToView(stockName);
        if (element != null) {
            value = true;
        }
        return value;
    }

    public String getBuyQuantity() {
        waitForElementToBeVisible(holdingQuantity);
        String holdQuantity = getTextFromElement(holdingQuantity);
        return holdQuantity;
    }

    public String getSellQuantity() {
        waitForElementToBeVisible(soldQuantity);
        String soldQuant = getTextFromElement(soldQuantity);
        return soldQuant;

    }

    public List<String> verifyStockInHoldingScreen(String stockName) {
        List<String> quantity = new ArrayList<>();

        String heldquantity;
        String soldquantity;
        if (isStockInhold(stockName)) {
            if (driver.getPlatformName().equalsIgnoreCase("ios")) {
                heldquantity = driver.findElement(By.xpath("(//XCUIElementTypeTable//XCUIElementTypeCell[1])[1]//XCUIElementTypeStaticText[1]")).getAttribute("value");
                soldquantity = driver.findElement(By.xpath("(//XCUIElementTypeTable//XCUIElementTypeCell[1])[1]//XCUIElementTypeStaticText[11]")).getAttribute("value");

            } else {
                heldquantity = driver.findElement(By.xpath("//android.widget.TextView[@text='" + stockName + "']/../..//*[@resource-id='jp.co.rakuten_sec.ispeed:id/assets_holding_description_amount_stock_mouth_text']")).getText();
                soldquantity = driver.findElement(By.xpath("//android.widget.TextView[@text='" + stockName + "']/../..//*[@resource-id='jp.co.rakuten_sec.ispeed:id/assets_holding_description_execution_text']")).getText();
            }
            quantity.add(heldquantity);
            quantity.add(soldquantity);
            return quantity;
        } else {
            return null;
        }


    }

    public void verifyStockInHoldingScreenSell(String stockName, String quantity, String initialHeldQuant, String initialSoldquant, SoftAssert softAssert) {

        softAssert.assertTrue(isStockInhold(stockName));
        if (isStockInhold(stockName)) {
            String expectedQuantity = addMethod(initialSoldquant, quantity);
            String soldquantity = driver.findElement(By.xpath("//android.widget.TextView[@text='" + stockName + "']/../..//*[@resource-id='jp.co.rakuten_sec.ispeed:id/assets_holding_description_execution_text']")).getText();
            Assert.assertEquals(soldquantity, expectedQuantity);
        }


    }

    public void verifyStockInHoldingScreenSellafterOms(String stockName, String quantity, String initialHeldQuant, String initialSoldquant, SoftAssert softAssert) {
        if (initialHeldQuant.equals(quantity)) {
            waitForSeconds(2);
            Assert.assertFalse(isStockInhold(stockName));
        } else {
            Assert.assertTrue(isStockInhold(stockName));
            if (isStockInhold(stockName)) {
                String expectedQuantity = addMethod(initialSoldquant, quantity);
                String soldquantity = driver.findElement(By.xpath("//android.widget.TextView[@text='" + stockName + "']/../..//*[@resource-id='jp.co.rakuten_sec.ispeed:id/assets_holding_description_execution_text']")).getText();
                Assert.assertEquals(soldquantity, expectedQuantity);
            }
        }
    }

    public void verifyStockInHoldingScreenafterOMS(String stockName, SoftAssert softAssert, String initialheldQuantity, String initialSoldQuantity, String quantity, String type) {
        waitForSeconds(4);
        Assert.assertTrue(isStockInhold(stockName), "stock is not found in holding screen");

        if (isStockInhold(stockName)) {

            if (type.equals("buy")) {
                if (initialheldQuantity == null) {
                    By heldquantityElement = By.xpath("(//XCUIElementTypeTable//XCUIElementTypeCell[1])[1]//XCUIElementTypeStaticText[11]");
                    String heldquantity = null;
                    if (driver.getPlatformName().equalsIgnoreCase("ios")) {
                        heldquantity = driver.findElement(heldquantityElement).getAttribute("value");
                    } else {
                        heldquantity = driver.findElement(By.xpath("//android.widget.TextView[@text='" + stockName + "']/../..//*[@resource-id='jp.co.rakuten_sec.ispeed:id/assets_holding_description_amount_stock_mouth_text']")).getText();
                    }
                    Assert.assertEquals(heldquantity, quantity);

                } else {
                    String newheldQuantity = addMethod(initialheldQuantity, quantity);

                    String heldquantity1 = driver.findElement(By.xpath("//android.widget.TextView[@text='" + stockName + "']/../..//*[@resource-id='jp.co.rakuten_sec.ispeed:id/assets_holding_description_amount_stock_mouth_text']")).getText();
                    Assert.assertEquals(heldquantity1, newheldQuantity);
                }
            } else if (type.equals("sell")) {
                String soldQuatity = null;
                By soldQuantityElementios = By.xpath("(//XCUIElementTypeTable//XCUIElementTypeCell[1])[1]//XCUIElementTypeStaticText[1]");
                if (initialSoldQuantity == null) {
                    if (driver.getPlatformName().equalsIgnoreCase("ios")) {
                        soldQuatity = driver.findElement(soldQuantityElementios).getAttribute("value");
                    } else {
                        soldQuatity = driver.findElement(By.xpath("//android.widget.TextView[@text='" + stockName + "']/../..//*[@resource-id='jp.co.rakuten_sec.ispeed:id/assets_holding_description_execution_text']")).getText();
                    }
                    Assert.assertEquals(soldQuatity, quantity);
                }
                String newsoldQuantity = addMethod(initialSoldQuantity, quantity);
                String soldquantity = driver.findElement(By.xpath("//android.widget.TextView[@text='" + stockName + "']/../..//*[@resource-id='jp.co.rakuten_sec.ispeed:id/assets_holding_description_execution_text']")).getText();

                Assert.assertEquals(soldquantity, newsoldQuantity);

            }
        }

    }

    public String addMethod(String quantity, String initialQuantity) {
        String newQuantity1 = initialQuantity;
        if (initialQuantity.contains(",")) {
            newQuantity1 = removeComma(initialQuantity);
        }
        int q = Integer.parseInt(quantity);
        int r = Integer.parseInt(newQuantity1);
        int newQuantity = q + r;
        System.out.println(newQuantity);
        return String.valueOf(newQuantity);
    }

}



