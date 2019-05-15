package wirtualnySwiat.grafika;

import wirtualnySwiat.Akcje;
import wirtualnySwiat.Organizm;
import wirtualnySwiat.Swiat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

class Plansza extends JPanel {

    private Swiat swiat;
    private Pole[][] mapa;
    private int szer, wys;
    private HashMap<String, BufferedImage> grafiki;
    private final static String[] gatunki ={ "owca", "cyber-owca" };  // docelowo: lista gatunków z reprezentacją graficzną

    Plansza(int w, int h, Swiat swiat) {
        this.swiat = swiat;
        this.szer = w;
        this.wys = h;
        setLayout(new GridLayout(wys, szer, 1, 1));
        this.mapa = new Pole[wys][szer];
        for(int i=0; i < wys; i++) {
            for(int j=0; j < szer; j++) {
                mapa[i][j] = new Pole(swiat, this, i, j);
                add(mapa[i][j]);
            }
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                switch (ke.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        swiat.setKierunek(Akcje.prawo);
                        swiat.wykonajTure();
                        break;
                    case KeyEvent.VK_LEFT:
                        swiat.setKierunek(Akcje.lewo);
                        swiat.wykonajTure();
                        break;
                    case KeyEvent.VK_UP:
                        swiat.setKierunek(Akcje.gora);
                        swiat.wykonajTure();
                        break;
                    case KeyEvent.VK_DOWN:
                        swiat.setKierunek(Akcje.dol);
                        swiat.wykonajTure();
                        break;
                    case KeyEvent.VK_SPACE:
                        swiat.setKierunek(Akcje.spacja);
                        swiat.wykonajTure();
                        break;
                }
            }
        });

        grafiki = new HashMap<>();
        BufferedImage obrazek;
        for(String typ : gatunki) {
            try {
                obrazek = ImageIO.read(new File(typ+".png"));
                grafiki.put(typ,obrazek);
            } catch (IOException e) {
                System.out.println("brak obrazka dla typu: "+typ);
            }
        }

    }

    BufferedImage getGrafika(String typ) {
        return grafiki.get(typ);
    }

    void rysujOrganizmy() {

        for(int i=0; i < wys; i++) {
            for(int j=0; j < szer; j++) mapa[i][j].clear();
        }

        for (Organizm org : swiat.getOrganizmy()) {
            org.rysowanie(mapa[org.getPolozenie().x][org.getPolozenie().y]);
        }
    }

}
