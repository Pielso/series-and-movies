Feature: Updating the name of a serie
  Scenario: The user realizes that the serie had another name
    Given A serie with the name "South Park" exists
    When User changes the name to "Family Guy"
    Then The series name is now "Family Guy"