package tests.ui_tests;

import helpers.JsonUtils;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import models.UITestData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.main_page.MainPage;
import tests.ui_tests.Common.BaseTestClass;
import tests.ui_tests.Common.Navigation;

public class SearchAutocompleteTest extends BaseTestClass {

    private static final String DATA_PATH = "src\\test\\resources\\ui\\searchAutocomplete.json";

    private Navigation navigation;
    private MainPage mainPage;
    private SoftAssert softAssert;

    //For this test, the DataProvider is optional, but I used it as an architecture element
    @DataProvider(name = "data")
    public java.lang.Object[][] data() {
        UITestData testData = JsonUtils.generateDataFromFile(DATA_PATH, UITestData.class);
        return new Object[][]{{testData}};
    }

    @Feature("Search")
    @Test(description = "Check visibility 'Search autocomplete block'", dataProvider = "data")
    public void checkSearchAutocompleteTest(UITestData data) {

        softAssert = new SoftAssert();
        navigation = new Navigation();

        step1();
        step2();
        step3(data);

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

    @Step("Check visibility 'Search autocomplete block'")
    private void step3(UITestData data) {
        mainPage
                .setValueSearchField(data.getSearchValue())
                .checkVisibilitySearchAutocompleteBlock(true, softAssert);
    }
}
