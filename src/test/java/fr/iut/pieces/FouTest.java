
package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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

}
