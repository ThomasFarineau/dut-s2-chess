package fr.iut.interfacegraphique;

import javax.swing.JFrame;

import fr.iut.fonctions.Utils;
import fr.iut.gestionpartie.GameManager;
import fr.iut.plateau.Board;

public class Window extends JFrame {
    private final MenuWindow menuWindow;

    public Window(GameManager gp, Board p) {
        GamePanel pj = new GamePanel(p, this);

        this.setTitle("En Panne Corp. - Chess Game");
        // Ajout du menu
        menuWindow = new MenuWindow(pj, gp);
        this.setJMenuBar(menuWindow);

        // Ajout de l'affichage
        this.setContentPane(pj);
        this.setIconImage(Utils.loadImage("icone_appli_24x24.png"));

        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public MenuWindow getMenuWindow() {
        return menuWindow;
    }
}