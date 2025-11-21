package solveur.glouton;
import sacADos.*;
import solveur.glouton.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.Iterator;

public class GloutonRetraitSolver{
	
	public static List<Objet> methodeGloutonneRetrait(SacADos sac, Comparator<Objet> comp) {
		
		List<Objet> listedesobjets = new ArrayList<>(sac.getObjets());
		int[] budgetsdusac = sac.getBudgets().clone();
		
		Collections.sort(listedesobjets, comp);

		Iterator<Objet> objet = listedesobjets.iterator();

		while (objet.hasNext()) {
			Objet o = objet.next();
			

			//calcul de la somme des couts dim par dim et teste si respecte budget
			boolean dimensionRespecteBudget = true;
			for (int i = 0; i < budgetsdusac.length; i++){
				int sommeCoutsParDimension = 0;
				
				for (Objet o1: listedesobjets){
					sommeCoutsParDimension += o1.getCouts()[i];
				}
				if (sommeCoutsParDimension > budgetsdusac[i]){
					dimensionRespecteBudget = false;
					break;
				}				
			}
			if (dimensionRespecteBudget == true){
				//faire une nouvelle liste d'objets contenant tous les objets du sac privé de ceux qu'on a retiré puis la sort avec le critère d'ajout et commmencer la méthode gloutonne ajout à partir de cet objet dans la liste triée ?
			}
			else{
				objet.remove();
			}

		}
		return listedesobjets;
	}
}