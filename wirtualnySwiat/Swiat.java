package wirtualnySwiat;

import wirtualnySwiat.grafika.OknoNaSwiat;
import wirtualnySwiat.rosliny.*;
import wirtualnySwiat.zwierzeta.*;

import java.util.ArrayList;
import java.util.Random;

public class Swiat {

    private ArrayList<Organizm> organizmy;
    private ArrayList<Organizm> noweOrganizmy;
    private ArrayList<String> komunikaty;
    private int tura, rows, cols;
    private Akcje kierunek;
    private boolean koniecGry;
    private OknoNaSwiat okno;
    protected final Random random = new Random();

    public Swiat(int rows, int cols) {
        super();
        this.rows = rows;
        this.cols = cols;
        this.tura = 0;
        this.koniecGry = false;
        this.organizmy = new ArrayList<>();
        this.noweOrganizmy = new ArrayList<>();
        this.komunikaty = new ArrayList<>();
        this.kierunek = Akcje.stoj;
        this.okno = new OknoNaSwiat(this);
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public int getTura() { return tura; }
    public Akcje getKierunek() { return kierunek; }
    public void setKierunek(Akcje kier) { this.kierunek = kier; }
    public Iterable<Organizm> getOrganizmy() { return organizmy; }
    public Iterable<Organizm> getNoweOrganizmy() { return noweOrganizmy; }
    public Iterable<String> getKomunikaty() { return komunikaty; }

    public void wykonajTure() {

        organizmy.sort(Swiat::porownajOrganizmy);
        // wyzeruj flagę czyRozmnozylSie
        for (Organizm org : organizmy) org.setCzyRozmnozylSie(false);

        // po kolei wykonaj akcje
        for (Organizm org : organizmy) {
            if(org.getCzyZyje()) org.akcja();
        }

        // następnie usuń z listy organizmów wszystkie nieżywe
        usunOrganizmy();

        // następnie dodaj do listy organizmów wszystkie nowo narodzone
        dodajNoweOrganizmy();

        // narysuj obecny stan świata wraz z komunikatami
        rysujSwiat();

        // zerowanie pola kierunek, czyli ostatniego klikniętego klawisza
        kierunek = Akcje.stoj;

        // na koniec dolicz kolejną turę
        tura++;
    }

    public void dodajKomunikat(String info) { komunikaty.add(info); }

    void dodajOrganizm(Rodzaj typ, Wspolrzedne miejsce) {

        switch (typ) {
            case wilk:
                noweOrganizmy.add(new Wilk(this, miejsce));
                break;
            case owca:
                noweOrganizmy.add(new Owca(this, miejsce));
                break;
            case zolw:
                noweOrganizmy.add(new Zolw(this, miejsce));
                break;
            case lis:
                noweOrganizmy.add(new Lis(this, miejsce));
                break;
            case antylopa:
                noweOrganizmy.add(new Antylopa(this, miejsce));
                break;
            case trawa:
                noweOrganizmy.add(new Trawa(this, miejsce));
                break;
            case mlecz:
                noweOrganizmy.add(new Mlecz(this, miejsce));
                break;
            case guarana:
                noweOrganizmy.add(new Guarana(this, miejsce));
                break;
            case jagody:
                noweOrganizmy.add(new WilczeJagody(this, miejsce));
                break;
            case barszcz:
                noweOrganizmy.add(new Sosnowski(this, miejsce));
                break;
            case czlowiek:
                noweOrganizmy.add(new Czlowiek(this, miejsce));
                break;
            case cyberowca:
                noweOrganizmy.add(new CyberOwca(this, miejsce));
                break;
        }
    }

    //private
    void stworzSwiat() {

        int populacja = (rows*cols) / 14;			// ok. 7% zaludnienia
        int x, y, r;
        x = random.nextInt(rows);
        y = random.nextInt(cols);
        boolean juzZajete;

        // najpierw stwórz człowieka i cyber-owcę
        dodajOrganizm(Rodzaj.czlowiek, new Wspolrzedne(x,y));
        while (true) {
            x = random.nextInt(rows);
            y = random.nextInt(cols);
            juzZajete = false;
            for (Organizm org : noweOrganizmy) {			// sprawdzenie czy dane miejsce jest wolne
                if (org.getPolozenie().x == x && org.getPolozenie().y == y) {
                juzZajete = true;
                break;
                }
            }
            if (!juzZajete) {
                dodajOrganizm(Rodzaj.cyberowca, new Wspolrzedne(x,y));
                break;										// dopiero jak dodasz cyber-owcę to wyjdź z pętli while
            }
        }

        // następnie stwórz resztę organizmów
        for (int i = 0; i < populacja; ) {
            x = random.nextInt(rows);
            y = random.nextInt(cols);
            juzZajete = false;
            for (Organizm org : noweOrganizmy) {			// sprawdzenie czy dane miejsce jest wolne
                if (org.getPolozenie().x == x && org.getPolozenie().y == y) {
                    juzZajete = true;
                    break;
                }
            }
            if (!juzZajete) {							// jezeli miejsce wolne, stworz nowy organizm w tym miejscu
                r = random.nextInt(Rodzaj.values().length - 2);
                dodajOrganizm(Rodzaj.values()[r], new Wspolrzedne(x,y));
                i++;
            }
        }
        dodajNoweOrganizmy();
        rysujSwiat();

        // obsługa klawiatury - przed pierwszą turą
        //obslugaKlawiatury(); TODO
        tura++;
    }

    private void rysujSwiat() {

        okno.rysujOrganizmy();
        if (tura > 0) okno.ustawKomunikaty();
        komunikaty.clear();
    }

    private void dodajNoweOrganizmy() {

        // ROZMNAŻANIE ZWIERZĄT/ ROZSIEWANIE ROŚLIN: Umieszczanie nowego organizmu na planszy

        boolean juzZajete;
        for (Organizm nowyOrg : noweOrganizmy) {
            juzZajete = false;
            for (Organizm org : organizmy) {							// sprawdzenie, czy dane miejsce jest wolne
                if (org.getPolozenie().equals(nowyOrg.getPolozenie()) ) {
                    juzZajete = true;
                    break;
                }
            }
            if (!juzZajete) {
                if (nowyOrg instanceof Zwierze)
                    dodajKomunikat("Nowe zwierze: " + nowyOrg.getTyp().toString() + " rodzi sie na pozycji " + nowyOrg.getPolozenie().x + "," + nowyOrg.getPolozenie().y + ". ");
                else if (nowyOrg instanceof Roslina)
                    dodajKomunikat("Nowa roslina: " + nowyOrg.getTyp().toString() + " wyrasta na pozycji " + nowyOrg.getPolozenie().x + "," + nowyOrg.getPolozenie().y + ". ");
            }
            // jezeli miejsce zajęte, to nie powstanie tu nowy organizm
            else {
                nowyOrg.setCzyZyje(false);
            }
        }
        noweOrganizmy.removeIf(org -> !org.getCzyZyje());
        organizmy.addAll(noweOrganizmy);
        noweOrganizmy.clear();
    }

    private static int porownajOrganizmy(Organizm org1, Organizm org2) {

        if (org1.getInicjatywa() == org2.getInicjatywa()) {
            return org1.getWiek() - org2.getWiek();
        }
        else return org1.getInicjatywa() - org2.getInicjatywa();
    }

    private void usunOrganizmy() { organizmy.removeIf(org -> !org.getCzyZyje()); }

    /*
    public:
    void rozpocznijGre();

    private:

	void dodajOrganizm(rodzaj typ, wspolrzedne miejsce, int sila, int wiek, int licznik);


	void obslugaKlawiatury();
	int piszMenu();
	void zapiszSwiat();
	bool wczytajSwiat();
	void wyczyscOrganizmy();
     */

}
