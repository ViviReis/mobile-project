package com.pipefy.screens.login;

import com.pipefy.screensInterface.LoginInterface;
import com.pipefy.steps.DriverSteps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import static com.pipefy.config.DefaultConstants.APP_PACKAGE;

public class LoginScreen extends DriverSteps implements LoginInterface {

    @AndroidFindBy(id = "button_login")
    public MobileElement loginButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\""+APP_PACKAGE+":id/input_field\").textContains(\"Email\")")
    public MobileElement emailInput;

    @AndroidFindAll(value = {
            @AndroidBy(uiAutomator = "new UiSelector().textContains(\"Senha\")"),
            @AndroidBy(uiAutomator = "new UiSelector().textContains(\"Password\")")})
    public MobileElement passwordInput;

    @AndroidFindBy(id = "button_google_login")
    public MobileElement loginGoogleButton;

    @AndroidFindBy(id = "button_github_login")
    public MobileElement loginGithubButton;

    @AndroidFindBy(id = "button_cannot_login")
    public MobileElement loginCannotButton;

    @AndroidFindBy(id = "button_enterprise_sso")
    public MobileElement ssoEnterpriseButton;

    public LoginScreen() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Override
    public void clickOnLoginButton() {
        clickElement(loginButton, 60);
    }

    @Override
    public boolean checkEmailInputIsVisible() {
        return isElementVisible(emailInput);
    }

    @Override
    public boolean checkPasswordInputIsVisible() {
        return isElementVisible(passwordInput);
    }

    @Override
    public boolean checkLoginButtonIsVisible() {
        return isElementVisible(loginButton);
    }

    @Override
    public boolean checkLoginGoogleButtonIsVisible() {
        return isElementVisible(loginGoogleButton);
    }

    @Override
    public boolean checkLoginGithubButtonIsVisible() {
        return isElementVisible(loginGithubButton);
    }

    @Override
    public boolean checkLoginCannotButtonIsVisible() {
        return isElementVisible(loginCannotButton);
    }

    @Override
    public boolean checkSsoEnterpriseButtonIsVisible() {
        return isElementVisible(ssoEnterpriseButton);
    }

    @Override
    public void loginWithEmail(String email, String password) {
        sendKeysToElement(emailInput, email);
        sendKeysToElement(passwordInput, password);
    }
}
