package fr.iut.pieces;

import java.awt.Image;

import fr.iut.fonctions.Fonctions;

public class Fou extends Piece {
    private static Image imgB = Fonctions.loadImage("FouBlanc.png");
    private static Image imgN = Fonctions.loadImage("FouNoir.png");

    public Fou(boolean couleur) {
        super(couleur);
        setDeplacementsPossIndex(0, 0);
        setDeplacementsPossIndex(14, 0);
        setDeplacementsPossIndex(0, 14);
        setDeplacementsPossIndex(14, 14);
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