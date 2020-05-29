package fr.iut.interfacegraphique;

import javax.swing.*;

import fr.iut.gestionpartie.GestionnairePartie;
import fr.iut.plateau.Plateau;

import java.io.IOException;

public class Fenetre extends JFrame {
	private PanneauJeu pj;
	private MenuFenetre mf;
	public Fenetre(GestionnairePartie gp, Plateau p) {
		pj = new PanneauJeu(p, this);
		
		this.setTitle("En Panne Corp. - Chess Game");
		// Ajout du menu
		mf = new MenuFenetre(pj, gp);
		this.setJMenuBar(mf);

		// Ajout de l'affichage
		this.setContentPane(pj);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public PanneauJeu getPj() {
		return pj;
	}

	public MenuFenetre getMf() {
		return mf;
	}
}