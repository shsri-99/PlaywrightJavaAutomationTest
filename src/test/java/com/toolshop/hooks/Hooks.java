package com.toolshop.hooks;

import com.microsoft.playwright.Page;
import com.toolshop.utils.PlaywrightFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import com.toolshop.fixtures.ScreenshotManager;

public class Hooks {
    public static Page page; // This should be set in your @Before hook

    @Before
    public void beforeScenario() {
        // Initialize page from PlaywrightFactory (or however you create it)
        page = PlaywrightFactory.getPage();
    }
    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            ScreenshotManager.takeScreenshot(page, "Failure Screenshot");
        }else {
            ScreenshotManager.takeScreenshot(page, "Final Screenshot");
        }
    }
}
