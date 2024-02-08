package fr.iut.gestionpartie;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.iut.pieces.Knight;
import fr.iut.pieces.Bishop;
import fr.iut.pieces.Piece;
import fr.iut.pieces.Pawn;
import fr.iut.pieces.Queen;
import fr.iut.pieces.King;
import fr.iut.pieces.Rook;
import fr.iut.plateau.Board;

public class GameManagerTest {
    private Board p;
    private GameManager gp;

    private void comparerSauvegardes(File f, String[] echiquierAttendu, String nomFichierAttendu) {
        Scanner scan;
        try {
            scan = new Scanner(f);

            for (String s : echiquierAttendu) {
                assertEquals(s, scan.nextLine());
            }

            assertFalse(scan.hasNextLine());
            scan.close();
            assertEquals(nomFichierAttendu, gp.getFileName());
        } catch (Exception e) {
            fail();
        }
    }
    
    @BeforeEach
    public void init() {
        p = new Board();
        gp = new GameManager(p);
    }

    @Test
    public void chargerAnciennePartieExceptionsTest() {
        try {
            gp.loadSavedGame("fichierInexistant.csv");
            //Si aucune exception n'est générée, le test échoue
            fail();
        } catch (Exception e) {
            assertEquals("Fichier introuvable : \"fichierInexistant.csv\"", e.getMessage());
        }

        try {
            gp.loadSavedGame("fichierInexistant2");
            //Si aucune exception n'est générée, le test échoue
            fail();
        } catch (Exception e) {
            assertEquals("Fichier introuvable : \"fichierInexistant2.csv\"", e.getMessage());
        }

        try {
            gp.loadSavedGame("tests/tropDeColonnes2.csv");
            //Si aucune exception n'est générée, le test échoue
            fail();
        } catch (Exception e) {
            assertEquals("Ligne 7 invalide dans le fichier \"tests/tropDeColonnes2.csv\"", e.getMessage());
        }

        try {
            gp.loadSavedGame("tests/pieceInconnue.csv");
            //Si aucune exception n'est générée, le test échoue
            fail();
        } catch (Exception e) {
            assertEquals("Nom de pièce invalide \"poutre\" à la ligne 5 du fichier \"tests/pieceInconnue.csv\"", e.getMessage());
        }

        try {
            gp.loadSavedGame("tests/formatInvalide.csv");
            //Si aucune exception n'est générée, le test échoue
            fail();
        } catch (Exception e) {
            assertEquals("Format du fichier \"tests/formatInvalide.csv\" invalide", e.getMessage());
        }

        try {
            gp.loadSavedGame("newGame.csv");
            //Si aucune exception n'est générée, le test échoue
            fail();
        } catch (Exception e) {
            assertEquals("Vous n'avez pas le droit de charger le fichier \"newGame.csv\".", e.getMessage());
        }

        try {
            gp.loadSavedGame("tests/../noUvEllEpartiE");
            //Si aucune exception n'est générée, le test échoue
            fail();
        } catch (Exception e) {
            assertEquals("Vous n'avez pas le droit de charger le fichier \"newGame.csv\".", e.getMessage());
        }
    }

    @Test
    public void chargerAnciennePartieTest() {
        //exemple de chargement valide
        try {
            gp.loadSavedGame("tests/partieTest.csv");
        } catch (Exception e) {
            // Si une exception est générée, le test échoue
            fail();
        }

        Piece[][] echiquierAttendu = {
                {new King(false), null, new Queen(true), null, null, null, null, null},
                {new Pawn(false), new Rook(false), null, null, null, new Queen(true), null, null},
                {null, null, null, null, new Pawn(false), null, null, null},
                {null, null, null, null, null, null, null, new King(true)},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
        };

        assertArrayEquals(echiquierAttendu, p.getChessboard());
        assertEquals("tests/partieTest.csv", gp.getFileName());


        try {
            gp.loadSavedGame("tests/test28.csv");
        } catch (Exception e) {
            // Si une exception est générée, le test échoue
            fail();
        }

        echiquierAttendu = new Piece[][]{
                {null, null, null, null, null, null, null, null},
                {null, null, null, new Rook(false), null, null, null, null},
                {null, null, new King(true), null, new Pawn(false), null, null, null},
                {null, new Pawn(false), null, null, null, null, null, null},
                {null, null, null, new Bishop(false), null, null, null, null},
                {null, null, null, null, new King(false), null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, new Rook(true)}
        };

        assertArrayEquals(echiquierAttendu, p.getChessboard());
        assertEquals("tests/test28.csv", gp.getFileName());

        try {
            gp.loadSavedGame("tests/test10.csv");
        } catch (Exception e) {
            // Si une exception est générée, le test échoue
            fail();
        }

        echiquierAttendu = new Piece[][]{
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, new Rook(true), null, null, new King(true), null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, new Queen(true), new King(false), null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, new Rook(true), null, null, null}
        };

