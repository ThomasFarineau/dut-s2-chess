/**
 * 
 */
package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author Remy
 *
 */
public class testPion {
	
	@Test
	public void testToStringPion() {
		Piece p1 = new Pion(false);
		Piece p2 = new Pion(true);
		assertEquals("Pb", p1.toString());
		assertEquals("Pn", p2.toString());
	}

}
