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
		
		boolean[][] deplacementsCalculés = reineBlanche.getDeplacementsPoss();
		assertArrayEquals(deplacementsAttendus, deplacementsCalculés);
		
		deplacementsCalculés = reineNoir.getDeplacementsPoss();
		assertArrayEquals(deplacementsAttendus, deplacementsCalculés);
	}
}
