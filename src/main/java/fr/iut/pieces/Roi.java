package fr.iut.pieces;

import java.awt.Image;

import fr.iut.fonctions.Fonctions;

public class Roi extends Piece {
    private static Image imgB = Fonctions.loadImage("RoiBlanc.png");
    private static Image imgN = Fonctions.loadImage("RoiNoir.png");

    public Roi(boolean couleur) {
        super(couleur);
        setDeplacementsPossIndex(6, 6);
        setDeplacementsPossIndex(6, 7);
        setDeplacementsPossIndex(6, 8);
        setDeplacementsPossIndex(7, 6);
        setDeplacementsPossIndex(7, 8);
        setDeplacementsPossIndex(8, 6);
        setDeplacementsPossIndex(8, 7);
        setDeplacementsPossIndex(8, 8);
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
