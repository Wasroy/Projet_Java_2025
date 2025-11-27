package app;
//on importe tous les elements du projet afin d'avoir un meme fichier pouvant executer tous les tests
import java.util.ArrayList;
import java.util.List;
import equipe.EquipeDemo;
import sacADos.Objet;
import sacADos.SacADos;
import solveur.glouton.GloutonAjoutSolver;
import solveur.glouton.OrdreObjetsAjoutDeuxieme;
import solveur.glouton.OrdreObjetsAjoutPremier;

public class App {
	
	public static void main(String[] args) {
		//test du sac à dos avec methode gloutonne
		testSacADos();
		
		//test de la partie equipe municipale
		EquipeDemo.runDemo();
	}
	
	private static void testSacADos() {

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
		
		int[] budgets = {10, 12};
		SacADos sac = new SacADos(2, budgets, objets);
		
		sac.afficherSacADos();
		
		//test avec le premier critère
		List<Objet> resultat1 = GloutonAjoutSolver.methodeGloutonneAjout(sac, new OrdreObjetsAjoutPremier());
		
		System.out.println("--------------------------------");
		System.out.println("Resultat methode gloutonne (1er critere) :");
		for (Objet o : resultat1) {
			o.afficherObjet();
		}
		
		//test avec le deuxième critère
		List<Objet> resultat2 = GloutonAjoutSolver.methodeGloutonneAjout(sac, new OrdreObjetsAjoutDeuxieme());
		
		System.out.println("--------------------------------");
		System.out.println("Resultat methode gloutonne (2eme critere) :");
		for (Objet o : resultat2) {
			o.afficherObjet();
		}
	}
}

