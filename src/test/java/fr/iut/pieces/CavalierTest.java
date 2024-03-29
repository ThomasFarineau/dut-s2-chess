package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CavalierTest {
    private Cavalier cavalierBlanc;
    private Cavalier cavalierNoir;

    @BeforeEach
    public void initialisation() {
        cavalierBlanc = new Cavalier(false);
        cavalierNoir = new Cavalier(true);
    }

    @Test
    public void toStringTest() {
        assertEquals("Cb", cavalierBlanc.toString());
        assertEquals("Cn", cavalierNoir.toString());
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

        boolean[][] deplacementsCalcules = cavalierBlanc.getDeplacementsPoss();
        assertArrayEquals(deplacementsAttendus, deplacementsCalcules);

        deplacementsCalcules = cavalierNoir.getDeplacementsPoss();
        assertArrayEquals(deplacementsAttendus, deplacementsCalcules);
    }

    @Test
    public void equalsTest() {
        Cavalier cb1 = new Cavalier(false);
        Cavalier cb2 = new Cavalier(false);
        Cavalier cn3 = new Cavalier(true);
        Tour tb1 = new Tour(false);

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
