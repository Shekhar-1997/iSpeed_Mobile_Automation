package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import tests.BaseTest;
import utils.BasePage;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static tests.BaseTest.deviceType;

public class DeleteAlertPage extends BasePage {
    private By deleteButtonStatusList;
    private By deleteAllButtonStatusList;
    private By stockCodeInAlertList;
    private By deleteCheckBoxInStatusList;
    private By deleteOptionInPoPUp;
    private By deleteButtonAlertSetting;
    private By stockCodeInAlertSetting;
    private By deleteAllSetting;
    private By deleteAllStatus;
    private By deletecancelButton;
    private By deleteButtonMinus;
    private By closeButton;
    private By alertStatusListbtn;
    private By selectAllFromStatusList;
    private By saveButton;
    private By timeForStock;
    private By firstAlertStock;
    private By stockName;
    private By statusList;
    private By deleteAllAcceptPopup;
    private By firstStockAfterDelete;
    private By alertCount;
    private By stockNameInStatusList;
private void locators() {

    if(deviceType.equalsIgnoreCase("ios")){
        deleteButtonStatusList =By.xpath("//XCUIElementTypeStaticText[@name='選択削除']");
//        deleteAllButtonStatusList =;
//        stockCodeInAlertList =;
        deleteCheckBoxInStatusList =By.xpath("(//XCUIElementTypeImage[@name='minus.circle.fill'])[1]");
        deleteAllAcceptPopup=By.xpath("//XCUIElementTypeButton[@name='はい']");
        deleteOptionInPoPUp =By.xpath("//XCUIElementTypeButton[@name='削除']");
        deleteButtonAlertSetting =By.xpath("(//XCUIElementTypeImage[@name='minus.circle.fill'])[1]");
//        stockCodeInAlertSetting =;
        deleteAllSetting =By.xpath("//XCUIElementTypeStaticText[@name='全削除']");
        deleteAllStatus =By.xpath("//XCUIElementTypeStaticText[@name='全削除']");
//        deletecancelButton =;
//        deleteButtonMinus =;
//        closeButton =;
//        alertStatusListbtn =;
        selectAllFromStatusList =By.xpath("//XCUIElementTypeButton[@name='全て']");
        saveButton =By.xpath("//XCUIElementTypeButton[@name='完了']");
        timeForStock =By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]//XCUIElementTypeStaticText[2]");
        firstAlertStock=By.xpath("(//XCUIElementTypeOther/XCUIElementTypeTable[2]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText)[1]");
        firstStockAfterDelete=By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]");

//        stockName =;
       statusList =By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]//XCUIElementTypeStaticText[5]");
        alertCount=By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[1]");
        stockNameInStatusList=By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[2]");
    }else{
    deleteButtonStatusList = By.id("jp.co.rakuten_sec.ispeed:id/delete_select_button");
    deleteAllButtonStatusList = By.id("jp.co.rakuten_sec.ispeed:id/delete_all_button");
    stockCodeInAlertList = By.xpath("(//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/stock_code'])[1]");
    deleteCheckBoxInStatusList = By.xpath("(//android.widget.ImageView[@resource-id='jp.co.rakuten_sec.ispeed:id/delete_check_box'])[1]");
    deleteOptionInPoPUp = By.id("android:id/button1");
    deleteButtonAlertSetting = By.xpath("(//android.widget.ImageView[@resource-id='jp.co.rakuten_sec.ispeed:id/condition_delete_image'])[1]");
    stockCodeInAlertSetting = By.xpath("(//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/stock_code'])[1]");
    deleteAllSetting = By.id("jp.co.rakuten_sec.ispeed:id/delete_all_button");
    deleteAllStatus = By.id("jp.co.rakuten_sec.ispeed:id/delete_all_button");
    deletecancelButton = By.id("android:id/button2");
    deleteButtonMinus = By.xpath("(//android.widget.ImageView[@resource-id='jp.co.rakuten_sec.ispeed:id/condition_delete_image'])[1]");
    closeButton = By.id("jp.co.rakuten_sec.ispeed:id/close_button");
    alertStatusListbtn = By.id("jp.co.rakuten_sec.ispeed:id/menu_notification_history");
    selectAllFromStatusList = By.xpath("(//android.widget.LinearLayout[@resource-id='jp.co.rakuten_sec.ispeed:id/action_item'])[1]");
    saveButton = By.id("jp.co.rakuten_sec.ispeed:id/complete_button");
    timeForStock = By.xpath("//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/hms']");
    firstAlertStock = By.xpath("(//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/stock_name'])[1]");
    stockName = By.id("jp.co.rakuten_sec.ispeed:id/stock_name");
    statusList = By.xpath("(//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/stock_name'])[1]/../..//android.widget.LinearLayout[3]");
    alertCount=By.xpath("//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/stock_name']");
    stockNameInStatusList=By.xpath("//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/stock_name']");
    }}

    public DeleteAlertPage(AppiumDriver driver) {
        super(driver);
        locators();
    }

      public String waitForFirstStockToBeVisible(SoftAssert softAssert){
        waitForElementToBeVisible(firstAlertStock);
        String stockName=getTextFromElement(firstAlertStock);
        return stockName;
    }
    public void clickOnSelectToDelete(SoftAssert softAssert){
        waitForElementToBeClickable(deleteButtonAlertSetting);
        clickElement(deleteButtonAlertSetting);

    }
    public void acceptDeletePopup(SoftAssert softAssert){
        waitForElementToBeClickable(deleteOptionInPoPUp);
        clickElement(deleteOptionInPoPUp);
    }
    public void acceptDeletePopupIos(){
        waitForElementToBeClickable(deleteAllAcceptPopup);
        clickElement(deleteAllAcceptPopup);
    }
    public void stockNameAfterDelete(String stockNameBeforeDelete,SoftAssert softAssert){
        String stockNameAfterDelete;
if(deviceType.equalsIgnoreCase("ios")){
            waitForElementToBeVisible(firstStockAfterDelete);
             stockNameAfterDelete=getTextFromElement(firstStockAfterDelete);
        }else{
        waitForElementToBeVisible(firstAlertStock);
         stockNameAfterDelete=getTextFromElement(firstAlertStock);}
       // waitForElementToBeVisible(statusList);
        Assert.assertNotEquals(stockNameAfterDelete, stockNameBeforeDelete);
    }
    public void clickOnselectToDelete(){
        waitForElementToBeClickable(deleteButtonStatusList);
        clickElement(deleteButtonStatusList);
    }
    public void clickOndeleteMinus(){
        waitForElementToBeClickable(deleteCheckBoxInStatusList);
        clickElement(deleteCheckBoxInStatusList);
    }
    public void acceptPopup(){
        waitForElementToBeClickable(deleteOptionInPoPUp);
        clickElement(deleteOptionInPoPUp);
    }
    public String timeStampBeforeDeleteSingleInStatus(){
        waitForSeconds(2);
        waitForElementToBeVisible(timeForStock);
        String timeBeforeDlt= getTextFromElement(timeForStock);
        return timeBeforeDlt;
    }
    public String pushMessageBeforeDelete(){
        waitForSeconds(2);
        waitForElementToBeVisible(statusList);
        return getTextFromElement(statusList);
    }
    public String pushMessageAfterDelete(){
        waitForSeconds(2);
        waitForElementToBeVisible(statusList);
        String pushMessageBeforeDelete= getTextFromElement(statusList);
        return pushMessageBeforeDelete;
    }
    public void timeStampAfterDeleteSingleInStatus(SoftAssert softAssert,String timeBeforeDelete,String pushMessage,String stockNameBeforeDelete){
        waitForSeconds(5);
        String timeAftereDlt;
        if(isElementVisible(timeForStock)) {

                timeAftereDlt = driver.findElement(timeForStock).getText();

                String pushMessageAfterDelete = getTextFromElement(statusList);
                if (timeBeforeDelete.equals(timeAftereDlt)) {
                 String  stockNameAfterDelete= getStockNameBeforeDelete();
                 if(stockNameAfterDelete.equals(stockNameBeforeDelete)) {
                     softAssert.assertNotEquals(pushMessageAfterDelete, pushMessage);
                 }else{
                     softAssert.assertNotEquals(stockNameBeforeDelete,stockNameAfterDelete);
                 }
                } else {
                    softAssert.assertNotEquals(timeAftereDlt, timeBeforeDelete);
                }
            }

    }

    public void verifyDeleteAllAlert(SoftAssert softAssert) {
    waitForElementToBeClickable(closeButton);
        clickElement(closeButton);
        softAssert.assertFalse(isElementVisible(firstAlertStock));


    }
    public  void clickSaveButton(){
        waitForElementToBeClickable(saveButton);
        clickElement(saveButton);
    }
public void clickOnDeleteIcon(){
        waitForElementToBeClickable(deleteAllSetting);
        clickElement(deleteAllSetting);
    }

public void clickOnDeleteIconStatus(){
    waitForElementToBeClickable(deleteAllStatus);
    clickElement(deleteAllStatus);
}
    public void acceptPopupAllDelete(){
        waitForElementToBeClickable(selectAllFromStatusList);
        clickElement(selectAllFromStatusList);
    }
    public  void timeStampAfterDeleteAll(SoftAssert softAssert){

        softAssert.assertFalse(isElementVisible(timeForStock),"no stocks should display after delete all.");

    }
    public boolean waitFordeleteElementToBeVisible(SoftAssert softAssert){
        return(isElementVisible(saveButton));
    }

    public void selectAllOption() {
        waitForElementToBeClickable(selectAllFromStatusList);
        clickElement(selectAllFromStatusList);
    }
    public int countOfAlertsPresent(){
       List<WebElement> elements=driver.findElements(alertCount);
        return elements.size();

    }

    public String getStockNameBeforeDelete() {
    waitForElementToBeVisible(stockNameInStatusList);
        return getTextFromElement(stockNameInStatusList);
    }
}
