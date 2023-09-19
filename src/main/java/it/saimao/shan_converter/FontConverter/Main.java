package it.saimao.shan_converter.FontConverter;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import it.saimao.shan_converter.FontConverter.controller.MaoConverterController;
import it.saimao.shan_converter.FontConverter.utils.ThemeManager;
import it.saimao.shan_converter.FontConverter.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException {


        String theme = ThemeManager.loadThemeName();
        Utils.setTheme(theme);
        try {
            UIManager.setLookAndFeel(ThemeManager.loadTheme(theme));
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        MaoConverterController converterController = new MaoConverterController();
        SwingUtilities.invokeLater(converterController::showWindows);
    }
}
