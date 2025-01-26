Feature: Updating the rating of a serie
  Scenario: User has decided that South Park still is the best and decides to bash on the rating of Family Guy
    Given A serie exists
    When User changes the rating to 9
    Then The new rating is 9