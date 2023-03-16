package com.epam.training.oyatullogulomjonov.contactbook;

public class Contact {
    private String contactName;
    private String title = "Name";
    private ContactInfo name = new NameContactInfo();
    private Email[] emails = new Email[3];
    private int countEmail = 0;
    private ContactInfo phoneNumber;
    private int countPhoneNumber = 0;
    private Social[] socialMedias = new Social[5];  
    private int countSocialMedia = 0;

    public Contact(String contactName) {
        this.contactName = contactName;
    }

    private class NameContactInfo implements ContactInfo {
        @Override
        public String getTitle() {
            return title;
        }
        
        @Override
        public String getValue() {
            return contactName;
        }
    }
    
    public static class Email implements ContactInfo {
        public String title = "Email";
        public String email;
        
        public Email(String localPart, String domain) {
            email = localPart + "@" + domain;
        }
        
        @Override
        public String getTitle() {
            return title;
        }
        
        @Override
        public String getValue() {
            return email;
        }        
    }
    
    public static class Social implements ContactInfo {
        private String title;
        private String value;
        
        public Social(String title, String id) {
            this.title = title;
            this.value = id;
        }
        
        @Override
        public String getTitle() {
            return title;
        }
        
        @Override
        public String getValue() {
            return value;
        }
    }
    
    public void rename(String newName) {
        if (newName != null && newName.length() > 0) {
            this.contactName = newName;
        }
    }

    public Email addEmail(String localPart, String domain) {
        return addEmailToEmails(new Email(localPart, domain));
    }

    public Email addEpamEmail(String firstname, String lastname) {
        Email epamEmail = new Email(firstname, lastname) {
            private String title = "Epam Email";
            private String email = firstname + "_" + lastname + "@epam.com";
                        
            public String getTitle() {
                return title;
            }
        
            public String getValue() {
                return email;
            }
        };        
        return addEmailToEmails(epamEmail);
    }
    
    public ContactInfo addPhoneNumber(int code, String number) {
        ContactInfo phoneNumber = new ContactInfo() {
            private String title = "Tel";
            private String value = "+" + code + " " + number;
            
            public String getTitle() {
                return title;
            }
            
            public String getValue() {
                return value;
            }
        };
        
        if (countPhoneNumber == 0) {
            this.phoneNumber = phoneNumber;
            countPhoneNumber++;
            return phoneNumber;
        }        
        return null;        
    }

    public Social addTwitter(String twitterId) {
        return addSocialMediaToSocialMedias(new Social("Twitter", twitterId));        
    }

    public Social addInstagram(String instagramId) {
        return addSocialMediaToSocialMedias(new Social("Instagram", instagramId));
    }

    public Social addSocialMedia(String title, String id) { 
        return addSocialMediaToSocialMedias(new Social(title, id));        
    }

    public ContactInfo[] getInfo() {
        ContactInfo[] allInfo = new ContactInfo[1 + countPhoneNumber + countEmail + countSocialMedia];       
        int contact = 0;
        //name
        allInfo[0] = name;
        contact++;
        //phoneNumber
        if (countPhoneNumber == 1) {
            allInfo[1] = phoneNumber;
            contact++;
        }
        //emails
        System.arraycopy(emails, 0, allInfo, contact, countEmail);
        contact += countEmail;
        //social media
        System.arraycopy(socialMedias, 0, allInfo, contact, countSocialMedia);
        contact += countSocialMedia;        
        return allInfo;
    }
    
    private Email addEmailToEmails(Email email) {
        if (countEmail < emails.length) {
            emails[countEmail] = email;
            countEmail++;
            return email;
        }        
        return null;        
    }

    private Social addSocialMediaToSocialMedias(Social socialMedia) {
        if (countSocialMedia < socialMedias.length) {
            socialMedias[countSocialMedia] = socialMedia;
            countSocialMedia++;
            return socialMedia;
        }        
        return null;        
    }
}
