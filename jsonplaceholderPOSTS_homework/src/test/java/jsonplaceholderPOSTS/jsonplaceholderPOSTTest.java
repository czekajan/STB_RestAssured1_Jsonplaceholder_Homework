package jsonplaceholderPOSTS;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class jsonplaceholderPOSTTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String POSTS = "POSTS";

     String jsonBody = "  {\n" +
             "    \"userId\": 1,\n" +
             "    \"title\": \"what is API?\",\n" +
             "    \"body\": \"An API is a set of programming code that enables data transmission between one software product and another. It also contains the terms of this data exchange.\"\n" +
             "  }";

    @Test
    public void jsonplaceholderCreateNewPost() {

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post(BASE_URL + "/" + POSTS)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("what is API?", json.get("title"));
        assertEquals("An API is a set of programming code that enables data transmission between one software product and another. It also contains the terms of this data exchange.", json.get("body"));

    }

}
