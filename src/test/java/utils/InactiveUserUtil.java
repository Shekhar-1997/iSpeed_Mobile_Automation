package utils;

import tests.BaseTest;

import static tests.PageOjectManager.*;

public class InactiveUserUtil extends BaseTest {
    public void firstUserLogin() {
        login.login(testData.getUsername(), testData.getPassword(),testData.getaccDeviceCtrlNo(),"LoginFromUser1","InactiveUser");
    }

    public void logout(String accountDeviceCntrlNo,String alertType) {
        basePage.waitForSeconds(2);
        menuPage.goToMenuPage();
        boolean value= menuPage.verifyMenuPage();
        if(!value){
            menuPage.goToMenuPage();
        }
        basePage.waitForSeconds(2);
        logoutTest.logout("InactiveUser",alertType);
        inActiveUserDbData("InactiveUser",alertType);


    }

    public void secondUserLogin() {
        login.login(testData.getUsernameTwo(), testData.getPasswordTwo(),testData.getAccDeviceCtrlNoTwo(),"LoginFromUser2","InactiveUser");

    }
}
