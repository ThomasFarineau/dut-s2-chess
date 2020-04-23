package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.iut.pieces.Cavalier;

public class CavalierTest {
	private Cavalier cavalierNoir;
	private Cavalier cavalierBlanc;
	
	@BeforeEach
	public void initialisation() {
		cavalierNoir = new Cavalier(true);
		cavalierBlanc = new Cavalier(false);
	}

	@Test
	public void toStringTest() {
		assertEquals("Cb", cavalierBlanc.toString());
		assertEquals("Cn", cavalierNoir.toString());
	}
}
