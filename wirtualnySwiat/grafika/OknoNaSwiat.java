package wirtualnySwiat.grafika;

import wirtualnySwiat.Swiat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OknoNaSwiat extends JFrame {

    private Swiat swiat;
    private Plansza plansza;
    private JLabel komunikaty;
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
        setLayout(new FlowLayout());

        plansza = new Plansza(swiat.getCols(), swiat.getRows(), swiat);
        plansza.setPreferredSize(new Dimension(oknoSzer, oknoWys/2));
        add(plansza);

        komunikaty = new JLabel("<html>" +  "Witaj w Wirtualnym Swiecie. " + "<br>" + "Second line" + "<br>" + "Second line"+ "</html>");
        komunikaty.setFont(new Font("Calibri", Font.BOLD, 24));
        komunikaty.setBorder(new EmptyBorder(0, 200, 0, 0));
        komunikaty.setPreferredSize(new Dimension(oknoSzer, oknoWys/4));
        komunikaty.setVerticalAlignment(JLabel.TOP);
        komunikaty.setVerticalTextPosition(JLabel.TOP);
        add(komunikaty);

        setVisible(true);

    }
}
