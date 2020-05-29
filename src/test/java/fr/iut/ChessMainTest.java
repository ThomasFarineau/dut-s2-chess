package fr.iut;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class ChessMainTest {
	private final static InputStream sysin = System.in;
	private final static PrintStream sysout = new PrintStream(System.out);
	
	private final static String inputOutputPath = "tests_input_output/";

	// Méthode permettant de vérifier que deux fichiers sont identiques, au niveau contenu
	private static void assertMemesFichiers(String cheminFichierAttendu, String cheminFichierResultat) throws Exception {
		Scanner scannerExpectedOutput = new Scanner(new File(cheminFichierAttendu));
		Scanner scannerOutput = new Scanner(new File(cheminFichierResultat));

		while (scannerExpectedOutput.hasNextLine()) {
			assertEquals(scannerExpectedOutput.nextLine(), scannerOutput.nextLine());
		}
		assertFalse(scannerOutput.hasNextLine());

		scannerOutput.close();
		scannerExpectedOutput.close();
	}

	// Méthode permettant de changer System.in et de réinitialiser les variables statiques de la classe Main
	private static void switchSysIn(InputStream is) {
		System.setIn(is);
		ChessMain.reInitVars();
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
	
	private static void assertRightInputOutput(String fileName, Runnable r, boolean veryFirstAssert) {
		try {
			InputStream is = new FileInputStream(new File(getInputFileName(fileName)));
			if (veryFirstAssert) { // S'il s'agit du tout premier assert, il suffit de changer la valeur de System.in
				System.setIn(is);
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
			ps_file.delete();
		} catch (Exception e) {
			// Si une Exception est levée à n'importe quel moment du test, le test échoue.
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void demanderRecommencerTest() {
		assertRightInputOutput("recommencer_1", 
				() -> assertTrue(ChessMain.demanderRecommencer()),
				true);
		
		assertRightInputOutput("recommencer_2", 
				() -> assertFalse(ChessMain.demanderRecommencer()),
				false);
		
		assertRightInputOutput("recommencer_3", 
				() -> assertFalse(ChessMain.demanderRecommencer()),
				false);
		
		assertRightInputOutput("recommencer_4", 
				() -> assertTrue(ChessMain.demanderRecommencer()),
				false);
	}

	@Test
	public void demanderTourJoueurTest() {
		// A FAIRE
	}

	@AfterAll
	public static void finalisation() {
		System.setIn(sysin);
		ChessMain.reInitVars();
		System.setOut(sysout);
	}
}
