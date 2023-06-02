package com.epam.training.oyatullogulomjonov.test;

import com.epam.training.oyatullogulomjonov.model.User;
import com.epam.training.oyatullogulomjonov.service.YahooUserCreator;
import com.epam.training.oyatullogulomjonov.page.YahooLoginPage;
import com.epam.training.oyatullogulomjonov.page.YahooMailPage;
import org.testng.annotations.Test;
import org.testng.Assert;

public class YahooMailTest extends CommonConditions {
    @Test(description = "Login with valid email and password and check that Yahoo Mail page is opened", groups = {"yahooLogOut"})
    public void loginWithValidEmailAndValidPassword() {
      User user = new YahooUserCreator().withValidCredentials();
      YahooMailPage mailPage = new YahooLoginPage(driver)
      			.openPage()
      			.enterEmail(user.getEmail())
      			.clickNextButton()
      			.enterPassword(user.getPassword())
      			.clickSignInButton()
      			.switchToMailPage();
      Assert.assertTrue(mailPage.isPageOpen(), "Login is unsucessful and MailPage is not opened");
    }
    
    @Test(description = "Login with wrong password and check that the password is not accepted")
    public void loginWithValidEmailAndWrongPassword() {
      User user = new YahooUserCreator().withWrongPassword();    
      YahooLoginPage loginPage = new YahooLoginPage(driver)
      			.openPage()
      			.enterEmail(user.getEmail())
      			.clickNextButton()
      			.enterPassword(user.getPassword());
      loginPage.clickSignInButton();
      Assert.assertTrue(loginPage.isPasswordWrong(), "Wrong password is accepted as valid");
    }
    
    @Test(description = "Login with wrong email and check that the email is not accepted")
    public void loginWithWrongEmail() {
      User user = new YahooUserCreator().withWrongEmail();
      YahooLoginPage loginPage = new YahooLoginPage(driver)
      			.openPage()
      			.enterEmail(user.getEmail())
      			.clickNextButton();
      Assert.assertTrue(loginPage.isEmailNotRegistered(), "Wrong email is accepted as valid");
    }
       
    @Test(description = "Login with empty input for email and check that it is not accepted")
    public void loginWithEmptyAccount() {
      User user = new YahooUserCreator().withEmptyEmail();
      YahooLoginPage loginPage = new YahooLoginPage(driver)
      			.openPage()
      			.enterEmail(user.getEmail())
      			.clickNextButton();
      Assert.assertTrue(loginPage.isEmailNotRegistered(), "Empty account username is not ignored");
    }
    
    @Test(description = "Login with empty input for password and check that it is not accepted")
    public void loginWithValidEmailAndEmptyPassword() {
      User user = new YahooUserCreator().withEmptyPassword();
      YahooLoginPage loginPage = new YahooLoginPage(driver)
      			.openPage()
      			.enterEmail(user.getEmail())
      			.clickNextButton()
      			.enterPassword(user.getPassword());
      loginPage.clickSignInButton();
      Assert.assertTrue(loginPage.isPasswordWrong(), "Empty password is accepted as valid");
    }   
}
