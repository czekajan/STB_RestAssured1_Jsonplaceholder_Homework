package jsonplaceholderPOSTS;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class jsonplaceholderDELETETest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String POSTS = "posts";

    @Test
    public void jsonplaceholderDELETEPost(){

        Response response = given()
                .pathParam("userId", 5)
                .when()
                .delete(BASE_URL + "/" + POSTS + "/{userId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }

}
