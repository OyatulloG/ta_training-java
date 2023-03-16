package com.epam.training.oyatullogulomjonov.test;

import com.epam.training.oyatullogulomjonov.driver.DriverSingleton;
import com.epam.training.oyatullogulomjonov.util.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class CommonConditions {
    protected WebDriver driver;
    
    @BeforeMethod
    public void setUp() {
      driver = DriverSingleton.getDriver();
    }
    
    @AfterMethod
    public void stopBrowser() {
      DriverSingleton.closeDriver();
    }                    
}
