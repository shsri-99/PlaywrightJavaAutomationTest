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

public class CartObject {
    private final Page page;

    public CartObject(Page page) {
        this.page = page;
    }
    @Step("click on a {string} item")
    public void selectAnItem(String item){
        page.getByText(item).click();
    }

    @Step("User selects Add to cart button")
    public void addToCardButton(){
       Locator addCartButton = page.locator("#btn-add-to-cart");
       addCartButton.waitFor();
       addCartButton.click();
    }

    @Step("Verify the alert message with {message}")
    public void verifyAddToCartMessage(String message){
        Locator actualMessage = page.getByText("Product added to shopping cart.");
        actualMessage.waitFor();
        assertTrue(actualMessage.isVisible());
        String actualText = actualMessage.innerText().trim();
        assertEquals(message.trim(), actualText);
    }

    @Step("Validate cart quantity increments")
    public  void basketCount(int quantity){
        Locator cartCount =  page.locator("#lblCartCount");
        cartCount.waitFor();
        page.waitForTimeout(4000);
        int count = Integer.parseInt(cartCount.textContent());
        System.out.println("Count the items in cart: "+count);
    }

    @Step("Navigate to checkout page")
    public void navigateCheckoutPage(){
        page.navigate("https://practicesoftwaretesting.com/checkout");
    }
    @Step("See product details on cart page")
    public void showProductDetailsAtCarPage(List<String> productDetails){
        Locator row = page.locator("tr", new Page.LocatorOptions().setHasText("Combination Pliers"));
        String actualTitle = row.locator("span[data-test='product-title']").textContent().trim();
        String actualQuantity = row.locator("input[data-test='product-quantity']").inputValue().trim();
        String actualPrice = row.locator("span[data-test='product-price']").textContent().trim();
        assertEquals(productDetails.get(0).trim(), actualTitle.replace("\u00A0", " ").trim(), "Product title mismatch");
        assertEquals(productDetails.get(1).trim(), actualQuantity, "Quantity mismatch");
        assertEquals(productDetails.get(2).trim(), actualPrice, "Product price mismatch");

    }
}
