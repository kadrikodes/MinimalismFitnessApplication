Feature: Manage Walking Data

  Scenario: Getting all walking data
    Given I have 10 records in the "walking" data
    When I request all "walking" data
    Then the total number of "walking" data records I receive is 10 records

  Scenario Outline: Add new walking data
    Given I have "new walking" data with <steps>, <distance>, <caloriesBurned>, <duration>, and <speed>
    When I submit "new walking" data
    Then the submitted "walking" data should be saved with the specified attributes
    And a confirmation message "Walking data added successfully" is displayed


    Examples:
      | steps | distance | caloriesBurned | duration | speed |
      | 1000  | 0.8      | 50             | 15.0       | 3.2   |
      | 5000  | 4.0      | 250            | 60.0       | 4.0   |


