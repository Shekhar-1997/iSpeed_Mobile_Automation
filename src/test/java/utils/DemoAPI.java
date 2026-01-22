package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.Response;
import tests.BaseTest;
import utils.TestDataObject;

import static tests.PageOjectManager.basePage;

public class DemoAPI extends BaseTest {

    public Response getValueFromPushDB(String param) {
        String endpoinPushdb = "http://localhost:9090/api/pushadmin/query";//testData.getEndpoinPushdb();

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Host", "db-access.rakuten-sec.co.jp")
                .body(param)
                .post(endpoinPushdb);

        // Log the request and response details
        System.out.println("Request URL: " + endpoinPushdb);
        return response;
    }

    public Response getValueFromGetAPI(String param) {

        String endpointCoreDB = testData.getEndpointCoreDB();
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Host", "db-access.rakuten-sec.co.jp")
                .body(param)
                .post(endpointCoreDB);

        // Log the request and response details
        System.out.println("Request URL: " + endpointCoreDB);
        return response;
    }

    //corporate Action cronjob JP
    public Response cronJobsJP() {
        String CACronJobsJP = testData.getCACronJobsJP();
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Host", "db-access.rakuten-sec.co.jp")
                .post(CACronJobsJP);

        // Log the request and response details
        System.out.println("Request URL: " + CACronJobsJP);
        return response;
    }

    //corporate Action cronjob US
    public Response cronJobUS() {
        String CACronJobsUS = testData.getCACronJobsUS();
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Host", "db-access.rakuten-sec.co.jp")
                .post(CACronJobsUS);

        // Log the request and response details
        System.out.println("Request URL: " + CACronJobsUS);
        return response;
    }

    //Account cancelation cronjob
    public Response cronJobAccCancelation() {
        String CronJobAccCancel = testData.getCronJobAccCancel();

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Host", "db-access.rakuten-sec.co.jp")
                .post(CronJobAccCancel);
        basePage.waitForSeconds(50);
        // Log the request and response details
        System.out.println("Request URL: " + CronJobAccCancel);
        return response;
    }
    //margin-alert-job-stg

    public Response cronJobMarginAlert() {
        String CronJobMargin = testData.getCronJobMargin();
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Host", "db-access.rakuten-sec.co.jp")
                .post(CronJobMargin);

        // Log the request and response details
        System.out.println("Request URL: " + CronJobMargin);
        return response;
    }

    public Response cronJobLosscutAlert() {
        String CronJobLosscut = testData.getCronJobLosscut();
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Host", "db-access.rakuten-sec.co.jp")
                .post(CronJobLosscut);

        // Log the request and response details
        System.out.println("Request URL: " + CronJobLosscut);

        return response;
    }
}
