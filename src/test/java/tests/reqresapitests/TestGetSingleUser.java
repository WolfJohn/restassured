package tests.reqresapitests;

import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.runner.RunWith;
import utils.PropertyReader;
import pojos.reqres.Data;
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
    public static void setUp() throws IOException {
        RestAssured.baseURI = PropertyReader.readProperty("baseURL");
    }

    @Test
    public void testWithObjectMapperAndSimpleAssertion() throws IOException {
        ObjectMapper om = new ObjectMapper();

        String res = get(PropertyReader.readProperty("getSingleUserURL") + "2").asString();

        Data data = om.readValue(res, Data.class);

        assert data.data.id.equals("2") && data.data.last_name.equals("Weaver") &&
                data.data.first_name.equals("Janet") && data.data.avatar.equals("https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg");

        System.out.println(data);
    }

    @Test
    public void testWithJsonPath() throws IOException {

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
    public void testWithRestAssuredSerializationFeatures() throws IOException {

        Cookie cookie = new Cookie.Builder("__cfduid", "d40d74234c711c9be1ec5e72061bf32e51534405577").
                setHttpOnly(true).setSecured(true).build();

        Data data = given().
                accept(ContentType.JSON).
                cookie(cookie).
                when().
                get(PropertyReader.readProperty("getSingleUserURL") + "2").
                body().as(Data.class);

        assert data.data.id.equals("2") && data.data.last_name.equals("Weaver") &&
                data.data.first_name.equals("Janet") && data.data.avatar.equals("https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg");
    }
}
