package fr.iut.interfacegraphique;

import javax.swing.JFrame;

import fr.iut.fonctions.Fonctions;
import fr.iut.gestionpartie.GestionnairePartie;
import fr.iut.plateau.Plateau;

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
        this.setIconImage(Fonctions.loadImage("icone_appli_24x24.png"));

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