package it.saimao.shan_converter.FontConverter.view;

import it.saimao.shan_converter.FontConverter.filepicker.JFilePicker;
import it.saimao.shan_converter.FontConverter.toast.Toast;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static it.saimao.shan_converter.FontConverter.converter.ShanZawgyiConverter.uni2zg;
import static it.saimao.shan_converter.FontConverter.converter.ShanZawgyiConverter.zg2uni;
import static it.saimao.shan_converter.FontConverter.detector.ShanZawgyiDetector.isShanZawgyi;
import static it.saimao.shan_converter.FontConverter.utils.Utils.getAppFont;

public class FileNameConverter extends JDialog {

    private final JCheckBox cbAutoDetect;
    private File folder;


    public FileNameConverter(JFrame parent) {
        super(parent, "File Name Converter", true);
        setLayout(new FlowLayout());

        // set up a file picker component
        JFilePicker inputFolder = new JFilePicker("Location : ", "Choose");
        inputFolder.setMode(JFilePicker.MODE_SAVE);
        inputFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        input = inputFolder.getFileChooser();

        initDesign();

        JPanel panel = new JPanel();
        FlowLayout layout1 = new FlowLayout();
        layout1.setHgap(25);
        panel.setLayout(layout1);

        cbAutoDetect = new JCheckBox("Auto Detect");
        cbAutoDetect.setToolTipText("If enabled, Converter will only convert Zawgyi file name!");
        cbAutoDetect.setPreferredSize(new Dimension(100, 40));
        // Create a convert Button
        JButton btConvert = new JButton("Convert to Unicode");
        btConvert.setToolTipText("Will convert all file name to Unicode!");
        btConvert.setPreferredSize(new Dimension(150, 40));
        btConvert.addActionListener(e -> {
            if (inputFolder.isFolderSelected()) {
                folder = inputFolder.getFile();
                convertFileNameToUnicode();
            } else {
                Toast toast = new Toast("No folder is selected yet");
                toast.showToast(getX(), getY() + getHeight());
            }
        });

        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel.add(cbAutoDetect);
        panel.add(btConvert);

        // add the component to the frame
        add(inputFolder);
        add(panel);


        btConvert.setFont(getAppFont());
        cbAutoDetect.setFont(getAppFont());


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(600, 150);
        setLocationRelativeTo(null);    // center on screen
        setVisible(true);
    }

    private void initDesign() {
    }

    private void convertFileNameToUnicode() {

        Toast toast = new Toast("All files have been converted to Unicode!");
        File[] inputFolder = folder.listFiles();
        boolean autoDetect = cbAutoDetect.isSelected();
        if (inputFolder != null) {
            for (File file : inputFolder) {
                String convertedName;
                if (autoDetect && isShanZawgyi(file.getName())) {
                    convertedName = zg2uni(file.getName());
                    file.renameTo(new File(folder, convertedName));
                } else {
                    convertedName = zg2uni(file.getName());
                    file.renameTo(new File(folder, convertedName));
                }
            }
            toast.showToast(getX(), getY() + getHeight());
            dispose();
        } else {
            toast = new Toast("Please choose a folder to convert");
            toast.showToast(getX(), getY() + getHeight());
        }
    }
}
