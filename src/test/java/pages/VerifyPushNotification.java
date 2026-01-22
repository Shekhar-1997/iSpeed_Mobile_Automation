package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import tests.BaseTest;
import utils.BasePage;

import java.util.ArrayList;
import java.util.List;

public class VerifyPushNotification extends BasePage {

    private By expandIcon = By.xpath("//*[@text='iSPEED']/../..//android.widget.Button[@content-desc='Expand']");
    private By collapsedState = By.xpath("(//android.widget.Button[@content-desc=\"Expand\"])");
    private By ispeedElement = By.xpath("//*[contains(@text, 'iSPEED')]");
    public By appName = By.id("android:id/app_name_text");
    private By singlePushMessageHeader = By.xpath("(//*[@text='iSPEED']/../../..//android.widget.TextView[@resource-id='android:id/title'])[1]");
    private By singlePushMessage = By.xpath("(//*[@text='iSPEED']/../../..//android.widget.TextView[@resource-id='android:id/title']/../..//android.widget.TextView[@resource-id='android:id/text'])[1]");
    private By ListOfPushMessage = By.xpath("//*[@text='iSPEED']/../../..//android.widget.TextView[@resource-id='android:id/title']");
    BaseTest baseTest = new BaseTest();
    NotificationBarPage openNotificationBar;

    public VerifyPushNotification(AppiumDriver driver) {
        super(driver);
    }


    public void verifyMarginLossCutAlert(String message, SoftAssert softAssert, String fileName) {
        String messages = null;
        if (driver.getPlatformName().equalsIgnoreCase("ios")) {
            PushNotificationIOS pushNotificationIOS = new PushNotificationIOS(driver);
            messages = pushNotificationIOS.getMarginLoscutPushNotification(fileName);
        } else {
            expandNotification(fileName);
            baseTest.captureScreenshot(fileName);
            messages = driver.findElement(singlePushMessageHeader).getText();
        }
        softAssert.assertEquals(messages, message);

    }


    public void expandNotification(String fileName) {
        openNotificationBar = new NotificationBarPage(driver);
        ((AndroidDriver) driver).openNotifications();
        waitForSeconds(3);
        openNotificationBar.openNotificationAndScrollToAppName("iSPEED");
        boolean value = isElementDisplayed(ispeedElement);
        baseTest.captureScreenshot(fileName);
        if (!value) {
            driver.navigate().back();
        } else {
            boolean value2 = isElementDisplayed(expandIcon);
            if (value2) {
                clickElement(expandIcon);
                waitForSeconds(3);
                baseTest.captureScreenshot(fileName);
            }
        }
    }

    public List<String> verifyAlert(String fileName) {

        List<String> pushmessage = new ArrayList<>();
        expandNotification(fileName);
        List<WebElement> ele = driver.findElements(ListOfPushMessage);


        for (WebElement push : ele) {
            String actualMessage = push.getText();
            System.out.println(actualMessage);
            pushmessage.add(actualMessage);


        }

        return pushmessage;
    }

    public String verifyExcecutionAlert(String stockDetail, String fileName) {
        String messages = null;

        expandNotification(fileName);
        String pushmessages = driver.findElement(singlePushMessageHeader).getText();
        if (pushmessages.contains(stockDetail)) {
            System.out.println(pushmessages);
            messages = driver.findElement(singlePushMessage).getText();
            System.out.println(messages);
        }

        return messages;

    }

    public String verifyInrealDevice2(String stockDetail, String fileName) {
        String messages = null;
        waitForElementToBeVisible(singlePushMessageHeader);
        String pushmessages = getTextFromElement(singlePushMessageHeader);
        if (pushmessages.contains(stockDetail)) {
            System.out.println(pushmessages);
            waitForElementToBeVisible(singlePushMessage);
            messages = getTextFromElement(singlePushMessage);
        }

        return messages;

    }

    public List<String> verifyExpiryAlert(String fileName) {

        List<String> pushmessage = new ArrayList<>();
        expandNotification(fileName);
        List<WebElement> ele = driver.findElements(ListOfPushMessage);
        for (WebElement push : ele) {
            String actualMessage = push.getText();
            System.out.println(actualMessage);
            pushmessage.add(actualMessage);
        }
        return pushmessage;
    }

}