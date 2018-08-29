package tests.reqresapitests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import tests.reqresapitests.steps.ReqresSteps;

@RunWith(SerenityRunner.class)
public class TestGetSingleUserWithSteps {

    @Steps
    ReqresSteps steps;

    @Test
    public void userWithIdTwoShouldBeValid() {
        steps.givenSettingUpTheRequestSpecification(2);
        steps.whenMakingTheRequest();
        steps.thenTheSingleUserMustBeReturned(2, "Janet", "Weaver","https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg");
    }
}
