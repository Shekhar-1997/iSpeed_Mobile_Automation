package tests;

//public class WifiManagerIOS {
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.URL;

public class WifiManagerIOS {

    @Test
    public void wifiToggle() {
        try {
            // Set up Desired Capabilities
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("appium:deviceName", "iPhone 11");
            capabilities.setCapability("appium:platformVersion", "17.6.1");
            capabilities.setCapability("appium:udid", "00008030-000641EE0E68802E");
            capabilities.setCapability("appium:automationName", "XCUITest");

            // Use the Settings app bundle ID to access Wi-Fi settings
            capabilities.setCapability("appium:bundleId", "com.apple.Preferences");
            capabilities.setCapability("appium:wdaLaunchTimeout", "60000");
            capabilities.setCapability("appium:wdaLocalPort", "8100");
            capabilities.setCapability("appium:wdaStartupRetries", "3");
            capabilities.setCapability("appium:wdaConnectionTimeout", "60000");

            // Initialize the Appium Driver
            IOSDriver<MobileElement> driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

            // Navigate to Wi-Fi settings
            driver.findElementByAccessibilityId("Wi-Fi").click();

            // Locate the Wi-Fi toggle switch
            MobileElement wifiSwitch = driver.findElementByXPath("//XCUIElementTypeSwitch");
            String switchValue = wifiSwitch.getAttribute("value"); // 1 = On, 0 = Off

            if ("1".equals(switchValue)) {
                wifiSwitch.click(); // Turn WiFi Off
                System.out.println("WiFi turned off.");
            } else {
                wifiSwitch.click(); // Turn WiFi On
                System.out.println("WiFi turned on.");
            }

            // Close the driver
            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

