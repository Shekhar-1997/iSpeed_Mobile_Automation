package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CrossTest {
    WebDriver driver;

    @DataProvider(name = "browserProvider",parallel = true)
    public Object[][] browserData() {
        return new Object[][] {
                {"chrome"},
                {"firefox"}
        };
    }

    @BeforeMethod
    public void setup() {
        // Setup will be handled inside test
    }

    @Test(dataProvider = "browserProvider")
    public void testBrowser(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser: " + browser);
        }

        driver.get("https://www.amazon.com/registries?ref_=nav_cs_registry&ref_=nav_cs_registry");
        System.out.println("Title: " + driver.getTitle());
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
