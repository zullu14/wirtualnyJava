package wirtualnySwiat.zwierzeta;

import wirtualnySwiat.Swiat;
import wirtualnySwiat.Wspolrzedne;
import wirtualnySwiat.Zwierze;
import wirtualnySwiat.Rodzaj;
import wirtualnySwiat.grafika.Pole;

import java.awt.*;

public class Wilk extends Zwierze {

    public Wilk(Swiat srodowisko, Wspolrzedne miejsce) {
        super(srodowisko, miejsce);
        this.sila = 9;
        this.inicjatywa = 5;
        this.typ = Rodzaj.wilk;
    }

    @Override
    public void rysowanie(Pole pole) {
        pole.setBackground(new Color(50,130,230));
        pole.setNazwa("wilk");
    }
}