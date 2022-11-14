package com.pipefy.screensInterface;

public interface HomeInterface {
    boolean checkCompanyNameIsVisible(String company);
    boolean checkAvatarUserImageIsVisible();
    boolean checkSearchEditIsVisible();
    String getUserName();
    boolean checkMyPipesIsVisible();
    boolean checkMyPipesDescriptionIsVisible();
    boolean checkOtherPipes();
    boolean checkOtherPipesDescription();

    void clickOnAvatarUserImage();
}
