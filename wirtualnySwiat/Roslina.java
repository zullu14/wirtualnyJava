package wirtualnySwiat;

import wirtualnySwiat.grafika.Pole;

public abstract class Roslina extends Organizm {

    public Roslina(Swiat srodowisko, Wspolrzedne miejsce) {
        super(srodowisko, miejsce);
    }

    public Roslina(Swiat srodowisko, Wspolrzedne miejsce, int sila, int wiek) { super(srodowisko, miejsce, sila, wiek); }

    @Override
    public void akcja() {
        // ROZPRZESTRZENIANIE ROŚLIN
        if (random.nextInt(20) == 0) {			// gdy będzie 0 spośród 0-19 (5% szans)
            boolean juzZajete = false;
            Wspolrzedne nowePolozenie = losujPolozenie();

            for (Organizm org : swiat.getNoweOrganizmy()) {	// sprawdzenie czy dane miejsce jest wolne na liscie nowych organizmow
                if (nowePolozenie.equals(org.getPolozenie()) ) {
                    juzZajete = true;
                    break;
                }
            }
            if (!juzZajete) {						// jezeli miejsce wolne, stworz nowy organizm w tym miejscu
                swiat.dodajOrganizm(typ, nowePolozenie);
            }
        }
    }

    @Override
    public void kolizja(Organizm drugi) {
        // określa co dzieje się ze zwierzęciem, które zje roślinę. Domyślnie nic.
    }

    public abstract void rysowanie(Pole pole);

}
