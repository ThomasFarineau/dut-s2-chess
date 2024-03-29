package fr.iut.plateau;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.iut.gestionpartie.GestionnairePartie;
import fr.iut.pieces.Cavalier;
import fr.iut.pieces.Fou;
import fr.iut.pieces.Piece;
import fr.iut.pieces.Pion;
import fr.iut.pieces.Reine;
import fr.iut.pieces.Roi;
import fr.iut.pieces.Tour;

public class PlateauTest {
	private Plateau plat;
	private GestionnairePartie gp;

	@BeforeEach
	public void initialisation( ) {
		plat = new Plateau();
		gp = new GestionnairePartie(plat);
	}
	
	@Test
	public void deplacerTest() {
		
		try {
			gp.chargerAnciennePartie("tests/test12.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Piece[][] echiquierInitial = {
				{new Tour(true), new Cavalier(true), new Reine(false), null, new Roi(true), new Fou(true), new Cavalier(true), new Tour(true)},
				{new Pion(true), new Pion(true), new Cavalier(false), new Pion(true), null, new Pion(true), null, null},
				{null, null, null, null, null, null, new Pion(true), null},
				{null, null, null, null, new Pion(true), null, null, new Pion(true)},
				{null, new Pion(false), null, null, new Pion(false), null, null, null},
				{null, null, null, null, null, null, null, null},
				{new Pion(false), null, null, new Pion(false), null, new Pion(false), new Pion(false), new Pion(false)},
				{new Tour(false), null, null, null, new Roi(false), new Fou(false), new Cavalier(false), new Tour(false)}
		};
		
		try {
			int[] coord = {2, 0, 2, 3};
			plat.setTourJoueur(false);
			plat.deplacer(coord);
			fail();
		}catch(Exception e) {
			assertEquals("Il n'y a pas de piece sur la premi�re case entr�e.", e.getMessage());
			assertFalse(plat.getTourJoueur());
			assertArrayEquals(echiquierInitial, plat.getEchiquier());
		}
		
		try {
			int[] coord2 = {1, 1, 1, 2};
			plat.setTourJoueur(false);
		    plat.deplacer(coord2);
		    fail();
		}catch(Exception e) {
		    assertEquals("La pi�ce selectionn�e ne vous appartient pas.", e.getMessage());
		    assertFalse(plat.getTourJoueur());
		    assertArrayEquals(echiquierInitial, plat.getEchiquier());
		}
		
		try {
			int[] coord3 = {1, 0, 5, 0}; 
			plat.setTourJoueur(true);
			plat.deplacer(coord3);
			fail();
		}catch(Exception e) {
			assertEquals("La pi�ce s�lectionn�e ne peut pas aller ici.", e.getMessage());
			assertTrue(plat.getTourJoueur());
			assertArrayEquals(echiquierInitial, plat.getEchiquier());
		}
		
		try {
			int[] coord4 = {3, 7, 5, 7};//Test pion d�j� d�placer et veut aller deux cases plus loin
			plat.setTourJoueur(true);
			plat.deplacer(coord4);
			fail();
		}catch(Exception e) {
			assertEquals("La pi�ce s�lectionn�e ne peut pas aller ici.", e.getMessage());
			assertTrue(plat.getTourJoueur());
			assertArrayEquals(echiquierInitial, plat.getEchiquier());
		}
		
		try {
			int[] coord5 = {0, 4, 0, 3};
			plat.setTourJoueur(true);
		    plat.deplacer(coord5);
		    fail();
		}catch(Exception e) {
		    assertEquals("Mouvement impossible, il vous met en �chec : ReB(C8 -> D8)", e.getMessage());
		    assertTrue(plat.getTourJoueur());
		    assertArrayEquals(echiquierInitial, plat.getEchiquier());
		}
		
		Piece[][] sauvegardeEchiquier = plat.getEchiquier();
		
		Piece[][] nouvelEchiquier = {
				{new Tour(true),null,new Fou(true),new Reine(true),new Roi(true),new Fou(true),new Cavalier(true),new Tour(true)},
				{new Pion(true),new Pion(true),new Pion(true),new Pion(true),null,new Pion(true),new Pion(true),new Pion(true)},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,new Pion(true),null,null,new Reine(false)},
				{null,new Cavalier(true),new Fou(false),null,new Pion(false),null,null,null},
				{new Pion(false),null,null,null,null,null,null,null},
				{null,new Pion(false),new Pion(false),new Pion(false),null,new Pion(false),new Pion(false),new Pion(false)},
				{new Tour(false),new Cavalier(false),new Fou(false),null,new Roi(false),null,new Cavalier(false),new Tour(false)}
		};
		
		Piece[][] memeEchiquier = {
				{new Tour(true),null,new Fou(true),new Reine(true),new Roi(true),new Fou(true),new Cavalier(true),new Tour(true)},
				{new Pion(true),new Pion(true),new Pion(true),new Pion(true),null,new Pion(true),new Pion(true),new Pion(true)},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,new Pion(true),null,null,new Reine(false)},
				{null,new Cavalier(true),new Fou(false),null,new Pion(false),null,null,null},
				{new Pion(false),null,null,null,null,null,null,null},
				{null,new Pion(false),new Pion(false),new Pion(false),null,new Pion(false),new Pion(false),new Pion(false)},
				{new Tour(false),new Cavalier(false),new Fou(false),null,new Roi(false),null,new Cavalier(false),new Tour(false)}
		};
		
