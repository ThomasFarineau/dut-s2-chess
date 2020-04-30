package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}