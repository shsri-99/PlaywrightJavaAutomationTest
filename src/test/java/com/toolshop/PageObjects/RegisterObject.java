package com.toolshop.PageObjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import io.qameta.allure.Step;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterObject {
    private final Page page;

    public RegisterObject(Page page) {
        this.page = page;
    }
    @Step("Navigate to Sign in")
    public void navSignin(){
        Locator signInLink = page.locator("[data-test='nav-sign-in']");
        signInLink.waitFor();
        signInLink.click();
    }
    @Step("Navigate to Register")
    public void navRegister(){
        page.locator("[data-test='register-link']").click();
    }
    @Step("Submit Register")
    public  void registerButton(){
        page.locator("[data-test='register-submit']").click();
    }

    @Step("Verify Alert messages")
    public void assertAlertErrors() {
        Locator errorContainer = page.locator("[data-test='password-error']");
        List<String> errors = errorContainer.locator("div").allTextContents();
        List<String> actualErrors = errors.stream()
                .map(String::trim)
                .toList();

        List<String> expectedErrors = List.of(
                "Password is required",
                "Password must be minimal 6 characters long.",
                "Password can not include invalid characters."
        );

        assertEquals(expectedErrors, actualErrors);
        String errorText = page.locator("[data-test='email-error']").textContent();
        assertEquals("Email is required", errorText.trim());
    }

}
