package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
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

	@Test
	public void getDeplacementsPossTest() {
		boolean[][] deplacementsAttendus = { //                   centre
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, true, false, true, false, false, false, false, false, false},
				{false, false, false, false, false, true, false, false, false, true, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // centre
				{false, false, false, false, false, true, false, false, false, true, false, false, false, false, false},
				{false, false, false, false, false, false, true, false, true, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}};
		
		boolean[][] deplacementsCalculés = cavalierBlanc.getDeplacementsPoss();
		assertArrayEquals(deplacementsAttendus, deplacementsCalculés);
		
		deplacementsCalculés = cavalierNoir.getDeplacementsPoss();
		assertArrayEquals(deplacementsAttendus, deplacementsCalculés);
	}
}
