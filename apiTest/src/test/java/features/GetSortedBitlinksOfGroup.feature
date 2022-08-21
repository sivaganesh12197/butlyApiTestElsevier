Feature: Get Sorted Bitlinks of Group API method
  Retrieve all the Bitlinks for Group in a sorted order

   @test
  Scenario: Verify GetSortedBitlinksOfgroup status code when Token is not passed
    Given The GetSortedBitlinksOfgroup API is up and running 
    When Header is not passed
    And valid guid and sort value is passed
    Then status code should be 403 for GetSortedBitlinksOfgroup
    
  @test
  Scenario: Verify GetSortedBitlinksOfgroup Error Description when invalid guid is passed
   Given The GetSortedBitlinksOfgroup API is up and running 
    When Token is passed in header
    And invalid guid and correct sort value is passed
    Then status code should be 403 for GetSortedBitlinksOfgroup
    And Error should should be as You are currently forbidden to access this resource

  @test
  Scenario: Verify GetSortedBitlinksOfgroup status code when a valid Token and guid is passed
    Given The GetSortedBitlinksOfgroup API is up and running 
    When Token is passed in header
    Then status code should be 200 for GetSortedBitlinksOfgroup
    

    
    
    
  