package fr.iut.fonctions;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    public void verifSyntaxeTest() {
        String entree = "a4 b25";
        assertFalse(Utils.checkSyntax(entree));

        entree = "a1 a";
        assertFalse(Utils.checkSyntax(entree));

        entree = "a2a3";
        assertFalse(Utils.checkSyntax(entree));

        entree = "A1 B2N";
        assertFalse(Utils.checkSyntax(entree));

        entree = "A1 B";
        assertFalse(Utils.checkSyntax(entree));

        entree = "A1B2";
        assertFalse(Utils.checkSyntax(entree));

        entree = "j1 l2";
        assertFalse(Utils.checkSyntax(entree));

        entree = "a0 a2";
        assertFalse(Utils.checkSyntax(entree));

        entree = "A1 G1";
        assertTrue(Utils.checkSyntax(entree));

        entree = "a1 a2";
        assertTrue(Utils.checkSyntax(entree));
    }

    @Test
    public void convertEnIndicesTest() {
        String entree = "a1 b2";
        int[] sortie = {7, 0, 6,1};
        assertArrayEquals(sortie, Utils.convertIntoIndexes(entree));

        entree = "C3 d4";
        int[] sortie2 = {5, 2, 4, 3};
        assertArrayEquals(sortie2, Utils.convertIntoIndexes(entree));

        entree = "e5 F6";
        int[] sortie3 = {3, 4, 2, 5};
        assertArrayEquals(sortie3, Utils.convertIntoIndexes(entree));

        entree = "G7 H8";
        int[] sortie4 = {1, 6, 0, 7};
        assertArrayEquals(sortie4, Utils.convertIntoIndexes(entree));
        
        entree = "B2 A1";
        int[] sortie5 = {6, 1, 7, 0};
        assertArrayEquals(sortie5, Utils.convertIntoIndexes(entree));

        entree = "D4 c3";
        int[] sortie6 = {4, 3, 5, 2};
        assertArrayEquals(sortie6, Utils.convertIntoIndexes(entree));

        entree = "F6 e5";
        int[] sortie7 = {2, 5, 3, 4};
        assertArrayEquals(sortie7, Utils.convertIntoIndexes(entree));
       
        entree = "h8 g7";
        int[] sortie8 = {0, 7, 1, 6};
        assertArrayEquals(sortie8, Utils.convertIntoIndexes(entree));
    }

    @Test
    public void convertEnCaracteresTest() {
        char[] sortie = {'A', '1', 'B', '2'};
        int[] entree = {7, 0, 6, 1};
        assertArrayEquals(sortie, Utils.convertToChar(entree));

        char[] sortie2 = {'C', '3', 'D', '4'};
        int[] entree2 = {5, 2, 4, 3};
        assertArrayEquals(sortie2, Utils.convertToChar(entree2));

        char[] sortie3 = {'E', '5', 'F', '6'};
        int[] entree3 = {3, 4, 2, 5};
        assertArrayEquals(sortie3, Utils.convertToChar(entree3));

        char[] sortie4 = {'G', '7', 'H', '8'};
        int[] entree4 = {1, 6, 0, 7};
        assertArrayEquals(sortie4, Utils.convertToChar(entree4));

        char[] sortie5 = {'B', '2', 'A', '1'};
        int[] entree5 = {6, 1, 7, 0};
        assertArrayEquals(sortie5, Utils.convertToChar(entree5));

        char[] sortie6 = {'D', '4', 'C', '3'};
        int[] entree6 = {4, 3, 5, 2};
        assertArrayEquals(sortie6, Utils.convertToChar(entree6));
        
        char[] sortie7 = {'F', '6', 'E', '5'};
        int[] entree7 = {2, 5, 3, 4};
        assertArrayEquals(sortie7, Utils.convertToChar(entree7));
        
        char[] sortie8 = {'H', '8', 'G', '7'};
        int[] entree8 = {0, 7, 1, 6};
        assertArrayEquals(sortie8, Utils.convertToChar(entree8));
    }

    @Test
    public void convertCheminRelatif() {
        String cheminAbsolu = System.getProperty("user.dir") + "/parties";
        assertEquals("", Utils.convertRelativePath(cheminAbsolu));

        cheminAbsolu = System.getProperty("user.dir") + "/tests_input_output";
        assertEquals("../tests_input_output", Utils.convertRelativePath(cheminAbsolu));

        cheminAbsolu = System.getProperty("user.dir") + "/src/main/java/fr/iut/plateau";
        assertEquals("../src/main/java/fr/iut/plateau", Utils.convertRelativePath(cheminAbsolu));

        cheminAbsolu = System.getProperty("user.dir") + "/src/test/java";
        assertEquals("../src/test/java", Utils.convertRelativePath(cheminAbsolu));

        cheminAbsolu = System.getProperty("user.dir") + "/parties/tests";
        assertEquals("tests", Utils.convertRelativePath(cheminAbsolu));
    }
}
