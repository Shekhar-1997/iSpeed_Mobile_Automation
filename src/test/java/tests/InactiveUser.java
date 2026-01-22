package tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.CustomSoftAssert;
import utils.DeviceRegistationLogin;
import utils.PushDBAPICreateUpdate;

import static tests.PageOjectManager.*;

public class InactiveUser extends BaseTest {
    /*steps:
    Login with user 1
    set alert
    take DB evidence(checkpoint:last login kbn should be 0 for user 1)
    Logout user1
    Login user2
    set alert
    Take DB evidence(checkpoint:last login kbn should be 1 for user 1)
    */
    CustomSoftAssert softAssert;

    @Test
    public void checkInactiveUser() {
        softAssert=new CustomSoftAssert();
        inactiveUser.logout(testData.getaccDeviceCtrlNo(),"LogoutFromUser1");
        inactiveUser.firstUserLogin();
        inactiveUser.logout(testData.getaccDeviceCtrlNo(),"LogoutFromUser1");
        inactiveUser.secondUserLogin();
        basePage.waitForSeconds(3);
        inactiveUser.logout(testData.getAccDeviceCtrlNoTwo(),"LogoutFromUser2");
    }


    @AfterMethod
    public void afterMethod(ITestResult result) {
        afterMethodSteps(softAssert,result);
    }

}
