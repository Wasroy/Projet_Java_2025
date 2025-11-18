# Projet Java IM2D 2025 - Cours de Mr. Hugo Gilbert

## Groupe

- **Romane Fayon** – Simulation de l'équipe municipale  
- **Nathalie Habib** – Modélisation du sac à dos multidimensionnel et critères d'ordre ajout
- **William Miserolle** – Méthode gloutonne à ajout et critère d'ordre retrait

---

## Description du projet

Ce projet comprend deux parties principales :

1. **Simulation d'une équipe municipale** : Simulation du cycle de travail d'une équipe municipale où des experts proposent des projets, des évaluateurs les chiffrent selon leurs spécialités (économie, social, environnement) et un élu fixe le bénéfice.

2. **Résolution du problème du sac à dos multidimensionnel** : Implémentation d'un solveur glouton pour résoudre le problème du sac à dos avec plusieurs contraintes budgétaires (dimensions multiples).

---

## Structure du projet

```
Projet/
├── src/
│   ├── module-info.java
│   │
│   ├── equipe/
│   │   ├── Elu.java
│   │   ├── Employe.java
│   │   ├── EquipeMunicipale.java
│   │   ├── Evaluateur.java
│   │   ├── Expert.java
│   │   ├── Main.java
│   │   ├── Projet.java
│   │   ├── Secteur.java
│   │   └── Specialisation.java
│   │
│   ├── sacADos/
│   │   ├── DemoSacGlouton.java
│   │   ├── Objet.java
│   │   └── SacADos.java
│   │
│   └── solveur/
│       └── glouton/
│           ├── GloutonAjoutSolver.java
│           ├── OrdreObjetsAjoutPremier.java
│           ├── OrdreObjetsAjoutDeuxieme.java
│           └── OrdreObjetsRetrait.java
│
└── bin/ (fichiers compilés)
```

---

## Fonctionnalités implémentées

### 1. Simulation d'une équipe municipale

Le module `equipe` simule le fonctionnement d'une équipe municipale :

- **`Employe`** : Classe de base pour tous les employés (nom, prénom, âge)
- **`Expert`** : Hérite de `Employe`, propose des projets dans ses secteurs de compétence
- **`Evaluateur`** : Hérite de `Employe`, évalue les coûts des projets selon sa spécialisation (ÉCONOMIE, SOCIAL, ENVIRONNEMENT)
- **`Elu`** : Hérite de `Employe`, évalue le bénéfice des projets
- **`EquipeMunicipale`** : Orchestre le cycle complet :
  1. Les experts proposent des projets
  2. Les évaluateurs chiffrent les coûts selon leurs spécialités
  3. L'élu fixe le bénéfice
  4. Les projets finalisés sont stockés dans `projetsComplets`

**Exécution** : `java equipe.Main`

### 2. Problème du sac à dos multidimensionnel

Le module `sacADos` modélise et résout le problème du sac à dos avec plusieurs contraintes budgétaires :

- **`Objet`** : Représente un objet avec :
  - Une valeur d'utilité
  - Un tableau de coûts multidimensionnels (ex: coût économique, social, environnemental)
  - Méthodes : `getUtilite()`, `getCouts()`, `getCoutTotal()`, `afficherObjet()`

- **`SacADos`** : Représente le problème avec :
  - Une dimension (nombre de types de coûts)
  - Un tableau de budgets (un budget par dimension)
  - Une liste d'objets disponibles
  - Méthodes : `getObjets()`, `getDimension()`, `getBudgets()`, `afficherSacADos()`

### 3. Solveur glouton

Le module `solveur.glouton` implémente une méthode gloutonne pour résoudre le problème :

- **`GloutonAjoutSolver`** : Solveur principal qui :
  - Trie les objets selon un comparateur fourni
  - Ajoute les objets un par un s'ils respectent toutes les contraintes budgétaires
  - Retourne la liste des objets sélectionnés

- **Comparateurs implémentés** :
  - **`OrdreObjetsAjoutPremier`** : Trie par ratio utilité/coût total (décroissant)
  - **`OrdreObjetsAjoutDeuxieme`** : Trie par ratio utilité/coût maximum (décroissant)
  - **`OrdreObjetsRetrait`** : Comparateur pour retrait, basé sur la dimension avec le plus grand dépassement de budget

**Exécution** : `java sacADos.DemoSacPremierGlouton`

---

## Compilation et exécution

### Compilation

Depuis la racine du projet :

```bash
javac -d bin src/**/*.java
```

### Exécution

**Simulation de l'équipe municipale** :
```bash
cd bin
java equipe.Main
```

**Démonstration du solveur glouton** :
```bash
cd bin
java sacADos.DemoSacGlouton
```

---

## Exemple d'utilisation

### Exemple du solveur glouton

Le fichier `DemoSacGlouton.java` montre un exemple d'utilisation :

```java
// Création d'objets avec utilité et coûts multidimensionnels
Objet o1 = new Objet(12, new int[]{3, 5});
Objet o2 = new Objet(7, new int[]{2, 4});
// ...

// Création du sac à dos avec budgets
int[] budgets = {10, 12};
SacADos sac = new SacADos(2, budgets, objets);

// Résolution avec méthode gloutonne
List<Objet> resultat = GloutonAjoutSolver.methodeGloutonneAjout(
    sac, 
    new OrdreObjetsAjoutPremier()
);
```

---


## État d'avancement

### Implémenté

- Simulation complète de l'équipe municipale
- Modélisation du sac à dos multidimensionnel
- Solveur glouton avec ajout
- Trois stratégies de tri différentes (2x Ajouts 1x Retrait)
- Démonstrations fonctionnelles


## Auteurs

- Romane Fayon
- Nathalie Habib  
- William Miserolle

---

*Projet réalisé dans le cadre du cours IM2D 2025 de Mr. Hugo Gilbert*
