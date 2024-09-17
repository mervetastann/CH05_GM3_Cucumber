@Membership
Feature: Get Check Membership


  Scenario: Get All Membership Type
    Given I set the base specification for GM API
    When I send a GET request to "v1/application/2/membership"
    Then the response status code should be 200
    And the response have a field "user_id[1]" with value 680
    And print response body

  Scenario: Get All Membership Type
    Given I set the base specification for GM API
    When I send a GET request to "v1/membership"
    Then the response status code should be 200
    And the response have a field "user_id[1]" with value 680
    And print response body

  @
  Scenario: Verify API response genel
    Given I set the base specification for GM API
    When I send a GET request to "v1/application/2/membership"
    Then the response status code should be 200
    And the response count is displayed
    And the response data length should be 18
    And print response body


