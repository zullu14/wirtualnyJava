package wirtualnySwiat.zwierzeta;

import wirtualnySwiat.*;
import wirtualnySwiat.grafika.Pole;
import wirtualnySwiat.rosliny.Sosnowski;

import java.awt.*;

public class CyberOwca extends Zwierze {

    public CyberOwca(Swiat srodowisko, Wspolrzedne miejsce) {
        super(srodowisko, miejsce);
        this.sila = 11;
        this.inicjatywa = 4;
        this.typ = Rodzaj.cyberowca;
    }

    @Override
    public void akcja() {
        // ustalenie kierunku ruchu - tam gdzie pierwszy Barszcz Sosnowskiego
        Wspolrzedne nowePolozenie = new Wspolrzedne(polozenie);
        boolean czyJestBarszcz = false;
        for (Organizm org : swiat.getOrganizmy()) {
            if (org instanceof Sosnowski) {
                if (org.getPolozenie().x > polozenie.x) nowePolozenie.x += 1;
                else if (org.getPolozenie().x < polozenie.x) nowePolozenie.x -= 1;
                if (org.getPolozenie().y > polozenie.y) nowePolozenie.y += 1;
                else if (org.getPolozenie().y < polozenie.y) nowePolozenie.y -= 1;
                czyJestBarszcz = true;
                break;
            }
        }
        if (!czyJestBarszcz)
            nowePolozenie = losujPolozenie();				// jak nie ma barszczu na mapie, to porusza się jak inne owce

        // wykonanie ruchu na nowe polozenie
        boolean juzZajete = false;
        for (Organizm org : swiat.getOrganizmy()) {								// sprawdzenie czy dane miejsce jest wolne i czy to jest zywy organizm
            if (org.getPolozenie().equals(nowePolozenie) && org.getCzyZyje() && org != this) {      // aby nie wywoływać kolizji sam ze sobą
                juzZajete = true;
                kolizja(org);									// jezeli zajete, wywołaj kolizję
                break;
            }
        }
        if (!juzZajete) polozenie = nowePolozenie;                        // jezeli nie zajete, to przenies sie na to pole

    }

    @Override
    public void rysowanie(Pole pole) {
        pole.setBackground(new Color(255, 100, 248));
        pole.setNazwa("cyber-owca");
    }
}
