package wrike.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected void waitUntilElementDisappearsOrAppears(WebDriver driver, String cssSelector, boolean visibility) {
        By byForNoSurveyButton = new By.ByCssSelector(cssSelector);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        if (visibility) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(byForNoSurveyButton));
        } else {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(byForNoSurveyButton));
        }
    }

}
