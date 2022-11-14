package com.pipefy.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.Scenario;
import io.cucumber.messages.internal.com.google.gson.Gson;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

public class CucumberReportUtils {

    private static Logger logger = LoggerFactory.getLogger(CucumberReportUtils.class);
    private static final String cucumberReportJson = "target/reports/CucumberReport.json";
    private static final String cucumberRerunReportJson = "target/reports/CucumberReportRerunTests.json";
    private static final Gson gson = new Gson();

    public CucumberReportUtils() {}

    public static List<Map> getFailedTestsInfo() throws IOException {
        List<Map> features = readCucumberJsonReport(cucumberReportJson);

        List<Map> failedScenarios = new ArrayList<>();
        List<Map> passedScenariosRerun = getRerunPassedTestsInfo();

        for(Map feature : features) {
                String featureName = (String) feature.get("name");
                List<Map> elements = (List<Map>) feature.get("elements");

                for (Map scenario : elements) {
                    String scenarioName = (String) scenario.get("name");
                    String keyword = (String) scenario.get("keyword");
                    boolean passed = false;
                    Map item = new HashMap();
                    item.put("feature", featureName);
                    item.put("scenario", scenarioName.isBlank() ? keyword : scenarioName);
                    for (Map passedScenario : passedScenariosRerun) {
                        if (passedScenario.containsValue(scenarioName)) {
                            passed = true;
                            break;
                        }
                    }
                    List<Map> steps = (List<Map>) scenario.get("steps");
                    for (Map step : steps) {
                        Map result = (Map) step.get("result");
                        if (result.get("status").equals("failed") && !passed) {
                            item.put("step", step.get("name"));
                            item.put("status", result.get("status"));
                            item.put("duration", result.get("duration"));
                            item.put("error_message", result.get("error_message"));
                            failedScenarios.add(item);
                            break;
                        }
                    }
                }
      }
        return failedScenarios;
    }

    public static List<Map> getRerunPassedTestsInfo() throws IOException {
        List<Map> features = readCucumberJsonReport(cucumberRerunReportJson);
        List<Map> rerunPassedScenarios = new ArrayList<>();

        boolean passed = true;
        for(Map feature : features) {
            String featureName = (String) feature.get("name");
            List<Map> elements = (List<Map>) feature.get("elements");

            for (Map scenario : elements) {
                String scenarioName = (String) scenario.get("name");
                String keyword = (String) scenario.get("keyword");

                Map item = new HashMap();
                item.put("feature", featureName);
                item.put("scenario", scenarioName.isBlank() ? keyword : scenarioName);

                List<Map> steps = (List<Map>) scenario.get("steps");

                for (Map step : steps) {

                    Map result = (Map) step.get("result");
                    steps.size();
                    if (result.get("status").equals("failed")) {
                        passed = false;
                    }else if (result.get("status").equals("passed")){
                        passed = true;
                    }
                }
                if(passed){
                    rerunPassedScenarios.add(item);
                }
            }
        }
        return rerunPassedScenarios;
    }

    private static List<Map> readCucumberJsonReport(String cucumberReport) throws FileNotFoundException {
        InputStream source = new FileInputStream(new File(cucumberReport));
        Reader reader = new InputStreamReader(source);
        return gson.fromJson(reader, List.class);
    }

    public static void takeScreenshot(AppiumDriver<MobileElement> driver, Scenario scenario){
        try{
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());

        }catch (WebDriverException e){
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        //This list will store all failed steps with its respectively scenario and feature
        List<Map> failedScenarios = getFailedTestsInfo();
        for(Map failed : failedScenarios) {
            System.out.println("Feature: " + failed.get("feature"));
            System.out.println("Scenario: " + failed.get("scenario"));
            System.out.println("Step: " + failed.get("step"));
        }
    }
}