package com.toolshop.PageObjects;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.Step;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoriesObject {

    private final Page page;

    public CategoriesObject(Page page) {
        this.page = page;
    }

    @Step("User clicks on Categories")
    public void clickOnCategories(){
        Locator categoryNav =page.locator("[data-test='nav-categories']");
        categoryNav.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        categoryNav.click();
    }

    @Step("User selects Power Tools")
    public void selectPowerTools(){
        Locator powerToolsLink = page.locator("[data-test='nav-power-tools']");
        powerToolsLink.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        powerToolsLink.click();

    }

    @Step("User can see {int} items in result")
    public void numberOfResultItemsinCategories(int expectedCount){
        Locator cards = page.locator(".card");
        cards.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        int actualCount = cards.count();
        assertEquals(expectedCount, actualCount, "Expected " + expectedCount + " items, but found " + actualCount);
    }

    @Step("User verifies product names and prices")
    public void verifyProducts(String productsDetails) {
        // Parse JSON docString into Map<String, Double>
        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, Double>>() {}.getType();
        Map<String, Double> expectedProducts = gson.fromJson(productsDetails, mapType);
        Map<String, Double> actualProducts = new HashMap<>();
        Locator cards = page.locator(".card");
        int count = cards.count();
        for (int i = 0; i < count; i++) {
            Locator card = cards.nth(i);
            String name = card.locator("[data-test='product-name']").textContent().trim();
            String priceText = card.locator("[data-test='product-price']").textContent().trim().replace("$", "");
            double price = Double.parseDouble(priceText);
            actualProducts.put(name, price);
        }
        assertEquals(expectedProducts, actualProducts, "Product names and prices do not match!");
    }
}
