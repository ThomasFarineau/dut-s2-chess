package fr.iut.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

        boolean[][] deplacementsCalcules = tourBlanche.getDeplacementsPoss();
        assertArrayEquals(deplacementsAttendus, deplacementsCalcules);

        deplacementsCalcules = tourNoir.getDeplacementsPoss();
        assertArrayEquals(deplacementsAttendus, deplacementsCalcules);
    }

    @Test
    public void equalsTest() {
        Tour tb1 = new Tour(false);
        Tour tb2 = new Tour(false);
        Tour tn1 = new Tour(true);
        Reine ren1 = new Reine(true);

        assertFalse(tb1.equals(new Object()));
        assertFalse(tb1.equals(null));
        assertTrue(tb1.equals(tb1));

        assertTrue(tb1.equals(tb2));
        assertFalse(tb1.equals(tn1));
        assertFalse(tb1.equals(ren1));
        assertFalse(tn1.equals(ren1));
    }

}