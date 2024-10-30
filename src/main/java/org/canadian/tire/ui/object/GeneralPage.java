package org.canadian.tire.ui.object;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.canadian.tire.util.enums.DropdownElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.canadian.tire.util.constants.CommonConstants.VERTICAL_CENTER_ALIGNMENT;

public abstract class GeneralPage {

    private static final String GENERIC_DROPDOWN_BUTTON = "//div[contains(@data-testid,'%s')]//button[contains(@class,'dropdown')]";
    private static final String GENERIC_DROPDOWN_INPUT = "//input[@id='search_%s']";
    private static final String GENERIC_DROPDOWN_VALUE = "//div[@role='listbox']//button[@title='%s']";

    public String getCurrentWebPageUrl() {
        return WebDriverRunner.url();
    }

    protected void fillVisibleInputField(SelenideElement element, String value, String fieldName) {
        element.as(fieldName)
                .shouldBe(visible)
                .sendKeys(value);
    }

    protected void pressEnterOnElement(SelenideElement element, String fieldName) {
        element.as(fieldName)
                .shouldBe(visible)
                .pressEnter();
    }

    protected void clickOnGenericElement(String text, String... genericKeys) {
        $x(text.formatted(genericKeys))
                .scrollIntoView(VERTICAL_CENTER_ALIGNMENT)
                .shouldBe(enabled)
                .click();
    }

    protected void clickOnVisibleElement(SelenideElement element, String fieldName) {
        element.as(fieldName)
                .scrollIntoView(VERTICAL_CENTER_ALIGNMENT)
                .shouldBe(enabled)
                .click();
    }

    protected void waitForElementToBeVisible(SelenideElement element, String fieldName) {
        element.as(fieldName)
                .shouldBe(visible);
    }

    protected void selectDropdownOption(DropdownElement dropdownElement, String dropdownValue) {
        SelenideElement dropdownButton = $x(GENERIC_DROPDOWN_BUTTON.formatted(dropdownElement.getName()));
        SelenideElement dropdownInput = $x(GENERIC_DROPDOWN_INPUT.formatted(dropdownElement.getName()));
        clickOnVisibleElement(dropdownButton, dropdownElement.getName());
        fillVisibleInputField(dropdownInput, dropdownValue, dropdownElement.getName());
        clickOnGenericElement(GENERIC_DROPDOWN_VALUE, dropdownValue);
    }
}
