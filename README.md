# Projet Java IM2D 2025

**Cours :** Mr. Hugo Gilbert

## Groupe

- **Romane Fayon** – Simulation de l'équipe municipale
- **Nathalie Habib** – Modélisation du sac à dos multidimensionnel et critères d'ordre ajout
- **William Miserolle** – Méthode gloutonne à retrait, Hill Climbing et Main

## Description

Ce projet comprend trois parties principales :

1. **Simulation d'une équipe municipale** : Simulation du cycle de travail d'une équipe municipale où des experts proposent des projets, des évaluateurs les chiffrent selon leurs spécialités (économie, social, environnement) et un élu fixe le bénéfice.

2. **Résolution du problème du sac à dos multidimensionnel** : Implémentation de plusieurs solveurs (glouton à ajout, glouton à retrait, hill climbing) pour résoudre le problème du sac à dos avec plusieurs contraintes budgétaires.

3. **Pont entre les deux parties** : Conversion des projets municipaux en instances de sac à dos, et lecture de fichiers benchmark (.dat).

## Structure du projet

```
Projet/
└── src/
    ├── module-info.java
    │
    ├── app/
    │   ├── App.java
    │   └── Main.java              # Point d'entrée avec menus console
    │
    ├── equipe/
    │   ├── Elu.java
    │   ├── Employe.java           # Classe abstraite
    │   ├── EquipeMunicipale.java
    │   ├── EquipeDemo.java
    │   ├── Evaluateur.java
    │   ├── Expert.java
    │   ├── Fabrique.java
    │   ├── Projet.java
    │   ├── Secteur.java           # Enum
    │   └── Specialisation.java    # Enum
    │
    ├── sacADos/
    │   ├── DemoSacGlouton.java
    │   ├── Objet.java
    │   └── SacADos.java
    │
    ├── solveur/
    │   ├── Solveur.java           # Interface commune
    │   ├── glouton/
    │   │   ├── GloutonAjoutSolver.java
    │   │   ├── GloutonRetraitSolver.java
    │   │   ├── OrdreObjetsAjoutPremier.java
    │   │   ├── OrdreObjetsAjoutDeuxieme.java
    │   │   └── OrdreObjetsRetrait.java
    │   └── hill_climbing/
    │       ├── HillClimbingNormale.java
    │       ├── HillClimbingAlea.java
    │       └── SolutionHillClimbing.java
    │
    ├── pont/
    │   ├── VersSacADos.java       # Conversion projets/fichiers → SacADos
    │   └── MKP.java               # Modélisation fichiers benchmark
    │
    └── tests/
        ├── EluTest.java
        ├── EvaluateurTest.java
        ├── ExpertTest.java
        ├── EquipeMunicipaleTest.java
        └── VersSacADosTest.java
```

## Fonctionnalités

### 1. Simulation d'une équipe municipale

Le package `equipe` simule le fonctionnement d'une équipe municipale :

- **`Employe.java`** : Classe abstraite de base pour tous les employés (nom, prénom, âge)
- **`Expert.java`** : Hérite de `Employe`, propose des projets dans ses secteurs de compétence
- **`Evaluateur.java`** : Hérite de `Employe`, évalue les coûts des projets selon sa spécialisation (ÉCONOMIE, SOCIAL, ENVIRONNEMENT)
- **`Elu.java`** : Hérite de `Employe`, évalue le bénéfice des projets
- **`Projet.java`** : Représente un projet avec titre, description, secteur, bénéfice et coûts par spécialisation
- **`Secteur.java`** : Énumération des secteurs (SPORT, SANTE, EDUCATION, CULTURE, ATTRACTIVITE)
- **`Specialisation.java`** : Énumération des spécialisations (ECONOMIE, SOCIAL, ENVIRONNEMENT)
- **`EquipeMunicipale.java`** : Orchestre le cycle complet de simulation
- **`Fabrique.java`** : Génère aléatoirement des experts et évaluateurs

### 2. Problème du sac à dos multidimensionnel

Le package `sacADos` modélise le problème du sac à dos avec plusieurs contraintes budgétaires :

- **`Objet.java`** : Représente un objet avec une valeur d'utilité et un tableau de coûts multidimensionnels
- **`SacADos.java`** : Représente le problème avec dimension, budgets et liste d'objets disponibles

### 3. Solveurs

#### Méthodes gloutonnes (`solveur.glouton`)

- **`GloutonAjoutSolver.java`** : Solveur qui ajoute les objets selon un ordre de tri
- **`GloutonRetraitSolver.java`** : Solveur qui commence avec tous les objets puis retire les moins intéressants
- **`OrdreObjetsAjoutPremier.java`** : Comparateur utilité/coût total
- **`OrdreObjetsAjoutDeuxieme.java`** : Comparateur utilité/coût max
- **`OrdreObjetsRetrait.java`** : Comparateur pour retrait basé sur la dimension la plus dépassée

#### Hill Climbing (`solveur.hill_climbing`)

- **`HillClimbingNormale.java`** : Version classique qui explore tous les voisins
- **`HillClimbingAlea.java`** : Variante qui explore un nombre limité de voisins aléatoires
- **`SolutionHillClimbing.java`** : Représentation d'une solution avec tableau de booléens

Les solveurs Hill Climbing implémentent l'interface `Solveur` qui définit la méthode `resoudre(SacADos sac)`.

### 4. Pont (`pont`)

- **`VersSacADos.java`** : Convertit les projets municipaux ou fichiers benchmark en SacADos
- **`MKP.java`** : Modélise les fichiers benchmark du problème MKP

### 5. Tests JUnit

5 classes de tests dans le package `tests` :
- `EluTest` : Test de l'évaluation des bénéfices
- `EvaluateurTest` : Test de l'évaluation des coûts
- `ExpertTest` : Test de la proposition de projets
- `EquipeMunicipaleTest` : Test du cycle complet
- `VersSacADosTest` : Test des conversions et lecture de fichiers

## Exécution

### Application principale (menus console)

```bash
cd Projet/bin
java app.Main
```

Le programme propose :
- Génération d'instances aléatoires ou depuis fichier
- Conversion de projets municipaux en sac à dos
- Résolution par méthodes gloutonnes ou Hill Climbing

### Compilation

Depuis la racine du projet :

```bash
cd Projet
javac -d bin src/**/*.java
```

### Tests JUnit

```bash
cd Projet
java -jar lib/junit-platform-console-standalone-1.10.2.jar --class-path bin --scan-class-path
```

## Auteurs

- Romane Fayon
- Nathalie Habib
- William Miserolle

---

*Projet réalisé dans le cadre du cours Java-Objet L3 Info 2025 de Mr. Hugo Gilbert - Université Paris Dauphine*
