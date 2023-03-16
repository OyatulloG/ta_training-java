package com.epam.training.oyatullogulomjonov.test;

import com.epam.training.oyatullogulomjonov.model.Mail;
import com.epam.training.oyatullogulomjonov.page.MailRuLoginPage;
import com.epam.training.oyatullogulomjonov.page.MailRuMailPage;
import org.testng.annotations.Test;
import org.testng.Assert;

public class MailRuTest extends CommonConditions {
    @Test
    public void loginWithValidEmailAndValidPassword() {
      MailRuMailPage mailPage = new MailRuLoginPage(driver)
      			.openPage()
      			.enterEmail("sampletwosample")
      			.enterPassword("autoTest@2#aT");      
      Assert.assertTrue(mailPage.isPageOpen());
    }

    @Test
    public void loginWithValidEmailAndWrongPassword() {     
      MailRuLoginPage loginPage = new MailRuLoginPage(driver).openPage();
      MailRuMailPage mailPage = loginPage
      			.enterEmail("sampletwosample")
      			.enterPassword("autoTest@2");
      Assert.assertTrue(loginPage.isPasswordWrong());
    }

    @Test    
    public void loginWithWrongEmail() {
      MailRuLoginPage loginPage = new MailRuLoginPage(driver)
      			.openPage()
      			.enterEmail("sampletwosamplesdfdsf");
      Assert.assertTrue(loginPage.isAccountNotRegistered());            
    }
    
    @Test
    public void loginWithEmptyAccount() {
      MailRuLoginPage loginPage = new MailRuLoginPage(driver)
      			.openPage()
      			.enterEmail("");
      Assert.assertTrue(loginPage.isAccountEmpty());
    }

    @Test
    public void loginWithValidEmailAndEmptyPassword() {
      MailRuLoginPage loginPage = new MailRuLoginPage(driver).openPage();
      MailRuMailPage mailPage = loginPage
      			.enterEmail("sampletwosample")
      			.enterPassword("");
      Assert.assertTrue(loginPage.isPasswordWrong());
    }

    @Test
    public void sendNewMail() {
      MailRuMailPage mailPage = new MailRuLoginPage(driver)
      			.openPage()
      			.enterEmail("sampletwosample")
      			.enterPassword("autoTest@2#aT");      			
      mailPage.sendNewMail(new Mail("sampleonesample@yahoo.com", "Message from MailRu", "Dear Yahoo\nI am MailRu"));
      Assert.assertTrue(mailPage.isMailSent());
    }    
    
    @Test
    public void isMailReceived() {
      MailRuMailPage mailPage = new MailRuLoginPage(driver)
      			.openPage()
      			.enterEmail("sampletwosample")   
      			.enterPassword("autoTest@2#aT");
      Mail expectedMail = new Mail("sampleonesample@yahoo.com", "Message from Yahoo", "Dear MailRu\nI am Yahoo");
      Mail mail = mailPage.checkMail(expectedMail.getSubject()); 
      Assert.assertTrue(mail.equals(expectedMail));
    }
}
