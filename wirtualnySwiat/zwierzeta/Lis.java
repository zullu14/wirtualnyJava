package wirtualnySwiat.zwierzeta;

import wirtualnySwiat.*;
import wirtualnySwiat.grafika.Pole;

import java.awt.*;

public class Lis extends Zwierze {

    public Lis(Swiat srodowisko, Wspolrzedne miejsce) {
        super(srodowisko, miejsce);
        this.sila = 3;
        this.inicjatywa = 7;
        this.typ = Rodzaj.lis;
    }

    public Lis(Swiat srodowisko, Wspolrzedne miejsce, int sila, int wiek) {
        super(srodowisko, miejsce, sila, wiek);
        this.inicjatywa = 7;
        this.typ = Rodzaj.lis;
    }

    @Override
    public void akcja() {
        if (!czyRozmnozylSie) {					// jezeli uczestniczyl w rozmnazaniu w tej turze, to nie moze sie ruszac
            boolean juzZajete = false;
            Wspolrzedne nowePolozenie = losujPolozenie();
            if (!(nowePolozenie.equals(polozenie))) {		// aby nie wywoływać kolizji sam ze sobą
                // DOBRY WĘCH: sprawdzenie czy dane miejsce jest wolne, czy to jest zywy organizm ORAZ czy jest silniejszy
                for (Organizm org : swiat.getOrganizmy()) {
                    if (org.getPolozenie().equals(nowePolozenie) && org.getCzyZyje()) {
                        juzZajete = true;
                        if(this.sila >= org.getSila())
                        kolizja(org);									// jezeli Lis jest silniejszy, wywołaj kolizję
                        break;
                    }
                }
                if (!juzZajete)					// jezeli nie zajete, to przenies sie na to pole
                    polozenie = nowePolozenie;
            }
        }
    }

    @Override
    public void rysowanie(Pole pole) {
        pole.setBackground(new Color(255, 160, 16));
        pole.setNazwa("lis");
    }
}
