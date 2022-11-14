package com.pipefy.services;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AppiumService {

    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<AppiumDriverLocalService> service = new ThreadLocal<>();

    public synchronized static void setDriver(AppiumDriver driver) {
        AppiumService.driver.set(driver);
    }

    public synchronized static AppiumDriver getDriver() {
        return driver.get();
    }

    public synchronized static AppiumDriverLocalService getService() {
        return service.get();
    }

    public synchronized static void setService(AppiumDriverLocalService service) {
        AppiumService.service.set(service);
    }
}
