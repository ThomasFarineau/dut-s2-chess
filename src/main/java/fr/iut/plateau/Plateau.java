package fr.iut.plateau;

import java.util.Arrays;

import fr.iut.fonctions.Fonctions;
import fr.iut.pieces.Cavalier;
import fr.iut.pieces.Piece;
import fr.iut.pieces.Pion;
import fr.iut.pieces.Roi;

public class Plateau {
    private boolean tourJoueur = false;
    private Piece[][] echiquier;
    private int[] dernierEchec = null;

    public Plateau() { }
    
    public void deplacer(int[] coord) throws Exception {
        int i = coord[0];
        int j = coord[1];
        int k = coord[2];
        int l = coord[3];

        if (echiquier[i][j] == null) {
            throw new Exception("Il n'y a pas de piece sur la première case entrée.");
        }

        if (echiquier[i][j].getCouleur() != tourJoueur) {
            throw new Exception("La pièce selectionnée ne vous appartient pas.");
        }

        boolean[][] deplacementsPiece = calculerDeplacementsPiece(i, j);

        if (!deplacementsPiece[k][l]) {
            throw new Exception("La pièce sélectionnée ne peut pas aller ici.");
        }

        Piece copiePiece = echiquier[k][l];
        echiquier[k][l] = echiquier[i][j];
        echiquier[i][j] = null;

        if ((dernierEchec = verifEchec()) != null) {
            char[] charsEchec = Fonctions.convertEnCaracteres(dernierEchec);
            String etatEchec = echiquier[dernierEchec[0]][dernierEchec[1]].toString() + "(" + charsEchec[0] + charsEchec[1] + " -> " + charsEchec[2] + charsEchec[3] + ")";
            echiquier[i][j] = echiquier[k][l];
            echiquier[k][l] = copiePiece;
            throw new Exception("Mouvement impossible, il vous met en échec : " + etatEchec);
        }

        tourJoueur = !tourJoueur;
    }

    public boolean[][] calculerDeplacementsPiece(int xP, int yP) {
        boolean[][] deplacementsPlateau = new boolean[8][8]; // On crée la matrice retour
        for (boolean[] ligne : deplacementsPlateau) // On la remplit de false
            Arrays.fill(ligne, false); // Ligne par ligne

        // On récupère les différents déplacements possible de la pièce dont les "coordonnées" sont rentrées en paramètre
        boolean[][] deplacementsPiece = new boolean[15][15];

        boolean[][] deplementsACopier = echiquier[xP][yP].getDeplacementsPoss();
        for (int i = 0; i < 15; i++) {
            System.arraycopy(deplementsACopier[i], 0, deplacementsPiece[i], 0, 15);
        }

        if (echiquier[xP][yP] instanceof Pion) {
            if (echiquier[xP][yP].getCouleur() && xP == 1) {
                deplacementsPiece[9][7] = true;
                deplacementsPiece[8][7] = false;
            } else if (!echiquier[xP][yP].getCouleur() && xP == 6) {
                deplacementsPiece[5][7] = true;
                deplacementsPiece[6][7] = false;
            }
        }

        if (echiquier[xP][yP] instanceof Cavalier) { // Algorithme pour le cavalier : pas de chemin à tracer
            for (int i = 0; i < deplacementsPiece.length; i++) {
                for (int j = 0; j < deplacementsPiece[0].length; j++) {
                    if (deplacementsPiece[i][j]) { //Pour chaque déplacement
                        int xDeplacementPiece = xP + i - 7;
                        int yDeplacementPiece = yP + j - 7;

                        if (xDeplacementPiece >= 0 && xDeplacementPiece <= 7 && yDeplacementPiece >= 0 && yDeplacementPiece <= 7) {
                            if (echiquier[xDeplacementPiece][yDeplacementPiece] == null) {
                                deplacementsPlateau[xDeplacementPiece][yDeplacementPiece] = true;
                            } else if (echiquier[xDeplacementPiece][yDeplacementPiece].getCouleur() != echiquier[xP][yP].getCouleur()) {
                                deplacementsPlateau[xDeplacementPiece][yDeplacementPiece] = true;
                            }
                        }
                    }
                }
            }
        } else {
            // Calcul de la matrice retour
            for (int i = 0; i < deplacementsPiece.length; i++) {
                for (int j = 0; j < deplacementsPiece[0].length; j++) {
                    if (deplacementsPiece[i][j]) { //Pour chaque déplacement maximal
                        // Calcul du déplacement maximal absolu (par rapport à (0;0))
                        int xDeplacementPiece = i - 7;
                        int yDeplacementPiece = j - 7;

                        // Calcul de la distance du déplacement unitaire (1, -1 ou 0)
                        int xShift = (xDeplacementPiece == 0) ? 0 : (xDeplacementPiece / (Math.abs(xDeplacementPiece)));
                        int yShift = (yDeplacementPiece == 0) ? 0 : (yDeplacementPiece / (Math.abs(yDeplacementPiece)));

                        //Calcul des premiers déplacements à vérifier
                        int x = xP + xShift;
                        int y = yP + yShift;

                        while ( //Tant que :
                                (x >= 0 && x <= 7 && y >= 0 && y <= 7) &&  // On reste sur l'échiquier et qu'
                                        // On ne dépasse pas le déplacement maximal
                                        (Math.abs(x - xP) <= Math.abs(xDeplacementPiece) && (Math.abs(y - yP) <= Math.abs(yDeplacementPiece)))
                        ) {
                            if (echiquier[xP][yP] instanceof Pion) {
                                if (echiquier[x][y] == null) {
                                    if (y == yP) {
                                        deplacementsPlateau[x][y] = true;
                                        x += xShift;
                                    } else {
                                        x = 8;
                                        y = 8;
                                    }
                                } else {
                                    if (y != yP)
                                        if (echiquier[x][y].getCouleur() != echiquier[xP][yP].getCouleur())
                                            deplacementsPlateau[x][y] = true;
                                    x = 8;
                                    y = 8;
                                }
                            } else {
                                if (echiquier[x][y] == null) { // S'il n'y a rien dans la case où aller
                                    deplacementsPlateau[x][y] = true; // Le déplacement unitaire est possible

                                    // On passe au déplacement unitaire suivant (s'il existe)
                                    x += xShift;
                                    y += yShift;
                                } else { // Sinon
                                    // S'il y a une pièce de la couleur adverse, alors on peut tout de même y aller
                                    if (echiquier[x][y].getCouleur() != echiquier[xP][yP].getCouleur()) {
                                        deplacementsPlateau[x][y] = true;
                                    }

                                    // On a fini de calculer les déplacements pour le déplacement maximal courant
                                    x = 8;
                                    y = 8;
                                } // Fin else
                            }
                        } // Fin while
                    } // Fin if (deplacements...)
                } // Fin for int j
            } //Fin for int i
        }

        // On retourne la matrice des déplacements possible de la pièce sur le plateau
        return deplacementsPlateau;
    } //Fin méthode

