@OrganizationServiceApi
Feature: Get Organization Service

  @GetAllOrganizationServices
  Scenario: Get All Organization Services
    Given I set the base specification for GM API
    When I send a GET request to "/v1/organization/1724253527891397/chart/3f5f58ee-72bf-41bb-a5ba-31beceff791b"
    Then the response status code should be 200
    And the response must have a field "name" with value "deneme"

  @CPTC_001_GetOrganizationChartData
  Scenario: User Gets Organization Chart
    Given I set the base specification for GM API
    When I send a GET request to "/v1/organization/1724253527891397/chart/3f5f58ee-72bf-41bb-a5ba-31beceff791b"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And the response must have a field "email" with value "test@gmail.com"

  @CPTC_002_CheckIncomingAndExpectedId
  Scenario: Checks if incoming 'id' has the expected 'id'
    Given I set the base specification for GM API
    When I send a GET request to "/v1/organization/1724253527891397/chart/3f5f58ee-72bf-41bb-a5ba-31beceff791b"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And user should check if Incoming id has expected id
    And print response body

  @CPTC_003_CalculateTotalNumberofUsers
  Scenario: Calculate Total Number of Users
    Given I set the base specification for GM API
    When I send a GET request to "/v1/organization/1724253527891397/chart/3f5f58ee-72bf-41bb-a5ba-31beceff791b"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And Ensure totalUsers is greater than Zero

  @CPTC_004_ValidateGroupTypeNameinResponseBody
  Scenario: Validate Group Type Name in Response Body
    Given I set the base specification for GM API
    When I send a GET request to "/v1/organization/1724253527891397/summary"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And the response must have a field "group_types[0].name" with value "Department"
    And print response body

  @CPTC_005_Validateif'group_types'listhas3items
  Scenario: Validate if 'group_types' list has 3 items
    Given I set the base specification for GM API
    When I send a GET request to "/v1/organization/1724253527891397/summary"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And Validate if group_types list has 3 items
    And print response body













