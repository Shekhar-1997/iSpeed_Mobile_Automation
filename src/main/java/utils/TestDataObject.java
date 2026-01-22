package utils;




import java.util.ArrayList;
import java.util.List;


public class TestDataObject {
    private String JpStockpriceUp;
    private String JpStockpriceDown;
    private String JpStockpreviousUp;
    private String JpStockpreviousDown;
    private String tickerCodePriceUp;
    private String tickerCodePriceDown;
    private String tickerCodePreviousDayUp;
    private String tickerCodePreviousDayDown;
    private String indexUSstockpriceUp;
    private String indexUSstockpriceDown;
    private String indexUSstockpreviousUp;
    private String indexUSstockpreviousDown;
    private String indexJPstockpriceUp;
    private String indexJPstockpricedown;
    private String indexJPstockpreviousUp;
    private String indexJPstockpreviousDown;
    private String indexFutureStockPriceUp;
    private String indexFutureStockPriceDown;
    private String indexFutureStockPreviousUp;
    private String indexFutureStockPreviousDown;
    private String domesticIncrementAmount;
    private int domesticDecrementAmount;
    private double usIncrementAmount;
    private double usDecrementAmount;
    private int indexIncrementAmount;
    private int indexDecrementAmount;
    private double previousDayRatioIncrementAmount1;
    private double previousDayRatioDecrementAmount1;
    private double uspreviousDayRatioIncrementAmount;
    private double uspreviousDayRatioDecrementAmount;
    private double incrementPercent;
    private double decrementPercent;
    private  String account;
    private  String userNameAWS;
    private String passwordAWS;
    private String caPrice;
    private int waitTime;


    private String usStock3;



    private String lossMarginClientCd;


    private String stockCode;
    private String stockNameUS;
    private String stockNameIndex;
    private int incrementAmount;
    private int decrementAmount;
    private double previousDayRatioIncrementAmount;
    private double previousDayRatioDecrementAmount;
    private int updateIncrementAmount;
    private int updateDecrementAmount;
    private String date;
    private double updatePreviousDayRatioIncrementAmount;
    private double updatePreviousDayRatioDecrementAmount;
    private String passPin;
    private String quantity;
    private String stockCodeBuySell;
    private String sellQuantity;
    private ArrayList<String> indexType;
    private String server;
    private String indexUS;
    private String indexNippon;
    private String indexSakimono;
    private String multipleAccountStockCode3;
    private String multipleAccountUSstockName;
    // Getters and Setters
    private String multipleAccountStockCode2;
    private String udid;
    private String appiumurl;
    private String appPath;
    private String username;
    private String password;
    private String accDeviceCtrlNo;
    private String clientCode;
    private String branchCode;
    private String usernameTwo;
    private String passwordTwo;
    private String accDeviceCtrlNoTwo;
    private String clientCodeTwo;
    private String endpointCoreDB;
    private String endpoinPushdb;
    private String cACronJobsJP;
    private String cACronJobsUS;
    private String cronJobAccCancel;
    private String cronJobMargin;
    private String cronJobLosscut;

    private String jpStock1;
    private String jpStock2;
    private String jpStock3;
    private String jpStock4;
    private String jpStock5;


    private String usStock1;
    private String usStock2;
    private String indexSakimono1;
    private String indexSakimono2;
    private String indexAlertUS1;
    private String indexAlertUS2;


    // below are suffix and prefix
    private String prefix;
    private String suffixCurrentDay;
    private String suffixPreviousDay;
    private String suffixAlertStatusList;
    private String suffixLoginScreen;
    private String suffixMainMenu;
    private String suffixAlertSettingScreen;
    private String suffixEditAlertSettingScreen;
    private String suffixJapanOrderHoldingsTab;
    private String suffixJapanOrderEnquiryTab;
    private String suffixJapanOrderContractEnquiryTab;
    private String suffixUSOrderHoldingsTab;
    private String suffixUSOrderEnquiryTab;
    private String suffixUSOrderContractEnquiryTab;
    private String textFileExtension;
    private String imageFileExtension;

    private String suffixOrderSellJP;
    private String suffixOrderSellUS;


    public String getSuffixEditAlertSettingScreen() {
        return suffixEditAlertSettingScreen;
    }

    public void setSuffixEditAlertSettingScreen(String suffixEditAlertSettingScreen) {
        this.suffixEditAlertSettingScreen = suffixEditAlertSettingScreen;
    }

