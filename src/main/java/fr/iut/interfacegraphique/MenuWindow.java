package fr.iut.interfacegraphique;

import static fr.iut.fonctions.Utils.showPopupWithChoice;
import static fr.iut.fonctions.Utils.createPopupFile;
import static fr.iut.fonctions.Utils.createGroupPanel;

import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.iut.fonctions.Utils;
import fr.iut.gestionpartie.GameManager;
import fr.iut.listener.ChessboardListener;

public class MenuWindow extends JMenuBar {
    private JMenuItem enregistrerPartie = new JMenuItem("Enregistrer", new ImageIcon(Utils.loadImage("icone_sauvegarder.png")));
    private JMenuItem enregistrerPartieSous = new JMenuItem("Enregistrer sous...");

    private GamePanel pj;
    private GameManager gp;

    public MenuWindow(GamePanel pj, GameManager gp) {
        JMenuItem nouvellePartie = new JMenuItem("Nouvelle Partie", new ImageIcon(Utils.loadImage("icone_nouvelle_partie.png")));
        nouvellePartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        nouvellePartie.addActionListener(e -> nouvellePartie());
        JMenu partie = new JMenu("Partie");
        partie.add(nouvellePartie);

        JMenuItem chargerPartie = new JMenuItem("Charger une Partie...", new ImageIcon(Utils.loadImage("icone_ouvrir.png")));
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

        JMenuItem aPropos = new JMenuItem("À propos");
        aPropos.addActionListener(null);
        informations.add(aPropos);
        
        JMenuItem credits = new JMenuItem("Crédits images");
        credits.addActionListener(null);
        informations.add(credits);
       

        this.add(partie);
        this.add(informations);

        this.pj = pj;
        this.gp = gp;
    }

    public void activerEnregistrement() {
        enregistrerPartie.setEnabled(true);
        enregistrerPartieSous.setEnabled(true);
    }

    public void disableSaving() {
        enregistrerPartie.setEnabled(false);
        enregistrerPartieSous.setEnabled(false);
    }

    public boolean partieCommencer() {
        return enregistrerPartie.isEnabled() && enregistrerPartieSous.isEnabled();
    }

    public void enregistrerPartie() {
        try {
            gp.saveGame();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(pj, "Erreur: " + e.getMessage(), "Une erreur est survenue", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void enregistrerPartieSous() {
        JFileChooser choixFichier = createPopupFile(new File("./parties"), "Enregistrer un fichier", new FileNameExtensionFilter("Tableau", "csv"));

        if (choixFichier.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String file = Utils.convertRelativePath(choixFichier.getSelectedFile().getAbsolutePath());
            try {
                if (choixFichier.getSelectedFile().exists()) {
                    JPanel panelEcraser = createGroupPanel(430, 100, "Le fichier " + choixFichier.getSelectedFile().getName() + " existe déjà, voulez-vous l'écraser ?");
                    int resp = showPopupWithChoice(panelEcraser, "Enregistrer une partie", new String[]{"Oui", "Non", "Annuler"}, "Non");

                    if (resp == 0) {
                        gp.saveGame(file);
                    } else if (resp == 1) {
                        enregistrerPartieSous();
                    } else {
                        panelEcraser.setVisible(false);
                    }
                } else {
                    gp.saveGame(file);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pj, "Erreur: " + e.getMessage(), "Une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean chargerPartie() {
        JFileChooser choixFichier = createPopupFile(new File("./parties"), "Ouvrir un fichier", new FileNameExtensionFilter("Tableau", "csv"));

        if (choixFichier.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String file = Utils.convertRelativePath(choixFichier.getSelectedFile().getAbsolutePath());

            int resp = 0;
            if (partieCommencer()) {
                JPanel panelValider = createGroupPanel(430, 100, "Êtes-vous sûr de vouloir charger une partie ?");
                resp = showPopupWithChoice(panelValider, "Charger une partie", new String[]{"Oui", "Non"}, "Non");
            }
            // Utilisation de la réponse
            if (resp == 0) {
                try {
                    gp.loadSavedGame(file);
                    JPanel panelChoixTour = createGroupPanel(430, 100, "Qui va commencer à jouer, à la reprise de la partie ?");
                    resp = showPopupWithChoice(panelChoixTour, "Charger une partie", new String[]{"Blanc", "Noir"}, null);

                    if (resp == 0) {
                        gp.setPlayerRound(false);
                    } else {
                        gp.setPlayerRound(true);
                    }
                    activerEnregistrement();
                    ((ChessboardListener) pj.getListeners(MouseListener.class)[0]).setInteroperable(true);
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
            JPanel panelValider = createGroupPanel(430, 100, "Êtes-vous sûr de vouloir démarrer une nouvelle partie ?");
            resp = showPopupWithChoice(panelValider, "Nouvelle partie", new String[]{"Oui", "Non"}, "Non");
        }
        if (resp == 0) {
            try {
                gp.newGame();
                activerEnregistrement();
                gp.setPlayerRound(false);
                ((ChessboardListener) pj.getListeners(MouseListener.class)[0]).setInteroperable(true);
                pj.reInitValues();
                pj.repaint();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(pj, "Erreur: " + e.getMessage(), "Une erreur est survenue", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}