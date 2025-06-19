package com.toolshop.fixtures;

import com.microsoft.playwright.Page;
import com.toolshop.utils.PlaywrightFactory;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;

public class  RecordsAllureScreenshots {
    private final Page page = PlaywrightFactory.getPage();

    @After
    public void takeScreenshotAfterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            ScreenshotManager.takeScreenshot(page, "Failure Screenshot - " + scenario.getName());
            byte[] screenshot = page.screenshot();
            scenario.attach(screenshot, "image/png", "Failure Screenshot");
            Allure.addAttachment("Failure Screenshot", "image/png", new ByteArrayInputStream(screenshot), ".png");
        } else {
            ScreenshotManager.takeScreenshot(page, "End of Scenario - " + scenario.getName());
        }
    }
}
