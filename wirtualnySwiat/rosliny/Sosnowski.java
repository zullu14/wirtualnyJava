package wirtualnySwiat.rosliny;

import wirtualnySwiat.*;
import wirtualnySwiat.grafika.Pole;
import wirtualnySwiat.zwierzeta.CyberOwca;

import java.awt.*;

public class Sosnowski extends Roslina {

    public Sosnowski(Swiat srodowisko, Wspolrzedne miejsce) {
        super(srodowisko, miejsce);
        this.sila = 10;
        this.inicjatywa = 0;
        this.typ = Rodzaj.barszcz;
    }

    @Override
    public void akcja() {
        for (Organizm org : swiat.getOrganizmy()) {								// sprawdzenie czy wokoło są żywe organizmy i czy nie jest to cyber-owca
            if ((org.getPolozenie().x > polozenie.x-2 && org.getPolozenie().x < polozenie.x+2)
                    && (org.getPolozenie().y > polozenie.y-2 && org.getPolozenie().y < polozenie.y+2)
                    && org.getCzyZyje() && org != this && !(org instanceof CyberOwca)) {
                org.setCzyZyje(false);									// jezeli zajete, wywołaj śmierć
                swiat.dodajKomunikat(org.getTyp().toString() + " zostaje zabity przez barszcz Sosnowskiego na pozycji "
                        + org.getPolozenie().x + "," + org.getPolozenie().y);
            }
        }
    }

    @Override
    public void kolizja(Organizm drugi) {
        if (!(drugi instanceof CyberOwca)) {
            drugi.setCzyZyje(false);		// po zjedzeniu barszczu Sosnowskiego każde zwierzę poza cyber-owcą umiera
            swiat.dodajKomunikat(drugi.getTyp().toString() + " umiera od zjedzenia barszczu Sosnowskiego na pozycji " + polozenie.x + "," + polozenie.y);
        }
        else swiat.dodajKomunikat(drugi.getTyp().toString() + " zjada barszcz Sosnowskiego na pozycji " + polozenie.x + "," + polozenie.y);
    }

    @Override
    public void rysowanie(Pole pole) {
        pole.setBackground(new Color(132, 224, 109));
        pole.setNazwa("barszcz");
    }
}
