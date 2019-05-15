package wirtualnySwiat;

import wirtualnySwiat.grafika.OknoNaSwiat;
import wirtualnySwiat.rosliny.*;
import wirtualnySwiat.zwierzeta.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Swiat {

    private ArrayList<Organizm> organizmy;
    private ArrayList<Organizm> noweOrganizmy;
    private ArrayList<String> komunikaty;
    private int tura, rows, cols;
    private Akcje kierunek;
    private OknoNaSwiat okno;
    private final Random random = new Random();
    private final static String newline = "\n";

    public Swiat(int rows, int cols) throws ZleWymiarySwiataException {

        super();
        if (rows <= 0 || cols <= 0) throw new ZleWymiarySwiataException("Zle wymiary swiata!");
        this.rows = rows;
        this.cols = cols;
        this.tura = 0;
        this.organizmy = new ArrayList<>();
        this.noweOrganizmy = new ArrayList<>();
        this.komunikaty = new ArrayList<>();
        this.kierunek = Akcje.stoj;
        this.okno = new OknoNaSwiat(this);
        stworzSwiat();
        okno.setVisible(true);
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

    public void zapiszSwiat(String plik) {

        File zapisSwiata = new File(plik + ".txt");
        try{
            zapisSwiata.createNewFile();
            BufferedWriter zapis = new BufferedWriter(new FileWriter(plik+".txt", false));      // overwrite
            zapis.append(Integer.toString(rows)); zapis.append(" ");
            zapis.append(Integer.toString(cols)); zapis.append(" ");
            zapis.append(Integer.toString(tura)); zapis.append(" ");
            zapis.append(Integer.toString(organizmy.size())); zapis.append(" ");
            for (Organizm org : organizmy) {
                zapis.append(org.getTyp().toFile()); zapis.append(newline);
                zapis.append(Integer.toString(org.getSila())); zapis.append(" ");
                zapis.append(Integer.toString(org.getWiek())); zapis.append(" ");
                zapis.append(Integer.toString(org.getPolozenie().x)); zapis.append(" ");
                zapis.append(Integer.toString(org.getPolozenie().y)); zapis.append(" ");
                if(org instanceof Czlowiek) {
                    zapis.append(Integer.toString(((Czlowiek) org).getLicznikTarczy()));
                    zapis.append(" ");
                }
            }
            zapis.close();
        } catch (Exception e){
            System.out.println("IO exceptiion "+e);
        }
    }

    public boolean wczytajSwiat(String plik) {

        try (Scanner s = new Scanner(new FileReader(plik+".txt"))) {
            int w, k, ileTur, n;
            w = s.nextInt();
            k = s.nextInt();
            ileTur = s.nextInt();
            n = s.nextInt();
            if (w > 0 && k > 0) {
                organizmy.clear();
                if (rows != w || cols != k) {               // jeżeli wymiary świata się zmieniły, stwórz nowe okno graficzne
                    rows = w;
                    cols = k;
                    okno.resetPlanszy();
                }
                tura = ileTur;

                Rodzaj gatunek;
                int sila, wiek, x, y, licznik = 0;
                for(int i  = 0; i < n; i++) {
                    gatunek = Rodzaj.valueOf(s.nextLine().substring(1));
                    sila = s.nextInt();
                    wiek = s.nextInt();
                    x = s.nextInt();
                    y = s.nextInt();
                    if (gatunek.equals(Rodzaj.czlowiek)) licznik = s.nextInt();
                    dodajOrganizm(gatunek, new Wspolrzedne(x,y), sila, wiek, licznik);
                }
                okno.ustawPanelSterowania("Wczytany ostatni zapis z pliku "+plik+".txt. ");
                rysujSwiat();           // po udanym wczytaniu, narysuj nowy stan gry
                return true;
            }
            else return false;
        } catch (Exception e) {
            System.out.println("IO exceptiion "+e);
            return false;
        }
    }

    public void dodajWlasnyOrganizm(String typ, Wspolrzedne miejsce) {
        switch (typ) {
            case "wilk":
                organizmy.add(new Wilk(this, miejsce));
                break;
            case "owca":
                organizmy.add(new Owca(this, miejsce));
                break;
            case "zolw":
                organizmy.add(new Zolw(this, miejsce));
                break;
            case "lis":
                organizmy.add(new Lis(this, miejsce));
                break;
            case "antylopa":
                organizmy.add(new Antylopa(this, miejsce));
                break;
            case "trawa":
                organizmy.add(new Trawa(this, miejsce));
                break;
            case "mlecz":
                organizmy.add(new Mlecz(this, miejsce));
                break;
            case "guarana":
                organizmy.add(new Guarana(this, miejsce));
                break;
            case "wilcze jagody":
                organizmy.add(new WilczeJagody(this, miejsce));
                break;
            case "barszcz Sosnowskiego":
                organizmy.add(new Sosnowski(this, miejsce));
                break;
            case "cyber-owca":
                organizmy.add(new CyberOwca(this, miejsce));
                break;
            default:
                organizmy.add(new Owca(this, miejsce));
                break;
        }
    }

    public void rysujSwiat() {

        okno.rysujOrganizmy();
        if (tura > 0) okno.ustawKomunikaty();
        okno.ustawPanelSterowania(null);
        komunikaty.clear();
    }

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

    private void dodajOrganizm(Rodzaj typ, Wspolrzedne miejsce, int sila, int wiek, int licznik) {

        switch (typ) {
            case wilk:
                organizmy.add(new Wilk(this, miejsce, sila, wiek));
                break;
            case owca:
                organizmy.add(new Owca(this, miejsce, sila, wiek));
                break;
            case zolw:
                organizmy.add(new Zolw(this, miejsce, sila, wiek));
                break;
            case lis:
                organizmy.add(new Lis(this, miejsce, sila, wiek));
                break;
            case antylopa:
                organizmy.add(new Antylopa(this, miejsce, sila, wiek));
                break;
            case trawa:
                organizmy.add(new Trawa(this, miejsce, sila, wiek));
                break;
            case mlecz:
                organizmy.add(new Mlecz(this, miejsce, sila, wiek));
                break;
            case guarana:
                organizmy.add(new Guarana(this, miejsce, sila, wiek));
                break;
            case jagody:
                organizmy.add(new WilczeJagody(this, miejsce, sila, wiek));
                break;
            case barszcz:
                organizmy.add(new Sosnowski(this, miejsce, sila, wiek));
                break;
            case czlowiek:
                organizmy.add(new Czlowiek(this, miejsce, sila, wiek, licznik));
                break;
            case cyberowca:
                organizmy.add(new CyberOwca(this, miejsce, sila, wiek));
                break;
        }
    }

    private void stworzSwiat() {

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

        tura++;
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

}
