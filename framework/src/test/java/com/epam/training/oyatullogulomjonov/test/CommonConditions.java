package com.epam.training.oyatullogulomjonov.test;

import com.epam.training.oyatullogulomjonov.driver.DriverSingleton;
import com.epam.training.oyatullogulomjonov.util.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;


@Listeners({TestListener.class})
public class CommonConditions {
    protected WebDriver driver;
    
    @BeforeClass
    public void setUp() {
      driver = DriverSingleton.getDriver();
    }
    
    @AfterClass
    public void stopBrowser() {
      DriverSingleton.closeDriver();
    }          
}
