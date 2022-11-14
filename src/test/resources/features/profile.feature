@profile
Feature: Profile

  Scenario: User should be able to see organization info
    Given I access login option
    And I login with user email "001"
    When I click on switch company
    Then I validate that "Viviane" organization with "1 memb" is displayed