# Projet Java IM2D 2025 — Gestion des budgets d’une ville

Projet de Java-Objet (L3 Info, IM2D) : Simulation d'une équipe municipale qui propose des projets, puis on transforme cela en problème de sac à dos multidimensionnel, pour finir on développe plusieurs manières de l'optimiser.

**Cours :** Mr. Hugo Gilbert  
**Université :** Paris Dauphine

## Groupe

- **Romane Fayon** : simulation équipe municipale, génération d’instance, lecture fichier `.dat`, une partie du menu
- **Nathalie Habib** : modélisation du sac à dos + critères d’ordre (glouton à ajout)
- **William Miserolle** : glouton à retrait, hill climbing, `Main` (menus)

## Ce que fait le programme

Le point d’entrée principal c’est `Projet/src/app/Main.java` (menu console).

### Création d’instance (sac à dos)

Dans le menu, on peut créer un `SacADos` de 4 façons :

- **Instance aléatoire** : dimension entre 1 et 5, quelques objets, budgets calculés pour éviter le cas “tout rentre”.
- **Depuis un fichier `.dat`** : lecture d’un benchmark (format MKP) puis conversion en `SacADos`.
- **Depuis des projets municipaux** : on lance une simulation qui génère des projets, puis on demande 3 budgets (économie/social/environnement) et on convertit en `SacADos`.
- **Manuel** : soit un sac “par défaut” (test rapide), soit création personnalisée (dimensions, budgets, objets).

### Solveurs disponibles

Ensuite on peut lancer :

- **Glouton à ajout** avec 2 ordres :
  - critère 1 : utilité / somme des coûts
  - critère 2 : utilité / coût max
- **Glouton à retrait** :
  - on commence avec tous les objets
  - on retire les “moins intéressants” tant que ça dépasse
  - puis on fait un glouton à ajout derrière
- **Hill Climbing** :
  - version classique (explore les voisins)
  - version aléatoire (explore un nombre limité de voisins par itération)
- **Combo Glouton + Hill Climbing** : on part d’un glouton (au choix) puis on “affine” avec hill climbing.

Le menu affiche aussi des petites stats : temps, utilité totale, validité de la solution, et coûts par dimension.

## Structure du projet

```
Projet/
└── src/
    ├── app/
    │   ├── Main.java        # menus console
    │   └── App.java         # petit fichier de démo
    │
    ├── equipe/              # simulation municipale
    ├── sacADos/             # modèle du sac à dos (Objet / SacADos)
    ├── solveur/
    │   ├── glouton/         # glouton ajout / retrait + comparateurs
    │   └── hill_climbing/   # hill climbing normal + aléatoire
    │
    ├── pont/                # conversions (projets <-> sac) + lecture .dat
    └── tests/               # tests JUnit
```

## Détails par package (simple)

### `equipe/` — simulation municipale

Idée : des **experts** proposent des projets, des **évaluateurs** donnent des coûts, et un **élu** donne un bénéfice.

- `Projet` : titre, description, secteur, bénéfice, et 3 coûts (économie/social/environnement).
- `Expert` : propose un projet dans un de ses secteurs (titre et description générés).
- `Evaluateur` : a une spécialisation (ECONOMIE / SOCIAL / ENVIRONNEMENT) et attribue un coût aléatoire.
- `Elu` : attribue un bénéfice aléatoire.
- `EquipeMunicipale` : méthode `cycle(int nbProjets)` qui génère des projets, les évalue, puis les met dans `projetsComplets`.
- `Fabrique` : crée des experts et les 3 évaluateurs “de base”.

### `sacADos/` — modèle du sac à dos multidimensionnel

- `Objet` : une utilité + un tableau de coûts (une case par dimension).
- `SacADos` : dimension, budgets, liste d’objets. Il y a aussi une méthode pour afficher le contenu.

### `solveur/glouton/`

- `GloutonAjoutSolver` : trie les objets selon un comparateur, puis ajoute si ça respecte les budgets.
- `GloutonRetraitSolver` : retire des objets jusqu’à respecter les budgets, puis applique un glouton à ajout.
- `OrdreObjetsAjoutPremier` : utilité / somme des coûts.
- `OrdreObjetsAjoutDeuxieme` : utilité / coût max.
- `OrdreObjetsRetrait` : utilité / coût sur la dimension la plus dépassée.

### `solveur/hill_climbing/`

- `SolutionHillClimbing` : une solution avec un tableau de booléens (true = objet pris), et des méthodes `utilite()`, `couts()`, `estValide()`.
- `HillClimbingNormale` : démarre (par défaut) d’une solution vide et explore les voisins. Voisinage avec `t=1` (on enlève/ajoute au plus 1 objet).
- `HillClimbingAlea` : pareil mais au lieu de tout explorer, on tire `nombreVoisins` voisins aléatoires par itération (plus rapide mais pas garanti).

### `pont/` — conversion + fichiers `.dat`

- `VersSacADos` :
  - convertit un `Projet` en `Objet` (utilité = bénéfice, coûts = (éco, social, env))
  - lit un fichier `.dat` (format MKP) → crée un `MKP` → convertit en `SacADos`
- `MKP` : juste un “conteneur” des données du fichier (n, k, utilités, contraintes, budgets, valeur optimale).

Petit rappel sur le `.dat` (benchmark) : première ligne `n k opt`, puis la liste des utilités, puis la matrice des contraintes (k lignes), puis les budgets.

## Compilation / Exécution

### 1) Compiler

Sous PowerShell (Windows) :

```powershell
cd Projet
mkdir bin -ea 0
javac -d bin (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object FullName)
```

Sous Linux/macOS :

```bash
cd Projet
mkdir -p bin
javac -d bin $(find src -name "*.java")
```

### 2) Lancer le menu

```bash
cd Projet
java -cp bin app.Main
```

## Tests JUnit

On a des tests dans `Projet/src/tests/` :

- `EluTest`
- `EvaluateurTest`
- `ExpertTest`
- `EquipeMunicipaleTest`
- `HillClimbingNormaleTest`
- `VersSacADosTest`
- `TestAjout`
- `TestRetrait`

Exemple de commande (si vous avez le jar dans `Projet/`) :

```bash
cd Projet
java -jar junit-platform-console-standalone-1.10.2.jar --class-path bin --scan-class-path
```

## Remarques rapides

- Les valeurs en “euros” dans la simulation sont tirées aléatoirement (coûts et bénéfices).
- Le menu est fait pour être lisible et testable (affichage, stats, etc.).

---

*Projet réalisé dans le cadre du cours Java-Objet L3 Info 2025–2026 (IM2D) — Université Paris Dauphine.*
