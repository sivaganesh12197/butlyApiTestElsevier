package stepDefinitions;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.gson.JsonParser;

import io.cucumber.java.*;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.*;

public class Definitions {
	private Response RestResponse;
	private RequestSpecification RestRequestSpecification;
	public static ExtentSparkReporter reporter ;
	public static ExtentReports extent;
	public ExtentTest test;
	utility utils = new utility();
	
	@BeforeAll
	public static void beforeAll()
	{
		String path =System.getProperty("user.dir")+"\\reports\\index.html";
    	reporter = new ExtentSparkReporter(path);
    	reporter.config().setReportName("Bitly API Automation Results");
    	reporter.config().setDocumentTitle("Test Results");
    	extent =new ExtentReports();
    	extent.attachReporter(reporter);
    	extent.setSystemInfo("Tester", "Siva ganeesh");
	}

	@Before
	public void beforeHook(Scenario s) {
		test = extent.createTest(s.getName());
	}

	@After
	public void afterHook(Scenario s){
	
		
	}

	@BeforeStep
	public  void beforeStep(Scenario s){
		
	
		
	}
	@AfterStep
	public  void afterStep(Scenario s){
		 if(s.getStatus().toString()=="PASSED")
	       {
	    	   test.log(com.aventstack.extentreports.Status.PASS, "Step Passed");
	    	   
	       }
	       else
	       {
	    	   test.log(com.aventstack.extentreports.Status.FAIL, "Step Failed"); 
	       }
		
	}
	
	@AfterAll
	public static void afterAll()
	{
		extent.flush();
	}
	
	//*********************************************************
    //*********************GIVEN*******************************
    //*********************************************************
	  

	    @Given("^The GetGroupDetail API is up and running$")
	    public void the_getgroupdetail_api_is_up_and_running() throws Throwable {
	    	RestRequestSpecification =	utils.createRequest(test);
	    }  
	    
	    @Given("^The GetSortedBitlinksOfgroup API is up and running$")
	    public void the_getsortedbitlinksofgroup_api_is_up_and_running() throws Throwable {
	    	RestRequestSpecification =	utils.createRequest(test);
	    }
	
	
	//*********************************************************
    //*********************WHEN*******************************
    //*********************************************************

	
	  @When("^Header is not passed$")
	    public void header_is_not_passed() throws Throwable {
		 RestRequestSpecification =  utils.addHeader(null,null,test);
	    }
	  
	  @When("^Token is passed in header$")
	    public void token_is_passed_in_header() throws Throwable {	  
		  RestRequestSpecification =  utils.addHeader("Authorization","Bearer e98cdcab4a34d75f040d3909487e9836f6dc04ef",test);
	    }
	  
	  @When("^valid guid and sort value is passed$")
	    public void valid_guid_and_sort_value_is_passed() throws Throwable {
		  RestResponse =  utils.sendGetRequest("/v4/groups/Bm8j9wfsBvw/bitlinks/clicks",test);
	    }
	  
	  @When("^invalid guid and correct sort value is passed$")
	    public void invalid_guid_and_correct_sort_value_is_passed() throws Throwable {
		  RestResponse =  utils.sendGetRequest("/v4/groups/1232/bitlinks/clicks",test);
	    }
	  


	
	//*********************************************************
    //*********************THEN*******************************
    //*********************************************************
	
	 
	 @Then("^status code should be 403$")
	    public void status_code_should_be_403() throws Throwable {
		 RestResponse =  utils.sendGetRequest("/v4/groups/1223",test);
		Assert.assertEquals(RestResponse.statusCode(),403);
	    }
	 
	 @Then("status code should be 200")
	    public void status_code_should_be_200() throws Throwable {
		 RestResponse =  utils.sendGetRequest("/v4/groups/Bm8j9wfsBvw",test);
		Assert.assertEquals(RestResponse.statusCode(),200);
	    } 

	    @Then("^Error should should be as You are currently forbidden to access this resource$")
	    public void error_should_should_be_as_you_are_currently_forbidden_to_access_this_resource() throws Throwable {
	     String body =   RestResponse.body().asString();	   
         Assert.assertEquals(utils.getSpecificKeyValuefromJsonObject(body, "description"),"You are currently forbidden to access this resource.");
	    }
	    
	    @Then("^the response should return the group for the \"([^\"]*)\" passed in header$")
	    public void the_response_should_return_the_group_for_the_guid_passed_in_header(String guid) throws Throwable {
	    	 RestResponse =  utils.sendGetRequest("/v4/groups/Bm8j9wfsBvw",test);
	    	String body =   RestResponse.body().asString();
	         Assert.assertEquals(utils.getSpecificKeyValuefromJsonObject(body, "guid"),guid);
	    }

	    @Then("status code should be 403 for GetSortedBitlinksOfgroup")
	    public void status_code_should_be_403_for_getsortedbitlinksofgroup() throws Throwable {
	    	 RestResponse =  utils.sendGetRequest("/v4/groups/3445/bitlinks/clicks",test);
	 		Assert.assertEquals(RestResponse.statusCode(),403);
	    }

	    @Then("status code should be 200 for GetSortedBitlinksOfgroup")
	    public void status_code_should_be_200_for_getsortedbitlinksofgroup() throws Throwable {
	    	 RestResponse =  utils.sendGetRequest("/v4/groups/Bm8j9wfsBvw/bitlinks/clicks",test);
		 		Assert.assertEquals(RestResponse.statusCode(),200);
	    }
	    
	 
	//*********************************************************
	    //*********************utilities*******************************
	    //*********************************************************
	
	
		
	        	
}
