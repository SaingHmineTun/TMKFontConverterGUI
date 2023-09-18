package it.saimao.shan_converter.FontConverter.listener;

import it.saimao.shan_converter.FontConverter.controller.MaoConverterController;
import it.saimao.shan_converter.FontConverter.view.FileNameConverter;
import it.saimao.shan_converter.FontConverter.view.MaoConverter;

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

        if (e.getActionCommand().equalsIgnoreCase("open")) {
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
                    jFrame.getEdInput().setText("");
                    while ((string = reader.readLine()) != null) {
                        jFrame.getEdInput().append(string);
                    }
                    reader.close();
                    inputStream.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("save")) {

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
            }
        } else if (e.getActionCommand().equalsIgnoreCase("exit")) {
            System.exit(0);
        } else if (e.getActionCommand().equalsIgnoreCase("file name converter")) {
            new FileNameConverter(controller.maoConverter);
        }

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
