package com.toolshop.PageObjects;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
