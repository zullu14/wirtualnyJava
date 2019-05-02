package wirtualnySwiat.grafika;

import wirtualnySwiat.Swiat;

import javax.swing.*;
import java.awt.*;

public class OknoNaSwiat extends JFrame {

    private Swiat swiat;
    private Plansza plansza;
    private JLabel komunikaty;
    private final int oknoWys, oknoSzer;

    public OknoNaSwiat(Swiat swiat) {
        super("Michał Baranowski 165463");
        this.swiat = swiat;
        this.oknoWys = swiat.getRows();
        this.oknoSzer = swiat.getCols();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(oknoSzer*100 + 50, oknoWys*60 + 50);
        setLayout(new BorderLayout());

        plansza = new Plansza(oknoSzer, oknoWys, swiat);
        add(plansza);
        plansza.setPreferredSize(new Dimension(oknoSzer*100, oknoWys*50));

        komunikaty = new JLabel("Witaj w Wirtualnym Swiecie. ");
        add(komunikaty);
        komunikaty.setPreferredSize(new Dimension(oknoSzer*100, oknoWys*10));

        setVisible(true);

    }
}
