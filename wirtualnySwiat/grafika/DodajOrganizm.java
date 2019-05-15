package wirtualnySwiat.grafika;

import wirtualnySwiat.Organizm;
import wirtualnySwiat.Swiat;
import wirtualnySwiat.Wspolrzedne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DodajOrganizm extends MouseAdapter {

    private Swiat swiat;
    private Wspolrzedne polozenie;

    DodajOrganizm(Swiat swiat, int x, int y) {
        super();
        this.swiat = swiat;
        this.polozenie = new Wspolrzedne(x,y);
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        // sprawdzenie czy dane miejsce jest wolne
        for (Organizm org : swiat.getOrganizmy())
            if (polozenie.equals(org.getPolozenie())) return;

        JPopupMenu menu = new JPopupMenu();

        ActionListener menuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                swiat.dodajWlasnyOrganizm(event.getActionCommand(), polozenie);
                swiat.rysujSwiat();
            }
        };
        String[] gatunki = {"wilk", "owca", "zolw", "lis", "antylopa", "cyber-owca", "trawa", "mlecz", "guarana", "wilcze jagody", "barszcz Sosnowskiego" };
        JMenuItem item;

        for(String typ : gatunki) {
            item = new JMenuItem(typ);
            item.setFont(new Font("Calibri", Font.BOLD, 32));
            menu.add(item);
            item.addActionListener(menuListener);
        }

        menu.show(event.getComponent(),event.getX(),event.getY());
    }
}
