package ru.ozon.pages;

public enum CategoriesList {
    SPORTS("Спортивное питание"),
    COMPUTER("Компьютерные технологии"),
    BAA("Витамины, БАД и пищевые добавки");

    public final String fullCategoryName;

    CategoriesList(String fullCategoryName) {
        this.fullCategoryName = fullCategoryName;
    }
}
