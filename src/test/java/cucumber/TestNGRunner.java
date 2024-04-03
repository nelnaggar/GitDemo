package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber", glue = "rahulshettyacademy.stepdefinitions", monochrome = true, tags = "@Regression", plugin = {"html:target/cucumber.html"})
public class TestNGRunner extends AbstractTestNGCucumberTests{
	

}
