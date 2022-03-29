package com.jupiter.pages;

import com.jupiter.Constants.Constant;
import com.jupiter.hooks.BrowserHooks;
import com.jupiter.utility.StringUtility;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@Slf4j
public class ContactPage {

    @FindBy(css = ".btn-contact.btn.btn-primary")
    public WebElement submitButton;

    @FindBy(id = "header-message")
    public WebElement headerErrMsg;

    @FindBy(id = "forename-err")
    public WebElement forNameErr;

    @FindBy(id = "email-err")
    public WebElement emailErr;

    @FindBy(id = "message-err")
    public WebElement messageErr;

    @FindBy(id = "forename")
    public WebElement forNameTextField;

    @FindBy(id = "email")
    public WebElement emailTextField;

    @FindBy(id = "message")
    public WebElement messageTextField;

    @FindBy(css = ".alert.alert-success")
    public WebElement successMsgField;

    private String name;
    private final WebDriver localDriver = BrowserHooks.driverInstance.getDriver();
    private final WebDriverWait waitExplicit = new WebDriverWait(localDriver, Duration.ofSeconds(30));

    public ContactPage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(localDriver,10),this);
    }

    public void launchUrl(){
        localDriver.manage().window().maximize();
        localDriver.get(Constant.BASE_URL);
    }

    public void navigateToContactPage() {
        localDriver.navigate()
                .to(Constant.BASE_URL + "/" + Constant.Pages
                        .CONTACT.name().toLowerCase(Locale.ROOT));
    }

    public void clickOnSubmitButton() throws NoSuchElementException,ElementNotVisibleException {
            if(submitButton.isDisplayed()){
                submitButton.click();
            }
            else{
                throw new ElementNotVisibleException("Submit button is not visible");
            }

    }

    public void validateMainError(){
        assertThat(headerErrMsg.isDisplayed()).isTrue();
    }

    public void validateMandatoryFieldErrMsg(String fieldName, String fieldErrMsg){
        switch (fieldName) {
            case Constant.MSG_FIELD -> {
                assertThat(messageErr.isDisplayed()).isTrue();
                assertThat(messageErr.getText()).isEqualTo(fieldErrMsg);
            }
            case Constant.EMAIL_FIELD -> {
                assertThat(emailErr.isDisplayed()).isTrue();
                assertThat(emailErr.getText()).isEqualTo(fieldErrMsg);
            }
            case Constant.FORENAME_FIELD -> {
                assertThat(forNameErr.isDisplayed()).isTrue();
                assertThat(forNameErr.getText()).isEqualTo(fieldErrMsg);
            }
            default -> log.info("not a correct field value");
        }
    }

    public void inputDataToMandatoryFields(String fieldName) throws NoSuchElementException{
        switch (fieldName) {
            case Constant.MSG_FIELD -> messageTextField.sendKeys(StringUtility.generator(20));
            case Constant.EMAIL_FIELD -> emailTextField.sendKeys(StringUtility.emailGenerator(7));
            case Constant.FORENAME_FIELD -> {
                name = StringUtility.generator(10);
                forNameTextField.sendKeys(name);
            }
            default -> log.info("not a correct field value");
        }
    }

    public void checkErrMsgVisibility(){
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(()-> messageErr.isDisplayed());
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(()-> emailErr.isDisplayed());
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(()-> forNameErr.isDisplayed());
    }

    public void checkBackgroundForTextBox(){
        assertThat(forNameTextField.getAttribute("class")).isNotEqualTo("ng-dirty ng-invalid ng-invalid-required");
        assertThat(emailTextField.getAttribute("class")).isNotEqualTo("ng-dirty ng-invalid ng-invalid-required ng-valid-email");
        assertThat(messageTextField.getAttribute("class")).isNotEqualTo("ng-dirty ng-invalid ng-invalid-required");
    }

    public void verifySuccessMessage(String message){
        waitExplicit.until(ExpectedConditions.visibilityOf(successMsgField));
        message = message.replace("Forename",name);
        assertThat(successMsgField.getText().strip()).isEqualTo(message);
    }
}
