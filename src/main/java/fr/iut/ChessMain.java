package fr.iut;

import java.util.Scanner;

import javax.swing.UIManager;

import fr.iut.fonctions.Fonctions;
import fr.iut.gestionpartie.GestionnairePartie;
import fr.iut.interfacegraphique.Fenetre;
import fr.iut.plateau.Plateau;

public class ChessMain {
    private static Scanner sc = new Scanner(System.in); // On cr�e un scanner pour r�cup�rer les entr�es de l'utilisateur
    private static Plateau p = new Plateau(); // On charge le plateau
    private static GestionnairePartie gp = new GestionnairePartie(p); // On initialise un fichier avec le plateau
    private static String alerte = "";
    private static boolean quitter = false;
    
    private ChessMain() { }

    // M�thode utilis�e pour les tests JUnit
    public static void reInitVars() {
        sc.close();

        sc = new Scanner(System.in);
        p = new Plateau();
        gp = new GestionnairePartie(p);
        alerte = "";
        quitter = false;
    }

    public static boolean demanderTourJoueur() {
        while (true) {
            System.out.print("Qui va commencer � jouer, � la reprise de la partie charg�e ? (Blanc/Noir) : ");

            String reponse = sc.nextLine().toUpperCase();
            switch (reponse) {
                case "B":
                case "BLANC":
                case "BLANCS":
                    return false;
                case "N":
                case "NOIR":
                case "NOIRS":
                    return true;
                default:
                    System.out.println("R�ponse invalide, veuillez recommencer.");
            }
        }
    }

    public static void initialisationConsole() {
        boolean entree1Valide = false; // Sert � v�rifier la validit� du message saisi

        while (!entree1Valide) {
            entree1Valide = true;

            System.out.println("\nVoulez-vous d�marrer une nouvelle partie ou bien charger une ancienne partie ?");
            System.out.println("\t 1 - D�marrer une nouvelle partie");
            System.out.println("\t 2 - Charger une ancienne partie");

            System.out.print("\nVeuillez saisir votre choix : ");
            String entree1 = sc.nextLine(); // On demande la proposition

            switch (entree1.toLowerCase()) {
                case "1":
                case "nouvelle":
                case "demarrer":
                    try {
                        gp.nouvellePartie(); // On charge un fichier qui se nomme : "nouvellePartie.csv"
                        p.setTourJoueur(false);
                        alerte = "La partie vient de commencer !";
                    } catch (Exception e) {
                        System.out.println("Erreur : " + e.getMessage());
                        entree1Valide = false; // Si on a une erreur IOException, on initialise le boolean a false.
                    }
                    break;

                case "2":
                case "charger":
                    boolean entree2Valide = false;

                    while (!entree2Valide) {
                        System.out.print("\nVeuillez saisir le nom du fichier de partie (ex : \"maPartie.csv\"), entrez \"retour\" pour revenir en arri�re : ");
                        String entree2 = sc.nextLine(); // Un scan pour demander le nom du fichier
                        entree2Valide = true; // L'entr�e est suppos�e valide
                        if (entree2.toLowerCase().equals("retour")) {
                            entree1Valide = false;
                        } else {
                            try {
                                gp.chargerAnciennePartie(entree2); // On charge le nom du fichier en entr�e
                                p.setTourJoueur(demanderTourJoueur());
                                alerte = "La partie a �t� charg�e !";
                            } catch (Exception e) {
                                System.out.println("Erreur : " + e.getMessage());
                                entree2Valide = false; // Si on a une Exception, l'entr�e n'est pas valid�e
                            }
                        }
                    }
                    break;

                default:
                    System.out.println("Entr�e invalide, veuillez recommencer."); // Si le premier scan ne contient pas l'une des 2 propositions, on demande � l'utilisateur de ressaisir
                    entree1Valide = false;
                    break;
            }
        }
    }

    public static String getMessageDebutTour() {
        StringBuilder retour = new StringBuilder();
        retour.append("\n\n\n\n\n\n");

        retour.append("\n\n").append(p.toString()).append("\n\n");

        retour.append("Pour sauvegarder votre partie, entrez \"sauvegarder (fichierSauvegarde)\".\n");
        retour.append("Pour quitter le jeu, entrez \"quitter\".\n");
        retour.append(alerte).append("\n");

        retour.append("\nC'est au tour du joueur ").append(p.getTourJoueur() ? "noir." : "blanc.").append("\n");

        if (p.verifEchec() != null)
            retour.append("Le roi ").append(p.getTourJoueur() ? "noir" : "blanc").append(" est en �chec.\n");

        retour.append("\nVeuillez entrer votre d�placement : ");

        return retour.toString();
    }
    
    public static void jouerTour() {
        boolean entreeValide = false;

        while (!entreeValide) {
            System.out.print(getMessageDebutTour());
            String entree = sc.nextLine();

            if (entree.toLowerCase().equals("sauvegarder")) {
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
            } else if (entree.toLowerCase().equals("quitter")) {
                quitter = true;
                entreeValide = true;
            } else {
                if (Fonctions.verifSyntaxe(entree)) {
                    try {
                        p.deplacer(Fonctions.convertEnIndices(entree));
                        alerte = "";
                        entreeValide = true;
                    } catch (Exception e) {
                        alerte = "Erreur : " + e.getMessage();
                    }
                } else {
                    alerte = "Erreur : Syntaxe de d�placement invalide.";
                }
            }
        }
    }
    
    public static void partieConsole() {
        initialisationConsole();

        while (!(p.verifMat() || quitter)) {
            jouerTour();
        }

        if (!quitter) {
            System.out.println("\n" + p);
            System.out.println("\nF�licitations, les " + (p.getTourJoueur() ? "blancs" : "noirs") + " ont gagn� !\n");
        }
    }
    
    public static boolean demanderRecommencer() {
        while (!quitter) {
            System.out.print("Voulez-vous jouer une autre partie ? (Oui/Non) : ");

            String reponse = sc.nextLine().toUpperCase();
            switch (reponse) {
                case "OUI":
                case "O":
                    return true;
                case "NON":
                case "N":
                    return false;
                default:
                    System.out.println("R�ponse invalide, veuillez recommencer.");
            }
        }

        return false;
    }
    
    public static void mainConsole() {
        System.out.println("Bienvenue dans le jeu d'�chec en mode Console.");

        do {
            partieConsole();
            alerte = "";
        } while (demanderRecommencer());

        System.out.println("Au revoir !");
        sc.close();
    }

    public static void mainGraphique() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement de l'UI du syst�me.");
        }

        new Fenetre(gp, p);
    }

    public static void initialisationModeDeJeu() {
        boolean entreeValide = false;

        while (!entreeValide) {
            System.out.println("Voulez-vous jouer en mode console ou bien en mode graphique ?");
            System.out.println("\t 1 - Mode Console");
            System.out.println("\t 2 - Mode Graphique");

            System.out.print("\nVeuillez saisir votre choix : ");
            String entree1 = sc.nextLine();

            switch (entree1.toLowerCase()) {
                case "1":
                case "mode console":
                case "console":
                    System.out.println("Vous avez d�cid� de jouer en mode Console.\n\n");
                    mainConsole();
                    entreeValide = true;
                    break;

                case "2":
                case "mode graphique":
                case "graphique":
                    System.out.println("Vous avez d�cid� de jouer en mode Graphique.\n\n");
                    mainGraphique();
                    entreeValide = true;
                    break;

                default:
                    System.out.println("Entr�e invalide, veuillez recommencer.\n");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        initialisationModeDeJeu();
    }
}