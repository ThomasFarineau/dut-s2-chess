package fr.iut.gestionpartie;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.iut.pieces.Piece;
import fr.iut.plateau.Plateau;

public class GestionnairePartieTest {
	private Plateau p;
	private GestionnairePartie gp;
	
	@BeforeEach
    public void init() {
		p = new Plateau();
		gp = new GestionnairePartie(p);
	}
	
	@Test
	public void chargerAnciennePartieTest() {
		//exemple de chargement qui echoue
		try {
			gp.chargerAnciennePartie("fichierInexistant.csv");
			//Si aucune exception n'est générée, le test échoue
			fail();
		} catch (Exception e) {
			assertEquals("Insérer le message d'erreur attendu ici", e.getMessage());
		}
		
		//exemple de chargement valide
		try {
			gp.chargerAnciennePartie("tests/partieTest.csv");
		} catch (Exception e) {
			// Si une exception est générée, le test échoue
			fail();
		}
		
		// A modifier
		Piece[][] echiquierAttendu = {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null}
		};
		
		assertArrayEquals(echiquierAttendu, p.getEchiquier());
		
		// A FINIR
    }
	
	@Test
	public void sauvegarderPartieTest() {
		// PAREIL
	}
	
	//TESTER LES AUTRES METHODES AUSSI
}
