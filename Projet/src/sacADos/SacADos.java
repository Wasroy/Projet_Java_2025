package sacADos;
import java.util.List;
import sacADos.Objet;


public class SacADos{
	private int dimension;
	private int[] budgets;
	private List<Objet> objets;


	public SacADos(int dimension, int[] budgets, List<Objet> objets){ //dimension c en gros le nb de cout d'un objet par ex 3 (cout eco, social, environnement..)
		this.dimension = dimension;
		this.budgets = budgets;
		this.objets = objets;
	}

	//getter pour pouvoir recup les objets du sac dans les autres fichiers
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
