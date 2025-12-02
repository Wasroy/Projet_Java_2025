package solveur.glouton;
import sacADos.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.Iterator; // va etre utile pour supprimer un element pdnt qu'on parcourt la liste

public class GloutonRetraitSolver{
	
	public static List<Objet> methodeGloutonneRetrait(SacADos sac, Comparator<Objet> comp) {
		
		//Init clonage par secu
		List<Objet> listedesobjets = new ArrayList<>(sac.getObjets()); //par securite on recup clone les objets
		int[] budgetsdusac = sac.getBudgets().clone(); //idem on clone les budgets
		
		Collections.sort(listedesobjets, comp);

		//par soucis d'optimisation on va calculer en avance toutes les sommes pour gagner en complexité (la manière de base serait de recalculer tout a chaque objet)
		int[] sommes = new int[budgetsdusac.length];
		for (Objet obj : listedesobjets) {
			for (int i = 0; i < budgetsdusac.length; i++) {
				sommes[i] += obj.getCouts()[i];
			}
		}

		Iterator<Objet> curseur = listedesobjets.iterator(); //on utilise un iterator car on va modifier la liste pendant qu'on la parcourt d'où le fait qu'on peut pas faire juste un for...
		
		while (curseur.hasNext()) {
			
			// On vérifie si, avec les sommes actuelles, tous les budgets sont respectés
			boolean dimensionRespecteBudget = true;
			for (int i = 0; i < budgetsdusac.length; i++) {
				if (sommes[i] > budgetsdusac[i]) {
					dimensionRespecteBudget = false;
					break;
				}
			}

			// Si les contraintes sont respectées, on peut arrêter le retrait
			if (dimensionRespecteBudget) {
				break;
			}
			else {
				
				Objet courant = curseur.next(); 
				curseur.remove();

				// On met à jour les sommes : on enlève les coûts de l'objet retiré
				for (int i = 0; i < budgetsdusac.length; i++) {
					sommes[i] -= courant.getCouts()[i];
				}

			}

		}

		 SacADos sacFinal = new SacADos(sac.getDimension(), budgetsdusac, listedesobjets);
		 return GloutonAjoutSolver.methodeGloutonneAjout(sacFinal, comp);
	}

	
}