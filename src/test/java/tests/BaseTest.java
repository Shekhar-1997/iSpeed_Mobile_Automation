package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Allure;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeMethod;
import pages.*;
import utils.*;
import java.io.*;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static tests.PageOjectManager.*;

public class BaseTest{
    public static AppiumDriver driver;
    public static String filePath;
    public static FileInputStream fileInputStream;
    public static String filePathForDeviceRegisteration;
    public static File file;
    public static TestDataObject testData;
    private Thread keepAliveThread;
    public static Workbook workbook;
    public static Workbook deviceWorkbook;
    private volatile boolean keepAlive;
    public static String deviceType;
    public static final String ANDROID_PACKAGE_NAME = "jp.co.rakuten_sec.ispeed"; // Replace with your app's package name
    private static final String ANDROID_MAIN_ACTIVITY = "jp.co.rakuten_sec.ispeed.SplashActivity"; // Replace with your app's main activity
    private static final String iosBundleID="jp.co.rakuten-sec.iSPEED.Pushnotification";
    public String APP_PATH;

    @BeforeSuite
    public void setUp() throws IOException {
        getSysdate();
        mobileOS=new DetectMobileOS();
        startTime = LocalDateTime.now();
        System.out.println("Test suite started at: " + formatDateTime(startTime,"yyyy-MM-dd HH:mm:ss"));
        Object[][] data = TestDataProvider.jsonData();
        if (data.length > 0) {
            testData = (TestDataObject) data[0][0];
            System.out.println("Test data initialized from json file");
        }
        APP_PATH = testData.getAppPath();
        String APPIUM_SERVER_URL = System.getProperty("appium.url", testData.getAppiumurl());
        String driverType = System.getProperty("driverType", "ios");
        String[] deviceInfo=mobileOS.deviceType();
        deviceType=deviceInfo[0];
        String deviceUDID=System.getProperty("device.udid", deviceInfo[1]);
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (deviceType.equalsIgnoreCase("android")) {

            capabilities.setCapability("udid", deviceUDID);
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("automationName", "UiAutomator2");
            capabilities.setCapability("appium:autoGrantPermissions", true);
            capabilities.setCapability("appium:permissions", "notification");
            capabilities.setCapability("newCommandTimeout", "600");
            driver = new AndroidDriver<>(new URL(APPIUM_SERVER_URL), capabilities);
//            // Check if the app is installed
            if (!driver.isAppInstalled(ANDROID_PACKAGE_NAME)) {

                // Install the app
                driver.installApp(APP_PATH);
                System.out.println("App installed successfully.");
            }

            // Launch the app using startActivity
            Activity activity = new Activity(ANDROID_PACKAGE_NAME, ANDROID_MAIN_ACTIVITY);
            activity.setAppWaitPackage(ANDROID_PACKAGE_NAME);
            activity.setAppWaitActivity(ANDROID_MAIN_ACTIVITY);
            ((AndroidDriver) driver).startActivity(activity);

        }

        else if (deviceType.equalsIgnoreCase("ios")) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("appium:deviceName", "iPhone 11");
            capabilities.setCapability("appium:platformVersion", "17.6.1");
            capabilities.setCapability("appium:udid", "00008030-000641EE0E68802E");
            capabilities.setCapability("appium:automationName", "XCUITest");

            capabilities.setCapability("appium:bundleId", "jp.co.rakuten-sec.iSPEED.Pushnotification");
            capabilities.setCapability("appium:wdaLaunchTimeout", "60000");
            capabilities.setCapability("appium:wdaLocalPort", "8100");
            capabilities.setCapability("appium:wdaStartupRetries", "3");
            capabilities.setCapability("appium:wdaConnectionTimeout", "60000");
            // Add other necessary iOS capabilities here
            driver = new IOSDriver<>(new URL(APPIUM_SERVER_URL), capabilities);
            // Check if the app is installed (adjust the bundleId as necessary)
            // String iosBundleId = "com.example.yourapp"; // Replace with your app's bundle ID
//            if (!driver.isAppInstalled(iosBundleId)) {
//                // Install the app
//                driver.installApp("path/to/your/app.ipa"); // Path to your app's IPA file
//            }
//            // Launch the app
//            driver.launchApp();
        } else {
            throw new IllegalArgumentException("Invalid driver type: " + driverType);
        }
        startKeepAliveThread();
        PageOjectManager.initpages();
        createWorkbook();

    }
