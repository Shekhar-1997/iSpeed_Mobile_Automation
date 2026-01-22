package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import utils.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;

public class NotificationBarPage extends BasePage {
    private By expandIcon = By.xpath("(//android.widget.Button[@resource-id='android:id/expand_button'])[1]");
    private By expandIcon2 = By.xpath("(/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.FrameLayout[3]/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout");
    private By alertIndicator = By.id("");


    public NotificationBarPage(AppiumDriver driver) {
        super(driver);
    }

    public void openNotificationBar() {
        String appNameText = "ispeed";
        // Assuming 'driver' is your Appium driver instance
        ((AndroidDriver)driver).openNotifications();
        try {
            WebElement element = driver.findElement(expandIcon);
            element.click();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public WebElement openNotificationAndScrollToAppName(String appName) {
        // Open the notification bar

        // Use UiScrollable to scroll to the app name in the notification bar
        MobileElement element = (MobileElement) driver.findElementByCustom(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(\"" + appName + "\"));"
        );

        // Verify the element is found and print its text
        if (element != null) {
            System.out.println("Found notification: " + element.getText());
        } else {
            System.out.println("Notification with app name \"" + appName + "\" not found.");
        }
        return element;
    }



    public void waitForAlertElement() {
        try {
            // Example: Wait for an element that indicates an alert is present
           waitForElementToBeVisible(alertIndicator);   // Optionally, you can further validate or interact with the alertElement
        } catch (Exception e) {
            System.out.println("Alert element not found or timed out.");
            // Handle timeout or element not found exception
        }
    }

}