    public String getSuffixOrderSellJP() {
        return suffixOrderSellJP;
    }

    public void setSuffixOrderSellJP(String suffixOrderSellJP) {
        this.suffixOrderSellJP = suffixOrderSellJP;
    }

    public String getSuffixOrderSellUS() {
        return suffixOrderSellUS;
    }

    public void setSuffixOrderSellUS(String suffixOrderSellUS) {
        this.suffixOrderSellUS = suffixOrderSellUS;
    }



    public String getTextFileExtension() {
        return textFileExtension;
    }

    public void setTextFileExtension(String textFileExtension) {
        this.textFileExtension = textFileExtension;
    }

    public String getImageFileExtension() {
        return imageFileExtension;
    }

    public void setImageFileExtension(String imageFileExtension) {
        this.imageFileExtension = imageFileExtension;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffixCurrentDay() {
        return suffixCurrentDay;
    }

    public void setSuffixCurrentDay(String suffixCurrentDay) {
        this.suffixCurrentDay = suffixCurrentDay;
    }

    public String getSuffixPreviousDay() {
        return suffixPreviousDay;
    }

    public void setSuffixPreviousDay(String suffixPreviousDay) {
        this.suffixPreviousDay = suffixPreviousDay;
    }

    public String getSuffixAlertStatusList() {
        return suffixAlertStatusList;
    }

    public void setSuffixAlertStatusList(String suffixAlertStatusList) {
        this.suffixAlertStatusList = suffixAlertStatusList;
    }

    public String getSuffixLoginScreen() {
        return suffixLoginScreen;
    }

    public void setSuffixLoginScreen(String suffixLoginScreen) {
        this.suffixLoginScreen = suffixLoginScreen;
    }

    public String getSuffixMainMenu() {
        return suffixMainMenu;
    }

    public void setSuffixMainMenu(String suffixMainMenu) {
        this.suffixMainMenu = suffixMainMenu;
    }

    public String getSuffixAlertSettingScreen() {
        return suffixAlertSettingScreen;
    }

    public void setSuffixAlertSettingScreen(String suffixAlertSettingScreen) {
        this.suffixAlertSettingScreen = suffixAlertSettingScreen;
    }

    public String getSuffixJapanOrderHoldingsTab() {
        return suffixJapanOrderHoldingsTab;
    }

    public void setSuffixJapanOrderHoldingsTab(String suffixJapanOrderHoldingsTab) {
        this.suffixJapanOrderHoldingsTab = suffixJapanOrderHoldingsTab;
    }

    public String getSuffixJapanOrderEnquiryTab() {
        return suffixJapanOrderEnquiryTab;
    }

    public void setSuffixJapanOrderEnquiryTab(String suffixJapanOrderEnquiryTab) {
        this.suffixJapanOrderEnquiryTab = suffixJapanOrderEnquiryTab;
    }

    public String getSuffixJapanOrderContractEnquiryTab() {
        return suffixJapanOrderContractEnquiryTab;
    }

    public void setSuffixJapanOrderContractEnquiryTab(String suffixJapanOrderContractEnquiryTab) {
        this.suffixJapanOrderContractEnquiryTab = suffixJapanOrderContractEnquiryTab;
    }

    public String getSuffixUSOrderHoldingsTab() {
        return suffixUSOrderHoldingsTab;
    }

    public void setSuffixUSOrderHoldingsTab(String suffixUSOrderHoldingsTab) {
        this.suffixUSOrderHoldingsTab = suffixUSOrderHoldingsTab;
    }

    public String getSuffixUSOrderEnquiryTab() {
        return suffixUSOrderEnquiryTab;
    }

    public void setSuffixUSOrderEnquiryTab(String suffixUSOrderEnquiryTab) {
        this.suffixUSOrderEnquiryTab = suffixUSOrderEnquiryTab;
    }

    public String getSuffixUSOrderContractEnquiryTab() {
        return suffixUSOrderContractEnquiryTab;
    }

    public void setSuffixUSOrderContractEnquiryTab(String suffixUSOrderContractEnquiryTab) {
        this.suffixUSOrderContractEnquiryTab = suffixUSOrderContractEnquiryTab;
    }

    public String getJpStock1() {
        return jpStock1;
    }

    public void setJpStock1(String jpStock1) {
        this.jpStock1 = jpStock1;
    }

    public String getJpStock2() {
        return jpStock2;
    }

    public void setJpStock2(String jpStock2) {
        this.jpStock2 = jpStock2;
    }

    public String getJpStock3() {
        return jpStock3;
    }

    public void setJpStock3(String jpStock3) {
        this.jpStock3 = jpStock3;
    }

    public String getJpStock4() {
        return jpStock4;
    }

    public void setJpStock4(String jpStock4) {
        this.jpStock4 = jpStock4;
    }

    public String getJpStock5() {
        return jpStock5;
    }

    public void setJpStock5(String jpStock5) {
        this.jpStock5 = jpStock5;
    }

    public String getEndpoinPushdb() {
        return endpoinPushdb;
    }

    public void setEndpoinPushdb(String endpoinPushdb) {
        this.endpoinPushdb = endpoinPushdb;
    }


    public String getEndpointCoreDB() {
        return endpointCoreDB;
    }

    public void setEndpointCoreDB(String endpointCoreDB) {
        this.endpointCoreDB = endpointCoreDB;
    }


    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockNameUS() {
        return stockNameUS;
    }
    public String getLossMarginClientCd() {
        return lossMarginClientCd;
    }

    public void setLossMarginClientCd(String lossMarginClientCd) {
        this.lossMarginClientCd = lossMarginClientCd;
    }
    public void setStockNameUS(String stockNameUS) {
        this.stockNameUS = stockNameUS;
    }

    public int getIncrementAmount() {
        return incrementAmount;
    }

    public void setIncrementAmount(int incrementAmount) {
        this.incrementAmount = incrementAmount;
    }

    public int getDecrementAmount() {
        return decrementAmount;
    }

    public void setDecrementAmount(int decrementAmount) {
        this.decrementAmount = decrementAmount;
    }

    public double getPreviousDayRatioIncrementAmount() {
        return previousDayRatioIncrementAmount;
    }

    public void setPreviousDayRatioIncrementAmount(double previousDayRatioIncrementAmount) {
        this.previousDayRatioIncrementAmount = previousDayRatioIncrementAmount;
    }

    public double getPreviousDayRatioDecrementAmount() {
        return previousDayRatioDecrementAmount;
    }

    public void setPreviousDayRatioDecrementAmount(double previousDayRatioDecrementAmount) {
        this.previousDayRatioDecrementAmount = previousDayRatioDecrementAmount;
    }

    public int getUpdateIncrementAmount() {
        return updateIncrementAmount;
    }

    public void setUpdateIncrementAmount(int updateIncrementAmount) {
        this.updateIncrementAmount = updateIncrementAmount;
    }

    public int getUpdateDecrementAmount() {
        return updateDecrementAmount;
    }

    public void setUpdateDecrementAmount(int updateDecrementAmount) {
        this.updateDecrementAmount = updateDecrementAmount;
    }

    public double getUpdatePreviousDayRatioIncrementAmount() {
        return updatePreviousDayRatioIncrementAmount;
    }

    public void setUpdatePreviousDayRatioIncrementAmount(double updatePreviousDayRatioIncrementAmount) {
        this.updatePreviousDayRatioIncrementAmount = updatePreviousDayRatioIncrementAmount;
    }

    public double getUpdatePreviousDayRatioDecrementAmount() {
        return updatePreviousDayRatioDecrementAmount;
    }

    public void setUpdatePreviousDayRatioDecrementAmount(double updatePreviousDayRatioDecrementAmount) {
        this.updatePreviousDayRatioDecrementAmount = updatePreviousDayRatioDecrementAmount;
    }

    public String getStockNameIndex() {
        return stockNameIndex;
    }

    public void setStockNameIndex(String stockNameIndex) {
        this.stockNameIndex = stockNameIndex;
    }

    public String getPassPin() {
        return passPin;
    }

    public void setPassPin(String passPin) {
        this.passPin = passPin;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStockCodeBuySell() {
        return stockCodeBuySell;
    }

    public void setStockCodeBuySell(String stockCodeBuySell) {
        this.stockCodeBuySell = stockCodeBuySell;
    }

    public String getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(String sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public ArrayList<String> getIndexType() {
        return indexType;
    }

    public void setIndexType(ArrayList<String> indexType) {
        this.indexType = indexType;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getIndexUS() {
        return indexUS;
    }

    public void setIndexUS(String indexUS) {
        this.indexUS = indexUS;
    }

    public String getIndexNippon() {
        return indexNippon;
    }

    public void setIndexNippon(String indexNippon) {
        this.indexNippon = indexNippon;
    }

    public String getIndexSakimono() {
        return indexSakimono;
    }

    public void setIndexSakimono(String indexSakimono) {
        this.indexSakimono = indexSakimono;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getIncrementPercent() {
        return incrementPercent;
    }

    public void setIncrementPercent(double incrementPercent) {
        this.incrementPercent = incrementPercent;
    }

    public double getDecrementPercent() {
        return decrementPercent;
    }

    public void setDecrementPercent(double decrementPercent) {
        this.decrementPercent = decrementPercent;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public String getMultipleAccountStockCode2() {
        return multipleAccountStockCode2;
    }

    public String getMultipleAccountUSstockName() {
        return multipleAccountUSstockName;
    }

    public String getMultipleAccountStockCode3() {
        return multipleAccountStockCode3;
    }


    public String getJpStockpriceUp() {
        return JpStockpriceUp;
    }

    public void setJpStockpriceUp(String jpStockpriceUp) {
        JpStockpriceUp = jpStockpriceUp;
    }

    public String getJpStockpriceDown() {
        return JpStockpriceDown;
    }

    public void setJpStockpriceDown(String jpStockpriceDown) {
        JpStockpriceDown = jpStockpriceDown;
    }

    public String getJpStockpreviousUp() {
        return JpStockpreviousUp;
    }

    public void setJpStockpreviousUp(String jpStockpreviousUp) {
        JpStockpreviousUp = jpStockpreviousUp;
    }

    public String getJpStockpreviousDown() {
        return JpStockpreviousDown;
    }

    public void setJpStockpreviousDown(String jpStockpreviousDown) {
        JpStockpreviousDown = jpStockpreviousDown;
    }

    public String getTickerCodePriceUp() {
        return tickerCodePriceUp;
    }

    public void setTickerCodePriceUp(String tickerCodePriceUp) {
        this.tickerCodePriceUp = tickerCodePriceUp;
    }

    public String getTickerCodePriceDown() {
        return tickerCodePriceDown;
    }

    public void setTickerCodePriceDown(String tickerCodePriceDown) {
        this.tickerCodePriceDown = tickerCodePriceDown;
    }

    public String getTickerCodePreviousDayUp() {
        return tickerCodePreviousDayUp;
    }

    public void setTickerCodePreviousDayUp(String tickerCodePreviousDayUp) {
        this.tickerCodePreviousDayUp = tickerCodePreviousDayUp;
    }

    public String getTickerCodePreviousDayDown() {
        return tickerCodePreviousDayDown;
    }

    public void setTickerCodePreviousDayDown(String tickerCodePreviousDayDown) {
        this.tickerCodePreviousDayDown = tickerCodePreviousDayDown;
    }

    public String getIndexUSstockpriceUp() {
        return indexUSstockpriceUp;
    }

    public void setIndexUSstockpriceUp(String indexUSstockpriceUp) {
        this.indexUSstockpriceUp = indexUSstockpriceUp;
    }

    public String getIndexUSstockpriceDown() {
        return indexUSstockpriceDown;
    }

    public void setIndexUSstockpriceDown(String indexUSstockpriceDown) {
        this.indexUSstockpriceDown = indexUSstockpriceDown;
    }

    public String getIndexUSstockpreviousUp() {
        return indexUSstockpreviousUp;
    }

    public void setIndexUSstockpreviousUp(String indexUSstockpreviousUp) {
        this.indexUSstockpreviousUp = indexUSstockpreviousUp;
    }

    public String getIndexUSstockpreviousDown() {
        return indexUSstockpreviousDown;
    }

    public void setIndexUSstockpreviousDown(String indexUSstockpreviousDown) {
        this.indexUSstockpreviousDown = indexUSstockpreviousDown;
    }

    public String getIndexJPstockpriceUp() {
        return indexJPstockpriceUp;
    }

    public void setIndexJPstockpriceUp(String indexJPstockpriceUp) {
        this.indexJPstockpriceUp = indexJPstockpriceUp;
    }

    public String getIndexJPstockpricedown() {
        return indexJPstockpricedown;
    }

    public void setIndexJPstockpricedown(String indexJPstockpricedown) {
        this.indexJPstockpricedown = indexJPstockpricedown;
    }

    public String getIndexJPstockpreviousUp() {
        return indexJPstockpreviousUp;
    }

    public void setIndexJPstockpreviousUp(String indexJPstockpreviousUp) {
        this.indexJPstockpreviousUp = indexJPstockpreviousUp;
    }

    public String getIndexJPstockpreviousDown() {
        return indexJPstockpreviousDown;
    }

    public void setIndexJPstockpreviousDown(String indexJPstockpreviousDown) {
        this.indexJPstockpreviousDown = indexJPstockpreviousDown;
    }

    public String getIndexFutureStockPriceUp() {
        return indexFutureStockPriceUp;
    }

    public void setIndexFutureStockPriceUp(String indexFutureStockPriceUp) {
        this.indexFutureStockPriceUp = indexFutureStockPriceUp;
    }

    public String getIndexFutureStockPriceDown() {
        return indexFutureStockPriceDown;
    }

    public void setIndexFutureStockPriceDown(String indexFutureStockPriceDown) {
        this.indexFutureStockPriceDown = indexFutureStockPriceDown;
    }

    public String getIndexFutureStockPreviousUp() {
        return indexFutureStockPreviousUp;
    }

    public void setIndexFutureStockPreviousUp(String indexFutureStockPreviousUp) {
        this.indexFutureStockPreviousUp = indexFutureStockPreviousUp;
    }

    public String getIndexFutureStockPreviousDown() {
        return indexFutureStockPreviousDown;
    }

    public void setIndexFutureStockPreviousDown(String indexFutureStockPreviousDown) {
        this.indexFutureStockPreviousDown = indexFutureStockPreviousDown;
    }
//chnaged int -> String
    public String getDomesticIncrementAmount() {
        return domesticIncrementAmount;
    }

    public void setDomesticIncrementAmount(String domesticIncrementAmount) {
        this.domesticIncrementAmount = domesticIncrementAmount;
    }

    public int getDomesticDecrementAmount() {
        return domesticDecrementAmount;
    }

    public void setDomesticDecrementAmount(int domesticDecrementAmount) {
        this.domesticDecrementAmount = domesticDecrementAmount;
    }

    public double getUsIncrementAmount() {
        return usIncrementAmount;
    }

    public void setUsIncrementAmount(double usIncrementAmount) {
        this.usIncrementAmount = usIncrementAmount;
    }

    public double getUsDecrementAmount() {
        return usDecrementAmount;
    }

    public void setUsDecrementAmount(double usDecrementAmount) {
        this.usDecrementAmount = usDecrementAmount;
    }

    public int getIndexIncrementAmount() {
        return indexIncrementAmount;
    }

    public void setIndexIncrementAmount(int indexIncrementAmount) {
        this.indexIncrementAmount = indexIncrementAmount;
    }

    public int getIndexDecrementAmount() {
        return indexDecrementAmount;
    }

    public void setIndexDecrementAmount(int indexDecrementAmount) {
        this.indexDecrementAmount = indexDecrementAmount;
    }

    public double getPreviousDayRatioIncrementAmount1() {
        return previousDayRatioIncrementAmount1;
    }

    public void setPreviousDayRatioIncrementAmount1(double previousDayRatioIncrementAmount1) {
        this.previousDayRatioIncrementAmount = previousDayRatioIncrementAmount1;
    }

    public double getPreviousDayRatioDecrementAmount1() {
        return previousDayRatioDecrementAmount1;
    }

    public void setPreviousDayRatioDecrementAmount1(double previousDayRatioDecrementAmount1) {
        this.previousDayRatioDecrementAmount1 = previousDayRatioDecrementAmount1;
    }

    public double getUspreviousDayRatioIncrementAmount() {
        return uspreviousDayRatioIncrementAmount;
    }

    public void setUspreviousDayRatioIncrementAmount(double uspreviousDayRatioIncrementAmount) {
        this.uspreviousDayRatioIncrementAmount = uspreviousDayRatioIncrementAmount;
    }

    public double getUspreviousDayRatioDecrementAmount() {
        return uspreviousDayRatioDecrementAmount;
    }

    public void setUspreviousDayRatioDecrementAmount(double uspreviousDayRatioDecrementAmount) {
        this.uspreviousDayRatioDecrementAmount = uspreviousDayRatioDecrementAmount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserNameAWS() {
        return userNameAWS;
    }

    public void setUserNameAWS(String userNameAWS) {
        this.userNameAWS = userNameAWS;
    }

    public String getPasswordAWS() {
        return passwordAWS;
    }

    public void setPasswordAWS(String passwordAWS) {
        this.passwordAWS = passwordAWS;
    }



    public String getCaPrice() {
        return caPrice;
    }

    public void setCaPrice(String caPrice) {
        this.caPrice = caPrice;
    }

    public void setMultipleAccountStockCode3(String multipleAccountStockCode3) {
        this.multipleAccountStockCode3 = multipleAccountStockCode3;
    }

    public void setMultipleAccountUSstockName(String multipleAccountUSstockName) {
        this.multipleAccountUSstockName = multipleAccountUSstockName;
    }

    public void setMultipleAccountStockCode2(String multipleAccountStockCode2) {
        this.multipleAccountStockCode2 = multipleAccountStockCode2;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public String getAppiumurl() {
        return appiumurl;
    }

    public void setAppiumurl(String appiumurl) {
        this.appiumurl = appiumurl;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getaccDeviceCtrlNo() {
        return accDeviceCtrlNo;
    }

    public void setaccDeviceCtrlNo(String accDeviceCtrlNo) {

        this.accDeviceCtrlNo = accDeviceCtrlNo;
    }

    public String getClientCode() {

        return clientCode;
    }

    public void setClientCode(String clientCode) {

        this.clientCode = clientCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getUsernameTwo() {
        return usernameTwo;
    }

    public void setUsernameTwo(String usernameTwo) {
        this.usernameTwo = usernameTwo;
    }

    public String getPasswordTwo() {
        return passwordTwo;
    }

    public void setPasswordTwo(String passwordTwo) {
        this.passwordTwo = passwordTwo;
    }

    public String getAccDeviceCtrlNoTwo() {
        return accDeviceCtrlNoTwo;
    }

    public void setAccDeviceCtrlNoTwo(String accDeviceCtrlNoTwo) {
        this.accDeviceCtrlNoTwo = accDeviceCtrlNoTwo;
    }

    public String getClientCodeTwo() {
        return clientCodeTwo;
    }

    public void setClientCodeTwo(String clientCodeTwo) {
        this.clientCodeTwo = clientCodeTwo;
    }

    public String getCACronJobsJP() {
        return cACronJobsJP;
    }

    public void setCACronJobsJP(String cACronJobsJP) {
        this.cACronJobsJP = cACronJobsJP;
    }

    public String getCACronJobsUS() {
        return cACronJobsUS;
    }

    public void setCACronJobsUS(String cACronJobsUS) {
        this.cACronJobsUS = cACronJobsUS;
    }

    public String getCronJobAccCancel() {
        return cronJobAccCancel;
    }

    public void setCronJobAccCancel(String cronJobAccCancel) {
        this.cronJobAccCancel = cronJobAccCancel;
    }

    public String getCronJobMargin() {
        return cronJobMargin;
    }

    public void setCronJobMargin(String cronJobMargin) {
        this.cronJobMargin = cronJobMargin;
    }

    public String getCronJobLosscut() {
        return cronJobLosscut;
    }

    public void setCronJobLosscut(String cronJobLosscut) {
        this.cronJobLosscut = cronJobLosscut;
    }


    public String getUsStock1() {
        return usStock1;
    }

    public void setUsStock1(String usStock1) {
        this.usStock1 = usStock1;
    }

    public String getUsStock2() {
        return usStock2;
    }

    public void setUsStock2(String usStock2) {
        this.usStock2 = usStock2;
    }

    public String getIndexSakimono1() {
        return indexSakimono1;
    }

    public void setIndexSakimono1(String indexSakimono1) {
        this.indexSakimono1 = indexSakimono1;
    }

    public String getIndexSakimono2() {
        return indexSakimono2;
    }

    public void setIndexSakimono2(String indexSakimono2) {
        this.indexSakimono2 = indexSakimono2;
    }

    public String getIndexAlertUS1() {
        return indexAlertUS1;
    }

    public void setIndexAlertUS1(String indexAlertUS1) {
        this.indexAlertUS1 = indexAlertUS1;
    }

    public String getIndexAlertUS2() {
        return indexAlertUS2;
    }

    public void setIndexAlertUS2(String indexAlertUS2) {
        this.indexAlertUS2 = indexAlertUS2;
    }

    public String getUsStock3() {
        return usStock3;
    }

    public void setUsStock3(String usStock3) {
        this.usStock3 = usStock3;
    }


}