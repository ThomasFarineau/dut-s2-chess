package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KingTest {

    private King kingNoir;
    private King kingBlanc;

    @BeforeEach
    public void initialisation() {
        kingNoir = new King(true);
        kingBlanc = new King(false);
    }

    @Test
    public void testToStringRoi() {
        assertEquals("RoB", kingBlanc.toString());
        assertEquals("RoN", kingNoir.toString());
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

        boolean[][] deplacementsCalcules = kingBlanc.getPossibleMoves();
        assertArrayEquals(deplacementsAttendus, deplacementsCalcules);

        deplacementsCalcules = kingNoir.getPossibleMoves();
        assertArrayEquals(deplacementsAttendus, deplacementsCalcules);
    }

    @Test
    public void equalsTest() {
        King rb1 = new King(false);
        King rb2 = new King(false);
        King rn1 = new King(true);
        Queen ren1 = new Queen(true);

        assertFalse(rb1.equals(null));
        assertFalse(rb1.equals(new Object()));
        assertTrue(rb1.equals(rb1));

        assertFalse(rb1.equals(rn1));
        assertTrue(rb1.equals(rb2));
        assertFalse(rb1.equals(ren1));
        assertFalse(rn1.equals(ren1));

    }


}
