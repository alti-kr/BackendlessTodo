package com.gmail.sergii_tymofieiev.backendlesstodo.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * @author Sergii Tymofieiev on 13.11.2018
 */
public class Utils {

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return isValidByPattern(phoneNumber, Constants.PATTERN_PHONE_NUMBER);
    }
    private static boolean isValidByPattern(String s, String pattern) {
        Pattern put = Pattern.compile(pattern);
        Matcher m = put.matcher(s);
        if (m.matches()) {
            return true;
        }
        return false;
    }
}
