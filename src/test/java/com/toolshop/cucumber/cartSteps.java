package com.toolshop.cucumber;
import com.microsoft.playwright.Page;
import com.toolshop.PageObjects.CartObject;
import com.toolshop.fixtures.PlaywrightFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;

public class cartSteps {
    Page page = PlaywrightFactory.getPage();
    CartObject cart = new CartObject(page);

    @When("click on a {string} item")
    @Step("click on a {string} item")
    public void selectAProduct(String product) {
        cart.selectAnItem(product);
    }
    @When("User selects Add to cart button")
    @Step("User selects Add to cart button")
    public void userSelectsAddToCartButton() {
       cart.addToCardButton();
    }
    @Then("message {string} is displayed on the screen")
    @Step("Verify the alert message with {message}")
    public void messageIsDisplayedOnTheScreen(String message) {
    cart.verifyAddToCartMessage(message);
    }

}
