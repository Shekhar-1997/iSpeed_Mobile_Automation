package pages;


import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tests.BaseTest;
import utils.BasePage;

public class HandleErrorPopup extends BasePage {
    private By errorPopupHeader = By.id("android:id/title_template");
    private By errorMessageBody = By.id("android:id/message");
    private By acceptButton = By.id("android:id/button1");
    private By errorHeader = By.xpath("//android.widget.TextView[@resource-id='android:id/alertTitle']");
    private By errorText = By.xpath("//android.widget.TextView[@resource-id='android:id/message']");
    private By errorOkButton = By.xpath("//android.widget.Button[@resource-id='android:id/button1']");
    BaseTest baseTest = new BaseTest();

    public HandleErrorPopup(AppiumDriver driver) {
        super(driver);
    }

    public void checkIfErrorPopup() {
        waitForSeconds(3);
        boolean value = isElementDisplayed(errorPopupHeader);
        if (value) {
            String errorMessage = getTextFromElement(errorMessageBody);
            baseTest.captureScreenshot("errorPopup.png");
            Assert.assertFalse(value, "errorMessage:" + errorMessage);
            clickElement(acceptButton);
        }
    }

    public void checkErrorInPreviousTab() {
        if (isElementVisible(errorHeader)){
            waitForElementToBeClickable(errorOkButton);
            clickElement(errorOkButton);
        }
    }

}
