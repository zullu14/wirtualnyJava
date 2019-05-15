package wirtualnySwiat.grafika;

import wirtualnySwiat.Organizm;
import wirtualnySwiat.Swiat;
import wirtualnySwiat.zwierzeta.Czlowiek;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OknoNaSwiat extends JFrame {

    private Swiat swiat;
    private Plansza plansza;
    private JLabel komunikaty;
    private JLabel panelSterowania;
    private final int oknoWys, oknoSzer;

    public OknoNaSwiat(Swiat swiat) {
        super("Michał Baranowski 165463");
        this.swiat = swiat;
        Dimension wymiaryEkranu = Toolkit.getDefaultToolkit().getScreenSize();
        this.oknoWys = (int) wymiaryEkranu.getHeight();
        this.oknoSzer = (int) wymiaryEkranu.getWidth();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(oknoSzer, oknoWys);
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));

        plansza = new Plansza(swiat.getCols(), swiat.getRows(), swiat);
        plansza.setPreferredSize(new Dimension(oknoSzer, oknoWys/2));
        plansza.setFocusable(true);
        add(plansza);


        komunikaty = new JLabel("<html> Witaj w Wirtualnym Świecie. </html>");
        komunikaty.setFont(new Font("Calibri", Font.BOLD, 30));
        komunikaty.setBorder(new EmptyBorder(0, 100, 0, 0));
        komunikaty.setPreferredSize(new Dimension(oknoSzer/2 - 100, oknoWys/4));
        komunikaty.setVerticalAlignment(JLabel.TOP);
        komunikaty.setVerticalTextPosition(JLabel.TOP);
        komunikaty.setFocusable(false);
        add(komunikaty);

        panelSterowania = new JLabel("<html>Lista dostępnych akcji: </html>");
        panelSterowania.setFont(new Font("Calibri", Font.BOLD, 30));
        panelSterowania.setBorder(new EmptyBorder(0, 100, 0, 0));
        panelSterowania.setPreferredSize(new Dimension(oknoSzer/2 - 100, oknoWys/4));
        panelSterowania.setVerticalAlignment(JLabel.TOP);
        panelSterowania.setVerticalTextPosition(JLabel.TOP);
        panelSterowania.setFocusable(false);
        add(panelSterowania);

        JButton nowaTura = new JButton("Nowa Tura");
        nowaTura.setFont(new Font("Calibri", Font.BOLD, 40));
        nowaTura.setBackground(new Color(199, 186, 154));
        nowaTura.setPreferredSize(new Dimension(oknoSzer/10, oknoWys/20));
        nowaTura.setVerticalAlignment(JLabel.CENTER);
        nowaTura.setVerticalTextPosition(JLabel.CENTER);
        nowaTura.addActionListener(actionEvent -> swiat.wykonajTure());
        nowaTura.setFocusable(false);
        add(nowaTura);

        JButton zapiszGre = new JButton("Zapisz Grę");
        zapiszGre.setFont(new Font("Calibri", Font.BOLD, 40));
        zapiszGre.setBackground(new Color(199, 186, 154));
        zapiszGre.setPreferredSize(new Dimension(oknoSzer/10, oknoWys/20));
        zapiszGre.setVerticalAlignment(JLabel.CENTER);
        zapiszGre.setVerticalTextPosition(JLabel.CENTER);
        zapiszGre.addActionListener(actionEvent -> {
            OknoDialogowe oknoZapisu = new OknoDialogowe(this,"Zapis gry");
            String nazwaPliku = oknoZapisu.dialoguj();
            if(nazwaPliku != null && !nazwaPliku.equals("")) {
                swiat.zapiszSwiat(nazwaPliku);
                ustawPanelSterowania("Stan gry zapisany do pliku "+nazwaPliku+".txt. ");
            } else ustawPanelSterowania("Stan gry nie zapisany - nie podano nazwy zapisu.");
        });
        zapiszGre.setFocusable(false);
        add(zapiszGre);

        JButton wczytajGre = new JButton("Wczytaj Grę");
        wczytajGre.setFont(new Font("Calibri", Font.BOLD, 40));
        wczytajGre.setBackground(new Color(199, 186, 154));
        wczytajGre.setPreferredSize(new Dimension(oknoSzer/10, oknoWys/20));
        wczytajGre.setVerticalAlignment(JLabel.CENTER);
        wczytajGre.setVerticalTextPosition(JLabel.CENTER);
        wczytajGre.addActionListener(actionEvent -> {
            OknoDialogowe oknoWczytywania = new OknoDialogowe(this,"Wczytywanie gry");
            String nazwaPliku = oknoWczytywania.dialoguj();
            if(nazwaPliku != null && !nazwaPliku.equals("")) {
                if(!swiat.wczytajSwiat(nazwaPliku)) ustawPanelSterowania("Nie udało się wczytać stanu gry z pliku "+nazwaPliku+".txt.");
            } else ustawPanelSterowania("Stan gry nie wczytany - nie podano nazwy zapisu.");
        });
        wczytajGre.setFocusable(false);
        add(wczytajGre);

        JButton zakoncz = new JButton("Zakończ Grę");
        zakoncz.setFont(new Font("Calibri", Font.BOLD, 40));
        zakoncz.setBackground(new Color(199, 186, 154));
        zakoncz.setPreferredSize(new Dimension(oknoSzer/10, oknoWys/20));
        zakoncz.setVerticalAlignment(JLabel.CENTER);
        zakoncz.setVerticalTextPosition(JLabel.CENTER);
        zakoncz.addActionListener(actionEvent -> {
            this.dispose();
            System.exit(0);
        });
        zakoncz.setFocusable(false);
        add(zakoncz);

        setVisible(false);

    }

    public void rysujOrganizmy() { plansza.rysujOrganizmy(); }

    public void resetPlanszy() {
        remove(plansza);
        plansza = new Plansza(swiat.getCols(), swiat.getRows(), swiat);
        plansza.setPreferredSize(new Dimension(oknoSzer, oknoWys/2));
        plansza.setFocusable(true);
        add(plansza, 0);
        validate();
        repaint();
    }

    public void ustawKomunikaty() {
        StringBuilder calyTekst = new StringBuilder();
        calyTekst.append("<html>");
        for (String komunikat : swiat.getKomunikaty()) {
            calyTekst.append(komunikat);
            calyTekst.append("<br>");
        }
        calyTekst.append("</html>");
        komunikaty.setText(calyTekst.toString());
    }

    public void ustawPanelSterowania(String opcja) {
        StringBuilder calyTekst = new StringBuilder();
        calyTekst.append("<html>");
        calyTekst.append("Lista dostępnych akcji: <br>");

        boolean czyCzlowiekZyje = false;
        int licznik = -1;

        // sprawdzenie czy Czlowiek zyje i spisanie licznika specjalnej umiejętności
        for (Organizm org : swiat.getOrganizmy()) {
            if (org instanceof Czlowiek) {
                czyCzlowiekZyje = true;
                licznik = ((Czlowiek) org).getLicznikTarczy();
                break;
            }
        }
        if (czyCzlowiekZyje) {
            calyTekst.append("* klawisze strzałek (prawo, lewo, góra, dół) - ruch Człowieka <br>");
            if (licznik > 5) calyTekst.append("* Tarcza Alzura jest aktywna <br>");
            else if (licznik > 0) {
                calyTekst.append("Tarcza Alzura bedzie aktywna za ");
                calyTekst.append(licznik);
                switch (licznik) {
                    case 5:
                        calyTekst.append(" tur <br>");
                        break;
                    case 4:
                    case 3:
                    case 2:
                        calyTekst.append(" tury <br>");
                        break;
                    case 1:
                        calyTekst.append(" turę <br>");
                        break;
                }
            } else if (licznik == 0) calyTekst.append("* SPACJA - aktywacja Tarczy Alzura <br>");
        }
        else calyTekst.append("* klawisze strzalek /spacja - kolejna tura <br>");
        // w obu wypadkach jeszcze opcje:
        calyTekst.append("* dedykowane przyciski - kolejna tura, zapis, odczyt i zakończenie <br>");
        calyTekst.append("* kliknięcie myszką w puste pole - dodanie nowego organizmu z listy <br>");
        if (opcja != null) calyTekst.append(opcja);
        calyTekst.append("</html>");
        panelSterowania.setText(calyTekst.toString());
    }
}
