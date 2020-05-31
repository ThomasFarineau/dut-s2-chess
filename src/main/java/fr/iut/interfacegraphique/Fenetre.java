package fr.iut.interfacegraphique;

import fr.iut.gestionpartie.GestionnairePartie;
import fr.iut.plateau.Plateau;

import javax.swing.*;

public class Fenetre extends JFrame {
    private MenuFenetre mf;

    public Fenetre(GestionnairePartie gp, Plateau p) {
        PanneauJeu pj = new PanneauJeu(p, this);

        this.setTitle("En Panne Corp. - Chess Game");
        // Ajout du menu
        mf = new MenuFenetre(pj, gp);
        this.setJMenuBar(mf);

        // Ajout de l'affichage
        this.setContentPane(pj);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public MenuFenetre getMf() {
        return mf;
    }
}