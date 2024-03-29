# Chess Game

## ♟ Introduction

Découvrez notre jeu d'échecs tout fraîchement créé par une équipe de développeurs motivés!  
Chaque partie du code a été testée pour éviter tous les bugs possibles ou imaginables.

Chaque joueur possède au départ seize pièces : un roi, une reine, deux tours, deux fous, deux cavaliers et huit pions.  
Le but du jeu est d'infliger un échec et mat où le roi adverse.
https://fr.wikipedia.org/wiki/R%C3%A8gles_du_jeu_d%27%C3%A9checs

## 🔨 Caractéristiques

- INTERFACE GRAPHIQUE ✔
- INTELLIGENCE ARTIFICIELLE ❌
- RÈGLES SUPPLÉMENTAIRES ❌

## 🕹 Comment jouer ?

### Pour Windows :
Pour lancer le jeu, il faut se rendre dans le répertoire du projet, lancer une invite de commande (shift + clic droit dans le répertoire puis powershell) et exécuter la commande suivante :
```bash
.\maven\bin\mvn exec:java
```

### Autre :
Pour lancer le jeu, il faut se rendre dans le répertoire du projet avec une invite de commande et exécuter la commande suivante :
```bash
./maven/bin/mvn exec:java
```

Vous allez avoir le choix entre le mode console et le mode graphique:

* [Utilisation avec le mode "Console"](#console)
* [Utilisation avec le mode "Graphique"](#graphique)

### Console

Une fois la commande d'[exécution](#-comment-jouer) lancée, écrire "1" pour lancer le mode Console.  
Une fois le mode console lancé, l'interprète de commande vous demandera de saisir soit 1 pour démarrer une nouvelle partie, soit 2 pour charger une partie existante.  

##### Lancer une nouvelle partie
Apres avoir écrit "1" pour lancer une nouvelle partie, l'échiquier apparaîtra et vous pourrez jouer.  
Pour se déplacer, il suffit d'écrire la position initiale et la position finale.
![Image montrant la console](https://cdn.discordapp.com/attachments/685203840282394720/716938571574935562/unknown.png "Image montrant la console")
```bash
A2 A4 #Déplacer une pièce de la case A2 à la case A4 par exemple
```

##### Sauvegarder une partie
Pour sauvegarder le jeu, il vous suffit d'écrire "sauvegarder" à la place de votre saisie pour le déplacement.  
Par défaut fichier sauvegardé prendra pour nom "partieActuelle.csv".
Si vous souhaitez donner un nom particulier au fichier de sauvegarde, il faut ajouter un nom après "sauvegarder" :
```
sauvegarder <nom du fichier>
```
Chaque fichier sauvegardé sera alors dans le répertoire "parties" à la racine du projet.

##### Charger une ancienne partie
Apres avoir écrit "2" pour charger une ancienne partie, la console vous demandera le nom du fichier à charger, il vous suffit de rentrer le nom de votre sauvegarde.  
![Image montrant la console](https://media.discordapp.net/attachments/685203840282394720/716940136096727040/unknown.png "Image graphique")  
Votre ancienne partie sera automatiquement rechargée, et l'invite de commande vous demandera qui commencera a la reprise de la partie.  
Il suffit d'écrire "blanc" ou "noir" pour que celui-ci commence.
Attention, seuls les fichiers CSV sont acceptés.

##### Quitter le jeu
Pour quitter le jeu, il vous suffit d'écrire "quitter" à la place de votre saisi pour le déplacement.

### Graphique
Une fois la commande d'[exécution](#-comment-jouer) lancée, écrire "2" pour lancer le mode Graphique.  
![Image montrant la console](https://cdn.discordapp.com/attachments/685203840282394720/716944254391418981/unknown.png "Image graphique")  

##### Lancer une nouvelle partie
Pour lancer une nouvelle partie faites CTRL + N ou allez dans le menu et sélectionnez "Nouvelle Partie".  
![Image montrant la console](https://cdn.discordapp.com/attachments/685203840282394720/716944581106729020/unknown.png "Image graphique")    

##### Pour charger une partie
Pour charger une partie faites CTRL + C ou allez dans le menu et sélectionnez "Charger une Partie".  
![Image montrant la console](https://cdn.discordapp.com/attachments/685203840282394720/716946833150050325/unknown.png "Image graphique")    
Une fois le fichier sélectionné, un message vous demandera de choisir qui commence :  
![Image montrant la console](https://cdn.discordapp.com/attachments/685203840282394720/716947184242786364/unknown.png "Image graphique")    

##### Enregistrer une partie
Pour enregistrer une partie sur le fichier actuel (partieActuelle.csv par défaut) faites CTRL + S ou allez dans le menu et sélectionnez "Enregistrer".  

##### Pour enregistrer sous
Pour enregistrer une partie sous un nom spécifique faites CTRL + SHIFT + S ou allez dans le menu et sélectionnez "Enregistrer sous...".  
![Image montrant la console](https://cdn.discordapp.com/attachments/685203840282394720/716948895120359465/unknown.png "Image graphique")  
Si vous choisissez un fichier déjà existant, une alerte vous demandera si vous êtes sûr de vous :  
![Image montrant la console](https://cdn.discordapp.com/attachments/685203840282394720/716949044194181172/unknown.png "Image graphique")  
  
##### Comment jouer?

Pour déplacer une pièce, cliquez dessus, puis cliquez sur la case où vous voulez que la pièce aille. Seuls les mouvements acceptés dans le jeu original sont acceptés.  
 
 
> Tous droits réservés à En Panne Corp.