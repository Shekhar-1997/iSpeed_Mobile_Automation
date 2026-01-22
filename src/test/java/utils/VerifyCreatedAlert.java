package utils;

import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;
import tests.BaseTest;
import static tests.PageOjectManager.*;

public class VerifyCreatedAlert extends BaseTest {

    public void captureEvidenceAferRegister(SoftAssert softAssert, String alertType, String sheetName) {
        basePage.waitForSeconds(2);
        if (sheetName.contains("Delete")) {
            captureScreenshot("inputPriceUpUPandDown.png");
        } else {
            captureScreenshot("" + alertType + "inputPreviousDayUPandDown.png");
        }

        editAlertSettingPage.clickOnRegisterBtn();

        basePage.waitForSeconds(3);

        //multipleAccountsSingelDeviceDB("SetAlertMultipleAccounts",alertType,4);
        handlePopup.checkIfErrorPopup();
        basePage.waitForSeconds(2);
        try {
            if (editAlertSettingPage.waitFordeleteElementToBeVisible(softAssert)) {
                captureScreenshot("" + alertType + "InEditAlertsettingList.png");
            }
            editAlertSettingPage.clickOnSaveBtn();
        } catch (Exception ignored) {
        }
        editAlertSettingPage.waitForSettingScreenTobeVisible();
        captureScreenshot("" + alertType + "InsettingList.png");
    }


    @Step("{0}")
    public void logToAllure1(String message) {
        // This method logs messages to Allure report
        System.out.println(message); // Optional: Also print to console
    }
}
