package utils;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import tests.BaseTest;

import static tests.PageOjectManager.*;

public class CreateNotificationTestIndex extends BaseTest {

public WebDriver driver;

    public void createIndexAlert(String stockNameIndex,String alertType ,SoftAssert softAssert,String snapshotName) {

        createAlertUtil.createAllAlert(softAssert,stockNameIndex,"Index",alertType,snapshotName+"price Up and Down",snapshotName+"previousDay UP and Down",true,false);


    }

    public void updateIndexAlert(String stockNameIndex,SoftAssert softAssert,String sheetName,String alertType) {
        createAlertUtil.updateAllAlert(stockNameIndex,softAssert,"Index",sheetName,alertType);


    }


}
