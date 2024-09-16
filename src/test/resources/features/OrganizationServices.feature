@PermissionServiceApi
Feature: Permission Service

  @GetPermissionByAppId
  Scenario: Get Permissions By App Id
    Given I set the base specification for GM API
    When I send a GET request to "/application/2/permission"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And the response data length should be 94
    And each permission in the response should contain "resource" and "action" fields
    And the response have a field "app_id[0]" with value 2

  @GetPermission
  Scenario: Get All Permissions
    Given I set the base specification for GM API
    When I send a GET request to "/permission"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And the response data length should be 202
    And the app_id in the response should only contain values 1, 2, or 20

  @GetPermissionById
  Scenario: Get Permission By Permission Id
    Given I set the base specification for GM API
    When I send a GET request to "/permission/36"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And the response have a field "id" with value 36

