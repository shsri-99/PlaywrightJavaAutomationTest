package com.toolshop.PageObjects;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class RegisterAPI {   private final HttpClient httpClient = HttpClient.newHttpClient();
    private String apiUrl;
    private String requestBody;
    private HttpResponse<String> response;
    private  Page page;

    public RegisterAPI(Page page) {

    }

    @Step("Set API URL to: {apiUrl}")
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Step("Set request body")
    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    @Step("Send POST request to API")
    public void sendPostRequest() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Step("Verify response status code is {expectedStatusCode}")
    public void assertStatusCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.statusCode(), "Unexpected HTTP status code");
    }

    @Step("Verify response body contains expected JSON")
    public void assertResponseBodyContains(String expectedJsonResponse) {
        String actualBody = response.body();

        JsonObject expectedJson = JsonParser.parseString(expectedJsonResponse).getAsJsonObject();
        JsonObject actualJson = JsonParser.parseString(actualBody).getAsJsonObject();

        // Remove dynamic fields if necessary
        expectedJson.remove("id");
        expectedJson.remove("created_at");

        actualJson.remove("id");
        actualJson.remove("created_at");

        assertEquals(expectedJson, actualJson,
                "\nExpected JSON:\n" + expectedJson.toString() +
                        "\n\nActual JSON:\n" + actualJson.toString());
    }
}
