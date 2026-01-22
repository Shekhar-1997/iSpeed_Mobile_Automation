package tests;

import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.*;

import java.io.IOException;
import java.util.List;

import static tests.PageOjectManager.*;
import static utils.DeleteAlertsDBData.getAlertDbDBDataAfterDelete;
import static utils.DeleteAlertsDBData.getAlertsInAlertSettingListBeforeDelete;

public class DeleteFromSetting extends BaseTest {
    CustomSoftAssert softAssert;
    List<Integer> noticeIds;

    @Feature("Create and Validate Index Alert Nippon")
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        softAssert = new CustomSoftAssert();
        menuPage.goToNotificationSetting();
        alertSettingPage.goToAlertSettingPage();
        basePage.waitForSeconds(3);
        handlePopup.checkIfErrorPopup();
        basePage.waitForSeconds(3);

    }

    @Feature("Delete single and Validate")
    @Test(priority = 1)

    public void deleteSingleAlert() {
        basePage.waitForSeconds(5);
        alertSettingPage.goToEditAlertSettingPage(softAssert);
        if (!editAlertSettingPage.isAlertNotPresent()) {
            // noticeIds=getAlertsInAlertSettingListBeforeDelete("DeleteAlertSettings","deleteOneAlertbefore");
            deleteAlertUtil.deleteOneInAlertSettings(softAssert);
        } else {
            jpAlert.createJPAlert(softAssert);
      //      noticeIds = getAlertsInAlertSettingListBeforeDelete("DeleteAlertSettings", "deleteOneAlertbefore");
            alertSettingPage.goToEditAlertSettingPage(softAssert);
            getAlertDbDBDataAfterDelete("DeleteAlertSettings", "deleteOneAlertafter", false, noticeIds);
            deleteAlertUtil.deleteOneInAlertSettings(softAssert);

        }
     //   getAlertDbDBDataAfterDelete("DeleteAlertSettings", "deleteOneAlertAfter", false, noticeIds);
        softAssert.assertAll();
    }

    @Feature("DeleteAll and Validate")
    @Test(priority = 2)
    public void deleteAllAlert() {
        basePage.waitForSeconds(2);
        captureScreenshot("deletesettingBeforeDelete.png");
        deleteAlertUtil.deleteAllInSetting(softAssert);
        //noticeIds=getAlertsInAlertSettingListBeforeDelete("DeleteAllAlertSettings","deleteAllAlertbefore");
        basePage.waitForSeconds(2);
        //  getAlertDbDBDataAfterDelete("DeleteAllAlertSettings","deleteAllAlertAfter",false,noticeIds);
        softAssert.assertAll();
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        // Attach all captured screenshots to Allure in teardown
       afterMethodSteps(softAssert,result);
    }

}
