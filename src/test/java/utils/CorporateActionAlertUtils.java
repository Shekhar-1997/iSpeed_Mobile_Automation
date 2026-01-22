package utils;

import org.testng.asserts.SoftAssert;
import pages.*;
import tests.BaseTest;
import pages.VerifyPushNotification;

import java.util.List;

import static tests.PageOjectManager.*;

public class CorporateActionAlertUtils extends BaseTest {
    public void setCorporationAlert(String dscrCd, String caCd, SoftAssert softAssert, String alertType) {
        menuPage.goToMenuPage();
        basePage.waitForSeconds(5);
        createCorporateAlert(dscrCd, softAssert, alertType);
    }

    public void createCorporateAlert(String dscrCd, SoftAssert softAssert, String alertType) {
        menuPage.goToNotificationSetting();
        basePage.waitForSeconds(6);
        alertSettingPage.goToEditAlertSettingPage(softAssert);
        editAlertSettingPage.clickOnAddSymbolOption();
        myOptionPage.goToSearchOption();
        searchPage.searchStock(dscrCd);
        captureScreenshot("verificationPageOfResult.png");
        basePage.waitForSeconds(4);
        searchPage.goToEditAlertStockPageForCA(softAssert);

        editAlertSettingPage.selectPresentValueTab();

        basePage.waitForSeconds(2);

        basePage.waitForSeconds(3);
        if (alertType.equalsIgnoreCase("JP")) {
            System.out.println("CAPrice before method call: " + testData.getCaPrice());
            editAlertSettingPage.setCAAlert(testData.getCaPrice(), softAssert, alertType);


        } else if (alertType.equalsIgnoreCase("US")) {
            editAlertSettingPage.setCAAlert(testData.getCaPrice(), softAssert, alertType);

        }

        basePage.hideKeyboard();
        basePage.waitForSeconds(2);
        captureScreenshot("setCorporateActionAlert.png");
        basePage.waitForSeconds(2);
        editAlertSettingPage.clickOnRegisterBtn();
        basePage.waitForSeconds(2);

        try {
            editAlertSettingPage.clickOnSaveBtn();
        } catch (Exception ignored) {
        }

        basePage.waitForSeconds(10);
        //Verify in pushNotification


    }

