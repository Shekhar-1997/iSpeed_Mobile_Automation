package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utils.DemoAPI;
import utils.WaitUtilForBrowser;
import utils.DemoAPI;
import utils.WaitUtilForBrowser;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static tests.BaseTest.*;

public class DeviceRegistrationUtil  {
    public static WebDriver driver;
    private static By accountNumber = By.xpath("//input[@id='account' and @name='account' and @type='text' and @ng-model='vm.account']");
    private static By loginId = By.xpath("//input[@name='username']");
    private static By password = By.xpath("//input[@name='password']");
    private static By signInButton = By.xpath("//button[@id='signin_button']");
    //  private static By systemNotification = By.xpath("//div[@class='linkWrapper-0-1-36']/a");
    private static By systemNotification = By.xpath("//a[@data-analytics='serviceLink_sns']");

    private static By menuIconElement = By.xpath("//button[@aria-label='Open side navigation' and @type='button']");
    private static By pushNotificationsLink = By.xpath("//*[contains(text(), 'Push notifications')]");//STG_iSpeed-for-Android
    private static By androidLink = By.xpath("//*[contains(text(), 'STG_iSpeed-for-Android')]");
    private static By iphoneLink=By.xpath("//*[contains(text(), 'STG_iSpeed-for-iPhone')]");
    private static By searchInput = By.xpath("//input[@aria-label='Search' and @placeholder='Search']");
    private static By account = By.id("//input[@id='account']");
    private static By logoutLink = By.xpath("//span[@data-testid='awsc-nav-account-menu-button']");
    private static By signout = By.xpath("//a[@data-testid='aws-console-signout']");
    private static By endPointArnScreenLink = By.xpath("//div[contains(@class,'awsui_body-cell-content_c6tup_epzwe_112')]//a");
    private static By enPointArnScreen = By.xpath("//div[contains(text(),'Status')]/div");
    private DemoAPI demoAPI = new DemoAPI();

    public void enterEndpointArn(String account, String userName, String password, String endPointArn) {
        openbrowser();
        loginAWS(account, userName, password);
        traverseToscreen(endPointArn);
        logout();
        // closebrowser();
    }

    public static void openbrowser() {
        System.setProperty("webdriver.chrome.driver", "./resources/driver1/chromedriver.exe");

        //WebDriverManager.firefoxdriver().setup();
        driver = new ChromeDriver();
        driver.get("https://721709919548.signin.aws.amazon.com/console");
        captureScreenshot1("googlePage");
        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().maximize();


    }

