package fr.iut.pieces;

public class Cavalier extends Piece {
	public Cavalier(boolean couleur) {
		super(couleur);
		setDeplacementsPossIndex(5,6, true);
		setDeplacementsPossIndex(6,5, true);
		setDeplacementsPossIndex(5,8, true);
		setDeplacementsPossIndex(6,9, true);
		setDeplacementsPossIndex(8,5, true);
		setDeplacementsPossIndex(9,6, true);
		setDeplacementsPossIndex(9,8, true);
		setDeplacementsPossIndex(8,9, true);
	}

	@Override
	public String toString() {
		if(getCouleur())
			return"Cn";
		else
			return"Cb";
	}
}
