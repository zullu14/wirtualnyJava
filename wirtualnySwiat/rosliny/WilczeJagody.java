package wirtualnySwiat.rosliny;

import wirtualnySwiat.*;
import wirtualnySwiat.grafika.Pole;

import java.awt.*;

public class WilczeJagody extends Roslina {

    public WilczeJagody(Swiat srodowisko, Wspolrzedne miejsce) {
        super(srodowisko, miejsce);
        this.sila = 99;
        this.inicjatywa = 0;
        this.typ = Rodzaj.jagody;
    }

    public WilczeJagody(Swiat srodowisko, Wspolrzedne miejsce, int sila, int wiek) {
        super(srodowisko, miejsce, sila, wiek);
        this.inicjatywa = 0;
        this.typ = Rodzaj.jagody;
    }

    @Override
    public void kolizja(Organizm drugi) {
        drugi.setCzyZyje(false);		// po zjedzeniu wilczych jagód każde zwierzę umiera
        swiat.dodajKomunikat(drugi.getTyp().toString() + " umiera od zjedzenia wilczych jagod na pozycji " + polozenie.x + "," + polozenie.y);

    }

    @Override
    public void rysowanie(Pole pole) {
        pole.setBackground(new Color(151, 46, 185));
        pole.setNazwa("jagody");
    }
}
