package it.saimao.shan_converter.FontConverter.view;

import it.saimao.shan_converter.FontConverter.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static it.saimao.shan_converter.FontConverter.converter.ShanZawgyiConverter.uni2zg;
import static it.saimao.shan_converter.FontConverter.converter.ShanZawgyiConverter.zg2uni;
import static it.saimao.shan_converter.FontConverter.detector.ShanZawgyiDetector.isShanZawgyi;

public class MaoPopupConverter extends JDialog implements ActionListener {
    private JTextArea taResult;
    private JPanel contentPane;
    private JButton btConvert;
    private JButton btCopy;
    private static MaoPopupConverter maoPopupConverter;
    private Font uniFont, zgFont, uiFont;

    public static MaoPopupConverter getInstance() {
        if (maoPopupConverter == null) {
            maoPopupConverter = new MaoPopupConverter();
        }
        return maoPopupConverter;
    }

    public MaoPopupConverter() {
        initializeUI();
        initializeListeners();
    }

    private void initializeListeners() {
        btConvert.addActionListener(this);
        btCopy.addActionListener(this);
    }

    private void initializeUI() {
        setContentPane(contentPane);
        setSize(400, 275);
        setAlwaysOnTop(true);
        setTitle("Tai Nuea Tools");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        try {
            uniFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/myanmar_taung_thu.ttf")).deriveFont(16f);
            zgFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/zawgyi.ttf")).deriveFont(15f);
            uiFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/robotocondensed-regular.ttf")).deriveFont(18f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        taResult.setFont(uniFont);
        btConvert.setFont(uiFont);
        btCopy.setFont(uiFont);
    }

    public void show(String str) {
        String convertedString;
        if (isShanZawgyi(str)) {
            convertedString = zg2uni(str);
            taResult.setFont(uniFont);
        } else {
            convertedString = uni2zg(str);
            taResult.setFont(zgFont);
        }
        taResult.setText(convertedString);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equalsIgnoreCase("convert")) {
            String convertedText;
            /*
            Detect if the text is Tai Nuea,
            if it was then convert Tai Nuea to Shan
             */
            if (isShanZawgyi(taResult.getText())) {
                convertedText = zg2uni(taResult.getText());
                taResult.setFont(uniFont);
            } else {
                convertedText = uni2zg(taResult.getText());
                taResult.setFont(zgFont);
            }
            taResult.setText(convertedText);
        } else if (e.getActionCommand().equalsIgnoreCase("copy")) {
            Utils.setShowCopyDialog(false);
            Utils.copyToClipboard(taResult.getText());
        }
    }
}
