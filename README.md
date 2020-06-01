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
Une fois le mode console lancer, l'interprète de commande vous demandera de saisir soit 1 pour démarrer une nouvelle partie, soit 2 pour charger une partie existante.  

##### Lancer une nouvelle partie
Apres avoir écrit "1" pour lancer une nouvelle partie, le tableau d'échecs apparaitra et vous pourrez jouer.  
Pour se déplacer, il suffit d'écrire la position initiale et la position finale.
![Image montrant la console](https://cdn.discordapp.com/attachments/685203840282394720/716938571574935562/unknown.png "Image montrant la console")
```bash
A2 A4 #Déplacer une pièce de la case a2 à la case a4 par exemple
```

##### Sauvegarder une partie
Pour sauvegarder le jeu, il vous suffit d'écrire "sauvegarder" à la place de votre saisi pour le déplacement.  
Le fichier sauvegardé prendra pour nom "partieActuelle.csv"
Si vous souhaitez donner un nom particulier au fichier de sauvegarde, ajouter le nom du fichier après "sauvegarder":
```bash
sauvegarder <nom du fichier>
```
Chaque fichier sauvegardé sera alors dans le répertoire "parti" du répertoire du projet.

##### Charger une ancienne partie
Apres avoir écrit "2" pour charger une ancienne partie, la console vous demandera le nom du fichier à charger, il vous suffit de rentrer le nom de votre sauvegarde.
![Image montrant la console](https://nsa40.casimages.com/img/2020/05/26/200526073557307707.png "Image montrant la console")
Votre ancienne partie sera automatiquement rechargé et vous reprendrez la ou vous vous êtiez laissez!

##### Quitter le jeu
Pour quitter le jeu, il vous suffit d'écrire "quitter" à la place de votre saisi pour le déplacement.

### Graphique
Une fois la commande d'[exécution](#-comment-jouer) lancée, écrire "2" pour lancer le mode Graphique.

##### Lancer une nouvelle partie
Pour lancer une nouvelle partie faites CTRL + N

##### Enregistrer une partie
Pour sauvegarder une partie faites CTRL + S

###### Pour enregistrer sous
Pour sauvegarder une partie sous un fichier spécifique faites CTRL + SHIFT + S

##### Pour charger une partie
Pour charger une partie faites CTRL + C

> Tous droits réservés à En Panne Corp.