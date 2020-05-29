package fr.iut.gestionpartie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import fr.iut.pieces.Cavalier;
import fr.iut.pieces.Fou;
import fr.iut.pieces.Piece;
import fr.iut.pieces.Pion;
import fr.iut.pieces.Reine;
import fr.iut.pieces.Roi;
import fr.iut.pieces.Tour;
import fr.iut.plateau.Plateau;

public class GestionnairePartie {
	private final static Path partiesPath = Paths.get(System.getProperty("user.dir") + "/parties");
	private final static String nomNouvellePartie = "nouvellePartie.csv";
	private Plateau plat;
	private String nomFichier = "partieActuelle.csv";

	public GestionnairePartie(Plateau plat) {
		this.plat = plat;
	}

	private void chargerPartie(String nomFichier) throws IOException {
		Piece[][] echiquier = new Piece[8][8];
		BufferedReader br;

		try {
			br = new BufferedReader(
					new FileReader(
							new File(partiesPath+"//"+nomFichier)
							)
					);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Fichier introuvable : \"" + nomFichier + "\"");
		}

		
		for (int i = 0; i < echiquier.length; i++) {
			String line = br.readLine();
			
			String[] values = null;
			
			try {
				values = line.split(",");
			} catch (NullPointerException e) {
				br.close();
				throw new NullPointerException("Format du fichier \"" + nomFichier +"\" invalide");
			} 

			if (values.length != 8) {
				br.close();
				throw new IOException("Ligne " + (i+1) + " invalide dans le fichier \"" + nomFichier + "\"");
			}

			for (int j = 0; j < values.length; j++) {
				switch (values[j]) {
				case "Tn" :
					echiquier[i][j] = new Tour(true);
					break;
				case "Tb" : 
					echiquier[i][j] = new Tour(false);
					break;
				case "Cn" : 
					echiquier[i][j] = new Cavalier(true);
					break;
				case "Cb" : 
					echiquier[i][j] = new Cavalier(false);
					break;
				case "Fn" : 
					echiquier[i][j] = new Fou(true);
					break;
				case "Fb" : 
					echiquier[i][j] = new Fou(false);
					break;
				case "ReN" : 
					echiquier[i][j] = new Reine(true);
					break;
				case "ReB" : 
					echiquier[i][j] = new Reine(false);
					break;
				case "RoN" : 
					echiquier[i][j] = new Roi(true);
					break;
				case "RoB" : 
					echiquier[i][j] = new Roi(false);
					break;
				case "Pn" : 
					echiquier[i][j] = new Pion(true);
					break;
				case "Pb" : 
					echiquier[i][j] = new Pion(false);
					break;
				case "V": 
					break;
				default:
					throw new IOException("Nom de pièce invalide \"" + values[j] + 
							"\" à la ligne " + (i+1) + " du fichier \"" + nomFichier + "\"");
				}
			}
		}

		br.close();

		plat.setEchiquier(echiquier);
	}

	public void chargerAnciennePartie(String nomFichier) throws IOException, Exception {
		if (!nomFichier.endsWith(".csv"))
			nomFichier += ".csv";
		
		if (nomFichier.toLowerCase().endsWith(nomNouvellePartie.toLowerCase()))
			throw new Exception("Vous n'avez pas le droit de charger le fichier \"" + nomNouvellePartie + "\".");

		this.nomFichier = nomFichier;
		chargerPartie(nomFichier);
	}

	public void nouvellePartie() throws IOException {
		nomFichier = "partieActuelle.csv";
		chargerPartie("nouvellePartie.csv");
	}

	public String sauvegarderPartie() throws IOException {
		BufferedWriter bw;
		String retour = "";
		
		try {
			File f = new File(partiesPath+"//"+nomFichier);
			if (f.createNewFile()) {
				retour = "Le fichier \"" + nomFichier + "\" vient d'être créé. ";
			}

			bw = new BufferedWriter(new FileWriter(f));
			
			Piece[][] echiquierAEcrire = plat.getEchiquier();

			for (int i = 0; i < echiquierAEcrire.length; i++) {
				for (int j = 0; j < echiquierAEcrire[i].length; j++) {
					bw.write(
							((echiquierAEcrire[i][j] == null) ? "V" : echiquierAEcrire[i][j].toString()) + 
							((j == echiquierAEcrire[i].length - 1 )? "" : ",")
							);
				}

				bw.write("\n");
			}

			bw.close();
		} catch (IOException e) {
			throw new IOException("Erreur lors de la sauvegarde du fichier");
		}
		
		retour += "La sauvegarde vers \"" + nomFichier + "\" a été effectuée avec succès !";
		
		return retour;
	}

	public String sauvegarderPartie(String nomFichier) throws IOException, Exception {
		if (!nomFichier.endsWith(".csv"))
			nomFichier += ".csv";
		
		if (nomFichier.toLowerCase().endsWith(nomNouvellePartie.toLowerCase()))
			throw new Exception("Vous n'avez pas le droit de sauvegarder une partie de nom \"" + nomNouvellePartie + "\".");

		this.nomFichier = nomFichier;
		return sauvegarderPartie();
	}
	
	public String getNomFichier() {
		return nomFichier;
	}
	
	public static Path getPartiesPath() {
		return partiesPath;
	}
	
	public void setTourJoueur(boolean tour) {
		plat.setTourJoueur(tour);
	}
}
