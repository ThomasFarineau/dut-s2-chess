package fr.iut.interfacegraphique;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        chargerPartie.addActionListener(e -> chargerPartie());
        partie.add(chargerPartie);

        enregistrerPartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        enregistrerPartie.addActionListener(e -> enregistrerPartie());
        partie.add(enregistrerPartie);

        enregistrerPartieSous.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        enregistrerPartieSous.addActionListener(e -> enregistrerPartieSous());
        partie.add(enregistrerPartieSous);

        regles.addActionListener(null);
        informations.add(regles);

        this.add(partie);
        this.add(informations);
    }

    public String convertCheminRelatif(String cheminAbsolu) {
        String path = System.getProperty("user.dir");

        Path pathDesParties = Paths.get(path + "/parties");
        Path cheminAbsoluFichier = Paths.get(cheminAbsolu);
        Path pathRelative = pathDesParties.relativize(cheminAbsoluFichier);

        return (pathRelative.toString()).replace('\\', '/');
    }

    public void chargerPartie() {
        JPanel panel = new JPanel();
        panel.setSize(new Dimension(500, 500));
        panel.setLayout(null);

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("./parties"));
        chooser.setDialogTitle("Ouvrir un fichier");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Tableau", "csv"));
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String file = convertCheminRelatif(chooser.getSelectedFile().getAbsolutePath());
            try {
                Fenetre.getGp().chargerAnciennePartie(file);
                Fenetre.getJeu().repaint();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(Fenetre.getJeu(), "Erreur: " + e.getMessage(), "Une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void enregistrerPartie() {
        try {
            Fenetre.getGp().sauvegarderPartie();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(Fenetre.getJeu(), "Erreur: " + e.getMessage(), "Une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void enregistrerPartieSous() {
        JPanel panel = new JPanel();
        panel.setSize(new Dimension(500, 500));
        panel.setLayout(null);

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("./parties"));
        chooser.setDialogTitle("Ouvrir un fichier");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Tableau", "csv"));
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String file = convertCheminRelatif(chooser.getSelectedFile().getAbsolutePath());
            try {
                Fenetre.getGp().sauvegarderPartie(file);
                Fenetre.getJeu().repaint();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(Fenetre.getJeu(), "Erreur: " + e.getMessage(), "Une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            }
        }
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
        String[] options = {"Oui", "Non"};

        // Affichage du dialogue et recuperation de la réponse sous resp
        int resp = JOptionPane.showOptionDialog(null, panel, "Nouvelle partie", 0, JOptionPane.PLAIN_MESSAGE, null, options, null);

        // Utilisation de la réponse
        if (resp == 0) {
            try {
                Fenetre.getGp().nouvellePartie();
                Fenetre.getJeu().repaint();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(Fenetre.getJeu(), "Erreur: " + e.getMessage(), "Une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
