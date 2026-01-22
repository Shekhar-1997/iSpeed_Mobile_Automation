package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class ExpiryAlertUtils extends BaseTest {

    private static DemoAPI demoAPI = new DemoAPI();
    private static List<Integer> noticeIds = new ArrayList<>();
    private static String expireDate; // Variable to store expireDate

    public void expiryAlert(int limit) {
        getNoticeIds(limit); // Notice IDs are retrieved first, then expire dates are fetched
        updateData();
    }

    public static void getNoticeIds(int limit) {
        String requestBody = "{\n" +
                "  \"query\": \"select c.notice_id from push_device_info a, push_notice_service_info b, push_notice_set_info c " +
                "where a.branch_cd = b.branch_cd and a.client_cd = b.client_cd " +
                "and a.acc_device_ctrl_no='" +  testData.getaccDeviceCtrlNo() + "' " +
                "and b.notice_id = c.notice_id " +
                "order by c.create_dt desc limit " + limit + "\"\n" +
                "}";

        Response response = demoAPI.getValueFromPushDB(requestBody);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        System.out.println("the Json value: " + jsonPath.getList("data").size());

        if (((int) jsonPath.getList("data").size()) > 0) {
            List<Integer> noticeIdsList = jsonPath.getList("data.notice_id");
            System.out.println("Notice IDs for Expiry Alert: " + noticeIdsList);

            for (Integer noticeId : noticeIdsList) {
                System.out.println("noticeId: " + noticeId);
                noticeIds.add(noticeId);

                // Call getExpireDate with the current noticeId and store the result
               // getExpireDate(noticeId);
            }
        } else {
            System.out.println("No data found in query2 response.");
        }
    }

    public static void updateData() {
        // Convert noticeIds list to a string for use in the SQL query
        String noticeIdsStr = noticeIds.toString().replace("[", "(").replace("]", ")");
        System.out.println("The noticeIds: " + noticeIdsStr);

        // SQL query to update expire_dt with the fetched expireDate for the specified notice_ids
        String requestBody = "{\n" +
                "  \"query\": \"update push_notice_service_info set expire_dt = sysdate() " +
                "where notice_id in " + noticeIdsStr + "\"\n" +
                "}";

        System.out.println("The request body: " + requestBody);
        noticeIds.clear();

        try {
            // Execute the query using the DemoAPI instance
            Response response = demoAPI.getValueFromPushDB(requestBody);
            String responseBody = response.getBody().asString();
            JsonPath jsonPath = new JsonPath(responseBody);

            System.out.println("Response Body: " + responseBody);

        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
