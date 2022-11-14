package com.pipefy.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;

@CucumberOptions(
        monochrome = true,
        plugin = {"pretty", "com.pipefy.utils.ExtentCucumberAdapter:",
                "json:target/reports/CucumberReportRerunTests.json", "testng:target/reports/CucumberReportRerunTests.xml", "html:target/reports/CucumberReportRerunTests.html"},
        features = "@target/failed_scenarios.txt",
        glue = {"com.pipefy"})
public class ParallelReRunRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(name = "scenarios", parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
