package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import tests.BaseTest;
import utils.BasePage;

import java.util.ArrayList;
import java.util.List;


public class PushNotificationIOS extends BasePage {
    private By singlePushMessage = MobileBy.xpath("(//XCUIElementTypeOther[@name='NotificationShortLookView' and contains(@label, 'iSPEED')])[1]");
    private By pushMessages = MobileBy.xpath("//XCUIElementTypeOther[@name='NotificationShortLookView' and contains(@label, 'iSPEED')]");
    private By lockScreenDate = By.xpath("(//XCUIElementTypeOther[@name='lockscreen-date-view']/XCUIElementTypeStaticText)[1]");
    private By notificationCentre = By.xpath("//XCUIElementTypeStaticText[@name='Notification Centre']");

    BaseTest baseTest = new BaseTest();
    String alertMessage = null;
    boolean flag = false;


    public PushNotificationIOS(AppiumDriver driver) {
        super(driver);
    }

    public String getSinglePushNotification(String stockDetail, String pushMessageType) {
        openNotificationInIOS();
        baseTest.captureScreenshot(pushMessageType);


        // checkLockScreen();
        boolean notificationelement = isElementDisplayed(singlePushMessage);
        if (notificationelement) {
            WebElement pushMessage = waitForElementToBeVisible(singlePushMessage);
            String pushMessagesingle = pushMessage.getText();
            if (pushMessagesingle.contains(stockDetail)) {
                alertMessage = pushMessagesingle;
            }
        }
        scrollToTopIos();
        return alertMessage;
    }

    public String getMarginLoscutPushNotification(String pushMessageType) {
        openNotificationInIOS();
        baseTest.captureScreenshot(pushMessageType);

        WebElement pushMessage = waitForElementToBeVisible(singlePushMessage);
        String pushMessagesingle = pushMessage.getText();

        alertMessage = pushMessagesingle;

        return alertMessage;
    }

    public List<String> getPushNotificationList(String expectedStockName, String pushMessageType) {
        openNotificationInIOS();
        baseTest.captureScreenshot(pushMessageType);
        List<String> pushNotificationList = new ArrayList<>();
        List<WebElement> pushMessage = driver.findElements(pushMessages);
        for (WebElement element : pushMessage) {
            String pushMessages = element.getText();
            if (pushMessages.contains(expectedStockName)) {
                pushNotificationList.add(pushMessages);
            }
        }
        scrollToTopIos();
        return pushNotificationList;
    }

    public List<String> getCorporateActionPushNotificationList(String pushMessageType) {
        openNotificationInIOS();
        baseTest.captureScreenshot(pushMessageType);
        List<String> pushNotificationList = new ArrayList<>();
        boolean pushmessageElement=isElementVisible(pushMessages);
        if(pushmessageElement){
        List<WebElement> pushMessage = driver.findElements(pushMessages);
        for (WebElement element : pushMessage) {
            String pushMessages = element.getText();

            pushNotificationList.add(pushMessages);

        }}
        waitForSeconds(2);
        scrollToTopIos();
        return pushNotificationList;
    }
}


