package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {
    private WebDriver driver;

    @Override
    public void onTestFailure(ITestResult result) {
        captureScreenshot(result.getName()); // Capture screenshot on test failure
    }

    private void captureScreenshot(String testName) {
        if (driver instanceof TakesScreenshot) {
            // Capture screenshot as byte array
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            // Attach the screenshot to the Allure report
            Allure.addAttachment(testName + "_failure", new ByteArrayInputStream(screenshot));
        } else {
            System.out.println("Driver does not support screenshots.");
        }
    }

}
