package it.saimao.shan_converter.FontConverter.toast;

import javax.swing.*;
import java.awt.*;

public class Toast extends JFrame {

    // String of it.saimao.shan_converter.FontConverter.toast
    String s;

    // JWindow
    JWindow w;

    public Toast(String s) {

        w = new JWindow();

        // make the background transparent
        w.setBackground(new Color(0, 0, 0, 0));

        // Create a panel
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                int wid = g.getFontMetrics().stringWidth(s);
                int hei = g.getFontMetrics().getHeight();

                //Draw the boundary of the it.saimao.shan_converter.FontConverter.toast and fill it
                g.setColor(Color.black);
                g.fillRect(10, 10, wid + 30, hei + 10);
                g.setColor(Color.black);
                g.drawRect(10, 10, wid + 30, hei + 10);

                // Set the color of text
                g.setColor(new Color(255, 255, 255, 240));
                g.drawString(s, 25, 27);
                int t = 250;

                // Draw the shadow of the it.saimao.shan_converter.FontConverter.toast
                for (int i = 0; i < 4; i++) {
                    t -= 60;
                    g.setColor(new Color(0, 0, 0, t));
                    g.drawRect(10 - i, 10 - i, wid + 30 + i * 2, hei + 10 + i * 2);
                }
            }
        };
        w.add(p);
        w.setSize(300, 100);

    }

    public void showToast(int x, int y) {
        try {
            w.setLocation(x, y);
            // wait for some time
            w.setOpacity(1);
            w.setVisible(true);
            w.setAlwaysOnTop(true);
            Thread.sleep(500);
            // make the message disappear slowly
            for (double d = 1.0; d > 0.2; d -= 0.1) {
                Thread.sleep(100);
                w.setOpacity((float) d);
            }
            // set the visibility
            w.setVisible(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
