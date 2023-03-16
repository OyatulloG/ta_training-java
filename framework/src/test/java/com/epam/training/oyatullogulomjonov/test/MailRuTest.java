package com.epam.training.oyatullogulomjonov.test;

import com.epam.training.oyatullogulomjonov.model.User;
import com.epam.training.oyatullogulomjonov.model.Mail;
import com.epam.training.oyatullogulomjonov.service.UserCreator;
import com.epam.training.oyatullogulomjonov.service.MailRuUserCreator;
import com.epam.training.oyatullogulomjonov.service.MailCreator;
import com.epam.training.oyatullogulomjonov.service.MailRuMailCreator;
import com.epam.training.oyatullogulomjonov.page.MailRuLoginPage;
import com.epam.training.oyatullogulomjonov.page.MailRuMailPage;
import org.testng.annotations.Test;
import org.testng.Assert;

public class MailRuTest extends CommonConditions {
    UserCreator mailRuUserCreator = UserCreator.getCreator("MailRu");
    MailCreator mailRuMailCreator = MailCreator.getCreator("MailRu");
        
    @Test
    public void loginWithValidEmailAndValidPassword() {
      User user = mailRuUserCreator.withValidCredentials();
      MailRuMailPage mailPage = new MailRuLoginPage(driver)
      			.openPage()
      			.enterEmail(user.getEmail())
      			.enterPassword(user.getPassword());      
      Assert.assertTrue(mailPage.isPageOpen());
    }
    
    @Test
    public void loginWithValidEmailAndWrongPassword() { 
      User user = mailRuUserCreator.withWrongPassword();    
      MailRuLoginPage loginPage = new MailRuLoginPage(driver).openPage();
      MailRuMailPage mailPage = loginPage
      			.enterEmail(user.getEmail())
      			.enterPassword(user.getPassword());
      Assert.assertTrue(loginPage.isPasswordWrong());
    }

    @Test
    public void loginWithWrongEmail() {
      User user = mailRuUserCreator.withWrongEmail();
      MailRuLoginPage loginPage = new MailRuLoginPage(driver)
      			.openPage()
      			.enterEmail(user.getEmail());
      Assert.assertTrue(loginPage.isAccountNotRegistered());            
    }
        
    @Test
    public void loginWithEmptyAccount() {
      User user = mailRuUserCreator.withEmptyEmail();
      MailRuLoginPage loginPage = new MailRuLoginPage(driver)
      			.openPage()
      			.enterEmail(user.getEmail());
      Assert.assertTrue(loginPage.isAccountEmpty());
    }

    @Test
    public void loginWithValidEmailAndEmptyPassword() {
      User user = mailRuUserCreator.withEmptyPassword();
      MailRuLoginPage loginPage = new MailRuLoginPage(driver).openPage();
      MailRuMailPage mailPage = loginPage
      			.enterEmail(user.getEmail())
      			.enterPassword(user.getPassword());
      Assert.assertTrue(loginPage.isPasswordWrong());
    }

    @Test
    public void sendNewMail() {
      User user = mailRuUserCreator.withValidCredentials();
      Mail mail = mailRuMailCreator.sentWithAllCredentials();
      MailRuMailPage mailPage = new MailRuLoginPage(driver)
      			.openPage()
      			.enterEmail(user.getEmail())
      			.enterPassword(user.getPassword());      			
      mailPage.sendNewMail(mail);
      Assert.assertTrue(mailPage.isMailSent());
    }    
    
    @Test
    public void isMailReceived() {
      User user = mailRuUserCreator.withValidCredentials();
      Mail expectedMail = mailRuMailCreator.receivedWithAllCredentials();
      MailRuMailPage mailPage = new MailRuLoginPage(driver)
      			.openPage()
      			.enterEmail(user.getEmail())
      			.enterPassword(user.getPassword());
      Mail receivedMail = mailPage.checkMail(expectedMail.getSubject()); 
      Assert.assertTrue(receivedMail.equals(expectedMail));
    }
}
