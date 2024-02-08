package fr.iut.pieces;

import java.awt.Image;

import fr.iut.fonctions.Fonctions;

public class Reine extends Piece {
    private static Image imgB = Fonctions.loadImage("ReineBlanche.png");
    private static Image imgN = Fonctions.loadImage("ReineNoire.png");


    public Reine(boolean couleur) {
        super(couleur);
        setDeplacementsPossIndex(0, 0);
        setDeplacementsPossIndex(14, 0);
        setDeplacementsPossIndex(0, 14);
        setDeplacementsPossIndex(14, 14);
        setDeplacementsPossIndex(0, 7);
        setDeplacementsPossIndex(7, 0);
        setDeplacementsPossIndex(7, 14);
        setDeplacementsPossIndex(14, 7);
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