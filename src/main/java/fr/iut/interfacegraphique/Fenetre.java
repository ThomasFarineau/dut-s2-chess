package fr.iut.interfacegraphique;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.*;

import fr.iut.gestionpartie.GestionnairePartie;
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
		nouvellePartie.addActionListener(e -> nouvellePartie());
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



	public void nouvellePartie() {
		// Creation du panel pour l'affichage du dialogue
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(430, 100));
		panel.setLayout(null);

		// Ajout du message
		JLabel label = new JLabel("Êtes-vous sûr de vouloir démarrer une nouvelle partie ?");
		label.setBounds(23, 15, 430, 30);
		label.setFont(new Font("Calibri", Font.PLAIN, 16));
		panel.add(label);

		// Changement de la taille du dialogue
		UIManager.put("OptionPane.minimumSize", new Dimension(430, 100));
		// Ajout des options oui et non
		String[] options = { "Oui", "Non"};

		// Affichage du dialogue et recuperation de la réponse sous resp
		int resp = JOptionPane.showOptionDialog(null, panel, "Nouvelle partie", 0,JOptionPane.PLAIN_MESSAGE,null,options,null);

		// Utilisation de la réponse
		if(resp == 0) {
			System.out.println("Nouvelle partie");
		} else {
			System.out.println("Non, rien de rien, je ne regrette rien");
		}

	}

	public static void main(String[] args) {
		Plateau p = new Plateau();

		JFrame f = new Fenetre(p, null);
		
		for (int i = 1; i < 36; i++) {
			//CODE TEMPORAIRE
			GestionnairePartie gp = new GestionnairePartie(p);
			try {
				gp.chargerAnciennePartie("tests/test"+i);
			} catch (Exception e) {
				System.out.println(i + " : " + e.getMessage());
			}
		}
	}
}
