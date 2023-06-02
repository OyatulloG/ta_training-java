package com.epam.training.oyatullogulomjonov.service;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.epam.training.oyatullogulomjonov.model.Mail;
import com.epam.training.oyatullogulomjonov.model.User;
import com.epam.training.oyatullogulomjonov.page.YahooLoginPage;
import com.epam.training.oyatullogulomjonov.page.YahooMailPage;

public class YahooService {
    private final Logger logger = LogManager.getRootLogger();
    private YahooLoginPage loginPage;
    private YahooMailPage mailPage;
    
    public YahooService(WebDriver driver) {
      loginPage = new YahooLoginPage(driver);
      mailPage = new YahooMailPage(driver);
    }
    
    public YahooService login(User user) {
      loginPage.openPage()
      		.enterEmail(user.getEmail())
      		.clickNextButton()
      		.enterPassword(user.getPassword())
      		.clickSignInButton()
      		.switchToMailPage();
      return this;
    }
    
    public YahooService removeAccount() {
      loginPage.openPage()
      		.clickAccountSwitcherThreeDotsButton()
      		.clickRemoveAccountButton();
      return this;
    }
    
    public YahooMailPage sendMail(Mail mail) {
      mailPage.clickComposeButton()
      		.sendKeysToMailToTextBox(mail.getMailTo())
      		.sendKeysToMailSubjectTextBox(mail.getSubject())
      		.sendKeysToMailContentTextBox(mail.getContent())
      		.clickSendMailButton();
      logger.info("New Mail is sent");
      return mailPage;   
    }
    
    public boolean isMailReceived(Mail sentMail) {
      WebElement unreadMail = mailPage.getUnreadMailWithSubject(sentMail.getSubject());
      if (unreadMail == null) {
	return false;
      }
      mailPage.clickMail(unreadMail);
      
      String mailFrom = mailPage.getMailFromText();
      String mailContent = mailPage.getMailContentText();
      Mail receivedMail = new Mail(mailFrom, mailPage.YAHOOMAIL, sentMail.getSubject(), mailContent);
      return receivedMail.equals(sentMail);   
    }
    
    public YahooService logOut() {
      mailPage.clickAccountProfileButton()
      		.clickProfileSignOutButton();
      return this;
    }
}
