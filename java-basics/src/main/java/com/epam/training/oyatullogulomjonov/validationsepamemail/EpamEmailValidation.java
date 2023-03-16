package com.epam.training.oyatullogulomjonov.validationsepamemail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EpamEmailValidation {
    public static boolean validateEpamEmail(String email) {
        if (email == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[a-zA-Z]+_[a-zA-Z]+\\d*@epam\\.com", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
