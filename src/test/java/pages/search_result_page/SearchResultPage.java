package pages.search_result_page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helpers.WebActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultPage extends WebActions {

    public SearchResultPage() {
        $(paginatorPanel).waitUntil(Condition.visible.because("Search result page didn't open"), 10000);
    }

    @Name("Paginator panel")
    private SelenideElement paginatorPanel = $(By.xpath("//div[@class='pagination-bar']"));

    @Name("Product list items")
    private ElementsCollection productListItems = $$(By.xpath("//div[@class='products-list']/section"));

    @Step("Check visibility [{0}] product list items")
    public SearchResultPage checkVisibilityProductListItems(boolean isVisible, SoftAssert softAssert) {
        softAssert.assertEquals(waitForElementsCollection(productListItems, isVisible, 3000), isVisible,
                String.format("Product list items must be %svisible", (isVisible ? "" : "in")));
        return this;
    }

    @Step("Check [{0}] product list items count")
    public SearchResultPage checkCountProductListItems(int count, SoftAssert softAssert) {
        softAssert.assertTrue(productListItems.size() == count,
                String.format("The number of items in the product list was expected: %d", count));
        return this;
    }

    @Step("Check product list item titlewith index [{0}] contains value [{1}]")
    public SearchResultPage checkContainsValueProductListItemTitle(int index, String value, SoftAssert softAssert) {
        softAssert.assertTrue(getValueProductItemTitle(index).contains(value),
                String.format("Product list item title with index '%d' doesn't contains value: %s", index, value));
        return this;
    }


    private String getValueProductItemTitle(int index) {
        String[] title = productListItems.get(index).getText().split("\n");
        return title[0];
    }
}
