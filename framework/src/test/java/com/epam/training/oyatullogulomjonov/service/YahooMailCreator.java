package com.epam.training.oyatullogulomjonov.service;

import com.epam.training.oyatullogulomjonov.model.Mail;

public class YahooMailCreator {
    private final String TESTDATA_MAIL = "testdata.yahoo.mail";    
    private final String TESTDATA_MAIL_TO = "testdata.yahoo.mail.to";
    private final String TESTDATA_MAIL_SUBJECT = "testdata.yahoo.mail.subject";
    private final String TESTDATA_MAIL_CONTENT = "testdata.yahoo.mail.content";
    
    public Mail withAllCredentials() {
      return new Mail(TestDataReader.getTestData(TESTDATA_MAIL),
      			TestDataReader.getTestData(TESTDATA_MAIL_TO),
      			TestDataReader.getTestData(TESTDATA_MAIL_SUBJECT),
      			TestDataReader.getTestData(TESTDATA_MAIL_CONTENT));
    }
    
    public Mail withEmptySubject() {
      return new Mail(TestDataReader.getTestData(TESTDATA_MAIL),
      			TestDataReader.getTestData(TESTDATA_MAIL_TO), "",
      			TestDataReader.getTestData(TESTDATA_MAIL_CONTENT));    
    }
    
    public Mail withEmptyContent() {
      return new Mail(TestDataReader.getTestData(TESTDATA_MAIL),
      			TestDataReader.getTestData(TESTDATA_MAIL_TO),
      			TestDataReader.getTestData(TESTDATA_MAIL_SUBJECT), "");    
    }
    
    public Mail withEmptySubjectAndEmptyContent() {
      return new Mail(TestDataReader.getTestData(TESTDATA_MAIL),
      			TestDataReader.getTestData(TESTDATA_MAIL_TO), "", "");    
    }
}
