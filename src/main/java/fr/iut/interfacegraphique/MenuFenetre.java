package fr.iut.interfacegraphique;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MenuFenetre extends JMenuBar {

    private JMenu partie = new JMenu("Partie");
    private JMenuItem nouvellePartie = new JMenuItem("Nouvelle Partie");
    private JMenuItem chargerPartie = new JMenuItem("Charger une Partie...");
    private JMenuItem enregistrerPartie = new JMenuItem("Enregistrer");
    private JMenuItem enregistrerPartieSous = new JMenuItem("Enregistrer sous...");

    private JMenu informations = new JMenu("Informations");
    private JMenuItem regles = new JMenuItem("Règles du jeu");

    public MenuFenetre() {
        nouvellePartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        nouvellePartie.addActionListener(null);
        partie.add(nouvellePartie);

        chargerPartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        chargerPartie.addActionListener(null);
        partie.add(chargerPartie);

        enregistrerPartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        enregistrerPartie.addActionListener(null);
        enregistrerPartie.setEnabled(false);
        partie.add(enregistrerPartie);

        enregistrerPartieSous.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        enregistrerPartieSous.addActionListener(null);
        enregistrerPartieSous.setEnabled(false);
        partie.add(enregistrerPartieSous);

        regles.addActionListener(null);
        informations.add(regles);

        this.add(partie);
        this.add(informations);
    }

    public void chargerPartie() {

    }


}
