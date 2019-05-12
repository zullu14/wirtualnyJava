package wirtualnySwiat.zwierzeta;

import wirtualnySwiat.*;
import wirtualnySwiat.grafika.Pole;

import java.awt.*;

public class Zolw extends Zwierze {

    public Zolw(Swiat srodowisko, Wspolrzedne miejsce) {
        super(srodowisko, miejsce);
        this.sila = 2;
        this.inicjatywa = 1;
        this.typ = Rodzaj.zolw;
    }

    public Zolw(Swiat srodowisko, Wspolrzedne miejsce, int sila, int wiek) {
        super(srodowisko, miejsce, sila, wiek);
        this.inicjatywa = 1;
        this.typ = Rodzaj.zolw;
    }

    @Override
    public void akcja() {
        if(random.nextInt(4) == 0) super.akcja();
    }

    @Override
    public void rysowanie(Pole pole) {
        pole.setBackground(new Color(185, 140, 17));
        pole.setNazwa("zolw");
    }

    @Override
    public boolean czyOdbilAtak(Organizm atakujacy) {
        if (atakujacy.getSila() < 5) {
            swiat.dodajKomunikat("zolw odbil atak " + atakujacy.getTyp().toString() + " na pozycji " + polozenie.x + "," + polozenie.y + ". ");
            return true;
        }
        else return false;
    }
}
