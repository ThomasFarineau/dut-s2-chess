package fr.iut.pieces;

import java.awt.Image;

import fr.iut.fonctions.Utils;

public class Queen extends Piece {
    private static Image imgB = Utils.loadImage("ReineBlanche.png");
    private static Image imgN = Utils.loadImage("ReineNoire.png");


    public Queen(boolean couleur) {
        super(couleur);
        setPossibleMovesIndex(0, 0);
        setPossibleMovesIndex(14, 0);
        setPossibleMovesIndex(0, 14);
        setPossibleMovesIndex(14, 14);
        setPossibleMovesIndex(0, 7);
        setPossibleMovesIndex(7, 0);
        setPossibleMovesIndex(7, 14);
        setPossibleMovesIndex(14, 7);
    }

    @Override
    public String toString() {
        if (getColor())
            return "ReN";
        else
            return "ReB";
    }

    @Override
    public Image getImage() {
        if (getColor())
            return imgN;
        else
            return imgB;
    }
}