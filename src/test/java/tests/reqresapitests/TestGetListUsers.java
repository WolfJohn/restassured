package tests.reqresapitests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import pojos.reqres.UsersPage;
import utils.PropertyReader;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@RunWith(SerenityRunner.class)
public class TestGetListUsers {

    private final static Cookie COOKIE = new Cookie.Builder("__cfduid", "d40d74234c711c9be1ec5e72061bf32e51534405577").
            setHttpOnly(true).setSecured(true).build();

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = PropertyReader.readProperty("baseURL");
    }

    @Test
    public void getListOfUsersAndAssertWithObjectMapper() {


        String body =
        given().
                accept(ContentType.JSON).
                cookie(COOKIE).
        when().
                get(PropertyReader.readProperty("getListUsersURL") + "=4").
        body().asString();

        ObjectMapper om = new ObjectMapper();

        try {
            UsersPage page;
            page = om.readValue(body, UsersPage.class);
            assert page.page == 4 && page.per_page == 3 && page.total == 12 &&
                    page.total_pages == 4 && page.data.size() == 3;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getListOfUsersAndAssertWithJsonPath() {
        given().
                accept(ContentType.JSON).
                cookie(COOKIE).
        when().
                get(PropertyReader.readProperty("getListUsersURL") + "=4").
        then().body("page", equalTo(4)).and().
                body("per_page", equalTo(3)).and().
                body("total", equalTo(12)).and().
                body("total_pages", equalTo(4)).and().
                body("data.id.collect{it -> Integer.parseInt(\"${it}\")}", contains(10, 11, 12)).and().
                body("data.first_name", containsInAnyOrder("Byron", "George", "Rachel")).and().
                body("data.last_name", containsInAnyOrder("Edwards", "Fields", "Howell")).and().
                body("data.avatar", hasSize(3));
    }
}
