package wirtualnySwiat;

import wirtualnySwiat.grafika.OknoNaSwiat;

import java.util.ArrayList;

public class Swiat {

    private ArrayList<Organizm> organizmy;
    private ArrayList<Organizm> noweOrganizmy;
    private ArrayList<String> komunikaty;
    private int tura, rows, cols, kierunek;
    private boolean koniecGry;
    private OknoNaSwiat okno;

    public Swiat(int rows, int cols) {
        super();
        this.rows = rows;
        this.cols = cols;
        this.tura = 0;
        this.koniecGry = false;
        this.organizmy = new ArrayList<>();
        this.noweOrganizmy = new ArrayList<>();
        this.komunikaty = new ArrayList<>();
        this.okno = new OknoNaSwiat(this);
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public int getTura() { return tura; }
    public int getKierunek() { return kierunek; }
    Iterable<Organizm> getOrganizmy() { return organizmy; }
    Iterable<Organizm> getNoweOrganizmy() { return noweOrganizmy; }
    void dodajKomunikat(String info) { komunikaty.add(info); }

}
