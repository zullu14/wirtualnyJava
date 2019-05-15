package wirtualnySwiat.grafika;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OknoDialogowe extends JDialog implements ActionListener {

    private final int oknoWys, oknoSzer;
    private JTextField poleTekstowe;
    private String tekst;

    OknoDialogowe(Frame okno, String tytul) {

        super(okno, tytul, true);
        Dimension wymiaryEkranu = Toolkit.getDefaultToolkit().getScreenSize();
        this.oknoWys = (int) wymiaryEkranu.getHeight()/6;
        this.oknoSzer = (int) wymiaryEkranu.getWidth()/3;

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(oknoSzer, oknoWys);
        setLocation((int) (wymiaryEkranu.getWidth()/2 - oknoSzer/2), (int) (wymiaryEkranu.getHeight()/2 - oknoWys/2));
        setLayout(new FlowLayout());

        JLabel info = new JLabel("Podaj nazwę zapisu: ");
        info.setFont(new Font("Calibri", Font.BOLD, 30));
        info.setPreferredSize(new Dimension(oknoSzer*9/10, oknoWys/6));
        add(info);

        poleTekstowe = new JTextField();
        poleTekstowe.setFont(new Font("Calibri", Font.BOLD, 30));
        poleTekstowe.setPreferredSize(new Dimension(oknoSzer*9/10, oknoWys/6));
        add(poleTekstowe);

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Calibri", Font.BOLD, 30));
        okButton.setPreferredSize(new Dimension(oknoSzer/4, oknoWys/6));
        okButton.addActionListener(this);
        add(okButton);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        tekst = poleTekstowe.getText();
        setVisible(false);
        dispose();
    }

    String dialoguj() {
        setVisible(true);
        return tekst;
    }
}
