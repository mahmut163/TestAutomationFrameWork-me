@AddMultipleProducts
Feature: Add Multiple products

  Background:
    Given user already on the login page
    When  user enter valid username and password and click on the login button
    Then  user should be able to login successfully

  Scenario Outline: User should be able to add multiple products
    Given admin user is on the dashboard page
    And click on Add product link to fill out "<productName>" "<productCode>" and other information
    Then product should be created




    Examples:
      |productName|productCode|
      |Books      |AA8976     |
      |Shoes      |BB7865     |