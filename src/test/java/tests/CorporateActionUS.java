package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.CorporateActionAlertUtils;
import utils.CustomSoftAssert;
import utils.DemoAPI;

import java.util.*;

import static tests.PageOjectManager.basePage;
import static tests.PageOjectManager.setCorporationAlert;

public class CorporateActionUS extends BaseTest {

    private static DemoAPI demoAPI = new DemoAPI();
    private static List<String> dscrCds = new ArrayList<>();
    private static CustomSoftAssert softAssert;
    public static String caSeqNo;
    public static String countryCd;
    public static String corporateActionType;
    public static String reutersCd;
    public static String ticker;
    public static String dscrNm;
    public static String exDt;
    public static String announcementDt;
    public static String deleteFlg;
    public static List<String> dscrCdList = new ArrayList<>();
    public static List<String> ticerList;
    public static List<String> announcementDtList;
    public static List<String> corpActionTypeList;
    private static Integer corpActionType;

    @Test(priority = 1)
    public static void corporateActionUS() {
        softAssert = new CustomSoftAssert();
        System.out.println(testData.getCaPrice());
        announcementDtList = new ArrayList<>();
        corpActionTypeList = new ArrayList<>();
        ticerList = new ArrayList<>();
        corporateActions();
    }

    public static void corporateActions() {

        selectUS_CA();
        updateUS_CA(false);

    }

    public static void selectUS_CA() {
        System.out.println("Response Body from query1: *******************************************************************");
        // US corporate actions
        String requestBody = "{\n" +
                "  \"query\": \"select ticker, COUNTRY_CD, CORPORATE_ACTION_TYPE, TO_CHAR(ANNOUNCEMENT_DT,'YYYY-MM-DD HH24:MI:SS') AS ANNOUNCEMENT_DT from ca_web_info where country_cd='US' and CORPORATE_ACTION_TYPE IN ('1001','1002','1003','1010') and DELETE_FLG=0 order by ticker\"\n" +
                "}";

        Response response = demoAPI.getValueFromGetAPI(requestBody);
        String responseBody = response.getBody().asString();
        System.out.println("Response Body from query1:");
        System.out.println(responseBody);

        JsonPath jsonPath = new JsonPath(responseBody);

        List<Map<String, Object>> data = jsonPath.getList("data");
        Set<Integer> seenTypes = new HashSet<>();
        boolean found = false;

        for (Map<String, Object> record : data) {
            Object corpActionTypeObj = record.get("CORPORATE_ACTION_TYPE");
            corpActionType = corpActionTypeObj != null ? (Integer) corpActionTypeObj : null;

            if (corpActionType != null && (corpActionType == 1001 || corpActionType == 1002 || corpActionType == 1003 || corpActionType == 1010) && !seenTypes.contains(corpActionType)) {
                seenTypes.add(corpActionType);
                ticker = (String) record.get("TICKER");
                //System.out.println("Value of ticker:"  +ticker);
                announcementDt = (String) record.get("ANNOUNCEMENT_DT");
                //System.out.println(""+announcementDt+"announcementdate");
                CorporateActionAlertUtils actionAlertUtils = new CorporateActionAlertUtils();
                actionAlertUtils.setCorporationAlert(ticker, String.valueOf(corpActionType), softAssert, "US");

                if (ticker != null && announcementDt != null) {
                    System.out.println(ticker);
                    ticerList.add(ticker);
                    announcementDtList.add(announcementDt);
                    corpActionTypeList.add(corpActionType.toString());
                    found = true;

                    System.out.println("ticker: " + ticker);
                    System.out.println("corpActionType: " + corpActionType);
                    System.out.println("announcementDt: " + announcementDt);
                    // Break the loop after the first occurrence
                    if (seenTypes.size() == 4) { // since we are interested in 4 types only
                        break;
                    }
                }
                //query2
            }
        }

        if (!found) {
            System.out.println("No matching corpActionType found in the response.");
        }
    }

    public static void updateUS_CA(boolean isVerifyAlert) {
        if (ticerList.isEmpty() || announcementDtList.isEmpty() || corpActionTypeList.isEmpty()) {
            System.out.println("No data available for query2.");
            return;
        }
        System.out.println("Start of query2.****************************************");
        // Iterate through all records stored in the lists
        for (int i = 0; i < ticerList.size(); i++) {
            String ticker = ticerList.get(i);
            String corpActionType = corpActionTypeList.get(i);
            String announcementDt = announcementDtList.get(i);

            if (ticker != null && corpActionType != null && announcementDt != null) {
                // Escape the date string for SQL
                String escapedAnnouncementDt = announcementDt.replace("T", " ").replace("Z", "");

                // Properly formatted SQL query string
                String requestBody = "{\n" +
                        "  \"query\": \"UPDATE ca_web_info SET ANNOUNCEMENT_DT = SYSDATE " +
                        "WHERE CORPORATE_ACTION_TYPE = '" + corpActionType + "' " +
                        "AND TICKER = '" + ticker + "' " +
                        "AND ANNOUNCEMENT_DT = TO_DATE('" + escapedAnnouncementDt + "', 'YYYY-MM-DD HH24:MI:SS')\"\n" +
                        "}";

                // Execute the update query and print the response
                Response response = demoAPI.getValueFromGetAPI(requestBody);
                String responseBody = response.getBody().asString();
                System.out.println("Response Body from query2:");
                System.out.println("Updated CA " + responseBody);

                int statusCode = response.getStatusCode();
                if (statusCode == 200) {
                    System.out.println("Update query executed successfully for ticker: " + ticker);
                } else {
                    System.out.println("Failed to execute update query for ticker: " + ticker + ". Status code: " + statusCode);
                }
            } else {
                System.out.println("Incomplete data for record index: " + i);
            }
        }
        if(isVerifyAlert) {
            basePage.waitForSeconds(2);
            Response responseUS = demoAPI.cronJobUS();
            basePage.waitForSeconds(6);
            setCorporationAlert.verifyCorporateAction(ticker, String.valueOf(corpActionType), softAssert);
            System.out.println("value of :" + ticker);
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        softAssert.assertAll();
        // Attach all captured screenshots to Allure in teardown
        afterMethodSteps(softAssert, result);
    }

}
