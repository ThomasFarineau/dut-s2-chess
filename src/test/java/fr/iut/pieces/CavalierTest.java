package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.iut.pieces.Cavalier;

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
}
