package com.epam.training.oyatullogulomjonov.test;

import com.epam.training.oyatullogulomjonov.model.User;
import com.epam.training.oyatullogulomjonov.service.UserCreator;
import com.epam.training.oyatullogulomjonov.service.MailRuUserCreator;
import com.epam.training.oyatullogulomjonov.service.YahooUserCreator;
import com.epam.training.oyatullogulomjonov.model.Mail;
import com.epam.training.oyatullogulomjonov.service.MailCreator;
import com.epam.training.oyatullogulomjonov.service.MailRuMailCreator;
import com.epam.training.oyatullogulomjonov.service.YahooMailCreator;
import com.epam.training.oyatullogulomjonov.page.MailRuLoginPage;
import com.epam.training.oyatullogulomjonov.page.MailRuMailPage;
import com.epam.training.oyatullogulomjonov.page.YahooHomePage;
import com.epam.training.oyatullogulomjonov.page.YahooLoginPage;
import com.epam.training.oyatullogulomjonov.page.YahooMailPage;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;
import org.testng.Assert;

public class IntegrationTest extends CommonConditions {
    User mailRuUser = UserCreator.getCreator("MailRu").withValidCredentials();
    Mail mailRuMail = MailCreator.getCreator("MailRu").withAllCredentials();
    User yahooUser = UserCreator.getCreator("Yahoo").withValidCredentials();
    Mail yahooMail = MailCreator.getCreator("Yahoo").withAllCredentials();
    
    @Test(description = "Send mail from Yahoo to MailRu, check that mail is sent and received")
    public void verifyMailSentFromYahooToMailRu() {
        YahooMailPage yahooMailPage = new YahooLoginPage(driver)
      				.enterEmail(yahooUser.getEmail())
      				.clickNextButton()
	      			.enterPassword(yahooUser.getPassword())
	      			.clickSignInButton()
      				.switchToMailPage()
      				.sendNewMail(yahooMail);
      	Assert.assertTrue(yahooMailPage.isMailSent(), "Mail is not sent");
	yahooMailPage.logOut();      	
	new YahooLoginPage(driver).removeAccount();      	
      	
      	MailRuMailPage mailRuMailPage = new MailRuLoginPage(driver)
      	                       .enterEmail(mailRuUser.getEmail())
      	                       .clickEnterPasswordButton()
      	                       .enterPassword(mailRuUser.getPassword())
      	                       .clickSignInButton();
	Mail receivedMail = mailRuMailPage.getMailWithSubject(yahooMail.getSubject());
	Assert.assertTrue(receivedMail.equals(yahooMail), "Received and sent mails are not the same");
	mailRuMailPage.logOut();
    }
    
    @Test(description = "Send mail from MailRu to Yahoo, check that mail is sent and received")
    public void verifyMailSentFromMailRuToYahoo() {
    	MailRuMailPage mailRuMailPage = new MailRuLoginPage(driver)
    				.enterEmail(mailRuUser.getEmail())
    				.clickEnterPasswordButton()
    				.enterPassword(mailRuUser.getPassword())
    				.clickSignInButton()
    				.sendNewMail(mailRuMail);
    	Assert.assertTrue(mailRuMailPage.isMailSent(), "Mail is not sent");   	
    	mailRuMailPage.clickMailSentMessageCloseButton();
	mailRuMailPage.logOut();    	
    	
    	YahooMailPage yahooMailPage = new YahooLoginPage(driver)
	      			.enterEmail(yahooUser.getEmail())
	      			.clickNextButton()
      				.enterPassword(yahooUser.getPassword())
      				.clickSignInButton()
      				.switchToMailPage();      
      	Mail receivedMail = yahooMailPage.getMailWithSubject(mailRuMail.getSubject());
      	Assert.assertTrue(receivedMail.equals(mailRuMail), "Received and sent mails are not the same");    	
	yahooMailPage.logOut();      	      	
	new YahooLoginPage(driver).removeAccount();
    }   
}
