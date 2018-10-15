package wrike;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import wrike.pageObject.HomePage;
import wrike.pageObject.ResendPage;

@Feature("Wrike company site")
@Story("Create account on wrike.com with email 'abc+wpt@wriketask.qaa' and check interface of pages")
public class TestForWrike {

    private static WebDriver driver;
    private static HomePage homePage;
    private static ResendPage resendPage;

    @BeforeClass
    public static void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        homePage = PageFactory.initElements(driver, HomePage.class);
        resendPage = PageFactory.initElements(driver, ResendPage.class);
    }

    @AfterClass
    public static void afterClass() {
        driver.close();
    }

    @DisplayName("Check interface and functionality")
    @Test
    public void shouldAnswerWithTrue() {
        // 1. Open url: wrike.com;
        homePage.open(driver);

        // 2. Click "Get started for free" button near "Login" button;
        homePage.startedButtonClick();

        // 3. Fill in the email field with "abc" value of email with postfix “+wpt@wriketask.qaa”;
        homePage.fillEmailField("abc");

        // 4. Click on "Create my Wrike account" button and check with assertion that we are moved to the next page;
        homePage.createAccountButtonClick(driver);

        // 5. Refuse to send mailing survey
        resendPage.refusalOfEmailSurvey(driver);

        // 6. Fill in the Q&A section at the left part of page with random generated answers.
        //    Check with assertion that answers are submitted;
        resendPage.randomAnswersOnSurveyAndCheckSubmitted(driver);

        // 7. Click on "Resend email" and check it with assertion;
        resendPage.clickOnResentEmailAndCheckSuccess(driver);

        // 8. Check that section "Follow us" at the site footer contains the "Twitter" button that leads to the correct url and has the correct icon;
        resendPage.checkTwitterButtonAndIconLinks();

    }
}
