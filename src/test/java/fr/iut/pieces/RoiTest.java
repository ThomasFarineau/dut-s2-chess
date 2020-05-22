 
package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RoiTest {
	
	private Roi roiNoir;
    private Roi roiBlanc;

    @BeforeEach
    public void initialisation() {
        roiNoir = new Roi(true);
        roiBlanc = new Roi(false);
    }
	
	@Test
	public void testToStringRoi() {
		assertEquals("RoB", roiBlanc.toString());
		assertEquals("RoN", roiNoir.toString());
	}
	
	@Test
	public void getDeplacementsPossTest() {
		boolean[][] deplacementsAttendus = { //                   centre
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, true, true, true, false, false, false, false, false, false},
				{false, false, false, false, false, false, true, false, true, false, false, false, false, false, false}, // centre
				{false, false, false, false, false, false, true, true, true, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}
				};
		
		boolean[][] deplacementsCalcul�s = roiBlanc.getDeplacementsPoss();
		assertArrayEquals(deplacementsAttendus, deplacementsCalcul�s);
		
		deplacementsCalcul�s = roiNoir.getDeplacementsPoss();
		assertArrayEquals(deplacementsAttendus, deplacementsCalcul�s);
	}
	
	 @Test
		public void equalsTest(){
		   Roi rb1 = new Roi(false);
		   Roi rb2 = new Roi(false);
		   Roi rn1 = new Roi(true);
		   Reine ren1 = new Reine(true);

		   assertFalse(rb1.equals(null));
		   assertFalse(rb1.equals(new Object()));
		   assertTrue(rb1.equals(rb1));
		   
		   assertFalse(rb1.equals(rn1));
		   assertTrue(rb1.equals(rb2));
		   assertFalse(rb1.equals(ren1));
		   assertFalse(rn1.equals(ren1));
		
		}


}
