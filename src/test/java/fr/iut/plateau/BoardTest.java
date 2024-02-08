package fr.iut.plateau;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.iut.gestionpartie.GameManager;
import fr.iut.pieces.Knight;
import fr.iut.pieces.Bishop;
import fr.iut.pieces.Piece;
import fr.iut.pieces.Pawn;
import fr.iut.pieces.Queen;
import fr.iut.pieces.King;
import fr.iut.pieces.Rook;

public class BoardTest {
	private Board plat;
	private GameManager gp;

	@BeforeEach
	public void initialisation( ) {
		plat = new Board();
		gp = new GameManager(plat);
	}
	
	@Test
	public void deplacerTest() {
		
		try {
			gp.loadSavedGame("tests/test12.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Piece[][] echiquierInitial = {
				{new Rook(true), new Knight(true), new Queen(false), null, new King(true), new Bishop(true), new Knight(true), new Rook(true)},
				{new Pawn(true), new Pawn(true), new Knight(false), new Pawn(true), null, new Pawn(true), null, null},
				{null, null, null, null, null, null, new Pawn(true), null},
				{null, null, null, null, new Pawn(true), null, null, new Pawn(true)},
				{null, new Pawn(false), null, null, new Pawn(false), null, null, null},
				{null, null, null, null, null, null, null, null},
				{new Pawn(false), null, null, new Pawn(false), null, new Pawn(false), new Pawn(false), new Pawn(false)},
				{new Rook(false), null, null, null, new King(false), new Bishop(false), new Knight(false), new Rook(false)}
		};
		
		try {
			int[] coord = {2, 0, 2, 3};
			plat.setPlayerRound(false);
			plat.move(coord);
			fail();
		}catch(Exception e) {
			assertEquals("Il n'y a pas de piece sur la premi�re case entr�e.", e.getMessage());
			assertFalse(plat.getPlayerRound());
			assertArrayEquals(echiquierInitial, plat.getChessboard());
		}
		
		try {
			int[] coord2 = {1, 1, 1, 2};
			plat.setPlayerRound(false);
		    plat.move(coord2);
		    fail();
		}catch(Exception e) {
		    assertEquals("La pi�ce selectionn�e ne vous appartient pas.", e.getMessage());
		    assertFalse(plat.getPlayerRound());
		    assertArrayEquals(echiquierInitial, plat.getChessboard());
		}
		
		try {
			int[] coord3 = {1, 0, 5, 0}; 
			plat.setPlayerRound(true);
			plat.move(coord3);
			fail();
		}catch(Exception e) {
			assertEquals("La pi�ce s�lectionn�e ne peut pas aller ici.", e.getMessage());
			assertTrue(plat.getPlayerRound());
			assertArrayEquals(echiquierInitial, plat.getChessboard());
		}
		
		try {
			int[] coord4 = {3, 7, 5, 7};//Test pion d�j� d�placer et veut aller deux cases plus loin
			plat.setPlayerRound(true);
			plat.move(coord4);
			fail();
		}catch(Exception e) {
			assertEquals("La pi�ce s�lectionn�e ne peut pas aller ici.", e.getMessage());
			assertTrue(plat.getPlayerRound());
			assertArrayEquals(echiquierInitial, plat.getChessboard());
		}
		
		try {
			int[] coord5 = {0, 4, 0, 3};
			plat.setPlayerRound(true);
		    plat.move(coord5);
		    fail();
		}catch(Exception e) {
		    assertEquals("Mouvement impossible, il vous met en �chec : ReB(C8 -> D8)", e.getMessage());
		    assertTrue(plat.getPlayerRound());
		    assertArrayEquals(echiquierInitial, plat.getChessboard());
		}
		
		Piece[][] sauvegardeEchiquier = plat.getChessboard();
		
		Piece[][] nouvelEchiquier = {
				{new Rook(true),null,new Bishop(true),new Queen(true),new King(true),new Bishop(true),new Knight(true),new Rook(true)},
				{new Pawn(true),new Pawn(true),new Pawn(true),new Pawn(true),null,new Pawn(true),new Pawn(true),new Pawn(true)},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,new Pawn(true),null,null,new Queen(false)},
				{null,new Knight(true),new Bishop(false),null,new Pawn(false),null,null,null},
				{new Pawn(false),null,null,null,null,null,null,null},
				{null,new Pawn(false),new Pawn(false),new Pawn(false),null,new Pawn(false),new Pawn(false),new Pawn(false)},
				{new Rook(false),new Knight(false),new Bishop(false),null,new King(false),null,new Knight(false),new Rook(false)}
		};
		
		Piece[][] memeEchiquier = {
				{new Rook(true),null,new Bishop(true),new Queen(true),new King(true),new Bishop(true),new Knight(true),new Rook(true)},
				{new Pawn(true),new Pawn(true),new Pawn(true),new Pawn(true),null,new Pawn(true),new Pawn(true),new Pawn(true)},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,new Pawn(true),null,null,new Queen(false)},
				{null,new Knight(true),new Bishop(false),null,new Pawn(false),null,null,null},
				{new Pawn(false),null,null,null,null,null,null,null},
				{null,new Pawn(false),new Pawn(false),new Pawn(false),null,new Pawn(false),new Pawn(false),new Pawn(false)},
				{new Rook(false),new Knight(false),new Bishop(false),null,new King(false),null,new Knight(false),new Rook(false)}
		};
		
