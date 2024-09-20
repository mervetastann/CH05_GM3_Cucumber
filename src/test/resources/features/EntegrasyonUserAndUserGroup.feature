@EntegrasyonUserAndUserGroup
Feature: User and User Group Entegrasyonu

  Scenario: Create a New User and Save User ID
    Given I set the base specification for GM API
    When I send a POST request to "/v1/organization/user/register-manual" with the following body
      """
      {
        "email": "bomba1@example.com",
        "organization_id": "1724253527891397",
        "app_id": "2",
        "default_role_id": 5,
        "subscription_id": "3f5f58ee-72bf-41bb-a5ba-31beceff791b"
      }
      """
    Then the response status code should be 200
    And the response should have a field "email" with value "bomba1@example.com"

  Scenario: Create a User Group with Saved User ID
    Given I set the base specification for GM API
    When I take user id send with a POST request for group to "/user-group" with the following body
      """
      {
        "name": "Remote Unit Sirketi(user ve user-group entegrasyonu ürünüyümmm)",
        "short_name": "RF5",
        "description": "BOSS",
        "group_type_id": 2,
        "organization_id": {{org_ID}},
        "users": [
            {
                "id": {{id}}
            }
        ],
        "roles": [
            {
                "id": 30
            },
            {
                "id": 5
            }
        ]
      }
      """
    And get and save information from field after create "group_type_id"
    And get and save information from field "id"
    Then the response for group status code should be 201



  @DeleteCreateUserGroup
  Scenario: Delete User group
    Given I set the base specification for GM API
    When I send a DELETE request for group to "/user-group/{{id}}"
    Then the response for group status code should be 200


  Scenario: Delete User
    Given I set the base specification for GM API
    When I send a DELETE request to "/v1/organization/1724253527891397/user/{{id}}"
#  https://qa-gm3.quaspareparts.com/a3m/auth/api/v1/organization/1724253527891397/user/729
    Then the response status code should be 200