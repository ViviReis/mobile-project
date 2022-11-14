package com.pipefy.config;

import java.io.*;
import java.util.*;

public class ConfigurationManager {

    private static volatile ConfigurationManager obj = null;
    private String platform;
    private String environment;
    private String appPath;
    private String appTarget;
    private String appVersion;
    private String appiumPath;
    private String appiumHost = "127.0.0.1";
    private String automationSecretKey;
    private String tags;
    private TestConfig testConfig;
    private boolean shouldAcceptAlertsOnIos;

    private final String credentialsFilePath = "src/test/resources/data/credentials.properties";
    private Properties credentials = new Properties();
    private String buildAlias;
    private ConfigurationManager() {
    }

    public synchronized static ConfigurationManager getInstance() {
        if (obj == null) {
            synchronized (ConfigurationManager.class) {
                if (obj == null)
                    obj = new ConfigurationManager();
            }
        }
        return obj;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getAppiumPath() {
        return appiumPath;
    }

    public void setAppiumPath(String appiumPath) {
        this.appiumPath = appiumPath;
    }

    public String getAppiumHost() {
        return appiumHost;
    }

    public void setAppiumHost(String appiumHost) {
        this.appiumHost = appiumHost;
    }

    public String getAutomationSecretKey() {
        return automationSecretKey;
    }

    public String getPassword() {
        return getCredentials().getProperty("user.password");
    }

    public String getUser(String userId){
       return getCredentials().getProperty("user.email" + userId);
    }

    public void setAutomationSecretKey(String automationSecretKey) {
        this.automationSecretKey = automationSecretKey;
    }

    public boolean isAndroid() {
        return this.platform.equalsIgnoreCase(DefaultConstants.ANDROID_PLATFORM);
    }

    public boolean isIOS() {
        return this.platform.equalsIgnoreCase(DefaultConstants.IOS_PLATFORM);
    }

    public boolean isLocal() {
        return this.environment.equalsIgnoreCase(DefaultConstants.LOCAL);
    }

    public String getBuildAlias() {
        return buildAlias;
    }

    public void setBuildAlias(String buildAlias) {
        this.buildAlias = buildAlias;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Properties getCredentials() {
        return credentials;
    }

    public TestConfig getTestConfig() {
        return testConfig;
    }

    public void setTestConfig(TestConfig testConfig) {
        this.testConfig = testConfig;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    public void setAppTarget(String appTarget){
        this.appTarget = appTarget;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public boolean shouldAcceptAlertsOnIos() {
        return shouldAcceptAlertsOnIos;
    }

    public void setShouldAcceptAlertsOnIos(boolean shouldAcceptAlertsOnIos) {
        this.shouldAcceptAlertsOnIos = shouldAcceptAlertsOnIos;
    }

    public void setup() throws IOException {
        initProperties();
        initEnvVariables();
    }

    private void initProperties() throws IOException {

        String platform = System.getProperty("platform");
        if (platform == null) {
            throw new RuntimeException("Missing 'platform' property");
        }
        setPlatform(platform);

        String environment = System.getProperty("environment");
        if (environment == null) {
            throw new RuntimeException("Missing 'environment' property");
        }
        setEnvironment(environment);

        String appPath = System.getProperty("appPath");
        if(appPath == null && isLocal()){
            throw new RuntimeException("Missing 'appPath' property");
        }else if(appPath != null && !appPath.equals("")){
            setAppPath(appPath);
        }

        String tags = System.getProperty("cucumber.filter.tags");
        if (tags == null || tags.isBlank()) {
            setTags("All tests");
        } else {
            setTags(tags);
        }

        credentials.load(new FileInputStream(new File(credentialsFilePath)));
    }

    private void initEnvVariables() {

        String automationSecretKey = System.getenv("AUTOMATION_SECRET");
        if (automationSecretKey == null) {
            throw new RuntimeException("Missing AUTOMATION_SECRET env variable");
        }
        setAutomationSecretKey(automationSecretKey);

        String buildAlias = System.getenv("BUILD_ALIAS");
        if (buildAlias != null) {
            setBuildAlias(buildAlias);
        } else {
            setBuildAlias(getPlatform() + " - " + System.getenv("USER") + " - " + getTags() + " " + Calendar.getInstance().getTimeInMillis());
        }

        String appVersion = System.getenv("S3_APP_MAJOR_VERSION");
        if (appVersion != null) {
            setAppVersion(appVersion);
        }
    }
}