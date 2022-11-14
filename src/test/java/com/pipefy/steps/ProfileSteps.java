package com.pipefy.steps;

import com.pipefy.screens.ScreenFactory;
import com.pipefy.screensInterface.HomeInterface;
import com.pipefy.screensInterface.ProfileInterface;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class ProfileSteps {

    private HomeInterface homeScreen = ScreenFactory.getHomeScreen();
    private ProfileInterface profileScreen = ScreenFactory.getProfileScreen();

    @And("I click on switch company")
    public void iClickOnSwitchCompany() {
        homeScreen.clickOnAvatarUserImage();
        profileScreen.clickOnSwitchCompany();
    }

    @When("I validate that {string} organization with {string} is displayed")
    public void iValidateThatOrganizationInfo(String organization, String info) {
        assertTrue(profileScreen.checkCompaniesDescriptionTextIsVisible());
        assertTrue(profileScreen.checkCompaniesCountTextIsVisible());
        assertEquals(profileScreen.getOrganizationName(), organization);
        assertTrue(profileScreen.getOrganizationInfo().contains(info));;
    }
}
