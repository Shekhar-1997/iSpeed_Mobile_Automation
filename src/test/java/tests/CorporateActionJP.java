package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.CorporateActionAlertUtils;
import utils.CustomSoftAssert;
import utils.DemoAPI;

import java.io.IOException;
import java.util.*;

import static tests.PageOjectManager.*;

/**
 * Test class for validating Corporate Action Alerts in the JP (Japan) market.
 * This test fetches and updates data related to delisting, trading unit changes,
 * capital changes, and verifies corresponding alerts.
 */
public class CorporateActionJP extends BaseTest {

    private static DemoAPI demoAPI = new DemoAPI();

    /** Holds DSCR_CD values from delist/trading unit query. */
    public static List<String> dscrCdList1;

    /** Holds CA_DT values (corporate action date) from delist/trading unit query. */
    public static List<String> caDtList;

    private static CustomSoftAssert softAssert;

    /** Holds CA_CODE values from delist/trading unit query. */
    public static List<String> caCdList;

    /** Holds DSCR_CD values from capital change query. */
    public static List<String> dscrCdList = new ArrayList<>();

    /** Holds CAPITAL_CHANGE_DT values from capital change query. */
    public static List<String> capitalChangeDtList = new ArrayList<>();

    /** Holds CAPITAL_CHANGE_TYPE_CD values from capital change query. */
    public static List<Integer> capitalChangeTypeCdList = new ArrayList<>();

    public String trimDc;
    public String caCd;
    public String trimDc1;
    public Integer capitalChangeTypeCd;

    /**
     * Main test method for validating corporate action alerts in JP market.
     * This includes delisting, trading unit changes, and capital changes.
     */
    @Test(priority = 1)
    public void corporateActionJP() throws IOException {
        softAssert = new CustomSoftAssert();
        System.out.println(testData.getCaPrice());

        caDtList = new ArrayList<>();
        caCdList = new ArrayList<>();
        dscrCdList1 = new ArrayList<>();

        selectCA_Delist_TradingUnit();
        updateCA_Delist_TradingUnit(false);
        selectCA_Merge_Split_Capital();
        updateCA_Merge_Split_Capital(false);

        softAssert.assertAll();
    }

    /**
     * Fetches delist and trading unit changes for JP market via SQL API call,
     * filters based on CA_CODE (003, 102), and triggers alert creation.
     */
    public void selectCA_Delist_TradingUnit() {
        System.out.println("Response Body from selectCA_Delist_TradingUnit: *******************************************************************");

        String requestBody = "{\n" +
                "  \"query\": \"select dscr_cd, dscr_nm, CA_CODE, TO_CHAR(CA_DT,'YYYY-MM-DD HH24:MI:SS') AS CA_DT from DM_STOCK_CA_PLAN_FRONT where MKT_CD IN (1,3) and CA_CODE in ('003','102') and DATA_SUB_NO = '1' and DEL_FLG = '0' order by ca_dt desc\"\n" +
                "}";

        Response response = demoAPI.getValueFromGetAPI(requestBody);
        String responseBody = response.getBody().asString();
        System.out.println("Response Body from selectCA_Delist_TradingUnit:");
        System.out.println(responseBody);

        JsonPath jsonPath = new JsonPath(responseBody);
        List<Map<String, Object>> data = jsonPath.getList("data");
        Set<String> seenTypes = new HashSet<>();
        boolean found = false;

        for (Map<String, Object> record : data) {
            caCd = (String) record.get("CA_CODE");
            if ((caCd.equals("003") || caCd.equals("102")) && !seenTypes.contains(caCd)) {
                seenTypes.add(caCd);
                String dscrCd = (String) record.get("DSCR_CD");
                trimDc = setCorporationAlert.trimLastDigit(dscrCd);
                String caDt = (String) record.get("CA_DT");
                setCorporationAlert.setCorporationAlert(trimDc, caCd, softAssert, "JP");

                if (dscrCd != null && caDt != null && caCd != null) {
                    dscrCdList1.add(dscrCd);
                    caDtList.add(caDt);
                    caCdList.add(caCd);
                    found = true;

                    System.out.println("dscr_cd: " + dscrCd);
                    System.out.println("caCd: " + caCd);
                    System.out.println("caDt: " + caDt);
                }
            }
        }

        if (!found) {
            System.out.println("No matching caCd found in the response.");
        }
    }

