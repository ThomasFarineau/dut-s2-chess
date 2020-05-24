package fr.iut;

import static org.junit.jupiter.api.Assertions.*;

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

	@Test
	public void demanderRecommencerTest() {
		try {
			// Execution de la méthode
			InputStream is1 = new FileInputStream(new File("tests_input_output/input/recommencer_1.txt"));
			System.setIn(is1);

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
			e.printStackTrace();
			// Si une Exception est levée à n'importe quel moment du test, le test échoue.
			fail();
		}
	}


	@AfterAll
	public static void finalisation() {
		System.setIn(sysin);
		System.setOut(sysout);
	}
}
