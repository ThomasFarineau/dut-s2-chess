package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReineTest {
    private Reine reineNoir;
    private Reine reineBlanche;

    @BeforeEach
    public void initialisation() {
        reineNoir = new Reine(true);
        reineBlanche = new Reine(false);
    }

    @Test
    public void toStringTest() {
        assertEquals("ReB", reineBlanche.toString());
        assertEquals("ReN", reineNoir.toString());
    }
    @Test
	public void getDeplacementsPossTest() {
		boolean[][] deplacementsAttendus = { //                   centre
				{true, false, false, false, false, false, false, true, false, false, false, false, false, false, true},
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
				{true, false, false, false, false, false, false, true, false, false, false, false, false, false, true}
				};
		
		boolean[][] deplacementsCalcul�s = reineBlanche.getDeplacementsPoss();
		assertArrayEquals(deplacementsAttendus, deplacementsCalcul�s);
		
		deplacementsCalcul�s = reineNoir.getDeplacementsPoss();
		assertArrayEquals(deplacementsAttendus, deplacementsCalcul�s);
	}
    
    @Test
	public void equalsTest() {
	   Reine rb1 = new Reine(false);
	   Reine rb2 = new Reine(false);
	   Reine rn1 = new Reine(true);
	   Tour tb1 = new Tour(false);

	   // Cas particulier
	   assertFalse(rb1.equals(null));
	   assertFalse(rb1.equals(new Object()));
	   assertTrue(rb1.equals(rb1));
	   // Autres cas
	   assertTrue(rb1.equals(rb2));
	   assertFalse(rb1.equals(tb1));
	   assertFalse(rn1.equals(tb1));
	   assertFalse(rb1.equals(rn1));
	}
}
