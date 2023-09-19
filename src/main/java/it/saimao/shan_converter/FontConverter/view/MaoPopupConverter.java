package it.saimao.shan_converter.FontConverter.view;

import it.saimao.shan_converter.FontConverter.utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static it.saimao.shan_converter.FontConverter.converter.ShanZawgyiConverter.uni2zg;
import static it.saimao.shan_converter.FontConverter.converter.ShanZawgyiConverter.zg2uni;
import static it.saimao.shan_converter.FontConverter.detector.ShanZawgyiDetector.isShanZawgyi;
import static it.saimao.shan_converter.FontConverter.utils.Utils.*;

public class MaoPopupConverter extends JDialog implements ActionListener {
    private JTextArea taResult;
    private JPanel contentPane;
    private JButton btConvert;
    private JButton btCopy;
    private static MaoPopupConverter maoPopupConverter;

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
        setSize(400, 300);
        setAlwaysOnTop(true);
        setTitle("Copy Converter");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        // TODO : Set icon image for copy converter
//        setIconImage(getIconImages().get(2));

        taResult.setFont(getUniFont());
        btConvert.setFont(getAppFont());
        btCopy.setFont(getAppFont());
    }

    public void show(String str) {
        String convertedString;
        if (isShanZawgyi(str)) {
            convertedString = zg2uni(str);
            taResult.setFont(getUniFont());
        } else {
            convertedString = uni2zg(str);
            taResult.setFont(getZgFont());
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
                taResult.setFont(getUniFont());
            } else {
                convertedText = uni2zg(taResult.getText());
                taResult.setFont(getZgFont());
            }
            taResult.setText(convertedText);
        } else if (e.getActionCommand().equalsIgnoreCase("copy")) {
            Utils.setShowCopyDialog(false);
            Utils.copyToClipboard(taResult.getText());
        }
    }
}
