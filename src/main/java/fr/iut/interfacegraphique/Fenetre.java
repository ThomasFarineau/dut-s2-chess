package fr.iut.interfacegraphique;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import fr.iut.plateau.Plateau;

public class Fenetre extends JFrame {
	private JPanel jeu;
	
private JMenuBar menu = new JMenuBar();
	
	private JMenu partie = new JMenu("Partie");
	private JMenuItem nouvellePartie = new JMenuItem("Nouvelle Partie");
	private JMenuItem chargerPartie = new JMenuItem("Charger une Partie...");
	private JMenuItem enregistrerPartie = new JMenuItem("Enregistrer");
	private JMenuItem enregistrerPartieSous = new JMenuItem("Enregistrer sous...");
	
	private JMenu informations = new JMenu("Informations");
	private JMenuItem regles = new JMenuItem("R�gles du jeu");
	
	public Fenetre(Plateau p) {
		jeu = new PanneauJeu(p);
		this.setContentPane(jeu);
		this.initMenu();
		
		this.pack();
		
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("epic java project");
		
		this.setVisible(true);
	}
	
	private void initMenu() {
		nouvellePartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		nouvellePartie.addActionListener(null);
		partie.add(nouvellePartie);
		
		chargerPartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
		chargerPartie.addActionListener(null);
		partie.add(chargerPartie);
		
		enregistrerPartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		enregistrerPartie.addActionListener(null);
		enregistrerPartie.setEnabled(false);
		partie.add(enregistrerPartie);
		
		enregistrerPartieSous.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		enregistrerPartieSous.addActionListener(null);
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
		
		JFrame f = new Fenetre(p);
	}
}
