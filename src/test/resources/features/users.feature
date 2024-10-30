Feature: Users Endpoint

  Scenario Outline: User 'Create' functionality
    Given a list of non-empty users and a new user to create
      | name   | email   | gender   | status   |
      | <name> | <email> | <gender> | <status> |
    When I create a new user
    Then I should see created user in the list of users

    Examples:
      | name       | email                | gender | status |
      | Bill Gates | bill_gates_s@gmail.com | male   | active |

  Scenario Outline: User 'Update' functionality
    Given a created user
    When I update the user
      | name   | email   | gender   | status   |
      | <name> | <email> | <gender> | <status> |
    Then I should see updated user in the list of users

    Examples:
      | name         | email                  | gender | status   |
      | Joan Rowling | joan_rowling@gmail.com | female | inactive |

  Scenario: User 'Delete' functionality
    Given a created user
    When I delete the user
    Then I should not see deleted user in the list of users