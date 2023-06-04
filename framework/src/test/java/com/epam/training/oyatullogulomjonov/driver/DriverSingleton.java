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
            //chromeOptions.addArguments("--window-size=1920,1080");            
            //chromeOptions.addArguments("--allow-insecure-localhost");
            chromeOptions.addArguments("--headless");
            //chromeOptions.addArguments("--disable-gpu");
            //chromeOptions.addArguments("--no-sandbox");
            //chromeOptions.addArguments("--ignore-certificate-errors");            
            //chromeOptions.addArguments("--allow-running-insecure-content");
            //chromeOptions.addArguments("--disable-setuid-sandbox");
            //chromeOptions.addArguments("--disable-dev-shm-usage");
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
