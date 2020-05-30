package fr.iut.fonctions;

import fr.iut.gestionpartie.GestionnairePartie;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.awt.Component.CENTER_ALIGNMENT;

public class Fonctions {
    private Fonctions() {
    }

    // V�rifie qu'un String correspond � la syntaxe d'un d�placement
    public static boolean verifSyntaxe(String entree) {
        if (entree.length() != 5)
            return false;

        char[] tabEntree = entree.toUpperCase().toCharArray();

        return (tabEntree[2] == ' ') &&
                (tabEntree[0] >= 'A' && tabEntree[0] <= 'H') &&
                (tabEntree[1] >= '1' && tabEntree[1] <= '8') &&
                (tabEntree[3] >= 'A' && tabEntree[3] <= 'H') &&
                (tabEntree[4] >= '1' && tabEntree[4] <= '8');
    }

    // Convertit un d�placement en String en des indices en int
    public static int[] convertEnIndices(String entree) {
        char[] tabEntree = entree.toLowerCase().toCharArray();
        return new int[]{8 - (tabEntree[1] - '0'), tabEntree[0] - 'a', 8 - (tabEntree[4] - '0'), tabEntree[3] - 'a'};
    }

    // Convertit des indices en int, en d�placements en chiffres et lettres en char
    public static char[] convertEnCaracteres(int[] entree) {
        return new char[]{
                (char) (entree[1] + 'A'),
                (char) ((8 - entree[0]) + '0'),
                (char) (entree[3] + 'A'),
                (char) (8 - entree[2] + '0')
        };
    }

    public static String convertCheminRelatif(String cheminAbsolu) {
        Path cheminAbsoluFichier = Paths.get(cheminAbsolu);
        Path pathRelative = GestionnairePartie.getPartiesPath().relativize(cheminAbsoluFichier);

        return (pathRelative.toString()).replace('\\', '/');
    }

    public static JPanel creerPopup(int x, int y, String message) {
        JPanel panel = new JPanel();
        panel.setSize(new Dimension(x, y));
        panel.setLayout(null);

        // Ajout du message
        JLabel label = new JLabel("<html><div style='text-align: center;'>" + message + "</div></html>");
        label.setBounds(0, 0, x - 15, 40 % y);
        label.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        label.setFont(new Font("Calibri", Font.PLAIN, 16));
        panel.add(label);
        return panel;
    }

    public static int ajoutChoix(JPanel panel, String titre, String[] options, String choixParDefaut) {
        UIManager.put("OptionPane.minimumSize", new Dimension(panel.getWidth(), panel.getHeight()));
        return JOptionPane.showOptionDialog(null, panel, titre, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, choixParDefaut);
    }

    public static JFileChooser creerChoixFichier(File chemin, String titre, FileNameExtensionFilter extension) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(chemin);
        chooser.setDialogTitle(titre);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(extension);
        chooser.setAcceptAllFileFilterUsed(false);
        return chooser;
    }
}
