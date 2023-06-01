package com.epam.training.oyatullogulomjonov.service;

import com.epam.training.oyatullogulomjonov.model.User;

public class YahooUserCreator {
    private final String TESTDATA_USER_VALID_EMAIL = "testdata.yahoo.user.valid.email";
    private final String TESTDATA_USER_VALID_PASSWORD = "testdata.yahoo.user.valid.password";
    private final String TESTDATA_USER_WRONG_EMAIL = "testdata.yahoo.user.wrong.email";
    private final String TESTDATA_USER_WRONG_PASSWORD = "testdata.yahoo.user.wrong.password";
    
    public User withValidCredentials(){
      return new User(TestDataReader.getTestData(TESTDATA_USER_VALID_EMAIL),
      			TestDataReader.getTestData(TESTDATA_USER_VALID_PASSWORD));
    }
    
    public User withWrongEmail(){
      return new User(TestDataReader.getTestData(TESTDATA_USER_WRONG_EMAIL),
      			TestDataReader.getTestData(TESTDATA_USER_VALID_PASSWORD));
    }
    
    public User withWrongPassword(){
      return new User(TestDataReader.getTestData(TESTDATA_USER_VALID_EMAIL),
      			TestDataReader.getTestData(TESTDATA_USER_WRONG_PASSWORD));
    }
    
    public User withEmptyEmail(){
      return new User("", TestDataReader.getTestData(TESTDATA_USER_VALID_PASSWORD));
    }
    
    public User withEmptyPassword(){
      return new User(TestDataReader.getTestData(TESTDATA_USER_VALID_EMAIL), "");
    }
}
