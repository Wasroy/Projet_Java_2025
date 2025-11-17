package solveur.glouton;
import sacADos.*;
import java.util.Comparator;

public class OrdreObjetsRetrait implements Comparator<Objet> {
	//le L de l'énoncé:

	public static int maxCoutObjetDimension(Objet o){
		
		int maxDepassementBudget = 0;
		int[] dimensionsMaxDepassementBudget = new int[sac1.dimension];
		int indiceCourant = 0;
		
		for (int i = 0; i < sac1.dimension; i++){
			int sommeCouts = 0;
			int depassementBudget = 0;
			for (Objet o: sac1.objets){
				sommeCouts += o.couts[i];
			}
			depassementBudget = sommeCouts - sac1.budgets[i];
			if (depassementBudget >= maxDepassementBudget){
				maxDepassementBudget = depassementBudget;
				dimensionMaxDepassementBudget[indiceCourant] = i+1;
				indiceCourant++;
			}
		}

		
		int maxCout;
		for (int i = 0; i < dimensionsMaxDepassementBudget.length; i++){
			if (dimensionsMaxDepassementBudget[i] != 0 && o.couts[dimensionsMaxDepassementBudget[i]] >= maxCout){
				maxCout = o.couts[dimensionsMaxDepassementBudget[i]];
			}
		}
		return maxCout;
	}

	@Override
	public int compare(Objet o1, Objet o2){
		double f1 = (double) o1.utilite/(maxCoutObjetDimension(o1));
		double f2 = (double) o2.utilite/(maxCoutObjetDimension(o2));

		if (f1>f2)
			return -1;
		else if (f1 == f2)
			return 0;
		else
			return 1;
	}
}