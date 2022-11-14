package com.pipefy.config;

import com.pipefy.services.AppiumService;
import io.appium.java_client.remote.*;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.cucumber.java.Scenario;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class LocalConfig implements TestConfig {

    private static Logger logger = LoggerFactory.getLogger(LocalConfig.class);
    private final String propertiesFilePath = "src/main/resources/local.properties";
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    public LocalConfig() {}

    @Override
    public void setup() {

        //Sanitize required variables/properties
        String appiumPath = System.getenv("APPIUM_HOME");
        if(appiumPath == null) {
            throw new RuntimeException("Missing APPIUM_HOME env variable");
        }
        configurationManager.setAppiumPath(appiumPath);

        //Start Appium Server
        AppiumService.setService(
                new AppiumServiceBuilder()
                        .withAppiumJS(new File(configurationManager.getAppiumPath()))
                        .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                        .usingAnyFreePort()
                        .withIPAddress(configurationManager.getAppiumHost()).build());
        AppiumService.getService().start();
        logger.info("Appium server started at " + AppiumService.getService().getUrl());
    }

    @Override
    public Properties getProperties() throws IOException {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(propertiesFilePath)));
        } catch (IOException e) {
            logger.error("Could not read property file");
            throw e;
        }
        return properties;
    }

    @Override
    public DesiredCapabilities getCapabilities(String platform) throws IOException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        Properties properties = getProperties();

        switch(platform) {

            case DefaultConstants.ANDROID_PLATFORM:

                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, properties.get("android.platformVersion"));
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, properties.get("android.deviceName"));
                capabilities.setCapability(AndroidMobileCapabilityType.AVD, properties.get("android.deviceName"));
                capabilities.setCapability(MobileCapabilityType.APP, configurationManager.getAppPath());
                capabilities.setCapability(AndroidMobileCapabilityType.DISABLE_WINDOW_ANIMATION, true);
                capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
                capabilities.setCapability(AndroidMobileCapabilityType.IGNORE_UNIMPORTANT_VIEWS, true);
                capabilities.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, true);
                capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD,true);
                capabilities.setCapability("appWaitForLaunch", false);
                capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
                break;

            case DefaultConstants.IOS_PLATFORM:

                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, properties.get("ios.platformVersion"));
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, properties.get("ios.deviceName"));
                capabilities.setCapability(MobileCapabilityType.APP, configurationManager.getAppPath());
                capabilities.setCapability(IOSMobileCapabilityType.LOCATION_SERVICES_ENABLED, true);
                capabilities.setCapability(IOSMobileCapabilityType.CONNECT_HARDWARE_KEYBOARD, false);
                capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, configurationManager.shouldAcceptAlertsOnIos());
                capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
                capabilities.setCapability(IOSMobileCapabilityType.WDA_STARTUP_RETRIES, 3);
                capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
                break;
        }

        return capabilities;
    }

    @Override
    public URL getAppiumURL() {
        return AppiumService.getService().getUrl();
    }

    @Override
    public void teardown() {

        //   Stops Appium Server
        if (AppiumService.getService() != null && AppiumService.getService().isRunning()) {
            AppiumService.getService().stop();
            logger.info("Appium server stopped successfully.");
        }
    }

    @Override
    public void scenarioSetup(Scenario scenario) {}

    @Override
    public void scenarioTeardown(Scenario scenario) { }
}