        assertArrayEquals(echiquierAttendu, p.getChessboard());
        assertEquals("tests/test10.csv", gp.getFileName());

        try {
            gp.loadSavedGame("tests/test15.csv");
        } catch (Exception e) {
            // Si une exception est générée, le test échoue
            fail();
        }

        echiquierAttendu = new Piece[][]{
                {null, null, null, null, null, null, null, new Rook(true)},
                {null, new Pawn(true), null, new King(true), null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, new Knight(true), null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, new Pawn(false), new King(false), null, null, new Rook(true)},
                {null, null, null, null, null, new Rook(false), null, null}
        };

        assertArrayEquals(echiquierAttendu, p.getChessboard());
        assertEquals("tests/test15.csv", gp.getFileName());

        try {
            gp.loadSavedGame("tests/test20.csv");
        } catch (Exception e) {
            // Si une exception est générée, le test échoue
            fail();
        }

        echiquierAttendu = new Piece[][]{
                {null, null, null, null, null, new Bishop(true), null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, new Pawn(false), new King(false)},
                {null, null, null, null, null, null, null, new Rook(true)},
                {null, null, null, null, null, null, new Bishop(false), null},
                {null, null, null, null, null, null, new Pawn(true), null},
                {null, null, null, null, null, new King(true), null, null},
                {null, new Rook(false), null, null, null, null, null, null}
        };

        assertArrayEquals(echiquierAttendu, p.getChessboard());
        assertEquals("tests/test20.csv", gp.getFileName());

        try {
            gp.loadSavedGame("tests/test35.csv");
        } catch (Exception e) {
            // Si une exception est générée, le test échoue
            fail();
        }

        echiquierAttendu = new Piece[][]{
                {null, null, null, null, new King(true), new Rook(true), null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, new Knight(true)},
                {null, null, null, null, null, null, null, new Pawn(false)},
                {null, null, null, new Queen(true), null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, new King(false), null, null, null}
        };

