package com.pipefy.screens;

import com.pipefy.screens.home.HomeScreen;
import com.pipefy.screens.login.LoginScreen;
import com.pipefy.screens.profile.ProfileScreen;
import com.pipefy.screensInterface.*;

public class ScreenFactory {

    public static LoginInterface getLoginScreen() {
        return new LoginScreen();
    }

    public static HomeInterface getHomeScreen() {
        return new HomeScreen();
    }

    public static ProfileInterface getProfileScreen() {
        return new ProfileScreen();
    }
}
