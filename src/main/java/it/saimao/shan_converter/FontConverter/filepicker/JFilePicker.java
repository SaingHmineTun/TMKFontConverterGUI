package it.saimao.shan_converter.FontConverter.filepicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class JFilePicker extends JPanel {
    private String textFieldLabel;
    private String buttonLabel;

    private JLabel label;
    private JTextField textField;
    private JButton button;

    private JFileChooser fileChooser;
    private File file;

    private int mode;
    public static final int MODE_OPEN = 1;
    public static final int MODE_SAVE = 2;

    public JFilePicker(String textFieldLabel, String buttonLabel) {
        this.textFieldLabel = textFieldLabel;
        this.buttonLabel = buttonLabel;

        fileChooser = new JFileChooser();

        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // creates the GUI
        label = new JLabel(textFieldLabel);

        textField = new JTextField(30);
        textField.setEditable(false);
        button = new JButton(buttonLabel);

        button.addActionListener(evt -> buttonActionPerformed(evt));

        add(label);
        add(textField);
        add(button);

    }

    public void setFileSelectionMode(int mode) {

        fileChooser.setFileSelectionMode(mode);

    }

    private void buttonActionPerformed(ActionEvent evt) {
        if (mode == MODE_OPEN) {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                textField.setText(file.getAbsolutePath());
            }
        } else if (mode == MODE_SAVE) {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                textField.setText(file.getAbsolutePath());
            }
        }
    }

    public File getFile() {
        return file;
    }

    public boolean isFolderSelected() {
        return textField.getText().equalsIgnoreCase("") ? false : true;
    }

    public void addFileTypeFilter(String extension, String description) {
        FileTypeFilter filter = new FileTypeFilter(extension, description);
        fileChooser.addChoosableFileFilter(filter);
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getSelectedFilePath() {
        return textField.getText();
    }

    public JFileChooser getFileChooser() {
        return this.fileChooser;
    }
}
