@RegressionTest
Feature: admin user can manage products
  as an admin,the user should be able to add,update or delete products

  Background:
    Given user already on the login page
    When  user enter valid username and password and click on the login button
    Then  user should be able to login successfully

  @AddProduct
  Scenario: admin user can add a new product
    Given admin user is on the dashboard page
    When the user fills out a new product form
    Then a new product should be created

  @DeleteProduct
  Scenario: admin user can delete a  product
    Given admin user is on the dashboard page
    When the user tries to delete an existing product
    Then the product should be deleted
