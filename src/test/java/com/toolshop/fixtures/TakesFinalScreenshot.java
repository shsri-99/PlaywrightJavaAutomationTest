package com.toolshop.fixtures;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.AfterEach;

public interface TakesFinalScreenshot {

    @AfterEach
    default void takeScreenshot(Page page)  {
        ScreenshotManager.takeScreenshot(page, "Final screenshot");
    }
}