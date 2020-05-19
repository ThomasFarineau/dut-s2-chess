package fr.iut.interfacegraphique;

import javax.swing.*;

import fr.iut.plateau.Plateau;

public class Fenetre extends JFrame {

	public Fenetre(Plateau p) {
		JPanel jeu = new PanneauJeu(p);
		this.setContentPane(jeu);

		JMenuBar menu = new MenuFenetre(); // Demander un GestionnairePartie en paramètre
		this.setJMenuBar(menu);

		this.pack();

		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("En Panne Corp. - Chess Game");

		this.setVisible(true);
	}

	public static void main(String[] args) {
		Plateau p = new Plateau();

		JFrame f = new Fenetre(p);
	}
}
