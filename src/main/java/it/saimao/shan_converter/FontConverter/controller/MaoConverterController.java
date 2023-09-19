package it.saimao.shan_converter.FontConverter.controller;

import it.saimao.shan_converter.FontConverter.listener.ClipboardTextListener;
import it.saimao.shan_converter.FontConverter.listener.MenuItemListener;
import it.saimao.shan_converter.FontConverter.toast.Toast;
import it.saimao.shan_converter.FontConverter.utils.Utils;
import it.saimao.shan_converter.FontConverter.view.MaoConverter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.ArrayList;

import static it.saimao.shan_converter.FontConverter.converter.ShanZawgyiConverter.uni2zg;
import static it.saimao.shan_converter.FontConverter.converter.ShanZawgyiConverter.zg2uni;
import static it.saimao.shan_converter.FontConverter.detector.ShanZawgyiDetector.isShanZawgyi;
import static it.saimao.shan_converter.FontConverter.utils.ThemeManager.*;
import static it.saimao.shan_converter.FontConverter.utils.Utils.*;

public class MaoConverterController implements ActionListener, ChangeListener {

    public MaoConverter maoConverter;
    private JTextArea edInput, edOutput;
    private JButton btConvert, btClear, btCopy, btCopyUni, btCopyZg;
    private JRadioButton rbZg2Uni, rbUni2Zg;
    private JRadioButtonMenuItem light, dark, intellij, dracula, macLight, macDark;
    private JLabel lbInput, lbOutput;
    private JMenuItem open, save, exit;
    private JCheckBoxMenuItem enablePopup;
    private ClipboardTextListener clipboardListener;
    private JMenuItem fileNameConverter;


    public MaoConverterController() throws IOException, FontFormatException {
        initializeComponents();
        initializeMenubar();
        initializeAppIcons();
        initializeListeners();
        initializeMenuListeners();

        /*
        Select Shan to Tai Nuea at the startup
         */
        rbUni2Zg.setSelected(true);
    }

