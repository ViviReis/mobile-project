package com.pipefy.screensInterface;

public interface LoginInterface {

    void clickOnLoginButton();
    boolean checkEmailInputIsVisible();
    boolean checkPasswordInputIsVisible();
    boolean checkLoginButtonIsVisible();
    boolean checkLoginGoogleButtonIsVisible();
    boolean checkLoginGithubButtonIsVisible();
    boolean checkLoginCannotButtonIsVisible();
    boolean checkSsoEnterpriseButtonIsVisible();
    void loginWithEmail(String email, String password);
}
