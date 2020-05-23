package fr.iut.fonctions;

import java.nio.file.Path;
import java.nio.file.Paths;

import fr.iut.gestionpartie.GestionnairePartie;

public class Fonctions {
	private Fonctions() {}

	// Vérifie qu'un String correspond à la syntaxe d'un déplacement
	public static boolean verifSyntaxe(String entree) {
		if(entree.length() != 5)
			return false;

		char[] tabEntree = entree.toUpperCase().toCharArray();

		return (tabEntree[2] == ' ') &&
				(tabEntree[0] >= 'A' && tabEntree[0] <= 'H') &&
				(tabEntree[1] >= '1' && tabEntree[1] <= '8') &&
				(tabEntree[3] >= 'A' && tabEntree[3] <= 'H') &&
				(tabEntree[4] >= '1' && tabEntree[4] <= '8');
	}

	// Convertit un déplacement en String en des indices en int
	public static int[] convertEnIndices(String entree) {
		char[] tabEntree = entree.toLowerCase().toCharArray();
		return new int[] {8-(tabEntree[1]-'0'), tabEntree[0]-'a', 8-(tabEntree[4]-'0'), tabEntree[3]-'a'};
	}

	// Convertit des indices en int, en déplacements en chiffres et lettres en char
	public static char[] convertEnCaracteres(int[] entree) {	
		return new char[] {
				(char)(entree[1] + 'A'),
				(char)((8 - entree[0]) + '0'),
				(char)(entree[3] + 'A'),
				(char)(8 - entree[2] + '0')
		};
	}

	public static String convertCheminRelatif(String cheminAbsolu) {
		Path cheminAbsoluFichier = Paths.get(cheminAbsolu);
		Path pathRelative = GestionnairePartie.getPartiesPath().relativize(cheminAbsoluFichier);

		return (pathRelative.toString()).replace('\\', '/');
	}
}
