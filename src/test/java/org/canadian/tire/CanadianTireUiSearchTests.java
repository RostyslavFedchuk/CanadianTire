package org.canadian.tire;

import com.codeborne.selenide.Selenide;
import org.canadian.tire.spring.PropertyManager;
import org.canadian.tire.spring.SpringConfig;
import org.canadian.tire.ui.business.SearchPageBO;
import org.canadian.tire.ui.model.TireModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.canadian.tire.ui.data.TireModels.getMichelinTireModel;

@SpringBootTest
@ContextConfiguration(classes = {SpringConfig.class})
public class CanadianTireUiSearchTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private PropertyManager propertyManager;
    @Autowired
    private SearchPageBO searchPageBO;

    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        Selenide.open(propertyManager.getCanadianTireBaseUiUrl());
        getWebDriver().manage().window().maximize();
    }

    @Test
    public void verifyCanadianTireProductSearch() {
        TireModel tireModel = getMichelinTireModel();

        searchPageBO
                .verifyHomePageOpened()
                .performGlobalSearch(tireModel.getSearchKeyword())
                .verifyGlobalSearchPerformed(tireModel.getSearchKeyword())
                .verifySearchedItemFound(tireModel.getName())
                .openSearchedItem(tireModel.getName())
                .selectTireSize(tireModel)
                .verifyAvailableItemsCount(tireModel.getTireCount());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownBrowser() {
        Selenide.closeWebDriver();
    }
}
