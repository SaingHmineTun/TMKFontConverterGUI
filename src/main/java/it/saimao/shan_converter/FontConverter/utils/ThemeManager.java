package it.saimao.shan_converter.FontConverter.utils;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import java.io.*;

public class ThemeManager {

    public static final String LIGHT = "light";
    public static final String DARK = "dark";
    public static final String DRACULA = "dracula";
    public static final String INTELLIJ = "intellij";
    public static final String MAC_LIGHT = "macLight";
    public static final String MAC_DARK = "macDark";

    private static String getThemeFilePath() {
        String userHome = System.getProperty("user.home") + File.separator + "TMK_Font_Converter";
        File directory = new File(userHome);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        return userHome + File.separator + "theme.tmk";
    }

    public static void saveThemeName(String themeName) {
        try (Writer writer = new FileWriter(getThemeFilePath())) {
            writer.write(themeName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Utils.setTheme(themeName);
    }

    public static String loadThemeName() {
        String themeFilePath = getThemeFilePath();
        File file = new File(themeFilePath);

        if (!file.exists()) {
            return "light";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static LookAndFeel loadTheme(String themeName) {

        if (themeName == null) return new FlatLightLaf();

        switch (themeName) {
            case ThemeManager.DARK -> {
                return new FlatDarkLaf();
            }
            case ThemeManager.LIGHT -> {
                return new FlatLightLaf();
            }
            case ThemeManager.INTELLIJ -> {
                return new FlatIntelliJLaf();
            }
            case ThemeManager.DRACULA -> {
                return new FlatDarculaLaf();
            }
            case ThemeManager.MAC_DARK -> {
                return new FlatMacDarkLaf();
            }
            case ThemeManager.MAC_LIGHT -> {
                return new FlatMacLightLaf();
            }
        }
        return new FlatLightLaf();
    }

}