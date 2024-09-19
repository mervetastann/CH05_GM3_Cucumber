@EntegrasyonUserAndRole
Feature: User and Role Entegrasyon

  Scenario: Create a New User and Save User ID
    Given I set the base specification for GM API
    When I send a POST request to "/v1/organization/user/register-manual" with the following body
      """
      {
        "email": "merveyy@example.com",
        "organization_id": "1724253527891397",
        "app_id": "2",
        "default_role_id": 5,
        "subscription_id": "3f5f58ee-72bf-41bb-a5ba-31beceff791b"
      }
      """
    Then the response status code should be 200
    And the response should have a field "email" with value "merveyy@example.com"
   And get and saved information from field "id"


    Scenario: Get and set random role id
      Given I set the base specification for GM API
      When I send a GET request to "/role"
      Then the response status code should be 200
      And the Content-Type should be "application/json"
      And user saved the random id
      And update user role
      And set default role
      And I send a GET user request to "/v1/user"




