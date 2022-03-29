package com.jupiter.stepDefinitions;

import com.jupiter.pages.ShopPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShopStepDef {

    private final ShopPage shopPage = new ShopPage();

    @And("navigate to shop page from home page")
    public void navigateToShopPageFromHomePage() {
        shopPage.navigateToShopPage();
    }

    @When("the user clicks on buy button {int} times for {string}")
    public void theUserClicksOnBuyButtonTimesFor(int times, String item) {
        shopPage.clickBuy(item,times);
    }

    @And("user clicks on cart button")
    public void userClicksOnCartButton()  {
        shopPage.clickCartButton();
    }

    @Then("verify the number of {string}, {string} and {string}")
    public void verifyTheNumberOfEachItems(String item_1, String item_2, String item_3) {
        shopPage.eachItem(item_1);
        shopPage.eachItem(item_2);
        shopPage.eachItem(item_3);
    }

    @And("Verify the price for each product {string}, {string} and {string}")
    public void verifyThePriceForEachProduct(String item_1, String item_2, String item_3)  {
        shopPage.checkPriceOfEachItem(item_1);
        shopPage.checkPriceOfEachItem(item_2);
        shopPage.checkPriceOfEachItem(item_3);
    }

    @And("Verify the subtotal for each product  {string}, {string} and {string} is correct")
    public void verifyTheSubtotalForEachProductAndIsCorrect(String item_1, String item_2, String item_3) {
        shopPage.checkTotalPriceOfEachItem(item_1);
        shopPage.checkTotalPriceOfEachItem(item_2);
        shopPage.checkTotalPriceOfEachItem(item_3);
    }

    @And("total sum matches the one shown on the cart details section")
    public void totalSumMatchesTheOneShownOnTheCartDetailsSection() {
        shopPage.totalPriceOfAllItems();
    }
}
