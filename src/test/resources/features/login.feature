Feature: Login

  Scenario Outline: Login with invalid credentials shows an error
    Given the user is on the login page
    When the user logs in with email "<email>" and password "<password>"
    Then the login error message should be "Your email or password is incorrect!"

    Examples:
      | email                   | password   |
      | nobody@example.com      | wrongpass1 |
      | qa.tester.fake@mail.com | 123456     |
      | invalid.user@test.org   | Passw0rd!  |