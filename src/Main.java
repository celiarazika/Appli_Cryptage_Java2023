import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

class Main {

    public static void main(String[] args) {

        // ---------- Boutons panel ---------- //
        JPanel boutons = new JPanel();
        JButton decalage = new JButton("Décalage");
        JButton playfaire = new JButton("Playfair");

        boutons.setLayout(new BoxLayout(boutons, BoxLayout.LINE_AXIS));
        boutons.setAlignmentX(Component.LEFT_ALIGNMENT);

        ButtonGroup bp = new ButtonGroup();
        JRadioButton cryptage = new JRadioButton("cryptage"); cryptage.setSelected(true);
        JRadioButton decryptage = new JRadioButton("décryptage");
        bp.add(cryptage);
        bp.add(decryptage);


        boutons.add(decalage);
        boutons.add(playfaire);
        boutons.add(cryptage);
        boutons.add(decryptage);


        // ---------- Clef panel ---------- //

        JPanel clefPan = new JPanel();
        JLabel clefLab = new JLabel("<html><font color='blue'>Clef : </font></html>");
        JTextField clefText = new JTextField();
        GridBagConstraints gc = new GridBagConstraints();

        clefPan.setLayout(new GridBagLayout());

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.ipady = gc.anchor = GridBagConstraints.WEST;

        gc.weighty = 2;

        gc.gridx = 0;
        gc.gridy = 0;

        clefPan.add(clefLab, gc);
        gc.gridx = 1;
        gc.weightx = 30;
        gc.gridwidth = 27;
        clefPan.add(clefText, gc);

        // ---------- Clear text panel ---------- //

        JPanel texteClair = new JPanel();
        JTextArea texteClairTexte = new JTextArea();
        TitledBorder texteClairBorder = new TitledBorder("Texte clair");

        texteClair.setLayout(new BoxLayout(texteClair, BoxLayout.Y_AXIS));

        texteClair.add(texteClairTexte);

        texteClairBorder.setTitleJustification(TitledBorder.CENTER);
        texteClairBorder.setTitleColor(Color.BLUE);
        texteClair.setBorder(texteClairBorder);


        // ---------- Crypted text panel ---------- //

        JPanel texteCrypte = new JPanel();
        JTextArea texteCrypteTexte = new JTextArea();
        TitledBorder texteCrypteBorder = new TitledBorder("Texte crypté");

        texteCrypte.setLayout(new BoxLayout(texteCrypte, BoxLayout.Y_AXIS));

        texteCrypte.add(texteCrypteTexte);

        texteCrypteBorder.setTitleJustification(TitledBorder.CENTER);
        texteCrypteBorder.setTitleColor(Color.BLUE);
        texteCrypte.setBorder(texteCrypteBorder);


        // ---------- Top panel (Buttons + Clef) ---------- //

        JPanel top = new JPanel();

        top.setLayout(new GridLayout(2, 1));

        top.add(boutons);
        top.add(clefPan);

        // ---------- Bottom panel (Clear + Crypted text) ---------- //

        JPanel bottom = new JPanel();

        bottom.setLayout(new GridLayout(2, 1));

        bottom.add(texteClair);
        bottom.add(texteCrypte);

        // ---------- Events ---------- //

        decalage.addActionListener(e -> {
            boolean toCryptage = cryptage.isSelected();
            String s = toCryptage ? texteClairTexte.getText() : texteCrypteTexte.getText();
            String sClef = clefText.getText();

            if(sClef != null) {
                if(toCryptage) {
                    String sCrypte = new Decalage(sClef).cryptage(s);
                    texteCrypteTexte.setText(sCrypte);
                }
                else {
                    String sDecrypte = new Decalage(sClef).decryptage(s);
                    texteClairTexte.setText(sDecrypte);
                }
            }
        });

            playfaire.addActionListener(e -> {
                boolean toCryptage = cryptage.isSelected();
                String s = toCryptage ? texteClairTexte.getText() : texteCrypteTexte.getText();
                String sClef = clefText.getText();

                if(sClef != null) {
                    if(toCryptage) {
                        String sCrypte = new Playfair(sClef).cryptage(s);
                        texteCrypteTexte.setText(sCrypte);
                    }
                    else {
                        String sDecrypte = new Playfair(sClef).decryptage(s);
                        texteClairTexte.setText(sDecrypte);
                    }
                }
            });


        // ---------- Main panel ---------- //

        JFrame frame = new JFrame("Cryptage");
        JPanel pan = new JPanel();

        //frame.setMinimumSize(new Dimension(400, 200));


        pan.setLayout(new BorderLayout());
        pan.add(top, BorderLayout.NORTH);
        pan.add(bottom, BorderLayout.CENTER);


        frame.setContentPane(pan);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(400, 300));
        frame.setSize(new Dimension(480, 320));
    }

    private static void testMethodesCryptage() {
        Decalage d = new Decalage("decalage");
        System.out.println(d);
        System.out.println(d.cryptage("le langage java"));
        System.out.println(d.decryptage("a6 a2g8286 e2n2"));

        System.out.println();

        Playfair pf = new Playfair("playfair");
        System.out.println(pf);
        System.out.println(pf.cryptage("le langage java"));
        System.out.println(pf.decryptage("fb lfkcirg jfta"));
    }
}