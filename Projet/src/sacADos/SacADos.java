package sacADos;
import java.util.List;
import sacADos.Objet;
import java.lang.Exception;

/** SacADos permet de creer un sac a dos qui possede une dimension, une liste d'objets et une liste de budgets
@author Nathalie Habib
*/

public class SacADos{ //peut etre rajouter final ?
	private int dimension;
	private int[] budgets;
	private List<Objet> objets;

/** constructeur du sac a dos
* @param dimension est la dimension du sac, la dimension correspond au nombre de couts d'un objet (cout social, economique etc)
* @param budgets correspond a la liste des budgets du sac
* @param objets correspond a la liste des objets du sac
* @throws si la dimension est negative ou si un budget de la liste est negatif
*/
	public SacADos(int dimension, int[] budgets, List<Objet> objets){
		if (dimension < 0){
			throw new IllegalArgumentException("La dimension doit etre positive.");
		}
		this.dimension = dimension;
		for (int i = 0; i < budgets.length; i++){
			if (budgets[i] < 0){
				throw new IllegalArgumentException("Tous les budgets doivent etre positifs.");
			}
		}
		this.budgets = budgets;
		this.objets = objets;
	}

/** getter permet de recuperer les objets du sac dans les autres fichiers
*/
	public List<Objet> getObjets(){
		return objets;
	}
/** getter permet de recuperer la dimension du sac dans les autres fichiers
*/
	public int getDimension(){
		return dimension;
	}
/** getter permet de recuperer les budgets du sac dans les autres fichiers
*/
	public int[] getBudgets(){
		return budgets;
	}

/** afficher le contenu du sac a dos (dimension, liste des budgets et liste des objets)
*/

	public void afficherSacADos(){
		System.out.println("Sac : ");
		System.out.println("La dimension est le nombre de couts que possedent les objets.");
		System.out.println("Dimension : " + this.dimension);
		for (int i = 1; i <= this.budgets.length; i++){
			System.out.println("Budget " + i + " = " + budgets[i-1]);
		}
		int numero = 1;
		for (Objet o: this.objets){
			System.out.println("\nObjet " + numero + " : ");
			o.afficherObjet();
			numero++;
		}
	}
}
