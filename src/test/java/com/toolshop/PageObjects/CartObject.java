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
}