		try {
			int[] coord = {1, 5, 2, 5};
			plat.setChessboard(nouvelEchiquier);
			plat.setPlayerRound(true);
		    plat.move(coord);
		    fail();
		}catch(Exception e) {
		    assertEquals("Mouvement impossible, il vous met en �chec : ReB(H5 -> E8)", e.getMessage());
		    assertTrue(plat.getPlayerRound());
		    assertArrayEquals(memeEchiquier, plat.getChessboard());
		}
		
		plat.setChessboard(sauvegardeEchiquier);
		
		try {
			int[] coord = {0, 4, 1, 4};
			plat.setPlayerRound(true);
			plat.move(coord);
			assertFalse(plat.getPlayerRound());
		}catch(Exception e) {
			fail();
		}
		
		Piece[][] echiquierAttendu1 = {
				{new Rook(true), new Knight(true), new Queen(false), null, null, new Bishop(true), new Knight(true), new Rook(true)},
				{new Pawn(true), new Pawn(true), new Knight(false), new Pawn(true), new King(true), new Pawn(true), null, null},
				{null, null, null, null, null, null, new Pawn(true), null},
				{null, null, null, null, new Pawn(true), null, null, new Pawn(true)},
				{null, new Pawn(false), null, null, new Pawn(false), null, null, null},
				{null, null, null, null, null, null, null, null},
				{new Pawn(false), null, null, new Pawn(false), null, new Pawn(false), new Pawn(false), new Pawn(false)},
				{new Rook(false), null, null, null, new King(false), new Bishop(false), new Knight(false), new Rook(false)}
		};
		
		assertArrayEquals(echiquierAttendu1, plat.getChessboard());
		
		try {
			int[] coord = {1, 0, 2, 0};
			plat.setPlayerRound(true);
			plat.move(coord);
			assertFalse(plat.getPlayerRound());
		}catch(Exception e) {
			fail();
		}
		
		Piece[][] echiquierAttendu2 = {
				{new Rook(true), new Knight(true), new Queen(false), null, null, new Bishop(true), new Knight(true), new Rook(true)},
				{null, new Pawn(true), new Knight(false), new Pawn(true), new King(true), new Pawn(true), null, null},
				{new Pawn(true), null, null, null, null, null, new Pawn(true), null},
				{null, null, null, null, new Pawn(true), null, null, new Pawn(true)},
				{null, new Pawn(false), null, null, new Pawn(false), null, null, null},
				{null, null, null, null, null, null, null, null},
				{new Pawn(false), null, null, new Pawn(false), null, new Pawn(false), new Pawn(false), new Pawn(false)},
				{new Rook(false), null, null, null, new King(false), new Bishop(false), new Knight(false), new Rook(false)}
		};
		
		assertArrayEquals(echiquierAttendu2, plat.getChessboard());
		
		try {
			int[] coord = {1, 1, 3, 1};
			plat.setPlayerRound(true);
			plat.move(coord);
			assertFalse(plat.getPlayerRound());
		}catch(Exception e) {
			fail();
		}
		
