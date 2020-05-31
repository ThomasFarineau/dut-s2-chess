package fr.iut.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class FouTest {

    private Fou fouNoir;
    private Fou fouBlanc;

    @BeforeEach
    public void initialisation() {
        fouNoir = new Fou(true);
        fouBlanc = new Fou(false);
    }

    @Test
    public void toStringTest() {
        assertEquals("Fb", fouBlanc.toString());
        assertEquals("Fn", fouNoir.toString());
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

        boolean[][] deplacementsCalcules = fouBlanc.getDeplacementsPoss();
        assertArrayEquals(deplacementsAttendus, deplacementsCalcules);

        deplacementsCalcules = fouNoir.getDeplacementsPoss();
        assertArrayEquals(deplacementsAttendus, deplacementsCalcules);
    }

    @Test
    public void equalsTest() {
        Fou fb1 = new Fou(false);
        Fou fb2 = new Fou(false);
        Fou fn3 = new Fou(true);
        Tour tb1 = new Tour(false);

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
