package fr.iut.pieces;

import java.awt.Image;

public class Cavalier extends Piece {
    private static Image imgB = loadImage("CavalierBlanc.png");
    private static Image imgN = loadImage("CavalierNoir.png");

    public Cavalier(boolean couleur) {
        super(couleur);
        setDeplacementsPossIndex(5, 6);
        setDeplacementsPossIndex(6, 5);
        setDeplacementsPossIndex(5, 8);
        setDeplacementsPossIndex(6, 9);
        setDeplacementsPossIndex(8, 5);
        setDeplacementsPossIndex(9, 6);
        setDeplacementsPossIndex(9, 8);
        setDeplacementsPossIndex(8, 9);
    }

    @Override
    public String toString() {
        if (getCouleur())
            return "Cn";
        else
            return "Cb";
    }

    @Override
    public Image getImage() {
        if (getCouleur())
            return imgN;
        else
            return imgB;
    }
}
