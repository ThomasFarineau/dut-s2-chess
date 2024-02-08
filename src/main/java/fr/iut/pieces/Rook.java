package fr.iut.pieces;

import java.awt.Image;

import fr.iut.fonctions.Utils;

public class Rook extends Piece {
    private static Image imgB = Utils.loadImage("TourBlanche.png");
    private static Image imgN = Utils.loadImage("TourNoire.png");

    public Rook(boolean couleur) {
        super(couleur);
        setPossibleMovesIndex(0, 7);
        setPossibleMovesIndex(7, 0);
        setPossibleMovesIndex(7, 14);
        setPossibleMovesIndex(14, 7);
    }

    @Override
    public String toString() {
        if (getColor())
            return "Tn";
        else
            return "Tb";
    }

    @Override
    public Image getImage() {
        if (getColor())
            return imgN;
        else
            return imgB;
    }
}