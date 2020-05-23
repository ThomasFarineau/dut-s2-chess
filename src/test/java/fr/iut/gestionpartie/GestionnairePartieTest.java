package fr.iut.gestionpartie;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.iut.pieces.Cavalier;
import fr.iut.pieces.Fou;
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
	public void chargerAnciennePartieExceptionsTest() {
		try {
			gp.chargerAnciennePartie("fichierInexistant.csv");
			//Si aucune exception n'est générée, le test échoue
			fail();
		} catch (Exception e) {
			assertEquals("Fichier introuvable : fichierInexistant.csv", e.getMessage());
		}
		
		try {
			gp.chargerAnciennePartie("fichierInexistant2");
			//Si aucune exception n'est générée, le test échoue
			fail();
		} catch (Exception e) {
			assertEquals("Fichier introuvable : fichierInexistant2.csv", e.getMessage());
		}
		
		try {
			gp.chargerAnciennePartie("tests/tropDeColonnes2.csv");
			//Si aucune exception n'est générée, le test échoue
			fail();
		} catch (Exception e) {
			assertEquals("Ligne 7 invalide dans le fichier tests/tropDeColonnes2.csv", e.getMessage());
		}
		
		try {
			gp.chargerAnciennePartie("tests/pieceInconnue.csv");
			//Si aucune exception n'est générée, le test échoue
			fail();
		} catch (Exception e) {
			assertEquals("Nom de pièce invalide poutre à la ligne 5 du fichier tests/pieceInconnue.csv", e.getMessage());
		}
	}
	
	@Test
	public void chargerAnciennePartieTest() {		
		//exemple de chargement valide
		try {
			gp.chargerAnciennePartie("tests/partieTest.csv");
		} catch (Exception e) {
			// Si une exception est générée, le test échoue
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
		try {
			gp.nouvellePartie();
		}catch(Exception e) {
			fail();
		}
		Piece[][] echiquierACharger ={
		{new Tour(true), new Cavalier(true), new Fou(true), new Reine(true), new Roi(true), new Fou(true), new Cavalier(true), new Tour(true)},
	    {new Pion(true), new Pion(true), new Pion(true), new Pion(true), new Pion(true), new Pion(true), new Pion(true), new Pion(true)},
	    {null, null, null, null, null, null, null, null},
	    {null, null, null, null, null, null, null, null},
	    {null, null, null, null, null, null, null, null},
	    {null, null, null, null, null, null, null, null},
	    {new Pion(false), new Pion(false), new Pion(false), new Pion(false), new Pion(false), new Pion(false), new Pion(false), new Pion(false)},
	    {new Tour(false), new Cavalier(false), new Fou(false), new Reine(false), new Roi(false), new Fou(false), new Cavalier(false), new Tour(false)}
		};
		
		assertArrayEquals(echiquierACharger, p.getEchiquier());

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
		Piece[][] echiquierASave ={
				{new Tour(true), new Cavalier(true), new Fou(true), new Reine(true), new Roi(true), new Fou(true), new Cavalier(true), new Tour(true)},
			    {new Pion(true), new Pion(true), new Pion(true), new Pion(true), new Pion(true), new Pion(true), new Pion(true), new Pion(true)},
			    {null, null, null, null, null, null, null, null},
			    {null, null, null, null, null, null, null, null},
			    {null, null, null, null, null, null, null, null},
			    {null, new Pion(false), null, null, null, null, null, null},
			    {new Pion(false), null, new Pion(false), new Pion(false), new Pion(false), new Pion(false), new Pion(false), new Pion(false)},
			    {new Tour(false), new Cavalier(false), new Fou(false), new Reine(false), new Roi(false), new Fou(false), new Cavalier(false), new Tour(false)}
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
				"Tn,Cn,Fn,ReN,RoN,Fn,Cn,Tn",
				"Pn,Pn,Pn,Pn,Pn,Pn,Pn,Pn",
				"V,V,V,V,V,V,V,V",
				"V,V,V,V,V,V,V,V",
				"V,V,V,V,V,V,V,V",
				"V,Pb,V,V,V,V,V,V",
				"Pb,V,Pb,Pb,Pb,Pb,Pb,Pb",
				"Tb,Cb,Fb,ReB,RoB,Fb,Cb,Tb"};
		Scanner scan;
		try {
			scan = new Scanner(new File("parties/tests/sauvegarde1.csv")); //a changer
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
}
