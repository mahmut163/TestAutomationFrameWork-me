@unittest

  Feature: CubeCart Login Function

    @ValidLogin  @SmokeTest
    Scenario: user can login with valid username and password
      Given user already on the login page
      When  user enter valid username and password and click on the login button
      Then  user should be able to login successfully


    @InvalidLogin
    Scenario: user can not login with invalid username and password
      Given user already on the login page
      When  user enter invalid username and password and click on the login button
      Then  user should get error massage
