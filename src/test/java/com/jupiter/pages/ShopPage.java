package com.jupiter.pages;

import com.jupiter.Constants.Constant;
import com.jupiter.hooks.BrowserHooks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ShopPage {

    @FindBy(xpath = "//h4[contains(text(),'Stuffed Frog')]/parent::div/child::p/a")
    WebElement frogBuyButton;

    @FindBy(xpath = "//h4[contains(text(),'Bunny')]/parent::div/child::p/a")
    WebElement bunnyBuyButton;

    @FindBy(xpath = "//h4[contains(text(),'Valentine Bear')]/parent::div/child::p/a")
    WebElement bearBuyButton;

    @FindBy(xpath = "//h4[contains(text(),'Stuffed Frog')]/parent::div/child::p/span")
    WebElement eachFrogPrice;

    @FindBy(xpath = "//h4[contains(text(),'Bunny')]/parent::div/child::p/span")
    WebElement eachBunnyPrice;

    @FindBy(xpath = "//h4[contains(text(),'Valentine Bear')]/parent::div/child::p/span")
    WebElement eachBearPrice;

    @FindBy(xpath = "//a[@href=\"#/cart\"]")
    WebElement cartButton;

    @FindBy(xpath = "//td[contains(text(),'Frog')]/parent::tr/td[3]/input")
    WebElement numberOfItemsForFrog;

    @FindBy(xpath = "//td[contains(text(),'Bunny')]/parent::tr/td[3]/input")
    WebElement numberOfItemsForBunny;

    @FindBy(xpath = "//td[contains(text(),'Bear')]/parent::tr/td[3]/input")
    WebElement numberOfItemsForBear;

    @FindBy(xpath = "//td[contains(text(),'Frog')]/parent::tr/td[4]")
    WebElement totalPriceOfFrog;

    @FindBy(xpath = "//td[contains(text(),'Bunny')]/parent::tr/td[4]")
    WebElement totalPriceOfBunny;

    @FindBy(xpath = "//td[contains(text(),'Bear')]/parent::tr/td[4]")
    WebElement totalPriceOfBear;

    @FindBy(xpath = "//td[contains(text(),'Frog')]/parent::tr/td[2]")
    WebElement eachFrogPriceOnCart;

    @FindBy(xpath = "//td[contains(text(),'Bunny')]/parent::tr/td[2]")
    WebElement eachBunnyPriceOnCart;

    @FindBy(xpath = "//td[contains(text(),'Bear')]/parent::tr/td[2]")
    WebElement eachBearPriceOnCart;

    @FindBy(xpath = "//tfoot/tr/td")
    WebElement totalAmount;

    private static float sum =0;
    private final Map<String,Integer> totalItems =new HashMap<>();
    private final Map<String,String> eachPriceOfTheItem = new HashMap<>();
    private final WebDriver localDriver = BrowserHooks.driverInstance.getDriver();

    public ShopPage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(localDriver,10),this);
    }

    public void navigateToShopPage(){
        localDriver.navigate()
                .to(Constant.BASE_URL + "/" + Constant.Pages
                        .SHOP.name().toLowerCase(Locale.ROOT));
    }

    public void clickBuy(String itemName, int times){
        switch (itemName){
            case Constant.FROG_ITEM -> {
                totalItems.put(Constant.FROG_ITEM,times);
                eachPriceOfTheItem.put(Constant.FROG_ITEM,eachFrogPrice.getText());
                for (int i=0;i<times;i++){
                    frogBuyButton.click();
                    localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
                }
            }
            case Constant.BUNNY_ITEM -> {
                totalItems.put(Constant.BUNNY_ITEM,times);
                eachPriceOfTheItem.put(Constant.BUNNY_ITEM,eachBunnyPrice.getText());
                for (int i=0;i<times;i++){
                    bunnyBuyButton.click();
                    localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
                }
            }

            case Constant.BEAR_ITEM -> {
                totalItems.put(Constant.BEAR_ITEM,times);
                eachPriceOfTheItem.put(Constant.BEAR_ITEM,eachBearPrice.getText());
                for (int i=0;i<times;i++){
                    bearBuyButton.click();
                    localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
                }
            }
        }
    }

    public void clickCartButton(){
        assertThatNoException().isThrownBy(() -> {
            cartButton.isDisplayed();
        });

        cartButton.click();
        localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public void eachItem(String productDetail){
        switch (productDetail){
            case Constant.BEAR_ITEM -> {
                assertThat(totalItems.get(Constant.BEAR_ITEM)).isEqualTo(Integer
                        .parseInt(numberOfItemsForBear.getAttribute("value")));
            }
            case Constant.BUNNY_ITEM -> {
                assertThat(totalItems.get(Constant.BUNNY_ITEM)).isEqualTo(Integer
                        .parseInt(numberOfItemsForBunny.getAttribute("value")));
            }
            case Constant.FROG_ITEM -> {
                assertThat(totalItems.get(Constant.FROG_ITEM)).isEqualTo(Integer
                        .parseInt(numberOfItemsForFrog.getAttribute("value")));
            }
        }
    }

    public void checkPriceOfEachItem(String productDetail){
        switch (productDetail){
            case Constant.BEAR_ITEM -> {
                assertThat(eachPriceOfTheItem.get(Constant.BEAR_ITEM)).isEqualTo(
                        eachBearPriceOnCart.getText());
            }
            case Constant.BUNNY_ITEM -> {
                assertThat(eachPriceOfTheItem.get(Constant.BUNNY_ITEM)).isEqualTo(
                        eachBunnyPriceOnCart.getText());
            }
            case Constant.FROG_ITEM -> {
                assertThat(eachPriceOfTheItem.get(Constant.FROG_ITEM)).isEqualTo(
                        eachFrogPriceOnCart.getText());
            }
        }
    }

    public void checkTotalPriceOfEachItem(String productDetail){
        switch (productDetail){
            case Constant.BEAR_ITEM -> {
                float itemVal = Float.parseFloat(eachPriceOfTheItem.get(Constant.BEAR_ITEM)
                        .replace("$",""));
                sum += itemVal*totalItems.get(Constant.BEAR_ITEM);
                assertThat(String.format("$%.2f",itemVal*totalItems.get(Constant.BEAR_ITEM))).isEqualTo(
                        totalPriceOfBear.getText());
            }
            case Constant.BUNNY_ITEM -> {
                float itemVal = Float.parseFloat(eachPriceOfTheItem.get(Constant.BUNNY_ITEM)
                        .replace("$",""));
                sum += itemVal*totalItems.get(Constant.BUNNY_ITEM);
                assertThat(String.format("$%.2f",itemVal*totalItems.get(Constant.BUNNY_ITEM))).isEqualTo(
                        totalPriceOfBunny.getText());
            }
            case Constant.FROG_ITEM -> {
                float itemVal = Float.parseFloat(eachPriceOfTheItem.get(Constant.FROG_ITEM)
                        .replace("$",""));
                sum += itemVal*totalItems.get(Constant.FROG_ITEM);
                assertThat(String.format("$%.2f",itemVal*totalItems.get(Constant.FROG_ITEM))).isEqualTo(
                        totalPriceOfFrog.getText());
            }
        }
    }

    public  void totalPriceOfAllItems(){
        String finalAmount = totalAmount.getText().replaceAll("[a-zA-Z]|\\s|:","");
        assertThat(String.format("%.1f",sum)).isEqualTo(finalAmount);
    }
}

