package fr.iut.plateau;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.iut.gestionpartie.GestionnairePartie;

public class PlateauTest {
	private Plateau plat;
	private GestionnairePartie gp;
	
	@BeforeEach
	public void initialisation( ) {
		plat = new Plateau();
		gp = new GestionnairePartie(plat);
	}
	
	@Test
	public void toStringTest1() {
		try {
			gp.chargerAnciennePartie("tests/partieTest.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String valeurAttendue = "8 RoB  -  ReN  -   -   -   -   -  \n" + 
				"7 Pb  Tb   -   -   -  ReN  -   -  \n" + 
				"6  -   -   -   -  Pb   -   -   -  \n" + 
				"5  -   -   -   -   -   -   -  RoN \n" + 
				"4  -   -   -   -   -   -   -   -  \n" + 
				"3  -   -   -   -   -   -   -   -  \n" + 
				"2  -   -   -   -   -   -   -   -  \n" + 
				"1  -   -   -   -   -   -   -   -  \n" + 
				"   A   B   C   D   E   F   G   H";
		assertEquals(valeurAttendue, plat.toString());
	}
	
	@Test
	public void toStringTest2() {
		try {
			gp.chargerAnciennePartie("tests/test2.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String valeurAttendue = "8  -   -   -   -   -   -  Fn  RoN \n" +
				"7  -   -   -   -   -   -  Pb   -  \n" +
				"6  -   -   -   -   -   -   -  RoB \n" +
				"5  -   -   -   -   -   -   -   -  \n" +
				"4  -   -   -   -   -   -   -   -  \n" +
				"3  -   -   -   -   -   -   -   -  \n" +
				"2  -   -   -   -   -   -   -   -  \n" +
				"1  -   -   -   -   -   -   -   -  \n" +
				"   A   B   C   D   E   F   G   H";
		assertEquals(valeurAttendue, plat.toString());
	}
	
	@Test
	public void toStringTest3() {
		try {
			gp.chargerAnciennePartie("tests/test3.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String valeurAttendue = 
				"8  -   -   -   -   -   -  Tn  RoN \n" + 
				"7  -   -   -   -   -  Cb  Pn  Pn  \n" + 
				"6  -   -   -   -   -   -   -   -  \n" + 
				"5  -   -   -   -   -   -   -   -  \n" + 
				"4  -   -   -   -   -   -   -   -  \n" + 
				"3  -   -   -   -   -   -   -   -  \n" + 
				"2  -   -   -   -   -  Pb  Pb  Pb  \n" + 
				"1  -   -   -   -   -   -  RoB  -  \n" + 
				"   A   B   C   D   E   F   G   H";
		assertEquals(valeurAttendue, plat.toString());
	}
	
	@Test
	public void toStringTest4() {
		try {
			gp.chargerAnciennePartie("tests/test4.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String valeurAttendue = 
				"8 Tn  Cn  Fn  ReN RoN Fn  Cn  Tn  \n" + 
				"7 Pn  Pn  Pn  Pn  Pn  Pn  Pn  Pn  \n" + 
				"6  -   -   -   -   -   -   -   -  \n" + 
				"5  -   -   -   -   -   -   -   -  \n" + 
				"4  -   -   -   -   -   -   -   -  \n" + 
				"3  -   -   -   -   -   -   -   -  \n" + 
				"2 Pb  Pb  Pb  Pb  Pb  Pb  Pb  Pb  \n" + 
				"1 Tb  Cb  Fb  ReB RoB Fb  Cb  Tb  \n" + 
				"   A   B   C   D   E   F   G   H";
		assertEquals(valeurAttendue, plat.toString());
	}
	
	@Test
	public void toStringTest5() {
		try {
			gp.chargerAnciennePartie("tests/test5.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String valeurAttendue = 
				"8  -   -   -   -   -   -   -   -  \n" + 
				"7  -   -   -   -   -   -   -   -  \n" + 
				"6  -   -   -   -   -   -   -   -  \n" + 
				"5  -   -   -   -   -   -   -   -  \n" + 
				"4  -   -   -  RoB  -   -   -   -  \n" + 
				"3  -   -   -   -   -   -   -   -  \n" + 
				"2  -   -  Cn   -   -   -   -   -  \n" + 
				"1  -   -   -  RoN  -   -   -   -  \n" + 
				"   A   B   C   D   E   F   G   H";
		assertEquals(valeurAttendue, plat.toString());
	}
	
	@Test
	public void toStringTest6() {
		try {
			gp.chargerAnciennePartie("tests/test6.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String valeurAttendue = 
				"8 Tn  Cn  Fn  ReN  -  Fn  Cn  Tn  \n" + 
				"7 Pn  Pn  Pn  Pn  Pn  Pn  Pn  Pn  \n" + 
				"6  -   -   -   -   -   -   -   -  \n" + 
				"5  -   -   -   -   -   -   -   -  \n" + 
				"4  -   -   -   -   -   -   -   -  \n" + 
				"3 RoN  -   -   -   -   -   -   -  \n" + 
				"2 Pb  Pb  Pb  Pb  Pb  Pb  Pb  Pb  \n" + 
				"1 Tb  Cb  Fb  ReB RoB Fb  Cb  Tb  \n" + 
				"   A   B   C   D   E   F   G   H";
		assertEquals(valeurAttendue, plat.toString());
	}
	
	@Test
	public void toStringTest7() {
		try {
			gp.chargerAnciennePartie("tests/test7.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String valeurAttendue = 
				"8  -   -   -  Tn   -   -  RoN  -  \n" + 
				"7  -   -   -   -   -   -   -   -  \n" + 
				"6  -   -   -   -   -   -   -   -  \n" + 
				"5 Fn   -   -   -   -   -   -   -  \n" + 
				"4  -   -   -   -   -   -   -   -  \n" + 
				"3  -   -   -   -   -   -   -   -  \n" + 
				"2  -   -   -   -  Pb  Pb   -   -  \n" + 
				"1  -   -   -   -  RoB Fb   -   -  \n" + 
				"   A   B   C   D   E   F   G   H";
		assertEquals(valeurAttendue, plat.toString());
	}
	
	@Test
	public void toStringTest8() {
		try {
			gp.chargerAnciennePartie("tests/test8.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String valeurAttendue = 
				"8 Tn   -  Fn  ReN RoN Fn   -  Tn  \n" + 
				"7 Pn  Pn  Pn  Pn   -  ReB Pn  Pn  \n" + 
				"6  -   -  Cn   -   -  Cn   -   -  \n" + 
				"5  -   -   -   -  Pn   -   -   -  \n" + 
				"4  -   -  Fb   -  Pb   -   -   -  \n" + 
				"3  -   -   -   -   -   -   -   -  \n" + 
				"2 Pb  Pb  Pb  Pb  Pb  Pb  Pb  Pb  \n" + 
				"1 Tb  Cb  Fb   -  RoB  -  Cb  Tb  \n" + 
				"   A   B   C   D   E   F   G   H";
		assertEquals(valeurAttendue, plat.toString());
	}
	
	@Test
	public void toStringTest9() {
		try {
			gp.chargerAnciennePartie("tests/test9.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String valeurAttendue = 
				"8  -   -   -  RoN  -   -   -   -  \n" + 
				"7 Tb   -   -   -   -  Cb   -   -  \n" + 
				"6  -   -   -   -   -  Fb   -   -  \n" + 
				"5  -   -   -   -   -   -   -   -  \n" + 
				"4  -   -   -   -   -   -   -   -  \n" + 
				"3  -   -   -   -   -   -   -   -  \n" + 
				"2  -   -   -   -   -   -   -   -  \n" + 
				"1  -  RoB  -   -   -   -   -   -  \n" + 
				"   A   B   C   D   E   F   G   H";
		assertEquals(valeurAttendue, plat.toString());
	}
	
	@Test
	public void calculerDeplacementTest2()
	{
		try {
            gp.chargerAnciennePartie("tests/test2.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }

    boolean[][] deplacementsTest = { 
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, true},
                {false, false, false, false, false, false, true, false},
                {false, false, false, false, false, false, true, true},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false}

    };
    
    boolean[][] deplacementAttendu = plat.calculerDeplacementsPiece(2, 7);
    assertArrayEquals(deplacementsTest, deplacementAttendu);
	}
	
	@Test
	public void calculerDeplacementTest7()
	{
		try {
            gp.chargerAnciennePartie("tests/test7.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }

    boolean[][] deplacementsTest = { 
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, true},
                {false, false, false, false, false, false, true, false},
                {false, false, false, false, false, false, false, false}

    };
    
    boolean[][] deplacementAttendu = plat.calculerDeplacementsPiece(7, 5);
    assertArrayEquals(deplacementsTest, deplacementAttendu);
	}
	
	@Test
	public void calculerDeplacementTest9()
	{
		try {
            gp.chargerAnciennePartie("tests/test9.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }

    boolean[][] deplacementsTest = { 
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {true, true, true, false, false, false, false, false},
                {true, false, true, false, false, false, false, false}

    };
    
    boolean[][] deplacementAttendu = plat.calculerDeplacementsPiece(7, 1);
    assertArrayEquals(deplacementsTest, deplacementAttendu);
	}
	
	@Test
	public void calculerDeplacementsTest1() {
		
		try {
				gp.chargerAnciennePartie("tests/test1.csv");
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		boolean[][] deplacementTourValide = {
				{false, false, false, false, false, false, true, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false}
		};
		
		boolean[][] deplacementTour = plat.calculerDeplacementsPiece(0, 7); 
		assertArrayEquals(deplacementTourValide, deplacementTour);
		
		boolean[][] deplacementCavalierValide = {
				{false, true, false, false, false, false, false, false},
				{false, false, false, false, true, false, false, false},
				{false, false, false, false, false, false, false, false},
				{true, false, false, false, true, false, false, false},
				{false, true, false, true, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false}
		};
		
		boolean[][] deplacementCavalier = plat.calculerDeplacementsPiece(2, 2); 
		assertArrayEquals(deplacementCavalierValide, deplacementCavalier);
		
		System.out.println(plat);
		
		boolean[][] deplacementFouValide = {
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{true, false, false, false, true, false, false, false},
				{false, true, false, true, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, true, false, true, false, false, false, false},
				{false, false, false, false, true, false, false, false},
				{false, false, false, false, false, true, false, false}
		};
		boolean[][] deplacementFou = plat.calculerDeplacementsPiece(4, 2); 
		assertArrayEquals(deplacementFouValide, deplacementFou);
		
		boolean[][] deplacementRoiValide = {
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, true, false, false, false},
				{false, false, false, true, false, true, false, false}
		};
		boolean[][] deplacementRoi = plat.calculerDeplacementsPiece(7, 4); 
		assertArrayEquals(deplacementRoiValide, deplacementRoi);
		
		boolean[][] deplacementReineValide = {
				{false, false, false, false, false, false, true, false},
				{false, false, false, false, true, false, false, false},
				{false, false, false, false, true, false, true, false},
				{false, false, false, true, false, false, false, true},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false}
		};
		boolean[][] deplacementReine = plat.calculerDeplacementsPiece(1, 5); 
		assertArrayEquals(deplacementReineValide, deplacementReine);
		
		boolean[][] deplacementFouValide = {
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{true, false, false, false, true, false, false, false},
				{false, true, false, true, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, true, false, true, false, false, false, false},
				{false, false, false, false, true, false, false, false},
				{false, false, false, false, false, true, false, false}
		};
		boolean[][] deplacementFou = plat.calculerDeplacementsPiece(4, 2); 
		assertArrayEquals(deplacementFouValide, deplacementFou);
	}
}
