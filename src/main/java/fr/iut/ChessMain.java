package fr.iut;

import java.util.Scanner;

import fr.iut.gestionpartie.GestionnairePartie;
import fr.iut.plateau.Plateau;

public class ChessMain {
	private static Plateau p = new Plateau(); // On charge le plateau
	private static GestionnairePartie gp = new GestionnairePartie(p); // On initialise un fichier avec le plateau

	public static boolean verifSyntaxe(String entree) {
		if(entree.length() != 5)
			return false;

		char[] tabEntree = entree.toUpperCase().toCharArray();

		if( (tabEntree[2] != ' ') || 
				(tabEntree[0] < 'A' || tabEntree[0] > 'H') || 
				(tabEntree[1]<'1' || tabEntree[1]>'8') || 
				(tabEntree[3]<'A' || tabEntree[3]>'H') || 
				(tabEntree[4]<'1' || tabEntree[4]>'8') )
			return false;

		return true;
	}

	public static int[] convertChaine(String entree) {
		char[] tabEntree = entree.toLowerCase().toCharArray();
		return new int[] {tabEntree[0]-'a', 8-(tabEntree[1]-'0'), tabEntree[3]-'a', 8-(tabEntree[4]-'0')};
	}

	public static void initialisation() {

		System.out.println("Bienvenue dans le jeu d'échec.");

		Scanner sc = new Scanner(System.in); // On implémente un scanner pour demander ce que veut l'utilisateur

		boolean entree1Valide = false; // Sert à vérifier la validité du message saisi

		while(!entree1Valide) {
			entree1Valide = true;

			System.out.println("\nVoulez vous démarrer une nouvelle partie ou bien charger une ancienne partie ?"); 
			System.out.println("\t 1 - Démarrer une nouvelle partie");
			System.out.println("\t 2 - Charger une ancienne partie");

			System.out.print("\nVeuillez saisir 1 ou 2 : ");
			String entree1 = sc.nextLine(); // On demande la proposition

			switch (entree1) {
			case "1": 
				try {
					gp.nouvellePartie(); // On charge un fichier qui se nomme : "nouvellePartie.csv"
				} catch(Exception e) {
					System.out.println("Erreur : " + e.getMessage());
					entree1Valide=false; // Si on a une erreur IOException, on initialise le boolean a false.
				}
				break;

			case "2":
				boolean entree2Valide = false;

				while(!entree2Valide) {
					System.out.print("\nVeuillez saisir le nom du fichier de partie (ex : \"maPartie.csv\"), entrez \"cancel\" pour revenir en arrière : ");
					String entree2 = sc.nextLine(); // Un scan pour demander le nom du fichier
					entree2Valide = true; // 
					if(entree2.equals("cancel"))
						entree1Valide = false;
					else {
						try {
							gp.chargerAnciennePartie(entree2); // On charger le nom du fichier entrée
						} catch(Exception e) {
							System.out.println("Erreur : " + e.getMessage());
							entree2Valide = false; // Si on a une erreur IOException, on initialise le boolean a false.
						}
					}
				}
				break;

			default:
				System.out.println("Entrée invalide, veuillez recommencer."); // Si le premier scan ne contient pas l'une des 2 propositions, on demande à l'utilisateur de ressaisir 
				entree1Valide = false;
				break;
			}
		}

		sc.close();
	}

	public static void main(String[] args) {
		initialisation();

		Scanner sc = new Scanner(System.in); // On implémente un scanner pour demander ce que veut l'utilisateur

		System.out.println("\n\n\n\n\n" + p);
		System.out.println("\nLa partie commence !"); // On affiche le plateau pour commencer la partie.

		while(!p.verifMat()) {
			System.out.print("\n\n\n\n\nC'est au tour des ");

			if(p.getTourJoueur())
				System.out.println("blancs");
			else
				System.out.println("noirs");


			String deplacement = "";
			boolean entreeValide = false;
			while(!entreeValide) {
				System.out.println("Veuillez entrer votre déplacement :");
				deplacement = sc.nextLine();
				if(verifSyntaxe(deplacement)) {
					try {
						p.deplacer(convertChaine(deplacement));
						entreeValide = true;
					} catch(Exception e) {
						System.out.println("Erreur "+e.getMessage());
					}
				} else {
					System.out.println("Erreur : Syntaxe invalide");
				}
			}
		}

		sc.close();
	}
}