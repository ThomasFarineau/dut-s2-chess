package fr.iut.pieces;

import java.awt.Image;

import fr.iut.fonctions.Fonctions;

public class Tour extends Piece {
    private static Image imgB = Fonctions.loadImage("TourBlanche.png");
    private static Image imgN = Fonctions.loadImage("TourNoire.png");

    public Tour(boolean couleur) {
        super(couleur);
        setDeplacementsPossIndex(0, 7);
        setDeplacementsPossIndex(7, 0);
        setDeplacementsPossIndex(7, 14);
        setDeplacementsPossIndex(14, 7);
    }

    @Override
    public String toString() {
        if (getCouleur())
            return "Tn";
        else
            return "Tb";
    }

    @Override
    public Image getImage() {
        if (getCouleur())
            return imgN;
        else
            return imgB;
    }
}