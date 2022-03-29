package com.jupiter.stepDefinitions;

import com.jupiter.pages.ContactPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ContactPageStepDef {
    private final ContactPage contactPage = new ContactPage();
    @Given("^the user launches the url successfully$")
    public void launchUrl()  {
        contactPage.launchUrl();
    }

    @When("navigate to contact page from home page")
    public void navigateToContactPageFromHomePage() throws InterruptedException {
        contactPage.navigateToContactPage();
    }


    @When("the user clicks on submit button")
    public void theUserClicksOnSubmitButton() throws InterruptedException {
        contactPage.clickOnSubmitButton();
    }


    @Then("verify the generic error message")
    public void verifyTheGenericErrorMessage() {
        contactPage.validateMainError();
    }

    @Then("verify the error message for the fields {string} is {string}")
    public void verifyTheErrorMessageForTheFieldsIs(String fieldName, String fieldValue) {
        contactPage.validateMandatoryFieldErrMsg(fieldName,fieldValue);
    }

    @When("correct data is provided to the fields {string} , {string} and {string}")
    public void correctDataIsProvidedToTheFieldsAnd(String field1, String field2, String field3) {
        contactPage.inputDataToMandatoryFields(field1);
        contactPage.inputDataToMandatoryFields(field2);
        contactPage.inputDataToMandatoryFields(field3);
    }

    @Then("the error message disappears")
    public void theErrorMessageDisappears() {
        contactPage.checkErrMsgVisibility();
    }

    @Then("fields , labels does not have background in red.")
    public void fieldsLabelsDoesNotHaveBackgroundInRed() {
        contactPage.checkBackgroundForTextBox();
    }

    @And("user gets a message {string}")
    public void userGetsAMessage(String messageSuccess) {
        contactPage.verifySuccessMessage(messageSuccess);
    }
}