		try {
			int[] coord = {1, 5, 2, 5};
			plat.setEchiquier(nouvelEchiquier);
			plat.setTourJoueur(true);
		    plat.deplacer(coord);
		    fail();
		}catch(Exception e) {
		    assertEquals("Mouvement impossible, il vous met en �chec : ReB(H5 -> E8)", e.getMessage());
		    assertTrue(plat.getTourJoueur());
		    assertArrayEquals(memeEchiquier, plat.getEchiquier());
		}
		
		plat.setEchiquier(sauvegardeEchiquier);
		
		try {
			int[] coord = {0, 4, 1, 4};
			plat.setTourJoueur(true);
			plat.deplacer(coord);
			assertFalse(plat.getTourJoueur());
		}catch(Exception e) {
			fail();
		}
		
		Piece[][] echiquierAttendu1 = {
				{new Tour(true), new Cavalier(true), new Reine(false), null, null, new Fou(true), new Cavalier(true), new Tour(true)},
				{new Pion(true), new Pion(true), new Cavalier(false), new Pion(true), new Roi(true), new Pion(true), null, null},
				{null, null, null, null, null, null, new Pion(true), null},
				{null, null, null, null, new Pion(true), null, null, new Pion(true)},
				{null, new Pion(false), null, null, new Pion(false), null, null, null},
				{null, null, null, null, null, null, null, null},
				{new Pion(false), null, null, new Pion(false), null, new Pion(false), new Pion(false), new Pion(false)},
				{new Tour(false), null, null, null, new Roi(false), new Fou(false), new Cavalier(false), new Tour(false)}
		};
		
		assertArrayEquals(echiquierAttendu1, plat.getEchiquier());
		
		try {
			int[] coord = {1, 0, 2, 0};
			plat.setTourJoueur(true);
			plat.deplacer(coord);
			assertFalse(plat.getTourJoueur());
		}catch(Exception e) {
			fail();
		}
		
		Piece[][] echiquierAttendu2 = {
				{new Tour(true), new Cavalier(true), new Reine(false), null, null, new Fou(true), new Cavalier(true), new Tour(true)},
				{null, new Pion(true), new Cavalier(false), new Pion(true), new Roi(true), new Pion(true), null, null},
				{new Pion(true), null, null, null, null, null, new Pion(true), null},
				{null, null, null, null, new Pion(true), null, null, new Pion(true)},
				{null, new Pion(false), null, null, new Pion(false), null, null, null},
				{null, null, null, null, null, null, null, null},
				{new Pion(false), null, null, new Pion(false), null, new Pion(false), new Pion(false), new Pion(false)},
				{new Tour(false), null, null, null, new Roi(false), new Fou(false), new Cavalier(false), new Tour(false)}
		};
		
