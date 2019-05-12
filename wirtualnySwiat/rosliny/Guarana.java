package wirtualnySwiat.rosliny;

import wirtualnySwiat.*;
import wirtualnySwiat.grafika.Pole;

import java.awt.*;

public class Guarana extends Roslina {

    public Guarana(Swiat srodowisko, Wspolrzedne miejsce) {
        super(srodowisko, miejsce);
        this.sila = 0;
        this.inicjatywa = 0;
        this.typ = Rodzaj.guarana;
    }

    public Guarana(Swiat srodowisko, Wspolrzedne miejsce, int sila, int wiek) {
        super(srodowisko, miejsce, sila, wiek);
        this.inicjatywa = 0;
        this.typ = Rodzaj.guarana;
    }

    @Override
    public void kolizja(Organizm drugi) {
        drugi.zwiekszSileO(3);
        swiat.dodajKomunikat(drugi.getTyp().toString() + " zjada guarane na pozycji " + polozenie.x + "," + polozenie.y + " i zwieksza sile o 3. ");

    }

    @Override
    public void rysowanie(Pole pole) {
        pole.setBackground(new Color(241, 55, 16));
        pole.setNazwa("guarana");
    }
}
