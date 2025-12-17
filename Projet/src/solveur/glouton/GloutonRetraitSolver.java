package solveur.glouton;
import sacADos.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.Iterator; // va etre utile pour supprimer un element pdnt qu'on parcourt la liste

/**
 * classe qui implemente la methode gloutonne a retrait pour resoudre le sac a dos
 * on commence avec tous les objets puis on retire les moins interessants jusqu'a respecter les budgets
 * puis on applique la methode gloutonne a ajout
 */

public class GloutonRetraitSolver{

/** 
 * methode gloutonne a retrait avec 2 comparateurs distincts
 * @param sac le sac a dos qu'on veut resoudre
 * @param compRetrait le critere d'ordre pour le retrait (les moins interessants en premier)
 * @param compAjout le critere d'ordre pour l'ajout final (les plus interessants en premier)
 * @return la liste des objets selectionnés pour la solution
 */
	
	public static List<Objet> methodeGloutonneRetrait(SacADos sac, Comparator<Objet> compRetrait, Comparator<Objet> compAjout) {
		
		//Init clonage par secu
		List<Objet> listedesobjets = new ArrayList<>(sac.getObjets()); //par securite on recup clone les objets
		int[] budgetsdusac = sac.getBudgets().clone(); //idem on clone les budgets
		
		Collections.sort(listedesobjets, compRetrait); //on trie par ordre de retrait (les moins interessants en premier)

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
		 //on applique la methode gloutonne a ajout avec le comparateur d'ajout (pas celui de retrait !)
		 return GloutonAjoutSolver.methodeGloutonneAjout(sacFinal, compAjout);
	}
	
	/** 
	 * version simplifiee qui utilise le meme comparateur pour le retrait et l'ajout
	 * garde pour la compatibilite avec l'ancien code (mais c'est pas l'ideal)
	 * @param sac le sac a dos qu'on veut resoudre
	 * @param comp le critere d'ordre (utilise pour les deux phases)
	 * @return la liste des objets selectionnés pour la solution
	 */
	public static List<Objet> methodeGloutonneRetrait(SacADos sac, Comparator<Objet> comp) {
		//on utilise le premier critere d'ajout par defaut pour la phase d'ajout
		return methodeGloutonneRetrait(sac, comp, new OrdreObjetsAjoutPremier());
	}

	
}
