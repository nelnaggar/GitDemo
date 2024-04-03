@tag
Feature: Purchase the Order from E-commerce Website
  I want to use this template for my feature file
  
  Background:
  Given User landed on the e-commerce page.

  @Regression
  Scenario Outline: Positive Test of Order Submitting
    Given User is logged in with user name <name> and password <password> 
    When User adds product <productName> to cart
    And checks out <productName> and submits the order
    Then "THANKYOU FOR THE ORDER." is displayed on confirmation page.

    Examples: 
      | name  					| password 	| productName |
      | raul@ortega.net |	Start123! |	ZARA COAT 3	|
