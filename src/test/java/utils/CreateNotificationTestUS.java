package utils;

import org.testng.asserts.SoftAssert;
import tests.BaseTest;

import static tests.PageOjectManager.*;


public class CreateNotificationTestUS extends BaseTest {

    public String createUSAlert(SoftAssert softAssert) {
        String USstockName= createAlertUtil.createAllAlert(softAssert,testData.getStockNameUS(),"US","create","US priceUpandDown","US previousDayUpandDown",false,false);

        return USstockName;


    }

    public void updateUSAlert(String USstockName,SoftAssert softAssert,String sheetName,String alertType) {
        createAlertUtil.updateAllAlert(USstockName,softAssert,"US",sheetName,alertType);

    }

}
