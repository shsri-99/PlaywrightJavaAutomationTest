package com.toolshop.fixtures;

import io.cucumber.java.Before;

public class CallingAllureCleanup {
    private static boolean alreadyCleaned = false;

    @Before(order = 0)
    public void cleanAllureBeforeFirstScenario() {
        if (!alreadyCleaned) {
            AllureCleanup.cleanAllureResultsFolder("allure-results");
            alreadyCleaned = true;
        }
    }

}