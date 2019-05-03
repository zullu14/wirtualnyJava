package wirtualnySwiat.grafika;

import javax.swing.*;
import java.awt.*;

public class Pole extends JPanel {

    private JLabel nazwa;

    Pole(int x, int y) {
        setBackground(new Color(100, 160, 100));
        nazwa = new JLabel();
        nazwa.setFont(new Font("Calibri", Font.BOLD, 24));
        add(nazwa);

    }

    public void setNazwa(String tekst) {
        nazwa.setText(tekst);
    }

    void clear() {
        setNazwa("");
        setBackground(new Color(100, 160, 100));
    }
}
