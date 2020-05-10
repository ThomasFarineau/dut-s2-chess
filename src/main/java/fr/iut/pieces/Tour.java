package fr.iut.pieces;

public class Tour extends Piece {
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
}