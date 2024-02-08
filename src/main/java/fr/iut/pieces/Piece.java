package fr.iut.pieces;

import java.awt.*;
import java.util.Arrays;

public abstract class Piece {
    private final boolean color;
    private final boolean[][] possibleMoves = new boolean[15][15];

    public Piece(boolean color) {
        this.color = color;
        for (boolean[] ligne : possibleMoves)
            Arrays.fill(ligne, false);
    }

    protected void setPossibleMovesIndex(int x, int y) {
        possibleMoves[x][y] = true;
    }

    public boolean getColor() {
        return color;
    }

    public boolean[][] getPossibleMoves() {
        return possibleMoves;
    }

    public abstract Image getImage();

    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;

        if (!(o instanceof Piece)) return false;

        if (o == this) return true;

        return this.toString().equals(o.toString());
    }
}
