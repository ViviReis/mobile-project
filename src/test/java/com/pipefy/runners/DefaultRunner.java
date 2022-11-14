package com.pipefy.runners;

import com.pipefy.config.ConfigurationManager;
import com.pipefy.config.TestConfig;
import com.pipefy.factory.CapabilitiesFactory;
import com.pipefy.hooks.CucumberHooks;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

@CucumberOptions(
        monochrome = true,
        plugin = {"pretty", "com.pipefy.utils.ExtentCucumberAdapter:",
                "json:target/reports/CucumberReport.json", "testng:target/reports/CucumberReport.xml", "html:target/reports/CucumberReport.html","rerun:target/failed_scenarios_default.txt"},
        features = "src/test/resources/features/",
        glue = {"com.pipefy"})
public class DefaultRunner extends AbstractTestNGCucumberTests {

    private static Logger logger = LoggerFactory.getLogger(CucumberHooks.class);
    private static ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    @BeforeSuite
    public void setup() throws Exception {
        //Performs an overall initial configuration such as credentials, properties and env variables.
        configurationManager.setup();

        //Sets the environment specific configuration
        configurationManager.setTestConfig(CapabilitiesFactory.getConfig(configurationManager.getEnvironment()));

        TestConfig testConfig = configurationManager.getTestConfig();
        testConfig.setup();
    }

    @AfterSuite
    public static void teardown() {

        TestConfig testConfig = configurationManager.getTestConfig();
        if(testConfig != null) {
            testConfig.teardown();
        }
    }
}
