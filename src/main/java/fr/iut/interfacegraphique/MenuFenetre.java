package fr.iut.interfacegraphique;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.iut.fonctions.Fonctions;
import fr.iut.gestionpartie.GestionnairePartie;
import fr.iut.listener.EchiquierListener;

public class MenuFenetre extends JMenuBar {
    private JMenu partie = new JMenu("Partie");
    private JMenuItem nouvellePartie = new JMenuItem("Nouvelle Partie");
    private JMenuItem chargerPartie = new JMenuItem("Charger une Partie...");
    private JMenuItem enregistrerPartie = new JMenuItem("Enregistrer");
    private JMenuItem enregistrerPartieSous = new JMenuItem("Enregistrer sous...");

    private JMenu informations = new JMenu("Informations");
    private JMenuItem regles = new JMenuItem("Règles du jeu");
    
    private PanneauJeu pj = null;
    private GestionnairePartie gp = null;

    public MenuFenetre(PanneauJeu pj, GestionnairePartie gp) {
        nouvellePartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        nouvellePartie.addActionListener(e -> nouvellePartie());
        partie.add(nouvellePartie);

        chargerPartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        chargerPartie.addActionListener(e -> chargerPartie());
        partie.add(chargerPartie);

        enregistrerPartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        enregistrerPartie.addActionListener(e -> enregistrerPartie());
        enregistrerPartie.setEnabled(false);
        partie.add(enregistrerPartie);

        enregistrerPartieSous.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        enregistrerPartieSous.addActionListener(e -> enregistrerPartieSous());
        enregistrerPartieSous.setEnabled(false);
        partie.add(enregistrerPartieSous);

        regles.addActionListener(null);
        informations.add(regles);

        this.add(partie);
        this.add(informations);
        
        this.pj = pj;
        this.gp = gp;
    }

    public void chargerPartie() {
        JPanel panel = new JPanel();
        panel.setSize(new Dimension(500, 500));
        panel.setLayout(null);

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("./parties"));
        chooser.setDialogTitle("Ouvrir un fichier");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Tableau", "csv"));
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String file = Fonctions.convertCheminRelatif(chooser.getSelectedFile().getAbsolutePath());
            try {
                gp.chargerAnciennePartie(file);
                pj.repaint();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pj, "Erreur: " + e.getMessage(), "Une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void enregistrerPartie() {
        try {
            gp.sauvegarderPartie();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(pj, "Erreur: " + e.getMessage(), "Une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void enregistrerPartieSous() {
        JPanel panel = new JPanel();
        panel.setSize(new Dimension(500, 500));
        panel.setLayout(null);

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("./parties"));
        chooser.setDialogTitle("Enregistrer un fichier");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Tableau", "csv"));
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String file = Fonctions.convertCheminRelatif(chooser.getSelectedFile().getAbsolutePath());
            try {
                gp.sauvegarderPartie(file);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pj, "Erreur: " + e.getMessage(), "Une erreur est survenue", JOptionPane.ERROR_MESSAGE);
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
                gp.nouvellePartie();
                pj.repaint();
                ((EchiquierListener)pj.getListeners(MouseListener.class)[0]).setInteractable(true);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(pj, "Erreur: " + e.getMessage(), "Une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
