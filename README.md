# Projet Java — Gestion des budgets d’une ville

Ce projet simule une **équipe municipale** qui propose des projets (bénéfice + coûts), puis transforme ces projets en un **sac à dos multidimensionnel** et teste plusieurs **solveurs** (glouton, hill climbing) via un **menu console**.

Le point d’entrée est : `Projet/src/app/Main.java`.

## Prérequis

- Java (JDK) installé (Java 17+ recommandé).

## Compiler

### Windows (PowerShell)

```powershell
cd Projet
mkdir bin -ea 0
javac -d bin (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object FullName)
```

### Linux et macOS

```bash
cd Projet
mkdir -p bin
javac -d bin $(find src -name "*.java")
```

## Exécuter

```bash
cd Projet
java -cp bin app.Main
```

## Utiliser (menu console)

Dans le menu, vous pouvez :

- **Créer une instance** de sac à dos :
  - aléatoire,
  - depuis un fichier benchmark `.dat`,
  - depuis une simulation de projets municipaux,
  - ou en saisie manuelle.
- **Choisir un solveur** (glouton, glouton à retrait, hill climbing, etc.).
- **Lire les résultats** (utilité totale, validité, coûts par dimension, temps).

## Tests (JUnit)

Les tests sont dans `Projet/src/tests/`.

Si vous avez `junit-platform-console-standalone-1.10.2.jar` dans `Projet/` :

```bash
cd Projet
java -jar junit-platform-console-standalone-1.10.2.jar --class-path bin --scan-class-path
```

## Structure (rapide)

```
Projet/
└── src/
    ├── app/        # menu console (Main)
    ├── equipe/     # simulation municipale
    ├── sacADos/    # modèle (SacADos, Objet)
    ├── solveur/    # glouton + hill climbing
    ├── pont/       # conversions + lecture .dat
    └── tests/      # tests JUnit
```
