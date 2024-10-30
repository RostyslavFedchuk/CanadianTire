package org.canadian.tire.ui.object;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.canadian.tire.util.constants.CommonConstants.*;
import static org.canadian.tire.util.enums.DropdownElement.*;

@Component
public class SearchPage extends GeneralPage {

    private final ElementsCollection searchedItemTitles = $$x("//div[contains(@data-testid,'product-card-container')]//div[contains(@id,'title__search')]");
    private final SelenideElement searchingResultsTitle = $x("//h2[@class='nl-srp__title']");
    private final SelenideElement itemCounterInput = $("input.nl-qty-selector__text-input");
    private final SelenideElement itemsCounterErrorMessage = $("div.nl-qty-error");
    private final SelenideElement tireSizesTabButton = $x("//div[@data-testid='testid-1']");
    private final SelenideElement shopByTireSizeButton = $x("//div[contains(@class,'nl-add-vehicle-form')]//button");

    public String getSearchingResultsTitle() {
        return searchingResultsTitle.getText();
    }

    public List<String> getSearchedItemTitles() {
        return searchedItemTitles.stream()
                .map(SelenideElement::getText)
                .toList();
    }

    public void clickOnSearchItem(String itemTitle) {
        clickOnVisibleElement(searchedItemTitles.stream()
                .filter(element -> element.getText().equals(itemTitle))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Searched item not found")), itemTitle);
    }

    public SearchPage clickOnTireSizesTabButton() {
        clickOnVisibleElement(tireSizesTabButton, TIRE_SIZE_TAB_KEY);
        return this;
    }

    public void setItemCounterInput(Integer itemCounter) {
        itemCounterInput.clear();
        itemCounterInput.setValue(itemCounter.toString());
    }

    public Integer getItemCounterInput() {
        return Integer.valueOf(Objects.requireNonNull(itemCounterInput.getValue()));
    }

    @Step("Verify that Items Counter Error Message is displayed")
    public boolean isItemsCounterErrorMessageDisplayed() {
        return itemsCounterErrorMessage.is(visible, Duration.ofSeconds(3));
    }

    public SearchPage selectWidthDropdown(String width) {
        selectDropdownOption(TIRE_WIDTH, width);
        return this;
    }

    public SearchPage selectAspectRatioDropdown(String aspectRatio) {
        selectDropdownOption(ASPECT_RATIO, aspectRatio);
        return this;
    }

    public SearchPage selectDiameterDropdown(String diameter) {
        selectDropdownOption(DIAMETER, diameter);
        return this;
    }

    public void clickOnShopByTireSizeButton() {
        clickOnVisibleElement(shopByTireSizeButton, SHOP_BY_TIRE_SIZE_KEY);
    }
}
