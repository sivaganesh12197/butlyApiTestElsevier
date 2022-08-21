package stepDefinitions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
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
	  

	    @Given("^The GetGroupDetail API is up and running")
	    public void the_getgroupdetail_api_is_up_and_running() throws Throwable {
	    	RestRequestSpecification =	utils.createRequest(test);
	    }  
	    
	    @Given("^The GetSortedBitlinksOfgroup API is up and running")
	    public void the_getsortedbitlinksofgroup_api_is_up_and_running() throws Throwable {
	    	RestRequestSpecification =	utils.createRequest(test);
	    }
	    
	    @Given("^The POSTbutlinks API is up and running$")
	    public void the_postbutlinks_api_is_up_and_running() throws Throwable {
	    	RestRequestSpecification =	utils.createRequest(test);
	    }
	
	
	//*********************************************************
    //*********************WHEN*******************************
    //*********************************************************

	
	  @When("Header is not passed")
	    public void header_is_not_passed() throws Throwable {
		 RestRequestSpecification =  utils.addHeader(null,null,test);
	    }
	  
	  @When("Token is passed in header")
	    public void token_is_passed_in_header() throws Throwable {	  
		  RestRequestSpecification =  utils.addHeader("Authorization","Bearer e98cdcab4a34d75f040d3909487e9836f6dc04ef",test);
	    }
	  
	  @When("valid guid and sort value is passed")
	    public void valid_guid_and_sort_value_is_passed() throws Throwable {
		  RestResponse =  utils.sendGetRequest("/v4/groups/Bm8j9wfsBvw/bitlinks/clicks",test);
	    }
	  
	  @When("invalid guid and correct sort value is passed")
	    public void invalid_guid_and_correct_sort_value_is_passed() throws Throwable {
		  RestResponse =  utils.sendGetRequest("/v4/groups/1232/bitlinks/clicks",test);
	    }
	  
	  @When("valid guid and invalid sort value is passed")
	    public void valid_guid_and_invalid_sort_value_is_passed() throws Throwable {
		  RestResponse =  utils.sendGetRequest("/v4/groups/1232/bitlinks/invalidSort",test);
	    }
	  
	  @When("^Parameter \"([^\"]*)\" is passed as \"([^\"]*)\"$")
	    public void parameter_something_is_passed_as_something(String strArg1, String strArg2) throws Throwable {
		  RestRequestSpecification =  utils.addParam(strArg1,strArg2,test);
	    }
	  


	
	//*********************************************************
    //*********************THEN*******************************
    //*********************************************************
	
	 
	    @Then("status code should be 403")
	    public void status_code_should_be_403() throws Throwable {
		 RestResponse =  utils.sendGetRequest("/v4/groups/1223",test);
		Assert.assertEquals(RestResponse.statusCode(),403);
	    }
	 
	    @Then("status code should be 200")
	    public void status_code_should_be_200() throws Throwable {
		 RestResponse =  utils.sendGetRequest("/v4/groups/Bm8j9wfsBvw",test);
		Assert.assertEquals(RestResponse.statusCode(),200);
	    } 

	    @Then("Error should should be as You are currently forbidden to access this resource")
	    public void error_should_should_be_as_you_are_currently_forbidden_to_access_this_resource() throws Throwable {
	     String body =   RestResponse.body().asString();	   
         Assert.assertEquals(utils.getSpecificKeyValuefromJsonObject(body, "description"),"You are currently forbidden to access this resource.");
	    }
	    
	    @Then("^the response should return the group for the \"([^\"]*)\" passed$")
	    public void the_response_should_return_the_group_for_the_guid_passed(String guid) throws Throwable {
	    	 RestResponse =  utils.sendGetRequest("/v4/groups/Bm8j9wfsBvw",test);
	    	String body =   RestResponse.body().asString();
	         Assert.assertEquals(utils.getSpecificKeyValuefromJsonObject(body, "guid"),guid);
	    }

	    @Then("status code should be 403 for GetSortedBitlinksOfgroup")
	    public void status_code_should_be_403_for_getsortedbitlinksofgroup() throws Throwable {
	    	 RestResponse =  utils.sendGetRequest("/v4/groups/3445/bitlinks/clicks",test);
	 		Assert.assertEquals(RestResponse.statusCode(),403);
	    }
	    
	    @Then("status code should be 403 for POSTbutlinks")
	    public void status_code_should_be_403_for_postbutlinks() throws Throwable {
	    	 RestResponse =  utils.sendPostRequest("/v4/bitlinks","{}",test);
		 		Assert.assertEquals(RestResponse.statusCode(),403);
	    }
	    
	    @Then("status code should be 422 for POSTbutlinks")
	    public void status_code_should_be_422_for_postbutlinks() throws Throwable {
	    	 RestResponse =  utils.sendPostRequest("/v4/bitlinks",null,test);
		 		Assert.assertEquals(RestResponse.statusCode(),422);
	    }

	    @Then("status code should be 200 for GetSortedBitlinksOfgroup")
	    public void status_code_should_be_200_for_getsortedbitlinksofgroup() throws Throwable {
		 		Assert.assertEquals(RestResponse.statusCode(),200);
	    }
	    
	    @Then("status code should be 400 for GetSortedBitlinksOfgroup")
	    public void status_code_should_be_400_for_getsortedbitlinksofgroup() throws Throwable {
	    	Assert.assertEquals(RestResponse.statusCode(),400);
	    }
	    
	    @And("^the Error message should state \"([^\"]*)\" in output$")
	    public void the_error_message_should_state_something_in_output(String strArg1) throws Throwable {
	    	String body =   RestResponse.body().asString();
	         Assert.assertEquals(utils.getSpecificKeyValuefromJsonObject(body, "message"),strArg1);
	    }
	    
	    @Then("the list should be sorted based on the number of clicks")
	    public void the_list_should_be_sorted_based_on_the_number_of_clicks() throws Throwable {
	    	 RestResponse =  utils.sendGetRequest("/v4/groups/Bm8j9wfsBvw/bitlinks/clicks",test);
	    	String body =   RestResponse.body().asString();
	    	JSONArray arr = utils.returnJsonArrayfromObj(body,"sorted_links");
	    	List<String> returnval = new ArrayList<String>();
	    	returnval = utils.getAllValueForKeyJsonArray(arr,"clicks");
	    	for(int i=0;i<returnval.size();i++)
	    	{	    		
	    		int firstvalue = Integer.valueOf(returnval.get(i));
	    		int Second = Integer.valueOf(returnval.get(i));
	    	  Assert.assertTrue(firstvalue>=Second);	
	    	}
	    }
	    
	    @Then("^should return \"([^\"]*)\" items$")
	    public void should_return_something_items(String strArg1) throws Throwable {
	    	String body =   RestResponse.body().asString();
	    	JSONArray arr = utils.returnJsonArrayfromObj(body,"links");
	    	Assert.assertTrue(arr.size()==Integer.valueOf(strArg1));
	    }
	    
	 
	//*********************************************************
	    //*********************utilities*******************************
	    //*********************************************************
	
	
		
	        	
}
