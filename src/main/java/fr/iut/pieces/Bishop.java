package fr.iut.pieces;

import java.awt.Image;

import fr.iut.fonctions.Utils;

public class Bishop extends Piece {
    private static Image imgB = Utils.loadImage("FouBlanc.png");
    private static Image imgN = Utils.loadImage("FouNoir.png");

    public Bishop(boolean couleur) {
        super(couleur);
        setPossibleMovesIndex(0, 0);
        setPossibleMovesIndex(14, 0);
        setPossibleMovesIndex(0, 14);
        setPossibleMovesIndex(14, 14);
    }

    @Override
    public String toString() {
        if (getColor())
            return "Fn";
        else
            return "Fb";
    }

    @Override
    public Image getImage() {
        if (getColor())
            return imgN;
        else
            return imgB;
    }
}