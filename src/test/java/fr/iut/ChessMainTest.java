package fr.iut;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class ChessMainTest {
	private final static InputStream sysin = System.in;
	private final static PrintStream sysout = new PrintStream(System.out);
	
	private final static String inputOutputPath = "tests_input_output/";
	
	private static boolean premierRun = true;

	// Méthode permettant de vérifier que deux fichiers sont identiques, au niveau contenu
	private static void assertMemesFichiers(String cheminFichierAttendu, String cheminFichierResultat) throws Exception {
		BufferedReader scannerExpectedOutput = new BufferedReader(new FileReader(cheminFichierAttendu));
		BufferedReader scannerOutput = new BufferedReader(new FileReader(cheminFichierResultat));

		int i = 0;
		while (scannerExpectedOutput.ready()) {
			assertEquals(scannerExpectedOutput.readLine(), scannerOutput.readLine(), "Différence à la ligne " + ++i);
		}
		assertFalse(scannerOutput.ready());

		scannerOutput.close();
		scannerExpectedOutput.close();
	}

	// Méthode permettant de changer System.in et de réinitialiser les variables statiques de la classe Main
	private static void switchSysIn(InputStream is) {
		System.setIn(is);
		ChessMain.reInitVars();
	}
	
	private static File instanciateFileAndDeleteIfExists(String cheminFichier) {
		File f = new File(cheminFichier);
		if (f.exists())
			assertTrue(f.delete());
		return f;
	}
	
	private static String getInputFileName(String fileName) {
		return inputOutputPath + "input/" + fileName + "_input.txt";
	}
	
	private static String getOutputFileName(String fileName) {
		return inputOutputPath + "output/" + fileName + "_output.txt";
	}
	
	private static String getExpectedOutputFileName(String fileName) {
		return inputOutputPath + "expected_output/" + fileName + "_expected_output.txt";
	}
	
	private static void assertRightInputOutput(String fileName, Runnable r) {
		try {
			InputStream is = new FileInputStream(new File(getInputFileName(fileName)));
			if (premierRun) { // S'il s'agit du tout premier assert, il suffit de changer la valeur de System.in
				System.setIn(is);
				premierRun = false;
			} else {
				switchSysIn(is); // S'il s'agit d'un autre assert, il faut également rafraichir les variables du main
			}
			
			File ps_file = new File(getOutputFileName(fileName));
			ps_file.createNewFile();
			PrintStream ps = new PrintStream(ps_file);
			System.setOut(ps);

			r.run();

			ps.close();
			is.close();

			// Verification de la sortie du lancement de la méthode
			assertMemesFichiers(
					getExpectedOutputFileName(fileName), 
					getOutputFileName(fileName)
					);

			// Suppression du fichier créé par l'output
			assertTrue(ps_file.delete());
		} catch (Exception e) {
			// Si une Exception est levée à n'importe quel moment du test, le test échoue.
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void demanderRecommencerTest() {
		assertRightInputOutput("recommencer_1", 
				() -> assertTrue(ChessMain.demanderRecommencer()));
		
		assertRightInputOutput("recommencer_2", 
				() -> assertFalse(ChessMain.demanderRecommencer()));
		
		assertRightInputOutput("recommencer_3", 
				() -> assertFalse(ChessMain.demanderRecommencer()));
		
		assertRightInputOutput("recommencer_4", 
				() -> assertTrue(ChessMain.demanderRecommencer()));
	}

	@Test
	public void demanderTourJoueurTest() {
		assertRightInputOutput("demanderTour_1", 
				() -> assertFalse(ChessMain.demanderTourJoueur()));
		
		assertRightInputOutput("demanderTour_2", 
				() -> assertFalse(ChessMain.demanderTourJoueur()));
		
		assertRightInputOutput("demanderTour_3", 
				() -> assertFalse(ChessMain.demanderTourJoueur()));
		
		assertRightInputOutput("demanderTour_4", 
				() -> assertTrue(ChessMain.demanderTourJoueur()));
		
		assertRightInputOutput("demanderTour_5", 
				() -> assertTrue(ChessMain.demanderTourJoueur()));
		
		assertRightInputOutput("demanderTour_6", 
				() -> assertTrue(ChessMain.demanderTourJoueur()));
	}
	
	@Test
	public void initialisationConsoleTest() {
		assertRightInputOutput("initConsole_1",
				ChessMain::initialisationConsole);
		
		// A FINIR
	}
	
	// UNIQUEMENT DES SCENARIOS CONSOLE (car les scénarios Graphiques sont difficilement testables et n'affichent rien dans la console)
	@Test
	public void mainTest() {
		File matBerger = instanciateFileAndDeleteIfExists("parties/mat du berger.csv");
		File partieActuelle = instanciateFileAndDeleteIfExists("parties/partieActuelle.csv");
		File partieDeTest = instanciateFileAndDeleteIfExists("parties/maPartieDeTest.csv");
		
		// Scenario nouvelle partie (avec mat du berger blanc, enregistrer sous avant le mat du berger, et ne pas rejouer)
		assertRightInputOutput("main_1",
				() -> ChessMain.main(null));
		
		// Scenario avec chargement de mat_du_berger.csv, mat par les blancs et recommencement avec nouvelle partie
		// et autre mat blanc puis ne pas rejouer
		assertRightInputOutput("main_2",
				() -> ChessMain.main(null));
		
		// Scenario avec mat des noirs, recommencer avec chargement, puis cancel, puis chargements invalides,
		// Puis chargement berger, puis mat et ne pas rejouer
		assertRightInputOutput("main_3",
				() -> ChessMain.main(null));
		
		// Scenario avec début de jeu puis enregistrer puis enregistrer sous vers "maPartieDeTest.csv", puis enregistrer, puis quitter
		assertRightInputOutput("main_4",
				() -> ChessMain.main(null));
		
		// Scenario avec chargement de "partieActuelle.csv", puis avancement puis quitter
		assertRightInputOutput("main_5",
				() -> ChessMain.main(null));
		
		// Scenario avec chargement du "maPartieTest.csv" puis mouvements invalides avant mat et ne pas rejouer
		assertRightInputOutput("main_6",
				() -> ChessMain.main(null));
		
		// Scenario avec chargement avec mat blanc puis rejouer une nouvelle partie puis 
		// sauvegardes invalides ("sauvegarder ") et sauvegarder vers "nouvellePartie.csv"
		// Sauvegarde valide puis quitter en pleine partie
		
		
		// Suppression des fichiers créés par la méthode
		assertTrue(matBerger.delete());
		assertTrue(partieActuelle.delete());
		assertTrue(partieDeTest.delete());
	}

	@AfterAll
	public static void finalisation() {
		System.setIn(sysin);
		ChessMain.reInitVars();
		System.setOut(sysout);
	}
}
