Feature: Manage Walking Data

  Scenario: Getting all walking data
    Given I request all walking data
    Then the total number of walking data records I receive matches the number in the database

  Scenario Outline: Add new walking data
    Given I submit new walking data with steps "<steps>", distance "<distance>", calories burned "<caloriesBurned>", duration "<duration>", and speed "<speed>"
    Then the new walking data should be saved with the specified attributes
    And a confirmation message "Walking data added successfully" is displayed

    Examples:
      | steps | distance | caloriesBurned | duration | speed |
      | 1000  | 0.8      | 50             | 15       | 3.2   |
      | 5000  | 4.0      | 250            | 60       | 4.0   |

  Scenario Outline: Update walking data
    Given I have existing walking data with ID "<id>"
    When I update the walking data with new steps "<newSteps>", new distance "<newDistance>", new calories burned "<newCaloriesBurned>", new duration "<newDuration>", and new speed "<newSpeed>"
    Then the walking data with ID "<id>" should be updated with the new values
    And a confirmation message "Walking data updated successfully" is displayed

    Examples:
      | id | newSteps | newDistance | newCaloriesBurned | newDuration | newSpeed |
      | 1  | 2000     | 1.6         | 100               | 30          | 3.2      |
      | 2  | 6000     | 4.8         | 300               | 70          | 4.1      |

  Scenario Outline: Delete walking data
    Given I have existing walking data with ID "<id>"
    When I request to delete the walking data with ID "<id>"
    Then the walking data with ID "<id>" should be deleted from the database
    And a confirmation message "Walking data deleted successfully" is displayed

    Examples:
      | id |
      | 1  |
      | 2  |

