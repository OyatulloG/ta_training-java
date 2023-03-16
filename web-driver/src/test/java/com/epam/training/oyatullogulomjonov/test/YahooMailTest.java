package com.epam.training.oyatullogulomjonov.test;

import com.epam.training.oyatullogulomjonov.model.Mail;
import com.epam.training.oyatullogulomjonov.page.YahooHomePage;
import com.epam.training.oyatullogulomjonov.page.YahooLoginPage;
import com.epam.training.oyatullogulomjonov.page.YahooMailPage;
import org.testng.annotations.Test;
import org.testng.Assert;

public class YahooMailTest extends CommonConditions {
    @Test
    public void loginWithValidEmailAndValidPassword() {
      YahooMailPage mailPage = new YahooLoginPage(driver)
      			.openPage()
      			.enterEmail("sampleonesample")
      			.enterPassword("autoTest@1")
      			.switchToMailPage();
      Assert.assertTrue(mailPage.isPageOpen());
    }
    
    @Test
    public void loginWithValidEmailAndWrongPassword() {
      YahooLoginPage loginPage = new YahooLoginPage(driver)
      			.openPage()
      			.enterEmail("sampleonesample");
      YahooHomePage homePage = loginPage.enterPassword("autoTest@1dsd");
      Assert.assertTrue(loginPage.isPasswordWrong());
    }
    
    @Test
    public void loginWithWrongEmail() {
      YahooLoginPage loginPage = new YahooLoginPage(driver)
      			.openPage()
      			.enterEmail("sampleonesamplefdsfd");
      Assert.assertTrue(loginPage.isEmailNotRegistered());
    }
    
    @Test
    public void loginWithEmptyAccount() {
      YahooLoginPage loginPage = new YahooLoginPage(driver)
      			.openPage()
      			.enterEmail("");
      Assert.assertTrue(loginPage.isEmailNotRegistered());
    }
    
    @Test
    public void loginWithValidEmailAndEmptyPassword() {
      YahooLoginPage loginPage = new YahooLoginPage(driver)
      			.openPage()
      			.enterEmail("sampleonesample");
      YahooHomePage homePage = loginPage.enterPassword("");
      Assert.assertTrue(loginPage.isPasswordWrong());
    }
    
    @Test
    public void sendNewMail() {
      YahooMailPage mailPage = new YahooLoginPage(driver)
      			.openPage()
      			.enterEmail("sampleonesample")
      			.enterPassword("autoTest@1")
      			.switchToMailPage()
      			.sendNewMail(new Mail("sampletwosample@mail.ru", "Message from Yahoo", "Dear MailRu\nI am Yahoo"));
      Assert.assertTrue(mailPage.isMailSent());
    }    
        
    @Test
    public void isMailReceived() {
      YahooMailPage mailPage = new YahooLoginPage(driver)
      			.openPage()
      			.enterEmail("sampleonesample")
      			.enterPassword("autoTest@1")
      			.switchToMailPage();      
      Mail expectedMail = new Mail("sampletwosample@mail.ru", "Message from MailRu", "Dear Yahoo\nI am MailRu");      
      Mail mail = mailPage.checkMail(expectedMail.getSubject());
      Assert.assertTrue(mail.equals(expectedMail));
    }        
}
