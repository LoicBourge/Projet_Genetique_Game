# Projet_Genetique_Game

## Description

Le projet consiste à créer un jeu de plateforme en utilisant des algorithmes génétiques. Le but du jeu est de déplacer une créature d'un point de départ à un point d'arrivée en évitant les obstacles. La créature peut effectuer des mouvements vers le haut, le bas, la gauche, la droite et en diagonale.

## Fonctionnalités
- Création d'un environnement de jeu avec une grille, une position de départ et une position d'arrivée.
- Création d'une créature avec une liste de mouvements.
- Déplacement de la créature dans l'environnement.
- Gestion de la gravité lors du déplacement de la créature.
- Calcul du score de la créature en fonction de sa distance par rapport à l'objectif et du nombre de mouvements valides effectués.
- Sélection des meilleures créatures en fonction de leur score.
- Croisement des meilleures créatures pour créer une nouvelle génération de créatures.
- Mutation des mouvements de la créature en fonction d'un taux de mutation.
- Affichage de l'environnement et de la créature.
- Affichage des étapes du jeu.
- Affichage de la meilleure créature.
- Affichage du score de la meilleure créature.
- Affichage du nombre de mouvements de la meilleure créature.

## Classes
1. **Game**
   - **Description :** La classe `Game` gère le déroulement du jeu. Elle initialise un certain nombre de créatures, les fait évoluer au fil des générations, et détermine la meilleure créature basée sur des critères définis.
   - **Méthodes principales :**
      - `playGame(boolean print)`: Simule le jeu pour un certain nombre de générations. Affiche les étapes du jeu si `print` est vrai.
      - `bestfitselection()`: Sélectionne la meilleure créature parmi un pourcentage défini.
      - `newGeneration()`: Crée une nouvelle génération de créatures en utilisant la sélection et le croisement des meilleurs parents.

2. **Creature**
   - **Description :** La classe `Creature` représente une créature dans le jeu. Elle peut muter, effectuer un croisement avec une autre créature, et est évaluée en fonction de sa performance dans l'environnement.
   - **Méthodes principales :**
      - `mutate(double mutationRate)`: Modifie aléatoirement certains mouvements de la créature en fonction du taux de mutation.
      - `crossover(Creature b, Environment environment, double crossoverRate)`: Effectue un croisement avec une autre créature pour créer une nouvelle créature.
      - `getScore()`: Calcule le score de la créature en fonction de sa distance par rapport à l'objectif et du nombre de mouvements valides effectués.

3. **Movement**
   - **Description :** L'énumération `Movement` définit les mouvements possibles pour une créature dans le jeu.
   - **Méthodes principales :**
      - `multiplechoiceMovement(String choice)`: Crée une liste de mouvements à partir d'une chaîne de caractères représentant des choix.
      - `generate(int MaxTic)`: Génère une liste aléatoire de mouvements pour une créature.

4. **Environment**
   - **Description :** La classe `Environment` représente l'environnement du jeu avec une grille, une position de départ et une position de fin. Elle gère le déplacement de la créature et la gravité.
   - **Méthodes principales :**
      - `movecreature(Movement movement, int maxtic)`: Déplace la créature en fonction du mouvement et met à jour la position.
      - `movegraviti(Movement movement, int maxtic)`: Gère la gravité lors du déplacement vers le bas ou en diagonale.
      - `printGrid()`: Affiche la grille de l'environnement.

5. **Coordinates**
   - **Description :** La classe `Coordinates` représente les coordonnées (x, y) dans le jeu.
   - **Méthodes principales :**
      - `equals(Object o)`: Vérifie si deux coordonnées sont égales.
      - `hashCode()`: Calcule le code de hachage pour les coordonnées.

6. **EnvironmentTile**
   - **Description :** L'énumération `EnvironmentTile` définit les types de tuiles dans l'environnement du jeu.
   - **Méthodes principales :**
      - `getTile()`: Renvoie la représentation textuelle de la tuile.

## Auteur
Bourge Loïc


