package fr.iut.interfacegraphique;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fr.iut.plateau.Plateau;

public class PanneauJeu extends JPanel {
	private final static String imgPath = "./img/";
	private Image fondEchiquier = null;
	private Plateau plat;
	
	public PanneauJeu(Plateau plat) {
		this.plat = plat;
		
		try {
			fondEchiquier = ImageIO.read(new File(imgPath+"tempEchiquier.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(fondEchiquier, this.getPreferredSize().width-fondEchiquier.getWidth(null), 0, null);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600,600);
	}
}
