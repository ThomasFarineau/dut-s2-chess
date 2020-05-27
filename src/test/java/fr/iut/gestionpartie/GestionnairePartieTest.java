package fr.iut.gestionpartie;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
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
			assertEquals("Fichier introuvable : \"fichierInexistant.csv\"", e.getMessage());
		}

		try {
			gp.chargerAnciennePartie("fichierInexistant2");
			//Si aucune exception n'est générée, le test échoue
			fail();
		} catch (Exception e) {
			assertEquals("Fichier introuvable : \"fichierInexistant2.csv\"", e.getMessage());
		}

		try {
			gp.chargerAnciennePartie("tests/tropDeColonnes2.csv");
			//Si aucune exception n'est générée, le test échoue
			fail();
		} catch (Exception e) {
			assertEquals("Ligne 7 invalide dans le fichier \"tests/tropDeColonnes2.csv\"", e.getMessage());
		}

		try {
			gp.chargerAnciennePartie("tests/pieceInconnue.csv");
			//Si aucune exception n'est générée, le test échoue
			fail();
		} catch (Exception e) {
			assertEquals("Nom de pièce invalide \"poutre\" à la ligne 5 du fichier \"tests/pieceInconnue.csv\"", e.getMessage());
		}

		try {
			gp.chargerAnciennePartie("tests/formatInvalide.csv");
			//Si aucune exception n'est générée, le test échoue
			fail();
		} catch (Exception e) {
			assertEquals("Format du fichier \"tests/formatInvalide.csv\" invalide", e.getMessage());
		}
		
		try {
			gp.chargerAnciennePartie("nouvellePartie.csv");
			//Si aucune exception n'est générée, le test échoue
			fail();
		} catch (Exception e) {
			assertEquals("Vous n'avez pas le droit de charger le fichier \"nouvellePartie.csv\".", e.getMessage());
		}
		
		try {
			gp.chargerAnciennePartie("tests/../noUvEllEpartiE");
			//Si aucune exception n'est générée, le test échoue
			fail();
		} catch (Exception e) {
			assertEquals("Vous n'avez pas le droit de charger le fichier \"nouvellePartie.csv\".", e.getMessage());
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
		assertEquals("tests/partieTest.csv", gp.getNomFichier());
		
		
		try {
			gp.chargerAnciennePartie("tests/test28.csv");
		} catch (Exception e) {
			// Si une exception est générée, le test échoue
			fail();
		}

		echiquierAttendu = new Piece[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, new Tour(false), null, null, null, null},
				{null, null, new Roi(true), null, new Pion(false), null, null, null},
				{null, new Pion(false), null, null, null, null, null, null},
				{null, null, null, new Fou(false), null, null, null, null},
				{null, null, null, null, new Roi(false), null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, new Tour(true)}
		};

		assertArrayEquals(echiquierAttendu, p.getEchiquier());
		assertEquals("tests/test28.csv", gp.getNomFichier());
		
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
		assertEquals(gp.getNomFichier(),"partieActuelle.csv");
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

		String[] echiquierAttendu= {
				"RoB,V,ReN,V,V,V,V,V",
				"Pb,Tb,V,V,V,ReN,V,V",
				"V,V,V,V,Pb,V,V,V",
				"V,V,V,V,V,V,V,RoN",
				"V,V,V,V,V,V,V,V",
				"V,V,V,V,V,V,V,V",
				"V,V,V,V,V,V,V,V",
		"V,V,V,V,V,V,V,V"};

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

		p.setEchiquier(echiquierASave);

		try {
			gp.sauvegarderPartie("tests/nouvellePartie.csv");
			//Si aucune exception n'est générée, le test échoue
			fail();
		} catch (Exception e) {
			assertEquals("Vous n'avez pas le droit de sauvegarder une partie de nom \"nouvellePartie.csv\".", e.getMessage());
		}

		try {
			gp.sauvegarderPartie("nouvellepartie.csv");
			//Si aucune exception n'est générée, le test échoue
			fail();
		} catch (Exception e) {
			assertEquals("Vous n'avez pas le droit de sauvegarder une partie de nom \"nouvellePartie.csv\".", e.getMessage());
		}

		try {
			gp.sauvegarderPartie("../parties/nouvEllePartie");
			//Si aucune exception n'est générée, le test échoue
			fail();
		} catch (Exception e) {
			assertEquals("Vous n'avez pas le droit de sauvegarder une partie de nom \"nouvellePartie.csv\".", e.getMessage());
		}
		
		File f = new File("parties/tests/sauvegarde1.csv");
		if(f.exists())
			f.delete();
		
		try {
			String retourAttendu ="Le fichier \"tests/sauvegarde1.csv\" vient d'être créé. "+
					"La sauvegarde vers \"tests/sauvegarde1.csv\" a été effectuée avec succès !";
			assertEquals(retourAttendu,gp.sauvegarderPartie("tests/sauvegarde1.csv"));
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
			scan = new Scanner(f); 

			for(String s : echiquierAttendu) {
				assertEquals(s, scan.nextLine());
			}

			assertFalse(scan.hasNextLine());
			scan.close();
			assertEquals("tests/sauvegarde1.csv", gp.getNomFichier());
		} catch (Exception e) {
			fail();
		}

		try {
			p.setTourJoueur(true);
			p.deplacer(new int[] {0, 1, 2, 0});
			String retourAttendu = "La sauvegarde vers \"tests/sauvegarde1.csv\" a été effectuée avec succès !";
			assertEquals(retourAttendu, gp.sauvegarderPartie("tests/sauvegarde1.csv"));
		} catch (Exception e) {
			fail();
		}

		String[] echiquierAttendu2= {
				"Tn,V,Fn,ReN,RoN,Fn,Cn,Tn",
				"Pn,Pn,Pn,Pn,Pn,Pn,Pn,Pn",
				"Cn,V,V,V,V,V,V,V",
				"V,V,V,V,V,V,V,V",
				"V,V,V,V,V,V,V,V",
				"V,Pb,V,V,V,V,V,V",
				"Pb,V,Pb,Pb,Pb,Pb,Pb,Pb",
		"Tb,Cb,Fb,ReB,RoB,Fb,Cb,Tb"};

		try {
			scan = new Scanner(f);

			for(String s : echiquierAttendu2) {
				assertEquals(s, scan.nextLine());
			}

			assertFalse(scan.hasNextLine());
			scan.close();
			assertEquals("tests/sauvegarde1.csv", gp.getNomFichier());
			f.delete();
		} catch (Exception e) {
			fail();
		}
	}	
	
	@Test 
	public void getNomFichierTest() {
		try {
			gp.nouvellePartie();			
		} catch (IOException e) {}
		assertEquals("partieActuelle.csv", gp.getNomFichier()); 
		
		try {
			gp.chargerAnciennePartie("tests/test1.csv");		
		} catch (Exception e) {}
		assertEquals("tests/test1.csv", gp.getNomFichier()); 
		
		try {
			gp.sauvegarderPartie("savGetNomTest");
		} catch (Exception e) {}
		assertEquals("savGetNomTest.csv", gp.getNomFichier());
		
		try {
			new File("parties/partieActuelle.csv").delete();
			new File("parties/savGetNomTest.csv").delete();
		} catch (Exception e) {}
	}
	
	@Test 
	public void getPartiesPathTest() {
		assertEquals(Paths.get(System.getProperty("user.dir") + "/parties"), GestionnairePartie.getPartiesPath());
	}
}
