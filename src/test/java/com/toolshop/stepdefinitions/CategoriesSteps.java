package com.toolshop.stepdefinitions;
import com.microsoft.playwright.Page;
import com.toolshop.pages.CategoriesObject;
import com.toolshop.utils.PlaywrightFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;


public class CategoriesSteps {
    Page page = PlaywrightFactory.getPage();
    CategoriesObject categories = new CategoriesObject(page);

    @When("User clicks on Categories")
    @Step("User clicks on Categories")
    public void userClicksOnCategories() {
       categories.clickOnCategories();
    }
    @When("User selects Power Tools")
    @Step("User selects Power Tools")
    public void userSelectsPowerTools() {
      categories.selectPowerTools();
    }
    @Then("User can see {int} items in result")
    @Step("User can see {int} items in result")
    public void userCanSeeItemsInResult(Integer result) {
        categories.numberOfResultItemsinCategories(result);
    }
    @Then("User can see below products")
    @Step("User verifies product names and prices")
    public void userCanSeeBelowProducts(String productsDetails) {
        categories.verifyProducts(productsDetails);
    }

}