    public int[] verifEchec() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (echiquier[i][j] != null) {
                    if (echiquier[i][j] instanceof Roi && echiquier[i][j].getCouleur() == tourJoueur) {

                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {

                                if (echiquier[k][l] != null) {
                                    if (echiquier[k][l].getCouleur() != tourJoueur) {

                                        if (calculerDeplacementsPiece(k, l)[i][j]) {
                                            return new int[]{k, l, i, j};
                                        }

                                    }
                                }
                            }
                        }

                        j = 8;
                        i = 8;
                    }
                }

            }
        }


        return null;
    }

    public boolean verifMat() {
        if ((dernierEchec = verifEchec()) == null)
            return false;

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (echiquier[i][j] != null) {
                    if (echiquier[i][j].getCouleur() == tourJoueur) {
                        boolean[][] deplacementsPossPiece = calculerDeplacementsPiece(i, j);

                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                if (deplacementsPossPiece[k][l]) {
                                    Piece copiePiece = echiquier[k][l];
                                    echiquier[k][l] = echiquier[i][j];
                                    echiquier[i][j] = null;

                                    if ((verifEchec() == null)) {
                                        echiquier[i][j] = echiquier[k][l];
                                        echiquier[k][l] = copiePiece;
                                        return false;
                                    }

                                    echiquier[i][j] = echiquier[k][l];
                                    echiquier[k][l] = copiePiece;
                                }
                            }
                        }

                    }
                }
            }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder retour = new StringBuilder();
        for (int i = 0; i < echiquier.length; i++) {
            retour.append(8 - i).append(" ");
            for (int j = 0; j < echiquier.length; j++) {
                if (echiquier[i][j] != null) {
                    if (echiquier[i][j].toString().length() == 2) {
                        retour.append(echiquier[i][j].toString()).append("  ");
                    } else {
                        retour.append(echiquier[i][j].toString()).append(" ");
                    }
                } else {
                    retour.append(" -  ");
                }
            }
            retour.append("\n");
        }
        retour.append("   A   B   C   D   E   F   G   H");
        return retour.toString();
    }

    public Piece[][] getEchiquier() {
        return echiquier;
    }

    public void setEchiquier(Piece[][] echiquier) {
        this.echiquier = echiquier;
    }

    public boolean getTourJoueur() {
        return tourJoueur;
    }

    public void setTourJoueur(boolean tourJoueur) {
        this.tourJoueur = tourJoueur;
    }

    public int[] getDernierEchec() {
        return dernierEchec;
    }
}
