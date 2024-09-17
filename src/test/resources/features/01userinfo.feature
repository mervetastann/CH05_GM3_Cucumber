@Userinfo
Feature: User Information Management

  Scenario: Get User Info
    Given I set the base specification for User Info API
    When I send a GET request to "/userinfo"
    Then the response status code should be 200
    And I save the "sub_app" from the response
    And I save the "sub_default_org_id" from the response
    And I save the "sub_default_subscription_id" from the response
    And I print the response body
