package tests.reqresapitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.PropertyReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
public class TestPostForCreatingAUser {

    private final static Cookie COOKIE = new Cookie.Builder("__cfduid", "d40d74234c711c9be1ec5e72061bf32e51534405577").
            setHttpOnly(true).setSecured(true).build();

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = PropertyReader.readProperty("baseURL");
    }

    @Ignore
    @Test
    public void testPostForCreatingAUserAndThenCheckItWithObjectMapper() {
//        postNewUserURL

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "todd");
        requestBody.put("job", "manager");

        given().
                cookie(COOKIE).
                contentType(ContentType.JSON).
                params(requestBody).
        when().
                post(PropertyReader.readProperty("postNewUserURL")).
        then().body("$.name", equalTo("Tree"), "$.job", equalTo("manager"));


    }
}
