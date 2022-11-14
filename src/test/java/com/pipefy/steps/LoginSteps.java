package com.pipefy.steps;

import com.pipefy.config.ConfigurationManager;
import com.pipefy.screens.ScreenFactory;
import com.pipefy.screensInterface.HomeInterface;
import com.pipefy.screensInterface.LoginInterface;
import com.pipefy.utils.CryptUtils;
import io.cucumber.java.en.*;

import static org.junit.Assert.assertTrue;

public class LoginSteps {

    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    private LoginInterface loginScreen = ScreenFactory.getLoginScreen();
    private HomeInterface homeScreen = ScreenFactory.getHomeScreen();

    @Given("I access login option")
    public void iAccessLoginOption() {
        loginScreen.clickOnLoginButton();
        assertTrue(loginScreen.checkEmailInputIsVisible());
        assertTrue(loginScreen.checkPasswordInputIsVisible());
        assertTrue(loginScreen.checkLoginButtonIsVisible());
        assertTrue(loginScreen.checkLoginGoogleButtonIsVisible());
        assertTrue(loginScreen.checkLoginGithubButtonIsVisible());
        assertTrue(loginScreen.checkLoginCannotButtonIsVisible());
        assertTrue(loginScreen.checkSsoEnterpriseButtonIsVisible());
    }

    @When("I login with user email {string}")
    public void iLoginWithUserEmail(String userId) {
        loginScreen.loginWithEmail(configurationManager.getUser(userId),
                CryptUtils.decrypt(configurationManager.getPassword(), configurationManager.getAutomationSecretKey()));
        loginScreen.clickOnLoginButton();
    }

    @Then("I validate information about {string} company")
    public void iValidateInformationAboutInitialScreen(String value) {
        assertTrue(homeScreen.checkCompanyNameIsVisible(value));
        assertTrue(homeScreen.checkAvatarUserImageIsVisible());
        assertTrue(homeScreen.checkSearchEditIsVisible());
        assertTrue(homeScreen.getUserName().contains(value));
        assertTrue(homeScreen.checkMyPipesIsVisible());
        assertTrue(homeScreen.checkMyPipesDescriptionIsVisible());
        assertTrue(homeScreen.checkOtherPipes());
        assertTrue(homeScreen.checkOtherPipesDescription());
    }
}