package fr.iut;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ChessTest {
    @Test
    public void helloMessage() {
        assertEquals("Welcome to the chess game.", new ChessMain().getHelloMessage());
    }
}
