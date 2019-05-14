package wirtualnySwiat.grafika;

import wirtualnySwiat.Swiat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pole extends JPanel {

    private JLabel nazwa;
    private BufferedImage background = null;

    Pole(Swiat swiat, int x, int y) {
        setBackground(new Color(195, 195, 145));
        nazwa = new JLabel();
        nazwa.setFont(new Font("Calibri", Font.BOLD, 24));
        add(nazwa);
        DodajOrganizm dodajOrganizm = new DodajOrganizm(swiat, x, y);
        addMouseListener(dodajOrganizm);

    }

    public void setNazwa(String tekst) {
        nazwa.setText(tekst);
    }

    public void setBackgroundImg(String plik) {
        try {
            background = ImageIO.read(new File(plik+".png"));           // TODO: bez wczytywania nowego obrazu za kazdym razem
        } catch (IOException e) {
            setNazwa("brak obrazka");
        }
        nazwa.setIcon(new ImageIcon(background));
    }

    void clear() {
        setNazwa("");
        setBackground(new Color(195, 195, 145));
        nazwa.setIcon(null);
    }
}
