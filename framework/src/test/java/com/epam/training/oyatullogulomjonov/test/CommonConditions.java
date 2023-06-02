package com.epam.training.oyatullogulomjonov.test;

import com.epam.training.oyatullogulomjonov.driver.DriverSingleton;
import com.epam.training.oyatullogulomjonov.util.TestListener;
import com.epam.training.oyatullogulomjonov.page.MailRuMailPage;
import com.epam.training.oyatullogulomjonov.service.MailRuService;
import com.epam.training.oyatullogulomjonov.service.YahooService;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
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
    
    @AfterMethod(onlyForGroups = {"closeMailSentMessage"})
    public void closeMailSentMessage() {
      new MailRuMailPage(driver).clickMailSentMessageCloseButton();      
    }
    
    @AfterMethod(onlyForGroups = {"mailRuLogOut"})
    public void logOutFromMailRu() {
      new MailRuService(driver).logOut();
    }
    
    @AfterMethod(onlyForGroups = {"yahooLogOut"})
    public void logOutFromYahoo() {
      new YahooService(driver).logOut()
      				.removeAccount();
    }    
}
