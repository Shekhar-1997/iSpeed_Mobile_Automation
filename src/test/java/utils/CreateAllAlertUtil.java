package utils;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.EditAlertSettingPage;
import pages.HandleErrorPopup;
import pages.VerifyPushNotification;
import tests.BaseTest;


import java.util.ArrayList;
import java.util.List;

import static tests.PageOjectManager.*;


public class CreateAllAlertUtil extends BaseTest {
    BasePage basePage= new BasePage(driver);
    HandleErrorPopup errorPopupHeader = new HandleErrorPopup(driver);
    String stockName;
    // Common method to handle different alerts (Price Up/Down, Previous Day Up/Down)
    public String createAlert(SoftAssert softAssert, String stockDetail, String countryCode, String alertType, String priceDirection, String alertCategory, String snapshotName, boolean isPreviousDay, boolean isIndex, String alertTypseSetting) {
        if (isIndex) {
            preRequisiteIndex(stockDetail, softAssert);
        } else {
            preRequisite(stockDetail, softAssert);
        }

        if (countryCode.equalsIgnoreCase("Index")) {
            stockName = stockDetail;
        } else {
            stockName = getStockName(countryCode);
        }
        // Select Previous Day Tab if required
        if (isPreviousDay) {
            editAlertSettingPage.selectPreviousDayRatioTab(softAssert);
        }

        // Set the price or previous day alert based on the provided alertType
        String price ;
        String formattedPrice = "";

            if (isPreviousDay) {
                price = editAlertSettingPage.setPreviousDayAlert(priceDirection, Double.parseDouble(alertCategory), softAssert, alertType, testData.getIncrementPercent());
                formattedPrice = price;
            } else {
                price = editAlertSettingPage.setPriceAlert(countryCode, priceDirection, Double.parseDouble(alertCategory), softAssert, alertType, testData.getIncrementPercent());
                if (countryCode.equalsIgnoreCase("JP")) {
                    formattedPrice = basePage.formatNumberToIndianSystem(price);
                } else if (countryCode.equalsIgnoreCase("Index")) {
                    formattedPrice = basePage.formatNumberToIndianSystemDouble(price);
                } else if (countryCode.equalsIgnoreCase("US")) {
                    formattedPrice = price;
                }

        }

        // Create the alert with snapshot
        createAlertWithSnapshot(softAssert, snapshotName, "set");


        // Check the status of toggle and verify push notification

        if ("set".equals(alertType)) {
            basePage.waitForSeconds(testData.getWaitTime());
            // Check the status of the toggle for triggered alerts
            checkStatusOfToggleForTriggeredAlerts(stockName, softAssert, alertTypseSetting, snapshotName, testData.getWaitTime());

            // Verify push notification

            verifyPushNotification(stockName, price, alertTypseSetting, snapshotName + "PushAlert.png", softAssert);
            if (deviceType.equalsIgnoreCase("android")) {
                // Navigate back
                driver.navigate().back();
            }
            // Verify in the status list
            verifyInStatusList(stockName, formattedPrice, alertTypseSetting, snapshotName + "StatusListAlert.png", softAssert, isIndex);
        }
        return stockName;
    }


    // Index Price Up Alert
    public void createPriceUpIndexAlert(String stockDetail, SoftAssert softAssert, String indexType) {
        createAlert(softAssert, stockDetail, "Index", "set", "up", String.valueOf(testData.getDomesticIncrementAmount()), indexType + "PriceUpIndexAlert", false, true, "priceUp");
    }

    // Index Price Down Alert
    public void createPriceDownIndexAlert(String stockDetail, SoftAssert softAssert, String indexType) {
        createAlert(softAssert, stockDetail, "Index", "set", "down", String.valueOf(testData.getDomesticDecrementAmount()), indexType + "PriceUpIndexAlert", false, true, "priceDown");
    }

