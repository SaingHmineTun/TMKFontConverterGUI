package it.saimao.shan_converter.FontConverter.view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static it.saimao.shan_converter.FontConverter.utils.Utils.getAppFont;

public class MaoConverter extends JFrame {
    private JTextArea edInput;
    private JTextArea edOutput;
    private JButton btClear;
    private JButton btConvert;
    private JButton btCopy;
    private JPanel mainPanel;
    private JRadioButton rbZg2Uni;
    private JRadioButton rbUni2Zg;


    public JRadioButton getRbZg2Uni() {
        return rbZg2Uni;
    }

    public JRadioButton getRbUni2Zg() {
        return rbUni2Zg;
    }

    public JLabel getLbInput() {
        return lbInput;
    }

    public JButton getBtCopyUnicode() {
        return btCopyInput;
    }

    public JButton getBtCopyZawgyi() {
        return btCopyOutput;
    }

    public JLabel getLbOutput() {
        return lbOutput;
    }

    private JLabel lbInput;
    private JLabel lbOutput;
    private JButton btCopyInput;
    private JButton btCopyOutput;

    public MaoConverter() throws IOException, FontFormatException {

        designUi();

        setSize(900, 600);
        setDefaultLookAndFeelDecorated(true);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("TMK Font Converter");
    }

    private void designUi() throws IOException, FontFormatException {

//        UIManager.put("RadioButton.arc", 0);

        Dimension btDimension = new Dimension(150, 40);
        btClear.setPreferredSize(btDimension);
        btConvert.setPreferredSize(btDimension);
        btCopy.setPreferredSize(btDimension);
        btCopyInput.setPreferredSize(btDimension);
        btCopyOutput.setPreferredSize(btDimension);

        Font titleFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/robotocondensed-regular.ttf")).deriveFont(20f).deriveFont(Font.BOLD);
        UIManager.put("TitlePane.font", titleFont);

        Font appFont = getAppFont();
        btConvert.setFont(appFont);
        btClear.setFont(appFont);
        btCopy.setFont(appFont);
        btCopyInput.setFont(appFont);
        btCopyOutput.setFont(appFont);
        rbUni2Zg.setFont(appFont);
        rbZg2Uni.setFont(appFont);
        lbInput.setFont(appFont);
        lbOutput.setFont(appFont);

    }

    public JTextArea getEdInput() {
        return edInput;
    }

    public JTextArea getEdOutput() {
        return edOutput;
    }

    public JButton getBtClear() {
        return btClear;
    }

    public JButton getBtConvert() {
        return btConvert;
    }

    public JButton getBtCopy() {
        return btCopy;
    }
}
