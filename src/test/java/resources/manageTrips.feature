# Created by machadoca at 26/02/21
Feature: As a user I want to be able to create and edit a ticket reservation,
  so, I can easily buy an airplane ticket

  Scenario Outline: Successfully search for a trip
    Given a user is at the ryanair homepage
    And the user accepts the Cookie policy to continue navigating
    When the user chooses "<departure>" and "<destination>" to travel
    And the user selects "<depart>" and "<return>" dates


    Examples:
    |departure | destination    | depart      | return          |
    |Lisbon    | Paris Beauvais | June 6 2021 | October 30 2021 |