    private void initializeMenuListeners() {
        MenuItemListener listener = new MenuItemListener(this);
        open.addActionListener(listener);
        save.addActionListener(listener);
        exit.addActionListener(listener);
        fileNameConverter.addActionListener(listener);
        enablePopup.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                clipboardListener = new ClipboardTextListener(maoConverter);
                new Thread(clipboardListener).start();
                Utils.setEnablePopupConverter(true);
            } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                clipboardListener.terminate();
                Utils.setEnablePopupConverter(false);
            }
        });

        // Theme
        light.addActionListener(listener);
        dark.addActionListener(listener);
        intellij.addActionListener(listener);
        dracula.addActionListener(listener);
        macLight.addActionListener(listener);
        macDark.addActionListener(listener);
    }

    private void initializeMenubar() {

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(getAppFont());

        // File Menu
        JMenu file = new JMenu("File");
        file.setFont(getAppFont());
        open = new JMenuItem("Open");
        open.setFont(getAppFont());
        open.setToolTipText("Open text file and import to input text");
        save = new JMenuItem("Save");
        save.setFont(getAppFont());
        save.setToolTipText("Save output text as text file");
        exit = new JMenuItem("Exit");
        exit.setToolTipText("Exit");
        exit.setFont(getAppFont());
        file.add(open);
        file.add(save);
        file.add(exit);

        // Tool Menu
        JMenu tool = new JMenu("Tool");
        tool.setFont(getAppFont());
        enablePopup = new JCheckBoxMenuItem("Enable Popup Converter");
        enablePopup.setToolTipText("Enable Dialog Convert when copy text!");
        enablePopup.setFont(getAppFont());
        fileNameConverter = new JMenuItem("File Name Converter");
        fileNameConverter.setToolTipText("Convert file name written in Tai Nuea to Shan");
        fileNameConverter.setFont(getAppFont());
        tool.add(fileNameConverter);
        tool.add(enablePopup);

        // Theme Menu
        JMenu theme = new JMenu("Theme");
        theme.setFont(getAppFont());
        light = new JRadioButtonMenuItem("Light");
        light.setFont(getAppFont());
        dark = new JRadioButtonMenuItem("Dark");
        dark.setFont(getAppFont());
        intellij = new JRadioButtonMenuItem("IntelliJ");
        intellij.setFont(getAppFont());
        dracula = new JRadioButtonMenuItem("Dracula");
        dracula.setFont(getAppFont());
        macLight = new JRadioButtonMenuItem("Mac Light");
        macLight.setFont(getAppFont());
        macDark = new JRadioButtonMenuItem("Mac Dark");
        macDark.setFont(getAppFont());
        theme.add(light);
        theme.add(dark);
        theme.add(intellij);
        theme.add(dracula);
        theme.add(macLight);
        theme.add(macDark);
        selectThemeAtStart();

        // Add the radio button to the group
        ButtonGroup rg = new ButtonGroup();
        rg.add(light);
        rg.add(dark);
        rg.add(intellij);
        rg.add(dracula);
        rg.add(macLight);
        rg.add(macDark);

        // Add Menu to MenuBar
        menuBar.add(file);
        menuBar.add(tool);
        menuBar.add(theme);
        maoConverter.setJMenuBar(menuBar);
    }

    private void selectThemeAtStart() {
        switch (Utils.getTheme()) {
            case LIGHT -> light.setSelected(true);
            case DARK -> dark.setSelected(true);
            case DRACULA -> dracula.setSelected(true);
            case INTELLIJ -> intellij.setSelected(true);
            case MAC_DARK -> macDark.setSelected(true);
            case MAC_LIGHT -> macLight.setSelected(true);
        }
    }


    private void initializeAppIcons() {

        ArrayList<Image> icons = new ArrayList<>();
        icons.add(new ImageIcon(getClass().getResource("/icons/logo_64.png")).getImage());
        icons.add(new ImageIcon(getClass().getResource("/icons/logo_128.png")).getImage());
        icons.add(new ImageIcon(getClass().getResource("/icons/logo_256.png")).getImage());
//        icons.add(new ImageIcon(getClass().getResource("/icons/logo_512.png")).getImage());
        maoConverter.setIconImages(icons);

    }

    private void initializeListeners() {

        btConvert.addActionListener(this);
        btClear.addActionListener(this);
        btCopy.addActionListener(this);
        rbUni2Zg.addChangeListener(this);
        rbZg2Uni.addChangeListener(this);
        btCopyZg.addActionListener(this);
        btCopyUni.addActionListener(this);
    }

    private void initializeComponents() throws IOException, FontFormatException {
        maoConverter = new MaoConverter();
        edInput = maoConverter.getEdInput();
        edInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {

                if (isShanZawgyi(edInput.getText())) {
                    rbZg2Uni.setSelected(true);
                } else {
                    rbUni2Zg.setSelected(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {

            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {

            }
        });
        edInput.setFont(getUniFont());
        edOutput = maoConverter.getEdOutput();
        edOutput.setFont(getZgFont());
        btConvert = maoConverter.getBtConvert();
        btClear = maoConverter.getBtClear();
        btCopy = maoConverter.getBtCopy();
        btCopyUni = maoConverter.getBtCopyUnicode();
        btCopyZg = maoConverter.getBtCopyZawgyi();
        rbZg2Uni = maoConverter.getRbZg2Uni();
        rbUni2Zg = maoConverter.getRbUni2Zg();
        lbInput = maoConverter.getLbInput();
        lbOutput = maoConverter.getLbOutput();


    }

    public void showWindows() {
        maoConverter.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        CONVERT
         */
        if (e.getActionCommand().equalsIgnoreCase("convert")) {

            String convertedString;
            if (rbUni2Zg.isSelected()) {
                convertedString = uni2zg(edInput.getText());
            } else {
                convertedString = zg2uni(edInput.getText());
            }
            edOutput.setText(convertedString);
        }
        /*
        CLEAR
         */
        else if (e.getActionCommand().equalsIgnoreCase("clear")) {
            edInput.setText("");
            edOutput.setText("");
        }
        /*
        Copy BOTH
         */
        else if (e.getActionCommand().equalsIgnoreCase("copy both")) {
            String myString;
            if (rbUni2Zg.isSelected()) {

                myString = "#Shan Unicode#\n" + edOutput.getText() + "\n#Shan#\n" + edInput.getText() + "\n";
            } else {

                myString = "#Shan Zawgyi#\n" + edInput.getText() + "\n#Shan#\n" + edOutput.getText() + "\n";
            }
            Utils.setShowCopyDialog(false);
            Utils.copyToClipboard(myString);
            showToast("Both Shan Unicode and Shan Zawgyi are copied successfully!");
        }
        /*
        Copy Tai Nuea
         */
        else if (e.getActionCommand().equalsIgnoreCase("copy input")) {
            Utils.setShowCopyDialog(false);
            Utils.copyToClipboard(edInput.getText());
            showToast("Input Text copied successfully!");
        }
        /*
        Copy SHAN
         */
        else if (e.getActionCommand().equalsIgnoreCase("copy output")) {

            Utils.setShowCopyDialog(false);
            Utils.copyToClipboard(edOutput.getText());
            showToast("Output Text copied successfully!");
        }
    }

    private void showToast(String s) {
        Toast toast = new Toast(s);
        toast.showToast(maoConverter.getX() + maoConverter.getWidth() / 2, maoConverter.getY() + maoConverter.getHeight() / 2 + maoConverter.getHeight() / 3);

    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        /*
        To prevent multiple change event from firing,
        I just re-assure with boolean value
         */
        if (rbUni2Zg.isSelected()) {

            lbInput.setText("Shan Unicode");
            lbOutput.setText("Shan Zawgyi");
            edInput.setFont(getUniFont());
            edOutput.setFont(getZgFont());
        } else if (rbZg2Uni.isSelected()) {
            lbInput.setText("Shan Zawgyi");
            lbOutput.setText("Shan Unicode");
            edInput.setFont(getZgFont());
            edOutput.setFont(getUniFont());
        }
    }
}
