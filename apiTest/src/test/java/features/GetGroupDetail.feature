Feature: Get Group by GUID method
  Return the detail of the specific group with the GUID

  @test
  Scenario: Verify status code when Token is not passed
    Given The GetGroupDetail API is up and running 
    When Header is not passed
    Then status code should be 403
    
  @test
  Scenario: Verify Error Description when invalid guid is passed
    Given The GetGroupDetail API is up and running 
    When Token is passed in header
    Then status code should be 403
    And Error should should be as You are currently forbidden to access this resource

  @test
  Scenario: Verify status code when a valid Token and guid is passed
    Given The GetGroupDetail API is up and running 
    When Token is passed in header
    Then status code should be 200
    
     @test
  Scenario: Verify the response when not matching guid is given
    Given The GetGroupDetail API is up and running 
    When Token is passed in header 
    Then the response should return the group for the "Bm8j9wfsBvw" passed in header
    
    
    
  