package it.saimao.shan_converter.FontConverter.listener;

import it.saimao.shan_converter.FontConverter.utils.Utils;
import it.saimao.shan_converter.FontConverter.view.MaoConverter;
import it.saimao.shan_converter.FontConverter.view.MaoPopupConverter;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

public class ClipboardTextListener extends Observable implements Runnable {

    Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
    private volatile boolean running = true;

    public ClipboardTextListener(MaoConverter maoConverter) {
    }

    public void terminate() {
        running = false;
    }

    @Override
    public void run() {
        // the first output will be when a non-empty text is detected
        String recentContent = "";
        // continuously perform read from clipboard
        while (running) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                // request what kind of data-flavor is supported
                List<DataFlavor> flavors = Arrays.asList(sysClip.getAvailableDataFlavors());
                // this implementation only supports string-flavor
                if (flavors.contains(DataFlavor.stringFlavor)) {
                    String data = (String) sysClip.getData(DataFlavor.stringFlavor);
                    if (!data.equals(recentContent)) {
                        recentContent = data;
                        if (Utils.isShowCopyDialog()) {
                            showNotiPopup(data);
                        }
                        if (!Utils.isShowCopyDialog()) {
                            Utils.setShowCopyDialog(true);
                        }
                        setChanged();
                        notifyObservers();
                    }
                }
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            }
        }

    }


    private void showNotiPopup(String str) {
        MaoPopupConverter maoPopupConverter = MaoPopupConverter.getInstance();
        maoPopupConverter.show(str);
    }

}
