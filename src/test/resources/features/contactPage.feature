@ContactPage
Feature: Validate the Scenarios for contact page

  @TC01
  Scenario Outline: Verify the error messages on contact page on clicking submit button and check the behaviour on providing mandatory details

    Given the user launches the url successfully
    And navigate to contact page from home page
    When the user clicks on submit button
    Then verify the generic error message
    And verify the error message for the fields "<Field1>" is "<Err_Message_ForName>"
    And verify the error message for the fields "<Field2>" is "<Err_Message_Email>"
    And verify the error message for the fields "<Field3>" is "<Err_Message_Message>"
    And correct data is provided to the fields "<Field1>" , "<Field2>" and "<Field3>"
    And the error message disappears
    And fields , labels does not have background in red.

    Examples:
      | Field1   | Field2 | Field3  | Err_Message_ForName  | Err_Message_Email | Err_Message_Message |
      | Forename | Email  | Message | Forename is required | Email is required | Message is required |

  @TC02
  Scenario Outline: Verify the error messages on contact page on clicking submit button and check the behaviour on providing mandatory details

    Given the user launches the url successfully
    And navigate to contact page from home page
    When correct data is provided to the fields "<Field1>" , "<Field2>" and "<Field3>"
    Then the user clicks on submit button
    And user gets a message "Thanks <Field1>, we appreciate your feedback."

    Examples:
      | Field1   | Field2 | Field3  |
      | Forename | Email  | Message |
