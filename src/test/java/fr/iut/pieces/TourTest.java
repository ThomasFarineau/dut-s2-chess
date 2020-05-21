package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TourTest {
    private Tour tourNoir;
    private Tour tourBlanche;


    @BeforeEach
    public void initialisation() {
    	tourNoir = new Tour(true);
    	tourBlanche = new Tour(false);
    }

    @Test
    public void toStringTest() {
        assertEquals("Tb", tourBlanche.toString());
        assertEquals("Tn", tourNoir.toString());
    }
    
    @Test
	public void getDeplacementsPossTest() {
		boolean[][] deplacementsAttendus = { //                   centre
				{false, false, false, false, false, false, false, true, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{true, false, false, false, false, false, false, false, false, false, false, false, false, false, true}, // centre
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, true, false, false, false, false, false, false, false}
				};
		
		boolean[][] deplacementsCalculés = tourBlanche.getDeplacementsPoss();
		assertArrayEquals(deplacementsAttendus, deplacementsCalculés);
		
		deplacementsCalculés = tourNoir.getDeplacementsPoss();
		assertArrayEquals(deplacementsAttendus, deplacementsCalculés);
	}
    
    @Test
	public void equalsTest(){
	   Tour tb1 = new Tour(false);
	   Tour tb2 = new Tour(false);
	   Tour tn1 = new Tour(true);
	   Reine ren1 = new Reine(true);

	   assertTrue(tb1.equals(tb2));
	   assertTrue(tb1.equals(tb1));
	   
	   assertFalse(tb1.equals(tn1));
	   assertFalse(tb1.equals(new Object()));
	   assertFalse(tb1.equals(null));
	   assertFalse(tb1.equals(ren1));
	   assertFalse(tn1.equals(ren1));
	
	   
	   
	  

	  
	}

}