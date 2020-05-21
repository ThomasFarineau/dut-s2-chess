package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.iut.pieces.Cavalier;

public class CavalierTest {
	private Cavalier cavalierBlanc;
	private Cavalier cavalierNoir;

	@BeforeEach
	public void initialisation() {
		cavalierBlanc = new Cavalier(false);
		cavalierNoir = new Cavalier(true);
	}

	@Test
	public void toStringTest() {
		assertEquals("Cb", cavalierBlanc.toString());
		assertEquals("Cn", cavalierNoir.toString());
	}

	@Test
	public void getDeplacementsPossTest() {
		boolean[][] deplacementsAttendus = { //                   centre
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, true, false, true, false, false, false, false, false, false},
				{false, false, false, false, false, true, false, false, false, true, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // centre
				{false, false, false, false, false, true, false, false, false, true, false, false, false, false, false},
				{false, false, false, false, false, false, true, false, true, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}
				};
		
		boolean[][] deplacementsCalculés = cavalierBlanc.getDeplacementsPoss();
		assertArrayEquals(deplacementsAttendus, deplacementsCalculés);
		
		deplacementsCalculés = cavalierNoir.getDeplacementsPoss();
		assertArrayEquals(deplacementsAttendus, deplacementsCalculés);
	}
	
	@Test
	public void equalsTest() {
	   Cavalier c1 = new Cavalier(false);
	   Cavalier c2 = new Cavalier(false);
	   Cavalier c3 = new Cavalier(true);
	   Tour tb1 = new Tour(false);

	   assertTrue(c1.equals(c2));
	   assertFalse(c1.equals(new Object()));
	   assertFalse(c1.equals(tb1));
	   assertFalse(c1.equals(c3));
	}
}
