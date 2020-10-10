package pages.main_page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import helpers.WebActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import pages.main_page.blocks.CountryLocationWindow;
import pages.main_page.blocks.HeaderBlock;
import pages.search_result_page.SearchResultPage;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Selenide.$;

public class MainPage extends WebActions {

    public MainPage() {
        $(articlePanel).waitUntil(Condition.visible.because("The Wiley main page didn't open"), 10000);
    }

    public HeaderBlock headerBlock(SoftAssert soft) {
        return new HeaderBlock(soft);
    }

    public CountryLocationWindow countryLocationWindow(SoftAssert soft, boolean needWait) {
        return new CountryLocationWindow(soft, needWait);
    }

    @Name("Wiley logo")
    private SelenideElement wileyLogo = $(By.xpath("//div[@id='wileyLogo']"));

    @Name("Article panel")
    private SelenideElement articlePanel = $(By.xpath("//article[@class='page-section']"));

    @Name("Field 'Search'")
    private SelenideElement searchField = $(By.xpath("//input[@id='js-site-search-input']"));

    @Name("Search autocomplete block")
    private SelenideElement searchAutocompleteBlock = $(By.xpath("//aside[@id='ui-id-2']"));

    @Name("Search button")
    private SelenideElement searchButton = $(By.xpath("//button[text()='Search']"));

    @Step("Input value [{0}] into field 'Search'")
    public MainPage setValueSearchField(String value) {
        setTextField(searchField, value);
        return this;
    }

    @Step("Click 'Search button'")
    public SearchResultPage clickSearchButton() {
        clickElement(searchButton);
        return new SearchResultPage();
    }

    @Step("Check visibility [{0}] 'Search autocomplete block'")
    public MainPage checkVisibilitySearchAutocompleteBlock(boolean isVisible, SoftAssert softAssert) {
        softAssert.assertEquals(isVisibleElement(searchAutocompleteBlock, 5000), isVisible,
                String.format("'Search autocomplete block' must be %svisible", (isVisible ? "" : "in")));
        return this;
    }
}
