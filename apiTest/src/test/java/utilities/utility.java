package utilities;

import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.aventstack.extentreports.ExtentTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class utility {
	private Response RestResponse;
	private RequestSpecification RestRequestSpecification;
	
	public RequestSpecification createRequest(ExtentTest test)
	 {
		RestAssured.baseURI = "https://api-ssl.bitly.com";
     	RestRequestSpecification = RestAssured.given();
     	test.createNode("Host initiated https://api-ssl.bitly.com");
     	return RestRequestSpecification;
	 }
	 
	 public RequestSpecification addHeader(String Key, String Value,ExtentTest test)
	 {
		 RestRequestSpecification =(Key!=null&&Value!=null)? RestRequestSpecification.headers(Key,Value): RestRequestSpecification;
		 if(Key!=null&&Value!=null)test.createNode("headers added to the request: Key"+Key+"Value:"+Value);
		 return RestRequestSpecification;
	 }
	 
	 public RequestSpecification addParam(String Key, String Value,ExtentTest test)
	 {
		 RestRequestSpecification =(Key!=null&&Value!=null)? RestRequestSpecification.queryParams(Key,Value): RestRequestSpecification;
		 if(Key!=null&&Value!=null)test.createNode("Query parameter added to the request: Key"+Key+"Value:"+Value);
		 return RestRequestSpecification;
	 }
	 
	 public Response sendGetRequest(String ResoucePath,ExtentTest test)
	 {
		RestResponse = RestRequestSpecification.when().get(ResoucePath).then().extract().response();
		test.createNode("The get request is hit for the resource:"+ResoucePath);
		test.createNode("Response recieved as :"+RestResponse.body().asString());
		return RestResponse;
	 }
	 
	 public Response sendPostRequest(String ResoucePath,String body,ExtentTest test)
	 {
		RestRequestSpecification = (body!=null)?RestRequestSpecification.body(body): RestRequestSpecification;
 		RestResponse = RestRequestSpecification.when().body(body).post(ResoucePath).then().extract().response();
 		test.createNode("The Post request is hit for the resource:"+ResoucePath+"and Json body passed is: "+ body);
		test.createNode("Response recieved as :"+RestResponse.body().asString());
     	return RestResponse;
	 }
	 
	 public String getSpecificKeyValuefromJsonObject(String body, String Key) throws ParseException
	 {
		 
	     JSONParser parser = new JSONParser();
         Object obj = parser.parse(body);
         JSONObject jsonObject = (JSONObject) obj;
		 return jsonObject.get(Key).toString();
		 		 
	 }
}
