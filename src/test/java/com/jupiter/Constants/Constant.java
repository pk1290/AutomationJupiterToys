package com.jupiter.Constants;

public class Constant {
    //driver constants
    public static final String CHROMEDRIVER_PROPERTY  = "webdriver.chrome.driver";
    public static final String CHROMEDRIVER_LOCATION  = "drivers/chromeDriver";

    //application details
    public static final String BASE_URL  = "https://jupiter.cloud.planittesting.com/#";
    public enum Pages{
        CONTACT,
        HOME
    }

    public static final String FORENAME_FIELD  = "Forename";
    public static final String EMAIL_FIELD  = "Email";
    public static final String MSG_FIELD  = "Message";
}
