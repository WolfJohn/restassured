package tests.reqresapitests.steps;

import static io.restassured.RestAssured.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import pojos.reqres.Data;
import utils.PropertyReader;

import java.io.IOException;

public class ReqresSteps {

    private Cookie cookie;
    private RequestSpecification spec;
    private Data data;

    public void setUp() throws IOException {
        cookie = new Cookie.Builder("__cfduid", "d40d74234c711c9be1ec5e72061bf32e51534405577").
                setHttpOnly(true).setSecured(true).build();
    }


    @Step
    public void givenSettingUpTheRequestSpecification() throws IOException {
        setUp();
        spec = RestAssured.given().cookie(cookie).baseUri(PropertyReader.readProperty("baseURL")).
                accept(ContentType.JSON).basePath(PropertyReader.readProperty("getSingleUserURL") + "2");
    }

    @Step
    public void whenMakingTheRequest() {
        data = RestAssured.given().spec(spec).when().get().body().as(Data.class);
    }

    @Step
    public void thenTheSingleUserMustBeReturned(){
        assert data.data.id.equals("2") && data.data.last_name.equals("Weaver") &&
                data.data.first_name.equals("Janet") && data.data.avatar.equals("https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg");
        System.out.println(data);
    }
}
