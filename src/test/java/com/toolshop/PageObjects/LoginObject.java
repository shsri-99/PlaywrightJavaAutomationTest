package com.toolshop.PageObjects;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginObject {
    private final Page page;

    public LoginObject(Page page) {
        this.page = page;
    }

    @Step("Enter {email} email address")
    public  void enterEmailAddress(String email){
        page.locator("#email").fill(email);
    }
    @Step("Enter {password} password")
    public void enterPassword(String password){
        page.locator("#password").fill(password);
    }

    @Step("User submits login button")
    public void loginButton(){
        page.locator(".btnSubmit").click();
    }

    @Step("{expectedMyAccountTitle} is visible in the page")
    public void myAccountVisible(String expectedMyAccountTitle){
        page.waitForLoadState();
        String actualTitle = page.locator("[data-test='page-title']").textContent();
        assertEquals(actualTitle,expectedMyAccountTitle,"My Account is visible");
    }

    @Step("User receives alert message during login without entering email and password")
    public void showLoginAlertMessage(String emailError, String passwordError){
        Locator actualEmailError = page.locator("text='Email is required'");
        Locator actualPasswordError = page.locator("text='Password is required'");
        assertTrue(actualEmailError.isVisible(),emailError);
        assertTrue(actualPasswordError.isVisible(),passwordError);
    }

}
