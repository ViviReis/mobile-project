package com.pipefy.screens.profile;

import com.pipefy.screensInterface.ProfileInterface;
import com.pipefy.steps.DriverSteps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class ProfileScreen  extends DriverSteps implements ProfileInterface {
    @AndroidFindAll(value = {
            @AndroidBy(uiAutomator = "new UiSelector().textContains(\"Trocar de empresa\")"),
            @AndroidBy(uiAutomator = "new UiSelector().textContains(\"Switch company\")")})
    public MobileElement switchCompany;

    @AndroidFindBy(id = "text_companies_count")
    public MobileElement companiesCountText;

    @AndroidFindBy(id = "text_companies_description")
    public MobileElement companiesDescriptionText;

    @AndroidFindBy(id = "text_organization_name")
    public MobileElement organizationNameText;

    @AndroidFindBy(id = "text_organization_info")
    public MobileElement organizationInfoText;

    public ProfileScreen() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Override
    public void clickOnSwitchCompany() {
        clickElement(switchCompany);
    }

    @Override
    public boolean checkCompaniesDescriptionTextIsVisible() {
        return isElementVisible(companiesDescriptionText);
    }

    @Override
    public boolean checkCompaniesCountTextIsVisible() {
        return isElementVisible(companiesCountText);
    }

    @Override
    public String getOrganizationName() {
        return getTextFromElement(organizationNameText);
    }

    @Override
    public String getOrganizationInfo() {
        return getTextFromElement(organizationInfoText);
    }
}
