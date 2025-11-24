# Projet Java IM2D 2025

**Cours :** Mr. Hugo Gilbert

## Groupe

- **Romane Fayon** – Simulation de l'équipe municipale
- **Nathalie Habib** – Modélisation du sac à dos multidimensionnel et critères d'ordre ajout
- **William Miserolle** – Méthode gloutonne à ajout et critère d'ordre retrait

## Description

Ce projet comprend deux parties principales :

1. **Simulation d'une équipe municipale** : Simulation du cycle de travail d'une équipe municipale où des experts proposent des projets, des évaluateurs les chiffrent selon leurs spécialités (économie, social, environnement) et un élu fixe le bénéfice.

2. **Résolution du problème du sac à dos multidimensionnel** : Implémentation d'un solveur glouton pour résoudre le problème du sac à dos avec plusieurs contraintes budgétaires (dimensions multiples).

## Structure du projet

```
Projet/
└── src/
    ├── module-info.java
    ├── main.java
    │
    ├── equipe/
    │   ├── Elu.java
    │   ├── Employe.java
    │   ├── EquipeMunicipale.java
    │   ├── Evaluateur.java
    │   ├── Expert.java
    │   ├── Main.java
    │   ├── Projet.java
    │   ├── Secteur.java
    │   └── Specialisation.java
    │
    ├── sacADos/
    │   ├── DemoSacGlouton.java
    │   ├── Objet.java
    │   └── SacADos.java
    │
    └── solveur/
        └── glouton/
            ├── GloutonAjoutSolver.java
            ├── GloutonRetraitSolver.java
            ├── OrdreObjetsAjoutPremier.java
            ├── OrdreObjetsAjoutDeuxieme.java
            └── OrdreObjetsRetrait.java
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
- **`Main.java`** : Point d'entrée pour la simulation

**Exécution :**
```bash
cd bin
java equipe.Main
```

### 2. Problème du sac à dos multidimensionnel

Le package `sacADos` modélise le problème du sac à dos avec plusieurs contraintes budgétaires :

- **`Objet.java`** : Représente un objet avec une valeur d'utilité et un tableau de coûts multidimensionnels
- **`SacADos.java`** : Représente le problème avec dimension, budgets et liste d'objets disponibles
- **`DemoSacGlouton.java`** : Démonstration du solveur glouton

### 3. Solveur glouton

Le package `solveur.glouton` implémente une méthode gloutonne pour résoudre le problème :

- **`GloutonAjoutSolver.java`** : Solveur principal qui ajoute les objets selon un ordre de tri
- **`GloutonRetraitSolver.java`** : Solveur qui utilise une méthode de retrait
- **`OrdreObjetsAjoutPremier.java`** : Comparateur pour tri par ratio utilité/coût total (décroissant)
- **`OrdreObjetsAjoutDeuxieme.java`** : Comparateur pour tri par ratio utilité/coût maximum (décroissant)
- **`OrdreObjetsRetrait.java`** : Comparateur pour retrait, basé sur la dimension avec le plus grand dépassement de budget

**Exécution :**
```bash
cd bin
java sacADos.DemoSacGlouton
```

## Compilation

Depuis la racine du projet :

```bash
javac -d bin src/**/*.java
```

## Auteurs

- Romane Fayon
- Nathalie Habib
- William Miserolle

---

*Projet réalisé dans le cadre du cours IM2D 2025 de Mr. Hugo Gilbert*
