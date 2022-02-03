package jsonplaceholderPOSTS;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class jsonplaceholderPUTPATCHTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String POSTS = "posts";


    @Test
    public void jsonplaceholderUpdatePUTPost(){

        JSONObject post = new JSONObject();
        post.put("title", "Description of API");
        post.put("body", "Application Programming Interface");


        Response response = given()
                .pathParam("postsId", 5)
                .contentType("application/json")
                .body(post.toString())
                .when()
                .put(BASE_URL + "/" + POSTS + "/{postsId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

       System.out.println(post.toString());

        JsonPath json = response.jsonPath();
        assertEquals("Description of API", json.get("title"));
        assertEquals("Application Programming Interface", json.get("body"));

    }

    @Test
    public void jsonplaceholderUpdatePATCHPost(){

        JSONObject post = new JSONObject();
        post.put("body", "Application Programming Interface which is a software intermediary that allows two applications to talk to each other");

        Response response = given()
                .pathParam("postsId", 5)
                .contentType("application/json")
                .body(post.toString())
                .when()
                .patch(BASE_URL + "/" + POSTS + "/{postsId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("Application Programming Interface which is a software intermediary that allows two applications to talk to each other", json.getString("body"));

    }

}
