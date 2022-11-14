package com.pipefy.screens.home;

import com.pipefy.screensInterface.HomeInterface;
import com.pipefy.steps.DriverSteps;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class HomeScreen extends DriverSteps implements HomeInterface {
    public String companyName = "new UiSelector().textContains(\"${companyName}\")";

    @AndroidFindBy(id = "image_user_avatar")
    public MobileElement avatarUserImage;

    @AndroidFindBy(id = "edit_search")
    public MobileElement searchEdit;

    @AndroidFindBy(id = "text_welcome")
    public MobileElement welcomeText;

    @AndroidFindBy(id = "text_my_pipes")
    public MobileElement myPipes;

    @AndroidFindBy(id = "text_my_pipes_description")
    public MobileElement myPipesDescription;

    @AndroidFindBy(id = "text_other_pipes")
    public MobileElement otherPipes;

    @AndroidFindBy(id = "text_other_pipes_description")
    public MobileElement otherPipesDescription;

    public HomeScreen() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Override
    public boolean checkCompanyNameIsVisible(String company) {
        return isElementVisible(driver.findElement(MobileBy.AndroidUIAutomator(this.companyName.replace("${companyName}", company))));
    }

    @Override
    public boolean checkAvatarUserImageIsVisible() {
        return isElementVisible(avatarUserImage);
    }

    @Override
    public boolean checkSearchEditIsVisible() {
        return isElementVisible(searchEdit);
    }

    @Override
    public String getUserName() {
        return getTextFromElement(welcomeText);
    }

    @Override
    public boolean checkMyPipesIsVisible() {
        return isElementVisible(myPipes);
    }

    @Override
    public boolean checkMyPipesDescriptionIsVisible() {
        return isElementVisible(myPipesDescription);
    }

    @Override
    public boolean checkOtherPipes() {
        return isElementVisible(otherPipes);
    }

    @Override
    public boolean checkOtherPipesDescription() {
        return isElementVisible(otherPipesDescription);
    }

    @Override
    public void clickOnAvatarUserImage() {
        waitUntilElementIsVisible(avatarUserImage);
        clickElement(avatarUserImage);
    }
}
