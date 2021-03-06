package resources.features;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.Ignore;
import org.junit.runner.RunWith;

@Ignore(value = "ignored cucumber tests")
@CucumberOptions(features="src/test/java/resources/features/get_single_user_by_id.feature",
        tags = {"@first or @second"})
@RunWith(CucumberWithSerenity.class)
public class TestGetSingleUserWithCucumber {}