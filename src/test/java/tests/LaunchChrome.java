package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LaunchChrome {
    public static void main(String[] args) {

        // Create driver object (no need to set system property in Selenium 4.6+)
        WebDriver driver = new ChromeDriver();

        // Open a website
        driver.get("https://www.google.com");

        // Maximize window
        driver.manage().window().maximize();

        // Print page title
        System.out.println(driver.getTitle());

        // Close browser
        driver.quit();
    }
}

