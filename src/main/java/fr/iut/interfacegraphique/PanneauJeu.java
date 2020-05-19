package fr.iut.interfacegraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fr.iut.gestionpartie.GestionnairePartie;
import fr.iut.listener.EchiquierListener;
import fr.iut.pieces.Piece;
import fr.iut.plateau.Plateau;

public class PanneauJeu extends JPanel {
	private final static String imgPath = "./img/";
	private Image fondEchiquier = null;
	private Plateau plat;
	private int[] selection = null; // coordonnées de la 1re sélection, si elle est valide
	private boolean[][] selectionDeplacementsPoss; // déplacements possibles de la sélection
	private int[] casesEchec = null; // coordonnées des cases de l'échec (case du roi + case de la pièce qui le met en échec)

	public PanneauJeu(Plateau plat) {
		this.plat = plat;

		this.addMouseListener(new EchiquierListener(this));

		// CODE POUR CREER LA PARTIE, A ENLEVER QUAND LE GESTIONNAIRE
		// SERA INTEGRE A L'INTERFACE
		GestionnairePartie gp = new GestionnairePartie(plat);
		try {
			gp.nouvellePartie();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			fondEchiquier = ImageIO.read(new File(imgPath+"tempEchiquier.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 600, 600, 40);
		
		int xOffset = 40;
		g.drawImage(fondEchiquier, 0, 0, null);

		Piece[][] echiquier = plat.getEchiquier();

		if (casesEchec != null) {
			g.setColor(new Color(255, 150, 150));
			g.fillRect(xOffset+70*casesEchec[1], 70*casesEchec[0], 70, 70);
			g.fillRect(xOffset+70*casesEchec[3], 70*casesEchec[2], 70, 70);

			g.setColor(new Color(0, 0, 0, 75));
			g.drawRect(xOffset+70*casesEchec[1], 70*casesEchec[0], 69, 69);
			g.drawRect(xOffset+70*casesEchec[1]+1, 70*casesEchec[0]+1, 67, 67);
			g.drawRect(xOffset+70*casesEchec[3], 70*casesEchec[3], 69, 69);
			g.drawRect(xOffset+70*casesEchec[3]+1, 70*casesEchec[2]+1, 67, 67);
		}

		if (selection != null) {
			g.setColor(new Color(255, 255, 150));
			g.fillRect(xOffset+70*selection[1], 70*selection[0], 70, 70);

			g.setColor(new Color(80, 0, 0, 75));
			g.drawRect(xOffset+70*selection[1], 70*selection[0], 69, 69);
			g.drawRect(xOffset+70*selection[1]+1, 70*selection[0]+1, 67, 67);

			for (int k = 0; k < selectionDeplacementsPoss.length; k++) {
				for (int l = 0; l < selectionDeplacementsPoss[k].length; l++) {
					
				}
			}
		}

		for (int i = 0; i < echiquier.length; i++) {
			for (int j = 0; j < echiquier[i].length; j++) {
				if (selectionDeplacementsPoss != null) {
					if (selectionDeplacementsPoss[i][j]) {
						g.setColor(new Color(150, 255, 255));
						g.fillRect(xOffset+70*j, 70*i, 70, 70);

						g.setColor(new Color(0, 0, 0, 75));
						g.drawRect(xOffset+70*j, 70*i, 69, 69);
						g.drawRect(xOffset+70*j+1, 70*i+1, 67, 67);
					}
				}
				
				if (echiquier[i][j] != null) {
					g.drawImage(echiquier[i][j].getImage(), xOffset+70*j, 70*i, null);
				}
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600,600);
	}

	public void selectionner(int i, int j) {
		// La slection ne fonctionne que si elle est sur une pice de la bonne couleur
		if (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
			if (plat.getEchiquier()[i][j] != null) {
				if (plat.getEchiquier()[i][j].getCouleur() == plat.getTourJoueur()) {
					this.selection = new int[] {i,j};
					this.selectionDeplacementsPoss = plat.calculerDeplacementsPiece(i, j);
				} else {
					this.deplacer(i, j);
				}
			} else {
				this.deplacer(i, j);
			}
		} else {
			resetSelection();
		}
	}

	public void resetSelection() {
		this.selection = null;
		this.selectionDeplacementsPoss = null;
	}

	public boolean hasSelection() {
		// Retourne si une case a t slectionne
		return (selection != null);
	}

	public void deplacer(int i, int j) {
		try {
			plat.deplacer(new int[] {selection[0], selection[1], i, j});
			System.out.println(plat.verifMat());
		} catch (Exception e) {}
		
		resetSelection();
		this.casesEchec = plat.getDernierEchec();
	}

	public void setCasesEchec(int[] casesEchec) {
		this.casesEchec = casesEchec;
	}

}
