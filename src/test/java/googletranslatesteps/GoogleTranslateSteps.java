package googletranslatesteps;

import com.sun.istack.internal.NotNull;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.GoogleTranslateMainPage;

import javax.annotation.Nullable;

public class GoogleTranslateSteps {

    private WebDriver driver;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    GoogleTranslateMainPage mainPage = new GoogleTranslateMainPage(driver);

    @Step("I enter the google translate page")
    public GoogleTranslateSteps openGoogleTranslatePage(){
        mainPage.openPage();
        return this;
    }

    @Step
    public GoogleTranslateSteps populateTheTextField(String text){
        mainPage.populateTextField(text);
        return this;
    }

    @Step
    public GoogleTranslateSteps performTheSearch(){
        mainPage.pressTheSearchButton();
        return this;
    }

    @Step("When result should match '{text}'")
    public void compareResult(String text){
        String result = mainPage.getSearchResultText();
        assert text.equals(result);
    }
}