    public void verifyCorporateAction(String dscrCd, String caCd, SoftAssert softAssert) {

        List<String> pushMessages;
        if (deviceType.equalsIgnoreCase("ios")) {
            pushMessages = pushNotificationIOS.getCorporateActionPushNotificationList("CorPorateActionAlert");

        } else {
            pushMessages = pushNotification.verifyAlert("CorPorateActionAlert");
        }

        if (!pushMessages.isEmpty()) {
            for (String pushmessage : pushMessages) {

                if (pushmessage.contains("日本製鋼所の売買単位が変更されました。") && caCd.equals(003)) {

                    softAssert.assertEquals(pushmessage, "" + dscrCd + "日本製鋼所の売買単位が変更されました。");

                } else if (pushmessage.contains("メンバーズが上場廃止されました。") && caCd.equals(102)) {
                    softAssert.assertEquals(pushmessage, "" + dscrCd + "メンバーズが上場廃止されました。");

                } else if (pushmessage.contains("産業ファンドが株式分割されました。") && caCd.equals(1)) {
                    softAssert.assertEquals(pushmessage, "" + dscrCd + "産業ファンドが株式分割されました。");

                } else if (pushmessage.contains("") && caCd.equals(3)) {
                    softAssert.assertEquals(pushmessage, "" + dscrCd + "ジェイテクトが合併しました。");

                } else if (pushmessage.contains("") && caCd.equals(12)) {
                    softAssert.assertEquals(pushmessage, "" + dscrCd + "シーマに増資がありました。");

                } else if (pushmessage.contains("") && caCd.equals(1001)) {
                    softAssert.assertEquals(pushmessage, "" + dscrCd + "の株式分割があります。");

                } else if (pushmessage.contains("") && caCd.equals(1002)) {
                    softAssert.assertEquals(pushmessage, "" + dscrCd + "の株式併合があります。");

                } else if (pushmessage.contains("") && caCd.equals(1003)) {
                    softAssert.assertEquals(pushmessage, "" + dscrCd + "の株式配当があります。");

                } else if (pushmessage.contains("") && caCd.equals(1010)) {
                    softAssert.assertEquals(pushmessage, "" + dscrCd + "の上場廃止になります。");

                } else {
                    softAssert.fail();
                    System.out.println("Not received CorporateAction alert");
                }

            }


        } else {

            softAssert.fail("not recieved any alerts");
        }

        basePage.waitForSeconds(2);
        menuPage.goToMenuPage();
        menuPage.gotoAlertStatusList();
        basePage.waitForSeconds(4);
        captureScreenshot("CorporateActionAlertStatusListJP.png");
        basePage.waitForSeconds(2);
        switch (caCd) {
            case "102":
                verificationInStatusList.verifyCorporateActionInstatusList(dscrCd, "上場廃止されました", softAssert);
                break;
            case "003":
                verificationInStatusList.verifyCorporateActionInstatusList(dscrCd, "売買単位が変更されました", softAssert);
                break;
            case "1":
                verificationInStatusList.verifyCorporateActionInstatusList(dscrCd, "株式分割されました", softAssert);
                break;
            case "3":
                verificationInStatusList.verifyCorporateActionInstatusList(dscrCd, "合併しました", softAssert);
                break;
            case "12":
                verificationInStatusList.verifyCorporateActionInstatusList(dscrCd, "増資がありました", softAssert);
                break;
            case "1001":
                verificationInStatusList.verifyCorporateActionInstatusList(dscrCd, "株式分割があります", softAssert);

                break;
            case "1002":
                verificationInStatusList.verifyCorporateActionInstatusList(dscrCd, "株式併合があります", softAssert);

                break;
            case "1003":
                verificationInStatusList.verifyCorporateActionInstatusList(dscrCd, "株式配当があります", softAssert);

                break;
            case "1010":
                verificationInStatusList.verifyCorporateActionInstatusList(dscrCd, "上場廃止になります", softAssert);

                break;
        }
        basePage.waitForSeconds(6);

    }

    public void verifyExpiryAlertPush(SoftAssert softAssert) {
        List<String> pushMessages;
        if (deviceType.equalsIgnoreCase("ios")) {
            pushMessages = pushNotificationIOS.getCorporateActionPushNotificationList("ExpiryAlert");
        } else {
            pushMessages = pushNotification.verifyAlert("ExpiryAlert");
        }
        if (!pushMessages.isEmpty()) {

            if (pushMessages.contains("設定後60日が経過したアラートが削除されました。")) {
                softAssert.assertEquals(pushMessages, "設定後60日が経過したアラートが削除されました。");

            } else {
                System.out.println("Not received expiry alert");
            }

        } else {
            softAssert.fail("not received any alerts");
        }

        basePage.waitForSeconds(2);
        menuPage.goToMenuPage();
        basePage.waitForSeconds(2);
        menuPage.gotoAlertStatusList();
        basePage.waitForSeconds(4);
        captureScreenshot("ExpiryAlert.png");
        basePage.waitForSeconds(2);
        if (deviceType.equalsIgnoreCase("ios")) {
            verificationInStatusList.verifyMarginLosscutAlertInStatusList("アラートの期限が切れました", softAssert, "expiryAlert");
        } else {
            verificationInStatusList.verifyExpiryAlertInStatusList("アラートの期限が切れました", softAssert);
        }
        basePage.waitForSeconds(6);
    }

    public String trimLastDigit(String number) {
        // Convert the number to a string
        String numberStr = String.valueOf(number);
        // Remove the last character from the string
        String trimmedStr = numberStr.substring(0, numberStr.length() - 1);
        // Convert the trimmed string back to an integer
        return trimmedStr.trim();
    }
}


