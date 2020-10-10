package pages.main_page.blocks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helpers.WebActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.htmlelements.annotations.Name;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HeaderBlock extends WebActions {

    private SoftAssert soft;

    public HeaderBlock(SoftAssert soft) {
        this.soft = soft;
    }

    @Name("Dropdown link 'Who we serve'")
    private SelenideElement whoWeServeDropdownLink = $(By.xpath("//a[contains(text(), 'WHO WE SERVE')]"));

    @Name("Dropdown link 'Subjects'")
    private SelenideElement subjectsDropdownLink = $(By.xpath("//a[contains(text(), 'SUBJECTS')]/parent::li"));

    @Name("Dropdown menu items 'Who we serve'")
    private ElementsCollection whoWeServeDropdownMenuItems = $$(By.xpath("//div[@id='Level1NavNode1']//li/a"));

    @Name("Dropdown menu items 'Subjects'")
    private ElementsCollection subjectsDropdownMenuItems = $$(By.xpath("//div[@id='Level1NavNode2']//li/a"));

    @Name("Dropdown menu 'Subjects' item")
    private String subjectsItem = "//div[@id='Level1NavNode2']//li/a[contains(text(),'%s')]";

    @Step("Click on dropdown link 'Who we serve'")
    public HeaderBlock clickWhoWeServeDropdownLink() {
        clickElement(whoWeServeDropdownLink);
        return this;
    }

    @Step("Click on dropdown link 'Subjects'")
    public HeaderBlock clickSubjectsDropdownLink() {
        clickElement(subjectsDropdownLink);
        return this;
    }

    @Step("Move coursor on dropdown link 'Subjects'")
    public HeaderBlock moveCoursorToSubjectsDropdownLink() {
        moveCoursorToElement(subjectsDropdownLink);
        return this;
    }

    @Step("Move coursor to 'Subjects' menu sub item [{0}]")
    public HeaderBlock moveCoursorToMenuItem(String value) {
        waitForElementsCollection(subjectsDropdownMenuItems, true, 3000);
        moveCoursorToElement($(By.xpath(String.format(subjectsItem, value))));
        return this;
    }

    @Step("Check visibility [{0}] dropdown menu items 'Who we serve'")
    public HeaderBlock checkVisibilityWhoWeServeDropdownMenuItems(boolean isVisible) {
        soft.assertEquals(waitForElementsCollection(whoWeServeDropdownMenuItems, isVisible, 3000), isVisible,
                String.format("Dropdown menu items 'Who we serve' must be %svisible", (isVisible ? "" : "in")));
        return this;
    }

    @Step("Check values dropdown menu items 'Who we serve'")
    public HeaderBlock checkValuesWhoWeServeDropdownMenuItems(List<String> values) {
        waitForElementsCollection(whoWeServeDropdownMenuItems, true, 3000);
        soft.assertTrue(whoWeServeDropdownMenuItems.filter(visible).texts().containsAll(values),
                "The discrepancy in the check values dropdown menu items 'Who we serve'");
        return this;
    }

    @Step("Check visibility [{0}] dropdown menu items 'Subjects'")
    public HeaderBlock checkVisibilitySubjectsDropdownMenuItems(boolean isVisible) {
        soft.assertEquals(waitForElementsCollection(subjectsDropdownMenuItems, isVisible, 3000), isVisible,
                String.format("Dropdown menu items 'Subjects' must be %svisible", (isVisible ? "" : "in")));
        return this;
    }

    @Step("Check visibility [{0}] dropdown menu item 'Subjects'")
    public HeaderBlock checkVisibilitySubjectsDropdownMenuItem(boolean isVisible, String menuTitle) {
        waitForElementsCollection(subjectsDropdownMenuItems, true, 3000);
        SelenideElement element = $(By.xpath(String.format(subjectsItem, menuTitle)));
        soft.assertEquals(isVisibleElement(element, 5000), isVisible,
                String.format("Visibility sub menu item '%s' of dropdown menu item 'Subjects' must be %svisible",
                        menuTitle, (isVisible ? "" : "in")));
        return this;
    }

    @Step("Check sub menu item [{0}] of dropdown 'Subjects' contains values {0}")
    public HeaderBlock checkSubjectsDropdownMenuSubItemContainsValues(String subMenuTitle, List<String> values) {
        SelenideElement element = $(By.xpath(String.format(subjectsItem, subMenuTitle)));
        ElementsCollection items = element.findAll(By.xpath("./..//li/a"));
        waitForElementsCollection(items, true, 3000);
        soft.assertTrue(items.filter(visible).texts().containsAll(values),
                String.format("The discrepancy in the check values sub menu item '%s' of dropdown 'Subjects'", subMenuTitle));
        return this;
    }

    @Step("Get [{0}] sub menu items titels of dropdown menu 'Subjects'")
    public List<String> getSubjectsSubMenuItems(String subMenuTitle) {
        SelenideElement element = $(By.xpath(String.format(subjectsItem, subMenuTitle)));
        ElementsCollection items = element.findAll(By.xpath("./..//li/a"));
        waitForElementsCollection(items, true, 3000);
        return items.texts();
    }

}
