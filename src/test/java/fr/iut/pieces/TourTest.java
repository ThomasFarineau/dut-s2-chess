package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
		
		boolean[][] deplacementsCalcul�s = tourBlanche.getDeplacementsPoss();
		assertArrayEquals(deplacementsAttendus, deplacementsCalcul�s);
		
		deplacementsCalcul�s = tourNoir.getDeplacementsPoss();
		assertArrayEquals(deplacementsAttendus, deplacementsCalcul�s);
	}

}