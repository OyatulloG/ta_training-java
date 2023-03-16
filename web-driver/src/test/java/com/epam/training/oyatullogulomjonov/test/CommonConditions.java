package com.epam.training.oyatullogulomjonov.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class CommonConditions {
    protected WebDriver driver;
    
    @BeforeMethod
    public void setUp() {
      WebDriverManager.chromedriver().setup();
      ChromeOptions chromeOptions = new ChromeOptions();
      chromeOptions.addArguments("--remote-allow-origins=*");
      driver = new ChromeDriver(chromeOptions);      
      driver.manage().window().maximize();      
    }
    
    @AfterMethod
    public void stopBrowser() {
      driver.quit();
    }                    
}
