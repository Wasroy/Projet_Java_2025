package solveur.glouton;
import sacADos.*;
import java.util.Comparator;

public class OrdreObjetsRetrait implements Comparator<Objet> {
	
	private final SacADos sac;
	
	public OrdreObjetsRetrait(SacADos sac) {
		this.sac = sac;
	}
	
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
	
	private int maxCoutObjetDimension(Objet o) {
		int dimensionProblematique = trouverDimensionMaxDepassement();
		return o.getCouts()[dimensionProblematique];
	}

	@Override
	public int compare(Objet o1, Objet o2) {
		double f1 = (double) o1.getUtilite() / maxCoutObjetDimension(o1);
		double f2 = (double) o2.getUtilite() / maxCoutObjetDimension(o2);

		if (f1 < f2)
			return -1;
		else if (f1 == f2)
			return 0;
		else
			return 1;
	}
}