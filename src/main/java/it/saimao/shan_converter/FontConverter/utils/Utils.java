package it.saimao.shan_converter.FontConverter.utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;

public class Utils {

    private static boolean showCopyDialog = true;
    private static Font uniFont;
    private static Font zgFont;
    private static Font appFont;

    public static boolean isShowCopyDialog() {
        return showCopyDialog;
    }

    public static void setShowCopyDialog(boolean showCopyDialog) {
        Utils.showCopyDialog = showCopyDialog;
    }

    public static void copyToClipboard(String stringToCopy) {

        StringSelection stringSelection = new StringSelection(stringToCopy);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

//    uniFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/myanmar_taung_thu.ttf")).deriveFont(16f);
//    zgFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/zawgyi-tai.ttf")).deriveFont(15f);
//    uiFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/robotocondensed-regular.ttf")).deriveFont(18f);


    public static Font getUniFont() {
        if (uniFont == null) {
            try {
                uniFont = Font.createFont(Font.TRUETYPE_FONT, Utils.class.getResourceAsStream("/fonts/myanmar_taung_thu.ttf")).deriveFont(16f);
            } catch (FontFormatException | IOException e) {
                throw new RuntimeException(e);
            }
        }

        return uniFont;
    }

    public static Font getZgFont() {

        if (zgFont == null) {
            try {
                zgFont = Font.createFont(Font.TRUETYPE_FONT, Utils.class.getResourceAsStream("/fonts/zawgyi-tai.ttf")).deriveFont(15f);
            } catch (FontFormatException | IOException e) {
                throw new RuntimeException(e);
            }
        }

        return zgFont;
    }

    public static Font getAppFont() {
        if (appFont == null) {
            try {
                appFont = Font.createFont(Font.TRUETYPE_FONT, Utils.class.getResourceAsStream("/fonts/robotocondensed-regular.ttf")).deriveFont(18f);
            } catch (FontFormatException | IOException e) {
                throw new RuntimeException(e);
            }
        }

        return appFont;
    }
}
