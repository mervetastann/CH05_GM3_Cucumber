@user
Feature: User Management

  @GetAllUsers
  Scenario:Get All Users
    Given I set the base specification for GM API
    When I send a GET request to "/v1/user"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And print response body

  Scenario: Create a New User and Save User ID
    Given I set the base specification for GM API
    When I send a POST request to "/v1/organization/user/register-manual" with the following body
      """
      {
        "email": "mhs@example.com",
        "organization_id": "1724253527891397",
        "app_id": "2",
        "default_role_id": 5,
        "subscription_id": "3f5f58ee-72bf-41bb-a5ba-31beceff791b"
      }
      """
    Then the response status code should be 200
    And the response should have a field "email" with value "mhs@example.com"
#    And print response body

  Scenario: Update User Information and Validate ID
    Given I set the base specification for GM API
    When I send a PUT request to "/v1/user" with the following body
      """
      {
        "id": {{id}},
        "username": "mhss",
        "email": "mh@example.com",
        "organization_id": {{org_ID}},
        "app_id": {{app_ID}},
        "membership_type_id": 5,
        "default_role_id": 5,
        "subscription_id": "3f5f58ee-72bf-41bb-a5ba-31beceff791b"
      }
      """
    Then the response status code should be 200
    And the response should have a field "email" with value "mh@example.com"
    And print response body

  Scenario: Delete User
    Given I set the base specification for GM API
    When I send a DELETE request to "/v1/organization/1724253527891397/user/{{id}}"
#  https://qa-gm3.quaspareparts.com/a3m/auth/api/v1/organization/1724253527891397/user/729
    Then the response status code should be 200