    /**
     * Updates CA_DT field of delist/trading unit records to sysdate for re-verification purposes.
     *
     * @param isVerifyAlert whether to verify alert generation after update.
     */
    public void updateCA_Delist_TradingUnit(boolean isVerifyAlert) {
        if (dscrCdList1.isEmpty() || caDtList.isEmpty() || caCdList.isEmpty()) {
            System.out.println("No data available for query2.");
            return;
        }

        System.out.println("Start of updateCA_Delist_TradingUnit.****************************************");

        for (int i = 0; i < dscrCdList1.size(); i++) {
            String dscrCd = dscrCdList1.get(i);
            String caDt = caDtList.get(i);
            String caCd = caCdList.get(i);

            if (dscrCd != null && caDt != null && caCd != null) {
                String requestBody = "{\n" +
                        "  \"query\": \"UPDATE DM_STOCK_CA_PLAN_FRONT " +
                        "SET CA_DT = sysdate " +
                        "WHERE DSCR_CD = '" + dscrCd + "' " +
                        "AND CA_DT = to_date('" + caDt + "', 'yyyy-mm-dd hh24:mi:ss') " +
                        "AND CA_CODE = '" + caCd + "' " +
                        "AND DATA_SUB_NO = 1\"\n" +
                        "}";

                Response response = demoAPI.getValueFromGetAPI(requestBody);
                String responseBody = response.getBody().asString();
                System.out.println("Response Body from updateCA_Delist_TradingUnit:");
                System.out.println(responseBody);

                int statusCode = response.getStatusCode();
                if (statusCode == 200) {
                    System.out.println("Update query executed successfully for DSCR_CD: " + dscrCd);
                } else {
                    System.out.println("Failed to execute update query for DSCR_CD: " + dscrCd + ". Status code: " + statusCode);
                }
            }
        }

        if (isVerifyAlert) {
            basePage.waitForSeconds(3);
            setCorporationAlert.verifyCorporateAction(trimDc, caCd, softAssert);
        }
    }

    /**
     * Fetches records related to capital changes in JP market and stores distinct change types
     * (1, 3, 12, 13) for alert verification.
     */
    public void selectCA_Merge_Split_Capital() throws IOException {
        System.out.println("Start of selectCA_Merge_Split_Capital: *******************************************************************");

        String requestBody = "{\n" +
                "  \"query\": \"select dscr_cd, dscr_nm, CAPITAL_CHANGE_TYPE_CD, DATA_SUB_NO, TO_CHAR(CAPITAL_CHANGE_DT, 'YYYY-MM-DD HH24:MI:SS') AS CAPITAL_CHANGE_DT FROM DM_CAP_CHG_FRONT WHERE CAPITAL_CHANGE_TYPE_CD IN (1, 3, 12, 13) AND DEL_FLG = 0 order by CAPITAL_CHANGE_DT desc\"\n" +
                "}";

        Response response = demoAPI.getValueFromGetAPI(requestBody);
        String responseBody = response.getBody().asString();
        System.out.println("Response Body from selectCA_Merge_Split_Capital:");
        System.out.println(responseBody);

        JsonPath jsonPath = new JsonPath(responseBody);
        List<Map<String, Object>> data = jsonPath.getList("data");
        Set<Integer> seenTypes = new HashSet<>();
        boolean found = false;

        for (Map<String, Object> record : data) {
            capitalChangeTypeCd = (Integer) record.get("CAPITAL_CHANGE_TYPE_CD");
            if ((capitalChangeTypeCd == 1 || capitalChangeTypeCd == 3 || capitalChangeTypeCd == 12 || capitalChangeTypeCd == 13)
                    && !seenTypes.contains(capitalChangeTypeCd)) {
                seenTypes.add(capitalChangeTypeCd);
                String dscrCd = (String) record.get("DSCR_CD");
                trimDc1 = setCorporationAlert.trimLastDigit(dscrCd);
                String capitalChangeDt = (String) record.get("CAPITAL_CHANGE_DT");
                setCorporationAlert.setCorporationAlert(trimDc1, String.valueOf(capitalChangeTypeCd), softAssert, "JP");

                if (dscrCd != null && capitalChangeDt != null && capitalChangeTypeCd != null) {
                    dscrCdList.add(dscrCd);
                    capitalChangeDtList.add(capitalChangeDt);
                    capitalChangeTypeCdList.add(capitalChangeTypeCd);
                    found = true;

                    System.out.println("dscr_cd: " + dscrCd);
                    System.out.println("capital_change_type_cd: " + capitalChangeTypeCd);
                    System.out.println("capital_change_dt: " + capitalChangeDt);
                }
            }
        }

        if (!found) {
            System.out.println("No matching capital_change_type_cd found in the response.");
        }
    }

