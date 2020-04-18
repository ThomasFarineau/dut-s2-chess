package fr.iut.gestionpartie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import fr.iut.pieces.Cavalier;
import fr.iut.pieces.Fou;
import fr.iut.pieces.Piece;
import fr.iut.pieces.Pion;
import fr.iut.pieces.Reine;
import fr.iut.pieces.Roi;
import fr.iut.pieces.Tour;
import fr.iut.plateau.Plateau;

public class GestionnairePartie {
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
							new File(nomFichier)
							)
					);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Fichier non trouvé : " + nomFichier);
		}

		for (int i = 0; i < echiquier.length; i++) {
			String line = br.readLine();
			String[] values = line.split(",");

			if (values.length != 8) {
				br.close();
				throw new IOException("Ligne " + (i+1) + " invalide dans le fichier " +
						nomFichier);
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
					throw new IOException("Nom de pièce invalide " + values[j] + 
							" à la ligne " + (i+1) + " du fichier " + nomFichier);
				}
			}
		}

		br.close();

		plat.setEchiquier(echiquier);
	}

	public void chargerAnciennePartie(String nomFichier) throws IOException, Exception {
		if (nomFichier.equals("nouvellePartie.csv"))
			throw new Exception("Vous n'avez pas le droit de charger le fichier \"nouvellePartie.csv\".");
		this.nomFichier = nomFichier;
		chargerPartie(nomFichier);
	}

	public void nouvellePartie() throws IOException {
		chargerPartie("nouvellePartie.csv");
	}
	
	public void sauvegarderPartie() throws IOException {
		BufferedWriter bw;
		
		try {
			bw = new BufferedWriter(new FileWriter(new File(nomFichier)));
			
			for (Piece[] pieces : plat.getEchiquier()) {
				for (int j = 0; j < pieces.length; j++) {
					bw.write(
							((pieces[j] == null) ? "V" : pieces[j].toString()) + 
							((j == pieces.length - 1 )? "" : ",")
							);
				}
				bw.write("\n");
			}
			
			bw.close();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Fichier non trouvé : " + nomFichier);
		}
	}

	public void sauvegarderPartie(String nomFichier) throws IOException {
		this.nomFichier = nomFichier;
		sauvegarderPartie();
	}
}
