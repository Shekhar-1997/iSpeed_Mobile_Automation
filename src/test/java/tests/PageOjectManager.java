package tests;

import pages.*;
import utils.*;

import java.time.LocalDateTime;

import static tests.BaseTest.driver;

public class PageOjectManager{
    public  static EditAlertSettingPage editAlertSettingPage;
    public static BasePage basePage;
    public static MenuPage menuPage;
    //private DeleteAlertPage deleteAlert;
    public static AlertSettingPage alertSettingPage;
    public static MyOptionPage myOptionPage;
    public static SearchPage searchPage;
    // public static AlertIndexSetPage alertIndexPage;
    public  static DisableAlert disable;
    public static  DeleteAlertPage deleteAlert;
    public static BuyUsOrder buyUsOrder;
    public  static VerifyInAlertStatusList alert;
    public static SellUSOrder sellUSOrder;
    public static BuyDomesticOrder buyDomestic;
    public static OMSExecution omscommand;
    public static FilterOptionPage filter;
    public static SellDomesticPage sellDomestic;
    public static OrderSummaryPage orderSummary;
    public static CoreDBAPI coreDBAPI;
    public static LoginPage loginPage;
    public static VerifyCreatedAlert verification;

    public static DetectMobileOS mobileOS;

    public static VerificationForBuyAndSell verificationBuySell;
    public static LocalDateTime startTime;
    public static LocalDateTime endTime;
    public static HandleErrorPopup handlePopup;
    public static CreateAllAlertUtil createAlertUtil;
    public static LogoutTest logoutTest;
    public  static PushNotificationIOS pushNotificationIOS;
    public  static VerifyInStatusListIOS statusListIOS;
    public static AccountCancel accountCancel;
    public static CreateNotificationTestIndex indexAlert;
    public static CreateNotificationTestUS usalert;

    public static CreateNotificationTestJP jpAlert;
    public static DeleteAlertUtil deleteAlertUtil;
    public static   LoginTest login;
    public static CorporateActionAlertUtils setCorporationAlert;
    public static VerifyInAlertStatusList verificationInStatusList;
    public static VerifyPushNotification pushNotification;
    public static DeviceRegistrationUtil deviceRegisteration;
    public static ExpiryAlertUtils expiryAlertUtils;
    public static WiFiManagerADBUtility wifiManager;
    public static InactiveUserUtil inactiveUser;

    public static DemoAPI demoAPI;
    public static KeepAliveUtil keepAliveUtil;
    public static BuyAndSellOrderTestJP buyAndSellOrderTestJP;
    public static BuyAndSellOrderUS buyAndSellOrderUS;
    public static ScreenshotUtil screenshotUtil;
    public static PageSourceUtil pageSourceUtil;
    public static Logout logout;

    public static void initpages() {
        editAlertSettingPage = new EditAlertSettingPage(driver);
        alertSettingPage = new AlertSettingPage(driver);
        myOptionPage = new MyOptionPage(driver);
        searchPage = new SearchPage(driver);
        basePage = new BasePage(driver);
        menuPage = new MenuPage(driver);
        deleteAlert=new DeleteAlertPage(driver);
        buyDomestic = new BuyDomesticOrder(driver);
        sellUSOrder = new SellUSOrder(driver);
        buyUsOrder = new BuyUsOrder(driver);
        omscommand = new OMSExecution();
        filter = new FilterOptionPage(driver);
        sellDomestic = new SellDomesticPage(driver);
        orderSummary = new OrderSummaryPage(driver);
        loginPage = new LoginPage(driver);
        verification=new VerifyCreatedAlert();
        verificationBuySell=new VerificationForBuyAndSell();
        handlePopup=new HandleErrorPopup(driver);
        createAlertUtil=new CreateAllAlertUtil();
        logoutTest=new LogoutTest();
        pushNotificationIOS=new PushNotificationIOS(driver);
        deleteAlert = new DeleteAlertPage(driver);
        statusListIOS=new VerifyInStatusListIOS(driver);
        accountCancel=new AccountCancel(driver);
        indexAlert = new CreateNotificationTestIndex();
        usalert = new CreateNotificationTestUS();
        jpAlert = new CreateNotificationTestJP();
        disable = new DisableAlert();
        deleteAlertUtil=new DeleteAlertUtil();
        login = new LoginTest();
        setCorporationAlert=new CorporateActionAlertUtils();
        verificationInStatusList=new VerifyInAlertStatusList();
        pushNotification = new VerifyPushNotification(driver);
        deviceRegisteration=new DeviceRegistrationUtil();
        expiryAlertUtils=new ExpiryAlertUtils();
        wifiManager=new WiFiManagerADBUtility();
        inactiveUser=new InactiveUserUtil();
        demoAPI=new DemoAPI();
        keepAliveUtil=new KeepAliveUtil();
        buyAndSellOrderTestJP=new BuyAndSellOrderTestJP();
        buyAndSellOrderUS=new BuyAndSellOrderUS();
        screenshotUtil=new ScreenshotUtil();
        pageSourceUtil=new PageSourceUtil();
        logout= new Logout(driver);
        //demoPageSourse=new DemoPageSourse();


    }


}
