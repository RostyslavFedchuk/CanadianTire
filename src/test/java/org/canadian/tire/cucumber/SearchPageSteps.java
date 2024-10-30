package org.canadian.tire.cucumber;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.canadian.tire.spring.PropertyManager;
import org.canadian.tire.ui.business.SearchPageBO;
import org.canadian.tire.ui.model.TireModel;
import org.canadian.tire.util.CucumberUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class SearchPageSteps {

    @Autowired
    private PropertyManager propertyManager;
    @Autowired
    private SearchPageBO searchPageBO;

    @Given("I am on the Canadian Tire website")
    public void iAmOnTheCanadianTireWebsite() {
        Selenide.open(propertyManager.getCanadianTireBaseUiUrl());
        getWebDriver().manage().window().maximize();

        searchPageBO.verifyHomePageOpened();
    }

    @When("I search for a product")
    public void iSearchForAProduct(List<List<String>> data) {
        TireModel tireModel = CucumberUtil.convertToTireModel(data);
        searchPageBO
                .performGlobalSearch(tireModel.getSearchKeyword())
                .verifySearchedItemFound(tireModel.getName())
                .openSearchedItem(tireModel.getName())
                .selectTireSize(tireModel);
    }

    @Then("^I should see the product availability of \"([^\"]*)\" tires$")
    public void iShouldSeeTheProductAvailabilityOfTires(String arg0) {
        searchPageBO.verifyAvailableItemsCount(Integer.valueOf(arg0));
        Selenide.closeWebDriver();
    }
}
