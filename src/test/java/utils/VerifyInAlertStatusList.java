package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import tests.BaseTest;

import static tests.BaseTest.deviceType;
import static tests.PageOjectManager.*;

public class VerifyInAlertStatusList extends BaseTest {



    public String extractNumericPart(String input) {
        // Use regular expression to remove all non-numeric characters except for comma and dot
        return input.replaceAll("[^0-9,\\.]", "");
    }

    public void verifyBuySellInAlertStatusList(String quantity, String price, String executionType, String stockName, SoftAssert softAssert) {//,String expectedMessage
        if (executionType.equals("buy")) {
            WebElement element = basePage.scrollToView("" + quantity + "株 " + price + "円で約定しました。");
            captureScreenshot("alertTriggered_in_alert_status_list");
            softAssert.assertTrue(element != null);
            if (element != null) {
                String text = element.getText();
                String actualStockName = null;
                if (deviceType.equalsIgnoreCase("ios")) {
                    String xpath = "//XCUIElementTypeStaticText[@name='" + text + "']/..//XCUIElementTypeStaticText[3]";
                    System.out.println(text);
                    xpath = basePage.formatXPath(xpath);
                    actualStockName = driver.findElement(By.xpath(xpath)).getText();

                } else {
                    actualStockName = (driver.findElement(By.xpath("(//*[@text='" + text + "']/../preceding-sibling::*[2]/android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/stock_name'])[1]"))).getText();
                }
                System.out.println("alert triggered for buy.");
                softAssert.assertEquals(actualStockName, stockName);
                basePage.waitForSeconds(2);
            } else {
                Assert.fail();
            }
        } else if (executionType.equals("sell")) {
            WebElement element = basePage.scrollToView("" + quantity + "株 " + price + "円で約定しました。");
            captureScreenshot("alertTriggered_in_alert_status_list");
            softAssert.assertTrue(element != null);
            if (element != null) {
                String text = element.getText();
                String actualStockName = null;
                if (deviceType.equalsIgnoreCase("ios")) {
                    String xpath = "//XCUIElementTypeStaticText[@name='" + text + "']/..//XCUIElementTypeStaticText[3]";
                    System.out.println(text);
                    xpath = basePage.formatXPath(xpath);
                    actualStockName = driver.findElement(By.xpath(xpath)).getText();
                } else {
                    actualStockName = (driver.findElement(By.xpath("(//*[@text='" + text + "']/../preceding-sibling::*[2]/android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/stock_name'])[1]"))).getText();
                }
                softAssert.assertEquals(actualStockName, stockName);
                basePage.waitForSeconds(2);
            } else {
                Assert.fail();
            }
        }
    }

    public void verifyCorporateActionInstatusList(String dscrCd, String expectedText, SoftAssert softAssert) {
        if(deviceType.equalsIgnoreCase("ios")){
            String pushMessageInstatusList=statusListIOS.verifyInAlertStatusListPush(expectedText,dscrCd,softAssert,false);
            if(pushMessageInstatusList ==null){
                System.out.println("corporation alert not recieved");
            }
        }
        else{
        WebElement element = basePage.scrollToView(expectedText);

        softAssert.assertTrue(element != null);
        if(element!=null){
        System.out.println(element.getText());
        String text = element.getText();
        By statuslistPushMessage=By.xpath("//*[@text='" + text + "']/../..//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/market_name']");
        boolean pushMessageInstatusList= basePage.isElementVisible(statuslistPushMessage);
        if(pushMessageInstatusList) {
            WebElement stockElement = driver.findElement(By.xpath("//*[@text='" + text + "']/../..//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/market_name']"));
            String actualdscrCd = stockElement.getText();
            System.out.println(dscrCd);
            softAssert.assertEquals(actualdscrCd, dscrCd);
            basePage.waitForSeconds(2);
        }}else{
            System.out.println("corporation alert not recieved");
        }}
    }

    private By statusList;

    private void locators() {
        if (deviceType.equalsIgnoreCase("ios")) {
            statusList = By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]//XCUIElementTypeStaticText[5]");//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[5]");
        } else {
            statusList = By.xpath("(//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/content_basic'])[1]");
        }
    }


    public void verifyMarginLosscutAlertInStatusList(String message, SoftAssert softAssert, String fileName) {
        locators();
        try {
            if (driver.getPlatformName().equalsIgnoreCase("ios")) {
                deleteAlert.clickOnselectToDelete();
                deleteAlert.clickSaveButton();
            }
        } catch (Exception e) {
            // Handle exception or log error
        }
        basePage.waitForElementToBeVisible(statusList);
        captureScreenshot(fileName);
        String actualMessage = basePage.getTextFromElement(statusList);
        softAssert.assertEquals(actualMessage, message);


    }

    public String verifyAlertsInStatusListPush(String price, String stockDetail, SoftAssert softAssert) {
        String text = null;
        String stockIdentifier = null;
        WebElement priceElement = null;

        try {
            priceElement = basePage.scrollToView(price);
        } catch (Exception e) {
            // Handle exception or log error
        }
        basePage.waitForSeconds(2);

        if (priceElement != null) {
            System.out.println(priceElement.getText());
            text = priceElement.getText();

            // Determine XPath based on the region


            String xpath = "(//*[@text='" + text + "']/../..//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/stock_name'])[1]";


            WebElement stockElement = driver.findElement(By.xpath(xpath));
            stockIdentifier = stockElement.getText();
            System.out.println(stockIdentifier);
            softAssert.assertEquals(stockIdentifier, stockDetail, "stockName not matched");

        } else {
            System.out.println("Element is null");
        }

        return text;
    }

    public void verifyExpiryAlertInStatusList(String expectedText, SoftAssert softAssert) {
        WebElement element = basePage.scrollToView(expectedText);
        softAssert.assertTrue(element != null);
        System.out.println(element.getText());
        String text = element.getText();
        WebElement stockElement = driver.findElement(By.xpath("//*[@text='" + text + "']/../..//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/market_name']"));
        String actualText = stockElement.getText();
        System.out.println(actualText);
        softAssert.assertEquals(actualText, expectedText);
        basePage.waitForSeconds(2);

    }
}



