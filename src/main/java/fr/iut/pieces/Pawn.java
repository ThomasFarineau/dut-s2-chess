package fr.iut.pieces;

import java.awt.Image;

import fr.iut.fonctions.Utils;

public class Pawn extends Piece {
    private static Image imgB = Utils.loadImage("PionBlanc.png");
    private static Image imgN = Utils.loadImage("PionNoir.png");

    public Pawn(boolean couleur) {
        super(couleur);
        if (couleur) {
            this.setPossibleMovesIndex(8, 7);
            this.setPossibleMovesIndex(8, 6);
            this.setPossibleMovesIndex(8, 8);
        } else {
            this.setPossibleMovesIndex(6, 7);
            this.setPossibleMovesIndex(6, 6);
            this.setPossibleMovesIndex(6, 8);
        }
    }

    @Override
    public String toString() {
        if (getColor())
            return "Pn";
        else
            return "Pb";
    }

    @Override
    public Image getImage() {
        if (getColor())
            return imgN;
        else
            return imgB;
    }
}
