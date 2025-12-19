package app;
import equipe.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import pont.*;
import sacADos.*;
import solveur.glouton.*;
import solveur.hill_climbing.*;

/**
 * Classe principale avec menus pour tester les différents solveurs
 * interface console amelioree pour le projet de Mr Hugo Gilbert
 */
public class Main {
	
	private static SacADos sacCourant = null;	
	private static List<Objet> solutionCourante = null; //on stocke la solution courante (sera remplie après un solveur)
	private static Scanner scanner = new Scanner(System.in);
	
	//delai pour l'affichage progressif (en ms) - on peut le reduire si c'est trop lent
	private static final int DELAI_COURT = 3;
	private static final int DELAI_MOYEN = 40;
	private static final int DELAI_LONG = 100;
	
	public static void main(String[] args) {
		
		//on affiche le splash screen au demarrage du programme
		afficherSplashScreen();
		
		boolean continuer = true;
		while (continuer == true) {
			sacCourant = menuInstance();
			if (sacCourant == null) {
				System.out.println("\n[!] Aucun sac a dos charge, veuillez en creer un d'abord");
				continue; //on recommence la boucle si pas de sac
			}
			afficherMenuPrincipal();
			int choix = lireChoix();
			switch (choix) {
				case 1: menuGloutonAjout();
					break;
				case 2 : menuGloutonRetrait();
					break;
				case 3 : menuHillClimbing();
					break;
				case 4 : menuGloutonHillClimbing();
					break;
				case 5 : 
					afficherAuRevoir();
					continuer = false;
					break;
				default : System.out.println("[!] choix non valide veuillez reessayer");
					break;
			}
		}
		scanner.close();
	}
	
	/**
	 * affiche l'ecran de demarrage avec le titre du projet en ascii art
	 * c'est pour faire joli au lancement du programme
	 */
	private static void afficherSplashScreen() {
		effacerConsole();
		
		//le titre en ascii art
		//IMPORTANT: on utilise uniquement de l'ASCII "safe" (pas de caracteres Unicode type ╔═█),
		//car selon le terminal (notamment Windows) ils peuvent s'afficher en "???".
		String[] titre = {
			"",
			"    +-------------------------------------------------------------------+",
			"    |                                                                   |",
			"    |   #####    ##    ####      ##       ####     ####   #####         |",
			"    |  #        #  #  #         #  #      #    #  #    #  #             |",
			"    |   ####   ###### #        ######     #    #  #    #   ####         |",
			"    |       #  #    # #        #    #     #    #  #    #       #        |",
			"    |  #####   #    #  ####    #    #     #####    ####   #####         |",
			"    |                                                                   |",
			"    |         Probleme du Sac a Dos Multidimensionnel                   |",
			"    |                                                                   |",
			"    +-------------------------------------------------------------------+",
			""
		};
		
		//on affiche le titre lettre par lettre pour un effet sympa
		for (String ligne : titre) {
			afficherProgressif(ligne, DELAI_COURT);
			System.out.println();
		}
		
		pause(DELAI_MOYEN);
		
		//infos du projet
		String[] infos = {
			"    +-------------------------------------------------------------------+",
			"    |                    Projet Java L3 Info 2025                       |",
			"    |                     Cours de Mr Hugo Gilbert                      |",
			"    |                     Universite Paris Dauphine                     |",
			"    +-------------------------------------------------------------------+",
			"    |  Auteurs : William Miserolle, Romane Fayon, Nathalie Habib        |",
			"    +-------------------------------------------------------------------+",
			""
		};
		
		for (String ligne : infos) {
			afficherProgressif(ligne, DELAI_COURT);
			System.out.println();
		}
		
		pause(DELAI_LONG);
		
		//petit message de chargement pour faire pro
		System.out.print("\n    Chargement des modules");
		for (int i = 0; i < 3; i++) {
			pause(300);
			System.out.print(".");
		}
		System.out.println(" OK!\n");
		
		pause(DELAI_MOYEN);
		
		System.out.println("    Appuyez sur ENTREE pour continuer...");
		scanner.nextLine();
	}

