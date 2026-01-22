package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestResult;
import tests.BaseTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScreenshotUtil extends BaseTest {
   // private AppiumDriver driver;
    private static final String SCREENSHOT_DIR = "screenshots/"; // Folder to save screenshots

//    public ScreenshotUtil(AppiumDriver driver) {
//        this.driver = driver;
//        createScreenshotDirectory();
//    }

    // Ensure the screenshots directory exists
//    private void createScreenshotDirectory() {
//        File dir = new File(SCREENSHOT_DIR);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//    }

    // Capture and stitch full-page screenshot
    public void captureFullPageScreenshot(String filename) {
        try {
            List<BufferedImage> screenshots = new ArrayList<>();
            int screenHeight = driver.manage().window().getSize().getHeight(); // Viewport height

            // Keep scrolling and taking screenshots until the bottom is reached
            while (true) {
                BufferedImage screenshot = captureScreenshotAsImage();
                screenshots.add(screenshot);

                if (!scrollDown()) break; // Stop if no more scrolling possible
            }

            BufferedImage fullImage = stitchImages(screenshots);
            saveImageToFile(fullImage, SCREENSHOT_DIR + filename);
            System.out.println("Full-page screenshot saved: " + SCREENSHOT_DIR + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Capture a single screenshot and return as BufferedImage
    private BufferedImage captureScreenshotAsImage() throws IOException {
        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return ImageIO.read(new java.io.ByteArrayInputStream(screenshotBytes));
    }

    // Scroll down using Android UiScrollable
    private boolean scrollDown() {
        try {
            String scrollCommand = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward();";
            ((JavascriptExecutor) driver).executeScript("mobile: shell",
                    java.util.Map.of("command", "input touchscreen swipe 500 1500 500 500"));
            Thread.sleep(1000); // Wait for scroll animation
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Stitch multiple images together vertically
    private BufferedImage stitchImages(List<BufferedImage> images) {
        int totalHeight = images.stream().mapToInt(BufferedImage::getHeight).sum();
        int width = images.get(0).getWidth();

        BufferedImage stitchedImage = new BufferedImage(width, totalHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = stitchedImage.createGraphics();

        int currentY = 0;
        for (BufferedImage img : images) {
            graphics.drawImage(img, 0, currentY, null);
            currentY += img.getHeight();
        }

        graphics.dispose();
        return stitchedImage;
    }

    // Save final stitched image to file
    private void saveImageToFile(BufferedImage image, String filePath) {
        try {
            ImageIO.write(image, "png", new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Capture screenshot after test failure
    public void afterMethodSteps(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            captureFullPageScreenshot(result.getName() + "_failure.png");
        }
    }
}
