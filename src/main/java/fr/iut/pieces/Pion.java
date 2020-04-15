package fr.iut.pieces;

public class Pion extends Piece {
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
	
	public String toString() {
        if(getCouleur())
            return"Pn";
        else
            return"Pb";
    }
}
