package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class UserTests {

    @Test
    public void getSingleUserPrintResponse() {
        RestAssured.baseURI = "https://reqres.in";

        Response response = given()
                .when()
                .get("/api/users/2");

        System.out.println("Response: " + response.prettyPrint()); // Neat formatting
        System.out.println("Status Code: " + response.getStatusCode());
    }


    //  @Test
    public void createUser() {
        given()
                .baseUri("https://reqres.in")
                .header("Content-Type", "application/json")
                .body("{\"name\":\"Shekhar\", \"job\":\"QA Engineer\"}")
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Shekhar"))
                .body("job", equalTo("QA Engineer"));
    }
}