    // Index Previous Day Up Alert
    public void createPreviousDayUpIndexAlert(String stockDetail, SoftAssert softAssert, String indexType) {
        createAlert(softAssert, stockDetail, "Index", "set", "up", String.valueOf(testData.getPreviousDayRatioIncrementAmount1()), indexType + "PreviousDayUpAlert", true, true, "previousDayUp");
    }

    // Index Previous Day Down Alert
    public void createPreviousDayDownIndexAlert(String stockDetail, SoftAssert softAssert, String indexType) {
        createAlert(softAssert, stockDetail, "Index", "set", "down", String.valueOf(testData.getPreviousDayRatioDecrementAmount1()), indexType + "PreviousDayDownAlert", true, true, "previousDayDown");
    }

    public boolean verifyPushNotification(String stockDetail, String price, String type, String fileName, SoftAssert softAssert) {

        String pushMessage = null;
        if (deviceType.equalsIgnoreCase("ios")) {
            pushMessage = pushNotificationIOS.getSinglePushNotification(stockDetail, fileName);

        } else {
            pushMessage = pushNotification.verifyInrealDevice2(stockDetail, fileName);
        }
        basePage.waitForSeconds(2);
        System.out.println("main class" + pushMessage);

        if (pushMessage != null) {
            if (pushMessage.contains(price)) {
                logToAllure1("" + stockDetail + " " + price + " alert got triggered.");
                if (type.equalsIgnoreCase("priceUp")) {
                    boolean condition = (pushMessage.contains("" + price + "円に到達しました。") | pushMessage.contains("" + price + "00ドルに到達しました。") | pushMessage.contains("" + price + "に到達しました。"));
                    Assert.assertTrue(condition, " " + type + "alert not triggered for" + price + ".");
                } else if (type.equalsIgnoreCase("priceDown")) {
                    boolean condition = (pushMessage.contains("" + price + "円に到達しました。") | pushMessage.contains("" + price + "00ドルに到達しました。") | pushMessage.contains("" + price + "に到達しました。"));
                    Assert.assertTrue(condition, " " + type + "alert not triggered for" + price + ".");
                    //softAssert.assertEquals(pushMessage,""+price+"円に到達しました。");
                } else if (type.equalsIgnoreCase("previousDayUp")) {
                    boolean condition = (pushMessage.contains("が前日比+" + price + "%に到達しました。"));
                    Assert.assertTrue(condition, "previousDay up alert not got triggered.");

                } else if (type.equalsIgnoreCase("previousDayDown")) {
                    boolean condition = (pushMessage.contains("が前日比-" + price + "%に到達しました。"));
                    Assert.assertTrue(condition, "previousDay Down alert not got triggered.");

                }

            } else {
                logToAllure1("not recieved alert for " + stockDetail + "" + price + " ");
                softAssert.fail("not recieved alert for " + stockDetail + "" + price + " ");
                return false;
            }
        } else {
            logToAllure1("not recieved any " + stockDetail + " alert");
            softAssert.fail("not recieved any " + stockDetail + " alert in Notification center");
            return false;
        }
        return false;
    }

