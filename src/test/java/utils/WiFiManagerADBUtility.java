package utils;

import org.testng.asserts.SoftAssert;
import tests.BaseTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import static tests.PageOjectManager.*;
import static tests.PageOjectManager.basePage;

public class WiFiManagerADBUtility extends BaseTest {
    // Executes a given adb command and returns its output
    public static String executeCommand(String command) {
        StringBuilder output = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            reader.close();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString().trim();
    }

    // Checks the current WiFi status
    public  String checkWifiStatus() {
        String command = "adb shell dumpsys wifi | grep 'Wi-Fi is'";
        return executeCommand(command);
    }

    // Enables WiFi
    public  void enableWifi() {
        String command = "adb shell svc wifi enable";
        executeCommand(command);


    }

    // Disables WiFi
    public  void disableWifi() {
        String command = "adb shell svc wifi disable";
        executeCommand(command);
    }

    public  List<String> createAlertAndDisableWifi(SoftAssert softAssert) {
        alertSettingPage.goToAlertSettingPage();
        handlePopup.checkIfErrorPopup();
        List<String> data = createAlertUtil.createJPpriceDownalertInternetOff(softAssert, testData.getJpStockpriceUp());
        disableWifi();
        basePage.waitForSeconds(8);
        new BaseTest().dbEvidenceCapture("Set", "setbeforeTrigger", 1, false);
        return data;
    }


}
