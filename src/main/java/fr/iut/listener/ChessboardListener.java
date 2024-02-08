package fr.iut.listener;

import fr.iut.interfacegraphique.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChessboardListener implements MouseListener {
    private final GamePanel gamePanel;
    private boolean interoperable;

    public ChessboardListener(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        interoperable = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (interoperable) {
            int x = (e.getX() >= 40 ? ((e.getX() - 40) / 70) : -1);
            int y = (e.getY() / 70);

            gamePanel.select(y, x);
            gamePanel.repaint();
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

    public void setInteroperable(boolean interoperable) {
        this.interoperable = interoperable;
    }
}
