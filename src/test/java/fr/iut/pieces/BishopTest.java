package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BishopTest {

    private Bishop bishopNoir;
    private Bishop bishopBlanc;

    @BeforeEach
    public void initialisation() {
        bishopNoir = new Bishop(true);
        bishopBlanc = new Bishop(false);
    }

    @Test
    public void toStringTest() {
        assertEquals("Fb", bishopBlanc.toString());
        assertEquals("Fn", bishopNoir.toString());
    }

    @Test
    public void getDeplacementsPossTest() {
        boolean[][] deplacementsAttendus = { //                   centre
                {true, false, false, false, false, false, false, false, false, false, false, false, false, false, true},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // centre
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {true, false, false, false, false, false, false, false, false, false, false, false, false, false, true}
        };

        boolean[][] deplacementsCalcules = bishopBlanc.getPossibleMoves();
        assertArrayEquals(deplacementsAttendus, deplacementsCalcules);

        deplacementsCalcules = bishopNoir.getPossibleMoves();
        assertArrayEquals(deplacementsAttendus, deplacementsCalcules);
    }

    @Test
    public void equalsTest() {
        Bishop fb1 = new Bishop(false);
        Bishop fb2 = new Bishop(false);
        Bishop fn3 = new Bishop(true);
        Rook tb1 = new Rook(false);

        // Cas particulier
        assertFalse(fb1.equals(null));
        assertFalse(fb1.equals(new Object()));
        assertTrue(fb1.equals(fb1));
        // Autres cas particuliers
        assertFalse(fb1.equals(tb1));
        assertFalse(fn3.equals(tb1));
        assertFalse(fb1.equals(fn3));
        assertTrue(fb1.equals(fb2));
    }
}
