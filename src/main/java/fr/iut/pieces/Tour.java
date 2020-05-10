package fr.iut.pieces;

import java.awt.Image;

public class Tour extends Piece {
	private static Image imgB = loadImage("TourBlanche.png");
	private static Image imgN = loadImage("TourNoir.png");
	
	public Tour(boolean couleur) {
		super(couleur);
		setDeplacementsPossIndex(0, 7, true);
		setDeplacementsPossIndex(7,0, true);
		setDeplacementsPossIndex(7,14, true);
		setDeplacementsPossIndex(14,7, true);
	}
	
	@Override
	public String toString() {
        if(getCouleur())
            return"Tn";
        else
            return"Tb";
    }
	
	@Override
	public Image getImage() {
		if(getCouleur())
			return imgB;
		else
			return imgN;
	}
}