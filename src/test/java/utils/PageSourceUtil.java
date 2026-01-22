package utils; //PageSourceUtil

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import tests.BaseTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PageSourceUtil extends BaseTest {
    private static final String SOURCE_DIR = "page_sources/"; // Folder to store source files
    private static final String SOURCE_DIR_Stock = "Page_Sources_AS_IS/"; // Folder to store source files
    private static final String SCREENSHOT_DIR = "ScreenCapture_AS_IS/"; // Folder to store screenshots

    // Ensure the directory exists
    private void createDirectory(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // Capture and save the current screen's page source
    public void capturePageSource(String filename) {
        createDirectory(SOURCE_DIR);
        try {
            String pageSource = driver.getPageSource(); // Get the screen's XML source
            saveToFile(pageSource, SOURCE_DIR  + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//modify below to get dynamic clientCode
    public void capturePageSourceStockCode(String suffix, String userName,String stockCode,String prefix, String fileExtension) {
        createDirectory(SOURCE_DIR_Stock);
        try {
            String pageSource = driver.getPageSource(); // Get the screen's XML source
            saveToFile(pageSource, SOURCE_DIR_Stock  +suffix+"_" + userName +"_" +stockCode +"_" +prefix +fileExtension);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Save page source to a text file
    private void saveToFile(String content, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
            System.out.println("Saved: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Capture and save with a timestamped filename
    public void capturePageSourceWithTimestamp() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        capturePageSource("PageSource_" + timestamp + ".txt");
    }

    // Capture and save a screenshot
    public void captureScreenshot(String suffix, String userName,String stockCode,String prefix,String fileExtension) {
        createDirectory(SCREENSHOT_DIR);
        try {
            File screenshot = driver.getScreenshotAs(OutputType.FILE);
            File destFile = new File(SCREENSHOT_DIR  +suffix+"_" + userName +"_" +stockCode +"_" +prefix +fileExtension );
            FileUtils.copyFile(screenshot, destFile);
            System.out.println("Screenshot saved: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Capture and save a screenshot with a timestamped filename
    public void captureScreenshotWithTimestamp() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        captureScreenshot("Screenshot_" + timestamp + ".png");
    }
}
