package fr.iut.pieces;

import java.awt.Image;

public class Reine extends Piece {
    private static Image imgB = loadImage("ReineBlanche.png");
    private static Image imgN = loadImage("ReineNoire.png");


    public Reine(boolean couleur) {
        super(couleur);
        setDeplacementsPossIndex(0, 0, true);
        setDeplacementsPossIndex(14, 0, true);
        setDeplacementsPossIndex(0, 14, true);
        setDeplacementsPossIndex(14, 14, true);
        setDeplacementsPossIndex(0, 7, true);
        setDeplacementsPossIndex(7, 0, true);
        setDeplacementsPossIndex(7, 14, true);
        setDeplacementsPossIndex(14, 7, true);
    }

    @Override
    public String toString() {
        if (getCouleur())
            return "ReN";
        else
            return "ReB";
    }

    @Override
    public Image getImage() {
        if (getCouleur())
            return imgN;
        else
            return imgB;
    }
}