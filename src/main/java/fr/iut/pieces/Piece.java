package fr.iut.pieces;
import java.util.Arrays;

public abstract class Piece {
	private boolean couleur;
	
	private boolean[][] deplacementsPoss = new boolean[15][15];
	
	public Piece(boolean couleur) {
		this.couleur=couleur;
		for(boolean[] ligne : deplacementsPoss)
			Arrays.fill(ligne, false);
	}
	
	public void setDeplacementsPossIndex(int x, int y, boolean val) {
		deplacementsPoss[x][y] = val;
	}

	
	public boolean getCouleur() {
		return couleur;
	}


	public boolean[][] getDeplacementsPoss() {
		return deplacementsPoss;
	}

	public void setDeplacementsPoss(boolean[][] deplacementsPoss) {
		this.deplacementsPoss = deplacementsPoss;
	}
	
	public abstract String toString();
}
