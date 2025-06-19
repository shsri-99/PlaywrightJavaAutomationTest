package com.toolshop.runners;
import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
@ConfigurationParameter(key = "cucumber.glue", value = "com.toolshop.stepdefinitions,com.toolshop.fixtures,com.toolshop.hooks,com.toolshop.utils")
public class RunCucumberTest {
}
