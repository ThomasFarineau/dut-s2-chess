package fr.iut.gestionpartie;

import fr.iut.pieces.*;
import fr.iut.plateau.Board;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GameManager {
    private final static Path gamesPath = Paths.get(System.getProperty("user.dir") + "/games");
    private final static String newGameName = "newGame.csv";
    private final Board board;
    private String fileName;

    public GameManager(Board board) {
        this.board = board;
    }

    public static Path getGamesPath() {
        return gamesPath;
    }

    private void loadGame(String nomFichier) throws IOException {
        Piece[][] chessboard = new Piece[8][8];
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(gamesPath + "//" + nomFichier));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Fichier introuvable : \"" + nomFichier + "\"");
        }


        for (int i = 0; i < chessboard.length; i++) {
            String line = br.readLine();

            String[] values;

            try {
                values = line.split(",");
            } catch (NullPointerException e) {
                br.close();
                throw new NullPointerException("Format du fichier \"" + nomFichier + "\" invalide");
            }

            if (values.length != 8) {
                br.close();
                throw new IOException("Ligne " + (i + 1) + " invalide dans le fichier \"" + nomFichier + "\"");
            }

            for (int j = 0; j < values.length; j++) {
                switch (values[j]) {
                    case "Tn":
                        chessboard[i][j] = new Rook(true);
                        break;
                    case "Tb":
                        chessboard[i][j] = new Rook(false);
                        break;
                    case "Cn":
                        chessboard[i][j] = new Knight(true);
                        break;
                    case "Cb":
                        chessboard[i][j] = new Knight(false);
                        break;
                    case "Fn":
                        chessboard[i][j] = new Bishop(true);
                        break;
                    case "Fb":
                        chessboard[i][j] = new Bishop(false);
                        break;
                    case "ReN":
                        chessboard[i][j] = new Queen(true);
                        break;
                    case "ReB":
                        chessboard[i][j] = new Queen(false);
                        break;
                    case "RoN":
                        chessboard[i][j] = new King(true);
                        break;
                    case "RoB":
                        chessboard[i][j] = new King(false);
                        break;
                    case "Pn":
                        chessboard[i][j] = new Pawn(true);
                        break;
                    case "Pb":
                        chessboard[i][j] = new Pawn(false);
                        break;
                    case "V":
                        break;
                    default:
                        throw new IOException("Nom de pi�ce invalide \"" + values[j] + "\" � la ligne " + (i + 1) + " du fichier \"" + nomFichier + "\"");
                }
            }
        }

        br.close();

        board.setChessboard(chessboard);
    }

    public void loadSavedGame(String fileName) throws Exception {
        if (!fileName.endsWith(".csv")) fileName += ".csv";

        if (fileName.toLowerCase().endsWith(newGameName.toLowerCase()))
            throw new Exception("Vous n'avez pas le droit de charger le fichier \"" + newGameName + "\".");

        this.fileName = fileName;
        loadGame(fileName);
    }

    public void newGame() throws IOException {
        fileName = "currentGame.csv";
        loadGame("newGame.csv");
    }

    public String saveGame() throws IOException {
        BufferedWriter bw;
        String retour = "";

        try {
            File f = new File(gamesPath + "//" + fileName);
            if (f.createNewFile()) {
                retour = "Le fichier \"" + fileName + "\" vient d'�tre cr��. ";
            }

            bw = new BufferedWriter(new FileWriter(f));

            Piece[][] chessboardToWrite = board.getChessboard();

            for (Piece[] pieces : chessboardToWrite) {
                for (int j = 0; j < pieces.length; j++) {
                    bw.write(((pieces[j] == null) ? "V" : pieces[j].toString()) + ((j == pieces.length - 1) ? "" : ","));
                }

                bw.write("\n");
            }

            bw.close();
        } catch (IOException e) {
            throw new IOException("Erreur lors de la sauvegarde du fichier");
        }

        retour += "La sauvegarde vers \"" + fileName + "\" a �t� effectu�e avec succ�s !";

        return retour;
    }

    public String saveGame(String nomFichier) throws Exception {
        if (!nomFichier.endsWith(".csv")) nomFichier += ".csv";

        if (nomFichier.toLowerCase().endsWith(newGameName.toLowerCase()))
            throw new Exception("Vous n'avez pas le droit de sauvegarder une partie de nom \"" + newGameName + "\".");

        this.fileName = nomFichier;
        return saveGame();
    }

    public String getFileName() {
        return fileName;
    }

    public void setPlayerRound(boolean tour) {
        board.setPlayerRound(tour);
    }
}
