package fr.iut;

import java.util.Scanner;

import fr.iut.gestionpartie.GestionnairePartie;
import fr.iut.interfacegraphique.Fenetre;
import fr.iut.plateau.Plateau;

public class ChessMain {
	private static Scanner sc = new Scanner(System.in); // On crée un scanner pour récupérer les entrées de l'utilisateur
	private static Plateau p = new Plateau(); // On charge le plateau
	private static GestionnairePartie gp = new GestionnairePartie(p); // On initialise un fichier avec le plateau
	private static String erreur = "";
	private static boolean recommencer = true;
	
	// Pour l'interface graphique
	private static Fenetre f = null;

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

	public static int[] convertChaine(String entree) {
		char[] tabEntree = entree.toLowerCase().toCharArray();
		return new int[] {8-(tabEntree[1]-'0'), tabEntree[0]-'a', 8-(tabEntree[4]-'0'), tabEntree[3]-'a'};
	}
	
	public static String getMessageDebutTour() {
		StringBuilder retour = new StringBuilder();
		retour.append("\n\n\n\n\n\n");

		retour.append("\n\n"+p.toString()+"\n");
		retour.append(erreur);
		
		retour.append("\nC'est au tour du joueur " + (p.getTourJoueur() ? "noir." : "blanc.") + "\n");
		
		if (p.verifEchec() != null)
			retour.append("Le roi " + (p.getTourJoueur() ? "noir" : "blanc") + " est en échec.\n");
		
		return retour.toString();
	}

	public static void initialisation() {
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
					if(entree2.equals("cancel") || entree2.equals("retour")) {
						entree1Valide = false;
					} else {
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
	}

	public static void jouerTour() {
		boolean entreeValide = false;
		
		while(!entreeValide) {
			System.out.print(getMessageDebutTour());
			
			System.out.print("\nVeuillez entrer votre déplacement : ");
			
			String deplacement = sc.nextLine();

			if(deplacement.contains("sauvegarder")) {
				sauvegarder(deplacement);
			} else if(deplacement.contains("quitter")) {
				quitter(deplacement);
			} else {
				if(verifSyntaxe(deplacement)) {
					try {
						p.deplacer(convertChaine(deplacement));
						erreur = "";
						entreeValide = true;
					} catch(Exception e) {
						erreur = "Erreur : "+e.getMessage() + "\n";
					}
				} else {
					erreur = "Erreur : Syntaxe invalide.\n";
				}
			}
		}
	}
	
	public static void quitter(String entree) {
		try {
			if(entree.length() <= 7 ) { //si le mot est juste quitter
				gp.sauvegarderPartie();
				System.exit(0);
			} else {
				gp.sauvegarderPartie(entree.substring(8)); // créer un fichier a partir des caractères du 8ieme caractère
				System.exit(0);
			}
		} catch (Exception e) {
			erreur = "Erreur : " + e.getMessage() + "\n";
		}
	}

	public static void sauvegarder(String entree) {
		try {
			if(entree.length() <= 11 ) { //si le mot est juste sauvegarder
				erreur = gp.sauvegarderPartie();
			} else {
				erreur = gp.sauvegarderPartie(entree.substring(12)); // créer un fichier a partir des caractères du 12ieme caractère
			}
		} catch (Exception e) {
			erreur = "Erreur : " + e.getMessage() + "\n";
		}

	}
	
	public static void partie() {
		initialisation();

		System.out.println("\nLa partie commence !");

		while(!p.verifMat()) {
			jouerTour();
		}
		
		System.out.println("Félicitations, les " + (p.getTourJoueur()?"noirs":"blancs") + " ont gagné !");
	}
	
	public static void demanderRecommencer() {
		
	}
	
	public static void mainConsole() {
		System.out.println("Bienvenue dans le jeu d'échec.");
		
		while (recommencer) {
			partie();
			demanderRecommencer();
		}

		sc.close();
	}
	
	public static void mainGraphique() {
		f = new Fenetre(gp, p);
	}

	public static void main(String[] args) {
		mainConsole();
	}
}