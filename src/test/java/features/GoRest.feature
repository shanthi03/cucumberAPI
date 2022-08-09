Feature:Go Rest Feature
  Background a user
    Given  a user

  Scenario : Verify that a user resource can be updated
  When updating the user
  Then the user is updated


  Scenario Outline  : Verify that a user resource not to  be updated
    When updating the user with invalid input "<userName>" and "<password>"
    Then the user not to be  updated
    Examples:
      | userName     | password |
      |test@gmail.com|test123   |
      |test          |Test123   |
      |gmail.com     |test123   |
      |demo@gmail.com|test@123  |

