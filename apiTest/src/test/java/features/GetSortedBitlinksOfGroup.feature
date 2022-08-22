Feature: Get Sorted Bitlinks of Group API method
  Retrieve all the Bitlinks for Group in a sorted order

  @test
  Scenario: Verify GetSortedBitlinksOfgroup: status code when a valid Token and guid is passed
    Given The GetSortedBitlinksOfgroup API is up and running 
    When Token is passed in header
    And valid guid and sort value is passed
    Then status code should be 200 for GetSortedBitlinksOfgroup
	
  @test
  Scenario: Verify GetSortedBitlinksOfgroup: Error Description when invalid guid is passed
    Given The GetSortedBitlinksOfgroup API is up and running 
    When Token is passed in header
    And invalid guid and correct sort value is passed
    Then status code should be 403 for GetSortedBitlinksOfgroup
    And Error should should be as You are currently forbidden to access this resource
	
   @test
   Scenario: Verify GetSortedBitlinksOfgroup: status code when Token is not passed
    Given The GetSortedBitlinksOfgroup API is up and running 
    When Header is not passed
    And valid guid and sort value is passed
    Then status code should be 403 for GetSortedBitlinksOfgroup

  @test
  Scenario: Verify GetSortedBitlinksOfgroup: status code when no query parameter is passed
    Given The GetSortedBitlinksOfgroup API is up and running 
    When Token is passed in header
    And valid guid and sort value is passed
    Then status code should be 200 for GetSortedBitlinksOfgroup
  
  @test
  Scenario: Verify GetSortedBitlinksOfgroup: status code when invalid sort is passed
    Given The GetSortedBitlinksOfgroup API is up and running 
    When Token is passed in header
    And valid guid and invalid sort value is passed
    Then status code should be 400 for GetSortedBitlinksOfgroup
    And the Error message should state "INVALID_ARG_SORT" in output
     
  @test
  Scenario: Verify GetSortedBitlinksOfgroup: whether the links are sorted based clicks
    Given The GetSortedBitlinksOfgroup API is up and running 
    When Token is passed in header
    And the list should be sorted based on the number of clicks
       
  @test
  Scenario: Verify GetSortedBitlinksOfgroup: No of items are returned based on the applied parameter size
    Given The GetSortedBitlinksOfgroup API is up and running 
    When Token is passed in header
    And Parameter "size" is passed as "2"
    And valid guid and sort value is passed
    Then status code should be 200 for GetSortedBitlinksOfgroup
    And should return "2" items
    
  @test
  Scenario: Verify GetSortedBitlinksOfgroup: status code and error when invalid size is passed
    Given The GetSortedBitlinksOfgroup API is up and running 
    When Token is passed in header
    And Parameter "size" is passed as "asdd"
    And valid guid and sort value is passed
    Then status code should be 400 for GetSortedBitlinksOfgroup
    And the Error message should state "INVALID_ARG_SIZE" in output
  
  @test
  Scenario: Verify GetSortedBitlinksOfgroup: whether the links in output are retrived based on the provided unit_reference
    Given The GetSortedBitlinksOfgroup API is up and running 
    When Token is passed in header
    And Parameter "unit_reference" is passed as "2022-08-19T15:53:02+0000"
    And valid guid and sort value is passed
    Then status code should be 200 for GetSortedBitlinksOfgroup
    And should return "2" items
	
  @test
  Scenario: Verify GetSortedBitlinksOfgroup: status code and error when invalid unit_reference is passed
    Given The GetSortedBitlinksOfgroup API is up and running 
    When Token is passed in header
    And Parameter "unit_reference" is passed as "asdd"
    And valid guid and sort value is passed
    Then status code should be 400 for GetSortedBitlinksOfgroup
    And the Error message should state "INVALID_ARG_UNIT_REFERENCE" in output
    
  @test
  Scenario: Verify GetSortedBitlinksOfgroup: whether no links in output are retrived when a unit_reference provided has no hit is created
    Given The GetSortedBitlinksOfgroup API is up and running 
    When Token is passed in header
    And Parameter "unit_reference" is passed as "2022-08-08T15:53:02+0000"
    And valid guid and sort value is passed
    Then status code should be 200 for GetSortedBitlinksOfgroup
    And should return "0" items
           
  @test
  Scenario: Verify GetSortedBitlinksOfgroup: status code and error when invalid unit is passed
    Given The GetSortedBitlinksOfgroup API is up and running 
    When Token is passed in header
    And Parameter "unit" is passed as "asdd"
    And valid guid and sort value is passed
    Then status code should be 400 for GetSortedBitlinksOfgroup
    And the Error message should state "INVALID_ARG_UNIT" in output
    
  @test
  Scenario: Verify GetSortedBitlinksOfgroup: whether the items returns links based on the hits on specific time unit
    Given The GetSortedBitlinksOfgroup API is up and running 
    When Token is passed in header
    And Parameter "unit" is passed as "hour"
    And Parameter "units" is passed as "3"
    And valid guid and sort value is passed
    Then status code should be 200 for GetSortedBitlinksOfgroup
