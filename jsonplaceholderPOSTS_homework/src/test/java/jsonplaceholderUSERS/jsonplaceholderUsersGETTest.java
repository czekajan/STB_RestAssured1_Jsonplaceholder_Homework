package jsonplaceholderUSERS;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class jsonplaceholderUsersGETTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String USERS = "users";

    @Test
    public void jsonplaceholderCheckEmail(){

        Response response = given()
                .when()
                .get(BASE_URL + "/" + USERS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> emails = json.getList("email");
        List<String> emailEndWithPL = emails.stream()
            .filter(email -> email.endsWith(".pl"))
            .collect(Collectors.toList());
        Assertions.assertEquals(0, emailEndWithPL.size());

    }

}
