package solveur.glouton;
import sacADos.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de test pour la méthode gloutonne de retrait
 * Permet de tester GloutonRetraitSolver avec différents scénarios
 */
public class TestGloutonRetrait {
	
	public static void main(String[] args) {
		System.out.println("========================================");
		System.out.println("TEST DE LA METHODE GLOUTONNE DE RETRAIT");
		System.out.println("========================================\n");
		
		// Test 1 : Sac avec des objets qui dépassent les budgets
		test1_SacAvecDepassement();
		
		System.out.println("\n\n");
		
		// Test 2 : Sac avec budgets plus serrés
		test2_SacAvecBudgetsSerres();
	}
	
	/**
	 * Test 1 : Crée un sac où la somme des coûts dépasse les budgets
	 * La méthode de retrait doit enlever des objets pour respecter les budgets
	 */
	private static void test1_SacAvecDepassement() {
		System.out.println("--- TEST 1 : Sac avec dépassement de budgets ---");
		
		// Création d'objets avec utilités et coûts
		Objet o1 = new Objet(12, new int[]{3, 5});
		Objet o2 = new Objet(7, new int[]{2, 4});
		Objet o3 = new Objet(10, new int[]{4, 6});
		Objet o4 = new Objet(15, new int[]{5, 7});
		Objet o5 = new Objet(10, new int[]{3, 5});
		
		List<Objet> objets = new ArrayList<>();
		objets.add(o1);
		objets.add(o2);
		objets.add(o3);
		objets.add(o4);
		objets.add(o5);
		
		// Budgets serrés pour forcer des retraits
		int[] budgets = {10, 12};
		SacADos sac = new SacADos(2, budgets, objets);
		
		// Affichage du sac initial
		System.out.println("\nSac à dos initial :");
		sac.afficherSacADos();
		
		// Calcul des coûts totaux initiaux
		System.out.println("\n--- Analyse initiale ---");
		afficherCoutsTotaux(sac);
		
		// Test de la méthode gloutonne de retrait
		System.out.println("\n--- Application de la méthode gloutonne de retrait ---");
		OrdreObjetsRetrait comparateur = new OrdreObjetsRetrait(sac);
		List<Objet> resultat = GloutonRetraitSolver.methodeGloutonneRetrait(sac, comparateur);
		
		// Affichage du résultat
		System.out.println("\n--- Resultat apres retrait ---");
		System.out.println("Nombre d'objets retenus : " + resultat.size());
		System.out.println("\nObjets retenus :");
		int utiliteTotale = 0;
		for (int i = 0; i < resultat.size(); i++) {
			System.out.println("\nObjet " + (i + 1) + " :");
			resultat.get(i).afficherObjet();
			utiliteTotale += resultat.get(i).getUtilite();
		}
		
		// Vérification des budgets
		System.out.println("\n--- Verification des budgets ---");
		verifierBudgets(resultat, budgets);
		System.out.println("Utilite totale : " + utiliteTotale);
	}
	
	/**
	 * Test 2 : Test avec des budgets encore plus serrés
	 */
	private static void test2_SacAvecBudgetsSerres() {
		System.out.println("--- TEST 2 : Sac avec budgets tres serres ---");
		
		// Création d'objets
		Objet o1 = new Objet(20, new int[]{8, 10});
		Objet o2 = new Objet(15, new int[]{5, 7});
		Objet o3 = new Objet(12, new int[]{4, 6});
		Objet o4 = new Objet(18, new int[]{6, 8});
		Objet o5 = new Objet(10, new int[]{3, 4});
		
		List<Objet> objets = new ArrayList<>();
		objets.add(o1);
		objets.add(o2);
		objets.add(o3);
		objets.add(o4);
		objets.add(o5);
		
		// Budgets très serrés
		int[] budgets = {15, 20};
		SacADos sac = new SacADos(2, budgets, objets);
		
		System.out.println("\nSac à dos initial :");
		sac.afficherSacADos();
		
		System.out.println("\n--- Analyse initiale ---");
		afficherCoutsTotaux(sac);
		
		// Test de la méthode gloutonne de retrait
		System.out.println("\n--- Application de la méthode gloutonne de retrait ---");
		OrdreObjetsRetrait comparateur = new OrdreObjetsRetrait(sac);
		List<Objet> resultat = GloutonRetraitSolver.methodeGloutonneRetrait(sac, comparateur);
		
		// Affichage du résultat
		System.out.println("\n--- Resultat apres retrait ---");
		System.out.println("Nombre d'objets retenus : " + resultat.size());
		System.out.println("\nObjets retenus :");
		int utiliteTotale = 0;
		for (int i = 0; i < resultat.size(); i++) {
			System.out.println("\nObjet " + (i + 1) + " :");
			resultat.get(i).afficherObjet();
			utiliteTotale += resultat.get(i).getUtilite();
		}
		
		// Vérification des budgets
		System.out.println("\n--- Vérification des budgets ---");
		verifierBudgets(resultat, budgets);
		System.out.println("Utilité totale : " + utiliteTotale);
	}
	
	/**
	 * Affiche les coûts totaux par dimension du sac
	 */
	private static void afficherCoutsTotaux(SacADos sac) {
		int dimension = sac.getDimension();
		int[] budgets = sac.getBudgets();
		List<Objet> objets = sac.getObjets();
		
		for (int i = 0; i < dimension; i++) {
			int sommeCouts = 0;
			for (Objet o : objets) {
				sommeCouts += o.getCouts()[i];
			}
			System.out.println("Dimension " + (i + 1) + " :");
			System.out.println("  Budget disponible : " + budgets[i]);
			System.out.println("  Coût total des objets : " + sommeCouts);
			System.out.println("  Dépassement : " + Math.max(0, sommeCouts - budgets[i]));
		}
	}
	
	/**
	 * Vérifie que les budgets sont respectés pour la solution
	 */
	private static void verifierBudgets(List<Objet> objets, int[] budgets) {
		int dimension = budgets.length;
		
		for (int i = 0; i < dimension; i++) {
			int sommeCouts = 0;
			for (Objet o : objets) {
				sommeCouts += o.getCouts()[i];
			}
			System.out.println("Dimension " + (i + 1) + " :");
			System.out.println("  Budget : " + budgets[i]);
			System.out.println("  Coût total : " + sommeCouts);
			if (sommeCouts <= budgets[i]) {
				System.out.println("  ✓ Budget respecté (reste : " + (budgets[i] - sommeCouts) + ")");
			} else {
				System.out.println("  ✗ Budget dépassé de : " + (sommeCouts - budgets[i]));
			}
		}
	}
}

