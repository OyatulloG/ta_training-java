package com.epam.training.oyatullogulomjonov.model;

public class Mail {
    private String email;
    private String subject;
    private String content;

    public Mail(String email, String subject, String content) {
      this.email = email;
      this.subject = subject;
      this.content = content;
    }

    public String getEmail() {
      return email;
    }
    
    public String getSubject() {
      return subject;
    }
    
    public String getContent() {
      return content;
    }
        
    @Override
    public boolean equals(Object obj) {
      if (obj == null) {
        return false;
      }
      
      if (obj == this) {
        return true;
      }
      
      Mail other = (Mail) obj;
      return this.email.equals(other.email) && 
      		this.subject.equals(other.subject) && 
      		this.content.equals(other.content);
    }   
}
