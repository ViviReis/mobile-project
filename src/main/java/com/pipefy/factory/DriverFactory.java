package com.pipefy.factory;

import com.pipefy.config.DefaultConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class DriverFactory {

    public static AppiumDriver<MobileElement> driver;

    public static AppiumDriver<MobileElement> getDriver(DesiredCapabilities capabilities, String platform, URL url) {
        switch(platform.toLowerCase()) {
            case DefaultConstants.ANDROID_PLATFORM:
                driver = new AndroidDriver<>(url, capabilities);
                break;
            case DefaultConstants.IOS_PLATFORM:
                driver = new IOSDriver<>(url, capabilities);
                break;
            default : throw new RuntimeException("Invalid platform: " + platform);
        }
        return driver;
    }

}
