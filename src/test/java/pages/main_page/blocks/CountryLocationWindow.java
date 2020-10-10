package pages.main_page.blocks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import helpers.WebActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Selenide.$;

public class CountryLocationWindow extends WebActions {

    private SoftAssert soft;

    public CountryLocationWindow(SoftAssert soft, boolean needWait) {
        this.soft = soft;
        if (needWait) {
            $(window).waitUntil(Condition.visible.because("Window 'Country location form' didn't open"), 10000);
        }
    }

    @Name("Window 'Country location form'")
    private SelenideElement window = $(By.xpath("//form[@id='country-location-form']"));

    @Name("Button 'YES'")
    private SelenideElement yesButton = $(By.xpath("//button[text()='YES']"));

    @Name("Button 'NO'")
    private SelenideElement noButton = $(By.xpath("//button[text()='NO']"));

    @Step("Click on button 'YES'")
    public CountryLocationWindow clickButtonYes() {
        clickElement(yesButton);
        return this;
    }

    @Step("Click on button 'NO'")
    public CountryLocationWindow clickButtonNo() {
        clickElement(noButton);
        return this;
    }
}
