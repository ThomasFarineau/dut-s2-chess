package pieces;

public class Reine extends Piece {
	public Reine (boolean couleur) {
		super(couleur);
		setDeplacementsPossIndex(0,0, true);
		setDeplacementsPossIndex(14,0, true);
		setDeplacementsPossIndex(0,14, true);
		setDeplacementsPossIndex(14,14, true);
		setDeplacementsPossIndex(0, 7, true);
		setDeplacementsPossIndex(7,0, true);
		setDeplacementsPossIndex(7,14, true);
		setDeplacementsPossIndex(14,7, true);
	}

	public String toString() {
		if(getCouleur())
			return"ReN";
		else
			return"ReB";
	}
}