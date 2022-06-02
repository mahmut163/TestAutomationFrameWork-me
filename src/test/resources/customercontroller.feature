@CustomerApiTest  @RegressionTest
Feature: Get Post Put Delete request Api Test For Customer controller
  Scenario: User should be able to get customer information
    Given user should be able to send request for get specific customer information
    Then user should get information about the customer