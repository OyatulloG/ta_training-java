package com.epam.training.oyatullogulomjonov.validationscolorcode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorCodeValidation {
    public static boolean validateColorCode(String color) {
    	if (color == null) {
	    return false;
    	}
        Pattern pattern = Pattern.compile("#[0-9[a-f]]{3}|#[0-9[a-f]]{6}", Pattern.CASE_INSENSITIVE); 
        Matcher matcher = pattern.matcher(color);
        return matcher.matches();
    }
}
