package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Set;

public class WindowHandlingExample {
    public static void main(String[] args) throws InterruptedException {
        // Setup ChromeDriver using WebDriverManager
       //WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Maximize browser
        driver.manage().window().maximize();

        // Open test website
        driver.get("https://www.google.com/");

        // Store the current window handle
        String parentWindow = driver.getWindowHandle();

        // Click on the link that opens a new window
        driver.findElement(By.linkText("Click Here")).click();

        // Get all window handles
        Set<String> allWindows = driver.getWindowHandles();

        // Switch to the new window
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // Print title of the new window
        System.out.println("New Window Title: " + driver.getTitle());

        // Optional wait to see the window
        Thread.sleep(2000);

        // Close the new window
        driver.close();

        // Switch back to the parent window
        driver.switchTo().window(parentWindow);

        // Print title of parent window
        System.out.println("Back to Parent Window Title: " + driver.getTitle());

        // Close the browser
        driver.quit();
    }
}
