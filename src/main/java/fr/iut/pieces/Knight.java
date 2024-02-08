package fr.iut.pieces;

import java.awt.Image;

import fr.iut.fonctions.Utils;

public class Knight extends Piece {
    private static Image imgB = Utils.loadImage("CavalierBlanc.png");
    private static Image imgN = Utils.loadImage("CavalierNoir.png");

    public Knight(boolean couleur) {
        super(couleur);
        setPossibleMovesIndex(5, 6);
        setPossibleMovesIndex(6, 5);
        setPossibleMovesIndex(5, 8);
        setPossibleMovesIndex(6, 9);
        setPossibleMovesIndex(8, 5);
        setPossibleMovesIndex(9, 6);
        setPossibleMovesIndex(9, 8);
        setPossibleMovesIndex(8, 9);
    }

    @Override
    public String toString() {
        if (getColor())
            return "Cn";
        else
            return "Cb";
    }

    @Override
    public Image getImage() {
        if (getColor())
            return imgN;
        else
            return imgB;
    }
}
