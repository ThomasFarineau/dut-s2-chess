package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PawnTest {

    private Pawn pawnNoir;
    private Pawn pawnBlanc;

    @BeforeEach
    public void initialisation() {
        pawnNoir = new Pawn(true);
        pawnBlanc = new Pawn(false);
    }

    @Test
    public void toStringTest() {

        assertEquals("Pb", pawnBlanc.toString());
        assertEquals("Pn", pawnNoir.toString());
    }

    @Test
    public void getDeplacementsPossPionBlancTest() {
        boolean[][] deplacementsAttendus = { //                   centre
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, true, true, true, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // centre
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}
        };

        boolean[][] deplacementsCalcules = pawnBlanc.getPossibleMoves();
        assertArrayEquals(deplacementsAttendus, deplacementsCalcules);
    }


    @Test
    public void getDeplacementsPossPionNoirTest() {
        boolean[][] deplacementsAttendus = { //                   centre
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // centre
                {false, false, false, false, false, false, true, true, true, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}
        };


        boolean[][] deplacementsCalcules = pawnNoir.getPossibleMoves();
        assertArrayEquals(deplacementsAttendus, deplacementsCalcules);
    }

    @Test
    public void equalsTest() {
        Pawn pb1 = new Pawn(false);
        Pawn pb2 = new Pawn(false);
        Pawn pn1 = new Pawn(true);
        Rook tb1 = new Rook(false);

        // Cas particulier
        assertFalse(pb1.equals(null));
        assertFalse(pb1.equals(new Object()));
        assertTrue(pb1.equals(pb1));
        // Autres cas
        assertTrue(pb1.equals(pb2));
        assertFalse(pb1.equals(tb1));
        assertFalse(pn1.equals(tb1));
        assertFalse(pb1.equals(pn1));
    }

}
