package com.jupiter.hooks;

import com.jupiter.driverWrappers.DriverInstance;
import com.jupiter.driverWrappers.DriverTypes;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.messages.types.Scenario;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BrowserHooks {
    public static DriverInstance driverInstance=new DriverInstance();
    @Before
    public void beforeTest(){
        driverInstance.setDriver(DriverTypes.CHROME);
        log.info(new Scenario().getName());
    }

    @After
    public void tearDown(){
        driverInstance.getDriver().quit();
    }

}
