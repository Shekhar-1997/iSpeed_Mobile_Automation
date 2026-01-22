package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import utils.DemoAPI;

import java.io.FileOutputStream;
import java.io.IOException;

import static tests.PageOjectManager.keepAliveUtil;

public class KeepAlive extends BaseTest {


    @Test
    public void keepAlive() throws IOException {
        keepAliveUtil.KeepAlivequery();
    }

}
