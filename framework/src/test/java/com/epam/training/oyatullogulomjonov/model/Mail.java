package com.epam.training.oyatullogulomjonov.model;

import java.util.Objects;

public class Mail {
    private String mailFrom;
    private String mailTo;
    private String subject;
    private String content;

    public Mail(String mailFrom, String mailTo, String subject, String content){
      this.mailFrom = mailFrom;
      this.mailTo = mailTo;
      this.subject = subject;
      this.content = content;
    }

    public String getMailFrom(){
      return mailFrom;
    }

    public void setMailFrom(String mailFrom){
      this.mailFrom = mailFrom;
    }
    
    public String getMailTo(){
      return mailTo;
    }

    public void setMailTo(String mailTo){
      this.mailTo = mailTo;
    }
    
    public String getSubject(){
      return subject;
    }
    
    public void setSubject(String subject){
      this.subject = subject;
    }
    
    public String getContent(){
      return content;
    }
    
    public void setContent(String content){
      this.content = content;
    }
    
    @Override
    public String toString(){
      return "Mail{" + 
      		"mailFrom='" + mailFrom + "'" +
      		"mailTo='" + mailTo + "'" +       		 
      		"subject='" + subject + "'" + 
      		"content='" + content + "'" + 
      		"}";
    }
        
    @Override
    public boolean equals(Object o){
      if (o == this) {
        return true;
      }
      if (!(o instanceof Mail)){
        return false;
      }      
      Mail mail = (Mail) o;
      return Objects.equals(getMailFrom(), mail.getMailFrom()) &&
      		Objects.equals(getMailTo(), mail.getMailTo()) &&
      		Objects.equals(getSubject(), mail.getSubject()) &&
      		Objects.equals(getContent(), mail.getContent());      
    }   
    
    @Override
    public int hashCode(){
      return Objects.hash(getMailFrom(), getMailTo(), getSubject(), getContent());
    }
}
