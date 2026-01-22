package utils;

import org.testng.ITestResult;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class CustomSoftAssert extends SoftAssert{

BaseTest baseTest=new BaseTest();
    private  List<byte[]> failedScreenshots = new ArrayList<>();
@Override
public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
    super.onAssertFailure(assertCommand, ex);
    // Capture screenshot on assertion failure using the method name from the assertCommand
    String methodName;  // getTestName() should return the method name
    methodName = String.valueOf(assertCommand.getClass().getEnclosingMethod());
   byte[] screenshot = baseTest.captureScreenshotAsByte(methodName + "_failure.png");
    // Store the screenshot in the list
    failedScreenshots.add(screenshot);

}
    public List<byte[]> getFailedScreenshots() {
        return failedScreenshots;
    }

}