        assertArrayEquals(echiquierAttendu, p.getChessboard());
        assertEquals("tests/test35.csv", gp.getFileName());
    }

    @Test
    public void nouvellePartieTest() {
        try {
            gp.newGame();
        } catch (Exception e) {
            fail();
        }
        Piece[][] echiquierACharger = {
                {new Rook(true), new Knight(true), new Bishop(true), new Queen(true), new King(true), new Bishop(true), new Knight(true), new Rook(true)},
                {new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true)},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false)},
                {new Rook(false), new Knight(false), new Bishop(false), new Queen(false), new King(false), new Bishop(false), new Knight(false), new Rook(false)}
        };

        assertArrayEquals(echiquierACharger, p.getChessboard());
        assertEquals(gp.getFileName(), "partieActuelle.csv");
    }
    
    @Test
    public void sauvegarderPartieSansParamTest() {
        try {
            gp.newGame();
        } catch (IOException e) {
            fail();
        }
        File f = null;

        try {
            f = new File("parties/partieActuelle.csv");
        } catch (Exception e) {
            fail();
        }

        if (f.exists()) {
            assertTrue(f.delete());
        }

        try {
            p.move(new int[]{6, 0, 5, 0});

            assertEquals("Le fichier \"partieActuelle.csv\" vient d'être créé. " +
                    "La sauvegarde vers \"partieActuelle.csv\" a été effectuée avec succès !", gp.saveGame());
        } catch (Exception e) {
            fail();
        }

        String[] echiquierAttendu = {
                "Tn,Cn,Fn,ReN,RoN,Fn,Cn,Tn",
                "Pn,Pn,Pn,Pn,Pn,Pn,Pn,Pn",
                "V,V,V,V,V,V,V,V",
                "V,V,V,V,V,V,V,V",
                "V,V,V,V,V,V,V,V",
                "Pb,V,V,V,V,V,V,V",
                "V,Pb,Pb,Pb,Pb,Pb,Pb,Pb",
                "Tb,Cb,Fb,ReB,RoB,Fb,Cb,Tb"
        };

        comparerSauvegardes(f, echiquierAttendu, "partieActuelle.csv");

        try {
            p.move(new int[]{1, 0, 2, 0});

            assertEquals("La sauvegarde vers \"partieActuelle.csv\" a été effectuée avec succès !", gp.saveGame());
        } catch (Exception e) {
            fail();
        }

        echiquierAttendu = new String[]{
                "Tn,Cn,Fn,ReN,RoN,Fn,Cn,Tn",
                "V,Pn,Pn,Pn,Pn,Pn,Pn,Pn",
                "Pn,V,V,V,V,V,V,V",
                "V,V,V,V,V,V,V,V",
                "V,V,V,V,V,V,V,V",
                "Pb,V,V,V,V,V,V,V",
                "V,Pb,Pb,Pb,Pb,Pb,Pb,Pb",
                "Tb,Cb,Fb,ReB,RoB,Fb,Cb,Tb"
        };

        comparerSauvegardes(f, echiquierAttendu, "partieActuelle.csv");

        assertTrue(f.delete());

        try {
            f = new File("parties/tests/test36_copie.csv");
            FileOutputStream fos = new FileOutputStream(f);
            Files.copy(new File("parties/tests/test36.csv").toPath(), fos);
            fos.close();

            gp.loadSavedGame("tests/test36_copie");
        } catch (Exception e) {
            fail();
        }

        try {
            p.move(new int[]{1, 1, 0, 1});
            assertEquals("La sauvegarde vers \"tests/test36_copie.csv\" a été effectuée avec succès !", gp.saveGame());
        } catch (Exception e) {
            fail();
        }

        echiquierAttendu = new String[]{
                "RoB,Tb,ReN,V,V,V,V,V",
                "Pb,V,V,V,V,ReN,V,V",
                "V,V,V,V,Pb,V,V,V",
                "V,V,V,V,V,V,V,RoN",
                "V,V,V,V,V,V,V,V",
                "V,V,V,V,V,V,V,V",
                "V,V,V,V,V,V,V,V",
                "V,V,V,V,V,V,V,V"
        };

        comparerSauvegardes(f, echiquierAttendu, "tests/test36_copie.csv");
        assertTrue(f.delete());
    }

    @Test
    public void sauvegarderPartieAvecParamTest() {
        Piece[][] echiquierASave = {
                {new Rook(true), new Knight(true), new Bishop(true), new Queen(true), new King(true), new Bishop(true), new Knight(true), new Rook(true)},
                {new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true)},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, new Pawn(false), null, null, null, null, null, null},
                {new Pawn(false), null, new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false)},
                {new Rook(false), new Knight(false), new Bishop(false), new Queen(false), new King(false), new Bishop(false), new Knight(false), new Rook(false)}
        };

        p.setChessboard(echiquierASave);

        try {
            gp.saveGame("tests/newGame.csv");
            //Si aucune exception n'est générée, le test échoue
            fail();
        } catch (Exception e) {
            assertEquals("Vous n'avez pas le droit de sauvegarder une partie de nom \"newGame.csv\".", e.getMessage());
        }

        try {
            gp.saveGame("nouvellepartie.csv");
            //Si aucune exception n'est générée, le test échoue
            fail();
        } catch (Exception e) {
            assertEquals("Vous n'avez pas le droit de sauvegarder une partie de nom \"newGame.csv\".", e.getMessage());
        }

        try {
            gp.saveGame("../parties/nouvEllePartie");
            //Si aucune exception n'est générée, le test échoue
            fail();
        } catch (Exception e) {
            assertEquals("Vous n'avez pas le droit de sauvegarder une partie de nom \"newGame.csv\".", e.getMessage());
        }

        File f = null;

        try {
            f = new File("parties/tests/sauvegarde1.csv");
        } catch (Exception e) {
            fail();
        }

        if (f.exists())
            assertTrue(f.delete());

        try {
            String retourAttendu = "Le fichier \"tests/sauvegarde1.csv\" vient d'être créé. " +
                    "La sauvegarde vers \"tests/sauvegarde1.csv\" a été effectuée avec succès !";
            assertEquals(retourAttendu, gp.saveGame("tests/sauvegarde1.csv"));
        } catch (Exception e) {
            fail();
        }

        String[] echiquierAttendu = {
                "Tn,Cn,Fn,ReN,RoN,Fn,Cn,Tn",
                "Pn,Pn,Pn,Pn,Pn,Pn,Pn,Pn",
                "V,V,V,V,V,V,V,V",
                "V,V,V,V,V,V,V,V",
                "V,V,V,V,V,V,V,V",
                "V,Pb,V,V,V,V,V,V",
                "Pb,V,Pb,Pb,Pb,Pb,Pb,Pb",
                "Tb,Cb,Fb,ReB,RoB,Fb,Cb,Tb"};

        
        comparerSauvegardes(f, echiquierAttendu, "tests/sauvegarde1.csv");

        try {
            gp.setPlayerRound(true);
            p.move(new int[]{0, 1, 2, 0});
            String retourAttendu = "La sauvegarde vers \"tests/sauvegarde1.csv\" a été effectuée avec succès !";
            assertEquals(retourAttendu, gp.saveGame("tests/sauvegarde1.csv"));
        } catch (Exception e) {
            fail();
        }

        String[] echiquierAttendu2 = {
                "Tn,V,Fn,ReN,RoN,Fn,Cn,Tn",
                "Pn,Pn,Pn,Pn,Pn,Pn,Pn,Pn",
                "Cn,V,V,V,V,V,V,V",
                "V,V,V,V,V,V,V,V",
                "V,V,V,V,V,V,V,V",
                "V,Pb,V,V,V,V,V,V",
                "Pb,V,Pb,Pb,Pb,Pb,Pb,Pb",
                "Tb,Cb,Fb,ReB,RoB,Fb,Cb,Tb"};

        
        comparerSauvegardes(f, echiquierAttendu2, "tests/sauvegarde1.csv");
        
        assertTrue(f.delete());
    }

    @Test
    public void getPartiesPathTest() {
        assertEquals(Paths.get(System.getProperty("user.dir") + "/parties"), GameManager.getGamesPath());
    }
    
    @Test
    public void getNomFichierTest() {
        try {
            gp.newGame();
        } catch (IOException e) {
            fail();
        }
        assertEquals("partieActuelle.csv", gp.getFileName());

        try {
            gp.loadSavedGame("tests/test1.csv");
        } catch (Exception e) {
            fail();
        }
        assertEquals("tests/test1.csv", gp.getFileName());

        try {
            gp.saveGame("savGetNomTest");
        } catch (Exception e) {
            fail();
        }
        assertEquals("savGetNomTest.csv", gp.getFileName());

        try {
            assertTrue(new File("parties/savGetNomTest.csv").delete());
        } catch (Exception e) {
            fail();
        }
    }
    
    @Test
    public void setTourJoueurTest() {
    	assertFalse(p.getPlayerRound());
    	gp.setPlayerRound(true);
    	assertTrue(p.getPlayerRound());
    	gp.setPlayerRound(false);
    	assertFalse(p.getPlayerRound());
    }
}
