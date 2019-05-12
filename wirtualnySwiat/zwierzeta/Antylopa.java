package wirtualnySwiat.zwierzeta;

import wirtualnySwiat.*;
import wirtualnySwiat.grafika.Pole;

import java.awt.*;

public class Antylopa extends Zwierze {

    public Antylopa(Swiat srodowisko, Wspolrzedne miejsce) {
        super(srodowisko, miejsce);
        this.sila = 4;
        this.inicjatywa = 4;
        this.typ = Rodzaj.antylopa;
    }

    public Antylopa(Swiat srodowisko, Wspolrzedne miejsce, int sila, int wiek) {
        super(srodowisko, miejsce, sila, wiek);
        this.inicjatywa = 4;
        this.typ = Rodzaj.antylopa;
    }

    @Override
    public void akcja() {
        super.akcja();
        super.akcja();
    }

    @Override
    public void kolizja(Organizm drugi) {
        if (typ != drugi.getTyp() && drugi instanceof Zwierze) {	// jeżeli są różnego gatunku i drugi to zwierzę, to spróbuj uciec
            polozenie = drugi.getPolozenie();			            // z tego samego miejsca albo ucieknie, albo jedno zginie w walce
            if (czyUciekl(drugi)) return;							// jezeli udało się uciec, to nie wykona się kolizja Zwierząt
        }
        // jezeli są tego samego gatunku lub drugi jest rośliną, lub nie udało się uciec, wykonaj kolizję Zwierząt
        super.kolizja(drugi);
    }

    @Override
    public boolean czyUciekl(Organizm atakujacy) {
        if(random.nextInt(2) == 0) {			// gdy będzie 0 spośród 0, 1 (50% szans)
            Wspolrzedne nowePolozenie = losujPolozenie();

            if (!(nowePolozenie.equals(polozenie))) {		// jezeli wylosowano nowe polozenie, to spróbuj uciec
                // sprawdzenie czy dane miejsce jest wolne i czy to jest zywy organizm
                for (Organizm org : swiat.getOrganizmy()) {
                    if (org.getPolozenie().equals(nowePolozenie) && org.getCzyZyje())
                        return false;									// jezeli zajete, to nie udało się uciec; wyjście z metody
                }
                // jezeli nie zajete, to przenies sie na to pole, ucieczka udana
                swiat.dodajKomunikat("antylopa ucieka od walki z " + atakujacy.getTyp().toString() + " na pozycji " + polozenie.x + "," + polozenie.y + ". ");
                polozenie = nowePolozenie;
                return true;
            }
            else return false;					// jezeli wylosowane polozenie to stare polozenie, to nie udało się uciec
        }
        else return false;
    }

    @Override
    public void rysowanie(Pole pole) {
        pole.setBackground(new Color(228, 178, 40));
        pole.setNazwa("antylopa");
    }
}
