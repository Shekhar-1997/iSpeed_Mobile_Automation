package tests;

import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static tests.PageOjectManager.*;

public class UnInstall extends BaseTest {

    public static SoftAssert softAssert;

    @Test(priority = 1)
    public void setAlerts() {

        softAssert = new SoftAssert();
        createAlertUtil.prerequisiteForAlertSettingScreen();
        createAlertUtil.createAlert(softAssert, testData.getJpStockpreviousDown(), "JP", "create", "down", String.valueOf(testData.getPreviousDayRatioDecrementAmount()), "account cancellation previousday down", true, false, "previousDayDown");

        dbEvidenceCapture("Uninstall", "setAlertBeforeUninstall", 1, false);

        basePage.waitForSeconds(3);

        // ADB commands: Navigate to home and swipe up
        navigateToHomeAndSwipeUp();

        basePage.waitForSeconds(3);
        uninstallApp();

        basePage.waitForSeconds(3);
        dbEvidenceCapture("Uninstall", "setAlertAfterUninstall", 1, false);
    }

    // Method to execute ADB commands for navigating to the home screen and swiping up
    public void navigateToHomeAndSwipeUp() {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence tap = new Sequence(finger, 1);
        try {
            // Navigate to home screen
            Runtime.getRuntime().exec("adb shell input keyevent KEYCODE_HOME");
            System.out.println("Navigated to home screen.");

            // Swipe up to open the app drawer
            basePage.waitForSeconds(2);
            Runtime.getRuntime().exec("adb shell input swipe 500 1500 500 500");
            basePage.waitForSeconds(1);
            captureScreenshot("iSpeedAppBeforeUninstall.png");
            System.out.println("Swipe up executed.");
        } catch (Exception e) {
            System.out.println("Error executing ADB commands: " + e.getMessage());
        }
    }

    public void uninstallApp() {
        if (driver != null) {
            if (driver.isAppInstalled(ANDROID_PACKAGE_NAME)) {
                driver.removeApp(ANDROID_PACKAGE_NAME);
                System.out.println("App uninstalled successfully.");
                basePage.waitForSeconds(5);
                //navigateToHomeAndSwipeUp();
                captureScreenshot("iSpeedAppAfterUninstall.png");

                // Install the app again
                driver.installApp(APP_PATH);
                System.out.println("App reinstalled successfully.");
            } else {
                System.out.println("App is not installed on the device.");
            }
        } else {
            System.out.println("Driver is not initialized.");
        }
        // Verify alert in notification bar and settings
    }
}
