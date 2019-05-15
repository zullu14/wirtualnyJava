package wirtualnySwiat.grafika;

import wirtualnySwiat.Swiat;
import wirtualnySwiat.ZleWymiarySwiataException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OknoStart extends JFrame implements ActionListener {

    private JSpinner wys, szer;
    private final int oknoWys, oknoSzer;

    public OknoStart() {

        super("Michał Baranowski 165463");

        Dimension wymiaryEkranu = Toolkit.getDefaultToolkit().getScreenSize();
        this.oknoWys = (int) wymiaryEkranu.getHeight()/6;
        this.oknoSzer = (int) wymiaryEkranu.getWidth()/3;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(oknoSzer, oknoWys);
        setLocation((int) (wymiaryEkranu.getWidth()/2 - oknoSzer/2), (int) (wymiaryEkranu.getHeight()/2 - oknoWys/2));
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

        JLabel info = new JLabel("Podaj wymiary świata, który chcesz stworzyć.");
        info.setFont(new Font("Calibri", Font.BOLD, 30));
        info.setPreferredSize(new Dimension(oknoSzer*9/10, oknoWys/6));
        add(info);

        info = new JLabel("Szerokość: ");
        info.setFont(new Font("Calibri", Font.BOLD, 30));
        add(info);

        szer = new JSpinner();
        szer.setFont(new Font("Calibri", Font.BOLD, 30));
        szer.setValue(20);
        szer.setPreferredSize(new Dimension(oknoSzer/4,oknoWys/6));
        add(szer);

        info = new JLabel("Wysokość: ");
        info.setFont(new Font("Calibri", Font.BOLD, 30));
        add(info);

        wys = new JSpinner();
        wys.setFont(new Font("Calibri", Font.BOLD, 30));
        wys.setValue(20);
        wys.setPreferredSize(new Dimension(oknoSzer/4,oknoWys/6));
        add(wys);

        JButton zatwierdz = new JButton("OK");
        zatwierdz.setFont(new Font("Calibri", Font.BOLD, 30));
        zatwierdz.setPreferredSize(new Dimension(oknoSzer/4,oknoWys/6));
        zatwierdz.addActionListener(this);
        add(zatwierdz);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int cols = (int) szer.getValue();
        int rows = (int) wys.getValue();

        try {
            new Swiat(rows, cols);
        } catch (ZleWymiarySwiataException ex) {
            System.out.println("Nie udało się utworzyć świata o zadanych wymiarach!");
        }
        dispose();
    }
}
