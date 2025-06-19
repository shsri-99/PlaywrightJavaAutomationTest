package com.toolshop.stepdefinitions;

import com.microsoft.playwright.Page;
import com.toolshop.pages.LoginObject;
import com.toolshop.pages.RegisterObject;
import com.toolshop.utils.PlaywrightFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginSteps {
    Page page = PlaywrightFactory.getPage();
    RegisterObject registerObject = new RegisterObject(page);
    LoginObject loginObject = new LoginObject(page);

    @When("User enters {string} email address")
    @Step("Enter {email} email address")
    public void userEntersEmailAddress(String email) {
        loginObject.enterEmailAddress(email);

    }
    @When("User enters {string} password")
    @Step("Enter {password} password")
    public void userEntersPassword(String password) {
        loginObject.enterPassword(password);
    }
    @When("User submits login")
    @Step("User submits login button")
    public void userSubmitsLogin() {
        loginObject.loginButton();
    }

    @Then("{string} is visible in the page")
    @Step("{expectedMyAccountTitle} is visible in the page")
    public void is_visible_in_the_page(String expectedMyAccountTitle) {
    loginObject.myAccountVisible(expectedMyAccountTitle);
    }

    @Then("User receives {string} and {string} message")
    @Step("User receives alert message during login without entering email and password")
    public void userReceivesAlertMessageInLogin(String emailError, String passwordError) {
        registerObject.navSignin();
       loginObject.showLoginAlertMessage(emailError,passwordError);
    }


}
