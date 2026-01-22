package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import utils.BasePage;

import static tests.BaseTest.deviceType;

public class BuyUsOrder extends BasePage {

    private By CurrentPriceUS;
    private By limitPriceManual;
    private By orderIcon;
    private By usOrderTab;
    private By selectUsBuyTab;
    private By stockSearchButton;
    private By searchInputField;
    private By cancelButton;
    private By searchBtn;
    private By searchWithTicker;
    private By usQuantityInputField;
    private By selectMarketTab;
    private By tradingPasswordUs;
    private By orderConfirmButton;
    private By fund;
    private By stockNameUs;
    private By orderScreenHeader;
    private By holdingTab;
    private By holdingScreen;
    private By placeOrder;
    private  By okButton;
    private By orderHistoryTabUS;
    public void locators() {
        if(deviceType.equalsIgnoreCase("ios")){
            CurrentPriceUS= MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[6]");
//            limitPriceManual;
//            orderIcon;
           usOrderTab=By.xpath("//XCUIElementTypeButton[@name='米国株式']");
            selectUsBuyTab=By.xpath("//XCUIElementTypeButton[@name='買い注文']");
            stockSearchButton=By.xpath("(//XCUIElementTypeButton[@name='検索'])[2]");
//            searchInputField;
//            cancelButton;
//            searchBtn;
//            searchWithTicker;
            usQuantityInputField=By.xpath("//XCUIElementTypeTextField[@value=' 株']");
            selectMarketTab=By.xpath("//XCUIElementTypeButton[@name='成行']");
            tradingPasswordUs=By.xpath("//XCUIElementTypeSecureTextField[@value='文字列四桁']");
            orderConfirmButton=By.xpath("//XCUIElementTypeButton[@name='注文する']");
            fund=By.xpath("(//XCUIElementTypeStaticText)[8]");
            stockNameUs=By.xpath("(//XCUIElementTypeStaticText)[2]");
            orderScreenHeader=By.xpath("(//XCUIElementTypeStaticText)[1]");
//            holdingTab;
//            holdingScreen;
           // placeOrder;
            okButton = By.xpath("//XCUIElementTypeButton[@name='OK']");

        }else {
            CurrentPriceUS = By.id("jp.co.rakuten_sec.ispeed:id/us_description_price_text");
//quantityUs = By.id("jp.co.rakuten_sec.ispeed:id/number_orders_edit_text");
            limitPriceManual = By.id("jp.co.rakuten_sec.ispeed:id/limit_price_button");
//limitPriceDefault = By.id("jp.co.rakuten_sec.ispeed:id/process_button");
//tradingPasswordUs = By.id("jp.co.rakuten_sec.ispeed:id/trading_password_text");
            orderIcon = By.xpath("//android.widget.TextView[@text='注文']");
            usOrderTab = By.id("jp.co.rakuten_sec.ispeed:id/us_stocks_button");
            selectUsBuyTab = By.id("jp.co.rakuten_sec.ispeed:id/us_cash_order_buy_normal_input_button");
            stockSearchButton = By.xpath("//android.widget.TextView[@text='検索']");
            searchInputField = By.id("jp.co.rakuten_sec.ispeed:id/search_keyword");
            cancelButton = By.id("jp.co.rakuten_sec.ispeed:id/search_cancel_button");
            searchBtn = By.id("jp.co.rakuten_sec.ispeed:id/search_button");
            searchWithTicker = By.xpath("//android.widget.TextView[@text='AA']");
            usQuantityInputField = By.id("jp.co.rakuten_sec.ispeed:id/number_orders_edit_text");
            selectMarketTab = By.id("jp.co.rakuten_sec.ispeed:id/process_button");
            tradingPasswordUs = By.id("jp.co.rakuten_sec.ispeed:id/trading_password_text");
            orderConfirmButton = By.id("jp.co.rakuten_sec.ispeed:id/confirm_button");
            fund = By.id("jp.co.rakuten_sec.ispeed:id/open_to_buy_price");
            stockNameUs = By.id("jp.co.rakuten_sec.ispeed:id/us_desc_info_corporate_name_text");
            orderScreenHeader = By.id("jp.co.rakuten_sec.ispeed:id/search_header_text");
            holdingTab = By.id("jp.co.rakuten_sec.ispeed:id/us_cash_menu_selectable_button_0");
            holdingScreen = By.id("jp.co.rakuten_sec.ispeed:id/assets_tab_left_button");
            placeOrder = By.id("jp.co.rakuten_sec.ispeed:id/execute_button");
            okButton = By.xpath("//android.widget.Button[@text='OK']");
            orderHistoryTabUS= By.xpath("//android.widget.Button[@resource-id='jp.co.rakuten_sec.ispeed:id/us_cash_order_show_correction_button']");

        }
    }
    SoftAssert softAssert=new SoftAssert();

