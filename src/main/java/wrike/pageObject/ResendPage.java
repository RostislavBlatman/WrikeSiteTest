package wrike.pageObject;


import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import wrike.base.BasePage;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static wrike.enums.Links.TWITTER_ICON;
import static wrike.enums.Links.WRIKE_IN_TWITTER;


public class ResendPage extends BasePage {

    private final int TIMEOUT = 10;

    @FindBy(css = ".RveJvd")
    private WebElement noSurveyButton;

    @FindBy(css = "body > c-wiz > div > div > div:nth-child(2) > div.Pv7Rnc > span:nth-child(1) > div")
    private List<WebElement> surveyField;

    @FindBy(css = "div[data-code = 'interest_in_solution'] > label > button")
    private List<WebElement> interestInSolutionButton;

    @FindBy(css = "div[data-code = 'team_members'] > label > button")
    private List<WebElement> teamMembersButtons;

    @FindBy(css = "div[data-code = 'primary_business'] > label > button")
    private List<WebElement> primaryBusinessButtons;

    @FindBy(css = ".switch__input")
    private WebElement fieldInOtherButton;

    @FindBy(css = ".survey-form > button")
    private WebElement submitButton;

    @FindBy(css = ".survey-success")
    private WebElement surveySuccessField;

    @FindBy(css = ".wg-cell--order-1 > p > button")
    private WebElement resentEmailButton;

    @FindBy(css = "li[class = 'wg-footer__social-item']:nth-child(1) > a")
    private WebElement twitterButton;

    @FindBy(css = ".wg-footer__social-block > div > ul > li:nth-child(1) > a > svg > use")
    private WebElement twitterIcon;



    @Step("Refuse to send mailing survey")
    public void refusalOfEmailSurvey(WebDriver driver) {
        driver.switchTo().frame(1);
        waitUntilElementDisappearsOrAppears(driver,
                "div.Pv7Rnc > span:nth-child(1) > div", true);
        noSurveyButton.click();
        assertEquals("Survey field is still visible", 0, surveyField.size());

    }

    @Step("Fill in the Q&A section at the left part of page with random generated answers and check")
    public void randomAnswersOnSurveyAndCheckSubmitted(WebDriver driver) {
        Random rand = new Random();
        interestInSolutionButton.get(rand.nextInt(interestInSolutionButton.size())).click();
        teamMembersButtons.get(rand.nextInt(teamMembersButtons.size())).click();
        WebElement primaryBusinessButton = primaryBusinessButtons.get(rand.nextInt(primaryBusinessButtons.size()));
        if (primaryBusinessButton.getAttribute("innerHTML").contains("Other")) {
            primaryBusinessButton.click();
            fieldInOtherButton.sendKeys("la-la-la");
        } else {
            primaryBusinessButton.click();
        }
        assertTrue("Submit button not available", submitButton.isEnabled());
        submitButton.click();
        waitUntilElementDisappearsOrAppears(driver, ".survey-success", true);
        assertTrue("Survey results not sent", surveySuccessField.isDisplayed());
    }

    @Step("Click on \"Resend email\" and check success")
    public void clickOnResentEmailAndCheckSuccess(WebDriver driver) {
        resentEmailButton.click();
        waitUntilElementDisappearsOrAppears(driver, ".wg-cell--order-1 > p > button", false);
        assertFalse("Do not work resent email", resentEmailButton.isDisplayed());
    }

    @Step("Check that section \"Follow us\" at the site footer contains the \"Twitter\" button that leads to the correct url and has the correct icon")
    public void checkTwitterButtonAndIconLinks() {

        assertEquals("Incorrect link of twitter site", WRIKE_IN_TWITTER.text, twitterButton.getAttribute("href"));
        assertEquals("Incorrect link of icon", TWITTER_ICON.text, twitterIcon.getAttribute("xlink:href"));


    }


}
