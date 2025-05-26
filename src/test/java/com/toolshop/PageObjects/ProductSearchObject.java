package com.toolshop.PageObjects;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.SelectOption;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ProductSearchObject {
    private final Page page;

    public ProductSearchObject(Page page) {
        this.page = page;
    }

    @Step("User search for productName in search box")
    public void searchProductThroughInput(String productName){
        page.waitForLoadState(LoadState.LOAD);
        Locator searchInput = page.locator("[data-test='search-query']");
        searchInput.waitFor();
        searchInput.fill(productName);
    }

    @Step("User selects Search Button")
    public void clickSearch(){
        Locator search =page.locator("[data-test='search-submit']");
        search.waitFor();
        search.click();

    }

    @Step("User can view the results")
    public void verifySearchResultsInput(List<String> expectedProducts){
        Locator product = page.locator("[data-test='search-term']");
        product.waitFor();
        assertThat(product.isVisible());
        List<String> actualProducts = page.locator("[data-test='product-name']")
                .allInnerTexts()
                .stream()
                .map(String::trim)
                .toList();
        System.out.println("Actual products from DOM:");
        actualProducts.forEach(System.out::println);

        assertEquals(expectedProducts.size(), actualProducts.size(),
                "Mismatch in number of products displayed.");
        for (String expectedProduct : expectedProducts) {
            assertTrue(actualProducts.contains(expectedProduct),
                    "Expected product '" + expectedProduct + "' not found in search results.");
        }
    }
    @Step("User selects checkbox by label text: {productName} and waits for product API response {int}")
    public void searchProductByCheckbox(String productName, int resultResp) {
        Locator labelName = page.locator("label:has-text('" + productName + "')");
        Response response = page.waitForResponse(
                res -> res.url().contains("api.practicesoftwaretesting.com/products") &&
                        res.status() == 200,
                () -> labelName.check() // Clicking label checks the box
        );
        assertThat(response.status()).isEqualTo(resultResp);
    }

    @Step("User can see below products for checkbox search")
    public void verifyProductSearchCheckboxResults(List<String> expectedProductsSearchBoxResults) {
        List<String> actualProductSearchBoxResults = page.locator("[data-test='product-name']:visible").allInnerTexts()
                .stream()
                .map(String::trim)
                .toList();
        System.out.println("🔍 Products found after checkbox selection: " +actualProductSearchBoxResults);

        assertEquals(expectedProductsSearchBoxResults.size(), actualProductSearchBoxResults.size(),
                "Mismatch in number of products displayed.");
    }

    @Step("User adds filter {string} on the products and wait for API response {int}")
    public void addFilterSearch(String sortMethod,int expResp){
        Locator sort =page.locator("[data-test='sort']");
        sort.click();
        sort.selectOption(new SelectOption().setLabel(sortMethod));

        Response response = page.waitForResponse(
                res -> res.url().contains("api.practicesoftwaretesting.com/products?sort=price") && res.status() == 200,
                () -> sort.selectOption(new SelectOption().setLabel(sortMethod))
        );
    }
    @Step("User  can view {int} products as a result")
    public void verifyExpectedProductAfterFilter(int expectedProductsCount){
        int totalProductCount = 0;
        Locator productCards = page.locator(".card");
        while (true) {
            int count = productCards.count();
            totalProductCount += count;
            //System.out.println("Products on page: " + count + " | Total so far: " + totalProductCount);
            Locator nextButton =  page.locator("a[aria-label='Next']");
            Locator nextPageItem = nextButton.locator("..");
            String classes = nextPageItem.getAttribute("class");
            if (classes != null && classes.contains("disabled")) {
                break;
            }
            nextButton.click();
            page.waitForLoadState();
        }
        Assertions.assertEquals(expectedProductsCount, totalProductCount, "Total product count across pages is not 45");
    }
}
