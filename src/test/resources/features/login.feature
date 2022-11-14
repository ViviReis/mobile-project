@login @vivi
Feature: Login

  Scenario: User should be able to login using valid email and password
    Given I access login option
    When I login with user email "001"
    Then I validate information about "Viviane" company