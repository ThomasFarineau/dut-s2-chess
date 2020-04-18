package fr.iut;

import java.util.Scanner;

import fr.iut.gestionpartie.GestionnairePartie;
import fr.iut.plateau.Plateau;

public class ChessMain {
	private static Plateau p = new Plateau(); // On charge le plateau
	private static GestionnairePartie gp = new GestionnairePartie(p); // On initialise un fichier avec le plateau
	
	public static boolean verifSyntaxe(String entree) {
		if(entree.length()!=5) {
			return false;
		}
		char[] tab = entree.toUpperCase().toCharArray();
		if(tab[2]!=' ' || tab[0]<'A' || tab[0]>'H' || tab[1]<'1' || tab[1]>'8' || tab[3]<'A' || tab[3]>'H' || tab[4]<'1' || tab[4]>'8') {
			return false;
		}
		return true;
	}
	
	public static int[] convertChaine(String entree) {
		char[] tab = entree.toLowerCase().toCharArray();
		return new int[] {tab[0]-'a', 8-(tab[1]-'0'), tab[3]-'a', 8-(tab[4]-'0')};
	}
	
	public static void initialisation() {
		
        System.out.println("Bienvenue dans le jeu d'�chec");
        
        Scanner sc = new Scanner(System.in); // On impl�mente un scanner pour demander ce que veut l'utilisateur
        System.out.println("Choisissez une des deux propositions :"); 
        System.out.println("\t 1 - Nouvelle partie");
        System.out.println("\t 2 - Charger une partie");
       
        boolean entree1Valide = false; // Sert � v�rifier la validit� du message saisi
        
        while(!entree1Valide) {
        	entree1Valide=true;
        	System.out.println("Choisissez une des deux propositions :"); 
            System.out.println("\t 1 - Nouvelle partie");
            System.out.println("\t 2 - Charger une partie");
        	System.out.println("\nProposition :");
        	String entree1 = sc.nextLine(); // On demande la proposition
            
            switch (entree1) {
    		case "1": 
    			try {
    				gp.nouvellePartie(); // On charge un fichier qui se nomme : "nouvellePartie.csv"
    			}catch(Exception e) {
    				System.out.println("Erreur : "+e.getMessage());
    				entree1Valide=false; // Si on a une erreur IOException, on initialise le boolean a false.
    			}
    			break;
    			
    		case "2":
	    		boolean entree2Valide = false;
	    		
	    		while(!entree2Valide) {
	    			System.out.println("Veuillez saisir le nom du fichier (Entrer cancel pour revenir en arri�re) : ");
	    			String entree2 = sc.nextLine(); // Un scan pour demander le nom du fichier
	    			entree2Valide = true; // 
	    			if(entree2.equals("cancel"))
	    				entree1Valide = false;
	    			else {
	    				try {
	    					gp.chargerAnciennePartie(entree2); // On charger le nom du fichier entr�e
	    				}catch(Exception e) {
	    					System.out.println("Erreur : "+e.getMessage());
	    					entree2Valide=false; // Si on a une erreur IOException, on initialise le boolean a false.
	    				}
	    			}
	    		}
    			break;
    		default:
    			System.out.println("Entr�e invalide, veuillez recommencer"); // Si le premier scan ne contient pas l'une des 2 propositions, on demande � l'utilisateur de ressaisir 
    			entree1Valide=false;
    			break;
    		}
        }
        sc.close();
	}
	
    public static void main(String[] args) {
    	
        initialisation();
    	
        Scanner sc = new Scanner(System.in); // On impl�mente un scanner pour demander ce que veut l'utilisateur
        
        System.out.println("\n\n\n\n\n"+p);
        System.out.println("\nLa partie commence !"); // On affiche le plateau pour commencer la partie.
        
        while(!p.verifMat()){
        	System.out.print("\n\n\n\n\nC'est au tour des ");
        	if(p.getTourJoueur()) {
        		System.out.println("blancs");
        	}
        	else {
        		System.out.println("noirs");
        	}
        	String deplacement="";
        	boolean entreeValide=false;
        	while(!entreeValide) {
        		System.out.println("Veuillez entrer votre d�placement :");
        		deplacement=sc.nextLine();
        		if(verifSyntaxe(deplacement)) {
        			try {
            			p.deplacer(convertChaine(deplacement));
            			entreeValide=true;
            		}catch(Exception e) {
            			System.out.println("Erreur "+e.getMessage());
            		}
        		}
        		else {
        			System.out.println("Erreur : Syntaxe invalide");
        		}
        	}
        }
        sc.close();
    }
}