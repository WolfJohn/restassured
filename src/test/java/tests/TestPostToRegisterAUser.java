package tests;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.PropertyReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
public class TestPostToRegisterAUser {

    private final static Cookie COOKIE = new Cookie.Builder("__cfduid", "d40d74234c711c9be1ec5e72061bf32e51534405577").
            setHttpOnly(true).setSecured(true).build();

    @BeforeClass
    public static void setUp() throws IOException {
        RestAssured.baseURI = PropertyReader.readProperty("baseURL");
    }

    @Test
    public void testRegistration() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("email", "tets@email.com");
        map.put("password", "stest1234");

        String body = given().
                params(map).
                cookie(COOKIE).
        when().
                post(PropertyReader.readProperty("postRegisterURL")).
        body().asString();

        System.out.println(body);

    }
}
