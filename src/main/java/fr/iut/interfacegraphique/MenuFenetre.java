package fr.iut.interfacegraphique;

import fr.iut.fonctions.Fonctions;
import fr.iut.gestionpartie.GestionnairePartie;
import fr.iut.listener.EchiquierListener;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import static fr.iut.fonctions.Fonctions.*;

public class MenuFenetre extends JMenuBar {
    private JMenuItem enregistrerPartie = new JMenuItem("Enregistrer");
    private JMenuItem enregistrerPartieSous = new JMenuItem("Enregistrer sous...");

    private PanneauJeu pj;
    private GestionnairePartie gp;

    public MenuFenetre(PanneauJeu pj, GestionnairePartie gp) {
        JMenuItem nouvellePartie = new JMenuItem("Nouvelle Partie");
        nouvellePartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        nouvellePartie.addActionListener(e -> nouvellePartie());
        JMenu partie = new JMenu("Partie");
        partie.add(nouvellePartie);

        JMenuItem chargerPartie = new JMenuItem("Charger une Partie...");
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

        JMenu informations = new JMenu("Informations");

        JMenuItem credits = new JMenuItem("Cr\u00E9dits");
        credits.addActionListener(null);
        informations.add(credits);

        JMenuItem version = new JMenuItem("Version du Jeu");
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
        JFileChooser choixFichier = creerChoixFichier(new File("./parties"), "Enregistrer un fichier", new FileNameExtensionFilter("Tableau", "csv"));

        if (choixFichier.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String file = Fonctions.convertCheminRelatif(choixFichier.getSelectedFile().getAbsolutePath());
            try {
                if (choixFichier.getSelectedFile().exists()) {
                    JPanel panelEcraser = creerPopup(430, 100, "Le fichier " + choixFichier.getSelectedFile().getName() + " existe d\u00E9j\u00E0, voulez-vous l'\u00E9craser ?");
                    int resp = ajoutChoix(panelEcraser, "Enregistrer une partie", new String[]{"Oui", "Non", "Annuler"}, "Non");

                    if (resp == 0) {
                        gp.sauvegarderPartie(file);
                    } else if (resp == 1) {
                        enregistrerPartieSous();
                    } else {
                        panelEcraser.setVisible(false);
                    }
                } else {
                    gp.sauvegarderPartie(file);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pj, "Erreur: " + e.getMessage(), "Une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean chargerPartie() {
        JFileChooser choixFichier = creerChoixFichier(new File("./parties"), "Ouvrir un fichier", new FileNameExtensionFilter("Tableau", "csv"));

        if (choixFichier.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String file = Fonctions.convertCheminRelatif(choixFichier.getSelectedFile().getAbsolutePath());

            int resp = 0;
            if (partieCommencer()) {
                JPanel panelValider = creerPopup(430, 100, "\u00CAtes-vous s\u00FBr de vouloir charger une partie ?");
                resp = ajoutChoix(panelValider, "Charger une partie", new String[]{"Oui", "Non"}, "Non");
            }
            // Utilisation de la réponse
            if (resp == 0) {
                try {
                    gp.chargerAnciennePartie(file);
                    JPanel panelChoixTour = creerPopup(430, 100, "Qui va commencer \u00E0 jouer, \u00E0 la reprise de la partie ?");
                    resp = ajoutChoix(panelChoixTour, "Charger une partie", new String[]{"Blanc", "Noir"}, null);

                    if (resp == 0) {
                        gp.setTourJoueur(false);
                    } else {
                        gp.setTourJoueur(true);
                    }
                    activerEnregistrer();
                    ((EchiquierListener) pj.getListeners(MouseListener.class)[0]).setInteractable(true);
                    pj.reInitValues();
                    pj.repaint();
                    return true;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(pj, "Erreur: " + e.getMessage(), "Une erreur est survenue", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return false;
    }

    public void nouvellePartie() {
        int resp = 0;
        if (partieCommencer()) {
            JPanel panelValider = creerPopup(430, 100, "\u00CAtes-vous s\u00FBr de vouloir d\u00E9marrer une nouvelle partie ?");
            resp = ajoutChoix(panelValider, "Nouvelle partie", new String[]{"Oui", "Non"}, "Non");
        }
        if (resp == 0) {
            try {
                gp.nouvellePartie();
                activerEnregistrer();
                gp.setTourJoueur(false);
                ((EchiquierListener) pj.getListeners(MouseListener.class)[0]).setInteractable(true);
                pj.reInitValues();
                pj.repaint();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(pj, "Erreur: " + e.getMessage(), "Une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}