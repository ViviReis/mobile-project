package com.pipefy.config;

import io.cucumber.java.Scenario;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public interface TestConfig {

    void setup() throws Exception;
    void scenarioSetup(Scenario scenario);
    Properties getProperties() throws IOException;
    DesiredCapabilities getCapabilities(String platform) throws IOException;
    URL getAppiumURL() throws MalformedURLException;
    void teardown();
    void scenarioTeardown(Scenario scenario);
}
