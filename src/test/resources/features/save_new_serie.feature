Feature: Adding a new serie to the database
  Scenario: The user wants to add a new serie
    Given The user is on the correct page
    When The user have filled in the serie title "South Park" with a rating of 10 and clicks the save button
    Then The serie is created and has the name "South Park" and the rating 10