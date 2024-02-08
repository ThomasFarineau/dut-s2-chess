package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KnightTest {
    private Knight knightBlanc;
    private Knight knightNoir;

    @BeforeEach
    public void initialisation() {
        knightBlanc = new Knight(false);
        knightNoir = new Knight(true);
    }

    @Test
    public void toStringTest() {
        assertEquals("Cb", knightBlanc.toString());
        assertEquals("Cn", knightNoir.toString());
    }

    @Test
    public void getDeplacementsPossTest() {
        boolean[][] deplacementsAttendus = { //                   centre
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, true, false, true, false, false, false, false, false, false},
                {false, false, false, false, false, true, false, false, false, true, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // centre
                {false, false, false, false, false, true, false, false, false, true, false, false, false, false, false},
                {false, false, false, false, false, false, true, false, true, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}
        };

        boolean[][] deplacementsCalcules = knightBlanc.getPossibleMoves();
        assertArrayEquals(deplacementsAttendus, deplacementsCalcules);

        deplacementsCalcules = knightNoir.getPossibleMoves();
        assertArrayEquals(deplacementsAttendus, deplacementsCalcules);
    }

    @Test
    public void equalsTest() {
        Knight cb1 = new Knight(false);
        Knight cb2 = new Knight(false);
        Knight cn3 = new Knight(true);
        Rook tb1 = new Rook(false);

        // Cas particulier
        assertFalse(cb1.equals(new Object()));
        assertFalse(cb1.equals(null));
        assertTrue(cb1.equals(cb1));
        // Autres cas particuliers
        assertFalse(cb1.equals(tb1));
        assertFalse(cn3.equals(tb1));
        assertFalse(cb1.equals(cn3));
        assertTrue(cb1.equals(cb2));

    }
}
