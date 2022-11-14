package com.pipefy.steps;

import com.pipefy.services.AppiumService;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.time.Duration.ofSeconds;

public class DriverSteps {

    private final int DEFAULT_TIMEOUT = 30;

    public static Logger logger = LoggerFactory.getLogger(DriverSteps.class);
    public AppiumDriver<MobileElement> driver = AppiumService.getDriver();
    private WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, DEFAULT_TIMEOUT).ignoring(StaleElementReferenceException.class);

    public void waitUntilElementIsVisible(MobileElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isElementVisible(MobileElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (NoSuchElementException | TimeoutException err) {
            logger.warn("Element " + element.toString() + " not found");
            return false;
        }
    }

    public void clickElement(MobileElement element, Integer seconds) {
        wait.withTimeout(ofSeconds(seconds)).until(ExpectedConditions.elementToBeClickable(element));
        wait.withTimeout(ofSeconds(DEFAULT_TIMEOUT));
        element.click();
    }

    public void clickElement(MobileElement element) {
        waitUntilElementIsVisible(element);
        element.click();
    }

    public void sendKeysToElement(MobileElement element, String value) {
        waitUntilElementIsVisible(element);
        element.sendKeys(value);
    }

    public String getTextFromElement(MobileElement element) {
        return element.getText();
    }
}
