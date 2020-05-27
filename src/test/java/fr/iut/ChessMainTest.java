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

	@Test
	public void demanderRecommencerTest() {
		// Test 1
		try {
			// Execution de la méthode
			InputStream is1 = new FileInputStream(new File("tests_input_output/input/recommencer_1.txt"));
			System.setIn(is1); // On change le flux de System.in

			File ps1_file = new File("tests_input_output/output/recommencer_1_output.txt");
			ps1_file.createNewFile();
			PrintStream ps1 = new PrintStream(ps1_file);
			System.setOut(ps1);

			assertTrue(ChessMain.demanderRecommencer());

			ps1.close();
			is1.close();

			// Verification de la sortie du lancement de la méthode
			assertMemesFichiers(
					"tests_input_output/expected_output/recommencer_1_expected_output.txt", 
					"tests_input_output/output/recommencer_1_output.txt"
					);

			// Suppression du fichier créé par l'output
			ps1_file.delete();
		} catch (Exception e) {
			// Si une Exception est levée à n'importe quel moment du test, le test échoue.
			fail();
		}

		// Test 2
		try {
			// Execution de la méthode
			InputStream is2 = new FileInputStream(new File("tests_input_output/input/recommencer_2.txt"));
			switchSysIn(is2); // Pour les tests suivant le 1er, il faut réinitialiser le scanner en plus de changer System.in

			File ps2_file = new File("tests_input_output/output/recommencer_2_output.txt");
			ps2_file.createNewFile();
			PrintStream ps2 = new PrintStream(ps2_file);
			System.setOut(ps2);

			assertFalse(ChessMain.demanderRecommencer());

			ps2.close();
			is2.close();

			// Verification de la sortie du lancement de la méthode
			assertMemesFichiers(
					"tests_input_output/expected_output/recommencer_2_expected_output.txt", 
					"tests_input_output/output/recommencer_2_output.txt"
					);

			// Suppression du fichier créé par l'output
			ps2_file.delete();
		} catch (Exception e) {
			e.printStackTrace();
			// Si une Exception est levée à n'importe quel moment du test, le test échoue.
			fail();
		}


		// Test 3
		try {
			// Execution de la méthode
			InputStream is3 = new FileInputStream(new File("tests_input_output/input/recommencer_3.txt"));
			switchSysIn(is3); // Pour les tests suivant le 1er, il faut réinitialiser le scanner en plus de changer System.in

			File ps3_file = new File("tests_input_output/output/recommencer_3_output.txt");
			ps3_file.createNewFile();
			PrintStream ps3 = new PrintStream(ps3_file);
			System.setOut(ps3);

			assertFalse(ChessMain.demanderRecommencer());

			ps3.close();
			is3.close();

			// Verification de la sortie du lancement de la méthode
			assertMemesFichiers(
					"tests_input_output/expected_output/recommencer_3_expected_output.txt", 
					"tests_input_output/output/recommencer_3_output.txt"
					);

			// Suppression du fichier créé par l'output
			ps3_file.delete();
		} catch (Exception e) {
			e.printStackTrace();
			// Si une Exception est levée à n'importe quel moment du test, le test échoue.
			fail();
		}

		// Test 4
		try {
			// Execution de la méthode
			InputStream is4 = new FileInputStream(new File("tests_input_output/input/recommencer_4.txt"));
			switchSysIn(is4); // Pour les tests suivant le 1er, il faut réinitialiser le scanner en plus de changer System.in

			File ps4_file = new File("tests_input_output/output/recommencer_4_output.txt");
			ps4_file.createNewFile();
			PrintStream ps4 = new PrintStream(ps4_file);
			System.setOut(ps4);

			assertTrue(ChessMain.demanderRecommencer());

			ps4.close();
			is4.close();

			// Verification de la sortie du lancement de la méthode
			assertMemesFichiers(
					"tests_input_output/expected_output/recommencer_4_expected_output.txt", 
					"tests_input_output/output/recommencer_4_output.txt"
					);

			// Suppression du fichier créé par l'output
			ps4_file.delete();
		} catch (Exception e) {
			e.printStackTrace();
			// Si une Exception est levée à n'importe quel moment du test, le test échoue.
			fail();
		}
	}


	@AfterAll
	public static void finalisation() {
		System.setIn(sysin);
		ChessMain.reInitVars();
		System.setOut(sysout);
	}
}
