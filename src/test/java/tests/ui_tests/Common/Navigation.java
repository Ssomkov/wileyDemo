package tests.ui_tests.Common;

import helpers.Config;
import helpers.WebActions;
import io.qameta.allure.Step;
import pages.main_page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class Navigation extends WebActions {

    @Step("Opening the main page")
    public MainPage openMainPage() {
        open(Config.getMainPage());
        return new MainPage();
    }
}
