@EntegrasyonUserandStatus
Feature: User and User Group Entegrasyonu

  Scenario: Create and Update User
    Given I set the base specification for GM API
    When I send a POST request with status to "/v1/organization/user/register-manual" with the following body
    """
    {
        "email": "bomba2@example.com",
        "organization_id": {{org_ID}},
        "app_id": {{app_ID}},
        "default_role_id": 5,
        "subscription_id": "3f5f58ee-72bf-41bb-a5ba-31beceff791b",
        "phone": "+1 123 456 7890",
        "address": "123 Oak St, Springfield",
        "country_id": "US",
        "pic_id": 12345,
        "status_id": "",
        "user_groups": [],
        "created_at": "2024-09-11T21:43:27.734Z",
        "created_by": 0,
        "updated_at": "2024-09-11T21:43:27.734Z",
        "updated_by": 0
    }
    """
    Then the response status code should be 200
    And the response should have a field entg "email" with value "bomba2@example.com"
    When I send a for status GET request to "/user-status"
    And save a random status id from the response

  Scenario: Update User status
    Given I set the base specification for GM API
    When I send a PUT request for entegrasyon to "/v1/user" with the following body
    """
    {
        "id": {{id}},
        "email": "bombaupdate@example.com",
        "organization_id": {{org_ID}},
        "app_id": {{app_ID}},
        "default_role_id": 5,
        "subscription_id": "3f5f58ee-72bf-41bb-a5ba-31beceff791b",
        "phone": "+1 123 456 7890",
        "address": "123 Oak St, Springfield",
        "country_id": "US",
        "pic_id": 12345,
        "status_id": {{statusId}},
        "user_groups": [],
        "created_at": "2024-09-11T21:43:27.734Z",
        "created_by": 0,
        "updated_at": "2024-09-11T21:43:27.734Z",
        "updated_by": 0
    }
    """
    Then the response status code should be 200
    And the response should have a field entg "email" with value "bombaupdate@example.com"

  Scenario: Delete User
    Given I set the base specification for GM API
    When I send a DELETE  entegrasyon request to "/v1/organization/1724253527891397/user/{{id}}"
    Then the response status code should be 200