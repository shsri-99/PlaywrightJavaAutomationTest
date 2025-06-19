package com.toolshop.utils;

import com.microsoft.playwright.*;

import java.util.Arrays;

public class PlaywrightFactory {
    private static Playwright playwright;

    // One browser instance shared across threads is generally okay for Playwright
    private static Browser browser;

    // ThreadLocal for context and page so each thread gets its own instance
    private static final ThreadLocal<BrowserContext> threadLocalContext = new ThreadLocal<>();
    private static final ThreadLocal<Page> threadLocalPage = new ThreadLocal<>();

    // Initialize Playwright and Browser only once (lazy)
    private static synchronized void init() {
        if (playwright == null) {
            playwright = Playwright.create();
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(true)
                            .setArgs(Arrays.asList("--no-sandbox", "--disable-extensions", "--disable-gpu"))
            );
        }
    }

    public static Page getPage() {
        init();

        if (threadLocalPage.get() == null) {
            BrowserContext context = browser.newContext(
                    new Browser.NewContextOptions()
                            .setViewportSize(1280, 800)
            );
            threadLocalContext.set(context);

            Page page = context.newPage();
            threadLocalPage.set(page);
        }
        return threadLocalPage.get();
    }

    public static BrowserContext getContext() {
        return threadLocalContext.get();
    }

    // Close and clean up thread-local instances
    public static void close() {
        if (threadLocalPage.get() != null) {
            threadLocalPage.get().close();
            threadLocalPage.remove();
        }
        if (threadLocalContext.get() != null) {
            threadLocalContext.get().close();
            threadLocalContext.remove();
        }
    }

    // Call this once when all tests finish to close browser & playwright
    public static synchronized void shutdown() {
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
