@tag
Feature: POST butlinks API Method
  Create a bitlink

  @test
  Scenario: Verify POSTbutlinks: status code when Token is not passed
    Given The POSTbutlinks API is up and running 
    When Header is not passed
    Then status code should be 403 for POSTbutlinks
    
    
  @test
  Scenario: Verify POSTbutlinks: status code when body is not passed
    Given The POSTbutlinks API is up and running 
    When Token is passed in header
    Then status code should be 422 for POSTbutlinks
    
  @test
  Scenario: Verify POSTbutlinks: status code when mandatory parameter is not passed in JsonBody
    Given The POSTbutlinks API is up and running 
    When Token is passed in header
    And Mandatory parameter is not passed in the Json
    Then status code should be 400 for POSTbutlinks
    
   @test
  Scenario: Verify POSTbutlinks: Error message when long url is not passed in JsonBody
    Given The POSTbutlinks API is up and running 
    When Token is passed in header
    And Mandatory parameter is not passed in the Json
    Then Error message should be You must INVALID_ARG_LONG_URL
    
  @test
  Scenario: Verify POSTbutlinks: Error message and status code when deeplink is given for user who doesnt have access
    Given The POSTbutlinks API is up and running 
    When Token is passed in header
    And Json is provided with deeplink
    Then status code should be 402 for POSTbutlinks
    And Error message should be You must upgrade your account to access this resource
    
    
  @test
  Scenario: Verify POSTbutlinks: status code when valid json is passed
    Given The POSTbutlinks API is up and running 
    When Token is passed in header
    And Valid Json is provided
    Then status code should be 200 for POSTbutlinks
    
   @test
  Scenario: Verify POSTbutlinks: Verify the output when a bitink is created
    Given The POSTbutlinks API is up and running 
    When Token is passed in header
    And Valid Json is provided
    Then The ouput should return the bitlink created

  
