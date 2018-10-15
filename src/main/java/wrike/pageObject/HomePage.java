package wrike.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import wrike.base.BasePage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static wrike.enums.Links.*;

public class HomePage extends BasePage {

    @FindBy(css = "title")
    private WebElement title;

    @FindBy(css = ".wg-header__cell .wg-btn--green")
    private WebElement getStartedButton;

    @FindBy(css = ".wg-form.modal-form-trial__form")
    private WebElement trialForm;

    @FindBy(css = ".modal-form-trial__input")
    private WebElement emailField;

    @FindBy(css = ".modal-form-trial__submit")
    private WebElement createAccountButton;

    @Step("Open home page")
    public void open(WebDriver driver) {

        driver.navigate().to(WRIKE_HOME_PAGE.text);
        assertEquals("Page title is not correct", "Your online project management software - Wrike", driver.getTitle());

    }

    @Step("Click \"Get started for free\" button near \"Login\" button")
    public void startedButtonClick() {

        getStartedButton.click();
        assertTrue("Trial form is not displayed", trialForm.isDisplayed());

    }

    @Step("Fill in the email field")
    public void fillEmailField(String email) {

        emailField.sendKeys(email + EMAIL_POSTFIX.text);

    }

    @Step("Click on \"Create my Wrike account\" button")
    public void createAccountButtonClick(WebDriver driver) {

        createAccountButton.click();
        waitUntilElementDisappearsOrAppears(driver, ".modal-form-trial__input", false);
        assertEquals("Page title is not correct", WRIKE_RESEND_PAGE.text, driver.getCurrentUrl());

    }

}
