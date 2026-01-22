package tests;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.CustomSoftAssert;
import utils.DeleteAllStatusListDB;

import java.io.IOException;

import static tests.PageOjectManager.*;

public class DeleteAlertInStatusList extends BaseTest {
    private CustomSoftAssert softAssert;
    private DeleteAllStatusListDB deleteAllStatusListDB;


    @BeforeMethod
    public void beforeMethod() {
        softAssert = new CustomSoftAssert();
        deleteAllStatusListDB=new DeleteAllStatusListDB();
        basePage.waitForSeconds(2);
        menuPage.gotoAlertStatusList();

    }

   @Test(priority = 1)
    public void deleteOneAlertInStatusList() throws IOException {

        basePage.waitForSeconds(5);
        captureScreenshot("beforeDelete.png");
        if (editAlertSettingPage.isAlertPresentInStatusList()) {
            basePage.waitForSeconds(2);
            deleteAlertUtil.deleteOneInStatusList(softAssert);
            basePage.waitForSeconds(1);

        } else {
            System.out.println("no alerts are present in status list");
        }
        //deleteInStatusList(methodName,"deleteOneAlertInStatusListAfter",false);
        softAssert.assertAll();

       // deleteInSettingsDbData(methodName,alertType);
    }

   @Test(priority = 2)
    public void deleteAllAlertInStatusList() throws IOException {

        if (editAlertSettingPage.isAlertPresentInStatusList()) {
            deleteAlertUtil.deleteAllInStatus(softAssert);

        } else {
            System.out.println("no alerts are present in status list");
        }
        softAssert.assertAll();

    }



    @AfterMethod
    public void afterMethod(ITestResult result) {
        afterMethodSteps(softAssert,result);
    }


}