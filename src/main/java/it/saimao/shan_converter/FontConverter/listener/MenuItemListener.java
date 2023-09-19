package it.saimao.shan_converter.FontConverter.listener;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import it.saimao.shan_converter.FontConverter.controller.MaoConverterController;
import it.saimao.shan_converter.FontConverter.toast.Toast;
import it.saimao.shan_converter.FontConverter.utils.ThemeManager;
import it.saimao.shan_converter.FontConverter.utils.Utils;
import it.saimao.shan_converter.FontConverter.view.FileNameConverter;
import it.saimao.shan_converter.FontConverter.view.MaoConverter;
import it.saimao.shan_converter.FontConverter.view.MaoPopupConverter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class MenuItemListener implements ActionListener {

    MaoConverter jFrame;
    MaoConverterController controller;

    public MenuItemListener(MaoConverterController converterController) {
        this.controller = converterController;
        this.jFrame = converterController.maoConverter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand().toLowerCase()) {
            case "open" -> {

                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                chooser.setFileFilter(filter);
                int result = chooser.showOpenDialog(jFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try {
                        InputStream inputStream = new FileInputStream(file);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                        String string;
                        StringBuilder sb = new StringBuilder();
                        while ((string = reader.readLine()) != null) {
                            sb.append(string);
                        }
                        jFrame.getEdInput().setText(sb.toString());
                        reader.close();
                        inputStream.close();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            case "save" -> {

                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                fileChooser.setFileFilter(filter);
                int result = fileChooser.showSaveDialog(jFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
                        writer.write(jFrame.getEdOutput().getText());
                        writer.flush();
                        writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    new Toast("File has been saved successfully!").showToast(jFrame.getX() + jFrame.getWidth() / 2, jFrame.getY() + jFrame.getHeight() / 2 + jFrame.getHeight() / 3);
                }
            }

            case "exit" -> System.exit(0);
            case "file name converter" -> new FileNameConverter(controller.maoConverter);
            case "light" -> {
                try {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    updateUI();
                    ThemeManager.saveThemeName(ThemeManager.LIGHT);
                } catch (UnsupportedLookAndFeelException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case "dark" -> {
                try {
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    updateUI();
                    ThemeManager.saveThemeName(ThemeManager.DARK);
                } catch (UnsupportedLookAndFeelException ex) {
                    throw new RuntimeException(ex);
                }
            }

            case "intellij" -> {
                try {
                    UIManager.setLookAndFeel(new FlatIntelliJLaf());
                    updateUI();
                    ThemeManager.saveThemeName(ThemeManager.INTELLIJ);
                } catch (UnsupportedLookAndFeelException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case "dracula" -> {
                try {
                    UIManager.setLookAndFeel(new FlatDarculaLaf());
                    updateUI();
                    ThemeManager.saveThemeName(ThemeManager.DRACULA);
                } catch (UnsupportedLookAndFeelException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case "mac light" -> {
                try {
                    UIManager.setLookAndFeel(new FlatMacLightLaf());
                    updateUI();
                    ThemeManager.saveThemeName(ThemeManager.MAC_LIGHT);
                } catch (UnsupportedLookAndFeelException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case "mac dark" -> {
                try {
                    UIManager.setLookAndFeel(new FlatMacDarkLaf());
                    SwingUtilities.updateComponentTreeUI(SwingUtilities.getRootPane(jFrame));
                    if (Utils.isEnablePopupConverter())
                        SwingUtilities.updateComponentTreeUI(SwingUtilities.getRootPane(MaoPopupConverter.getInstance()));
                    ThemeManager.saveThemeName(ThemeManager.MAC_DARK);
                } catch (UnsupportedLookAndFeelException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }

    }

    private void updateUI() {
        SwingUtilities.updateComponentTreeUI(SwingUtilities.getRootPane(jFrame));
        if (Utils.isEnablePopupConverter())
            SwingUtilities.updateComponentTreeUI(SwingUtilities.getRootPane(MaoPopupConverter.getInstance()));
    }

//    @Override
//    public void itemStateChanged(ItemEvent e) {
//        // Enable Popup Converter
//        if (e.getStateChange() == 1) {
//            it.saimao.shan_converter.FontConverter.controller.getClipboardListener().start();
//        } else {
//            it.saimao.shan_converter.FontConverter.controller.getClipboardListener().stop();
//        }
//    }
}
