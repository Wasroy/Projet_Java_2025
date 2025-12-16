package solveur.glouton;
import sacADos.*;
import java.util.Comparator;
import java.lang.Exception;

/** critere d'ordre methode gloutonne a retrait
* @author William Miserolle Nathalie Habib 
*/

public class OrdreObjetsRetrait implements Comparator<Objet> {
	
	private final SacADos sac;
	
	public OrdreObjetsRetrait(SacADos sac) {
		this.sac = sac;
	}

/** determine la dimension ayant la plus gros depassement de budget
* @return dimensionMaxDepassement la dimension ayant le plus depassement de budget
*/	

	private int trouverDimensionMaxDepassement() {
		int dimensionMaxDepassement = 0;
		int maxDepassementBudget = 0;
		
		for (int i = 0; i < sac.getDimension(); i++) {
			int sommeCouts = 0;
			for (Objet obj : sac.getObjets()) {
				sommeCouts += obj.getCouts()[i];
			}
			int depassementBudget = sommeCouts - sac.getBudgets()[i];
			
			if (depassementBudget > maxDepassementBudget) {
				maxDepassementBudget = depassementBudget;
				dimensionMaxDepassement = i;
			}
		}
		return dimensionMaxDepassement;
	}

/** renvoie le maximum des couts pour la dimension ayant le plus gros depassement de budget
* @param o un objet du sac a dos
* @return o.getCouts()[dimensionProblematique] le cout en question
*/	
	
	private int maxCoutObjetDimension(Objet o) {
		int dimensionProblematique = trouverDimensionMaxDepassement();
		return o.getCouts()[dimensionProblematique];
	}

/** critere de comparaison retrait avec exceptions
* @param o1 premier objet
* @param o2 deuxieme objet
* @return 0, 1 ou -1 selon l'objet le plus interessant trouve
*/
	
	@Override
	public int compare(Objet o1, Objet o2) {
		try{
			double f1 = (double) o1.getUtilite() / maxCoutObjetDimension(o1);
		}
		catch(ArithmeticException e){
			System.out.println("Division par 0 impossible.");
		}
		try{
			double f2 = (double) o2.getUtilite() / maxCoutObjetDimension(o2);
		}
		catch(ArithmeticException e){
			System.out.println("Division par 0 impossible.");
		}

		if (f1 < f2)
			return -1;
		else if (f1 == f2)
			return 0;
		else
			return 1;
	}
}
