package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import utils.BasePage;

import static tests.BaseTest.deviceType;

public class SellUSOrder extends BasePage {
    private By sellOrderButtonUs;
    private By quantityTextFiledUs;
    private By PlusbuttonSellUs;
    private By priceDownButtonUs;
    private By processButtonUs;
    private By trading_passwordUs;
    private By confirmSellButtonUs;
    private By currentStockPriceUs;
    private By stockNameUs;
    private By selectHoldedStock;
    private By hodlingScreenHeader;
    private By usSellPageTitle;
    private By okButton;

    public void locators() {

        if (deviceType.equalsIgnoreCase("ios")) {
            sellOrderButtonUs = By.xpath("//XCUIElementTypeButton[@name='売り注文']");
            quantityTextFiledUs = By.xpath("(//XCUIElementTypeOther//XCUIElementTypeTextField)[1]");
            PlusbuttonSellUs = By.xpath("(//XCUIElementTypeStaticText[@name='＋'])[1]");
//        priceDownButtonUs;
//        processButtonUs;
//        trading_passwordUs;
//        confirmSellButtonUs;
//        currentStockPriceUs;
//        stockNameUs;
//        selectHoldedStock;
            hodlingScreenHeader = By.xpath("(//XCUIElementTypeStaticText)[1]");
//        usSellPageTitle;
            okButton = By.xpath("//XCUIElementTypeButton[@name='OK']");
        } else {
            sellOrderButtonUs = By.id("jp.co.rakuten_sec.ispeed:id/us_cash_order_sell_normal_input_button");
            quantityTextFiledUs = By.id("jp.co.rakuten_sec.ispeed:id/number_orders_edit_text");
            PlusbuttonSellUs = By.id("jp.co.rakuten_sec.ispeed:id/upper_button");
            priceDownButtonUs = By.id("jp.co.rakuten_sec.ispeed:id/lower_button");
            processButtonUs = By.id("jp.co.rakuten_sec.ispeed:id/process_button");
            trading_passwordUs = By.xpath("//android.widget.EditText//[@resource-id='jp.co.rakuten_sec.ispeed:id/trading_password_text']");
            confirmSellButtonUs = By.id("jp.co.rakuten_sec.ispeed:id/confirm_button");
            currentStockPriceUs = By.id("jp.co.rakuten_sec.ispeed:id/us_description_price_text");
            stockNameUs = By.id("jp.co.rakuten_sec.ispeed:id/a_order_market_info_us");
            selectHoldedStock = By.id("jp.co.rakuten_sec.ispeed:id/assets_holding_description_description_text");
            hodlingScreenHeader = By.id("jp.co.rakuten_sec.ispeed:id/center_title");
            usSellPageTitle = By.id("jp.co.rakuten_sec.ispeed:id/us_cash_order_sell_normal_input_global_header_title_text");
            okButton = By.xpath("//android.widget.Button[@text='OK']");
        }
    }

    public SellUSOrder(AppiumDriver driver) {
        super(driver);
        locators();
    }

    MyOptionPage myOptionPage = new MyOptionPage(driver);
    BuyUsOrder buyUsOrder = new BuyUsOrder(driver);

    public void getOptionPage() {
        //myOptionPage.clickOnOrderIcon();
        //buyUsOrder.clickOnUsOrderTab();
    }

    public void sellOrderUs() {
        waitForElementToBeClickable(sellOrderButtonUs);
        clickElement(sellOrderButtonUs);
    }

    public void selectStock() {
        waitForElementToBeClickable(selectHoldedStock);
        clickElement(selectHoldedStock);
    }

    public void clickplusbutton() {
        waitForElementToBeClickable(PlusbuttonSellUs);
        clickElement(PlusbuttonSellUs);
    }

    public void selectMarketTab() {
        buyUsOrder.selectMarketTab();
    }

    public void confirmSell() {

        waitForSeconds(3);
        scrollDownToBottom();
        buyUsOrder.clickOnTradingPasswordInputField("1234");
        buyUsOrder.clickOnOrderConfirmBtn();
    }

    public String getQuantity() {
        waitForElementToBeClickable(quantityTextFiledUs);
        clickElement(quantityTextFiledUs);
        waitForElementToBeVisible(okButton);
        clickElement(okButton);
        return getTextFromElement(quantityTextFiledUs);
    }

    public void verifyHoldingScreen(SoftAssert softAssert) {
        waitForElementToBeVisible(hodlingScreenHeader);
        String header = getTextFromElement(hodlingScreenHeader);
        softAssert.assertEquals(header, "米国株式 保有銘柄一覧");
    }

    public void verifyUSsellPage(SoftAssert softAssert) {
        waitForElementToBeVisible(usSellPageTitle);
        String title = getTextFromElement(usSellPageTitle);
        softAssert.assertEquals(title, "米国株式売り");
    }

}