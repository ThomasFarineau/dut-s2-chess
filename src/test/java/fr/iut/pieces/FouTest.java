
package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class FouTest {
	
	 private Fou fouNoir;
	 private Fou fouBlanc;

	 @BeforeEach
	 public void initialisation() {
	     fouNoir = new Fou(true);
	     fouBlanc = new Fou(false);
	 }
	
	@Test
	public void toStringTest() {
		assertEquals("Fb", fouBlanc.toString());
		assertEquals("Fn", fouNoir.toString());
	}
	
	@Test
	public void getDeplacementsPossTest() {
		boolean[][] deplacementsAttendus = { //                   centre
				{true, false, false, false, false, false, false, false, false, false, false, false, false, false, true},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // centre
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
	    		{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{true, false, false, false, false, false, false, false, false, false, false, false, false, false, true}
				};
		
		boolean[][] deplacementsCalculés = fouBlanc.getDeplacementsPoss();
		assertArrayEquals(deplacementsAttendus, deplacementsCalculés);
		
		deplacementsCalculés = fouNoir.getDeplacementsPoss();
		assertArrayEquals(deplacementsAttendus, deplacementsCalculés);
	}
	
	@Test
	public void equalsTest() {
	   Fou f1 = new Fou(false);
	   Fou f2 = new Fou(false);
	   Fou f3 = new Fou(true);
	   Tour tb1 = new Tour(false);

	   assertTrue(f1.equals(f2));
	   assertFalse(f1.equals(new Object()));
	   assertFalse(f1.equals(tb1));
	   assertFalse(f1.equals(f3));
	}

}
