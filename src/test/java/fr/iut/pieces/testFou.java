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
public class testFou {
	
	@Test
	public void testToStringFou() {
		Piece p1 = new Fou(false);
		Piece p2 = new Fou(true);
		assertEquals("Fb", p1.toString());
		assertEquals("Fn", p2.toString());
	}

}
