package wirtualnySwiat.grafika;

import wirtualnySwiat.Akcje;
import wirtualnySwiat.Organizm;
import wirtualnySwiat.Swiat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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

        plansza.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent ev) {
                plansza.requestFocus();
            }
        });

        add(plansza);


        komunikaty = new JLabel("<html> Witaj w Wirtualnym Swiecie. </html>");
        komunikaty.setFont(new Font("Calibri", Font.BOLD, 30));
        komunikaty.setBorder(new EmptyBorder(0, 100, 0, 0));
        komunikaty.setPreferredSize(new Dimension(oknoSzer/2 - 100, oknoWys/4));
        komunikaty.setVerticalAlignment(JLabel.TOP);
        komunikaty.setVerticalTextPosition(JLabel.TOP);
        add(komunikaty);

        panelSterowania = new JLabel("<html>Lista dostępnych akcji: </html>");
        panelSterowania.setFont(new Font("Calibri", Font.BOLD, 30));
        panelSterowania.setBorder(new EmptyBorder(0, 100, 0, 0));
        panelSterowania.setPreferredSize(new Dimension(oknoSzer/2 - 100, oknoWys/4));
        panelSterowania.setVerticalAlignment(JLabel.TOP);
        panelSterowania.setVerticalTextPosition(JLabel.TOP);
        add(panelSterowania);

        JButton nowaTura = new JButton("Nowa Tura");
        nowaTura.setFont(new Font("Calibri", Font.BOLD, 40));
        nowaTura.setBackground(new Color(199, 186, 154));
        nowaTura.setPreferredSize(new Dimension(oknoSzer/10, oknoWys/20));
        nowaTura.setVerticalAlignment(JLabel.CENTER);
        nowaTura.setVerticalTextPosition(JLabel.CENTER);
        nowaTura.addActionListener(actionEvent -> swiat.wykonajTure());
        add(nowaTura);

        JButton zapiszGre = new JButton("Zapisz Grę");
        zapiszGre.setFont(new Font("Calibri", Font.BOLD, 40));
        zapiszGre.setBackground(new Color(199, 186, 154));
        zapiszGre.setPreferredSize(new Dimension(oknoSzer/10, oknoWys/20));
        zapiszGre.setVerticalAlignment(JLabel.CENTER);
        zapiszGre.setVerticalTextPosition(JLabel.CENTER);
        zapiszGre.addActionListener(actionEvent -> {
            swiat.zapiszSwiat("zapis");
            ustawPanelSterowania("Stan gry zapisany do pliku zapis.txt. ");
        });    //TODO: dodaj mozliwosc nazwania pliku docelowego
        add(zapiszGre);

        JButton wczytajGre = new JButton("Wczytaj Grę");
        wczytajGre.setFont(new Font("Calibri", Font.BOLD, 40));
        wczytajGre.setBackground(new Color(199, 186, 154));
        wczytajGre.setPreferredSize(new Dimension(oknoSzer/10, oknoWys/20));
        wczytajGre.setVerticalAlignment(JLabel.CENTER);
        wczytajGre.setVerticalTextPosition(JLabel.CENTER);
        wczytajGre.addActionListener(actionEvent -> {
            if(swiat.wczytajSwiat("zapis")) ustawPanelSterowania("Wczytany ostatni zapis z pliku zapis.txt. ");
            else ustawPanelSterowania("Nie udało się wczytać stanu gry z pliku zapis.txt.");
        });    //TODO: dodaj mozliwosc nazwania pliku docelowego
        add(wczytajGre);

        setVisible(true);

    }

    public void rysujOrganizmy() { plansza.rysujOrganizmy(); }

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

    public void ustawPanelSterowania(String opcja) {        //TODO
        StringBuilder calyTekst = new StringBuilder();
        calyTekst.append("<html>");
        calyTekst.append("Lista dostępnych akcji: <br>");
        /*for (String komunikat : swiat.getKomunikaty()) {
            calyTekst.append(komunikat);
            calyTekst.append("<br>");
        } */
        calyTekst.append(opcja);
        calyTekst.append("</html>");
        panelSterowania.setText(calyTekst.toString());
    }
}
