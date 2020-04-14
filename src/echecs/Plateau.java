package echecs;

import java.util.Arrays;

import pieces.Cavalier;
import pieces.Fou;
import pieces.Piece;
import pieces.Pion;
import pieces.Reine;
import pieces.Roi;
import pieces.Tour;

public class Plateau {
	private boolean tourJoueur;
	private Piece[][] echiquier;

	public boolean deplacer(int x1, int y1, int x2, int y2) {
		//CODE A IMPLEMENTER

		return true;
	}

	public boolean[][] calculerDeplacementsPiece(int xP, int yP) {
		boolean[][] deplacementsPlateau = new boolean[8][8]; // On crée la matrice retour
		for (boolean[] ligne : deplacementsPlateau) // On la remplit de false
			Arrays.fill(ligne, false); // Ligne par ligne

		// On récupère les différents déplacements possible de la pièce dont les "coordonnées" sont rentrées en paramètre
		boolean[][] deplacementsPiece = echiquier[xP][yP].getDeplacementsPoss(); 
		
		if (echiquier[xP][yP] instanceof Pion) {
			if (echiquier[xP][yP].getCouleur() && xP == 1)
				deplacementsPiece[9][7] = true;
			else if (!echiquier[xP][yP].getCouleur() && xP == 6)
				deplacementsPiece[5][7] = true;
		}

		if (echiquier[xP][yP] instanceof Cavalier) {
			for (int i = 0; i < deplacementsPiece.length; i++) {
				for (int j = 0; j < deplacementsPiece[0].length; j++) {
					if (deplacementsPiece[i][j]) { //Pour chaque déplacement
						int xDeplacementPiece = xP + i - 7;
						int yDeplacementPiece = yP + j - 7;

						if (xDeplacementPiece >= 0 && xDeplacementPiece <= 7 && yDeplacementPiece >= 0 && yDeplacementPiece <= 7) {
							if (echiquier[xDeplacementPiece][yDeplacementPiece] == null)
								deplacementsPlateau[xDeplacementPiece][yDeplacementPiece] = true;
							else if (echiquier[xDeplacementPiece][yDeplacementPiece].getCouleur() != echiquier[xP][yP].getCouleur())
								deplacementsPlateau[xDeplacementPiece][yDeplacementPiece] = true;
						}
					}
				}
			}
		} else {
			// Calcul de la matrice retour
			for (int i = 0; i < deplacementsPiece.length; i++) {
				for (int j = 0; j < deplacementsPiece[0].length; j++) {
					if (deplacementsPiece[i][j]) { //Pour chaque déplacement maximal
						// Calcul du déplacement maximal absolu (par rapport à (0;0))
						int xDeplacementPiece = i-7;
						int yDeplacementPiece = j-7;

						// Calcul de la distance du déplacement unitaire (1, -1 ou 0)
						int xShift = (xDeplacementPiece == 0)?0 : (xDeplacementPiece/(Math.abs(xDeplacementPiece)));
						int yShift = (yDeplacementPiece == 0)?0 : (yDeplacementPiece/(Math.abs(yDeplacementPiece)));

						//Calcul des premiers déplacements à vérifier
						int x = xP + xShift;
						int y = yP + yShift;

						while ( //Tant que :
								(x >= 0 && x <= 7 && y >= 0 && y <= 7) &&  // On reste sur l'échiquier et qu'
								// On ne dépasse pas le déplacement maximal
								(Math.abs(x - xP) <= Math.abs(xDeplacementPiece) && (Math.abs(y - yP) <= Math.abs(yDeplacementPiece))) 
								) {
							if (echiquier[xP][yP] instanceof Pion) {
								if (echiquier[x][y] == null) {
									if (y == yP) {
										deplacementsPlateau[x][y] = true;
										x += xShift;
									} else {
										x = 8;
										y = 8;
									}
								} else {
									if (y != yP)
										if (echiquier[x][y].getCouleur() != echiquier[xP][yP].getCouleur())
											deplacementsPlateau[x][y] = true;
									x = 8;
									y = 8;
								}
							} else {
								if (echiquier[x][y] == null) { // S'il n'y a rien dans la case où aller
									deplacementsPlateau[x][y] = true; // Le déplacement unitaire est possible

									// On passe au déplacement unitaire suivant (s'il existe)
									x += xShift;
									y += yShift;
								} else { // Sinon
									// S'il y a une pièce de la couleur adverse, alors on peut tout de même y aller
									if (echiquier[x][y].getCouleur() != echiquier[xP][yP].getCouleur())
										deplacementsPlateau[x][y] = true;

									// On a fini de calculer les déplacements pour le déplacement maximal courant
									x = 8;
									y = 8;
								} // Fin else
							}
						} // Fin while
					} // Fin if (deplacements...)
				} // Fin for int j
			} //Fin for int i
		}

		// On retourne la matrice des déplacements possible de la pièce sur le plateau
		return deplacementsPlateau;
	} //Fin méthode

	public boolean verifEchec() {
		//CODE A IMPLEMENTER

		return true;
	}

	public boolean verifMat() {
		//CODE A IMPLEMENTER

		return true;
	}

	@Override
	public String toString() {
		String retour="";
		for(int i=0; i<echiquier.length; i++) {
			retour += 8-i+" ";
			for(int j=0; j<echiquier.length; j++) {
				if(echiquier[i][j] != null) {
					retour+=echiquier[i][j].toString() + ", ";
				}
				else {
					if(j==7) {
						retour += "V";
					}
					else {
						retour += "V, ";
					}

				}

			}
			retour += "\n";
		}
		retour += "  A  B  C  D  E  F  G  H";
		return retour;
	}

	public void setEchiquier(Piece[][] echiquier) {
		this.echiquier = echiquier;
	}

	public String toString2() {
		String toReturn = "";
		for (int i = 0; i < echiquier.length; i++) {
			toReturn += 8 - i + "\t";
			for (int j = 0; j < echiquier[i].length; j++) {
				if (echiquier[i][j] != null) {
					toReturn += echiquier[i][j].toString() + "\t";
				} else {
					toReturn += "V\t";
				}
			}
			toReturn += "\n\n";
		}
		toReturn += "\n\tA\tB\tC\tD\tE\tF\tG\tH";
		return toReturn;
	}

	public static void main(String[] args) {
		Piece[][] echiquier1 = {
				{new Reine(false), null, null, new Tour(false), null, null, null, new Fou(false)},
                {null, null, null, null, null, new Cavalier(false), null, new Roi(true)},
                {null, null, null, null, null, null, new Pion(true), new Pion(false)},
                {null, null, null, null, null, null, new Pion(false), null},
                {null, null, null, null, null, null, null, null},
                {null, new Pion(true), null, null, null, null, null, null},
                {null, new Pion(true), null, null, null, null, null, null},
                {null, new Roi(false), null, null, null, null, null, null}
		};

		Plateau plat = new Plateau();
		plat.setEchiquier(echiquier1);

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (echiquier1[i][j] != null) {
					System.out.println(plat.toString2());
					System.out.println("\nPiece : " + echiquier1[i][j] + "(" + i + ";" + j + ")");
					boolean[][] dep = plat.calculerDeplacementsPiece(i, j);
					for (int k = 0; k < 8; k++) {
						for (int l = 0; l < 8; l++) {
							if (k == i && l == j)
								System.out.print("p ");
							else if (dep[k][l])
								System.out.print("O ");
							else
								System.out.print("- ");
						}
						System.out.println();
					}
				}
			}
		}
	}
}
