package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtilForBrowser {

    // Method for setting implicit wait globally
    public static void setImplicitWait(WebDriver driver, long seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, java.util.concurrent.TimeUnit.SECONDS);
    }

    // Method for explicit wait on a specific element
    public static WebElement waitForElementExplicitly(WebDriver driver, By locator, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Example usage of a 16-second wait - you can call this method for general waits
    public static void wait16SecondsImplicitly(WebDriver driver) {
        setImplicitWait(driver, 10);
    }

    // Example usage of explicit wait for 16 seconds
    public static WebElement wait16SecondsExplicitly(WebDriver driver, By locator) {
        return waitForElementExplicitly(driver, locator, 16);
    }


}
