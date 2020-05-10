package fr.iut.plateau;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.iut.gestionpartie.GestionnairePartie;
import fr.iut.pieces.Piece;
import fr.iut.plateau.Plateau;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlateauTest {
	private Plateau plat;
	private GestionnairePartie gp;
	
	@BeforeEach
	public void initialisation( ) {
		plat = new Plateau();
		gp = new GestionnairePartie(plat);
	}
	
	@Test
	public void toStringTest() {
		try {
			gp.chargerAnciennePartie("partieTest.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("8 RoB  -  ReN  -   -   -   -   -  \r\n" + 
				"7 Pb  Tb   -   -   -  ReN  -   -  \r\n" + 
				"6  -   -   -   -  Pb   -   -   -  \r\n" + 
				"5  -   -   -   -   -   -   -  RoN \r\n" + 
				"4  -   -   -   -   -   -   -   -  \r\n" + 
				"3  -   -   -   -   -   -   -   -  \r\n" + 
				"2  -   -   -   -   -   -   -   -  \r\n" + 
				"1  -   -   -   -   -   -   -   -  \r\n" + 
				"   A   B   C   D   E   F   G   H", plat.toString());	
	}
}
