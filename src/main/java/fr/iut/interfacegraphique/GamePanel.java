package fr.iut.interfacegraphique;

import fr.iut.fonctions.Utils;
import fr.iut.listener.ChessboardListener;
import fr.iut.pieces.Piece;
import fr.iut.plateau.Board;

import javax.swing.*;
import java.awt.*;

import static fr.iut.fonctions.Utils.createGroupPanel;
import static fr.iut.fonctions.Utils.showPopupWithChoice;

public class GamePanel extends JPanel {
    private final Window parent;
    private final Image chessboardBackground = Utils.loadImage("fondEchiquier.png");
    private final Board board;
    private int[] selection = null; // coordonnées de la 1re sélection, si elle est valide
    private boolean[][] possibleMovesSelection; // déplacements possibles de la sélection

    private int[] chessCases = null; // coordonnées des cases de l'échec (case du roi + case de la pièce qui le met en échec)
    private String lastMove = "-";

    public GamePanel(Board board, Window parent) {
        this.parent = parent;
        this.board = board;
        this.addMouseListener(new ChessboardListener(this));
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 600, 600, 60);

        int xOffset = 40;
        g.drawImage(chessboardBackground, 0, 0, null);

        Piece[][] chessboard = board.getChessboard();

        if (chessCases != null) {
            g.setColor(new Color(255, 150, 150));
            g.fillRect(xOffset + 70 * chessCases[1], 70 * chessCases[0], 70, 70);
            g.fillRect(xOffset + 70 * chessCases[3], 70 * chessCases[2], 70, 70);

            g.setColor(new Color(0, 0, 0, 75));
            g.drawRect(xOffset + 70 * chessCases[1], 70 * chessCases[0], 69, 69);
            g.drawRect(xOffset + 70 * chessCases[1] + 1, 70 * chessCases[0] + 1, 67, 67);
            g.drawRect(xOffset + 70 * chessCases[3], 70 * chessCases[3], 69, 69);
            g.drawRect(xOffset + 70 * chessCases[3] + 1, 70 * chessCases[2] + 1, 67, 67);
        }

        if (selection != null) {
            g.setColor(new Color(255, 255, 150));
            g.fillRect(xOffset + 70 * selection[1], 70 * selection[0], 70, 70);

            g.setColor(new Color(80, 0, 0, 75));
            g.drawRect(xOffset + 70 * selection[1], 70 * selection[0], 69, 69);
            g.drawRect(xOffset + 70 * selection[1] + 1, 70 * selection[0] + 1, 67, 67);
        }

        if (chessboard != null) {
            for (int i = 0; i < chessboard.length; i++) {
                for (int j = 0; j < chessboard[i].length; j++) {
                    if (possibleMovesSelection != null) {
                        if (possibleMovesSelection[i][j]) {
                            g.setColor(new Color(150, 255, 255));
                            g.fillRect(xOffset + 70 * j, 70 * i, 70, 70);

                            g.setColor(new Color(0, 0, 0, 75));
                            g.drawRect(xOffset + 70 * j, 70 * i, 69, 69);
                            g.drawRect(xOffset + 70 * j + 1, 70 * i + 1, 67, 67);
                        }
                    }

                    if (chessboard[i][j] != null) {
                        g.drawImage(chessboard[i][j].getImage(), xOffset + 70 * j, 70 * i, null);
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
            if (board.getPlayerRound()) {
                g.setColor(Color.black);
                g.drawString("Noir", (horizontalCenter - fmOther.stringWidth("Noir")) / 2, 600 + fmBold.getHeight() + fmOther.getHeight());
                g.setColor(Color.white);
            } else {
                g.setColor(Color.white);
                g.drawString("Blanc", (horizontalCenter - fmOther.stringWidth("Blanc")) / 2, 600 + fmBold.getHeight() + fmOther.getHeight());
                g.setColor(Color.black);
            }

            g.drawString(lastMove, horizontalCenter + (horizontalCenter - fmOther.stringWidth(lastMove)) / 2, 600 + fmBold.getHeight() + fmOther.getHeight());
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 660);
    }

    public void restart() {
        parent.getMenuWindow().disableSaving();
        int resp = -1;
        while (resp == -1) {
            JPanel panelRecommencer = createGroupPanel(430, 120, "Félicitations, les " + (board.getPlayerRound() ? "blancs" : "noirs") + " ont gagné !<BR>Que voulez vous faire ?");
            resp = showPopupWithChoice(panelRecommencer, "Enregistrer une partie", new String[]{"Nouvelle partie", "Charger une partie", "Quitter"}, "Nouvelle partie");

            if (resp == 0) {
                parent.getMenuWindow().nouvellePartie();
            } else if (resp == 1) {
                if (!parent.getMenuWindow().chargerPartie()) resp = -1;
            } else if (resp == 2) {
                System.exit(0);
            }
        }
        repaint();
    }

    public void select(int i, int j) {
        // La sélection ne fonctionne que si elle est sur une pièce de la bonne couleur
        if (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
            if (board.getChessboard()[i][j] != null) {
                if (board.getChessboard()[i][j].getColor() == board.getPlayerRound()) {
                    this.selection = new int[]{i, j};
                    this.possibleMovesSelection = board.calculerDeplacementsPiece(i, j);
                } else {
                    this.movePiece(i, j);
                }
            } else {
                this.movePiece(i, j);
            }
        } else {
            resetSelection();
        }
    }

    public void resetSelection() {
        this.selection = null;
        this.possibleMovesSelection = null;
    }

    public void movePiece(int i, int j) {
        try {
            board.move(new int[]{selection[0], selection[1], i, j});
            this.chessCases = board.checkCheck();

            String movedPiece = board.getChessboard()[i][j].toString();
            char[] depChars = Utils.convertToChar(new int[]{selection[0], selection[1], i, j});
            lastMove = movedPiece + " (" + depChars[0] + depChars[1] + " -> " + depChars[2] + depChars[3] + ")";

        } catch (Exception e) {
            this.chessCases = board.getLastCheck();
        }

        resetSelection();

        if (board.checkMat()) {
            repaint();
            restart();
        }
    }

    public void reInitValues() {
        lastMove = "-";
        resetSelection();
        board.checkMat();
        chessCases = board.getLastCheck();
    }
}