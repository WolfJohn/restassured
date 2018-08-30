package resources.features.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import resources.features.steps.serenity.SerenitySteps;
import net.thucydides.core.annotations.Steps;

public class StepsDefinition {
    @Steps
    SerenitySteps userSteps;

    @Given("^I want to get details about person with id = (\\d+)$")
    public void first(int id) {
        userSteps.givenSettingUpTheRequestSpecification(id);
    }

    @When("I make the request to get the details")
    public void second(){
        userSteps.whenMakingTheRequest();
    }

    @Then("The user should be (.*) (.+) with id == (\\d+)")
    public void third(String firstName, String lastName, int id){
        userSteps.thenTheSingleUserMustBeReturned(firstName, lastName, id);
    }
}
