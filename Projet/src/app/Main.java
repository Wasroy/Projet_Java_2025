package app;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import equipe.*;
import sacADos.*;
import solveur.glouton.*;
import solveur.hill_climbing.*;
import pont.*;

/**
 * Classe principale avec menus pour tester les différents solveurs
 */
public class Main {
	
	private static SacADos sacCourant = null;	
	private static List<Objet> solutionCourante = null; //on stocke la solution courante (sera remplie après un solveur)
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean continuer = true;
		while (continuer == true) {
			sacCourant=menuInstance();
			menuSolveur();
			int choix = lireChoix();
			switch (choix) {
				case 1: menuGloutonAjout();
					break;
				case 2 : menuGloutonRetrait();
					break;
				case 3 : menuHillClimbing();
					break;
				case 4 : continuer = false;
					break;
				default : System.out.println("choix non valide veuillez reessayer");
					break;
			}
		}
		scanner.close();
	}
	
	/**
	 *pour afficher le menu qui permet de choisir le solveur
	 */
	private static void menuSolveur() {
		System.out.println("\n================================");
		System.out.println("Menu principal");
		System.out.println("================================");
		System.out.println("1 - Methode gloutonne à ajout");
		System.out.println("2 - Methode gloutonne à retrait");
		System.out.println("3 - Hill Climbing");
		System.out.println("4 - Quitter");
		System.out.print("Votre choix : ");
	}
	
	/**
	 * pour afficher le menu qui permet de choisir comment on génère le sac à dos 
	 * @return le sac à dos créer selon la méthode choisie 
	 */
	private static SacADos menuInstance() {
	    System.out.println("\n---- Choix de l'instance du probleme ----");
	    System.out.println("1 - Generer une instance aleatoire");
	    System.out.println("2 - Charger une instance depuis un fichier");
	    System.out.println("3 - Generer depuis les projets municipaux");
	    System.out.println("4 - Sac a dos manuel");
	    System.out.println("0 - Retour");
	    System.out.print("Votre choix : ");
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
	            System.out.println("Choix invalide");
	            return sacCourant;
	    }
	}
	
	/**
	 * génère un sac à dos aléatoire
	 * @return une instance de sac à dos 
	 */
	private static SacADos SacADosAleatoire() {
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
	    System.out.println("\nVoici le Sac a dos genere aleatoirement :");
	    sac.afficherSacADos();
	    return sac;
	}
	
	/**
	 * génère un sac à dos à partir d'un fichier .dat
	 * @return une instance de sac à dos
	 */
	private static SacADos SacADosFichier() {
		System.out.print("Entrez le chemin du fichier .dat : ");
		String chemin = scanner.nextLine();
		SacADos sac = VersSacADos.creerSacADosDepuisFichier(chemin);
	    if (sac != null) {
	        System.out.println("\nSac a dos charge depuis le fichier :");
	        sac.afficherSacADos();
	    }
	    return sac;
	}
	
	/**
	 * génère un sac à dos à partir de projets de l'équipe municipale
	 * @return une instance de sac à dos
	 */
	private static SacADos SacADosProjets() {
	    // Création
		System.out.print("Combien de projets voulez vous: ");
		String n = scanner.nextLine();           // lit la ligne
		int nbProjets = Integer.parseInt(n);     // convertit la String en int
		EquipeMunicipale equipeMun = new EquipeMunicipale();
		equipeMun.Cycle(nbProjets);
	    List<Projet> projetsComplets = equipeMun.getProjetsComplets();
	    if (projetsComplets == null || projetsComplets.isEmpty()) {
	        System.out.println("Aucun projet municipal n'a ete genere");
	        return null;
	    }
	    //Demander les budgets
	    int[] budgets = new int[3];
	    System.out.println("Entrez les budgets municipaux :");
	    System.out.print("Budget ECONOMIE : ");
	    budgets[0] = lireChoix();
	    System.out.print("Budget SOCIAL : ");
	    budgets[1] = lireChoix();
	    System.out.print("Budget ENVIRONNEMENT : ");
	    budgets[2] = lireChoix();
	    //Transformation en SacADos
	    VersSacADos convertisseur = new VersSacADos();
	    SacADos sac = convertisseur.creerSADProjet(
	        projetsComplets.toArray(new Projet[0]),
	        budgets
	    );
	    System.out.println("\nSac a dos cree a partir des projets municipaux :");
	    sac.afficherSacADos();
	    return sac;
	}
	
	/**
	 *menu pour la methode gloutonne à ajout
	 */
	private static void menuGloutonAjout() {
		SacADos sac = sacCourant;
		if (sac == null) {
			return; //on sort si l'utilisateur a annulé
		}
		System.out.println("\n--- Methode gloutonne à ajout ---");
		System.out.println("Choisissez le critere d'ordre :");
		System.out.println("1 - Premier critere (utilite/cout total)");
		System.out.println("2 - Deuxieme critere (utilite/cout max)");
		System.out.print("Votre choix : ");
		int choixOrdre = lireChoix();
		List<Objet> resultat;
		
		switch (choixOrdre) {
			case 1: 
				resultat = GloutonAjoutSolver.methodeGloutonneAjout(sac, new OrdreObjetsAjoutPremier());
				System.out.println("\nResultat avec premier critere :");
				afficherResultat(resultat);
				solutionCourante = resultat;
				break;
			case 2 : 
				resultat = GloutonAjoutSolver.methodeGloutonneAjout(sac, new OrdreObjetsAjoutDeuxieme());
				System.out.println("\nResultat avec deuxieme critere :");
				afficherResultat(resultat);
				solutionCourante = resultat;
				break;
			default : 
				System.out.println("Choix invalide, retour au menu");
				break;
			}		
	}
	
	/**
	 *menu pour la methode gloutonne à retrait
	 */
	private static void menuGloutonRetrait() {
		SacADos sac = sacCourant;
		if (sac == null) {
			return;
		}
		System.out.println("\n--------Methode gloutonne à retrait ------");
		System.out.println("Critère de retrait");
		List<Objet> resultat = GloutonRetraitSolver.methodeGloutonneRetrait(sac, new OrdreObjetsRetrait(sac));
		System.out.println("\nResultat avec methode retrait :");
		afficherResultat(resultat);
		solutionCourante = resultat;
	}
	
	/**
	 *menu pour la méthode du Hill Climbing
	 */
	private static void menuHillClimbing() {
		SacADos sac = sacCourant;
		if (sac == null) {
			return;
		}
		
		System.out.println("\n----Hill Climbing-------");
		
	    if (solutionCourante != null && !solutionCourante.isEmpty()) {
	        System.out.println("Une solution existe déjà. Voulez-vous l'améliorer avec Hill Climbing ?");
	        System.out.println("1 - Oui, utiliser la solution existante");
	        System.out.println("2 - Non, partir de zéro");
	        System.out.print("Votre choix : ");
	        int choix = lireChoix();
	        if (choix == 1) {
	            // Hill Climbing à partir de solutionCourante (idée : on commence par un solveur glouton car approche plus grossière puis HillClimbing pour affiner le résultat
	        }
	        // Sinon continue normalement
	    }
	
		System.out.println("Choisissez la variante :");
		System.out.println("1 - Hill Climbing normale (tous les voisins)");
		System.out.println("2 - Hill Climbing aleatoire (nombre limite de voisins)");
		System.out.print("Votre choix : ");
		int choixHC = lireChoix();
		List<Objet> resultat;
		if (choixHC == 1) {
			HillClimbingNormale hcNormale = new HillClimbingNormale();
			resultat = hcNormale.resoudre(sac);
			System.out.println("\nResultat avec Hill Climbing normale :");
		}
		else if (choixHC == 2) {
			System.out.print("Nombre de voisins aleatoires a considerer : ");
			int nbVoisins = lireChoix();
			if (nbVoisins <= 0) {
				System.out.println("Nombre invalide, retour au menu");
				return;
			}
			HillClimbingAlea hcAlea = new HillClimbingAlea(nbVoisins);
			resultat = hcAlea.resoudre(sac);
			System.out.println("\nResultat avec Hill Climbing aleatoire :");
		}
		else {
			System.out.println("Choix invalide, retour au menu");
			return;
		}
		afficherResultat(resultat);
		solutionCourante = resultat;
	}
	
	/**
	 * créer un sac a dos en demandant a l'utilisateur ce qu'il souhaite faire
	 * @return le sac a dos cree ou "null" si annulé
	 */
	
	private static SacADos SacADosManuel() {
		System.out.println("\n----Creation du sac a dos------");
		System.out.println("Choisissez :");
		System.out.println("1 - Sac à dos par defaut (pour tester rapidement)");
		System.out.println("2 - Sac à dos personnalise");
		System.out.print("Votre choix : ");
		int choix = lireChoix();
		if (choix == 1) {
			return creerSacDefaut();
		}
		else if (choix == 2) {
			return creerSacPersonnalise();
		}
		else {
			System.out.println("Choix invalide");
			return null;
		}
	}
	
	/**
	 *cree un sac a dos par defaut pour les tests
	 * @return le sac a dos
	 */
	private static SacADos creerSacDefaut() {
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
		System.out.println("\nVoici le Sac a dos de base :");
		sac.afficherSacADos();
		return sac;
	}
	
	/**
	 *cree un sac a dos personnalise en demandant a l'utilisateur
	 * @return le sac a dos ou null si erreur
	 */
	private static SacADos creerSacPersonnalise() {
		
		System.out.print("Nombre de dimensions (nombre de contraintes) : ");
		int dimension = lireChoix();
		
		if (dimension <= 0) {

			System.out.println("Dimension invalide");
			return null;
		}
		
		int[] budgets = new int[dimension];
		
		//on demande les budgets pour chaque dimension
		for (int i=0; i<dimension; i++) {
			System.out.print("Entrez un Budget pour la dimension " + (i+1) + " : ");
			budgets[i] = lireChoix();
		}
		System.out.print("Nombre d'objets : ");
		int nbObjets = lireChoix();
		if (nbObjets <= 0) {
			System.out.println("Nombre d'objets invalide");
			return null;
		}
		List<Objet> objets = new ArrayList<>();
		//on cree chaque objet en demandant utilite et couts
		for (int i=0; i<nbObjets; i++) {
			System.out.println("\nObjet " + (i+1) + " :");
			System.out.print("Utilite : ");
			int utilite = lireChoix();
			int[] couts = new int[dimension];
			for (int j=0; j<dimension; j++) {
				System.out.print("Cout dimension " + (j+1) + " : ");
				couts[j] = lireChoix();
			}
			try {
				Objet obj = new Objet(utilite, couts);
				objets.add(obj);
			}
			catch (IllegalArgumentException e) {
				System.out.println("Erreur : " + e.getMessage());
				System.out.println("Objet ignore");
			}
		}
		try {
			SacADos sac = new SacADos(dimension, budgets, objets);
			System.out.println("\nSac a dos cree :  ");
			sac.afficherSacADos();
			return sac;
		}
		catch (IllegalArgumentException err) {
			System.out.println("Erreur lors de la creation du sac : " + err.getMessage());
			return null;
		}
	}
	
	/**
	 * pour affiche le resultat d'un solveur
	 * @param resultat c'est la liste des objets selectionnes
	 */
	private static void afficherResultat(List<Objet> resultat) {
		if (resultat == null || resultat.isEmpty() == true) {
			System.out.println("aucun objet selectionne");
			return;
		}
		System.out.println("nombre d'objets selectionnes : " + resultat.size());
		int utiliteTotale = 0;
		for (Objet o : resultat) {
			utiliteTotale = utiliteTotale + o.getUtilite();
		}
		System.out.println("Utilite totale : " + utiliteTotale);
		System.out.println("\n Voici les details des objets selectionnes :");
		for (Objet o : resultat) {
			o.afficherObjet();
			System.out.println("-----");
		}
	}

	/**
	 *pour lire un choix entier depuis l'entree standard on créer une méthode 
     *car on va bcp l'utiliser pour bien intéragir avec l'utilisateur et ça évitera de refaire a chaque fois le scanner
	 * @return l'entier lu
	 */
	private static int lireChoix() {
		//on verif qu'il y a bien un entier a lire
		while (scanner.hasNextInt() == false) {
			String ligne = scanner.next(); //on lit ce qui n'est pas un int pour pas bloquer
			System.out.println("Veuillez entrer un nombre entier valide");
			System.out.print("Votre choix : ");
		}
		int choix = scanner.nextInt();
		scanner.nextLine(); //on vide le buffer pour la prochaine lecture
		return choix;
	}
}

