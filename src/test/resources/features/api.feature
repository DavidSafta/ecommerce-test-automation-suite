Feature: GitHub REST API

  Scenario Outline: Get a public user profile
    Given a GET request is sent to "/users/<username>"
    Then the response status code should be 200
    And the response field "login" should be "<username>"

    Examples:
      | username   |
      | octocat    |
      | torvalds   |
      | DavidSafta |

  Scenario: Get a non-existing user returns 404
    Given a GET request is sent to "/users/qa-no-such-user-9f3k2x7"
    Then the response status code should be 404

  Scenario: Get a public repository
    Given a GET request is sent to "/repos/octocat/Hello-World"
    Then the response status code should be 200
    And the response field "name" should be "Hello-World"
    And the response field "owner.login" should be "octocat"
    And the response should contain a field "stargazers_count"