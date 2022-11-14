package com.pipefy.hooks;

import com.pipefy.config.ConfigurationManager;
import com.pipefy.config.TestConfig;
import com.pipefy.factory.DriverFactory;
import com.pipefy.services.AppiumService;
import com.pipefy.utils.CucumberReportUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CucumberHooks {

    private static Logger logger = LoggerFactory.getLogger(CucumberHooks.class);
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    private TestConfig testConfig = configurationManager.getTestConfig();

    @Before(order = 1)
    public void beforeScenario(Scenario scenario) throws IOException {
        configurationManager.setShouldAcceptAlertsOnIos(!scenario.getSourceTagNames().contains("@checkoutAddress"));
        testConfig.scenarioSetup(scenario);
        DesiredCapabilities capabilities = testConfig.getCapabilities(configurationManager.getPlatform());
        Properties properties = testConfig.getProperties();

        //Set build alias
        capabilities.setCapability("build", configurationManager.getBuildAlias());
        capabilities.setCapability("name", scenario.getName());

        //Starts Appium Client
        AppiumDriver<MobileElement> driver = DriverFactory.getDriver(capabilities, configurationManager.getPlatform(), testConfig.getAppiumURL());
        AppiumService.setDriver(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void afterScenario(Scenario scenario) {
        testConfig.scenarioTeardown(scenario);
        CucumberReportUtils.takeScreenshot(AppiumService.getDriver(), scenario);
        //Stops Appium Client
        if (AppiumService.getDriver() != null) {
            AppiumService.getDriver().quit();
            logger.info("Appium driver stopped successfully.");
        }
    }
}
