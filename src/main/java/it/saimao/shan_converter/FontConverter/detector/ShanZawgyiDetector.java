package it.saimao.shan_converter.FontConverter.detector;

import java.util.regex.Pattern;

public class ShanZawgyiDetector {
    public static boolean isShanZawgyi(String input) {
        String regex = ".*[\\uaa00-\\uaa44].*";
        return Pattern.matches(regex, input);
    }

    /*
    Not perfect
    I just check if any of the shan characters are included in a string, let's say it is SHAN.
    So if you check it using words that are not included Shan specific characters (လီမီမီးယူတီ), it would definitely fail!
     */
    public static boolean isShanUnicode(String input) {
        String regex = ".*[\\u1075-\\u108a\\u1090-\\u1099\\u1062\\u1022\\ua9e0-\\ua9e6\\ua9eb\\ua9ed\\ua9ee\\ua9fb-\\ua9fd].*";
        return Pattern.matches(regex, input);
    }

}
