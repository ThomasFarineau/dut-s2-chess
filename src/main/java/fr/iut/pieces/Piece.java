package fr.iut.pieces;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public abstract class Piece {
    private boolean couleur;
    private boolean[][] deplacementsPoss = new boolean[15][15];

    public Piece(boolean couleur) {
        this.couleur = couleur;
        for (boolean[] ligne : deplacementsPoss)
            Arrays.fill(ligne, false);
    }

    protected void setDeplacementsPossIndex(int x, int y) {
        deplacementsPoss[x][y] = true;
    }

    public boolean getCouleur() {
        return couleur;
    }

    public boolean[][] getDeplacementsPoss() {
        return deplacementsPoss;
    }

    public abstract Image getImage();

    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;

        if (!(o instanceof Piece))
            return false;

        if (o == this)
            return true;

        return this.toString().equals(o.toString());
    }
}
