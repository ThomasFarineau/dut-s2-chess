package fr.iut.pieces;

import java.awt.Image;

import fr.iut.fonctions.Utils;

public class King extends Piece {
    private static Image imgB = Utils.loadImage("RoiBlanc.png");
    private static Image imgN = Utils.loadImage("RoiNoir.png");

    public King(boolean couleur) {
        super(couleur);
        setPossibleMovesIndex(6, 6);
        setPossibleMovesIndex(6, 7);
        setPossibleMovesIndex(6, 8);
        setPossibleMovesIndex(7, 6);
        setPossibleMovesIndex(7, 8);
        setPossibleMovesIndex(8, 6);
        setPossibleMovesIndex(8, 7);
        setPossibleMovesIndex(8, 8);
    }

    @Override
    public String toString() {
        if (getColor())
            return "RoN";
        else
            return "RoB";
    }

    @Override
    public Image getImage() {
        if (getColor())
            return imgN;
        else
            return imgB;
    }
}
