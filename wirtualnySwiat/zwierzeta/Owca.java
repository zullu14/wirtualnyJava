package wirtualnySwiat.zwierzeta;

import wirtualnySwiat.Rodzaj;
import wirtualnySwiat.Swiat;
import wirtualnySwiat.Wspolrzedne;
import wirtualnySwiat.Zwierze;

public class Owca extends Zwierze {

    public Owca(Swiat srodowisko, Wspolrzedne miejsce) {
        super(srodowisko, miejsce);
        this.sila = 4;
        this.inicjatywa = 4;
        this.typ = Rodzaj.owca;
    }

    @Override
    public void rysowanie() {
        // TODO
    }
}
