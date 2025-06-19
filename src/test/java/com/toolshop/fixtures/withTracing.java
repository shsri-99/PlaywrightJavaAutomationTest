package com.toolshop.fixtures;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import com.toolshop.utils.PlaywrightFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.nio.file.Paths;

public class withTracing {
    Page page = PlaywrightFactory.getPage();
    BrowserContext context = page.context();

    @Before
    public void startTracing() {
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }

    @After
    public void captureArtifacts(Scenario scenario) {
        // Screenshot (like TakesFinalScreenshot)
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        scenario.attach(screenshot, "image/png", "Final Screenshot");

        // Tracing (like withTracing)
        String traceName = scenario.getName().replace(" ", "_").toLowerCase();
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("target/traces/trace-" + traceName + ".zip")));
    }
}
