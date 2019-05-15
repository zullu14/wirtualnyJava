package wirtualnySwiat.grafika;

import wirtualnySwiat.Swiat;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Pole extends JPanel {

    private JLabel nazwa;
    private Plansza plansza;

    Pole(Swiat swiat, Plansza plansza, int x, int y) {
        setBackground(new Color(195, 195, 145));
        nazwa = new JLabel();
        nazwa.setFont(new Font("Calibri", Font.BOLD, 24));
        add(nazwa);
        DodajOrganizm dodajOrganizm = new DodajOrganizm(swiat, x, y);
        addMouseListener(dodajOrganizm);
        this.plansza = plansza;
    }

    public void setNazwa(String tekst) {
        BufferedImage obrazek = plansza.getGrafika(tekst);
        if (obrazek != null) {
            nazwa.setText("");
            nazwa.setIcon(new ImageIcon(obrazek));
        } else nazwa.setText(tekst);
    }

    void clear() {
        nazwa.setText("");
        setBackground(new Color(195, 195, 145));
        nazwa.setIcon(null);
    }
}
