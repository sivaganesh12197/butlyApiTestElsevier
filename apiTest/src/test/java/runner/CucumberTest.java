package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(		
		features = {"classpath:features"},
		glue = "stepDefinitions",
		dryRun=false
		
)
public class CucumberTest {




}
