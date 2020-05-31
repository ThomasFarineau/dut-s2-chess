package fr.iut.pieces;

import java.awt.*;

public class Fou extends Piece {
    private static Image imgB = loadImage("FouBlanc.png");
    private static Image imgN = loadImage("FouNoir.png");

    public Fou(boolean couleur) {
        super(couleur);
        setDeplacementsPossIndex(0, 0, true);
        setDeplacementsPossIndex(14, 0, true);
        setDeplacementsPossIndex(0, 14, true);
        setDeplacementsPossIndex(14, 14, true);
    }

    @Override
    public String toString() {
        if (getCouleur())
            return "Fn";
        else
            return "Fb";
    }

    @Override
    public Image getImage() {
        if (getCouleur())
            return imgN;
        else
            return imgB;
    }
}