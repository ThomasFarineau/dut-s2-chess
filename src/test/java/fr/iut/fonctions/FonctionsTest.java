package fr.iut.fonctions;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FonctionsTest {

    @Test
    public void verifSyntaxeTest() {
        String entree = "a4 b25";
        assertFalse(Fonctions.verifSyntaxe(entree));

        entree = "a1 a";
        assertFalse(Fonctions.verifSyntaxe(entree));

        entree = "a2a3";
        assertFalse(Fonctions.verifSyntaxe(entree));

        entree = "A1 B2N";
        assertFalse(Fonctions.verifSyntaxe(entree));

        entree = "A1 B";
        assertFalse(Fonctions.verifSyntaxe(entree));

        entree = "A1B2";
        assertFalse(Fonctions.verifSyntaxe(entree));

        entree = "j1 l2";
        assertFalse(Fonctions.verifSyntaxe(entree));

        entree = "a0 a2";
        assertFalse(Fonctions.verifSyntaxe(entree));

        entree = "A1 G1";
        assertTrue(Fonctions.verifSyntaxe(entree));

        entree = "a1 a2";
        assertTrue(Fonctions.verifSyntaxe(entree));
    }

    @Test
    public void convertEnIndicesTest() {
        String entree = "a1 b2";
        int[] sortie = {7, 0, 6,1};
        assertArrayEquals(sortie, Fonctions.convertEnIndices(entree));

        entree = "C3 d4";
        int[] sortie2 = {5, 2, 4, 3};
        assertArrayEquals(sortie2, Fonctions.convertEnIndices(entree));

        entree = "e5 F6";
        int[] sortie3 = {3, 4, 2, 5};
        assertArrayEquals(sortie3, Fonctions.convertEnIndices(entree));

        entree = "G7 H8";
        int[] sortie4 = {1, 6, 0, 7};
        assertArrayEquals(sortie4, Fonctions.convertEnIndices(entree));
        
        entree = "B2 A1";
        int[] sortie5 = {6, 1, 7, 0};
        assertArrayEquals(sortie5, Fonctions.convertEnIndices(entree));

        entree = "D4 c3";
        int[] sortie6 = {4, 3, 5, 2};
        assertArrayEquals(sortie6, Fonctions.convertEnIndices(entree));

        entree = "F6 e5";
        int[] sortie7 = {2, 5, 3, 4};
        assertArrayEquals(sortie7, Fonctions.convertEnIndices(entree));
       
        entree = "h8 g7";
        int[] sortie8 = {0, 7, 1, 6};
        assertArrayEquals(sortie8, Fonctions.convertEnIndices(entree));
    }

    @Test
    public void convertEnCaracteresTest() {
        char[] sortie = {'A', '1', 'B', '2'};
        int[] entree = {7, 0, 6, 1};
        assertArrayEquals(sortie, Fonctions.convertEnCaracteres(entree));

        char[] sortie2 = {'C', '3', 'D', '4'};
        int[] entree2 = {5, 2, 4, 3};
        assertArrayEquals(sortie2, Fonctions.convertEnCaracteres(entree2));

        char[] sortie3 = {'E', '5', 'F', '6'};
        int[] entree3 = {3, 4, 2, 5};
        assertArrayEquals(sortie3, Fonctions.convertEnCaracteres(entree3));

        char[] sortie4 = {'G', '7', 'H', '8'};
        int[] entree4 = {1, 6, 0, 7};
        assertArrayEquals(sortie4, Fonctions.convertEnCaracteres(entree4));

        char[] sortie5 = {'B', '2', 'A', '1'};
        int[] entree5 = {6, 1, 7, 0};
        assertArrayEquals(sortie5, Fonctions.convertEnCaracteres(entree5));

        char[] sortie6 = {'D', '4', 'C', '3'};
        int[] entree6 = {4, 3, 5, 2};
        assertArrayEquals(sortie6, Fonctions.convertEnCaracteres(entree6));
        
        char[] sortie7 = {'F', '6', 'E', '5'};
        int[] entree7 = {2, 5, 3, 4};
        assertArrayEquals(sortie7, Fonctions.convertEnCaracteres(entree7));
        
        char[] sortie8 = {'H', '8', 'G', '7'};
        int[] entree8 = {0, 7, 1, 6};
        assertArrayEquals(sortie8, Fonctions.convertEnCaracteres(entree8));
    }

    @Test
    public void convertCheminRelatif() {
        String cheminAbsolu = System.getProperty("user.dir") + "/parties";
        assertEquals("", Fonctions.convertCheminRelatif(cheminAbsolu));

        cheminAbsolu = System.getProperty("user.dir") + "/tests_input_output";
        assertEquals("../tests_input_output", Fonctions.convertCheminRelatif(cheminAbsolu));

        cheminAbsolu = System.getProperty("user.dir") + "/src/main/java/fr/iut/plateau";
        assertEquals("../src/main/java/fr/iut/plateau", Fonctions.convertCheminRelatif(cheminAbsolu));

        cheminAbsolu = System.getProperty("user.dir") + "/src/test/java";
        assertEquals("../src/test/java", Fonctions.convertCheminRelatif(cheminAbsolu));

        cheminAbsolu = System.getProperty("user.dir") + "/parties/tests";
        assertEquals("tests", Fonctions.convertCheminRelatif(cheminAbsolu));
    }
}
