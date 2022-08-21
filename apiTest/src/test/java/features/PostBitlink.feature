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
    

  
