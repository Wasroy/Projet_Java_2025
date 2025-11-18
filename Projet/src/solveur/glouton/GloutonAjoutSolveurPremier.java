package solveur.glouton;
import sacADos.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class GloutonAjoutSolver{
	
	public static List<Objet> methodeGloutonneAjoutPremier(SacADos sac, Comparator<Objet> comp) {
		
		//on récupère les objets du sac avec le getter : on les copie pour pas changer directement les vrais valeurs (par sécurité)
		List<Objet> listedesobjets = new ArrayList<>(sac.getObjets());

		//idem on clone le tableau des budgets
		int[] budgetsdusac = sac.getBudgets().clone();

		//on applique le comparateur qu'on a précédemment crée dans OrdreObjetsPremier pour trier la liste selon l'ordre désiré
		Collections.sort(listedesobjets, comp);

		List<Objet> resultat = new ArrayList<>(); //on initialise une liste vide pour les objets selectionnés

		for (Objet o : listedesobjets) {

			boolean selectionne = true; //on part du principe que l'objet peut ete ajouté et on va verifie si l'inverse n'est pas possible (ce qui va nous faire gagner de la complexité) : dès qu'un cout n'est pas possible on break et direct passe a l'objet suivant
			
			for (int i = 0; i < o.getCouts().length; i++) { 
				if (o.getCouts()[i] > budgetsdusac[i]) { //si un des couts de l'objet dépasse le budget du sac
					selectionne = false;
					break; 
				}
			}

			if (selectionne==true) {
				resultat.add(o); //on ajoute l'objet a la liste final
				for (int j = 0; j < o.getCouts().length; j++) { 
					budgetsdusac[j] -= o.getCouts()[j]; //on soustrait chacun des couts de l'objet au budget du sac
				}	
			}
		} 
		return resultat; //on retourne la liste des objets selectionnes
	}
}
