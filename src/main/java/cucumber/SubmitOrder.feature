@tag
Feature: Purchase order from Ecommerce website

  Background:
    Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of submitting order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation page

    Examples:
      | name           | password     | productName   |
      | cozy@gmail.com | Atgatgnite1! | ZARA COAT 3   |
      | Miya@gmail.com | Jaymee1! | ADIDAS ORIGINAL   |
