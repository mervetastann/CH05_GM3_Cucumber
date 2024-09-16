@UsersGroupsApi
Feature: UserGroupServices

  @GetAllUsersGroupControl
  Scenario: Get
    Given I set the base specification for GM API
    When I send a GET request to "/user-group"
    Then the response status code should be 200
    And print response body

  @GetOrgandIdUsersGroupControlSSSS
  Scenario: Get
    Given I set the base specification for GM API
    When I send a GET request to "v1/organization/1724253527891397/user-group/479/details"
    Then the response status code should be 200
    And print response body

  @PostCreateUserGroup
  Scenario: Create a new user-group
    Given I set the base specification for GM API
    When I send a POST request for group to "/user-group" with the following body
"""
{
    "name": "Test User Group SESA SeptApi",
    "short_name": "t2",
    "group_type_id": 1,
    "description": "Sept test body",
    "roles": [
        {
            "id": 5
        }
    ]
}
"""
    And the response group should have a field "short_name" with value "t2"
    And the response group should have a field "name" with value "Test User Group SESA SeptApi"
    And the response group should have a field "short_name" with value "t2"
    And the response group should have a field "description" with value "Sept test body"
    And get and save information from field "group_type_id"
    And get and save information from field "id"
#    Then the response status code should be 201
