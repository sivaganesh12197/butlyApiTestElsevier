Feature: Get Group by GUID method
  Retrieve Sorted Bitlinks for Group

  @test
  Scenario: Verify GetGroupDetail: status code when Token is not passed
    Given The GetGroupDetail API is up and running 
    When Header is not passed
    Then status code should be 403
    
  @test
  Scenario: Verify GetGroupDetail: Error Description when invalid guid is passed
    Given The GetGroupDetail API is up and running 
    When Token is passed in header
    Then status code should be 403
    And Error should should be as You are currently forbidden to access this resource

  @test
  Scenario: Verify GetGroupDetail: status code when a valid Token and guid is passed
    Given The GetGroupDetail API is up and running 
    When Token is passed in header
    Then status code should be 200
    
  @test
  Scenario: Verify GetGroupDetail: whether the group for the provided guid is returned in output
    Given The GetGroupDetail API is up and running 
    When Token is passed in header 
    Then the response should return the group for the "Bm8j9wfsBvw" passed
    
    
    
  