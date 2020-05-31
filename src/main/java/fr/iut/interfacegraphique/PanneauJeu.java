package fr.iut.interfacegraphique;

import static fr.iut.fonctions.Fonctions.ajoutChoix;
import static fr.iut.fonctions.Fonctions.creerPopup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fr.iut.fonctions.Fonctions;
import fr.iut.listener.EchiquierListener;
import fr.iut.pieces.Piece;
import fr.iut.plateau.Plateau;

public class PanneauJeu extends JPanel {
    private final static String imgPath = "./img/";
    private Fenetre parent;
    private Image fondEchiquier = null;
    private Plateau plat;
    private int[] selection = null; // coordonnées de la 1re sélection, si elle est valide
    private boolean[][] selectionDeplacementsPoss; // déplacements possibles de la sélection

    private int[] casesEchec = null; // coordonnées des cases de l'échec (case du roi + case de la pièce qui le met en échec)
    private String dernierMouvement = "-";

    public PanneauJeu(Plateau plat, Fenetre parent) {
        this.parent = parent;
        this.plat = plat;
        this.addMouseListener(new EchiquierListener(this));

        try {
            fondEchiquier = ImageIO.read(new File(imgPath + "tempEchiquier.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 600, 600, 60);

        int xOffset = 40;
        g.drawImage(fondEchiquier, 0, 0, null);

        Piece[][] echiquier = plat.getEchiquier();

        if (casesEchec != null) {
            g.setColor(new Color(255, 150, 150));
            g.fillRect(xOffset + 70 * casesEchec[1], 70 * casesEchec[0], 70, 70);
            g.fillRect(xOffset + 70 * casesEchec[3], 70 * casesEchec[2], 70, 70);

            g.setColor(new Color(0, 0, 0, 75));
            g.drawRect(xOffset + 70 * casesEchec[1], 70 * casesEchec[0], 69, 69);
            g.drawRect(xOffset + 70 * casesEchec[1] + 1, 70 * casesEchec[0] + 1, 67, 67);
            g.drawRect(xOffset + 70 * casesEchec[3], 70 * casesEchec[3], 69, 69);
            g.drawRect(xOffset + 70 * casesEchec[3] + 1, 70 * casesEchec[2] + 1, 67, 67);
        }

        if (selection != null) {
            g.setColor(new Color(255, 255, 150));
            g.fillRect(xOffset + 70 * selection[1], 70 * selection[0], 70, 70);

            g.setColor(new Color(80, 0, 0, 75));
            g.drawRect(xOffset + 70 * selection[1], 70 * selection[0], 69, 69);
            g.drawRect(xOffset + 70 * selection[1] + 1, 70 * selection[0] + 1, 67, 67);
        }

        if (echiquier != null) {
            for (int i = 0; i < echiquier.length; i++) {
                for (int j = 0; j < echiquier[i].length; j++) {
                    if (selectionDeplacementsPoss != null) {
                        if (selectionDeplacementsPoss[i][j]) {
                            g.setColor(new Color(150, 255, 255));
                            g.fillRect(xOffset + 70 * j, 70 * i, 70, 70);

                            g.setColor(new Color(0, 0, 0, 75));
                            g.drawRect(xOffset + 70 * j, 70 * i, 69, 69);
                            g.drawRect(xOffset + 70 * j + 1, 70 * i + 1, 67, 67);
                        }
                    }

                    if (echiquier[i][j] != null) {
                        g.drawImage(echiquier[i][j].getImage(), xOffset + 70 * j, 70 * i, null);
                    }
                }
            }

            int horizontalCenter = this.getPreferredSize().width / 2;
            // Dessin de la partie du bas
            g.setColor(new Color(30, 30, 30));
            g.drawLine(horizontalCenter, 600, horizontalCenter, 660);

            Font bold = new Font("Calibri", Font.BOLD, 16);
            Font other = new Font("Calibri", Font.PLAIN, 24);
            FontMetrics fmBold = g.getFontMetrics(bold);
            FontMetrics fmOther = g.getFontMetrics(other);

            g.setColor(Color.white);
            g.setFont(bold);
            g.drawString("Tour du joueur", (horizontalCenter - fmBold.stringWidth("Tour du joueur")) / 2, 600 + fmBold.getHeight());
            g.drawString("Dernier mouvement effectué", horizontalCenter + (horizontalCenter - fmBold.stringWidth("Dernier mouvement effectué")) / 2, 600 + fmBold.getHeight());

            g.setFont(other);
            if (plat.getTourJoueur()) {
                g.setColor(Color.black);
                g.drawString("Noir", (horizontalCenter - fmOther.stringWidth("Noir")) / 2, 600 + fmBold.getHeight() + fmOther.getHeight());
                g.setColor(Color.white);
            } else {
                g.setColor(Color.white);
                g.drawString("Blanc", (horizontalCenter - fmOther.stringWidth("Blanc")) / 2, 600 + fmBold.getHeight() + fmOther.getHeight());
                g.setColor(Color.black);
            }

            g.drawString(dernierMouvement, horizontalCenter + (horizontalCenter - fmOther.stringWidth(dernierMouvement)) / 2, 600 + fmBold.getHeight() + fmOther.getHeight());
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 660);
    }

    public void recommencer() {

        int resp = -1;
        while (resp == -1) {
            JPanel panelRecommencer = creerPopup(430, 120, "Félicitations, les " + (plat.getTourJoueur() ? "blancs" : "noirs") + " ont gagné !<BR>Que voulez vous faire ?");
            resp = ajoutChoix(panelRecommencer, "Enregistrer une partie", new String[]{"Nouvelle partie", "Charger une partie", "Quitter"}, "Nouvelle partie");

            if (resp == 0) {
                parent.getMf().nouvellePartie();
            } else if (resp == 1) {
                if (!parent.getMf().chargerPartie())
                    resp = -1;
            } else if (resp == 2) {
                System.exit(0);
            }
        }
        repaint();
    }

    public void selectionner(int i, int j) {
        // La sélection ne fonctionne que si elle est sur une pièce de la bonne couleur
        if (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
            if (plat.getEchiquier()[i][j] != null) {
                if (plat.getEchiquier()[i][j].getCouleur() == plat.getTourJoueur()) {
                    this.selection = new int[]{i, j};
                    this.selectionDeplacementsPoss = plat.calculerDeplacementsPiece(i, j);
                } else {
                    this.deplacer(i, j);
                }
            } else {
                this.deplacer(i, j);
            }
        } else {
            resetSelection();
        }
    }

    public void resetSelection() {
        this.selection = null;
        this.selectionDeplacementsPoss = null;
    }

    public void deplacer(int i, int j) {
        try {
            plat.deplacer(new int[]{selection[0], selection[1], i, j});
            this.casesEchec = plat.verifEchec();

            String pieceDeplacee = plat.getEchiquier()[i][j].toString();
            char[] depChars = Fonctions.convertEnCaracteres(new int[]{selection[0], selection[1], i, j});
            dernierMouvement = pieceDeplacee + " (" + depChars[0] + depChars[1] + " -> " + depChars[2] + depChars[3] + ")";

        } catch (Exception e) {
            this.casesEchec = plat.getDernierEchec();
        }

        resetSelection();

        if (plat.verifMat()) {
            repaint();
            recommencer();
        }
    }

    public void reInitValues() {
        dernierMouvement = "-";
        selection = null;
        plat.verifMat();
        casesEchec = plat.getDernierEchec();
    }
}