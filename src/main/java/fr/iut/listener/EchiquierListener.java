package fr.iut.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fr.iut.interfacegraphique.PanneauJeu;

public class EchiquierListener implements MouseListener {
	private PanneauJeu listenedPanel = null;
	
	public EchiquierListener(PanneauJeu listenedPanel) {
		this.listenedPanel = listenedPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = (e.getX() >= 40 ?((e.getX()-40)/70) : -1);
		int y = (e.getY()/70);

		if(e.getX() >= 40 && e.getY() <= 560) {
			if (!listenedPanel.hasSelection()) {
				listenedPanel.selectionner(y, x);
			} else {
				if(listenedPanel.getPlat().getEchiquier()[y][x] == null || listenedPanel.getPlat().getEchiquier()[y][x].getCouleur() != listenedPanel.getPlat().getTourJoueur()) {
					listenedPanel.deplacer(y, x);
				} else {
					listenedPanel.selectionner(y, x);
				}
			}
		} else {
			System.out.println("Hors du plateau");
		}
		listenedPanel.repaint();
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
