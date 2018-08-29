package googletranslatetests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WhenPageOpens;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import googletranslatesteps.GoogleTranslateSteps;


@RunWith(SerenityRunner.class)
public class GoogleTranslateTests {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @WhenPageOpens
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    @Managed
    WebDriver driver;

    @Steps
    GoogleTranslateSteps steps;

//    @Ignore
    @Test
    public void verifyIfTheTranslatorWorksCorrectly(){
        steps.openGoogleTranslatePage().populateTheTextField("edit").performTheSearch().compareResult("редактировать");
    }
}
