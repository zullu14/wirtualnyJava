package wirtualnySwiat.zwierzeta;

import wirtualnySwiat.*;
import wirtualnySwiat.grafika.Pole;

import java.awt.*;

public class Czlowiek extends Zwierze {

    private int licznikTarczy;

    public Czlowiek(Swiat srodowisko, Wspolrzedne miejsce) {
        super(srodowisko, miejsce);
        this.sila = 5;
        this.inicjatywa = 4;
        this.typ = Rodzaj.czlowiek;
        this.licznikTarczy = 0;
    }

    public Czlowiek(Swiat srodowisko, Wspolrzedne miejsce, int sila, int wiek, int licznikTarczy) {
        super(srodowisko, miejsce, sila, wiek);
        this.inicjatywa = 4;
        this.typ = Rodzaj.czlowiek;
        this.licznikTarczy = licznikTarczy;
    }

    public int getLicznikTarczy() { return licznikTarczy; }

    @Override
    public void akcja() {
        if (licznikTarczy>0) licznikTarczy--;				// cooldown umiejętności
        boolean juzZajete = false;
        Wspolrzedne nowePolozenie = polozenie;
        Akcje kierunek = swiat.getKierunek();

        switch (kierunek) {
            case prawo:
                if (nowePolozenie.y < swiat.getCols()-1)
                    nowePolozenie.y += 1;
                break;
            case lewo:
                if (nowePolozenie.y > 0)
                    nowePolozenie.y -= 1;
                break;
            case gora:
                if (nowePolozenie.x > 0)
                    nowePolozenie.x -= 1;
                break;
            case dol:
                if (nowePolozenie.x < swiat.getRows()-1)
                    nowePolozenie.x += 1;
                break;
            case spacja:				// obsługa specjalnej umiejętności
                if (licznikTarczy == 0) {
                    swiat.dodajKomunikat("Czlowiek aktywuje Tarcze Alzura na czas 5 tur. ");
                    licznikTarczy = 10;
                }
                break;
            default:
                break;
        }
        // obsluga na wypadek kolizji
        for (Organizm org : swiat.getOrganizmy()) {								// sprawdzenie czy dane miejsce jest wolne i czy to jest zywy organizm
            if (org.getPolozenie().equals(nowePolozenie) && org.getCzyZyje() && org != this) {    // aby nie wywoływać kolizji sam ze sobą
                juzZajete = true;
                kolizja(org);									// jezeli zajete, wywołaj kolizję
                break;
            }
        }
        if (!juzZajete)					// jezeli nie zajete, to przenies sie na to pole
            polozenie = nowePolozenie;
    }

    @Override
    public boolean czyOdbilAtak(Organizm atakujacy) {
        if (licznikTarczy > 5) {
            swiat.dodajKomunikat("Czlowiek odbil atak " + atakujacy.getTyp().toString() + " na pozycji " + polozenie.x + "," + polozenie.y + ". ");
            return true;
        }
        else return false;
    }

    @Override
    public void rysowanie(Pole pole) {
        pole.setBackground(new Color(0, 222, 168));
        pole.setNazwa("Czlowiek");
    }
}
