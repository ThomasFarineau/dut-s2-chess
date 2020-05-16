package fr.iut.interfacegraphique;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import fr.iut.plateau.Plateau;

public class Fenetre extends JFrame {
	private ActionListener ml = null; // Il faut une classe qui implémente ActionListener pour le menu
	
	private JPanel jeu;

	private JMenuBar menu = new JMenuBar();

	private JMenu partie = new JMenu("Partie");
	private JMenuItem nouvellePartie = new JMenuItem("Nouvelle Partie");
	private JMenuItem chargerPartie = new JMenuItem("Charger une Partie...");
	private JMenuItem enregistrerPartie = new JMenuItem("Enregistrer");
	private JMenuItem enregistrerPartieSous = new JMenuItem("Enregistrer sous...");

	private JMenu informations = new JMenu("Informations");
	private JMenuItem regles = new JMenuItem("Règles du jeu");

	public Fenetre(Plateau p, ActionListener al) {
		jeu = new PanneauJeu(p);
		this.setContentPane(jeu);
		this.initMenu(ml);

		this.pack();

		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("En Panne Corp. - Chess Game");

		this.setVisible(true);
	}

	private void initMenu(ActionListener al) {
		nouvellePartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		nouvellePartie.addActionListener(al);
		partie.add(nouvellePartie);

		chargerPartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
		chargerPartie.addActionListener(al);
		partie.add(chargerPartie);

		enregistrerPartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		enregistrerPartie.addActionListener(al);
		enregistrerPartie.setEnabled(false);
		partie.add(enregistrerPartie);

		enregistrerPartieSous.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		enregistrerPartieSous.addActionListener(al);
		enregistrerPartieSous.setEnabled(false);
		partie.add(enregistrerPartieSous);

		regles.addActionListener(null);
		informations.add(regles);

		menu.add(partie);
		menu.add(informations);

		this.setJMenuBar(menu);
	}

	public static void main(String[] args) {
		Plateau p = new Plateau();

		JFrame f = new Fenetre(p, null);
	}
}
