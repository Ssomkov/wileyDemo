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

public class WhoWeServeMenuItemsTest extends BaseTestClass {

    private Navigation navigation;
    private MainPage mainPage;
    private SoftAssert softAssert;

    @Feature("Menu")
    @Test(description = "Check 'Who We Serve' menu items")
    public void checkWhoWeServeMenuItemsTest() {

        softAssert = new SoftAssert();
        navigation = new Navigation();
        List<String> values = Arrays.asList("Students", "Instructors", "Book Authors", "Professionals", "Researchers",
                "Institutions", "Librarians", "Corporations", "Societies", "Journal Editors", "Government");

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

    @Step("Check menu items")
    private void step3(List<String> values) {
        mainPage.headerBlock(softAssert)
                .clickWhoWeServeDropdownLink()
                .checkVisibilityWhoWeServeDropdownMenuItems(true)
                .checkValuesWhoWeServeDropdownMenuItems(values);
    }
}
