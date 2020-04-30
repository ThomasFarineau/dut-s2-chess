package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TourTest {
    private Tour reineNoir;
    private Tour reineBlanche;

    @BeforeEach
    public void initialisation() {
        reineNoir = new Tour(true);
        reineBlanche = new Tour(false);
    }

    @Test
    public void toStringTest() {
        assertEquals("ReB", reineBlanche.toString());
        assertEquals("ReN", reineNoir.toString());
    }

}