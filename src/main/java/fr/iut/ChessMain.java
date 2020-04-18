package fr.iut;

import java.util.Scanner;

import fr.iut.gestionpartie.GestionnairePartie;
import fr.iut.plateau.Plateau;

public class ChessMain {
    
    public static void main(String[] args) {
    	Plateau p = new Plateau(); // On charge le plateau
    	GestionnairePartie gp = new GestionnairePartie(p); // On initialise un fichier avec le plateau
        System.out.println("Bienvenue dans le jeu d'échec");
        
        Scanner sc = new Scanner(System.in); // On implémente un scanner pour demander ce que veut l'utilisateur
        System.out.println("Choisissez une des deux propositions :"); 
        System.out.println("\t 1 - Nouvelle partie");
        System.out.println("\t 2 - Charger une partie");
       
        boolean entreeValide = false; // Sert à vérifier la validité du message saisi
        
        while(!entreeValide) {
        	entreeValide=true;
        	System.out.println("\nProposition :");
        	String entree = sc.nextLine(); // On demande la proposition
            
            switch (entree) {
    		case "1": 
    			try {
    				gp.nouvellePartie(); // On charge un fichier qui se nomme : "nouvellePartie.csv"
    			}catch(Exception e) {
    				System.out.println("Erreur : "+e.getMessage());
    				entreeValide=false; // Si on a une erreur IOException, on initialise le boolean a false.
    			}
    			break;
    			
    		case "2":
	    		System.out.println("Veuillez saisir le nom du fichier :");
	    		String file = sc.nextLine(); // Un scan pour demander le nom du fichier
    			try {
    				gp.chargerAnciennePartie(file); // On charger le nom du fichier entrée
    			}catch(Exception e) {
    				System.out.println("Erreur : "+e.getMessage()); // En cas de problème de chargement de fichier, si le fichier existe..etc
    				entreeValide=false;
    			}
    			break;
    		default:
    			System.out.println("Entrée invalide, veuillez recommencer"); // Si le premier scan ne contient pas l'une des 2 propositions, on demande à l'utilisateur de ressaisir 
    			entreeValide=false;
    			break;
    		}
        }
        
        System.out.println("\n\n\n\n\n"+p);
        System.out.println("\nLa partie commence !"); // On affiche le plateau pour commencer la partie.
        
         {
        	System.out.println("\n\n\n\n\n"+p);
        	System.out.println("\nVeuillez saisir votre déplacement :");
            String deplacement=sc.nextLine();
        }
        
        
        sc.close();
    }
}