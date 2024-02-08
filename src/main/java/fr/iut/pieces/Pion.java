package fr.iut.pieces;

import java.awt.Image;

import fr.iut.fonctions.Fonctions;

public class Pion extends Piece {
    private static Image imgB = Fonctions.loadImage("PionBlanc.png");
    private static Image imgN = Fonctions.loadImage("PionNoir.png");

    public Pion(boolean couleur) {
        super(couleur);
        if (couleur) {
            this.setDeplacementsPossIndex(8, 7);
            this.setDeplacementsPossIndex(8, 6);
            this.setDeplacementsPossIndex(8, 8);
        } else {
            this.setDeplacementsPossIndex(6, 7);
            this.setDeplacementsPossIndex(6, 6);
            this.setDeplacementsPossIndex(6, 8);
        }
    }

    @Override
    public String toString() {
        if (getCouleur())
            return "Pn";
        else
            return "Pb";
    }

    @Override
    public Image getImage() {
        if (getCouleur())
            return imgN;
        else
            return imgB;
    }
}