    public static void captureScreenshot1(String filename) {
        try {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            saveScreenshotToFile1(screenshotBytes, filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveScreenshotToFile1(byte[] screenshotBytes, String filename) {
        try {
            Allure.addAttachment(filename, new ByteArrayInputStream(screenshotBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void traverseToscreen(String endpointARN) {
        $(systemNotification).shouldBe(Condition.visible).click();
        captureScreenshot1("alferLogin.png");
        // driver.findElement(systemNotification).click();
        $(menuIconElement).shouldBe(Condition.visible).click();
        captureScreenshot1("menuIconElement.png");
        WaitUtilForBrowser.setImplicitWait(driver, 15);
        waitForElementToBeVisible(pushNotificationsLink, 4);
        driver.findElement(pushNotificationsLink).click();
        WaitUtilForBrowser.setImplicitWait(driver, 15);
        captureScreenshot1("androidLink.png");
        WaitUtilForBrowser.setImplicitWait(driver, 180);
        scrollToBottom();
        WaitUtilForBrowser.setImplicitWait(driver, 180);
        scrollToBottom();
        WaitUtilForBrowser.setImplicitWait(driver, 180);
        if(deviceType.equalsIgnoreCase("ios")){
            waitForElementToBeVisible(iphoneLink, 60);
            scrollToBottom();
            waitForElementToBeVisible(iphoneLink, 60);
            driver.findElement(iphoneLink).click();
            WaitUtilForBrowser.setImplicitWait(driver, 15);
        }else {
            waitForElementToBeVisible(androidLink, 60);
            scrollToBottom();
            waitForElementToBeVisible(androidLink, 60);
            driver.findElement(androidLink).click();
            WaitUtilForBrowser.setImplicitWait(driver, 15);
        }
        scrollToBottom();
        scrollToBottom();
        waitForElementToBeVisible(searchInput, 10);
        captureScreenshot1("androidLinkafterscroll.png");
        driver.findElement(searchInput).sendKeys(endpointARN);
        WaitUtilForBrowser.setImplicitWait(driver, 15);
        captureScreenshot1("searchInput.png");
        goToEndpointArnScreen(endpointARN);
        scrollToBottom();
        WaitUtilForBrowser.setImplicitWait(driver, 120);
        try {
            waitForElementToBeVisible(enPointArnScreen, 5);
        } catch (Exception e) {
        }
        captureScreenshot1("endPointArnScreen.png");

    }

    private static void loginAWS(String account, String username, String passWord) {
        waitForElementToBeVisible(loginId, 10);
        driver.findElement(loginId).sendKeys(username);
        driver.findElement(password).sendKeys(passWord);
        scrollToBottom();
        waitForElementToBeVisible(signInButton, 2);
        driver.findElement(signInButton).click();
        WaitUtilForBrowser.setImplicitWait(driver, 15);

    }

    private static void closebrowser() {
        driver.quit();
    }

    public static void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

// Scroll to the bottom of the page
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }


    public static WebElement waitForElementToBeVisible(By locator, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void goToEndpointArnScreen(String endpointARN) {
        scrollToBottom();
        //   WaitUtilForBrowser.setImplicitWait(driver,180);
        By endpointARNelement=By.xpath("//*[contains(text(),'" +endpointARN+ "')]");
        waitForElementToBeVisible(endpointARNelement, 120);
        driver.findElement(endpointARNelement).click();
    }

    public static void logout() {
        waitForElementToBeVisible(logoutLink, 5);
        driver.findElement(logoutLink).click();
        waitForElementToBeVisible(signout, 20);
        driver.findElement(signout).click();

    }

    public void queryToFetchCountOfsDevice(String sheetName, String alertType) throws IOException {
        String requestBody = "{\n" +
                "  \"query\": \"select count(*) as total_count from push_device_info\"\n" +
                "}";
        System.out.println(filePathForDeviceRegisteration);
        System.out.println("Request Body: " + requestBody);

        try {
            // Get the response from the API
            Response response = demoAPI.getValueFromPushDB(requestBody);
            String responseBody = response.getBody().asString();
            System.out.println("Response Body: " + responseBody);

            // Parse the response body using JsonPath
            JsonPath jsonPath = new JsonPath(responseBody);

            // Assuming the structure of the response contains the result in a field called "data"
            List<Map<String, Object>> dataList = jsonPath.getList("data");

            if (dataList == null || dataList.isEmpty()) {
                System.out.println("Data list is null or empty. Response body:");
                System.out.println(responseBody);
                return;
            }

            // Extract the first record from the "data" list
            Map<String, Object> firstRecord = dataList.get(0);

            // Assuming "total_count" is the key that holds the count value
            String countStr = firstRecord.get("total_count").toString();
            System.out.println("Device Count (as string): " + countStr);

            // Convert the count string to an integer
            int count;
            try {
                count = Integer.parseInt(countStr);
                System.out.println("Device Count (as integer): " + count);
            } catch (NumberFormatException e) {
                System.out.println("Error converting count(*) to an integer: " + e.getMessage());
                return;
            }

            // Write the count to an Excel file
            writeCountToExcel(count, sheetName, alertType);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeCountToExcel(int count, String sheetName, String alertType) throws IOException {
        try {
            // Check if the workbook already has the sheet, otherwise create one
            Sheet sheet = deviceWorkbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = deviceWorkbook.createSheet(sheetName);
            }

            // Get the next available row number
            int rowNum = sheet.getLastRowNum() + 1;

            // Create a new row for alert type
            Row sheetNameRow = sheet.createRow(rowNum++);
            Cell sheetNameCell = sheetNameRow.createCell(0);
            sheetNameCell.setCellValue(alertType);

            // Create a new row for condition and count
            Row dataRow = sheet.createRow(rowNum++);
            Cell countCell = dataRow.createCell(0);
            countCell.setCellValue(count);

            // Write changes to the Excel file
            try (FileOutputStream fileOut = new FileOutputStream(filePathForDeviceRegisteration)) {
                deviceWorkbook.write(fileOut);
                System.out.println("Data written to Excel file: " + filePathForDeviceRegisteration);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the Excel file: " + e.getMessage());
            e.printStackTrace();
        }

    }
    public  String queryToFetchEndPointArn() {
        String requestBody = "{\n" +
                "  \"query\": \"select endpoint_arn from push_device_info A order by A.last_login_dt desc limit 1\"\n" +
                "}";

        Response response = demoAPI.getValueFromPushDB(requestBody);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        String endPointArn = jsonPath.getString("data[0].endpoint_arn");//shekhar do changes here.
        System.out.println("endPointArn: " + endPointArn);

        if (endPointArn != null) {
            System.out.println("endPointArn is fetched");
        } else {
            System.out.println("No acc_device_ctrl_no found in query1 response.");
        }
        return endPointArn;
    }
}

//    private void writeResponseToFile(int count) {
//        String fileName = "deviceRegistration.txt";
//        try (FileWriter fileWriter = new FileWriter(fileName)) {
//            fileWriter.write("Device Count Bofore: " + count + "\n");
//            fileWriter.write("Device Count After: " + count + "\n");
//            System.out.println("Response written to file: " + fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}



