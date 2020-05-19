package fr.iut.interfacegraphique;

import javax.swing.*;

import fr.iut.plateau.Plateau;

public class Fenetre extends JFrame {

	public Fenetre(Plateau p) {
		JPanel jeu = new PanneauJeu(p);
		this.setContentPane(jeu);

		JMenuBar menu = new MenuFenetre();
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
		
		/*for (int i = 1; i < 36; i++) {
			//CODE TEMPORAIRE
			GestionnairePartie gp = new GestionnairePartie(p);
			try {
				gp.chargerAnciennePartie("tests/test"+i);
			} catch (Exception e) {
				System.out.println(i + " : " + e.getMessage());
			}
		}*/
	}
}
