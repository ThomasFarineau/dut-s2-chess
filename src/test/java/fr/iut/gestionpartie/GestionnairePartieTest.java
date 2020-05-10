package fr.iut.gestionpartie;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.iut.plateau.Plateau;

public class GestionnairePartieTest {
	private Plateau p1,p2;
	private GestionnairePartie gp1,gp2;
	
	@BeforeEach
    public void init() {
		p1 = new Plateau();
		p2 = new Plateau();
		gp1 = new GestionnairePartie (p1);
		gp2 = new GestionnairePartie (p2);
	}
	
	@Test
	public void chargerPartieTest() {
    	Assertions.assertThrows(IOException.class, ()->{gp1.chargerAnciennePartie("fichier qui exite pas");});
    }
	@Test
	public void sauvegarderPartieTest() {
		Assertions.assertThrows(Exception.class, ()->{gp2.sauvegarderPartie("nouvellePartie.csv");});
	}
}
