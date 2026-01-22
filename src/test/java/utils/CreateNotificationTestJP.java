package utils;

import io.qameta.allure.Description;
import org.testng.asserts.SoftAssert;
import tests.BaseTest;

import static tests.PageOjectManager.*;

public class CreateNotificationTestJP extends BaseTest {


    public String createJPAlert(SoftAssert softAssert) {
        String JPstockName= createAlertUtil.createAllAlert(softAssert,testData.getStockCode(),"JP","create","JPpriceUpandDown","JPpreviousDayUpandDown",false,false);
//        editAlertSettingPage.verifyAlertCreation(JPstockName, softAssert);
        return JPstockName;
    }

    @Description("Verify values are updated")
    public void updateJPAlert(String stockName, SoftAssert softAssert, String sheetName, String alertType) {

        createAlertUtil.updateAllAlert(stockName,softAssert,"JP",sheetName,alertType);

        }


}
