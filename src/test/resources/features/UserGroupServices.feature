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
    Then the response for group status code should be 201




  @PutUserGroup
  Scenario: Update a user-group
    Given I set the base specification for GM API
    When I for group service send a PUT request to "/user-group" with the following body
  """
  {
      "id": {{id}},
      "name": "Sema ve Salihan in put Testim",
      "short_name": "t2",
      "description": "Test 1 eklendi",
      "group_type_id": 1,
      "group_type": {
          "id": 1,
          "name": "Department",
          "description": "Remote organizational unit of the company, like remote branches or warehouses"
      },
      "organization_id": 1724253527891397,
      "organization": {
          "id": 1724253527891397,
          "name": "deneme",
          "founder_id": 24,
          "short_name": "test",
          "email": "test@gmail.com",
          "status_id": 1,
          "created_at": "2024-08-21T15:18:47.879567Z",
          "created_by": 24,
          "updated_at": "2024-08-31T17:48:08.212700Z",
          "updated_by": 24
      },
      "users": [],
      "roles": [
          {
              "id": 5,
              "name": "Business Owner",
              "app_id": 2
          }
      ]
  }
  """
    Then the response status code should be 200
    And the response group should have a field "name" with value "Sema ve Salihan in put Testim"
    And the response group should have a field "short_name" with value "t2"
    And get and save information from field "group_type_id"
    And get and save information from field "id"


  @DeleteCreateUserGroup
  Scenario: Delete User
    Given I set the base specification for GM API
    When I send a DELETE request for group to "/user-group/{{id}}"
    Then the response for group status code should be 200


  @BosNameUserGroup @NegativeUserGroup
  Scenario: Don't Create a new user-group (empty name)
    Given I set the base specification for GM API
    When I send a POST request for group to "/user-group" with the following body
    """
{
    "name": "",
    "short_name": "name_olmadan test",
    "group_type_id": 2,
    "description": "zorunlu alan eksik oldugu icin kayit yapilmamali.",
    "roles": [
        {
            "id": 5
        }
    ]
}
    """
    Then the response for group status code should be 406
    And the response should contain error message "UserGroup not created"



  @BosNameVeBosGroupType @NegativeUserGroup
  Scenario: Don't Create a new user-group (empty name and group type id)
    Given I set the base specification for GM API
    When I send a POST request for group to "/user-group" with the following body
    """
    {
      "name": "",
      "short_name": "name alanı yok ve grup type id bos senaryosu ve create edilemeyecek",
      "group_type_id": "",
      "description": "Ekleme yapılmadı.",
      "roles": [
        {
          "id": 5
        }
      ]
    }
    """
    Then the response for group status code should be 406
    And the response should contain error message "UserGroup not created"

  @InvalidIdUserGroup @NegativeUserGroup
  Scenario: Update user group with an invalid ID
    Given I set the base specification for GM API
    When I for group service send a PUT request to "/user-group" with the following body
      """
      {
        "id": 111111111111,
        "name": "Geçersiz bir id ile upgrade",
        "short_name": "t2",
        "description": "Test 1 eklendi",
        "group_type_id": 2,
        "group_type": {
          "id": 2,
          "name": "Remote Unit",
          "description": "Remote organizational unit of the company, like remote branches or warehouses"
        },
        "organization_id": 1724253527891397,
        "organization": {
          "id": 1724253527891397,
          "name": "deneme",
          "founder_id": 24,
          "short_name": "test",
          "email": "test@gmail.com",
          "status_id": 1,
          "created_at": "2024-08-21T15:18:47.879567Z",
          "created_by": 24,
          "updated_at": "2024-08-31T17:48:08.212700Z",
          "updated_by": 24
        },
        "users": [],
        "roles": [
          {
            "id": 5,
            "name": "Business Owner",
            "app_id": 2
          }
        ]
      }
      """
    Then the response for group status code should be 404

  @IdsizUserGroup @NegativeUserGroup
  Scenario: Update user group without an ID
    Given I set the base specification for GM API
    When I for group service send a PUT request to "/user-group" with the following body
      """
      {
        "name": "Sema ve Saliha'nın ID'siz upgrade etme çabalarının Testi",
        "short_name": "t2",
        "description": "Test 1 eklendi",
        "group_type_id": 1,
        "group_type": {
          "id": 1,
          "name": "Department",
          "description": "Remote organizational unit of the company, like remote branches or warehouses"
        }
      }
      """
    Then the response for group status code should be 406
