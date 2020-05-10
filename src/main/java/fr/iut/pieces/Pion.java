package fr.iut.pieces;

import java.awt.Image;

public class Pion extends Piece {
	private static Image imgB = loadImage("PionBlanc.png");
	private static Image imgN = loadImage("PionNoir.png");
	
	public Pion(boolean couleur) {
		super(couleur);
		if (couleur) {
			this.setDeplacementsPossIndex(8, 7, true);
			this.setDeplacementsPossIndex(8, 6, true);
			this.setDeplacementsPossIndex(8, 8, true);
		} else {
			this.setDeplacementsPossIndex(6, 7, true);
			this.setDeplacementsPossIndex(6, 6, true);
			this.setDeplacementsPossIndex(6, 8, true);
		}
	}
	
	@Override
	public String toString() {
        if(getCouleur())
            return"Pn";
        else
            return"Pb";
    }
	@Override
	public Image getImage() {
		if(getCouleur())
			return imgN;
		else
			return imgB;
	}
}
