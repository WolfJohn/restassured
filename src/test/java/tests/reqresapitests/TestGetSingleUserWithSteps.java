package tests.reqresapitests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import tests.reqresapitests.steps.ReqresSteps;
import java.io.IOException;

@RunWith(SerenityRunner.class)
public class TestGetSingleUserWithSteps {

    Logger logger = Logger
    @Steps
    ReqresSteps steps;

    @Test
    public void userWithIdTwoShouldBeValid() throws IOException {
        steps.givenSettingUpTheRequestSpecification();
        steps.whenMakingTheRequest();
        steps.thenTheSingleUserMustBeReturned();
    }
}
