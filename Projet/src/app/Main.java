package app;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import sacADos.*;
import solveur.glouton.*;
import solveur.hill_climbing.*;

/**
 * Classe principale avec menus pour tester les différents solveurs
 */
public class Main {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		boolean continuer = true;
		
		while (continuer == true) {
			
			afficherMenuPrincipal();
			int choix = lireChoix();
			
			if (choix == 1) {

				menuGloutonAjout();
			}

			else if (choix == 2) {

				menuGloutonRetrait();
			}
			else if (choix == 3) {

				menuHillClimbing();
			}

			else if (choix == 4) {

				continuer = false;

			}
			else {

				System.out.println("choix non valide veuillez reessayer");

			}

		}
		
		scanner.close();
	}
	
	/**
	 *pour afficher le menu principal
	 */
	private static void afficherMenuPrincipal() {
		System.out.println("\n================================");
		System.out.println("Menu principal");
		System.out.println("================================");
		System.out.println("1 - Methode gloutonne a ajout");
		System.out.println("2 - Methode gloutonne a retrait");
		System.out.println("3 - Hill Climbing");
		System.out.println("4 - Quitter");
		System.out.print("Votre choix : ");
	}
	
	/**
	 *menu pour la methode gloutonne a ajout
	 */
	private static void menuGloutonAjout() {
		
		SacADos sac = creerSacADos();
		
		if (sac == null) {
			return; //on sort si l'utilisateur a annulé

		}
		
		System.out.println("\n--- Methode gloutonne a ajout ---");
		System.out.println("Choisissez le critere d'ordre :");

		System.out.println("1 - Premier critere (utilite/cout total)");
		System.out.println("2 - Deuxieme critere (utilite/cout max)");

		System.out.print("Votre choix : ");
		
		int choixOrdre = lireChoix();
		
		List<Objet> resultat;
		
		if (choixOrdre == 1) {
			resultat = GloutonAjoutSolver.methodeGloutonneAjout(sac, new OrdreObjetsAjoutPremier());

			System.out.println("\nResultat avec premier critere :");
		}
		else if (choixOrdre == 2) {
			resultat = GloutonAjoutSolver.methodeGloutonneAjout(sac, new OrdreObjetsAjoutDeuxieme());

			System.out.println("\nResultat avec deuxieme critere :");

		}
        //gerer les erreurs de choix
		else {

			System.out.println("Choix invalide, retour au menu");

			return;
		}
		
		afficherResultat(resultat);
		
	}
	
	/**
	 *menu pour la methode gloutonne a retrait
	 */
	private static void menuGloutonRetrait() {
		
		SacADos sac = creerSacADos();
		
		if (sac == null) {
			return;
		}
		
		System.out.println("\n--------Methode gloutonne a retrait ------");
		System.out.println("Critère de retrait");
		
		List<Objet> resultat = GloutonRetraitSolver.methodeGloutonneRetrait(sac, new OrdreObjetsRetrait());
		
		System.out.println("\nResultat avec methode retrait :");
		afficherResultat(resultat);
		
	}
	
	/**
	 *menu pour la méthode du Hill Climbing
	 */
	private static void menuHillClimbing() {
		
		SacADos sac = creerSacADos();
		
		if (sac == null) {
			return;
		}
		
		System.out.println("\n----Hill Climbing-------");
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
		
	}
	
	/**
	 * créer un sac a dos en demandant a l'utilisateur ce qu'il souhaite faire
	 * @return le sac a dos cree ou "null" si annulé
	 */
	private static SacADos creerSacADos() {
		
		System.out.println("\n----Creation du sac a dos------");
		System.out.println("Choisissez :");

		System.out.println("1 - Sac a dos par defaut (pour tester rapidement)");

		System.out.println("2 - Sac a dos personnalise");

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

