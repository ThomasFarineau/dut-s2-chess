package fr.iut;

import java.util.Scanner;

import javax.swing.UIManager;

import fr.iut.fonctions.Fonctions;
import fr.iut.gestionpartie.GestionnairePartie;
import fr.iut.interfacegraphique.Fenetre;
import fr.iut.plateau.Plateau;

public class ChessMain {
	private static Scanner sc = new Scanner(System.in); // On crée un scanner pour récupérer les entrées de l'utilisateur
	private static Plateau p = new Plateau(); // On charge le plateau
	private static GestionnairePartie gp = new GestionnairePartie(p); // On initialise un fichier avec le plateau
	private static String alerte = "";
	private static boolean recommencer = true;

	// Pour l'interface graphique
	private static Fenetre f = null;

	public static String getMessageDebutTour() {
		StringBuilder retour = new StringBuilder();
		retour.append("\n\n\n\n\n\n");

		retour.append("\n\n"+p.toString()+"\n\n");
		
		retour.append("Pour sauvegarder votre partie, entrez \"sauvegarder (ficherSauvegarde)\".\n");
		retour.append("Pour quitter le jeu, entrez \"quitter\".\n");
		retour.append(alerte + "\n");

		retour.append("\nC'est au tour du joueur " + (p.getTourJoueur() ? "noir." : "blanc.") + "\n");

		if (p.verifEchec() != null)
			retour.append("Le roi " + (p.getTourJoueur() ? "noir" : "blanc") + " est en échec.\n");
		
		retour.append("\nVeuillez entrer votre déplacement : ");

		return retour.toString();
	}

	public static void initialisationConsole() {
		boolean entree1Valide = false; // Sert à vérifier la validité du message saisi

		while(!entree1Valide) {
			entree1Valide = true;

			System.out.println("\nVoulez vous démarrer une nouvelle partie ou bien charger une ancienne partie ?"); 
			System.out.println("\t 1 - Démarrer une nouvelle partie");
			System.out.println("\t 2 - Charger une ancienne partie");

			System.out.print("\nVeuillez saisir votre choix : ");
			String entree1 = sc.nextLine(); // On demande la proposition

			switch (entree1.toLowerCase()) {
			case "1":
			case "nouvelle":
			case "demarrer":
				try {
					gp.nouvellePartie(); // On charge un fichier qui se nomme : "nouvellePartie.csv"
				} catch(Exception e) {
					System.out.println("Erreur : " + e.getMessage());
					entree1Valide=false; // Si on a une erreur IOException, on initialise le boolean a false.
				}
				break;

			case "2":
			case "charger":
				boolean entree2Valide = false;

				while(!entree2Valide) {
					System.out.print("\nVeuillez saisir le nom du fichier de partie (ex : \"maPartie.csv\"), entrez \"cancel\" pour revenir en arrière : ");
					String entree2 = sc.nextLine(); // Un scan pour demander le nom du fichier
					entree2Valide = true; // L'entrée est supposée valide
					if(entree2.toLowerCase().equals("cancel") || entree2.toLowerCase().equals("retour")) {
						entree1Valide = false;
					} else {
						try {
							gp.chargerAnciennePartie(entree2); // On charge le nom du fichier en entrée
						} catch(Exception e) {
							System.out.println("Erreur : " + e.getMessage());
							entree2Valide = false; // Si on a une Exception, l'entrée n'est pas validée
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
			String entree = sc.nextLine();

			if(entree.toLowerCase().equals("sauvegarder")) {
				try {
					alerte = gp.sauvegarderPartie();
				} catch (Exception e) {
					alerte = "Erreur : " + e.getMessage();
				}
			} else if (entree.toLowerCase().startsWith("sauvegarder ") && entree.length() >= 13) {
				try {
					alerte = gp.sauvegarderPartie(entree.substring(12));
				} catch (Exception e) {
					alerte = "Erreur : " + e.getMessage();
				}	
			} else if(entree.toLowerCase().equals("quitter")) {
				sc.close();
				System.out.println("Au revoir !");
				System.exit(0);
			} else {
				if(Fonctions.verifSyntaxe(entree)) {
					try {
						p.deplacer(Fonctions.convertEnIndices(entree));
						alerte = "";
						entreeValide = true;
					} catch(Exception e) {
						alerte = "Erreur : " + e.getMessage();
					}
				} else {
					alerte = "Erreur : Syntaxe de déplacement invalide.";
				}
			}
		}
	}

	public static void partieConsole() {
		initialisationConsole();

		alerte = "La partie vient de commencer !";

		while(!p.verifMat()) {
			jouerTour();
		}
		
		System.out.println("\n" + p);
		System.out.println("\nFélicitations, les " + (p.getTourJoueur()?"blancs":"noirs") + " ont gagné !\n");
	}

	public static boolean demanderRecommencer() {
		while (true) {
			System.out.print("Voulez vous jouer une autre partie ? (Oui/Non) : ");

			String reponse = sc.nextLine().toUpperCase();
			switch (reponse) {
			case "OUI":
			case "O":
				return true;
			case "NON":
			case "N":
				return false;
			default:
				System.out.println("Réponse invalide, veuillez recommencer.");
			}
		}
	}

	public static void mainConsole() {
		System.out.println("Bienvenue dans le jeu d'échec.");

		while (recommencer) {
			partieConsole();
			recommencer = demanderRecommencer();
		}
		
		System.out.println("Au revoir !");
		sc.close();
	}

	public static void mainGraphique() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Erreur lors du chargement de l'UI du système.");
		}
		
		f = new Fenetre(gp, p);
	}
	
	public static void initialisationModeDeJeu() {
		boolean entreeValide = false;
		
		while(!entreeValide) {
			System.out.println("Voulez vous jouer en mode console ou bien en mode graphique ?"); 
			System.out.println("\t 1 - Mode Console");
			System.out.println("\t 2 - Mode Graphique");

			System.out.print("\nVeuillez saisir votre choix : ");
			String entree1 = sc.nextLine();

			switch (entree1.toLowerCase()) {
			case "1":
			case "mode console":
			case "console":
				System.out.println("Vous avez décidé de jouer en mode Console.\n\n");
				mainConsole();
				entreeValide = true;
				break;
				
			case "2":
			case "mode graphique":
			case "graphique":
				System.out.println("Vous avez décidé de jouer en mode Graphique.\n\n");
				mainGraphique();
				entreeValide = true;
				break;

			default:
				System.out.println("Entrée invalide, veuillez recommencer.\n"); 
				break;
			}
		}
	}

	public static void main(String[] args) {
		initialisationModeDeJeu();
	}
}