package com.epam.training.oyatullogulomjonov.service;

import com.epam.training.oyatullogulomjonov.model.Mail;

public abstract class MailCreator {
    public String TESTDATA_MAIL;    
    public String TESTDATA_MAIL_TO;
    public String TESTDATA_MAIL_SUBJECT;
    public String TESTDATA_MAIL_CONTENT;   
    
    public static MailCreator getCreator(String mailProvider){
      if (mailProvider.equalsIgnoreCase("MailRu")){
        return new MailRuMailCreator();
      } else if (mailProvider.equalsIgnoreCase("Yahoo")){
        return new YahooMailCreator();
      } else {
        throw new IllegalArgumentException("Invalid Mail Provider: " + mailProvider);
      }
    }
    
    public abstract Mail withAllCredentials();
    public abstract Mail withEmptySubject();    
    public abstract Mail withEmptyContent();
    public abstract Mail withEmptySubjectAndEmptyContent();    
}
