package com.epam.training.oyatullogulomjonov.words;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class StringUtil {
    public static int countEqualIgnoreCaseAndSpaces(String[] words, String sample) {
        int countSample = 0;
        if (sample != null && words != null && words.length > 0) {
            sample = sample.strip();
            for (String word : words) {
                word = word.strip();
                if (sample.equalsIgnoreCase(word)) {
                    countSample++;
                }
            }
        }
        return countSample;
    }

    public static String[] splitWords(String text) {
        if (text == null || text.length() == 0 || isOnlySymbols(text)) {
            return null;
        }      
        return Arrays.stream(text.split("[,.;:\\?\\s!]+")).filter(e -> e.trim().length() > 0).toArray(String[]::new);
    }
    
    public static String convertPath(String path, boolean toWin) {
        if (path == null || path.length() == 0 || (!isUnixPath(path) && !isWinPath(path))) {
            return null;
        }

        //convert Unix path To Win path
        if (isUnixPath(path) && toWin) {
            if ('~' == path.charAt(0)) {
                path = path.replaceFirst("~/", "C:\\\\User\\\\");
                path = path.replaceFirst("~", "C:\\\\User");                
            }
            if ('/' == path.charAt(0)) {
                path = path.replaceFirst("/", "C:\\\\");
            }
            path = path.replaceAll("/", "\\\\");
        }
        //convert Win path To Unix path        
        if (isWinPath(path) && !toWin) {
            path = path.replaceFirst("C:\\\\User", "~");
            path = path.replaceFirst("C:\\\\", "/");
            path = path.replaceAll("\\\\", "/");
        }
        return path;
    }
    
    public static String joinWords(String[] words) {
        if (words == null || words.length == 0) {
            return null;
        }

        String[] validWords = getValidWords(words);
        if (validWords.length == 0) {
            return null;
        }        
        return "[" + String.join(", ", validWords) + "]";
    }

    private static boolean isOnlySymbols(String text) {
        return text.matches("[,.;:\\s\\?!]+");
    }

    private static boolean isUnixPath(String path) {
        Pattern pattern = Pattern.compile("~?/?([a-zA-Z[\\.[\\.\\.[\\s_]]]]*/?([a-zA-Z[_]]\\.[a-zA-Z])*)*");
        Matcher matcher = pattern.matcher(path);
        return matcher.matches();
    }
    
    private static boolean isWinPath(String path) {
        Pattern pattern = Pattern.compile("(C:)?\\\\?([a-zA-Z[\\.[\\.\\.[\\s_]]]]*\\\\?([a-zA-Z[_]]\\.[a-zA-Z])*)*");
        Matcher matcher = pattern.matcher(path);
        return matcher.matches();        
    }
    
    private static String[] getValidWords(String[] words) {
        String[] validWords = new String[words.length];
        int countWord = 0;        
        for (String word : words) {
            if (word.length() > 0) {
                validWords[countWord] = word;
                countWord++;
            }
        }       
        return Arrays.copyOfRange(validWords, 0, countWord);
    }
    
    public static void main(String[] args) {
        System.out.println("Test 1: countEqualIgnoreCaseAndSpaces");
        String[] words = new String[]{" WordS    \t", "words", "w0rds", "WOR  DS", };
        String sample = "words   ";
        int countResult = countEqualIgnoreCaseAndSpaces(words, sample);
        System.out.println("Result: " + countResult);
        int expectedCount = 2;
        System.out.println("Must be: " + expectedCount);

        System.out.println("Test 2: splitWords");
        String text = "   ,, first, second!!!! third";
        String[] splitResult = splitWords(text);
        System.out.println("Result : " + Arrays.toString(splitResult));
        String[] expectedSplit = new String[]{"first", "second", "third"};
        System.out.println("Must be: " + Arrays.toString(expectedSplit));

        System.out.println("Test 3: convertPath");
        String unixPath = "/some/unix/path";
        String convertResult = convertPath(unixPath, true);
        System.out.println("Result: " + convertResult);
        String expectedWinPath = "C:\\some\\unix\\path";
        System.out.println("Must be: " + expectedWinPath);

        System.out.println("Test 4: joinWords");
        String[] toJoin = new String[]{"go", "with", "the", "", "FLOW"};
        String joinResult = joinWords(toJoin);
        System.out.println("Result: " + joinResult);
        String expectedJoin = "[go, with, the, FLOW]";
        System.out.println("Must be: " + expectedJoin);
    }
}