	// ==================== PERF / STATS ====================
	/**
	 * affichage compatible avec les terminals widnows et linux (vu qu'on sait pas sur quel ordi le prof va tester) : que du ASCII
	 */
	private static void afficherPerformance(String nomMethode, SacADos sac, List<Objet> resultat, long tempsNano) {
		System.out.println();
		afficherBoite("PERFORMANCE - " + nomMethode, 55);
		
		//temps
		double tempsMs = tempsNano / 1000000.0;
		
		//petites stats sur la solution
		int nbTotal = 0;
		if (sac != null) nbTotal = sac.getObjets().size();
		
		int nbPris = 0;
		if (resultat != null) nbPris = resultat.size();
		
		//utilite totale
		int utiliteTotale = 0;
		if (resultat != null) {
			for (Objet o : resultat) {
				utiliteTotale = utiliteTotale + o.getUtilite();
			}
		}
		
		System.out.println("    Temps    : " + String.format(java.util.Locale.US, "%.2f", tempsMs) + " ms");
		System.out.println("    Solution : " + nbPris + "/" + nbTotal + " objets");
		System.out.println("    Utilite  : " + utiliteTotale);
		
		//couts + validite
		if (sac != null) {
			int[] budgets = sac.getBudgets();
			int[] coutsTotal = new int[budgets.length];
			
			if (resultat != null) {
				for (Objet o : resultat) {
					int[] c = o.getCouts();
					for (int d = 0; d < budgets.length; d++) {
						if (c != null && d < c.length) {
							coutsTotal[d] = coutsTotal[d] + c[d];
						}
					}
				}
			}
			
			boolean valide = true;
			for (int d = 0; d < budgets.length; d++) {
				if (coutsTotal[d] > budgets[d]) {
					valide = false;
					break;
				}
			}
			
			System.out.println("    Valide   : " + (valide ? "OUI" : "NON"));
			System.out.println("    Budgets  :");
			
			for (int d = 0; d < budgets.length; d++) {
				int budget = budgets[d];
				int cout = coutsTotal[d];
				int pct = (budget <= 0) ? 0 : (int) Math.round((100.0 * cout) / budget);
				String barre = faireBarre(budget, cout, 20);
				System.out.println("    - d" + (d+1) + " : " + cout + "/" + budget + "  " + barre + "  " + pct + "%");
			}
		}
		
		afficherSeparateur("-", 55);
	}

