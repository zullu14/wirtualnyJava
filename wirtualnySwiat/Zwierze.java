package wirtualnySwiat;

import wirtualnySwiat.grafika.Pole;

public abstract class Zwierze extends Organizm {

    public Zwierze(Swiat srodowisko, Wspolrzedne miejsce) {
        super(srodowisko, miejsce);
    }

    @Override
    public void akcja() {
        if (!czyRozmnozylSie) {					// jezeli uczestniczyl w rozmnazaniu w tej turze, to nie moze sie ruszac
            boolean juzZajete = false;
            Wspolrzedne nowePolozenie = losujPolozenie();
            if (!(nowePolozenie.equals(polozenie))) {		// aby nie wywoływać kolizji sam ze sobą
                for (Organizm org : swiat.getOrganizmy()) {								// sprawdzenie czy dane miejsce jest wolne i czy to jest zywy organizm
                    if (nowePolozenie.equals(org.getPolozenie()) && org.getCzyZyje()) {
                        juzZajete = true;
                        kolizja(org);									// jezeli zajete, wywołaj kolizję
                        break;
                    }
                }
                if (!juzZajete)					// jezeli nie zajete, to przenies sie na to pole
                    polozenie = nowePolozenie;
            }
        }
    }

    @Override
    public void kolizja(Organizm drugi) {

        // ROZMNAŻANIE ZWIERZĄT
        if (typ == drugi.getTyp()) {					// jezeli zwierzęta są tego samego typu - rozmnazajcie sie
            czyRozmnozylSie = true;
            drugi.setCzyRozmnozylSie(true);
            boolean juzZajete = false;
            Wspolrzedne nowePolozenie = losujPolozenie();

            for (Organizm org : swiat.getNoweOrganizmy()) {	// sprawdzenie czy dane miejsce jest wolne na liscie nowych organizmow
                if (nowePolozenie.equals(org.getPolozenie())) {
                    juzZajete = true;
                    break;
                }
            }
            if (!juzZajete) {							// jezeli miejsce wolne, stworz nowy organizm w tym miejscu
                swiat.dodajOrganizm(typ, nowePolozenie);
            }
        }
        // ZJADANIE ROŚLINY
        else if (drugi instanceof Roslina) {	// jezeli drugi organizm jest rośliną
            polozenie = drugi.getPolozenie();				// zajmij miejsce rośliny
            drugi.kolizja(this);								// metoda kolizja() danej rośliny określa efekty zjedzenia jej przez zwierzęta
            drugi.setCzyZyje(false);							// ustawienie stanu rośliny na nieżywy
        }
        // WALKA POMIĘDZY RÓŻNYMI ZWIERZĘTAMI
        else if (!drugi.czyOdbilAtak(this) ) {					// jak nie odbił ataku to walka
            polozenie = drugi.getPolozenie();				// pierwszy zajmuje miejsce drugiego

            if (drugi.czyUciekl(this))							// jezeli tamten uciekł, to tylko zajmij jego miejsce i wyjdź
                return;
            else if (this.sila >= drugi.getSila()) {			// pierwszy zabija drugi organizm
                swiat.dodajKomunikat(this.getTyp().toString() + " zabija " + drugi.getTyp().toString() + " na pozycji " + polozenie.x + "," + polozenie.y + ". ");
                drugi.setCzyZyje(false);						// ustawienie stanu drugiego organizmu na nieżywy
            }
		else {												// na odwort: drugi zabija pierwszego
                swiat.dodajKomunikat(drugi.getTyp().toString() + " zabija " + this.getTyp().toString() + " na pozycji " + polozenie.x + "," + polozenie.y + ". ");
                this.setCzyZyje(false);						// ustawienie stanu tego organizmu na nieżywy
            }
        }
    }

    public abstract void rysowanie(Pole pole);

}
