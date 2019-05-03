package wirtualnySwiat.grafika;

import wirtualnySwiat.Organizm;
import wirtualnySwiat.Swiat;

import javax.swing.*;
import java.awt.*;

public class Plansza extends JPanel {

    private Swiat swiat;
    private Pole[][] mapa;
    private int szer, wys;

    public Plansza(int w, int h, Swiat swiat) {
        this.swiat = swiat;
        this.szer = w;
        this.wys = h;
        setLayout(new GridLayout(wys, szer, 1, 1));
        this.mapa = new Pole[wys][szer];
        for(int i=0; i < wys; i++) {
            for(int j=0; j < szer; j++) {
                mapa[i][j] = new Pole(i,j);
                add(mapa[i][j]);
            }
        }
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
