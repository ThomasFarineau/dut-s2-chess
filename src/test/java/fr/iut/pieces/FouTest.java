
package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
