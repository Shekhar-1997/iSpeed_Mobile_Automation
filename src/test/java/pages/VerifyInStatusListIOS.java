package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import utils.BasePage;

import static tests.PageOjectManager.deleteAlert;

public class VerifyInStatusListIOS extends BasePage {

    public VerifyInStatusListIOS(AppiumDriver driver) {
        super(driver);
    }

    public String verifyInAlertStatusListPush(String price, String stockDetail, SoftAssert softAssert, boolean isIndex) {

        String text = null;
        String stockIdentifier = null;
        WebElement priceElement = null;

        try {
            if (driver.getPlatformName().equalsIgnoreCase("ios")) {
                deleteAlert.clickOnselectToDelete();
                deleteAlert.clickSaveButton();
            }
            priceElement = scrollToView(price);
        } catch (Exception e) {
            // Handle exception or log error
        }
        waitForSeconds(2);

        if (priceElement != null) {
            System.out.println(priceElement.getText());
            text = priceElement.getText();

            // Determine XPath based on the region
            String xpath = null;
            if (driver.getPlatformName().equalsIgnoreCase("ios")) {
                xpath = "//XCUIElementTypeStaticText[@name='" + text + "']/..//XCUIElementTypeStaticText[2]";
                xpath = formatXPath(xpath);
            }
            WebElement stockElement = driver.findElement(By.xpath(xpath));
            stockIdentifier = stockElement.getText();
            System.out.println(stockIdentifier);
            softAssert.assertEquals(stockIdentifier, stockDetail, "stockName not matched");

        } else {
            System.out.println("Element is null");
        }

        return text;
    }
}
