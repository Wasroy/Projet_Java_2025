package sacADos;
import java.util.List;
import sacADos.Objet;
import java.lang.Exception;


public class SacADos{ //peut etre rajouter final ?
	private int dimension;
	private int[] budgets;
	private List<Objet> objets;


	public SacADos(int dimension, int[] budgets, List<Objet> objets){ //dimension est le nombre de cout d'un objet (cout eco, social, environnement..)
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

	//getter pour pouvoir recuperer les objets du sac dans les autres fichiers
	public List<Objet> getObjets(){
		return objets;
	}

	public int getDimension(){
		return dimension;
	}

	public int[] getBudgets(){
		return budgets;
	}

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
