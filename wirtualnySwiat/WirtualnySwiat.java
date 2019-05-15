package wirtualnySwiat;

import wirtualnySwiat.grafika.OknoStart;

import javax.swing.*;

public class WirtualnySwiat {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OknoStart();
            }
        });
    }
}
