package fr.iut.interfacegraphique;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
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
    private JMenuItem credits = new JMenuItem("Crédits");
    private JMenuItem version = new JMenuItem("Version du Jeu");
    
    private PanneauJeu pj = null;
    private GestionnairePartie gp = null;

    public MenuFenetre(PanneauJeu pj, GestionnairePartie gp) {
        nouvellePartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        nouvellePartie.addActionListener(e -> nouvellePartie());
        partie.add(nouvellePartie);

        chargerPartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
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

        credits.addActionListener(null);
        informations.add(credits);
        
        version.addActionListener(null);
        informations.add(version);

        this.add(partie);
        this.add(informations);
        
        this.pj = pj;
        this.gp = gp;
    }

    public void activerEnregistrer() {
        enregistrerPartie.setEnabled(true);
        enregistrerPartieSous.setEnabled(true);
    }

    public void desactiverEnregistrer() {
        enregistrerPartie.setEnabled(false);
        enregistrerPartieSous.setEnabled(false);
    }

    public boolean partieCommencer() {
        return enregistrerPartie.isEnabled() && enregistrerPartieSous.isEnabled();
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

            int resp = 0;
            if(partieCommencer()) {
                JPanel panel2 = new JPanel();
                panel2.setSize(new Dimension(430, 100));
                panel2.setLayout(null);

                // Ajout du message
                JLabel label = new JLabel("Êtes-vous sûr de vouloir charger une partie ?");
                label.setBounds(0, 15, 430, 30);
                label.setHorizontalAlignment((int) CENTER_ALIGNMENT);
                label.setFont(new Font("Calibri", Font.PLAIN, 16));
                panel2.add(label);

                // Changement de la taille du dialogue
                UIManager.put("OptionPane.minimumSize", new Dimension(430, 100));

                // Ajout des options oui et non
                String[] options = {"Oui", "Non"};

                // Affichage du dialogue et recuperation de la réponse sous resp
                resp = JOptionPane.showOptionDialog(null, panel2, "Charger une partie", 0, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
            }

            // Utilisation de la réponse
            if (resp == 0) {
                try {
                    gp.chargerAnciennePartie(file);

                    JPanel panel3 = new JPanel();
                    panel3.setSize(new Dimension(430, 100));
                    panel3.setLayout(null);

                    // Ajout du message
                    JLabel label = new JLabel("Qui va commencer à jouer, à la reprise de la partie ?");
                    label.setBounds(0, 15, 430, 30);
                    label.setHorizontalAlignment((int) CENTER_ALIGNMENT);
                    label.setFont(new Font("Calibri", Font.PLAIN, 16));
                    panel3.add(label);

                    // Changement de la taille du dialogue
                    UIManager.put("OptionPane.minimumSize", new Dimension(430, 100));

                    String[] options = {"Blanc", "Noir"};

                    // Affichage du dialogue et recuperation de la réponse sous resp
                    resp = JOptionPane.showOptionDialog(null, panel3, "Charger une partie", 0, JOptionPane.PLAIN_MESSAGE, null, options, null);

                    if(resp == 0) {
                        gp.getPlat().setTourJoueur(false);
                    } else if(resp == 1) {
                        gp.getPlat().setTourJoueur(true);
                    }

                    pj.repaint();
                    activerEnregistrer();
                    ((EchiquierListener)pj.getListeners(MouseListener.class)[0]).setInteractable(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(pj, "Erreur: " + e.getMessage(), "Une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void nouvellePartie() {

        int resp = 0;
        if(partieCommencer()) {
            // Creation du panel pour l'affichage du dialogue
            JPanel panel = new JPanel();
            panel.setSize(new Dimension(430, 100));
            panel.setLayout(null);

            // Ajout du message
            JLabel label = new JLabel("Êtes-vous sûr de vouloir démarrer une nouvelle partie ?");
            label.setBounds(0, 15, 430, 30);
            label.setHorizontalAlignment((int) CENTER_ALIGNMENT);
            label.setFont(new Font("Calibri", Font.PLAIN, 16));
            panel.add(label);

            // Changement de la taille du dialogue
            UIManager.put("OptionPane.minimumSize", new Dimension(430, 100));

            // Ajout des options oui et non
            String[] options = {"Oui", "Non"};

            // Affichage du dialogue et recuperation de la réponse sous resp
            resp = JOptionPane.showOptionDialog(null, panel, "Nouvelle partie", 0, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
        }
        // Utilisation de la réponse
        if (resp == 0) {
            try {
                gp.nouvellePartie();
                activerEnregistrer();
                pj.repaint();
                ((EchiquierListener)pj.getListeners(MouseListener.class)[0]).setInteractable(true);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(pj, "Erreur: " + e.getMessage(), "Une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
