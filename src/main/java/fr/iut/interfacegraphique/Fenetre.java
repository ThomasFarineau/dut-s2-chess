package fr.iut.interfacegraphique;

import javax.swing.*;

import fr.iut.gestionpartie.GestionnairePartie;
import fr.iut.plateau.Plateau;

import java.io.IOException;

public class Fenetre extends JFrame {
	public Fenetre(GestionnairePartie gp, Plateau p) {
		PanneauJeu pj = new PanneauJeu(p);
		
		this.setTitle("En Panne Corp. - Chess Game");
		// Ajout du menu
		this.setJMenuBar(new MenuFenetre(pj, gp));
		// Ajout de l'affichage
		this.setContentPane(pj);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}