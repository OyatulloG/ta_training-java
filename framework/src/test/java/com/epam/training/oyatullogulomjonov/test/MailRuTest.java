package com.epam.training.oyatullogulomjonov.test;

import com.epam.training.oyatullogulomjonov.model.User;
import com.epam.training.oyatullogulomjonov.service.MailRuUserCreator;
import com.epam.training.oyatullogulomjonov.service.MailRuService;
import com.epam.training.oyatullogulomjonov.page.MailRuLoginPage;
import com.epam.training.oyatullogulomjonov.page.MailRuMailPage;
import org.testng.annotations.Test;
import org.testng.Assert;

public class MailRuTest extends CommonConditions {        
    @Test(description = "Login with valid email and password and check that MailRu page is opened", groups = {"mailRuLogOut"})
    public void loginWithValidEmailAndValidPassword() {
      User user = new MailRuUserCreator().withValidCredentials();
      new MailRuService(driver).login(user);
      Assert.assertTrue(new MailRuMailPage(driver).isPageOpen(), "Login is unsucessful and MailPage is not opened");
    }
    
    @Test(description = "Login with wrong password and check that the password is not accepted")
    public void loginWithValidEmailAndWrongPassword() { 
      User user = new MailRuUserCreator().withWrongPassword();    
      MailRuLoginPage loginPage = new MailRuLoginPage(driver);
      loginPage.openPage()
      		.enterEmail(user.getEmail())
      		.clickEnterPasswordButton()
      		.enterPassword(user.getPassword())
      		.clickSignInButton();
      Assert.assertTrue(loginPage.isPasswordWrong(), "Wrong password is accepted as valid");
    }

    @Test(description = "Login with wrong email and check that the email is not accepted")
    public void loginWithWrongEmail() {
      User user = new MailRuUserCreator().withWrongEmail();
      MailRuLoginPage loginPage = new MailRuLoginPage(driver)
      			.openPage()
      			.enterEmail(user.getEmail())
      			.clickEnterPasswordButton();
      Assert.assertTrue(loginPage.isAccountNotRegistered(), "Wrong email is accepted as valid");
    }
        
    @Test(description = "Login with empty input for email and check that it is not accepted")
    public void loginWithEmptyAccount() {
      User user = new MailRuUserCreator().withEmptyEmail();
      MailRuLoginPage loginPage = new MailRuLoginPage(driver)
      			.openPage()
      			.enterEmail(user.getEmail())
      			.clickEnterPasswordButton();
      Assert.assertTrue(loginPage.isAccountEmpty(), "Empty account username is not ignored");
    }

    @Test(description = "Login with empty input for password and check that it is not accepted")
    public void loginWithValidEmailAndEmptyPassword() {
      User user = new MailRuUserCreator().withEmptyPassword();
      MailRuLoginPage loginPage = new MailRuLoginPage(driver);
      loginPage.openPage()
      		.enterEmail(user.getEmail())
      		.clickEnterPasswordButton()
      		.enterPassword(user.getPassword())
      		.clickSignInButton();
      Assert.assertTrue(loginPage.isPasswordWrong(), "Empty password is accepted as valid");
    }
}
