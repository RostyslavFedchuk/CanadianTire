package org.canadian.tire.ui.business;

import io.qameta.allure.Step;
import org.canadian.tire.ui.model.TireModel;
import org.canadian.tire.ui.object.HeaderPanel;
import org.canadian.tire.ui.object.SearchPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.canadian.tire.util.constants.CommonConstants.SEARCHING_RESULTS_TITLE;

@Component
public class SearchPageBO extends GeneralBO {

    @Autowired
    private SearchPage searchPage;
    @Autowired
    private HeaderPanel headerPanel;

    @Step("Verify that Home page is opened")
    public SearchPageBO verifyHomePageOpened() {
        String actualPageUrl = searchPage.getCurrentWebPageUrl();
        String expected = propertyManager.getCanadianTireBaseUiUrl();
        headerPanel.verifyHomePageLogoDisplayed();
        assertThat(actualPageUrl)
                .as("Home page is not opened")
                .isEqualTo(expected);
        return this;
    }

    @Step("Perform global search with '{searchKeyword}' keyword")
    public SearchPageBO performGlobalSearch(String searchKeyword) {
        String expectedSearchingTitle = SEARCHING_RESULTS_TITLE.formatted(searchKeyword);
        String actualSearchingTitle = headerPanel
                .fillGlobalSearchInput(searchKeyword)
                .submitGlobalSearch()
                .getSearchingResultsTitle();
        assertThat(actualSearchingTitle)
                .as("Searching title is not as expected")
                .isEqualTo(expectedSearchingTitle);
        return this;
    }

    @Step("Verify that '{itemTitle}' searched item is found")
    public SearchPageBO verifySearchedItemFound(String itemTitle) {
        assertThat(searchPage.getSearchedItemTitles())
                .as("%s Searched item is not found".formatted(itemTitle))
                .containsAnyOf(itemTitle);
        return this;
    }

    @Step("Open '{itemTitle}' searched item")
    public SearchPageBO openSearchedItem(String itemTitle) {
        searchPage.clickOnSearchItem(itemTitle);
        return this;
    }

    @Step("Select tire size: '{tireModel}'")
    public SearchPageBO selectTireSize(TireModel tireModel) {
        searchPage
                .clickOnTireSizesTabButton()
                .selectWidthDropdown(tireModel.getWidth())
                .selectAspectRatioDropdown(tireModel.getAspectRatio())
                .selectDiameterDropdown(tireModel.getDiameter())
                .clickOnShopByTireSizeButton();
        return this;
    }

    @Step("Verify that '{itemTitle}' items are available")
    public void verifyAvailableItemsCount(Integer itemTitle) {
        if(!Objects.equals(searchPage.getItemCounterInput(), itemTitle)) {
            searchPage.setItemCounterInput(itemTitle);
        }
        assertThat(searchPage.isItemsCounterErrorMessageDisplayed())
                .as("%d items are not available, but should be".formatted(itemTitle))
                .isFalse();
    }
}