	//barre ASCII style [#######-----]
	private static String faireBarre(int budget, int cout, int taille) {
		if (taille <= 0) taille = 10;
		int remplissage = 0;
		if (budget > 0) {
			remplissage = (int) Math.round((1.0 * cout * taille) / budget);
		}
		if (remplissage < 0) remplissage = 0;
		if (remplissage > taille) remplissage = taille;
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < taille; i++) {
			if (i < remplissage) sb.append("#");
			else sb.append("-");
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * affiche le message de fin quand l'utilisateur quitte
	 */
	private static void afficherAuRevoir() {
		System.out.println();
		afficherSeparateur("=", 60);
		System.out.println("    Merci d'avoir utilise notre projet !");
		System.out.println("    A bientot pour la soutenance :)");
		afficherSeparateur("=", 60);
		System.out.println();
	}
	
	/**
	 * affiche le menu principal pour choisir le solveur
	 */
	private static void afficherMenuPrincipal() {
		System.out.println();
		afficherBoite("MENU PRINCIPAL - Choix du Solveur", 50);
		System.out.println("    |                                                  |");
		System.out.println("    |   [1]  Methode gloutonne a ajout                 |");
		System.out.println("    |   [2]  Methode gloutonne a retrait               |");
		System.out.println("    |   [3]  Hill Climbing                             |");
		System.out.println("    |   [4]  Glouton + Hill Climbing                   |");
		System.out.println("    |   [5]  Quitter le programme                      |");
		System.out.println("    |                                                  |");
		System.out.println("    +--------------------------------------------------+");
		System.out.print("\n    >>> Votre choix : ");
	}
	
	/**
	 * pour afficher le menu qui permet de choisir comment on génère le sac à dos 
	 * @return le sac à dos créer selon la méthode choisie 
	 */
	private static SacADos menuInstance() {
		System.out.println();
		afficherBoite("CREATION DE L'INSTANCE", 50);
		System.out.println("    |                                                  |");
		System.out.println("    |   [1]  Generer une instance aleatoire            |");
		System.out.println("    |   [2]  Charger depuis un fichier .dat            |");
		System.out.println("    |   [3]  Generer depuis projets municipaux         |");
		System.out.println("    |   [4]  Creation manuelle du sac a dos            |");
		System.out.println("    |   [0]  Retour / Garder l'instance actuelle       |");
		System.out.println("    |                                                  |");
		System.out.println("    +--------------------------------------------------+");
		System.out.print("\n    >>> Votre choix : ");
		
		int choix = lireChoix();
		switch (choix) {
			case 1:
				return SacADosAleatoire();
			case 2:
				return SacADosFichier();
			case 3:
				return SacADosProjets();
			case 4:
				return SacADosManuel();
			case 0:
				return sacCourant;
			default:
				System.out.println("    [!] Choix invalide");
				return sacCourant;
		}
	}
	
	/**
	 * génère un sac à dos aléatoire
	 * @return une instance de sac à dos 
	 */
	private static SacADos SacADosAleatoire() {
		System.out.println("\n    [*] Generation d'une instance aleatoire...");
		pause(DELAI_MOYEN);
		
		List<Objet> objets = new ArrayList<>();
		Random r = new Random();
		int dimension = 1 + r.nextInt(5); //on rajoute +1 car on veut de 1 a 5 sans le 0 pouvant etre genere
		int nbObjets = 1 + r.nextInt(5); 
		int utiliteMax = 20;
		int coutMax = 10; 
		
		// génération des objets
		for (int i = 0; i < nbObjets; i++) {
			int utilite = 1 + r.nextInt(utiliteMax);
			int[] couts = new int[dimension];
			for (int d = 0; d < dimension; d++) {
				couts[d] = 1 + r.nextInt(coutMax);
			}
			objets.add(new Objet(utilite, couts));
		}
		
		// génération des budgets
		int[] budgets = new int[dimension];
		for (int d = 0; d < dimension; d++) {
			budgets[d] = (nbObjets * coutMax) / 2; //pour que le budget permete de séléctionner une partie des objets mais pas tous sinon cas trivial
		}
		
		SacADos sac = new SacADos(dimension, budgets, objets);
		
		System.out.println("    [OK] Sac a dos genere avec succes!\n");
		afficherSeparateur("-", 50);
		sac.afficherSacADos();
		afficherSeparateur("-", 50);
		
		return sac;
	}
	
	/**
	 * génère un sac à dos à partir d'un fichier .dat
	 * @return une instance de sac à dos
	 */
	private static SacADos SacADosFichier() {
		System.out.println("\n    [*] Chargement depuis un fichier benchmark");
		System.out.print("    Entrez le chemin du fichier .dat : ");
		String chemin = scanner.nextLine();
		
		System.out.println("    [*] Lecture du fichier en cours...");
		pause(DELAI_MOYEN);
		
		SacADos sac = VersSacADos.creerSacADosDepuisFichier(chemin);
		if (sac != null) {
			System.out.println("    [OK] Fichier charge avec succes!\n");
			afficherSeparateur("-", 50);
			sac.afficherSacADos();
			afficherSeparateur("-", 50);
		}
		else {
			System.out.println("    [ERREUR] Impossible de charger le fichier");
		}
		return sac;
	}
	
	/**
	 * génère un sac à dos à partir de projets de l'équipe municipale
	 * @return une instance de sac à dos
	 */
	private static SacADos SacADosProjets() {
		System.out.println("\n    [*] Simulation de l'equipe municipale de Dauphine City");
		afficherSeparateur("-", 50);
		
		System.out.print("    Combien de projets voulez-vous generer ? ");
		String n = scanner.nextLine();
		int nbProjets = Integer.parseInt(n);
		
		System.out.println("    [*] Lancement du cycle de simulation...");
		pause(DELAI_MOYEN);
		
		EquipeMunicipale equipeMun = new EquipeMunicipale();
		equipeMun.Cycle(nbProjets);
		List<Projet> projetsComplets = equipeMun.getProjetsComplets();
		
		if (projetsComplets == null || projetsComplets.isEmpty()) {
			System.out.println("    [ERREUR] Aucun projet municipal n'a ete genere");
			return null;
		}
		
		System.out.println("    [OK] " + projetsComplets.size() + " projet(s) genere(s) !\n");
		
		//on affiche les projets generés
		System.out.println("    Projets proposés par les experts :");
		for (Projet p : projetsComplets) {
			System.out.println("    - " + p.getTitre());
		}
		System.out.println();
		
		//Demander les budgets
		int[] budgets = new int[3];
		System.out.println("    Definissez les budgets municipaux :");
		System.out.print("    > Budget ECONOMIE (en euros) : ");
		budgets[0] = lireChoix();
		System.out.print("    > Budget SOCIAL (en euros) : ");
		budgets[1] = lireChoix();
		System.out.print("    > Budget ENVIRONNEMENT (en euros) : ");
		budgets[2] = lireChoix();
		
		//Transformation en SacADos
		System.out.println("\n    [*] Conversion des projets en instance de sac a dos...");
		pause(DELAI_MOYEN);
		
		VersSacADos convertisseur = new VersSacADos();
		SacADos sac = convertisseur.creerSADProjet(
			projetsComplets.toArray(new Projet[0]),
			budgets
		);
		
		System.out.println("    [OK] Conversion terminee!\n");
		afficherSeparateur("-", 50);
		sac.afficherSacADos();
		afficherSeparateur("-", 50);
		
		return sac;
	}
	
	/**
	 * menu pour la methode gloutonne à ajout
	 */
	private static void menuGloutonAjout() {
		SacADos sac = sacCourant;
		if (sac == null) {
			return; //on sort si l'utilisateur a annulé
		}
		
		System.out.println();
		afficherBoite("METHODE GLOUTONNE A AJOUT", 50);
		System.out.println("    |                                                  |");
		System.out.println("    |   Choisissez le critere d'ordre :                |");
		System.out.println("    |                                                  |");
		System.out.println("    |   [1]  Premier critere : utilite / cout total    |");
		System.out.println("    |        f(o) = u / somme(c)                       |");
		System.out.println("    |                                                  |");
		System.out.println("    |   [2]  Deuxieme critere : utilite / cout max     |");
		System.out.println("    |        f(o) = u / max(c)                         |");
		System.out.println("    |                                                  |");
		System.out.println("    +--------------------------------------------------+");
		System.out.print("\n    >>> Votre choix : ");
		
		int choixOrdre = lireChoix();
		List<Objet> resultat;
		
		switch (choixOrdre) {
			case 1: 
				System.out.println("\n    [*] Execution avec critere f(o) = u / somme(c)...");
				afficherBarreProgression();
				long debut = System.nanoTime();
				resultat = GloutonAjoutSolver.methodeGloutonneAjout(sac, new OrdreObjetsAjoutPremier());
				long fin = System.nanoTime();
				afficherResultatJoli("Methode Gloutonne Ajout (critere 1)", resultat);
				afficherPerformance("Glouton Ajout (critere 1)", sac, resultat, fin - debut);
				solutionCourante = resultat;
				break;
			case 2 : 
				System.out.println("\n    [*] Execution avec critere f(o) = u / max(c)...");
				afficherBarreProgression();
				long debut2 = System.nanoTime();
				resultat = GloutonAjoutSolver.methodeGloutonneAjout(sac, new OrdreObjetsAjoutDeuxieme());
				long fin2 = System.nanoTime();
				afficherResultatJoli("Methode Gloutonne Ajout (critere 2)", resultat);
				afficherPerformance("Glouton Ajout (critere 2)", sac, resultat, fin2 - debut2);
				solutionCourante = resultat;
				break;
			default : 
				System.out.println("    [!] Choix invalide, retour au menu");
				break;
		}		
	}
	
	/**
	 * menu pour la methode gloutonne à retrait
	 */
	private static void menuGloutonRetrait() {
		SacADos sac = sacCourant;
		if (sac == null) {
			return;
		}
		
		System.out.println();
		afficherBoite("METHODE GLOUTONNE A RETRAIT", 50);
		System.out.println("    |                                                  |");
		System.out.println("    |   Critere de retrait :                           |");
		System.out.println("    |   f(o) = u / cout(dimension la plus depassee)    |");
		System.out.println("    |                                                  |");
		System.out.println("    |   Principe :                                     |");
		System.out.println("    |   1. On commence avec tous les objets            |");
		System.out.println("    |   2. On retire les moins interessants            |");
		System.out.println("    |   3. On applique glouton a ajout                 |");
		System.out.println("    |                                                  |");
		System.out.println("    +--------------------------------------------------+");
		
		System.out.println("\n    [*] Execution de la methode gloutonne a retrait...");
		afficherBarreProgression();
		
		long debut = System.nanoTime();
		List<Objet> resultat = GloutonRetraitSolver.methodeGloutonneRetrait(sac, new OrdreObjetsRetrait(sac));
		long fin = System.nanoTime();
		afficherResultatJoli("Methode Gloutonne Retrait", resultat);
		afficherPerformance("Glouton Retrait", sac, resultat, fin - debut);
		solutionCourante = resultat;
	}
	
	/**
	 * menu pour la méthode du Hill Climbing
	 */
	private static void menuHillClimbing() {
		SacADos sac = sacCourant;
		if (sac == null) {
			return;
		}
		
		System.out.println();
		afficherBoite("HILL CLIMBING", 50);
		System.out.println("    |                                                  |");
		System.out.println("    |   Algorithme d'optimisation locale               |");
		System.out.println("    |   Voisinage : S' = (S \\ E) U A                   |");
		System.out.println("    |   avec |E| <= t et |A| <= t                      |");
		System.out.println("    |                                                  |");
		System.out.println("    +--------------------------------------------------+");
		
		//on propose d'ameliorer une solution existante si y en a une
		if (solutionCourante != null && !solutionCourante.isEmpty()) {
			System.out.println("\n    [i] Une solution existante a ete detectee !");
			System.out.println("    Voulez-vous l'ameliorer avec Hill Climbing ?");
			System.out.println("    [1] Oui, partir de la solution existante");
			System.out.println("    [2] Non, partir de zero");
			System.out.print("    >>> Votre choix : ");
			int choix = lireChoix();
			if (choix == 1) {
				// Hill Climbing à partir de solutionCourante 
				// (idée : on commence par un solveur glouton car approche plus grossière puis HillClimbing pour affiner le résultat)
				System.out.println("    [*] Amelioration de la solution existante...");
			}
			// Sinon continue normalement
		}
		
		System.out.println("\n    Choisissez la variante :");
		System.out.println("    [1]  Hill Climbing classique (explore tous les voisins)");
		System.out.println("    [2]  Hill Climbing aleatoire (voisins limites)");
		System.out.print("\n    >>> Votre choix : ");
		
		int choixHC = lireChoix();
		List<Objet> resultat;
		
		if (choixHC == 1) {
			System.out.println("\n    [*] Execution du Hill Climbing classique...");
			System.out.println("    [*] Exploration de tous les voisins a chaque iteration...");
			afficherBarreProgression();
			
			HillClimbingNormale hcNormale = new HillClimbingNormale();
			long debut = System.nanoTime();
			resultat = hcNormale.resoudre(sac);
			long fin = System.nanoTime();
			afficherResultatJoli("Hill Climbing Classique", resultat);
			afficherPerformance("Hill Climbing Classique", sac, resultat, fin - debut);
		}
		else if (choixHC == 2) {
			System.out.print("    Nombre de voisins aleatoires a explorer : ");
			int nbVoisins = lireChoix();
			if (nbVoisins <= 0) {
				System.out.println("    [!] Nombre invalide, retour au menu");
				return;
			}
			
			System.out.println("\n    [*] Execution du Hill Climbing aleatoire...");
			System.out.println("    [*] Exploration de " + nbVoisins + " voisins par iteration...");
			afficherBarreProgression();
			
			HillClimbingAlea hcAlea = new HillClimbingAlea(nbVoisins);
			long debut2 = System.nanoTime();
			resultat = hcAlea.resoudre(sac);
			long fin2 = System.nanoTime();
			afficherResultatJoli("Hill Climbing Aleatoire (" + nbVoisins + " voisins)", resultat);
			afficherPerformance("Hill Climbing Aleatoire (" + nbVoisins + ")", sac, resultat, fin2 - debut2);
		}
		else {
			System.out.println("    [!] Choix invalide, retour au menu");
			return;
		}
		solutionCourante = resultat;
	}
	
	private static void menuGloutonHillClimbing() {
	    SacADos sac = sacCourant;
	    if (sac == null) return;

	    System.out.println();
	    afficherBoite("GLOUTON + HILL CLIMBING", 55);

	    // phase 1 : glouton
	    System.out.println("\n    Phase 1 : Methode gloutonne");
	    System.out.println("    Choisissez le glouton initial :");
	    System.out.println("    [1] Glouton a ajout (critere 1 : u / somme(c))");
	    System.out.println("    [2] Glouton a ajout (critere 2 : u / max(c))");
	    System.out.println("    [3] Glouton a retrait");
	    System.out.print("\n    >>> Votre choix : ");

	    int choixGlouton = lireChoix();
	    List<Objet> solutionInitiale = null;

	    afficherBarreProgression();

	    switch (choixGlouton) {
	        case 1:
	        	long debutG1 = System.nanoTime();
	            solutionInitiale = GloutonAjoutSolver.methodeGloutonneAjout(
	                sac, new OrdreObjetsAjoutPremier());
	            long finG1 = System.nanoTime();
	            afficherPerformance("Glouton + HC - Glouton Ajout (critere 1)", sac, solutionInitiale, finG1 - debutG1);
	            break;
	        case 2:
	        	long debutG2 = System.nanoTime();
	            solutionInitiale = GloutonAjoutSolver.methodeGloutonneAjout(
	                sac, new OrdreObjetsAjoutDeuxieme());
	            long finG2 = System.nanoTime();
	            afficherPerformance("Glouton + HC - Glouton Ajout (critere 2)", sac, solutionInitiale, finG2 - debutG2);
	            break;
	        case 3:
	        	long debutG3 = System.nanoTime();
	            solutionInitiale = GloutonRetraitSolver.methodeGloutonneRetrait(
	                sac, new OrdreObjetsRetrait(sac));
	            long finG3 = System.nanoTime();
	            afficherPerformance("Glouton + HC - Glouton Retrait", sac, solutionInitiale, finG3 - debutG3);
	            break;
	        default:
	            System.out.println("    [!] Choix invalide");
	            return;
	    }

	    afficherResultatJoli("Solution initiale (Glouton)", solutionInitiale);

	    // phase 2 : Hill Climbing
	    System.out.println("\n    Phase 2 : Hill Climbing");
	    System.out.println("    Choisissez la variante :");
	    System.out.println("    [1] Hill Climbing classique");
	    System.out.println("    [2] Hill Climbing aleatoire");
	    System.out.print("\n    >>> Votre choix : ");
	    int choixHC = lireChoix();
	    List<Objet> solutionFinale;
	    afficherBarreProgression();
	    if (choixHC == 1) {
	        HillClimbingNormale hc = new HillClimbingNormale();
	        hc.setSolutionInitiale(solutionInitiale,sac);
	        long debutHC = System.nanoTime();
	        solutionFinale = hc.resoudre(sac);
	        long finHC = System.nanoTime();
	        afficherPerformance("Glouton + HC - Hill Climbing classique", sac, solutionFinale, finHC - debutHC);
	    }
	    else if (choixHC == 2) {
	        System.out.print("    Nombre de voisins aleatoires : ");
	        int nbVoisins = lireChoix();
	        HillClimbingAlea hcAlea = new HillClimbingAlea(nbVoisins);
	        hcAlea.setSolutionInitiale(solutionInitiale,sac);
	        long debutHC2 = System.nanoTime();
	        solutionFinale = hcAlea.resoudre(sac);
	        long finHC2 = System.nanoTime();
	        afficherPerformance("Glouton + HC - Hill Climbing aleatoire (" + nbVoisins + ")", sac, solutionFinale, finHC2 - debutHC2);
	    }
	    else {
	        System.out.println("    [!] Choix invalide");
	        return;
	    }

	    afficherResultatJoli("Solution finale (Glouton + Hill Climbing)", solutionFinale);

	    // ==================== STOCKAGE ====================
	    solutionCourante = solutionFinale;
	}

	
	/**
	 * créer un sac a dos en demandant a l'utilisateur ce qu'il souhaite faire
	 * @return le sac a dos cree ou "null" si annulé
	 */
	private static SacADos SacADosManuel() {
		System.out.println();
		afficherBoite("CREATION MANUELLE", 50);
		System.out.println("    |                                                  |");
		System.out.println("    |   [1]  Sac a dos par defaut (test rapide)        |");
		System.out.println("    |   [2]  Sac a dos personnalise                    |");
		System.out.println("    |                                                  |");
		System.out.println("    +--------------------------------------------------+");
		System.out.print("\n    >>> Votre choix : ");
		
		int choix = lireChoix();
		if (choix == 1) {
			return creerSacDefaut();
		}
		else if (choix == 2) {
			return creerSacPersonnalise();
		}
		else {
			System.out.println("    [!] Choix invalide");
			return null;
		}
	}
	
	/**
	 * cree un sac a dos par defaut pour les tests
	 * @return le sac a dos
	 */
	private static SacADos creerSacDefaut() {
		System.out.println("\n    [*] Creation du sac a dos par defaut...");
		pause(DELAI_MOYEN);
		
		//pour stocker les objs
		List<Objet> objets = new ArrayList<>();
		//on cree des objets pour tester
		Objet o1 = new Objet(12, new int[]{3, 5});
		Objet o2 = new Objet(7, new int[]{2, 4});
		Objet o3 = new Objet(10, new int[]{4, 6});
		Objet o4 = new Objet(15, new int[]{5, 7});
		Objet o5 = new Objet(10, new int[]{3, 5});
		objets.add(o1);
		objets.add(o2);
		objets.add(o3);
		objets.add(o4);
		objets.add(o5);
		int[] budgets = {10, 12};
		SacADos sac = new SacADos(2, budgets, objets);
		
		System.out.println("    [OK] Sac a dos par defaut cree!\n");
		afficherSeparateur("-", 50);
		sac.afficherSacADos();
		afficherSeparateur("-", 50);
		
		return sac;
	}
	
	/**
	 * cree un sac a dos personnalise en demandant a l'utilisateur
	 * @return le sac a dos ou null si erreur
	 */
	private static SacADos creerSacPersonnalise() {
		System.out.println("\n    [*] Creation personnalisee du sac a dos");
		afficherSeparateur("-", 50);
		
		System.out.print("    Nombre de dimensions (contraintes) : ");
		int dimension = lireChoix();
		
		if (dimension <= 0) {
			System.out.println("    [!] Dimension invalide");
			return null;
		}
		
		int[] budgets = new int[dimension];
		
		//on demande les budgets pour chaque dimension
		System.out.println("\n    Definition des budgets :");
		for (int i = 0; i < dimension; i++) {
			System.out.print("    > Budget dimension " + (i+1) + " : ");
			budgets[i] = lireChoix();
		}
		
		System.out.print("\n    Nombre d'objets a creer : ");
		int nbObjets = lireChoix();
		if (nbObjets <= 0) {
			System.out.println("    [!] Nombre d'objets invalide");
			return null;
		}
		
		List<Objet> objets = new ArrayList<>();
		//on cree chaque objet en demandant utilite et couts
		for (int i = 0; i < nbObjets; i++) {
			System.out.println("\n    --- Objet " + (i+1) + " ---");
			System.out.print("    Utilite : ");
			int utilite = lireChoix();
			int[] couts = new int[dimension];
			for (int j = 0; j < dimension; j++) {
				System.out.print("    Cout dimension " + (j+1) + " : ");
				couts[j] = lireChoix();
			}
			try {
				Objet obj = new Objet(utilite, couts);
				objets.add(obj);
				System.out.println("    [OK] Objet " + (i+1) + " ajoute");
			}
			catch (IllegalArgumentException e) {
				System.out.println("    [ERREUR] " + e.getMessage());
				System.out.println("    Objet ignore");
			}
		}
		
		try {
			SacADos sac = new SacADos(dimension, budgets, objets);
			System.out.println("\n    [OK] Sac a dos cree avec succes!\n");
			afficherSeparateur("-", 50);
			sac.afficherSacADos();
			afficherSeparateur("-", 50);
			return sac;
		}
		catch (IllegalArgumentException err) {
			System.out.println("    [ERREUR] " + err.getMessage());
			return null;
		}
	}
	
	/**
	 * affiche le resultat d'un solveur de maniere jolie
	 * @param nomMethode le nom de la methode utilisee
	 * @param resultat c'est la liste des objets selectionnes
	 */
	private static void afficherResultatJoli(String nomMethode, List<Objet> resultat) {
		System.out.println();
		afficherBoite("RESULTATS : " + nomMethode, 55);
		
		if (resultat == null || resultat.isEmpty() == true) {
			System.out.println("    |");
			System.out.println("    |   [!] Aucun objet selectionne");
			System.out.println("    |");
		}
		else {
			int utiliteTotale = 0;
			for (Objet o : resultat) {
				utiliteTotale = utiliteTotale + o.getUtilite();
			}
			
			System.out.println("    |");
			System.out.println("    |   >>> UTILITE TOTALE : " + utiliteTotale);
			System.out.println("    |   >>> Objets selectionnes : " + resultat.size());
			System.out.println("    |");
			System.out.println("    +-------------------------------------------------------");
			System.out.println("    |   Detail des objets :");
			System.out.println("    |");
			
			int num = 1;
			for (Objet o : resultat) {
				System.out.println("    |   Objet " + num + " : utilite=" + o.getUtilite() + ", couts=" + arrayToString(o.getCouts()));
				num++;
			}
			System.out.println("    |");
		}
		System.out.println("    +-------------------------------------------------------");
		System.out.println();
	}
	
	/**
	 * convertit un tableau d'int en string lisible
	 * @param arr le tableau
	 * @return la string genre "[1, 2, 3]"
	 */
	private static String arrayToString(int[] arr) {
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
			if (i < arr.length - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * pour lire un choix entier depuis l'entree standard on créer une méthode 
	 * car on va bcp l'utiliser pour bien intéragir avec l'utilisateur et ça évitera de refaire a chaque fois le scanner
	 * @return l'entier lu
	 */
	private static int lireChoix() {
		//on verif qu'il y a bien un entier a lire
		while (scanner.hasNextInt() == false) {
			String ligne = scanner.next(); //on lit ce qui n'est pas un int pour pas bloquer
			System.out.println("    [!] Veuillez entrer un nombre entier valide");
			System.out.print("    >>> Votre choix : ");
		}
		int choix = scanner.nextInt();
		scanner.nextLine(); //on vide le buffer pour la prochaine lecture
		return choix;
	}
	
	// ==================== METHODES D'AFFICHAGE ====================
	
	/**
	 * affiche une boite avec un titre dedans pour faire joli
	 * @param titre le texte a mettre dans la boite
	 * @param largeur la largeur de la boite
	 */
	private static void afficherBoite(String titre, int largeur) {
		//ligne du haut
		System.out.print("    +");
		for (int i = 0; i < largeur; i++) {
			System.out.print("-");
		}
		System.out.println("+");
		
		//ligne avec le titre (centré)
		int padding = (largeur - titre.length()) / 2;
		System.out.print("    |");
		for (int i = 0; i < padding; i++) {
			System.out.print(" ");
		}
		System.out.print(titre);
		for (int i = 0; i < largeur - padding - titre.length(); i++) {
			System.out.print(" ");
		}
		System.out.println("|");
		
		//ligne du bas
		System.out.print("    +");
		for (int i = 0; i < largeur; i++) {
			System.out.print("-");
		}
		System.out.println("+");
	}
	
	/**
	 * affiche un separateur horizontal
	 * @param caractere le caractere a utiliser (genre "-" ou "=")
	 * @param longueur la longueur du separateur
	 */
	private static void afficherSeparateur(String caractere, int longueur) {
		System.out.print("    ");
		for (int i = 0; i < longueur; i++) {
			System.out.print(caractere);
		}
		System.out.println();
	}
	
	/**
	 * affiche du texte de maniere progressive (lettre par lettre)
	 * ca fait un effet de machine a ecrire assez cool
	 * @param texte le texte a afficher
	 * @param delai le delai entre chaque caractere en ms
	 */
	private static void afficherProgressif(String texte, int delai) {
		for (int i = 0; i < texte.length(); i++) {
			System.out.print(texte.charAt(i));
			if (delai > 0) {
				pause(delai);
			}
		}
	}
	
	/**
	 * affiche une barre de progression pour les calculs
	 * ca fait plus pro quand on attend
	 */
	private static void afficherBarreProgression() {
		System.out.print("    [");
		for (int i = 0; i < 20; i++) {
			System.out.print("=");
			pause(40);
		}
		System.out.println("] OK!\n");
	}
	
	/**
	 * fait une pause dans l'execution
	 * @param ms la duree en millisecondes
	 */
	private static void pause(int ms) {
		try {
			Thread.sleep(ms);
		}
		catch (InterruptedException e) {
			//on ignore l'exception c'est pas grave si la pause est interrompue
		}
	}
	
	/**
	 * essaie d'effacer la console (marche sur linux/mac, pas toujours sur windows)
	 * c'est pas grave si ca marche pas, ca affiche juste des lignes vides
	 */
	private static void effacerConsole() {
		//on essaie d'abord avec le code ANSI (marche sur linux/mac)
		System.out.print("\033[H\033[2J");
		System.out.flush();
		
		//sinon on affiche des lignes vides pour "effacer" visuellement
		for (int i = 0; i < 5; i++) {
			System.out.println();
		}
	}
}
