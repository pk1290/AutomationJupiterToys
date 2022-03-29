package com.jupiter.driverWrappers;

import com.jupiter.Constants.Constant;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

@Getter
@Slf4j
public class DriverInstance {
    private WebDriver driver;
    public void setDriver(DriverTypes types){
        switch (types) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case SAFARI -> driver = new SafariDriver();
            default -> log.info("Not a valid driver detail: {}", types.name());
        }
    }
}
