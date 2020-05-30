
package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

	@Test
	public void getDeplacementsPossPionBlancTest() {
		boolean[][] deplacementsAttendus = { //                   centre
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, true, true, true, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // centre
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}
		};

		boolean[][] deplacementsCalcules = pionBlanc.getDeplacementsPoss();
		assertArrayEquals(deplacementsAttendus, deplacementsCalcules);
	}


		@Test
		public void getDeplacementsPossPionNoirTest() {
			boolean [][] deplacementsAttendus = { //                   centre
					{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
					{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
					{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
					{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
					{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
					{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
					{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
					{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // centre
					{false, false, false, false, false, false, true, true, true, false, false, false, false, false, false},
					{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
					{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
					{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
					{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
					{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
					{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}
			};


			boolean[][] deplacementsCalcules = pionNoir.getDeplacementsPoss();
			assertArrayEquals(deplacementsAttendus, deplacementsCalcules);
		}
		
		@Test
		public void equalsTest() {
		   Pion pb1 = new Pion(false);
		   Pion pb2 = new Pion(false);
		   Pion pn1 = new Pion(true);
		   Tour tb1 = new Tour(false);

		   // Cas particulier
		   assertFalse(pb1.equals(null));
		   assertFalse(pb1.equals(new Object()));
		   assertTrue(pb1.equals(pb1));
		   // Autres cas
		   assertTrue(pb1.equals(pb2));
		   assertFalse(pb1.equals(tb1));
		   assertFalse(pn1.equals(tb1));
		   assertFalse(pb1.equals(pn1));
		}

	}
