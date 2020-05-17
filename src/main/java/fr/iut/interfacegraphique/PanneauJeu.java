package fr.iut.interfacegraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fr.iut.gestionpartie.GestionnairePartie;
import fr.iut.pieces.Piece;
import fr.iut.plateau.Plateau;

public class PanneauJeu extends JPanel implements MouseListener {
	private final static String imgPath = "./img/";
	private Image fondEchiquier = null;
	private Plateau plat;
	private int[] selection = null; // coordonnées de la 1re sélection, si elle est valide
	private boolean[][] selectionDeplacementsPoss; // déplacements possibles de la sélection
	
	private int[] casesEchec = null; // coordonnées des cases de l'échec (case du roi + case de la pièce qui le met en échec)
	
	public PanneauJeu(Plateau plat) {
		this.plat = plat;

		this.addMouseListener(this);

		//CODE TEMPORAIRE
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
		
		//this.setCasesEchec(new int[] {0,1, 5,6});
		//this.selectionner(6, 3);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.red);
		
		int xOffset = this.getPreferredSize().width-560;
		g.drawImage(fondEchiquier, 0, 0, null);
		
		Piece[][] echiquier = plat.getEchiquier();
		
		for (int i = 0; i < echiquier.length; i++) {
			for (int j = 0; j < echiquier[i].length; j++) {
				if (casesEchec != null) {
					if ((casesEchec[0] == i && casesEchec[1] == j) || (casesEchec[2] == i && casesEchec[3] == j)) {
						g.setColor(new Color(255, 150, 150));
						g.fillRect(xOffset+70*j, 70*i, 70, 70);
						
						g.setColor(new Color(0, 0, 0, 75));
						g.drawRect(xOffset+70*j, 70*i, 69, 69);
						g.drawRect(xOffset+70*j+1, 70*i+1, 67, 67);
					}
				}
				
				if (selection != null) {
					if (selection[0] == i && selection[1] == j) {
						g.setColor(new Color(255, 255, 150));
						g.fillRect(xOffset+70*j, 70*i, 70, 70);
						
						g.setColor(new Color(80, 0, 0, 75));
						g.drawRect(xOffset+70*j, 70*i, 69, 69);
						g.drawRect(xOffset+70*j+1, 70*i+1, 67, 67);
						
						
						
						for (int k = 0; k < selectionDeplacementsPoss.length; k++) {
							for (int l = 0; l < selectionDeplacementsPoss[k].length; l++) {
								if (selectionDeplacementsPoss[k][l]) {
									g.setColor(new Color(150, 255, 255));
									g.fillRect(xOffset+70*l, 70*k, 70, 70);
									
									g.setColor(new Color(0, 0, 0, 75));
									g.drawRect(xOffset+70*l, 70*k, 69, 69);
									g.drawRect(xOffset+70*l+1, 70*k+1, 67, 67);
								}
									
							}
						}
					}
				}
				
				if (echiquier[i][j] != null)
					g.drawImage(echiquier[i][j].getImage(), xOffset+70*j, 70*i, null);
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600,600);
	}
	
	public void selectionner(int i, int j) {
		// La sélection ne fonctionne que si elle est sur une pièce de la bonne couleur
		if (plat.getEchiquier()[i][j] != null) {
			if (plat.getEchiquier()[i][j].getCouleur() == plat.getTourJoueur()) {
				this.selection = new int[] {i,j};
				this.selectionDeplacementsPoss = plat.calculerDeplacementsPiece(i, j);
				System.out.println("Piece " + plat.getEchiquier()[i][j] + " selectionné");
			}
		} else {
			this.resetSelection();
			System.out.println("Erreur lors de la selection");
		}
	}
	
	public void resetSelection() {
		this.selection = null;
		this.selectionDeplacementsPoss = null;
	}
	
	public boolean hasSelection() {
		// Retourne si une case a été sélectionnée
		return (selection != null);
	}
	
	public void deplacer(int i, int j) {
		try {
			plat.deplacer(new int[] {selection[0], selection[1], i, j});
		} catch (Exception e) {}
		
		resetSelection();
	}
	
	public void setCasesEchec(int[] casesEchec) {
		this.casesEchec = casesEchec;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		int x = ((e.getX()-40)/70);
		int y = (e.getY()/70);
		if(e.getX() >= 40 && e.getY() <= 560) {
			if (!this.hasSelection()) {
				selectionner(y, x);
			} else {
				if(plat.getEchiquier()[y][x] == null) {
					deplacer(y, x);
				} else {
					selectionner(y, x);
				}
			}
		} else {
			System.out.println("Hors du plateau");
		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