    public void verifyInStatusList(String stockDetail, String price, String type, String fileName, SoftAssert softAssert, boolean isIndex) {
        menuPage.goToMenuPage();
        menuPage.gotoAlertStatusList();
        basePage.waitForSeconds(5);
        captureScreenshot(fileName);

        String pushMessage = null;
        if (deviceType.equalsIgnoreCase("ios")) {
            pushMessage = statusListIOS.verifyInAlertStatusListPush(price, stockDetail, softAssert, isIndex);
        } else {
            pushMessage = verificationInStatusList.verifyAlertsInStatusListPush(price, stockDetail, softAssert);
        }
        if (pushMessage != null) {
            if (type.equalsIgnoreCase("priceUp")) {
                boolean condition = false;
                if (deviceType.equalsIgnoreCase("ios")) {
                    condition = (pushMessage.contains("(現在値)達成 " + price + ""));
                } else {
                    condition = (pushMessage.contains("(現在値)達成\n" + "" + price + ""));
                }
                Assert.assertTrue(condition);

            } else if (type.equalsIgnoreCase("priceDown")) {
                boolean condition = false;
                if (deviceType.equalsIgnoreCase("ios")) {
                    condition = (pushMessage.contains("(現在値)達成 " + price + ""));
                } else {
                    condition = (pushMessage.contains("(現在値)達成\n" + "" + price + ""));
                }
                Assert.assertTrue(condition);
            } else if (type.equalsIgnoreCase("previouDayUp")) {
                boolean condition;
                if (deviceType.equalsIgnoreCase("ios")) {
                    condition = (pushMessage.contains("(前日比)達成 " + price + ""));
                } else {
                    condition = (pushMessage.contains("(前日比)達成\n" +
                            "+" + price + ""));
                }
                Assert.assertTrue(condition);
            } else if (type.equalsIgnoreCase("previousDayDown")) {

                boolean condition;
                if (deviceType.equalsIgnoreCase("ios")) {
                    condition = (pushMessage.contains("(前日比)達成 -" + price + ""));
                } else {
                    condition = (pushMessage.contains("(前日比)達成\n" +
                            "-" + price + ""));
                }
                Assert.assertTrue(condition);

            }
        } else {
            System.out.println("not got alert in status list");
        }

    }

    public boolean verifyPushNotificationForWifi(String stockDetail, String price, String type, String fileName, SoftAssert softAssert) {
        VerifyPushNotification pushNotification = new VerifyPushNotification(driver);
        String pushMessage = pushNotification.verifyInrealDevice2(stockDetail, fileName);

        basePage.waitForSeconds(2);
        System.out.println("main class" + pushMessage);
        boolean flag = false;
        if (pushMessage != null) {
            if (pushMessage.contains(price)) {

                logToAllure1("" + stockDetail + "" + price + " alert got triggered.");
                if (type.equalsIgnoreCase("previousDayDown")) {
                    boolean condition = (pushMessage.contains("" + price + "円に到達しました。") | pushMessage.contains("" + price + "00ドルに到達しました。") | pushMessage.contains("" + price + "に到達しました。"));
                    Assert.assertTrue(condition, " " + type + "alert not triggered for" + price + ".");
                    flag = true;
                } else if (type.equalsIgnoreCase("priceDown")) {
                    boolean condition = (pushMessage.contains("" + price + "円に到達しました。") | pushMessage.contains("" + price + "00ドルに到達しました。") | pushMessage.contains("" + price + "に到達しました。"));
                    Assert.assertTrue(condition, " " + type + "alert not triggered for" + price + ".");
                    flag = true;
                }
            }
        } else {
            flag = false;
        }
        return flag;
    }

    public void preRequisite(String stockDetail, SoftAssert softAssert) {
        basePage.waitForSeconds(5);
        captureScreenshot("alertSettingList.png");
        boolean settingIconStateEnabled = alertSettingPage.goToEditAlertSettingPage(softAssert);
        basePage.waitForSeconds(3);
        if (settingIconStateEnabled) {
            captureScreenshot("editAlertSettingPage.png");
        }
//        pageSourceUtil.capturePageSourceWithTimestamp();
//        pageSourceUtil.captureScreenshotWithTimestamp();
        editAlertSettingPage.clickOnAddSymbolOption();
        myOptionPage.goToSearchOption();
        searchPage.searchStock(stockDetail);
        captureScreenshot("verificationPageOfResult.png");
        searchPage.goToEditAlertStockPage(softAssert);
        basePage.waitForSeconds(3);
        editAlertSettingPage.selectPresentValueTab();
        basePage.waitForSeconds(2);
        //String value = editAlertSettingPage.getCurrentPrice(softAssert);
    }

