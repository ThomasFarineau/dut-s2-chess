package fr.iut.gestionpartie;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.iut.pieces.Piece;
import fr.iut.pieces.Pion;
import fr.iut.pieces.Reine;
import fr.iut.pieces.Roi;
import fr.iut.pieces.Tour;
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
			//Si aucune exception n'est g�n�r�e, le test �choue
			fail();
		} catch (Exception e) {
			assertEquals("Fichier introuvable : fichierInexistant.csv", e.getMessage());
		}

		//exemple de chargement valide
		try {
			gp.chargerAnciennePartie("tests/partieTest.csv");
		} catch (Exception e) {
			// Si une exception est g�n�r�e, le test �choue
			fail();
		}

		Piece[][] echiquierAttendu = {
				{new Roi(false), null, new Reine(true), null, null, null, null, null},
				{new Pion(false), new Tour(false), null, null, null, new Reine(true), null, null},
				{null, null, null, null, new Pion(false), null, null, null},
				{null, null, null, null, null, null, null, new Roi(true)},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null}
		};

		assertArrayEquals(echiquierAttendu, p.getEchiquier());

	}

	@Test
	public void nouvellePartieTest() {
		// a faire
	}
	
	@Test
	public void sauvegarderPartieSansParamTest() {
		Piece[][] echiquierASave = {
				{new Roi(false), null, new Reine(true), null, null, null, null, null},
				{new Pion(false), new Tour(false), null, null, null, new Reine(true), null, null},
				{null, null, null, null, new Pion(false), null, null, null},
				{null, null, null, null, null, null, null, new Roi(true)},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null}
		};
		Plateau aSave = new Plateau();
		aSave.setEchiquier(echiquierASave);
		GestionnairePartie gp2= new GestionnairePartie(aSave);
		try {
			gp2.sauvegarderPartie("tests/sauvegarde1.csv");
		} catch (Exception e) {
			fail();
		}

		String[] echiquierAttendu= {
				"RoB,V,ReN,V,V,V,V,V",
				"Pb,Tb,V,V,V,ReN,V,V",
				"V,V,V,V,Pb,V,V,V",
				"V,V,V,V,V,V,V,RoN",
				"V,V,V,V,V,V,V,V",
				"V,V,V,V,V,V,V,V",
				"V,V,V,V,V,V,V,V",
		"V,V,V,V,V,V,V,V"};
		Scanner scan;
		try {
			scan = new Scanner(new File("parties/tests/sauvegarde1.csv"));
			int id=0;
			while (scan.hasNext()) {
				String ligneString = scan.nextLine();
				assertEquals(ligneString, echiquierAttendu[id]);
				id++;
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void sauvegarderPartieAvecParamTest() {
		//a faire
	}

}
