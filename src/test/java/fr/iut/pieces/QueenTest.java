package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QueenTest {
    private Queen queenNoir;
    private Queen queenBlanche;

    @BeforeEach
    public void initialisation() {
        queenNoir = new Queen(true);
        queenBlanche = new Queen(false);
    }

    @Test
    public void toStringTest() {
        assertEquals("ReB", queenBlanche.toString());
        assertEquals("ReN", queenNoir.toString());
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

        boolean[][] deplacementsCalcules = queenBlanche.getDeplacementsPoss();
        assertArrayEquals(deplacementsAttendus, deplacementsCalcules);

        deplacementsCalcules = queenNoir.getDeplacementsPoss();
        assertArrayEquals(deplacementsAttendus, deplacementsCalcules);
    }

    @Test
    public void equalsTest() {
        Queen rb1 = new Queen(false);
        Queen rb2 = new Queen(false);
        Queen rn1 = new Queen(true);
        Rook tb1 = new Rook(false);

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
