package helpers;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;

public class WebActions {

    public void setTextField(SelenideElement element, String value) {
        $(element).clear();
        $(element).sendKeys(value);
    }

    public void clickElement(SelenideElement element) {
        $(element).click();
    }

    public void moveCoursorToElement(SelenideElement element) {
        Selenide.actions().moveToElement(element).perform();
    }

    public boolean isVisibleElement(SelenideElement elem, int timeMS) {
        int step = 300;
        for (int i = 0; i < timeMS; i += step) {
            if ($(elem).isDisplayed()) {
                return true;
            }
            sleep(step);
        }
        return false;
    }

    public boolean isVisibleElement(SelenideElement elem, int timeMS, boolean expectVisibility) {
        int step = 300;
        for (int i = 0; i < timeMS; i += step) {
            if ($(elem).isDisplayed() == expectVisibility) {
                return $(elem).isDisplayed();
            }
            sleep(step);
        }
        return $(elem).isDisplayed();
    }

    protected static boolean waitForElementsCollection(ElementsCollection elements, boolean isVisible, long maxTime) {
        int currentTime = 0;
        boolean listVisible = ($$(elements).filter(visible).size() > 0);
        while (listVisible != isVisible && currentTime < maxTime) {
            sleep(250);
            currentTime += 250;
            listVisible = ($$(elements).filter(visible).size() > 0);
        }
        sleep(500);
        return listVisible;
    }

}
