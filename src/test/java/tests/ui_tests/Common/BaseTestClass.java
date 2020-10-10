package tests.ui_tests.Common;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import helpers.Config;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;

public class BaseTestClass {

    public BaseTestClass() {
        Configuration.browser = Config.getBrowser();
        Configuration.baseUrl = Config.getMainPage();
        Configuration.timeout = 5000;
        Configuration.startMaximized = true;
        System.setProperty("selenide.openBrowserTimeout", "30000");
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest() {
        if (WebDriverRunner.getWebDriver() != null) {
            WebDriverRunner.getWebDriver().quit();
        }
    }

    public WebDriver getDriver() {
        if (WebDriverRunner.hasWebDriverStarted())
            return WebDriverRunner.getWebDriver();
        return null;
    }

}
