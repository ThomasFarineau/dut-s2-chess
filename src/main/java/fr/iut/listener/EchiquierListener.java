package fr.iut.listener;

import fr.iut.interfacegraphique.PanneauJeu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EchiquierListener implements MouseListener {
    private PanneauJeu listenedPanel;
    private boolean interactable;

    public EchiquierListener(PanneauJeu listenedPanel) {
        this.listenedPanel = listenedPanel;
        interactable = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (interactable) {
            int x = (e.getX() >= 40 ? ((e.getX() - 40) / 70) : -1);
            int y = (e.getY() / 70);

            listenedPanel.selectionner(y, x);
            listenedPanel.repaint();
        }
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

    public void setInteractable(boolean interactable) {
        this.interactable = interactable;
    }
}
