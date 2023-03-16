package com.epam.training.oyatullogulomjonov.test;

import com.epam.training.oyatullogulomjonov.model.User;
import com.epam.training.oyatullogulomjonov.model.Mail;
import com.epam.training.oyatullogulomjonov.service.UserCreator;
import com.epam.training.oyatullogulomjonov.service.YahooUserCreator;
import com.epam.training.oyatullogulomjonov.service.MailCreator;
import com.epam.training.oyatullogulomjonov.service.YahooMailCreator;
import com.epam.training.oyatullogulomjonov.page.YahooHomePage;
import com.epam.training.oyatullogulomjonov.page.YahooLoginPage;
import com.epam.training.oyatullogulomjonov.page.YahooMailPage;
import org.testng.annotations.Test;
import org.testng.Assert;

public class YahooMailTest extends CommonConditions {
    UserCreator yahooUserCreator = UserCreator.getCreator("Yahoo");
    MailCreator yahooMailCreator = MailCreator.getCreator("Yahoo");
    
    @Test
    public void loginWithValidEmailAndValidPassword() {
      User user = yahooUserCreator.withValidCredentials();
      YahooMailPage mailPage = new YahooLoginPage(driver)
      			.openPage()
      			.enterEmail(user.getEmail())
      			.enterPassword(user.getPassword())
      			.switchToMailPage();
      Assert.assertTrue(mailPage.isPageOpen());
    }
    
    @Test
    public void loginWithValidEmailAndWrongPassword() {
      User user = yahooUserCreator.withWrongPassword();    
      YahooLoginPage loginPage = new YahooLoginPage(driver)
      			.openPage()
      			.enterEmail(user.getEmail());
      YahooHomePage homePage = loginPage.enterPassword(user.getPassword());
      Assert.assertTrue(loginPage.isPasswordWrong());
    }
    
    @Test
    public void loginWithWrongEmail() {
      User user = yahooUserCreator.withWrongEmail();
      YahooLoginPage loginPage = new YahooLoginPage(driver)
      			.openPage()
      			.enterEmail(user.getEmail());
      Assert.assertTrue(loginPage.isEmailNotRegistered());
    }
       
    @Test
    public void loginWithEmptyAccount() {
      User user = yahooUserCreator.withEmptyEmail();
      YahooLoginPage loginPage = new YahooLoginPage(driver)
      			.openPage()
      			.enterEmail(user.getEmail());
      Assert.assertTrue(loginPage.isEmailNotRegistered());
    }
    
    @Test
    public void loginWithValidEmailAndEmptyPassword() {
      User user = yahooUserCreator.withEmptyPassword();
      YahooLoginPage loginPage = new YahooLoginPage(driver)
      			.openPage()
      			.enterEmail(user.getEmail());
      YahooHomePage homePage = loginPage.enterPassword(user.getPassword());
      Assert.assertTrue(loginPage.isPasswordWrong());
    }
    
    @Test
    public void sendNewMail() {
      User user = yahooUserCreator.withValidCredentials();
      Mail mail = yahooMailCreator.sentWithAllCredentials();
      YahooMailPage mailPage = new YahooLoginPage(driver)
      			.openPage()
      			.enterEmail(user.getEmail())
      			.enterPassword(user.getPassword())
      			.switchToMailPage()
      			.sendNewMail(mail);
      Assert.assertTrue(mailPage.isMailSent());
    }    
        
    @Test
    public void isMailReceived() {
      User user = yahooUserCreator.withValidCredentials();
      Mail expectedMail = yahooMailCreator.receivedWithAllCredentials();
      YahooMailPage mailPage = new YahooLoginPage(driver)
      			.openPage()
      			.enterEmail(user.getEmail())
      			.enterPassword(user.getPassword())
      			.switchToMailPage();      
      Mail receivedMail = mailPage.checkMail(expectedMail.getSubject());
      Assert.assertTrue(receivedMail.equals(expectedMail));
    }        
}
