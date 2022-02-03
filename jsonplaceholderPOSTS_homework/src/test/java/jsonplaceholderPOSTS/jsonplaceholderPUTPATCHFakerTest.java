package jsonplaceholderPOSTS;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class jsonplaceholderPUTPATCHFakerTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String POSTS = "posts";

    private static Faker faker;
    private String fakeTitle;
    private String fakeBody;

    @BeforeAll
    public static void beforeAll() {
        faker = new Faker();
    }

    @BeforeEach
    public void beforeEach(){
        fakeTitle = faker.lorem().word();
        fakeBody = faker.lorem().sentence();
    }


    @Test
    public void jsonplaceholderUpdatePUTPost(){

        JSONObject post = new JSONObject();
        post.put("title", fakeTitle);
        post.put("body", fakeBody);


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
        assertEquals(fakeTitle, json.get("title"));
        assertEquals(fakeBody, json.get("body"));

    }

    @Test
    public void jsonplaceholderUpdatePATCHPost(){

        JSONObject post = new JSONObject();
        post.put("body", fakeBody);

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
        assertEquals(fakeBody, json.getString("body"));

    }

}