    public BuyUsOrder(AppiumDriver driver) {
        super(driver);
        locators();
    }
    public void clickOnOrderIcon() {
        waitForElementToBeClickable(orderIcon);
        clickElement(orderIcon);
    }
    public void clickOnUsOrderTab() {
        waitForElementToBeClickable(usOrderTab);
        clickElement(usOrderTab);
    }
    public void selectUsBuy() {
        waitForElementToBeClickable(selectUsBuyTab);
        clickElement(selectUsBuyTab);
    }
    public void clickOnSearchButton() {
        waitForElementToBeClickable(stockSearchButton);
        clickElement(stockSearchButton);
    }
    public void verifyOrderReviewScreen(SoftAssert softAssert){
        boolean value=isElementDisplayed(placeOrder);
        softAssert.assertTrue(value);

    }
    public void clickOnInputTextField(String value) {
        waitForElementToBeClickable(searchInputField);
        clickElement(searchInputField);
        sendKeysToElement(searchInputField,value);
    }
    public void clickOnCancelandSearchButton() {
        waitForElementToBeClickable(cancelButton);
        clickElement(cancelButton);
        waitForElementToBeClickable(searchBtn);
        clickElement(searchBtn);
    }
    public void selectStock() {
        waitForElementToBeClickable(searchWithTicker);
        clickElement(searchWithTicker);
    }
    public void clickOnUsQuantityInputField(String value) {
        waitForElementToBeClickable(usQuantityInputField);
        // clickElement(usQuantityInputField);
        sendKeysToElement(usQuantityInputField,value);
    }
    public void selectMarketTab(){
//        waitForElementToBeClickable(selectMarketTab);
//        clickElement(selectMarketTab);
        boolean value= isElementVisible(selectMarketTab);
        if(value){clickElement(selectMarketTab);}
        else{
            scrollDownToBottom();
            waitForElementToBeClickable(selectMarketTab);
            clickElement(selectMarketTab);
        }
    }
    public void clickOnTradingPasswordInputField(String value){
        waitForElementToBeClickable(tradingPasswordUs);
        //clickElement(tradingPasswordUs);
        sendKeysToElement(tradingPasswordUs,value);
        waitForElementToBeVisible(okButton);
             clickElement(okButton);


    }
    public void clickOnOrderConfirmBtn(){
        waitForElementToBeClickable(orderConfirmButton);
        clickElement(orderConfirmButton);
    }
    public boolean checkAmountUS(){
        boolean amount;
        waitForElementToBeVisible(fund);
        String currentFund=getTextFromElement(fund);
        if(currentFund.equals("0")){
            amount=false;
        }else{amount=true;}
        return amount;

    }
    public String getCurrentPriceUs(){
       return getTextFromElement(CurrentPriceUS);

    }
    public String getStockNameUs(){
        return getTextFromElement(stockNameUs);

    }


    public void verifyOrderPage() {
        waitForElementToBeVisible(orderScreenHeader);
        String header= getTextFromElement(orderScreenHeader);
        softAssert.assertEquals(header,"注文","orderPage not found");

    }

    public void verifyHoldingScreen() {
        waitForElementToBeVisible(holdingScreen);
        String header= getTextFromElement(holdingScreen);
        softAssert.assertEquals(header,"保有銘柄","Holding screen not found");


    }

    public void goToHoldingScreen() {
        waitForElementToBeClickable(holdingTab);
        clickElement(holdingTab);

    }

    public String getCurrentQuantity(String stockName) {
       WebElement element= driver.findElement(By.xpath("@text='"+stockName+""));
        WebElement quantity=element.findElement(By.xpath("/../..//android.widget.TextView[@resource-id='jp.co.rakuten_sec.ispeed:id/assets_holding_description_execution_text']"));
       return quantity.getText();

    }

    public void verifyQuantity(String currentQuantity,String sellquantity,String buyUsName) {
        String expectedQuantity= String.valueOf(Integer.parseInt(currentQuantity)+Integer.parseInt(sellquantity));
        String updatedquantity=getCurrentQuantity(buyUsName);
        softAssert.assertEquals(updatedquantity,expectedQuantity);

    }
    public void verifyOrderSummaryScreen(SoftAssert softAssert){
        boolean value=isElementDisplayed(placeOrder);
        softAssert.assertTrue(value);

    }
public void orderHistoryTabUS(){
        waitForSeconds(2);
        waitForElementToBeClickable(orderHistoryTabUS);
        clickElement(orderHistoryTabUS);
}

}
