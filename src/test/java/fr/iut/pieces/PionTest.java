  
package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PionTest {
	
	private Pion pionNoir;
    private Pion pionBlanc;

    @BeforeEach
    public void initialisation() {
        pionNoir = new Pion(true);
        pionBlanc = new Pion(false);
    }
	
	@Test
	public void toStringTest() {
	
		assertEquals("Pb", pionBlanc.toString());
		assertEquals("Pn", pionNoir.toString());
	}

}
