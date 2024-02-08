package fr.iut;

import java.util.Scanner;

import javax.swing.UIManager;

import fr.iut.fonctions.Utils;
import fr.iut.gestionpartie.GameManager;
import fr.iut.interfacegraphique.Window;
import fr.iut.plateau.Board;

public class ChessMain {
    private static Scanner sc = new Scanner(System.in); // On crée un scanner pour récupérer les entrées de l'utilisateur
    private static Board board = new Board(); // On charge le plateau
    private static GameManager gameManager = new GameManager(board); // On initialise un fichier avec le plateau
    private static String alert = "";
    private static boolean leave = false;
    
    private ChessMain() { }

    // Méthode utilisée pour les tests JUnit
    public static void reInitVars() {
        sc.close();

        sc = new Scanner(System.in);
        board = new Board();
        gameManager = new GameManager(board);
        alert = "";
        leave = false;
    }

    public static boolean askPlayerRound() {
        while (true) {
            System.out.print("Qui va commencer à jouer, à la reprise de la partie chargée ? (Blanc/Noir) : ");

            String response = sc.nextLine().toUpperCase();
            switch (response) {
                case "B":
                case "BLANC":
                case "BLANCS":
                    return false;
                case "N":
                case "NOIR":
                case "NOIRS":
                    return true;
                default:
                    System.out.println("Réponse invalide, veuillez recommencer.");
            }
        }
    }

    public static void initConsole() {
        boolean isFirstInputValid = false; // Sert à vérifier la validité du message saisi

        while (!isFirstInputValid) {
            isFirstInputValid = true;

            System.out.println("\nVoulez-vous démarrer une nouvelle partie ou bien charger une ancienne partie ?");
            System.out.println("\t 1 - Démarrer une nouvelle partie");
            System.out.println("\t 2 - Charger une ancienne partie");

            System.out.print("\nVeuillez saisir votre choix : ");
            String entree1 = sc.nextLine(); // On demande la proposition

            switch (entree1.toLowerCase()) {
                case "1":
                case "nouvelle":
                case "demarrer":
                    try {
                        gameManager.newGame(); // On charge un fichier qui se nomme : "newGame.csv"
                        board.setPlayerRound(false);
                        alert = "La partie vient de commencer !";
                    } catch (Exception e) {
                        System.out.println("Erreur : " + e.getMessage());
                        isFirstInputValid = false; // Si on a une erreur IOException, on initialise le boolean a false.
                    }
                    break;

                case "2":
                case "charger":
                    boolean isSecondInputValid = false;

                    while (!isSecondInputValid) {
                        System.out.print("\nVeuillez saisir le nom du fichier de partie (ex : \"maPartie.csv\"), entrez \"retour\" pour revenir en arrière : ");
                        String entree2 = sc.nextLine(); // Un scan pour demander le nom du fichier
                        isSecondInputValid = true; // L'entrée est supposée valide
                        if (entree2.equalsIgnoreCase("retour")) {
                            isFirstInputValid = false;
                        } else {
                            try {
                                gameManager.loadSavedGame(entree2); // On charge le nom du fichier en entrée
                                board.setPlayerRound(askPlayerRound());
                                alert = "La partie a été chargée !";
                            } catch (Exception e) {
                                System.out.println("Erreur : " + e.getMessage());
                                isSecondInputValid = false; // Si on a une Exception, l'entrée n'est pas validée
                            }
                        }
                    }
                    break;

                default:
                    System.out.println("Entrée invalide, veuillez recommencer."); // Si le premier scan ne contient pas l'une des 2 propositions, on demande à l'utilisateur de ressaisir
                    isFirstInputValid = false;
                    break;
            }
        }
    }

    public static String getStartMessage() {
        StringBuilder retour = new StringBuilder();
        retour.append("\n\n\n\n\n\n");

        retour.append("\n\n").append(board.toString()).append("\n\n");

        retour.append("Pour sauvegarder votre partie, entrez \"sauvegarder (fichierSauvegarde)\".\n");
        retour.append("Pour quitter le jeu, entrez \"quitter\".\n");
        retour.append(alert).append("\n");

        retour.append("\nC'est au tour du joueur ").append(board.getPlayerRound() ? "noir." : "blanc.").append("\n");

        if (board.checkCheck() != null)
            retour.append("Le roi ").append(board.getPlayerRound() ? "noir" : "blanc").append(" est en échec.\n");

        retour.append("\nVeuillez entrer votre déplacement : ");

        return retour.toString();
    }
    
    public static void playRound() {
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print(getStartMessage());
            String entree = sc.nextLine();

            if (entree.equalsIgnoreCase("sauvegarder")) {
                try {
                    alert = gameManager.saveGame();
                } catch (Exception e) {
                    alert = "Erreur : " + e.getMessage();
                }
            } else if (entree.toLowerCase().startsWith("sauvegarder ") && entree.length() >= 13) {
                try {
                    alert = gameManager.saveGame(entree.substring(12));
                } catch (Exception e) {
                    alert = "Erreur : " + e.getMessage();
                }
            } else if (entree.equalsIgnoreCase("quitter")) {
                leave = true;
                isValidInput = true;
            } else {
                if (Utils.checkSyntax(entree)) {
                    try {
                        board.move(Utils.convertIntoIndexes(entree));
                        alert = "";
                        isValidInput = true;
                    } catch (Exception e) {
                        alert = "Erreur : " + e.getMessage();
                    }
                } else {
                    alert = "Erreur : Syntaxe de déplacement invalide.";
                }
            }
        }
    }
    
    public static void partieConsole() {
        initConsole();

        while (!(board.checkMat() || leave)) {
            playRound();
        }

        if (!leave) {
            System.out.println("\n" + board);
            System.out.println("\nFélicitations, les " + (board.getPlayerRound() ? "blancs" : "noirs") + " ont gagné !\n");
        }
    }
    
    public static boolean demanderRecommencer() {
        while (!leave) {
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
                    System.out.println("Réponse invalide, veuillez recommencer.");
            }
        }

        return false;
    }
    
    public static void mainConsole() {
        System.out.println("Bienvenue dans le jeu d'échec en mode Console.");

        do {
            partieConsole();
            alert = "";
        } while (demanderRecommencer());

        System.out.println("Au revoir !");
        sc.close();
    }

    public static void mainGraphique() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement de l'UI du système.");
        }

        new Window(gameManager, board);
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