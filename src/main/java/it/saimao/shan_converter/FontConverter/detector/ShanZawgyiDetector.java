package it.saimao.shan_converter.FontConverter.detector;

public class ShanZawgyiDetector {
    public static boolean isShanZawgyi(String input) {
        for (char character: input.toCharArray()) {
            if (character >= '\uaa00' && character <= '\uaa44') return true;
        }
        return false;
    }
}
