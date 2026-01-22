package utils;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import tests.BaseTest;

import static tests.PageOjectManager.*;
import static tests.PageOjectManager.editAlertSettingPage;

public class DeleteAlertUtil extends BaseTest {
    public void deleteOneInAlertSettings(SoftAssert softAssert) {
        basePage.waitForSeconds(4);

        String stockNameBeforeDelete = deleteAlert.waitForFirstStockToBeVisible(softAssert);
        captureScreenshot("alertSetting_beforeDelete.png");
        deleteAlert.clickOnSelectToDelete(softAssert);
        captureScreenshot("deletesingleAlertPopup.png");
        deleteAlert.acceptDeletePopup(softAssert);
        captureScreenshot("acceptDeletePopup.png");
        try {
            editAlertSettingPage.clickOnSaveBtn();
            basePage.waitForSeconds(2);
            captureScreenshot("alertSettingListAferDelete.png");
        } catch (Exception e) {

        }
        if (!editAlertSettingPage.isAlertNotPresent()) {
            deleteAlert.stockNameAfterDelete(stockNameBeforeDelete, softAssert);
        } else {
            Assert.assertTrue(editAlertSettingPage.isAlertNotPresent());
        }

    }

    public void deleteAllInSetting(CustomSoftAssert softAssert) {
        basePage.waitForSeconds(5);


        //create 3 alerts with different stcock
        int countOfAlert = deleteAlert.countOfAlertsPresent();
        if (countOfAlert < 3) {
            createAlertUtil.preRequisite(testData.getStockCode(), softAssert);
            createAlertUtil.createAlertForDelete("JP", "DeleteAllAlert",softAssert);
            countOfAlert = deleteAlert.countOfAlertsPresent();
            if (countOfAlert < 2) {
                createAlertUtil.preRequisite(testData.getMultipleAccountStockCode2(), softAssert);
                createAlertUtil.createAlertForDelete("JP", "DeleteAllAlert",softAssert);
            }

        }
        alertSettingPage.goToEditAlertSettingPage(softAssert);
        //   noticeIds=getAlertsInAlertSettingListBeforeDelete("DeleteAllAlertSettings","deleteAllAlertbefore");
        deleteAlert.clickOnDeleteIcon();
        captureScreenshot("deletAllPopup.png");
        basePage.waitForSeconds(3);
        if (deviceType.equalsIgnoreCase("ios")) {
            deleteAlert.acceptDeletePopupIos();
        } else {
            deleteAlert.acceptDeletePopup(softAssert);
        }
        try {
            basePage.waitForSeconds(4);
            editAlertSettingPage.clickOnSaveBtn();
            captureScreenshot("alertSettingListAferDeleteAll.png");
        } catch (Exception e) {

        }
        basePage.waitForSeconds(5);
        captureScreenshot("alertSetting_screen_AfterDelete_All.png");
        softAssert.assertTrue(editAlertSettingPage.isAlertNotPresent());
    }

    public void deleteOneInStatusList(SoftAssert softAssert) {
        boolean isAlertPresent = editAlertSettingPage.isAlertPresentInStatusList();
        if (!isAlertPresent) {
            Assert.fail("Alerts are not present in status list");
        }
        try {
            if (deviceType.equalsIgnoreCase("ios")) {
                deleteAlert.clickOnselectToDelete();
                deleteAlert.clickSaveButton();
            }
        } catch (Exception e) {
            // Handle exception or log error
        }
        String timeStampBeforeDelete = deleteAlert.timeStampBeforeDeleteSingleInStatus();
        String pushMssage = deleteAlert.pushMessageBeforeDelete();
        String stockName=deleteAlert.getStockNameBeforeDelete();
        deleteAlert.clickOnselectToDelete();
        basePage.waitForSeconds(3);
        captureScreenshot("clickOnselectToDelete.png");
        deleteAlert.clickOndeleteMinus();
        basePage.waitForSeconds(2);
        captureScreenshot("clickOndeleteMinus.png");
        deleteAlert.acceptPopup();
        basePage.waitForSeconds(3);
        captureScreenshot("acceptPopup.png");
        try {
            if (deleteAlert.waitFordeleteElementToBeVisible(softAssert)) {
                captureScreenshot("afterDeleteInEditAlertstatusList.png");
            }
            deleteAlert.clickSaveButton();
        } catch (Exception ignored) {
        }
        if (editAlertSettingPage.isAlertPresentInStatusList()) {
            deleteAlert.timeStampAfterDeleteSingleInStatus(softAssert, timeStampBeforeDelete, pushMssage,stockName);
            captureScreenshot("afterDeleteStatusList.png");
        }
    }

    public void deleteAllInStatus(SoftAssert softAssert) {
        boolean isAlertPresent = editAlertSettingPage.isAlertPresentInStatusList();
        if (!isAlertPresent) {
            Assert.fail("Alerts are not present in status list");
        }
        captureScreenshot("BeforeDeleteAll.png");
        deleteAlert.clickOnDeleteIconStatus();
        captureScreenshot("ClickOnDelete.png");
        basePage.waitForSeconds(3);
        captureScreenshot("selectAllOption.png");
        deleteAlert.selectAllOption();
        basePage.waitForSeconds(4);
        captureScreenshot("DeleteAllpopup.png");


    }

}
