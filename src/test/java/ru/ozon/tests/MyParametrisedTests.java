package ru.ozon.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import ru.ozon.pages.CategoriesList;
import ru.ozon.pages.SearchPage;

import java.util.List;
import java.util.stream.Stream;

public class MyParametrisedTests {
    SearchPage ozonSearchPage = new SearchPage();

    @ValueSource(strings = {
            "корм для кошек",
            "selenium"
    })

    @ParameterizedTest(name = "Check search on Ozon with the {0}")
    void ozonSearchTestData(String testData) {
        //Precondition
        ozonSearchPage.openPage("https://www.ozon.ru")
                //Steps
                .setSearchData(testData)
                .clickSearchBtn()
                //Expected result
                .checkItemResult(testData);
    }


    @CsvSource(value = {
            "кошачий корм + Корм для кошек",
            "selenium + Компьютерные технологии"
    },
            delimiter = '+'
    )

    @ParameterizedTest(name = "Check search on Ozon with the {0}, expected result {1}")
    void ozonSearchTestDataExpectedResult(String testData, String expectedResult) {
        //Precondition
        ozonSearchPage.openPage("https://www.ozon.ru")
                //Steps
                .setSearchData(testData)
                .clickSearchBtn()
                //Expected result
                .checkLeftMenuItem(expectedResult);
    }

    static Stream<Arguments> methodSourceTestDataAndSeveralResults() {
        return Stream.of(
                Arguments.of("кошачий корм", List.of("Корм для кошек", "Товары для животных")),
                Arguments.of("selenium", List.of("Компьютерные технологии", "Витамины, БАД и пищевые добавки", "Спортивное питание"))
        );
    }

    @MethodSource("methodSourceTestDataAndSeveralResults")

    @ParameterizedTest
    void ozonSearchTextAndCheckSomeCategoryResults(String testData, List<String> expectedResult) {
        //Precondition
        ozonSearchPage.openPage("https://www.ozon.ru")
                //Steps
                .setSearchData(testData)
                .clickSearchBtn()
                //Expected result
                .checkLeftMenu(expectedResult);
    }

    @EnumSource(CategoriesList.class)
    @ParameterizedTest
    void ozonMenuItemsFromEnum(CategoriesList testData) {
        //Precondition
        ozonSearchPage.openPage("https://www.ozon.ru")
                //Steps
                .setSearchData("selenium")
                .clickSearchBtn()
                .clickOnLeftMenuItem(testData.fullCategoryName)
                //Expected result
                .checkItemInSearchDropdown(testData.fullCategoryName);
    }
}
