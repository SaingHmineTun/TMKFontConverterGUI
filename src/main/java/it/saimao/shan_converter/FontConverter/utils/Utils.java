package it.saimao.shan_converter.FontConverter.utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Utils {

    private static boolean showCopyDialog = true;

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

}
