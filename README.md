# Projet Java IM2D 2025 cours de Mr. Hugo Gilbert

## Jalon 1 -Premier Rendu — Simulation & Sac à Dos Multidimensionnel



## Groupe
 

- **Romane Fayon** – Simulation de l’équipe municipale  

- **Nathalie Habib** – Modélisation du sac à dos multidimensionnel et méthodes gloutonnes

- **William Miserolle** – Méthode de Hill Climbing, génération d'instances et Main



---



## Objectifs du jalon



Ce premier rendu montre comment nous avons implémenté les premières fonctionnalitées du projet :


- code de la partie simulation de l'équipe municipale (experts, évaluateurs, élu)

- Représentation d'un problème du sac à dos 

- Méthode de résolution gloutonne “à ajout” (voir potentiellement a retrait)

- Programme `Main` permettant de tester la totalitée du projet.



Ce jalon n’inclut pas encore la documentation complète, la gestion poussée des exceptions, les tests JUnit ni les méthodes glouton à retrait ou Hill Climbing.



---



## Structure du projet



```

src/

├── module-info.java

├── equipe/

│     ├── Elu.java

│     ├── Employe.java

│     ├── EquipeMunicipale.java

│     ├── Evaluateur.java

│     ├── Expert.java

│     ├── Main.java

│     ├── Projet.java

│     ├── Secteur.java

│     └── Specialisation.java

│

└── solveur/

      ├── glouton/

      │     ├── GloutonAjoutSolveur.java

      │     └── GloutonRetraitSolveur.java

      │

      └── hill_climbing/  (en préparation)

```



---



## Fonctionnalités implémentées



### Simulation d’une équipe municipale

- `EquipeMunicipale` orchestre un cycle complet : les `Expert` proposent des projets, les `Evaluateur` les chiffrent selon leurs spécialités et l’`Elu` fixe le bénéfice.

- Les projets finalisés sont stockés dans `projetsComplets`, prêts pour l’étape suivante du projet.



---



### Programme Main

- `equipe.Main` instancie l’ensemble de l’équipe (élu, évaluateurs, experts) et lance `EquipeMunicipale.Cycle()`.

- Le programme affiche ensuite les projets finalisés générés durant ce cycle.



---



### Solveurs en préparation

- `solveur/glouton/GloutonAjoutSolveur` et `GloutonRetraitSolveur` sont posés comme squelettes pour intégrer prochainement les heuristiques gloutonnes.

- Le dossier `solveur/hill_climbing` est réservé pour les méthodes d’amélioration locale.

- La modélisation du sac à dos multidimensionnel (objets, budgets, contraintes) reste à implémenter.



---



## Compilation & exécution



Depuis la racine du projet :



```bash

javac -d bin src/**/*.java

cd bin

java Main

````



---



## Notes



Ce jalon constitue la première étape du projet.

Les prochaines parties intégreront :



* glouton à retrait,

* Hill Climbing,

* lecture d’instances externes,

* documentation Javadoc complète,

* tests JUnit,

* menu interactif complet dans la console.

