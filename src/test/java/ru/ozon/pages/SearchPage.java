package ru.ozon.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class SearchPage {
    //locators
    SelenideElement searchField = $("#stickyHeader > div.g8c > div > form > div.xt8 > input:nth-child(1)"),
            searchBtn = $("#stickyHeader > div.g8c > div > form > button"),
            resultItem = $("#layoutPage > div.u1g > div.container.u2g > div:nth-child(2) " +
                    "> div:nth-child(2) > div.s5i > div:nth-child(1) > div"),
            leftMenu = $("#layoutPage > div.u1g > div.container.u2g > div:nth-child(2) " +
                    "> div:nth-child(1) > aside > section > main"),
            searchDropdown = $("#layoutPage > div.u1g > div.container.u2g > div:nth-child(2) " +
                    "> div:nth-child(1) > aside > section > main");

    //actions
    public SearchPage openPage(String website) {
        Selenide.open(website);
        return this;
    }

    public SearchPage setSearchData(String searchData) {
        searchField.sendKeys(searchData);
        return this;
    }

    public SearchPage clickSearchBtn () {
        searchBtn.click();
        return this;
    }

    public SearchPage checkItemResult(String expectedItemResult) {
        resultItem.shouldHave(text(expectedItemResult));
        return this;
    }

    public SearchPage checkLeftMenuItem(String expectedMenuItemResult) {
        leftMenu.shouldHave(text(expectedMenuItemResult));
        return this;
    }

    public SearchPage checkLeftMenu(List<String> expectedMenuResult) {
        leftMenu.equals(expectedMenuResult);
        return this;
    }

    public SearchPage clickOnLeftMenuItem(String menuItem) {
        leftMenu.$(byText(menuItem)).click();
        return this;
    }

    public SearchPage checkItemInSearchDropdown(String expectedSearchDropdown) {
        searchDropdown.shouldHave(text(expectedSearchDropdown));
        return this;
    }
}
