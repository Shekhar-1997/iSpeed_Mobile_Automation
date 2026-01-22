package utils;
 
import java.io.BufferedReader;

import java.io.InputStreamReader;
 
public class DetectMobileOS {
 
    public String[] deviceType() {

        String[] deviceInfo = detectMobileOS();

        String deviceOS = deviceInfo[0]; // Android or iOS

        String deviceName = deviceInfo[1]; // Device name
 
        return deviceInfo;

    }

 
    public static String[] detectMobileOS() {

        String osType = "Unknown";

        String deviceName = "Unknown";
 
        try {

            // Check if an Android device is connected using adb

            Process adbProcess = Runtime.getRuntime().exec("adb devices");

            BufferedReader adbReader = new BufferedReader(new InputStreamReader(adbProcess.getInputStream()));

            String adbOutput;

            boolean androidDeviceConnected = false;
 
            // Read the output and ignore the header

            adbReader.readLine(); // Skip the first line

            while ((adbOutput = adbReader.readLine()) != null) {

                if (adbOutput.contains("device") && !adbOutput.contains("offline") && !adbOutput.contains("unauthorized")) {

                    androidDeviceConnected = true;

                    // Extract the device name

                    String originaDdeviceName = adbOutput.split(" ")[0]; // Assuming the device ID is the first part of the output
                     deviceName = originaDdeviceName.replaceAll("\t\\S+", "");


                    osType = "Android";

                    break;

                }

            }

            adbProcess.waitFor();
 
            if (androidDeviceConnected) {

                return new String[]{osType, deviceName};

            }
 
            // Check if an iOS device is connected using ideviceinfo

            Process iosProcess = Runtime.getRuntime().exec("/opt/homebrew/bin/ideviceinfo -s DeviceName");

            BufferedReader iosReader = new BufferedReader(new InputStreamReader(iosProcess.getInputStream()));

            String iosOutput;

            boolean iosDeviceConnected = false;
 
            while ((iosOutput = iosReader.readLine()) != null) {

                if (!iosOutput.isEmpty()) {

                    iosDeviceConnected = true;

                    deviceName = iosOutput; // The device name should come from ideviceinfo

                    osType = "iOS";

                    break;

                }

            }

            iosProcess.waitFor();
 
            if (iosDeviceConnected) {

                return new String[]{osType, deviceName};

            }

        } catch (Exception e) {

            e.printStackTrace();

        }
 
        return new String[]{osType, deviceName}; // Return the current state

    }
 
}
 