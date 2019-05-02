package wirtualnySwiat.grafika;

import javax.swing.*;
import java.awt.*;

public class Pole extends JPanel {

    private JLabel nazwa;

    Pole(int x, int y) {
        setBackground(new Color(100, 180, 100));
        nazwa = new JLabel();
        add(nazwa);

    }

    public void setNazwa(String tekst) {
        nazwa.setText(tekst);
    }
}