    public void prerequisiteForAlertSettingScreen(){
        captureScreenshot("menuPage.png");
        basePage.waitForSeconds(2);
        menuPage.goToNotificationSetting();
        basePage.waitForSeconds(2);
        handlePopup.checkIfErrorPopup();
        captureScreenshot("alertSettingPage.png");
    }
    public void preRequisiteIndex(String stockNameIndex, SoftAssert softAssert) {
        basePage.waitForSeconds(5);
        captureScreenshot("alertSettingList.png");
        boolean settingIconStateEnabled = alertSettingPage.goToEditAlertSettingPage(softAssert);
        basePage.waitForSeconds(3);
        if (settingIconStateEnabled) {
            captureScreenshot("editAlertSettingPage.png");
        }
//        pageSourceUtil.capturePageSourceWithTimestamp();
//        pageSourceUtil.captureScreenshotWithTimestamp();
       // editAlertSettingPage.clickOnAddSymbolOption();
        // old changes are below
//        basePage.waitForSeconds(5);
//        //here i edited
//        alertSettingPage.goToEditAlertSettingPage(softAssert);
//        editAlertSettingPage.clickOnAddSymbolOption();
        basePage.waitForSeconds(2);
        captureScreenshot("editSettingScreen.png");
        editAlertSettingPage.selectMarketIndex(softAssert);
        basePage.waitForSeconds(2);
        captureScreenshot("marketIndexselectionList.png");
        editAlertSettingPage.selectMarketIndexList(stockNameIndex);
        basePage.waitForSeconds(1);
        captureScreenshot("selectMarketIndex.png");
    }
public void  verifyMenuPage(){
    menuPage.goToMenuPage();
        boolean menu=menuPage.verifyMenuPage();
        if(!menu){
            menuPage.goToMenuPage();
        }

}
    public String getStockName(String type) {
        String stockName = null;
        if (type.equalsIgnoreCase("JP")) {
            String jpStockName = editAlertSettingPage.getStockName();
            stockName = basePage.getStockName(jpStockName);
            System.out.println(stockName);
        } else if (type.equalsIgnoreCase("US")) {
            String usStockName = editAlertSettingPage.getStockName();
            stockName = basePage.removeSpaces(usStockName);
        }
        return stockName;
    }


    public void checkStatusOfToggleForTriggeredAlerts(String stockName, SoftAssert softAssert, String alertTypeSetting, String alertName, int waiTime) {
        editAlertSettingPage = new EditAlertSettingPage(driver);
        basePage.waitForSeconds(3);
        System.out.println(stockName);
        editAlertSettingPage.selectStockFromSetting(stockName);
        basePage.waitForSeconds(10);
        captureScreenshot("checkTheDisableState");
        if (alertTypeSetting.equalsIgnoreCase("priceUp")) {
            String status = editAlertSettingPage.isPriceUpAlertTriggered(softAssert);
            Assert.assertEquals(status, "Off", "price up alert not triggered.");
        } else if (alertTypeSetting.equalsIgnoreCase("priceDown")) {
            String status = editAlertSettingPage.isPriceDownAlertTriggered(softAssert);
            Assert.assertEquals(status, "Off", "price down alert not triggered.");
        } else if (alertTypeSetting.equalsIgnoreCase("previousDayUp")) {
            editAlertSettingPage.selectPreviousDayRatioTab(softAssert);
            String status = editAlertSettingPage.isPreviousDayUpAlertTriggered(softAssert);
            Assert.assertEquals(status, "Off", "previousDay ratio up alert not triggered.");
        } else if (alertTypeSetting.equalsIgnoreCase("previousDayDown")) {
            editAlertSettingPage.selectPreviousDayRatioTab(softAssert);
            String status = editAlertSettingPage.isPreviousdayDownAlertTriggered(softAssert);
            Assert.assertEquals(status, "Off", "previousDay ratio Down alert not triggered.");
        }
        basePage.waitForSeconds(1);
        captureScreenshot("" + alertTypeSetting + "alert_triggered_disable_toggle.png");
        alertSettingPage.goToAlertSettingPage();
        dbEvidenceCapture("CreateAlert" + alertName + "", "set" + alertTypeSetting + "afterTrigger", 1, true);
        editAlertSettingPage.waitForSettingScreenTobeVisible();
        captureScreenshot("" + alertTypeSetting + "alert_triggered.png");
        basePage.waitForSeconds(4);


    }

