@Membership
Feature: Get Check Membership


  Scenario: Get All user_id
    Given I set the base specification for GM API
    When I send a GET request to "v1/application/2/membership"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And the response should contain "user_id" in all objects
    And print response body

  Scenario: Get All Membership
    Given I set the base specification for GM API
    When I send a GET request to "v1/application/2/membership"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And the response count is displayed
    And print response body

@GetAppName
  Scenario: Get app name
    Given I set the base specification for GM API
    When I send a GET request to "v1/membership"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
  And the response should contain "app_name" in all objects
    And print response body

  @ResponseCanNotEmpty
  Scenario: Response can not empty
    Given I set the base specification for GM API
    When I send a GET request to "v1/membership"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And the data length should be greater than 0
    And print response body

@GetAllUsername
  Scenario: Get username
    Given I set the base specification for GM API
    When I send a GET request to "v1/membership"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And the response should contain "username" in all objects
    And print response body

  @GetIsActive
  Scenario: Response must have is active field
    Given I set the base specification for GM API
    When I send a GET request to "v1/user/546/application/2/membership"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And the response should have a area "is_active[0]" with value true
    And print response body

  @GetOrganizationName
  Scenario: Response should have organization name  field
    Given I set the base specification for GM API
    When I send a GET request to "v1/user/546/application/2/membership"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And the response should have a area "organization_name[0]" with value "deneme"
    And print response body

  @GetId
  Scenario: Response should have id  field
    Given I set the base specification for GM API
    When I send a GET request to "v1/user/546/application/2/membership/3f5f58ee-72bf-41bb-a5ba-31beceff791b"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And the response have a field "id" with value 674
    And print response body

  @GetDefaultRoleName
  Scenario: Response should have default role name  field
    Given I set the base specification for GM API
    When I send a GET request to "v1/user/546/application/2/membership/3f5f58ee-72bf-41bb-a5ba-31beceff791b"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And the response should have a area "default_role_name" with value "Business Owner"
    And print response body

  @GetCreatedValue
  Scenario: Response should  created value  field
    Given I set the base specification for GM API
    When I send a GET request to "v1/user/546/membership"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And the response should have a area "created_at[0]" with value "2024-08-21T15:19:29.358278Z"
    And print response body

  @GetUserName
  Scenario: Response should  username  field
    Given I set the base specification for GM API
    When I send a GET request to "v1/user/546/membership"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And the response should have a area "username[0]" with value "cstm"
    And print response body















