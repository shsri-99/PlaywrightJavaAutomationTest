package com.toolshop.cucumber;
import com.microsoft.playwright.Page;
import com.toolshop.PageObjects.ProductSearchObject;
import com.toolshop.PageObjects.RegisterObject;
import com.toolshop.fixtures.PlaywrightFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;

import java.util.List;

public class ProductSearchSteps {
    Page page = PlaywrightFactory.getPage();
    RegisterObject registerObject = new RegisterObject(page);
    ProductSearchObject productSearch = new ProductSearchObject(page);

    @When("User search {string} in search input")
    @Step("User search for productName in search box")
    public void userSearchInSearchInput(String productName) {
        productSearch.searchProductThroughInput(productName);
    }
    @When("User selects Search button")
    @Step("User selects Search Button")
    public void userSelectsSearchButton() {
        productSearch.clickSearch();
    }
    @Then("User can see below products results")
    @Step("User can view the results")
    public void userCanSeeProductsResults(io.cucumber.datatable.DataTable dataTable) {
        List<String> expectedProducts = dataTable.asList(String.class);
        productSearch.verifySearchResultsInput(expectedProducts);
    }

    @When("User selects checkbox by {string} and waits for product API response {int}")
    @Step("User selects checkbox by label text: {productName} and waits for product API response {int}")
    public void userSearchProductCheckbox(String productName, int resultResp){
        productSearch.searchProductByCheckbox(productName,resultResp);
    }

    @Then("User can see below products for checkbox search")
    @Step("User can see below products for checkbox search")
    public void userCanSeeBelowProductsForCheckboxSearch(io.cucumber.datatable.DataTable dataTable) {
        List<String> expectedProducts = dataTable.asList(String.class);
        productSearch.verifyProductSearchCheckboxResults(expectedProducts);
    }

    @When("User adds filter {string} on the products and wait for API response {int}")
    @Step("User adds filter {string} on the products and wait for API response {int}")
    public void userAddsFilterOnTheProducts(String sortMethod, int expResp) {
       productSearch.addFilterSearch(sortMethod,expResp);
    }
    @Then("User  can view {int} products as a result")
    @Step("User  can view {int} products as a result")
    public void userCanViewProductsAsAResult(int expectedProductCount) {
    productSearch.verifyExpectedProductAfterFilter(expectedProductCount);
    }

}