    public void checkStatusOfToggleForTriggeredAlertsExpiry(String stockName, SoftAssert softAssert, String alertType, String alertName, int waiTime) {
        basePage.waitForSeconds(3);
        menuPage.clickOnMenu();
        menuPage.goToNotificationSetting();
        editAlertSettingPage.selectStockFromSetting(stockName);
        basePage.waitForSeconds(7);
        editAlertSettingPage.selectPreviousDayRatioTab(softAssert);
        String status = editAlertSettingPage.isPreviousdayDownAlertTriggered(softAssert);
        captureScreenshot("ExpiryalertStatusOff.png");
        Assert.assertEquals(status, "Off", "Expiry Alert previousDay alert not triggered.");
       }


    @Step("{0}")
    public void logToAllure1(String message) {
        // This method logs messages to Allure report
        System.out.println(message); // Optional: Also print to console
    }

    public void createAlertWithSnapshot(SoftAssert softAssert, String snapshotName, String alertType) {
        basePage.waitForSeconds(2);
        basePage.hideKeyboard();
        captureScreenshot("" + snapshotName + ".png");
        editAlertSettingPage.clickOnRegisterBtn();
        try {
            handlePopup.checkErrorInPreviousTab();
            driver.navigate().back();
        } catch (Exception e) {
            System.out.println("The error is appologis for the inconvinience");
        }
        basePage.waitForSeconds(1);
        //handlePopup.checkIfErrorPopup();
        if (alertType.equalsIgnoreCase("set")) {
            dbEvidenceCapture("CreateAlert" + snapshotName + "", "setbeforeTrigger", 1, false);
        }
        try {
            if (editAlertSettingPage.waitFordeleteElementToBeVisible(softAssert)) {
                basePage.waitForSeconds(4);
                captureScreenshot("" + snapshotName + "Alert InEditAlertsettingList.png");
            }
            editAlertSettingPage.clickOnSaveBtn();
        } catch (Exception ignored) {
        }
        basePage.waitForSeconds(1);
        editAlertSettingPage.waitForSettingScreenTobeVisible();
        basePage.waitForSeconds(1);
        captureScreenshot("" + snapshotName + "Alert InsettingList.png");

    }

    public List<String> createJPpriceDownalertInternetOff(SoftAssert softAssert, String stockDetail) {
        List<String> values = new ArrayList<>();
        preRequisite(stockDetail, softAssert);
        String stockName = getStockName("JP");
        String price = editAlertSettingPage.setPriceAlert("JP", "down", testData.getDomesticDecrementAmount(), softAssert, "set", testData.getDecrementPercent());
        captureScreenshot("inputPricedown.png");
        createAlertWithSnapshot(softAssert, "JPpriceDownalert", "set");
        basePage.waitForSeconds(testData.getWaitTime());
        String value = testData.getJpStockpriceDown();
        values.add(stockName);
        values.add(price);
        return values;
    }

    public String createAlertForDelete(String region,String sheetName, CustomSoftAssert softAssert) {
        String JPstockName = createAlertUtil.getStockName(region);
        editAlertSettingPage.setPriceAlert(region,"up",testData.getIncrementAmount(),softAssert,"create",testData.getIncrementPercent());
        editAlertSettingPage.setPriceAlert(region,"down",testData.getIncrementAmount(),softAssert,"create",testData.getIncrementPercent());
        basePage.hideKeyboard();
        basePage.waitForSeconds(1);
        verification.captureEvidenceAferRegister(softAssert, sheetName, sheetName);
        return JPstockName;
    }

