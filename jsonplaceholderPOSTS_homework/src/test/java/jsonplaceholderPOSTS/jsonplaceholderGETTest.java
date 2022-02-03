package jsonplaceholderPOSTS;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class jsonplaceholderGETTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String POSTS = "POSTS";

    @Test
    public void jsonplaceholderReadAllPosts(){

        Response response = given()
                .when()
                .get(BASE_URL + "/" + POSTS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> posts = json.getList("title");
        assertEquals(100, posts.size());

        System.out.println(response.asString());
    }

    @Test
    public void jsonplaceholderReadOnePosts(){

        Response response = given()
                .pathParam("postsID", 5)
                .when()
                .get(BASE_URL + "/" + POSTS + "/{postsID}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("nesciunt quas odio", json.get("title"));
        assertEquals("repudiandae veniam quaerat sunt sed\nalias aut fugiat sit autem sed est\nvoluptatem omnis possimus esse voluptatibus quis\nest aut tenetur dolor neque", json.get("body"));

        System.out.println(response.asString());
    }

    @Test
    public void jsonplaceholderReadPostsWithQueryParams() {

        Response response = given()
                .queryParam("title", "nesciunt quas odio")
                .when()
                .get(BASE_URL + "/" + POSTS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        //assertEquals("nesciunt quas odio", json.get("title"));
        assertEquals("repudiandae veniam quaerat sunt sed\nalias aut fugiat sit autem sed est\nvoluptatem omnis possimus esse voluptatibus quis\nest aut tenetur dolor neque", json.getList("body").get(0));

    }

}
