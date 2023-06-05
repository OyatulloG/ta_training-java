package com.epam.training.oyatullogulomjonov.test;

import com.epam.training.oyatullogulomjonov.model.User;
import com.epam.training.oyatullogulomjonov.model.Mail;
import com.epam.training.oyatullogulomjonov.service.MailRuUserCreator;
import com.epam.training.oyatullogulomjonov.service.YahooUserCreator;
import com.epam.training.oyatullogulomjonov.service.MailRuMailCreator;
import com.epam.training.oyatullogulomjonov.service.YahooMailCreator;
import com.epam.training.oyatullogulomjonov.service.MailRuService;
import com.epam.training.oyatullogulomjonov.service.YahooService;
import com.epam.training.oyatullogulomjonov.page.MailRuMailPage;
import com.epam.training.oyatullogulomjonov.page.YahooMailPage;
import org.testng.annotations.Test;
import org.testng.Assert;

public class IntegrationTest extends CommonConditions {
    User mailRuUser = new MailRuUserCreator().withValidCredentials();
    Mail mailRuMail = new MailRuMailCreator().withAllCredentials();
    User yahooUser = new YahooUserCreator().withValidCredentials();
    Mail yahooMail = new YahooMailCreator().withAllCredentials();
    
    @Test(description = "Send mail from MailRu to Yahoo, and check that mail is sent", priority = 1,
    		groups = {"closeMailSentMessage", "mailRuLogOut"})
    public void verifyMailSentFromMailRuToYahoo() {
    	MailRuMailPage mailRuMailPage = new MailRuService(driver).login(mailRuUser)
    								.sendMail(mailRuMail);
    	Assert.assertTrue(mailRuMailPage.isMailSent(), "Mail is not sent");
    }
    
    @Test(description = "Check that Yahoo has received mail from MailRu", priority = 2, groups = {"yahooLogOut"})
    public void verifyYahooReceivedMailFromMailRu() {
	YahooService yahooService = new YahooService(driver).login(yahooUser);
      	Assert.assertTrue(yahooService.isMailReceived(mailRuMail), "Mail from MailRu is not received"); 	
    }
    
    @Test(description = "Send mail from Yahoo to MailRu, and check that mail is sent", priority = 3, groups = {"yahooLogOut"})
    public void verifyMailSentFromYahooToMailRu() {
	YahooMailPage yahooMailPage = new YahooService(driver).login(yahooUser)
								.sendMail(yahooMail);
      	Assert.assertTrue(yahooMailPage.isMailSent(), "Mail is not sent");
    }
    
    @Test(description = "Check that MailRu received mail from Yahoo", priority = 4, groups = {"mailRuLogOut"})
    public void verifyMailRuReceivedMailFromYahoo() {
	MailRuService mailRuService = new MailRuService(driver).login(mailRuUser);
	Assert.assertTrue(mailRuService.isMailReceived(yahooMail), "Mail from Yahoo is not received");	
    }   
}
