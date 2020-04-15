package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.iut.pieces.Cavalier;
import fr.iut.pieces.Fou;
import fr.iut.pieces.Piece;
import fr.iut.pieces.Pion;
import fr.iut.pieces.Reine;
import fr.iut.pieces.Roi;
import fr.iut.pieces.Tour;

public class PieceTest {
	@Test
	public void testToStringCav() {
		Piece p1 = new Cavalier(false);
		Piece p2 = new Cavalier(true);
		assertEquals("Cb", p1.toString());
		assertEquals("Cn", p2.toString());
	}

	@Test
	public void testToStringTour() {
		Piece p1 = new Tour(false);
		Piece p2 = new Tour(true);
		assertEquals("Tb", p1.toString());
		assertEquals("Tn", p2.toString());
	}

	@Test
	public void testToStringRoi() {
		Piece p1 = new Roi(false);
		Piece p2 = new Roi(true);
		assertEquals("RoB", p1.toString());
		assertEquals("RoN", p2.toString());
	}

	@Test
	public void testToStringReine() {
		Piece p1 = new Reine(false);
		Piece p2 = new Reine(true);
		assertEquals("ReB", p1.toString());
		assertEquals("ReN", p2.toString());
	}

	@Test
	public void testToStringPion() {
		Piece p1 = new Pion(false);
		Piece p2 = new Pion(true);
		assertEquals("Pb", p1.toString());
		assertEquals("Pn", p2.toString());
	}

	@Test
	public void testToStringFou() {
		Piece p1 = new Fou(false);
		Piece p2 = new Fou(true);
		assertEquals("Fb", p1.toString());
		assertEquals("Fn", p2.toString());
	}

}
