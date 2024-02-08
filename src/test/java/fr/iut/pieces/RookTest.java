package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RookTest {
    private Rook rookNoir;
    private Rook rookBlanche;


    @BeforeEach
    public void initialisation() {
        rookNoir = new Rook(true);
        rookBlanche = new Rook(false);
    }

    @Test
    public void toStringTest() {
        assertEquals("Tb", rookBlanche.toString());
        assertEquals("Tn", rookNoir.toString());
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

        boolean[][] deplacementsCalcules = rookBlanche.getPossibleMoves();
        assertArrayEquals(deplacementsAttendus, deplacementsCalcules);

        deplacementsCalcules = rookNoir.getPossibleMoves();
        assertArrayEquals(deplacementsAttendus, deplacementsCalcules);
    }

    @Test
    public void equalsTest() {
        Rook tb1 = new Rook(false);
        Rook tb2 = new Rook(false);
        Rook tn1 = new Rook(true);
        Queen ren1 = new Queen(true);

        assertFalse(tb1.equals(new Object()));
        assertFalse(tb1.equals(null));
        assertTrue(tb1.equals(tb1));

        assertTrue(tb1.equals(tb2));
        assertFalse(tb1.equals(tn1));
        assertFalse(tb1.equals(ren1));
        assertFalse(tn1.equals(ren1));
    }

}