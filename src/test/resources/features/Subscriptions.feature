@SubscriptionsServiceApi
Feature: Get Subscriptions Service

  @CPTC_001_LogAllUserIDValues
  Scenario: Log All User ID Values
    Given I set the base specification for GM API
    When I send a GET request to "/v1/application/2/subscription"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And print response body
    And Log All User ID Values

  @CPTC_002_GetUserbyIDPositiveTest
  Scenario: Get User by ID 673
    Given I set the base specification for GM API
    When I send a GET request to "/v1/application/2/subscription"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And Get User by ID 673

  @CPTC_003_CheckifNoUserWithNegativeTest
  Scenario: Check if no user with id 100 exists
    Given I set the base specification for GM API
    When I send a GET request to "/v1/subscription"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And Check if no user with id 100 exists

  @CPTC_004_ValidateOrganizationnName
  Scenario: Validate organization_name is "deneme"
    Given I set the base specification for GM API
    When I send a GET request to "  /v1/subscription/3f5f58ee-72bf-41bb-a5ba-31beceff791b"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And print response body
    And the response must have a field "organization_name" with value "deneme"

  @CPTC_005_ValidateOrganizationnName
  Scenario: Verify that the user's default role name is "Business Owner"
    Given I set the base specification for GM API
    When I send a GET request to "/v1/user/546/application/2/subscription"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And print response body
    And the response must have a field "[0].default_role_name" with value "Business Owner"

  @CPTC_006_ValidateifUserID
  Scenario: Validate if User ID is 546
    Given I set the base specification for GM API
    When I send a GET request to "v1/user/546/subscription"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And print response body
    And the response have a field "[0].user_id" with value 546













