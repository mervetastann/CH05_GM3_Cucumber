@MembershipType
Feature: Get Check Membership Type

  @appliget
  Scenario: Verify API response genel
    Given I set the base specification for GM API
    When I send a GET request to "application/2/membership-type"
    Then the response status code should be 200
    And print response body

  Scenario: Get All Membership Type
    Given I set the base specification for GM API
    When I send a GET request to "/membership-type"
    Then the response status code should be 200
    And the response should have a area "name[1]" with value "Guest Membership"
    And print response body

  @MembershipType5
  Scenario: The ID value of the first JSON data is 5
    Given I set the base specification for GM API
    When I send a GET request to "/membership-type/5"
    Then the response status code should be 200
    And the user ID should be 5


  Scenario: Verify API response status code and body attribute count
    Given I set the base specification for GM API
    When I send a GET request to "/membership-type"
    Then the response status code should be 200
    And the body attribute count should be less than or equal to 10


  Scenario: Verify API response status code and name field
    Given I set the base specification for GM API
    When I send a GET request to "/membership-type?content=name"
    Then the response status code should be 200
    And the name field should be "Company Membership"

  Scenario: The ID value of the first JSON data is 6
    Given I set the base specification for GM API
    When I send a GET request to "/membership-type/6"
    Then the response status code should be 200
    And the user ID should be 6


  Scenario: Verify API response status code and body attribute count
    Given I set the base specification for GM API
    When I send a GET request to "/membership-type"
    Then the response status code should be 200
    And the body attribute count should be less than or equal to 10

  Scenario: Verify API response status code and name field
    Given I set the base specification for GM API
    When I send a GET request to "/membership-type?content=name"
    Then the response status code should be 200
    And the name field should be "Guest Membership"

  @Negativemember
  Scenario: Verify API response status code is 404
    Given I set the base specification for GM API
    When I send a GET request to "/membership-type/7"
  Then the response status code should be 404

