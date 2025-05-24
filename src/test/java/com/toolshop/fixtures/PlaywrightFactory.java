package com.toolshop.fixtures;

import com.microsoft.playwright.*;

import java.util.Arrays;

public class PlaywrightFactory {
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    public static Page getPage() {
        if (page == null) {
            playwright = Playwright.create();
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(true)
                            .setArgs(Arrays.asList(new String[]{"--no-sandbox", "--disable-extensions", "--disable-gpu"}))
            );
            context = browser.newContext(
                    new Browser.NewContextOptions()
                            .setViewportSize(1280, 800)
            );
            page = context.newPage();
        }
        return page;
    }

    public static BrowserContext getContext() {
        getPage(); // ensures context is initialized
        return context;
    }

    public static void close() {
        if (page != null) {
            page.close();
            page = null;
        }
        if (context != null) {
            context.close();
            context = null;
        }
        if (browser != null) {
            browser.close();
            browser = null;
        }
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}
