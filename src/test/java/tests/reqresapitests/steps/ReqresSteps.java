package tests.reqresapitests.steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import pojos.reqres.UserData;
import utils.PropertyReader;

public class ReqresSteps {

    private UserData userData;
    private RequestSpecification spec;
    private final Cookie COOKIE = new Cookie.Builder("__cfduid", "d40d74234c711c9be1ec5e72061bf32e51534405577").
            setHttpOnly(true).setSecured(true).build();

    @Step("The id of the person is {id}")
    public void givenSettingUpTheRequestSpecification(int id) {
        spec = RestAssured.given().cookie(COOKIE).baseUri(PropertyReader.readProperty("baseURL")).
                accept(ContentType.JSON).basePath(PropertyReader.readProperty("getSingleUserURL") + id);
    }

    @Step("When we make a request to get the person details")
    public void whenMakingTheRequest() {
        userData = RestAssured.given().spec(spec).when().get().body().as(UserData.class);
    }

    @Step("Then that person should be {firstName} {lastName} with id {id}")
    public void thenTheSingleUserMustBeReturned(int id, String firstName, String lastName, String avatar) {
        assert  userData.data.id.equals(String.valueOf(id)) &&
                userData.data.first_name.equals(firstName) &&
                userData.data.last_name.equals(lastName) &&
                userData.data.avatar.equals(avatar);

        System.out.println(userData);
    }
}