    /**
     * Updates CAPITAL_CHANGE_DT to current timestamp in the database and optionally verifies alert.
     *
     * @param isVerifyAlert if true, performs alert verification after update
     */
    public void updateCA_Merge_Split_Capital(boolean isVerifyAlert) throws IOException {
        if (dscrCdList.isEmpty() || capitalChangeDtList.isEmpty() || capitalChangeTypeCdList.isEmpty()) {
            System.out.println("No data available for query6.");
            return;
        }

        System.out.println("Start of query6.****************************************");

        for (int i = 0; i < dscrCdList.size(); i++) {
            String dscrCd = dscrCdList.get(i);
            String capitalChangeDt = capitalChangeDtList.get(i);
            Integer capitalChangeTypeCd = capitalChangeTypeCdList.get(i);

            if (dscrCd != null && capitalChangeDt != null && capitalChangeTypeCd != null) {
                String requestBody = "{\n" +
                        "  \"query\": \"UPDATE DM_CAP_CHG_FRONT\\n" +
                        "SET CAPITAL_CHANGE_DT = sysdate\\n" +
                        "WHERE DSCR_CD = '" + dscrCd + "'\\n" +
                        "AND CAPITAL_CHANGE_DT = to_date('" + capitalChangeDt + "', 'yyyy/mm/dd hh24:mi:ss')\\n" +
                        "AND CAPITAL_CHANGE_TYPE_CD = '" + capitalChangeTypeCd + "'\"\n" +
                        "}";

                Response response = demoAPI.getValueFromGetAPI(requestBody);
                String responseBody = response.getBody().asString();
                System.out.println("Response Body from query6:");
                System.out.println(responseBody);

                int statusCode = response.getStatusCode();
                if (statusCode == 200) {
                    System.out.println("Update query executed successfully for DSCR_CD: " + dscrCd);
                } else {
                    System.out.println("Failed to execute update query for DSCR_CD: " + dscrCd + ". Status code: " + statusCode);
                }
            }
        }

        if (isVerifyAlert) {
            basePage.waitForSeconds(6);
            Response responseJP1 = demoAPI.cronJobsJP();
            basePage.waitForSeconds(6);
            setCorporationAlert.verifyCorporateAction(trimDc1, String.valueOf(capitalChangeTypeCd), softAssert);
        }
    }

    /**
     * Hook that runs after every test method to collect screenshots and attach them to Allure reports.
     *
     * @param result the ITestResult object from TestNG
     */
    @AfterMethod
    public void afterMethod(ITestResult result) {
        afterMethodSteps(softAssert, result);
    }
}
