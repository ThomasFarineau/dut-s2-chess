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
public class testRoi {
	
	@Test
	public void testToStringRoi() {
		Piece p1 = new Roi(false);
		Piece p2 = new Roi(true);
		assertEquals("RoB", p1.toString());
		assertEquals("RoN", p2.toString());
	}

}