		Piece[][] echiquierAttendu3 = {
				{new Rook(true), new Knight(true), new Queen(false), null, null, new Bishop(true), new Knight(true), new Rook(true)},
				{null, null, new Knight(false), new Pawn(true), new King(true), new Pawn(true), null, null},
				{new Pawn(true), null, null, null, null, null, new Pawn(true), null},
				{null, new Pawn(true), null, null, new Pawn(true), null, null, new Pawn(true)},
				{null, new Pawn(false), null, null, new Pawn(false), null, null, null},
				{null, null, null, null, null, null, null, null},
				{new Pawn(false), null, null, new Pawn(false), null, new Pawn(false), new Pawn(false), new Pawn(false)},
				{new Rook(false), null, null, null, new King(false), new Bishop(false), new Knight(false), new Rook(false)}
		};
		
		assertArrayEquals(echiquierAttendu3, plat.getChessboard());
		
		try {
			int[] coord = {7, 6, 5, 5};
			plat.setPlayerRound(false);
			plat.move(coord);
			assertTrue(plat.getPlayerRound());
		}catch(Exception e) {
			fail();
		}
		
		Piece[][] echiquierAttendu4 = {
				{new Rook(true), new Knight(true), new Queen(false), null, null, new Bishop(true), new Knight(true), new Rook(true)},
				{null, null, new Knight(false), new Pawn(true), new King(true), new Pawn(true), null, null},
				{new Pawn(true), null, null, null, null, null, new Pawn(true), null},
				{null, new Pawn(true), null, null, new Pawn(true), null, null, new Pawn(true)},
				{null, new Pawn(false), null, null, new Pawn(false), null, null, null},
				{null, null, null, null, null, new Knight(false), null, null},
				{new Pawn(false), null, null, new Pawn(false), null, new Pawn(false), new Pawn(false), new Pawn(false)},
				{new Rook(false), null, null, null, new King(false), new Bishop(false), null, new Rook(false)}
		};
		
		assertArrayEquals(echiquierAttendu4, plat.getChessboard());
		
		try {
			int[] coord = {0, 5, 1, 6};
			plat.setPlayerRound(true);
			plat.move(coord);
			assertFalse(plat.getPlayerRound());
		}catch(Exception e) {
			fail();
		}
		
		Piece[][] echiquierAttendu5 = { // Fou
				{new Rook(true), new Knight(true), new Queen(false), null, null, null, new Knight(true), new Rook(true)},
				{null, null, new Knight(false), new Pawn(true), new King(true), new Pawn(true), new Bishop(true), null},
				{new Pawn(true), null, null, null, null, null, new Pawn(true), null},
				{null, new Pawn(true), null, null, new Pawn(true), null, null, new Pawn(true)},
				{null, new Pawn(false), null, null, new Pawn(false), null, null, null},
				{null, null, null, null, null, new Knight(false), null, null},
				{new Pawn(false), null, null, new Pawn(false), null, new Pawn(false), new Pawn(false), new Pawn(false)},
				{new Rook(false), null, null, null, new King(false), new Bishop(false), null, new Rook(false)}
		};
		
		assertArrayEquals(echiquierAttendu5, plat.getChessboard());
		
		try {
			int[] coord = {0, 2, 0, 6};
			plat.setPlayerRound(false);
			plat.move(coord);
			assertTrue(plat.getPlayerRound());
		}catch(Exception e) {
			fail();
		}
		
		Piece[][] echiquierAttendu6 = { // Reine
				{new Rook(true), new Knight(true), null, null, null, null, new Queen(false), new Rook(true)},
				{null, null, new Knight(false), new Pawn(true), new King(true), new Pawn(true), new Bishop(true), null},
				{new Pawn(true), null, null, null, null, null, new Pawn(true), null},
				{null, new Pawn(true), null, null, new Pawn(true), null, null, new Pawn(true)},
				{null, new Pawn(false), null, null, new Pawn(false), null, null, null},
				{null, null, null, null, null, new Knight(false), null, null},
				{new Pawn(false), null, null, new Pawn(false), null, new Pawn(false), new Pawn(false), new Pawn(false)},
				{new Rook(false), null, null, null, new King(false), new Bishop(false), null, new Rook(false)}
		};
		
