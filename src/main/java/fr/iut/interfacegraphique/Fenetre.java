package fr.iut.interfacegraphique;

import javax.swing.*;

import fr.iut.gestionpartie.GestionnairePartie;
import fr.iut.plateau.Plateau;

import java.io.IOException;

public class Fenetre extends JFrame {
	private static Plateau p = new Plateau();
	private static GestionnairePartie gp = new GestionnairePartie(p);
	private static JPanel jeu = new PanneauJeu(p);
	private static JMenuBar menu = new MenuFenetre();

	public Fenetre() {
		this.setTitle("En Panne Corp. - Chess Game");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		// Ajout du menu
		this.setJMenuBar(menu);
		// Ajout de l'affichage
		this.setContentPane(jeu);

		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		JFrame f = new Fenetre();
	}

	public static GestionnairePartie getGp() {
		return gp;
	}

	public static JPanel getJeu() {
		return jeu;
	}
}