//    @BeforeMethod
//    public void navigate(){
//        MenuPage menuPage = new MenuPage(driver);
//        menuPage.goToNotificationSetting();
//        //alertSettingPage.goToAlertSettingPage();
//    }
//            capabilities.setCapability("platformName", "iOS");
//            capabilities.setCapability("appium:udid", deviceUDID);
//            capabilities.setCapability("appium:automationName", "XCUITest");
//            capabilities.setCapability("appium:bundleId", iosBundleID);
//            capabilities.setCapability("appium:wdaLaunchTimeout", "60000");
//            capabilities.setCapability("appium:wdaLocalPort", "8100");
//            capabilities.setCapability("appium:wdaStartupRetries", "3");
//            capabilities.setCapability("appium:wdaConnectionTimeout", "60000");
//
//            driver = new IOSDriver<>(new URL(APPIUM_SERVER_URL), capabilities);



    @AfterClass
    public void afterClass(ITestContext context) {
        // Capture screenshot for failed tests

        MenuPage menuPage = new MenuPage(driver);
        menuPage.goToMenuPage();

    }

    @AfterSuite
    public void tearDown(ITestContext context) throws IOException {
        stopKeepAliveThread();
        closeWokbook();
        if (driver != null) {
            driver.quit();
        }
        PageOjectManager.endTime = LocalDateTime.now();
        System.out.println("Test suite ended at: " + formatDateTime(PageOjectManager.endTime,"yyyy-MM-dd HH:mm:ss"));

        // Calculate the duration between start and end time
        Duration duration = Duration.between(PageOjectManager.startTime, PageOjectManager.endTime);
        System.out.println("Total time taken by the test suite: " + formatDuration(duration));


    }

    public AppiumDriver setUp1() throws IOException {
        PageOjectManager.mobileOS=new DetectMobileOS();
        String[] deviceInfo=PageOjectManager.mobileOS.deviceType();
        deviceType=deviceInfo[0];
        if(deviceType.equalsIgnoreCase("null")){
            deviceType="ios";
        }
        String deviceUDID=deviceInfo[1];
        Object[][] data = TestDataProvider.jsonData();
        if (data.length > 0) {
            testData = (TestDataObject) data[0][0];
            System.out.println("Test data initialized from json file");
        }
        if(filePath!=null){
            filePathForDeviceRegisteration=filePath;
            System.out.println(filePathForDeviceRegisteration);
            deviceWorkbook=workbook;}
        else{
            LocalDateTime currentTime = LocalDateTime.now();
            String formatDate= formatDateTime(currentTime,"yyyyMMddHHmmss");
            filePathForDeviceRegisteration = "DB_data/Alert_Settings_Dbevidence_"+formatDate+".xlsx";
            file = new File(filePathForDeviceRegisteration);
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                deviceWorkbook = new XSSFWorkbook(fileInputStream);

                fileInputStream.close();    }
            else {
                deviceWorkbook = new XSSFWorkbook();

            }
        }
        APP_PATH = testData.getAppPath();
        APP_PATH=System.getProperty("user.dir")+ APP_PATH;
        String APPIUM_SERVER_URL = System.getProperty("appium.url", testData.getAppiumurl());
        String driverType = System.getProperty("driverType", "android");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // Set platformName and automationName based on the driver type
        if (deviceType.equalsIgnoreCase("android")) {
            capabilities.setCapability("udid", System.getProperty("device.udid", deviceUDID));
            capabilities.setCapability("deviceName", System.getProperty("device.name", deviceUDID));
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("app", APP_PATH);
            capabilities.setCapability("automationName", "UiAutomator2");
        } else if (deviceType.equalsIgnoreCase("ios")) {
            // Add IOS capabilities
            capabilities.setCapability("udid", System.getProperty("device.udid", deviceUDID));
            capabilities.setCapability("deviceName", System.getProperty("device.name", deviceUDID));
            capabilities.setCapability("platformName", "ios");
            capabilities.setCapability("app", APP_PATH);
            capabilities.setCapability("automationName", "XCUITest");
        } else {
            throw new IllegalArgumentException("Invalid driver type: " + driverType);
        }

        // Add capability to allow notification access
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("appium:permissions", "notification");

        URL appiumUrl = new URL(APPIUM_SERVER_URL);
        if (deviceType.equalsIgnoreCase("android")) {
            driver = new AndroidDriver<>(appiumUrl, capabilities);
        } else if (deviceType.equalsIgnoreCase("ios")) {
            driver = new IOSDriver<>(appiumUrl, capabilities);
        }
        return driver;
    }

    private void startKeepAliveThread() {
        keepAlive = true;
        keepAliveThread = new Thread(() -> {
            while (keepAlive) {
                try {
                    // Perform a no-op action to keep the session alive
                    if (driver != null) {
                        driver.getPageSource(); // Example no-op action
                    }
                    // Wait for 1 minute before the next keep-alive action
                    Thread.sleep(60 * 1000); // 1 minute in milliseconds
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        keepAliveThread.setDaemon(true); // Ensure the thread does not block JVM shutdown
        keepAliveThread.start();
    }

    private void stopKeepAliveThread() {
        keepAlive = false;
        if (keepAliveThread != null) {
            try {
                keepAliveThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private String formatDateTime(LocalDateTime dateTime, String dateTimeFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormat);
        return dateTime.format(formatter);
    }

    private String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return  String.valueOf(secs);//String.format("%02d hours, %02d minutes, %02d seconds", hours, minutes, secs);
    }

    public String getSysdate(){
        LocalDateTime startDate = LocalDateTime.now();

        // Define a formatter to format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the current date and time
        String formattedDateTime = startDate.format(formatter);

        // Print the formatted date and time
        System.out.println("Current Date and Time: " + formattedDateTime);
        return formattedDateTime;
    }
    public void dbEvidenceCapture(String methodName,String alertType,int limit, Boolean valiDate) {
        System.out.println("Entered inside TestDataAPI");
        PushDBAPI.captureDBEvidence(methodName,alertType,limit,valiDate);
        System.out.println("Entered to executeQueries");
    }

    public void coreDBEvidenceCapture(String type,String stockCode,String methodName) throws IOException {
        System.out.println("Entered inside TestDataAPI");
        CoreDBAPI.orderExecution(type,stockCode,methodName);
        System.out.println("Entered to executeQueries");
    }

    public void multipleAccountsSingelDeviceDB(String methodName,String alertType,int limit) {
        System.out.println("Entered inside TestDataAPI");
        DBEvidenceMultipleAccounts.captureDBEvidenceMultipleAccounts(methodName,alertType,limit);
        System.out.println("Entered to executeQueries");
        System.out.println("After evidencce of JP alerts");
    }

    public void inActiveUserDbData(String methodName,String alertType) {
        System.out.println("Entered inside TestDataAPI");
        PushDBAPICreateUpdate.captureDBEvidence(methodName,alertType);
        System.out.println("Entered to executeQueries");
        System.out.println("After evidencce of JP alerts");
    }

    public void deleteInStatusList(String methodName,String alertType,Boolean valiDate) throws IOException {
        System.out.println("Entered inside TestDataAPI");
        DeleteAllStatusListDB.captureDBEvidenceDeleteAll(methodName,alertType,valiDate);
        System.out.println("Entered to executeQueries");
        System.out.println("After evidencce of JP alerts");
    }

    public void deleteOneAlertSettigsDB(String methodName,String alertType) throws IOException {
        System.out.println("Entered inside TestDataAPI");
//        DeleteAlertsDBData.deleteOneAlertInSettingsDBEvidence(methodName,alertType);
        System.out.println("Entered to executeQueries");
        System.out.println("After evidencce of JP alerts");
    }




    public void createWorkbook() throws IOException {
        LocalDateTime currentTime = LocalDateTime.now();
        String formatDate= formatDateTime(currentTime,"yyyyMMddHHmmss");
        filePath = "DB_data/Alert_Settings_Dbevidence_"+formatDate+".xlsx";
        file = new File(filePath);
        if (file.exists()) {
            fileInputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(fileInputStream);
            fileInputStream.close();    }
        else {
            workbook = new XSSFWorkbook();
        }
    }

    public void closeWokbook() throws IOException {

        workbook.close();
    }
    public void captureScreenshot(String filename) {
        Dimension screenSize = driver.manage().window().getSize();
        int originalWidth = screenSize.getWidth();
        int originalHeight = screenSize.getHeight();

        // Calculate the target width and height using the scaling factor
        float scalingFactor = 0.4f;
        int targetWidth = 310;
        int targetHeight = 720;
        byte[] screenshotBytes = CaptureScreenShotUtil.captureAndResizeScreenshot(targetWidth, targetHeight, scalingFactor);

        // Attach the resized screenshot to Allure
        Allure.addAttachment(filename, new ByteArrayInputStream(screenshotBytes));

    }
    public   void saveScreenshotToFile(byte[] screenshotBytes, String filename) {
        try {
            Allure.addAttachment(filename, new ByteArrayInputStream(screenshotBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public byte[] captureScreenshotAsByte(String filename) {

        byte[] screenshotBytes = CaptureScreenShotUtil.captureAndResizeScreenshot(310, 720, 0.4f);
        return screenshotBytes;
    }
    public void afterMethodSteps(CustomSoftAssert softAssert,ITestResult result){
        for (byte[] screenshot : softAssert.getFailedScreenshots()) {
            saveScreenshotToFile(screenshot, result.getName()+"Failed Screenshot");
        }
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(result.getName() + "_failure.png");
        }
        menuPage.goToMenuPage();
    }

}