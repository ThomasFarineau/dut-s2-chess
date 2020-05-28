package fr.iut.fonctions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FonctionsTest {
	
	@Test
	public void verifSyntaxeTest() {
		String entree= "a4 b25";
		assertFalse(Fonctions.verifSyntaxe(entree));
		
		entree="a1 a";
		assertFalse(Fonctions.verifSyntaxe(entree));
		
		entree="a2a3";
		assertFalse(Fonctions.verifSyntaxe(entree));
		
		entree="A1 B2N";
		assertFalse(Fonctions.verifSyntaxe(entree));
		
		entree="A1 B";
		assertFalse(Fonctions.verifSyntaxe(entree));
		
		entree="A1B2";
		assertFalse(Fonctions.verifSyntaxe(entree));
		
		entree="j1 l2";
		assertFalse(Fonctions.verifSyntaxe(entree));
		
		entree="a0 a2";
		assertFalse(Fonctions.verifSyntaxe(entree));
		
		entree="A1 G1";
		assertTrue(Fonctions.verifSyntaxe(entree));
		
		entree="a1 a2";
		assertTrue(Fonctions.verifSyntaxe(entree));
	}
	
	@Test
	public void convertCheminRelatif() {
		String cheminAbsolu = System.getProperty("user.dir") + "/parties";
		assertEquals("", Fonctions.convertCheminRelatif(cheminAbsolu));
		
		cheminAbsolu = System.getProperty("user.dir") + "/tests_input_output";
		assertEquals("../tests_input_output", Fonctions.convertCheminRelatif(cheminAbsolu));
	}
}
