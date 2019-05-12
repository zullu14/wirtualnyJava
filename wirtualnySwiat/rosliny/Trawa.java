package wirtualnySwiat.rosliny;

import wirtualnySwiat.Rodzaj;
import wirtualnySwiat.Roslina;
import wirtualnySwiat.Swiat;
import wirtualnySwiat.Wspolrzedne;
import wirtualnySwiat.grafika.Pole;

import java.awt.*;

public class Trawa extends Roslina {

    public Trawa(Swiat srodowisko, Wspolrzedne miejsce) {
        super(srodowisko, miejsce);
        this.sila = 0;
        this.inicjatywa = 0;
        this.typ = Rodzaj.trawa;
    }

    public Trawa(Swiat srodowisko, Wspolrzedne miejsce, int sila, int wiek) {
        super(srodowisko, miejsce, sila, wiek);
        this.inicjatywa = 0;
        this.typ = Rodzaj.trawa;
    }

    @Override
    public void rysowanie(Pole pole) {
        pole.setBackground(new Color(0, 150, 20));
        pole.setNazwa("trawa");
    }
}
