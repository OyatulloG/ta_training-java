package com.epam.training.oyatullogulomjonov.service;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.epam.training.oyatullogulomjonov.model.Mail;
import com.epam.training.oyatullogulomjonov.model.User;
import com.epam.training.oyatullogulomjonov.page.MailRuLoginPage;
import com.epam.training.oyatullogulomjonov.page.MailRuMailPage;

public class MailRuService {
    private final Logger logger = LogManager.getRootLogger();
    private MailRuLoginPage loginPage;
    private MailRuMailPage mailPage;
    
    public MailRuService(WebDriver driver) {
      loginPage = new MailRuLoginPage(driver);
      mailPage = new MailRuMailPage(driver);      
    }
       
    public MailRuService login(User user) {
      loginPage.openPage()
      		.enterEmail(user.getEmail())
      		.clickEnterPasswordButton()
      		.enterPassword(user.getPassword())
      		.clickSignInButton();
      return this;
    }
            
    public MailRuMailPage sendMail(Mail mail) {
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
      Mail receivedMail = new Mail(mailFrom, mailPage.MAILRU, sentMail.getSubject(), mailContent);
      return receivedMail.equals(sentMail);
    }

    public MailRuLoginPage logOut() {
      return mailPage.clickAccountButton()
      			.clickAccountLogoutButton();
    }    
}
