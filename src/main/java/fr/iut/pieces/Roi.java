package fr.iut.pieces;

public class Roi extends Piece {
	public Roi(boolean couleur) {
		super(couleur);
		setDeplacementsPossIndex(6,6, true);
		setDeplacementsPossIndex(6,7, true);
		setDeplacementsPossIndex(6,8, true);
		setDeplacementsPossIndex(7,6, true);
		setDeplacementsPossIndex(7,8, true);
		setDeplacementsPossIndex(8,6, true);
		setDeplacementsPossIndex(8,7, true);
		setDeplacementsPossIndex(8,8, true);
	}
	
	public String toString() {
        if(getCouleur())
            return"RoN";
        else
            return"RoB";
    }
}
