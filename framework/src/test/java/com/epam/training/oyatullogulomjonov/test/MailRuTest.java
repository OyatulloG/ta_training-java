package com.epam.training.oyatullogulomjonov.test;

import com.epam.training.oyatullogulomjonov.model.User;
import com.epam.training.oyatullogulomjonov.service.UserCreator;
import com.epam.training.oyatullogulomjonov.service.MailRuUserCreator;
import com.epam.training.oyatullogulomjonov.page.MailRuLoginPage;
import com.epam.training.oyatullogulomjonov.page.MailRuMailPage;
import org.testng.annotations.Test;
import org.testng.Assert;

public class MailRuTest extends CommonConditions {
    UserCreator mailRuUserCreator = UserCreator.getCreator("MailRu");
        
    @Test(description = "Login with valid email and password and check that MailRu page is opened")
    public void loginWithValidEmailAndValidPassword() {
      User user = mailRuUserCreator.withValidCredentials();
      MailRuMailPage mailPage = new MailRuLoginPage(driver)
      			.enterEmail(user.getEmail())
      			.clickEnterPasswordButton()
      			.enterPassword(user.getPassword())
      			.clickSignInButton();
      Assert.assertTrue(mailPage.isPageOpen(), "Login is unsucessful and MailPage is not opened");
      mailPage.logOut();
    }
    
    @Test(description = "Login with wrong password and check that the password is not accepted")
    public void loginWithValidEmailAndWrongPassword() { 
      User user = mailRuUserCreator.withWrongPassword();    
      MailRuLoginPage loginPage = new MailRuLoginPage(driver);
      MailRuMailPage mailPage = loginPage
      			.enterEmail(user.getEmail())
      			.clickEnterPasswordButton()
      			.enterPassword(user.getPassword())
      			.clickSignInButton();
      Assert.assertTrue(loginPage.isPasswordWrong(), "Wrong password is accepted as valid");
    }

    @Test(description = "Login with wrong email and check that the email is not accepted")
    public void loginWithWrongEmail() {
      User user = mailRuUserCreator.withWrongEmail();
      MailRuLoginPage loginPage = new MailRuLoginPage(driver)
      			.enterEmail(user.getEmail())
      			.clickEnterPasswordButton();
      Assert.assertTrue(loginPage.isAccountNotRegistered(), "Wrong email is accepted as valid");
    }
        
    @Test(description = "Login with empty input for email and check that it is not accepted")
    public void loginWithEmptyAccount() {
      User user = mailRuUserCreator.withEmptyEmail();
      MailRuLoginPage loginPage = new MailRuLoginPage(driver)
      			.enterEmail(user.getEmail())
      			.clickEnterPasswordButton();
      Assert.assertTrue(loginPage.isAccountEmpty(), "Empty account username is not ignored");
    }

    @Test(description = "Login with empty input for password and check that it is not accepted")
    public void loginWithValidEmailAndEmptyPassword() {
      User user = mailRuUserCreator.withEmptyPassword();
      MailRuLoginPage loginPage = new MailRuLoginPage(driver);
      MailRuMailPage mailPage = loginPage
      			.enterEmail(user.getEmail())
      			.clickEnterPasswordButton()
      			.enterPassword(user.getPassword())
      			.clickSignInButton();
      Assert.assertTrue(loginPage.isPasswordWrong(), "Empty password is accepted as valid");
    }
}