		assertArrayEquals(echiquierAttendu2, plat.getEchiquier());
		
		try {
			int[] coord = {1, 1, 3, 1};
			plat.setTourJoueur(true);
			plat.deplacer(coord);
			assertFalse(plat.getTourJoueur());
		}catch(Exception e) {
			fail();
		}
		
		Piece[][] echiquierAttendu3 = {
				{new Tour(true), new Cavalier(true), new Reine(false), null, null, new Fou(true), new Cavalier(true), new Tour(true)},
				{null, null, new Cavalier(false), new Pion(true), new Roi(true), new Pion(true), null, null},
				{new Pion(true), null, null, null, null, null, new Pion(true), null},
				{null, new Pion(true), null, null, new Pion(true), null, null, new Pion(true)},
				{null, new Pion(false), null, null, new Pion(false), null, null, null},
				{null, null, null, null, null, null, null, null},
				{new Pion(false), null, null, new Pion(false), null, new Pion(false), new Pion(false), new Pion(false)},
				{new Tour(false), null, null, null, new Roi(false), new Fou(false), new Cavalier(false), new Tour(false)}
		};
		
		assertArrayEquals(echiquierAttendu3, plat.getEchiquier());
		
		try {
			int[] coord = {7, 6, 5, 5};
			plat.setTourJoueur(false);
			plat.deplacer(coord);
			assertTrue(plat.getTourJoueur());
		}catch(Exception e) {
			fail();
		}
		
		Piece[][] echiquierAttendu4 = {
				{new Tour(true), new Cavalier(true), new Reine(false), null, null, new Fou(true), new Cavalier(true), new Tour(true)},
				{null, null, new Cavalier(false), new Pion(true), new Roi(true), new Pion(true), null, null},
				{new Pion(true), null, null, null, null, null, new Pion(true), null},
				{null, new Pion(true), null, null, new Pion(true), null, null, new Pion(true)},
				{null, new Pion(false), null, null, new Pion(false), null, null, null},
				{null, null, null, null, null, new Cavalier(false), null, null},
				{new Pion(false), null, null, new Pion(false), null, new Pion(false), new Pion(false), new Pion(false)},
				{new Tour(false), null, null, null, new Roi(false), new Fou(false), null, new Tour(false)}
		};
		
		assertArrayEquals(echiquierAttendu4, plat.getEchiquier());
		
		try {
			int[] coord = {0, 5, 1, 6};
			plat.setTourJoueur(true);
			plat.deplacer(coord);
			assertFalse(plat.getTourJoueur());
		}catch(Exception e) {
			fail();
		}
		
		Piece[][] echiquierAttendu5 = { // Fou
				{new Tour(true), new Cavalier(true), new Reine(false), null, null, null, new Cavalier(true), new Tour(true)},
				{null, null, new Cavalier(false), new Pion(true), new Roi(true), new Pion(true), new Fou(true), null},
				{new Pion(true), null, null, null, null, null, new Pion(true), null},
				{null, new Pion(true), null, null, new Pion(true), null, null, new Pion(true)},
				{null, new Pion(false), null, null, new Pion(false), null, null, null},
				{null, null, null, null, null, new Cavalier(false), null, null},
				{new Pion(false), null, null, new Pion(false), null, new Pion(false), new Pion(false), new Pion(false)},
				{new Tour(false), null, null, null, new Roi(false), new Fou(false), null, new Tour(false)}
		};
		
		assertArrayEquals(echiquierAttendu5, plat.getEchiquier());
		
		try {
			int[] coord = {0, 2, 0, 6};
			plat.setTourJoueur(false);
			plat.deplacer(coord);
			assertTrue(plat.getTourJoueur());
		}catch(Exception e) {
			fail();
		}
		
