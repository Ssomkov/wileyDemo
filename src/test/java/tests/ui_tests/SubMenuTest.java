package tests.ui_tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.main_page.MainPage;
import tests.ui_tests.Common.BaseTestClass;
import tests.ui_tests.Common.Navigation;

import java.util.Arrays;
import java.util.List;

public class SubMenuTest extends BaseTestClass {

    private Navigation navigation;
    private MainPage mainPage;
    private SoftAssert softAssert;

    @Feature("Menu")
    @Test(description = "Check sub menu")
    public void checkSubMenuTest() {

        softAssert = new SoftAssert();
        navigation = new Navigation();
        List<String> values = Arrays.asList("Information & Library Science", "Education & Public Policy", "K-12 General",
                "Higher Education General", "Vocational Technology", "Conflict Resolution & Mediation (School settings)",
                "Curriculum Tools- General", "Special Educational Needs", "Theory of Education", "Education Special Topics",
                "Educational Research & Statistics", "Literacy & Reading", "Classroom Management");
        step1();
        step2();
        step3(values);

        softAssert.assertAll();
    }

    @Step("Open main page")
    private void step1() {
        mainPage = navigation.openMainPage();
    }

    @Step("Close country location form")
    private void step2() {
        mainPage.countryLocationWindow(softAssert, true).clickButtonYes();
    }

    @Step("Check sub menu items")
    private void step3(List<String> values) {
        String menuItem = "Education";
        mainPage.headerBlock(softAssert)
                .moveCoursorToSubjectsDropdownLink()
                .checkVisibilitySubjectsDropdownMenuItem(true, menuItem);
        softAssert.assertAll();
        mainPage.headerBlock(softAssert)
                .moveCoursorToMenuItem(menuItem)
                .checkSubjectsDropdownMenuSubItemContainsValues(menuItem, values);
    }
}
