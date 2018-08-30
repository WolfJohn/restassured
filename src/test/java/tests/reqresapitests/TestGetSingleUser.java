package tests.reqresapitests;

import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.runner.RunWith;
import utils.PropertyReader;
import pojos.reqres.UserData;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

@RunWith(SerenityRunner.class)
public class TestGetSingleUser {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = PropertyReader.readProperty("baseURL");
    }

    @Test
    public void testWithObjectMapperAndSimpleAssertion() {
        ObjectMapper om = new ObjectMapper();

        String res = get(PropertyReader.readProperty("getSingleUserURL") + "2").asString();

        UserData data = null;
        try {
            data = om.readValue(res, UserData.class);
            assert data.data.id.equals("2") && data.data.last_name.equals("Weaver") &&
                    data.data.first_name.equals("Janet") && data.data.avatar.equals("https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(data);
    }

    @Test
    public void testWithJsonPath() {

        Cookie cookie = new Cookie.Builder("__cfduid", "d40d74234c711c9be1ec5e72061bf32e51534405577").
                setHttpOnly(true).setSecured(true).build();

        given().
                accept(ContentType.JSON).
                cookie(cookie).
        when().
                get(PropertyReader.readProperty("getSingleUserURL") + "2").
        then().
                assertThat().statusCode(200).body("data.first_name", equalTo("Janet")).extract().response();
    }

    @Test
    public void testWithRestAssuredSerializationFeatures() {

        Cookie cookie = new Cookie.Builder("__cfduid", "d40d74234c711c9be1ec5e72061bf32e51534405577").
                setHttpOnly(true).setSecured(true).build();

        UserData data = given().
                accept(ContentType.JSON).
                cookie(cookie).
                when().
                get(PropertyReader.readProperty("getSingleUserURL") + "2").
                body().as(UserData.class);

        assert data.data.id.equals("2") && data.data.last_name.equals("Weaver") &&
                data.data.first_name.equals("Janet") && data.data.avatar.equals("https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg");
    }
}
