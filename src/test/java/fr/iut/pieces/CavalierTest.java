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
	   Cavalier cb1 = new Cavalier(false);
	   Cavalier cb2 = new Cavalier(false);
	   Cavalier cn3 = new Cavalier(true);
	   Tour tb1 = new Tour(false);

	   assertFalse(cb1.equals(new Object()));
	   assertFalse(cb1.equals(null));
	   
	   assertFalse(cb1.equals(tb1));
	   assertFalse(cn3.equals(tb1));
	   assertFalse(cb1.equals(cn3));
	  
	   assertTrue(cb1.equals(cb2));
	   assertTrue(cb1.equals(cb1));
	}
}
