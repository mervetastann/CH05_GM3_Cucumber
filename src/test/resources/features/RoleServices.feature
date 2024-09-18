@Api
Feature: Role Services
  @GetRoleServices
  Scenario: Get Role Services
    Given I set the base specification for GM API
    When I send a GET request to "/role"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And each permission in the response should contain "Accountant"


  @GetRoleId
  Scenario: Get Role ID
    Given I set the URL and spec
    When I make a GET request
    Then I should receive a status code of 200
    And the response should be a JSON object
    And name of the role name in the response must be "Business Owner"

  @GetRoleAppId
  Scenario: Get Role AppID
    Given I set the URL and spec appId
    When I make a GET request AppID
    Then I should receive a status code of 200
    And the response should be a JSON object
    And name of the first element in the response must be "Accountant"

  @GetRoleOrgId
  Scenario: Get Role OrgID
    Given I set the URL and spec OrgId
    When I make a GET request OrgID
    Then I should receive a status code of 200
    And the response should be a JSON object
    And name of the first element in the response must be "Accountant"

