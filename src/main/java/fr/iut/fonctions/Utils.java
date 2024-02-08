package fr.iut.fonctions;

import fr.iut.gestionpartie.GameManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.awt.Component.CENTER_ALIGNMENT;

public final class Utils {
    private Utils() {
    }

    // Vérifie qu'un String correspond à la syntaxe d'un déplacement
    public static boolean checkSyntax(String entree) {
        if (entree.length() != 5) return false;

        char[] tabEntree = entree.toUpperCase().toCharArray();

        return (tabEntree[2] == ' ') && (tabEntree[0] >= 'A' && tabEntree[0] <= 'H') && (tabEntree[1] >= '1' && tabEntree[1] <= '8') && (tabEntree[3] >= 'A' && tabEntree[3] <= 'H') && (tabEntree[4] >= '1' && tabEntree[4] <= '8');
    }

    // Convertit un déplacement en String en des indices en int
    public static int[] convertIntoIndexes(String entree) {
        char[] tabEntree = entree.toLowerCase().toCharArray();
        return new int[]{8 - (tabEntree[1] - '0'), tabEntree[0] - 'a', 8 - (tabEntree[4] - '0'), tabEntree[3] - 'a'};
    }

    // Convertit des indices en int, en déplacements en chiffres et lettres en char
    public static char[] convertToChar(int[] entree) {
        return new char[]{(char) (entree[1] + 'A'), (char) ((8 - entree[0]) + '0'), (char) (entree[3] + 'A'), (char) (8 - entree[2] + '0')};
    }

    // Convertit un chemin absolu en chemin relatif au Path des fichiers de partie
    public static String convertRelativePath(String absolutePath) {
        Path absolutePathFile = Paths.get(absolutePath);
        Path pathRelative = GameManager.getGamesPath().relativize(absolutePathFile);

        return (pathRelative.toString()).replace('\\', '/');
    }

    public static JPanel createGroupPanel(int x, int y, String message) {
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

    public static int showPopupWithChoice(JPanel panel, String title, String[] options, String defaultChoice) {
        UIManager.put("OptionPane.minimumSize", new Dimension(panel.getWidth(), panel.getHeight()));
        return JOptionPane.showOptionDialog(null, panel, title, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, defaultChoice);
    }

    public static JFileChooser createPopupFile(File path, String title, FileNameExtensionFilter extension) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(path);
        chooser.setDialogTitle(title);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(extension);
        chooser.setAcceptAllFileFilterUsed(false);
        return chooser;
    }

    public static Image loadImage(String fileName) {
        Image img = null;

        try {
            img = ImageIO.read(new File("./img/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }
}
