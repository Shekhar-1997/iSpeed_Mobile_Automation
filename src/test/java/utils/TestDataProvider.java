package utils;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class TestDataProvider {
    @DataProvider(name = "jsonData")
    public static Object[][] jsonData() throws IOException {
        String filePath=System.getProperty("user.dir")+ "/resources/inputData.json";
        TestDataObject[] data = JsonUtils.readJsonFile(filePath, TestDataObject[].class);
        Object[][] testData = new Object[data.length][1];
        for (int i = 0; i < data.length; i++) {
            testData[i][0] = data[i];
        }
        return testData;
    }



}
