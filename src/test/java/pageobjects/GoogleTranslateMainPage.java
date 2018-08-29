package pageobjects;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleTranslateMainPage extends PageObject {

    public GoogleTranslateMainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//textarea[@id='source']")
    WebElement textField;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement button;

    @FindBy(xpath = "//span[@id='result_box']/span")
    WebElement searchResult;

    public GoogleTranslateMainPage openPage(){
        getDriver().get("https://translate.google.com/#auto/ru");
        return this;
    }

    public GoogleTranslateMainPage populateTextField(String text) {
        textField.sendKeys(text);
        return this;
    }

    public void pressTheSearchButton(){
        button.click();
    }

    public String getSearchResultText(){
        return searchResult.getText();
    }
}