package com.epam.training.oyatullogulomjonov.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverSingleton {
    private static WebDriver driver;
    
    private DriverSingleton(){
    }
    
    public static WebDriver getDriver(){
      if (null == driver){
        switch (System.getProperty("browser")){
          case "firefox": {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");
            driver = new FirefoxDriver(firefoxOptions);
            break;
          }
          default: {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            chromeOptions.addArguments("--window-size=1920,1080");            
            chromeOptions.addArguments("--headless");
            driver = new ChromeDriver(chromeOptions);
          }
        }
        driver.manage().window().maximize();
      }
      return driver;
    }
    
    public static void closeDriver(){
      driver.quit();
      driver = null;
    }    
}
