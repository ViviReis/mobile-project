package com.pipefy.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        monochrome = true,
        plugin = {"pretty", "com.pipefy.utils.ExtentCucumberAdapter:",
                "json:target/reports/CucumberReportRerunTests.json", "testng:target/reports/CucumberReportRerunTests.xml", "html:target/reports/CucumberReportRerunTests.html"},
        features = "@target/failed_scenarios_default.txt",
        glue = {"com.pipefy"})
public class DefaultReRunRunner extends AbstractTestNGCucumberTests {
}
