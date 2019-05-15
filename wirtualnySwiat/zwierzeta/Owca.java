package wirtualnySwiat.zwierzeta;

import wirtualnySwiat.Rodzaj;
import wirtualnySwiat.Swiat;
import wirtualnySwiat.Wspolrzedne;
import wirtualnySwiat.Zwierze;
import wirtualnySwiat.grafika.Pole;

import java.awt.*;

public class Owca extends Zwierze {

    public Owca(Swiat srodowisko, Wspolrzedne miejsce) {
        super(srodowisko, miejsce);
        this.sila = 4;
        this.inicjatywa = 4;
        this.typ = Rodzaj.owca;
    }

    public Owca(Swiat srodowisko, Wspolrzedne miejsce, int sila, int wiek) {
        super(srodowisko, miejsce, sila, wiek);
        this.inicjatywa = 4;
        this.typ = Rodzaj.owca;
    }

    @Override
    public void rysowanie(Pole pole) {
        pole.setBackground(new Color(200,200,200));
        pole.setNazwa("owca");
    }
}
