
@tag
Feature: Error Validation
  I want to use this template for my feature file



  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given User landed on the e-commerce page.
    When User is logged in with user name <name> and password <password>
    Then "Incorrect email or password." error message is displayed.

    Examples: 
      | name  					| password 	|
      | raul@ortega.net |	Startxxx! |
    	| carlos@ortega.net |	Startxxx! |