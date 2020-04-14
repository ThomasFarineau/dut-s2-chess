package pieces;

public class Fou extends Piece {
	
	public Fou(boolean couleur) {
		super(couleur);
		setDeplacementsPossIndex(0,0, true);
		setDeplacementsPossIndex(14,0, true);
		setDeplacementsPossIndex(0,14, true);
		setDeplacementsPossIndex(14,14, true);
	}
	 public String toString() {
	        if(getCouleur())
	            return"Fn";
	        else
	            return"Fb";
	    }
}