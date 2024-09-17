@UserGroupType
Feature:User Group Type Service Module


  @UserGroupTypeGet
  Scenario: All user group types and information should be displayed
    Given I set the base specification for GM API
    When I send a GET request to "/user-group-type"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And the response must contain "id", "name", or "description"

  @UserGroupTypeById
  Scenario:All requested information should be displayed according to the id of user group type
    Given I set the base specification for GM API
    When I send a GET request to "user-group-type/1"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And  the response have a field "id" with value 1
    And the response must have a field "name" with value "Department"
    And the response must have a field "description" with value "User account is deactivated, and not authorized to access any the application"


  @UserGroupTypeIdV1
  Scenario: Verify that a principal with different levels of 'organization' permissions can successfully fetch the summary of user group types.
    Given I set the base specification for GM API
    When I send a GET request to "v1/organization/1724253527891397/user-group-type/3/summary"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And the response must have a field "name[0]" with value "TeamTurk"








