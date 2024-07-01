@tag
  Feature: Error Validation

    @ErrorValidation
    Scenario Outline: asdgfadsf
      Given I landed on Ecommerce Page
      Given Logged in with username <name> and password <password>
      Then "Incorrect email or password." message is displayed

      Examples:
        | name           | password|
        | cozy@gmail.com | aasdgfa |