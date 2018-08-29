#  This is the first .feature file for BBD testing with serenity & cucumber
@all
Feature: Get a single user by its id
@first
Scenario Outline: Should get a particular user
    Given I want to get details about person with id = <id>
    When I make the request to get the details
    Then The user should be <first_name> <last_name> with id == <id>

Examples:
    |id|first_name|last_name|
    | 2|     Janet|   Weaver|
    |10|     Byron|   Fields|
    | 7|   Michael|   Lawson|
@second
Scenario Outline: Should get a particular user
    Given I want to get details about person with id = <id>
    When I make the request to get the details
    Then The user should be <first_name> <last_name> with id == <id>

Examples:
   |id|first_name|last_name|
   | 8|   Lindsay| Ferguson|
   | 7|   Michael|   Lawson|
   |10|     Byron|   Fields|