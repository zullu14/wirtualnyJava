package wirtualnySwiat;

import wirtualnySwiat.grafika.Pole;

import java.util.Random;

public abstract class Organizm {

    protected int sila, inicjatywa, wiek;
    protected boolean czyZyje, czyRozmnozylSie;
    protected Rodzaj typ;
    protected Wspolrzedne polozenie;
    protected Swiat swiat;
    protected final Random random = new Random();

    public Organizm(Swiat srodowisko, Wspolrzedne miejsce) {
        this.swiat = srodowisko;
        this.polozenie = miejsce;
        this.czyZyje = true;
        this.czyRozmnozylSie = false;
        this.wiek = swiat.getTura();
    }

    public Organizm(Swiat srodowisko, Wspolrzedne miejsce, int sila, int wiek) {
        this.swiat = srodowisko;
        this.polozenie = miejsce;
        this.sila = sila;
        this.wiek = wiek;
        this.czyZyje = true;
        this.czyRozmnozylSie = false;
    }

    public abstract void akcja();
    public abstract void kolizja(Organizm drugi);
    public abstract void rysowanie(Pole pole);

    public int getSila() { return sila; }
    public void zwiekszSileO(int str) { sila += str; }
    public int getInicjatywa() { return inicjatywa; }
    public int getWiek() { return wiek; }
    public Wspolrzedne getPolozenie() { return polozenie; }
    public void setPolozenie(Wspolrzedne nowePolozenie) { polozenie = nowePolozenie; }
    public Rodzaj getTyp() { return typ; }
    public boolean getCzyZyje() { return czyZyje; }
    public void setCzyZyje(boolean stan) { czyZyje = stan; }
    public boolean getCzyRozmnozylSie() { return czyRozmnozylSie; }
    void setCzyRozmnozylSie(boolean stan) { czyRozmnozylSie = stan; }

    public boolean czyOdbilAtak(Organizm atakujacy) { return false; } //do nadpisania
    public boolean czyUciekl(Organizm atakujacy) { return false; } //do nadpisania

    protected Wspolrzedne losujPolozenie()
    {
        int xNew = polozenie.x, yNew = polozenie.y;
        int r = random.nextInt(8);				// 8 pól na które można się przesunąć
        switch (r)
        {
            case 0:
                if (polozenie.x > 0 && polozenie.y > 0) {
                    xNew = polozenie.x - 1;
                    yNew = polozenie.y - 1;
                    break;
                }
            case 1:
                if (polozenie.x > 0) {
                    xNew = polozenie.x - 1;
                    yNew = polozenie.y;
                    break;
                }
            case 2:
                if (polozenie.x > 0 && polozenie.y < swiat.getCols() - 1) {
                    xNew = polozenie.x - 1;
                    yNew = polozenie.y + 1;
                    break;
                }
            case 3:
                if (polozenie.y > 0) {
                    xNew = polozenie.x;
                    yNew = polozenie.y - 1;
                    break;
                }
            case 4:
                if (polozenie.y < swiat.getCols() - 1) {
                    xNew = polozenie.x;
                    yNew = polozenie.y + 1;
                    break;
                }
            case 5:
                if (polozenie.x < swiat.getRows() - 1 && polozenie.y > 0) {
                    xNew = polozenie.x + 1;
                    yNew = polozenie.y - 1;
                    break;
                }
            case 6:
                if (polozenie.x < swiat.getRows() - 1) {
                    xNew = polozenie.x + 1;
                    yNew = polozenie.y;
                    break;
                }
            case 7:
                if (polozenie.x < swiat.getRows() - 1 && polozenie.y < swiat.getCols() - 1) {
                    xNew = polozenie.x + 1;
                    yNew = polozenie.y + 1;
                    break;
                }
            default:
                if (polozenie.x > 0 && polozenie.y > 0) {
                    xNew = polozenie.x - 1;
                    yNew = polozenie.y - 1;
                    break;
                }
                break;
        }  // end of switch
        return new Wspolrzedne(xNew, yNew);
    }


}
