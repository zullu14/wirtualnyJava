package wirtualnySwiat.rosliny;

import wirtualnySwiat.Rodzaj;
import wirtualnySwiat.Roslina;
import wirtualnySwiat.Swiat;
import wirtualnySwiat.Wspolrzedne;
import wirtualnySwiat.grafika.Pole;

import java.awt.*;

public class Mlecz extends Roslina {

    public Mlecz(Swiat srodowisko, Wspolrzedne miejsce) {
        super(srodowisko, miejsce);
        this.sila = 0;
        this.inicjatywa = 0;
        this.typ = Rodzaj.mlecz;
    }

    @Override
    public void akcja() {
        super.akcja();
        super.akcja();
        super.akcja();
    }

    @Override
    public void rysowanie(Pole pole) {
        pole.setBackground(new Color(255, 242, 0));
        pole.setNazwa("mlecz");
    }
}
