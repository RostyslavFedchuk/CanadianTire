package org.canadian.tire.ui.object;

import com.codeborne.selenide.SelenideElement;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.canadian.tire.spring.BeanFactory.getBean;
import static org.canadian.tire.util.constants.CommonConstants.GLOBAL_SEARCH_KEY;
import static org.canadian.tire.util.constants.CommonConstants.HOME_PAGE_KEY;

@Component
public class HeaderPanel extends GeneralPage {

    private final SelenideElement homePageLogo = $x("//a[@data-link-value='Canadian Tire Home page']");
    private final SelenideElement globalSearchInput = $("input#search-input-0");

    public void verifyHomePageLogoDisplayed() {
        waitForElementToBeVisible(homePageLogo, HOME_PAGE_KEY);
    }

    public HeaderPanel fillGlobalSearchInput(String searchKeyword) {
        fillVisibleInputField(globalSearchInput, searchKeyword, GLOBAL_SEARCH_KEY);
        return this;
    }

    public SearchPage submitGlobalSearch() {
        pressEnterOnElement(globalSearchInput, GLOBAL_SEARCH_KEY);
        return getBean(SearchPage.class);
    }
}
