@ShopPage
Feature: Validate the Scenarios for shop page

  @TC01
  Scenario: Verify the shop page button and price details on cart are updated successfully.

    Given the user launches the url successfully
    And navigate to shop page from home page
    When the user clicks on buy button 2 times for "Stuffed Frog"
    And the user clicks on buy button 5 times for "Fluffy Bunny"
    And the user clicks on buy button 3 times for "Valentine Bear"
    And user clicks on cart button
    Then verify the number of "Stuffed Frog", "Fluffy Bunny" and "Valentine Bear"
    And Verify the price for each product "Stuffed Frog", "Fluffy Bunny" and "Valentine Bear"
    And Verify the subtotal for each product  "Stuffed Frog", "Fluffy Bunny" and "Valentine Bear" is correct
    And total sum matches the one shown on the cart details section

