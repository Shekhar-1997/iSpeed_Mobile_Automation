package runner;

import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    static TestNG testNg;

    public static void main(String[] args) {
        // Create an instance of TestNG
        testNg = new TestNG();
        String path=System.getProperty("user.dir")+ "/resources/testCases.xml";
        List<String> xmlList=new ArrayList<String>();
        xmlList.add(path);
        // Set the TestNG XML file that contains the suite and test definitions
        testNg.setTestSuites(xmlList);

        // Run the tests as specified in the XML
        testNg.run();
    }


}
