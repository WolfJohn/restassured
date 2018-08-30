package tests.reqresapitests;

import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import static io.restassured.RestAssured.*;

@RunWith(SerenityRunner.class)
public class TestWithRestAssured {

    @Test
    public void verifyThatResponseCodeIsOK(){
        Response response =
//                need this only at home
//                given().proxy("127.0.0.1", 8888).
                expect().statusCode(200).
                when().get("http://services.groupkt.com/country/get/iso2code/GB");

        response.headers().forEach(h -> System.out.println(h.getName() + " = " + h.getValue()));
    }
}
