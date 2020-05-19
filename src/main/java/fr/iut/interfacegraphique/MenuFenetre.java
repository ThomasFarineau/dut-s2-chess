package fr.iut.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

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
        nouvellePartie.addActionListener(e -> nouvellePartie());
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

    public void nouvellePartie() {
        // Creation du panel pour l'affichage du dialogue
        JPanel panel = new JPanel();
        panel.setSize(new Dimension(430, 100));
        panel.setLayout(null);

        // Ajout du message
        JLabel label = new JLabel("Êtes-vous sûr de vouloir démarrer une nouvelle partie ?");
        label.setBounds(23, 15, 430, 30);
        label.setFont(new Font("Calibri", Font.PLAIN, 16));
        panel.add(label);

        // Changement de la taille du dialogue
        UIManager.put("OptionPane.minimumSize", new Dimension(430, 100));

        // Ajout des options oui et non
        String[] options = { "Oui", "Non"};

        // Affichage du dialogue et recuperation de la réponse sous resp
        int resp = JOptionPane.showOptionDialog(null, panel, "Nouvelle partie", 0,JOptionPane.PLAIN_MESSAGE,null,options,null);

        // Utilisation de la réponse
        if(resp == 0) {
            try {
                Fenetre.getGp().nouvellePartie();
                Fenetre.getJeu().repaint();
            } catch (IOException e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
    }
}