		Piece[][] echiquierAttendu6 = { // Reine
				{new Tour(true), new Cavalier(true), null, null, null, null, new Reine(false), new Tour(true)},
				{null, null, new Cavalier(false), new Pion(true), new Roi(true), new Pion(true), new Fou(true), null},
				{new Pion(true), null, null, null, null, null, new Pion(true), null},
				{null, new Pion(true), null, null, new Pion(true), null, null, new Pion(true)},
				{null, new Pion(false), null, null, new Pion(false), null, null, null},
				{null, null, null, null, null, new Cavalier(false), null, null},
				{new Pion(false), null, null, new Pion(false), null, new Pion(false), new Pion(false), new Pion(false)},
				{new Tour(false), null, null, null, new Roi(false), new Fou(false), null, new Tour(false)}
		};
		
		assertArrayEquals(echiquierAttendu6, plat.getEchiquier());
		
		try {
			int[] coord = {0, 7, 0, 6};
			plat.setTourJoueur(true);
			plat.deplacer(coord);
			assertFalse(plat.getTourJoueur());
		}catch(Exception e) {
			fail();
		}
		
		Piece[][] echiquierAttendu7 = { // Tour
				{new Tour(true), new Cavalier(true), null, null, null, null, new Tour(true), null},
				{null, null, new Cavalier(false), new Pion(true), new Roi(true), new Pion(true), new Fou(true), null},
				{new Pion(true), null, null, null, null, null, new Pion(true), null},
				{null, new Pion(true), null, null, new Pion(true), null, null, new Pion(true)},
				{null, new Pion(false), null, null, new Pion(false), null, null, null},
				{null, null, null, null, null, new Cavalier(false), null, null},
				{new Pion(false), null, null, new Pion(false), null, new Pion(false), new Pion(false), new Pion(false)},
				{new Tour(false), null, null, null, new Roi(false), new Fou(false), null, new Tour(false)}
		};
		
		assertArrayEquals(echiquierAttendu7, plat.getEchiquier());
	}
	
	
	@Test
	public void calculerDeplacementTest2()
	{
		try {
			gp.chargerAnciennePartie("tests/test2.csv");
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
			gp.chargerAnciennePartie("tests/test7.csv");
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
			gp.chargerAnciennePartie("tests/test9.csv");
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
			gp.chargerAnciennePartie("tests/test24.csv");
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
			gp.chargerAnciennePartie("tests/test1.csv");
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
			
			plat.setTourJoueur(tourJoueur[i-1]);
			
			try {
				gp.chargerAnciennePartie("tests/test"+i+".csv");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (echecAttendu[i-1] != null) 
				assertArrayEquals(echecAttendu[i-1], plat.verifEchec());

		}
	}
	
	@Test
	public void verifMatTest() {
		boolean[] matAttendu= {true, true, true, false, false, false, true, true, false, true,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false, false, false, false};
		boolean[] tourJoueur= {true, true, true,false, false, true, false, true, true, false, true, true, true, false, false, false, false, false, true, false, true, true, true, false, false, true, false, true, false, false, true, true, true, true, false, false};
		
		for(int i=1; i<=36; i++) {
			
			try {
				gp.chargerAnciennePartie("tests/test"+i+".csv");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			plat.setTourJoueur(tourJoueur[i-1]);
			assertEquals(matAttendu[i-1], plat.verifMat());
		}
	}
	

	@Test
	public void toStringTest1() {
		try {
			gp.chargerAnciennePartie("tests/partieTest.csv");
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
			gp.chargerAnciennePartie("tests/test2.csv");
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
			gp.chargerAnciennePartie("tests/test3.csv");
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
			gp.chargerAnciennePartie("tests/test4.csv");
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
			gp.chargerAnciennePartie("tests/test5.csv");
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
			gp.chargerAnciennePartie("tests/test6.csv");
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
			gp.chargerAnciennePartie("tests/test7.csv");
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
			gp.chargerAnciennePartie("tests/test8.csv");
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
			gp.chargerAnciennePartie("tests/test9.csv");
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
