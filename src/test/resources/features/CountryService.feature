@CountryApi
Feature: Get ALL Country

  @GetAllCountry
  Scenario:Get All Countries
    Given I set the base specification for GM API
    When I send a GET request to "/country"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And the response data length should be 250
    And print response body

  @GetCountryByName
  Scenario:Get Country by Name

    Given I set the base specification for GM API
    When I send a GET request to "/country/AF"
    Then the response status code should be 200
    And the Content-Type should be "application/json"
    And the response must have a field "name" with value "Afghanistan"
    And print response body

  @GetCountryByInvalidId
  Scenario:Get Countries are not displayed with an invalid ID.
    Given I set the base specification for GM API
    When I send a GET request to "/country/XX"
    Then the response status code should be 404
    And print response body


