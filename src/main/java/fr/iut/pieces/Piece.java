package fr.iut.pieces;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public abstract class Piece {
	protected final static String imgPath = "./img/";
	private boolean couleur;
	private boolean[][] deplacementsPoss = new boolean[15][15];
	
	public Piece(boolean couleur) {
		this.couleur=couleur;
		for(boolean[] ligne : deplacementsPoss)
			Arrays.fill(ligne, false);
	}
	
	protected void setDeplacementsPossIndex(int x, int y, boolean val) {
		deplacementsPoss[x][y] = val;
	}
	
	public boolean getCouleur() {
		return couleur;
	}


	public boolean[][] getDeplacementsPoss() {
		return deplacementsPoss;
	}
	
	protected static Image loadImage(String nomFichier) {
		Image img = null;
		try {
			img = ImageIO.read(new File(imgPath + nomFichier));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return img;
	}
	
	public abstract Image getImage();
	
	public abstract String toString();
}
