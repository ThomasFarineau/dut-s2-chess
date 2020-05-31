package fr.iut.pieces;

import java.awt.*;

public class Roi extends Piece {
    private static Image imgB = loadImage("RoiBlanc.png");
    private static Image imgN = loadImage("RoiNoir.png");

    public Roi(boolean couleur) {
        super(couleur);
        setDeplacementsPossIndex(6, 6, true);
        setDeplacementsPossIndex(6, 7, true);
        setDeplacementsPossIndex(6, 8, true);
        setDeplacementsPossIndex(7, 6, true);
        setDeplacementsPossIndex(7, 8, true);
        setDeplacementsPossIndex(8, 6, true);
        setDeplacementsPossIndex(8, 7, true);
        setDeplacementsPossIndex(8, 8, true);
    }

    @Override
    public String toString() {
        if (getCouleur())
            return "RoN";
        else
            return "RoB";
    }

    @Override
    public Image getImage() {
        if (getCouleur())
            return imgN;
        else
            return imgB;
    }
}