		assertArrayEquals(echiquierAttendu6, plat.getChessboard());
		
		try {
			int[] coord = {0, 7, 0, 6};
			plat.setPlayerRound(true);
			plat.move(coord);
			assertFalse(plat.getPlayerRound());
		}catch(Exception e) {
			fail();
		}
		
		Piece[][] echiquierAttendu7 = { // Tour
				{new Rook(true), new Knight(true), null, null, null, null, new Rook(true), null},
				{null, null, new Knight(false), new Pawn(true), new King(true), new Pawn(true), new Bishop(true), null},
				{new Pawn(true), null, null, null, null, null, new Pawn(true), null},
				{null, new Pawn(true), null, null, new Pawn(true), null, null, new Pawn(true)},
				{null, new Pawn(false), null, null, new Pawn(false), null, null, null},
				{null, null, null, null, null, new Knight(false), null, null},
				{new Pawn(false), null, null, new Pawn(false), null, new Pawn(false), new Pawn(false), new Pawn(false)},
				{new Rook(false), null, null, null, new King(false), new Bishop(false), null, new Rook(false)}
		};
		
		assertArrayEquals(echiquierAttendu7, plat.getChessboard());
	}
	
	
	@Test
	public void calculerDeplacementTest2()
	{
		try {
			gp.loadSavedGame("tests/test2.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean[][] deplacementsRoiValide = { 
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, true},
				{false, false, false, false, false, false, true, false},
				{false, false, false, false, false, false, true, true},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false}

		};

		boolean[][] deplacementRoi = plat.calculerDeplacementsPiece(2, 7);
		assertArrayEquals(deplacementsRoiValide, deplacementRoi);
		
		boolean[][] deplacementsFouValide = { 
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, true, false, true},
				{false, false, false, false, true, false, false, false},
				{false, false, false, true, false, false, false, false},
				{false, false, true, false, false, false, false, false},
				{false, true, false, false, false, false, false, false},
				{true, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false}

		};

		boolean[][] deplacementFou = plat.calculerDeplacementsPiece(0, 6);
		assertArrayEquals(deplacementsFouValide, deplacementFou);
		
		boolean[][] deplacementsPionValide = { 
				{false, false, false, false, false, false, false, true},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false}

		};

		boolean[][] deplacementPion = plat.calculerDeplacementsPiece(1, 6);
		assertArrayEquals(deplacementsPionValide, deplacementPion);
	}

	@Test
	public void calculerDeplacementTest7()
	{
		try {
			gp.loadSavedGame("tests/test7.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean[][] deplacementsFouNoir = { 
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, true},
				{false, false, false, false, false, false, true, false},
				{false, false, false, false, false, false, false, false}

		};

		boolean[][] deplacementAttendu = plat.calculerDeplacementsPiece(7, 5);
		assertArrayEquals(deplacementsFouNoir, deplacementAttendu);

		boolean[][] deplacementTourValide = {
				{true, true, true, false, true, true, false, false},
				{false, false, false, true, false, false, false, false},
				{false, false, false, true, false, false, false, false},
				{false, false, false, true, false, false, false, false},
				{false, false, false, true, false, false, false, false},
				{false, false, false, true, false, false, false, false},
				{false, false, false, true, false, false, false, false},
				{false, false, false, true, false, false, false, false}
		};

		boolean[][] deplacementTour = plat.calculerDeplacementsPiece(0, 3); 
		assertArrayEquals(deplacementTourValide, deplacementTour);

		boolean[][] deplacementFouValide = {
				{false, false, false, false, false, false, false, false},
				{false, false, true, false, false, false, false, false},
				{false, true, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, true, false, false, false, false, false, false},
				{false, false, true, false, false, false, false, false},
				{false, false, false, true, false, false, false, false},
				{false, false, false, false, true, false, false, false}
		};
		boolean[][] deplacementFou = plat.calculerDeplacementsPiece(3, 0); 
		assertArrayEquals(deplacementFouValide, deplacementFou);

		boolean[][] deplacementRoiValide = {
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, true, false, false, false, false},
				{false, false, false, true, false, false, false, false}
		};
		boolean[][] deplacementRoi = plat.calculerDeplacementsPiece(7, 4); 
		assertArrayEquals(deplacementRoiValide, deplacementRoi);

		boolean[][] deplacementPionValide = {
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, true, false, false, false},
				{false, false, false, false, true, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false}
		};
		boolean[][] deplacementPion = plat.calculerDeplacementsPiece(6, 4); 
		assertArrayEquals(deplacementPionValide, deplacementPion);
	}



	@Test
	public void calculerDeplacementTest9()
	{
		try {
			gp.loadSavedGame("tests/test9.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean[][] deplacementsTourValide = { 
				{true, false, false, false, false, false, false, false},
				{false, true, true, true, true, false, false, false},
				{true, false, false, false, false, false, false, false},
				{true, false, false, false, false, false, false, false},
				{true, false, false, false, false, false, false, false},
				{true, false, false, false, false, false, false, false},
				{true, false, false, false, false, false, false, false},
				{true, false, false, false, false, false, false, false}

		};

		boolean[][] deplacementTour = plat.calculerDeplacementsPiece(1, 0);
		assertArrayEquals(deplacementsTourValide, deplacementTour);
		
		boolean[][] deplacementsRoiValide = { 
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{true, true, true, false, false, false, false, false},
				{true, false, true, false, false, false, false, false}

		};

		boolean[][] deplacementRoi = plat.calculerDeplacementsPiece(7, 1);
		assertArrayEquals(deplacementsRoiValide, deplacementRoi);
		
		boolean[][] deplacementsFouValide = { 
				{false, false, false, true, false, false, false, true},
				{false, false, false, false, true, false, true, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, true, false, true, false},
				{false, false, false, true, false, false, false, true},
				{false, false, true, false, false, false, false, false},
				{false, true, false, false, false, false, false, false},
				{true, false, false, false, false, false, false, false}

		};

		boolean[][] deplacementFou = plat.calculerDeplacementsPiece(2, 5);
		assertArrayEquals(deplacementsFouValide, deplacementFou);
		
		boolean[][] deplacementsCavalierValide = { 
				{false, false, false, true, false, false, false, true},
				{false, false, false, false, false, false, false, false},
				{false, false, false, true, false, false, false, true},
				{false, false, false, false, true, false, true, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false}

		};

		boolean[][] deplacementCavalier = plat.calculerDeplacementsPiece(1, 5);
		assertArrayEquals(deplacementsCavalierValide, deplacementCavalier);
	}
	
	@Test
	public void calculerDeplacementsTest24() {
		try {
			gp.loadSavedGame("tests/test24.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		boolean[][] deplacementTourValide = {
				{true, false, false, false, false, false, false, false},
				{true, false, false, false, false, false, false, false},
				{false, true, true, true, true, true, true, true},
				{true, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false}
		};

		boolean[][] deplacementTour = plat.calculerDeplacementsPiece(2, 0); 
		assertArrayEquals(deplacementTourValide, deplacementTour);
		
		boolean[][] deplacementRoiValide = {
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, true, true, true, false, false, false},
				{false, false, true, false, true, false, false, false},
				{false, false, true, true, true, false, false, false},
				{false, false, false, false, false, false, false, false}
		};

		boolean[][] deplacementRoi = plat.calculerDeplacementsPiece(5, 3); 
		assertArrayEquals(deplacementRoiValide, deplacementRoi);
		
		boolean[][] deplacementReineValide = {
				{false, false, false, false, true, false, false, true},
				{false, false, false, false, false, true, false, true},
				{false, false, false, false, false, false, true, true},
				{false, false, false, true, true, true, true, false},
				{false, false, false, false, false, false, true, true},
				{false, false, false, false, false, true, false, true},
				{false, false, false, false, true, false, false, true},
				{false, false, false, true, false, false, false, true}
		};

		boolean[][] deplacementReine = plat.calculerDeplacementsPiece(3, 7); 
		assertArrayEquals(deplacementReineValide, deplacementReine);
		
		boolean[][] deplacementCavalierValide = {
				{false, true, false, false, false, true, false, false},
				{false, false, false, false, false, false, false, false},
				{false, true, false, false, false, true, false, false},
				{false, false, true, false, true, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false}
		};
		boolean[][] deplacementCavalier = plat.calculerDeplacementsPiece(1, 3); 
		assertArrayEquals(deplacementCavalierValide, deplacementCavalier);
	}


	@Test
	public void calculerDeplacementsTest1() {

		try {
			gp.loadSavedGame("tests/test1.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean[][] deplacementTourValide = {
				{false, false, false, false, false, false, true, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false}
		};

		boolean[][] deplacementTour = plat.calculerDeplacementsPiece(0, 7); 
		assertArrayEquals(deplacementTourValide, deplacementTour);

		boolean[][] deplacementCavalierValide = {
				{false, true, false, false, false, false, false, false},
				{false, false, false, false, true, false, false, false},
				{false, false, false, false, false, false, false, false},
				{true, false, false, false, true, false, false, false},
				{false, true, false, true, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false}
		};

		boolean[][] deplacementCavalier = plat.calculerDeplacementsPiece(2, 2); 
		assertArrayEquals(deplacementCavalierValide, deplacementCavalier);

		boolean[][] deplacementFouValide = {
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{true, false, false, false, true, false, false, false},
				{false, true, false, true, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, true, false, true, false, false, false, false},
				{false, false, false, false, true, false, false, false},
				{false, false, false, false, false, true, false, false}
		};
		boolean[][] deplacementFou = plat.calculerDeplacementsPiece(4, 2); 
		assertArrayEquals(deplacementFouValide, deplacementFou);

		boolean[][] deplacementRoiValide = {
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, true, false, false, false},
				{false, false, false, true, false, true, false, false}
		};
		boolean[][] deplacementRoi = plat.calculerDeplacementsPiece(7, 4); 
		assertArrayEquals(deplacementRoiValide, deplacementRoi);

		boolean[][] deplacementReineValide = {
				{false, false, false, false, true, true, true, false},
				{false, false, false, true, true, false, true, false},
				{false, false, false, false, true, true, true, false},
				{false, false, false, true, false, false, false, true},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false}
		};
		boolean[][] deplacementReine = plat.calculerDeplacementsPiece(1, 5); 
		assertArrayEquals(deplacementReineValide, deplacementReine);

		boolean[][] deplacementPionValide = {
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{true, false, false, false, false, false, false, false},
				{true, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false}
		};
		boolean[][] deplacementPion = plat.calculerDeplacementsPiece(1, 0); 
		assertArrayEquals(deplacementPionValide, deplacementPion);
	}
	
	@Test
	public void verifEchecTest() {
		int[][] echecAttendu= {
				{1, 5, 0, 4},
				{1, 6, 0, 7},
				{1, 5, 0, 7},
				null,
				{6, 2, 4, 3},
				{6, 1, 5, 0},
				{3, 0, 7, 4},
				{1, 5, 0, 4},
				{1, 5, 0, 3},
				{4, 2, 4, 3},
				{1, 2, 0, 4},
				{0, 2, 0, 4},
				{1, 3, 1, 4},
				{7, 0, 7, 4},
				{6, 7, 6, 4},
				{3, 4, 5, 5},
				{3, 7, 3, 3},
				{4, 7, 7, 4},
				{3, 7, 0, 4},
				{0, 5, 2, 7},
				{2, 2, 1, 3},
				{7, 1, 5, 1},
				null,
				{3, 7, 3, 3},
				{2, 4, 3, 3},
				{4, 5, 3, 6},
				{7, 7, 7, 3},
				{3, 1, 2, 2},
				{3, 4, 3, 5},
				{4, 3, 3, 4},
				{3, 2, 3, 4},
				{7, 0, 7, 4},
				{3, 2, 3, 3},
				{4, 5, 6, 3},
				null,
				{0, 2, 0, 0}
		};
		
		boolean[] tourJoueur= {true, true, true,false, false, true, false, true, true, false, true, true, true, false, false, false, false, false, true, false, true, true, true, false, false, true, false, true, false, false, true, true, true, true, false, false};

		
		for(int i=1; i <= 36; i++) {
			
			plat.setPlayerRound(tourJoueur[i-1]);
			
			try {
				gp.loadSavedGame("tests/test"+i+".csv");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (echecAttendu[i-1] != null) 
				assertArrayEquals(echecAttendu[i-1], plat.checkCheck());

		}
	}
	
	@Test
	public void verifMatTest() {
		boolean[] matAttendu= {true, true, true, false, false, false, true, true, false, true,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false, false, false, false};
		boolean[] tourJoueur= {true, true, true,false, false, true, false, true, true, false, true, true, true, false, false, false, false, false, true, false, true, true, true, false, false, true, false, true, false, false, true, true, true, true, false, false};
		
		for(int i=1; i<=36; i++) {
			
			try {
				gp.loadSavedGame("tests/test"+i+".csv");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			plat.setPlayerRound(tourJoueur[i-1]);
			assertEquals(matAttendu[i-1], plat.checkMat());
		}
	}
	

	@Test
	public void toStringTest1() {
		try {
			gp.loadSavedGame("tests/partieTest.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}

		String valeurAttendue = 
				"8 RoB  -  ReN  -   -   -   -   -  \n" + 
				"7 Pb  Tb   -   -   -  ReN  -   -  \n" + 
				"6  -   -   -   -  Pb   -   -   -  \n" + 
				"5  -   -   -   -   -   -   -  RoN \n" + 
				"4  -   -   -   -   -   -   -   -  \n" + 
				"3  -   -   -   -   -   -   -   -  \n" + 
				"2  -   -   -   -   -   -   -   -  \n" + 
				"1  -   -   -   -   -   -   -   -  \n" + 
				"   A   B   C   D   E   F   G   H";
		assertEquals(valeurAttendue, plat.toString());
	}

	@Test
	public void toStringTest2() {
		try {
			gp.loadSavedGame("tests/test2.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String valeurAttendue = 
				"8  -   -   -   -   -   -  Fn  RoN \n" +
				"7  -   -   -   -   -   -  Pb   -  \n" +
				"6  -   -   -   -   -   -   -  RoB \n" +
				"5  -   -   -   -   -   -   -   -  \n" +
				"4  -   -   -   -   -   -   -   -  \n" +
				"3  -   -   -   -   -   -   -   -  \n" +
				"2  -   -   -   -   -   -   -   -  \n" +
				"1  -   -   -   -   -   -   -   -  \n" +
				"   A   B   C   D   E   F   G   H";
		assertEquals(valeurAttendue, plat.toString());
	}

	@Test
	public void toStringTest3() {
		try {
			gp.loadSavedGame("tests/test3.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String valeurAttendue = 
				"8  -   -   -   -   -   -  Tn  RoN \n" + 
				"7  -   -   -   -   -  Cb  Pn  Pn  \n" + 
				"6  -   -   -   -   -   -   -   -  \n" + 
				"5  -   -   -   -   -   -   -   -  \n" + 
				"4  -   -   -   -   -   -   -   -  \n" + 
				"3  -   -   -   -   -   -   -   -  \n" + 
				"2  -   -   -   -   -  Pb  Pb  Pb  \n" + 
				"1  -   -   -   -   -   -  RoB  -  \n" + 
				"   A   B   C   D   E   F   G   H";
		assertEquals(valeurAttendue, plat.toString());
	}

	@Test
	public void toStringTest4() {
		try {
			gp.loadSavedGame("tests/test4.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String valeurAttendue = 
				"8 Tn  Cn  Fn  ReN RoN Fn  Cn  Tn  \n" + 
				"7 Pn  Pn  Pn  Pn  Pn  Pn  Pn  Pn  \n" + 
				"6  -   -   -   -   -   -   -   -  \n" + 
				"5  -   -   -   -   -   -   -   -  \n" + 
				"4  -   -   -   -   -   -   -   -  \n" + 
				"3  -   -   -   -   -   -   -   -  \n" + 
				"2 Pb  Pb  Pb  Pb  Pb  Pb  Pb  Pb  \n" + 
				"1 Tb  Cb  Fb  ReB RoB Fb  Cb  Tb  \n" + 
				"   A   B   C   D   E   F   G   H";
		assertEquals(valeurAttendue, plat.toString());
	}

	@Test
	public void toStringTest5() {
		try {
			gp.loadSavedGame("tests/test5.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String valeurAttendue = 
				"8  -   -   -   -   -   -   -   -  \n" + 
				"7  -   -   -   -   -   -   -   -  \n" + 
				"6  -   -   -   -   -   -   -   -  \n" + 
				"5  -   -   -   -   -   -   -   -  \n" + 
				"4  -   -   -  RoB  -   -   -   -  \n" + 
				"3  -   -   -   -   -   -   -   -  \n" + 
				"2  -   -  Cn   -   -   -   -   -  \n" + 
				"1  -   -   -  RoN  -   -   -   -  \n" + 
				"   A   B   C   D   E   F   G   H";
		assertEquals(valeurAttendue, plat.toString());
	}

	@Test
	public void toStringTest6() {
		try {
			gp.loadSavedGame("tests/test6.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String valeurAttendue = 
				"8 Tn  Cn  Fn  ReN  -  Fn  Cn  Tn  \n" + 
				"7 Pn  Pn  Pn  Pn  Pn  Pn  Pn  Pn  \n" + 
				"6  -   -   -   -   -   -   -   -  \n" + 
				"5  -   -   -   -   -   -   -   -  \n" + 
				"4  -   -   -   -   -   -   -   -  \n" + 
				"3 RoN  -   -   -   -   -   -   -  \n" + 
				"2 Pb  Pb  Pb  Pb  Pb  Pb  Pb  Pb  \n" + 
				"1 Tb  Cb  Fb  ReB RoB Fb  Cb  Tb  \n" + 
				"   A   B   C   D   E   F   G   H";
		assertEquals(valeurAttendue, plat.toString());
	}

	@Test
	public void toStringTest7() {
		try {
			gp.loadSavedGame("tests/test7.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String valeurAttendue = 
				"8  -   -   -  Tn   -   -  RoN  -  \n" + 
				"7  -   -   -   -   -   -   -   -  \n" + 
				"6  -   -   -   -   -   -   -   -  \n" + 
				"5 Fn   -   -   -   -   -   -   -  \n" + 
				"4  -   -   -   -   -   -   -   -  \n" + 
				"3  -   -   -   -   -   -   -   -  \n" + 
				"2  -   -   -   -  Pb  Pb   -   -  \n" + 
				"1  -   -   -   -  RoB Fb   -   -  \n" + 
				"   A   B   C   D   E   F   G   H";
		assertEquals(valeurAttendue, plat.toString());
	}

	@Test
	public void toStringTest8() {
		try {
			gp.loadSavedGame("tests/test8.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String valeurAttendue = 
				"8 Tn   -  Fn  ReN RoN Fn   -  Tn  \n" + 
				"7 Pn  Pn  Pn  Pn   -  ReB Pn  Pn  \n" + 
				"6  -   -  Cn   -   -  Cn   -   -  \n" + 
				"5  -   -   -   -  Pn   -   -   -  \n" + 
				"4  -   -  Fb   -  Pb   -   -   -  \n" + 
				"3  -   -   -   -   -   -   -   -  \n" + 
				"2 Pb  Pb  Pb  Pb  Pb  Pb  Pb  Pb  \n" + 
				"1 Tb  Cb  Fb   -  RoB  -  Cb  Tb  \n" + 
				"   A   B   C   D   E   F   G   H";
		assertEquals(valeurAttendue, plat.toString());
	}

	@Test
	public void toStringTest9() {
		try {
			gp.loadSavedGame("tests/test9.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String valeurAttendue = 
				"8  -   -   -  RoN  -   -   -   -  \n" + 
				"7 Tb   -   -   -   -  Cb   -   -  \n" + 
				"6  -   -   -   -   -  Fb   -   -  \n" + 
				"5  -   -   -   -   -   -   -   -  \n" + 
				"4  -   -   -   -   -   -   -   -  \n" + 
				"3  -   -   -   -   -   -   -   -  \n" + 
				"2  -   -   -   -   -   -   -   -  \n" + 
				"1  -  RoB  -   -   -   -   -   -  \n" + 
				"   A   B   C   D   E   F   G   H";
		assertEquals(valeurAttendue, plat.toString());
	}

}