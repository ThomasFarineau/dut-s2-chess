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
			gp.chargerAnciennePartie("tests/partieTest.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String valeurAttendue = "8 RoB  -  ReN  -   -   -   -   -  \n" + 
				"7 Pb  Tb   -   -   -  ReN  -   -  \n" + 
				"6  -   -   -   -  Pb   -   -   -  \n" + 
				"5  -   -   -   -   -   -   -  RoN \n" + 
				"4  -   -   -   -   -   -   -   -  \n" + 
				"3  -   -   -   -   -   -   -   -  \n" + 
				"2  -   -   -   -   -   -   -   -  \n" + 
				"1  -   -   -   -   -   -   -   -  \n" + 
				"   A   B   C   D   E   F   G   H";
		assertEquals(valeurAttendue, plat.toString());	
	}
}
