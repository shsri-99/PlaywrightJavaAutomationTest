package com.toolshop.cucumber;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.google.gson.JsonParser;
import com.microsoft.playwright.Page;
import com.toolshop.PageObjects.RegisterAPI;
import com.toolshop.PageObjects.RegisterObject;
import com.toolshop.fixtures.PlaywrightFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterSteps {
    Page page = PlaywrightFactory.getPage();
    RegisterObject registerObject = new RegisterObject(page);
    RegisterAPI registerAPI = new RegisterAPI(page);

    private String apiUrl;
    private String requestBody;
    private HttpResponse<String> response;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Given("User is on the homepage")
    @Step("Navigate to homepage")
    public void userIsOnTheHomepage() {
        page.navigate("https://practicesoftwaretesting.com");
        page.waitForLoadState();
    }

    @When("User navigates to the sign-in page")
    @Step("Click Sign In")
    public void userNavigatesToTheSignInPage() {
        registerObject.navSignin();
    }

    @When("User clicks on the register link")
    @Step("Click Register")
    public void userClicksOnTheRegisterLink() {

        registerObject.navRegister();
    }

    @Then("User is on the customer registration page")
    @Step("Verify registration page")
    public void userIsOnTheCustomerRegistrationPage() {
        String title = page.getByText("Customer registration").textContent();
        assertEquals("Customer registration", title);
    }

    @When("User submits Register button")
    @Step("Submit Register")
    public void userSubmitsRegisterButton() {
        registerObject.registerButton();
    }

    @Then("User receives alert message")
    @Step("Verify password error messages")
    public void userReceivesAlertMessage() {
        registerObject.assertAlertErrors();
    }
//Working with API sending post request to create new register.

    @Given("I set the request URL to {string}")
    @Step("Set API URL to: {apiUrl}")
    public void setTheRequestUrlTo(String url) {
        registerAPI.setApiUrl(url);
    }
    @Given("I set the request body to:")
    @Step("Set request body")
    public void setTheRequestBodyTo(String requestbody) {
        registerAPI.setRequestBody(requestbody);
    }
    @When("I send a POST request")
    @Step("Send POST request to API")
    public void sendAPostRequest() throws Exception{
        registerAPI.sendPostRequest();
    }

    @Then("the response status code should be {int}")
    @Step("Verify response status code is {expectedStatusCode}")
    public void theResponseStatusCodeShouldBe(Integer expectedStatusCode) {
        registerAPI.assertStatusCode(expectedStatusCode);
    }

    @Then("the response body should contain:")
    @Step("Verify response body contains expected JSON")
    public void theResponseBodyShouldContain(String expectedJsonResponse) {
        registerAPI.assertResponseBodyContains(expectedJsonResponse);
    }
}