    public String createAllAlert(SoftAssert softAssert, String stockDetail, String region, String alertType, String snapshotName, String snapshotName2, boolean isIndex,boolean isNewsAlert) {
        if (isIndex) {
            preRequisiteIndex(stockDetail, softAssert);
        } else {
            // here i chnaged
            //preRequisiteMigration(stockDetail, softAssert);
            preRequisite(stockDetail, softAssert);
        }
        String stockName;

        if (region.equalsIgnoreCase("Index")) {
            stockName = stockDetail;
        } else {
            stockName = getStockName(region);
        }
        if(isNewsAlert){
            editAlertSettingPage.setNewsAlert(softAssert);
        }else {
            // Set the price or previous day alert based on the provided snapshot name

            editAlertSettingPage.setPriceAlert(region, "up", testData.getIncrementAmount(), softAssert, alertType, testData.getIncrementPercent());
            editAlertSettingPage.setPriceAlert(region, "down", testData.getDecrementAmount(), softAssert, alertType, testData.getDecrementPercent());
//            pageSourceUtil.capturePageSourceWithTimestamp();
//            pageSourceUtil.captureScreenshotWithTimestamp();
            captureScreenshot(snapshotName);

            editAlertSettingPage.selectPreviousDayRatioTab(softAssert);
            editAlertSettingPage.setPreviousDayAlert("up", testData.getPreviousDayRatioIncrementAmount(), softAssert, alertType, testData.getIncrementPercent());
            editAlertSettingPage.setPreviousDayAlert("down", testData.getPreviousDayRatioDecrementAmount(), softAssert, alertType, testData.getDecrementPercent());
//            pageSourceUtil.capturePageSourceWithTimestamp();
//            pageSourceUtil.captureScreenshotWithTimestamp();
            // Create the alert with snapshot
        }
        createAlertWithSnapshot(softAssert, snapshotName2, "Create");


        return stockName;
    }

    @Description("Verify values are updated")
    public void updateAllAlert(String stockName, SoftAssert softAssert, String region, String sheetName, String alertType) {
        boolean value = editAlertSettingPage.isAlertCreated(stockName, softAssert);
        softAssert.assertTrue(value);
        captureScreenshot("beforeUpdate" + region + "Alert.png");
        editAlertSettingPage.selectStockFromSetting(stockName);
//        basePage.waitForSeconds(2);

        editAlertSettingPage.setPriceAlert(region, "up", testData.getUpdateIncrementAmount(), softAssert, alertType, testData.getIncrementPercent());
        editAlertSettingPage.setPriceAlert(region, "down", testData.getUpdateDecrementAmount(), softAssert, alertType, testData.getDecrementPercent());
        captureScreenshot("Update priceUp and PriceDown" + region + "Alert");

        editAlertSettingPage.selectPreviousDayRatioTab(softAssert);

        editAlertSettingPage.setPreviousDayAlert("up", testData.getUpdatePreviousDayRatioIncrementAmount(), softAssert, alertType, testData.getIncrementPercent());
        editAlertSettingPage.setPreviousDayAlert("down", testData.getUpdatePreviousDayRatioDecrementAmount(), softAssert, alertType, testData.getDecrementPercent());

        verification.captureEvidenceAferRegister(softAssert, alertType, sheetName);
        //  dbEvidenceCapture(sheetName, alertType, 8, false);
    }

    // for DB migration data creation
    public void preRequisiteMigration(String stockDetail, SoftAssert softAssert) {
//        basePage.waitForSeconds(5);
//        captureScreenshot("alertSettingList.png");
//        boolean settingIconStateEnabled = alertSettingPage.goToEditAlertSettingPage(softAssert);
//        basePage.waitForSeconds(3);
//        if (settingIconStateEnabled) {
//            captureScreenshot("editAlertSettingPage.png");
//        }
        alertSettingPage.clickOnSettingIcon();
        editAlertSettingPage.clickOnAddSymbolOption();
        pageSourceUtil.capturePageSource("Edit_AlertSetting.txt");
        myOptionPage.goToSearchOption();
        searchPage.searchStock(stockDetail);
        captureScreenshot("verificationPageOfResult.png");
        searchPage.goToEditAlertStockPage(softAssert);
        basePage.waitForSeconds(3);
        editAlertSettingPage.selectPresentValueTab();
        basePage.waitForSeconds(2);
        //String value = editAlertSettingPage.getCurrentPrice(softAssert);
    }
}
