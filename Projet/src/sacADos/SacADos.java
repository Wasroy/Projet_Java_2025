package sacADos;
import java.util.List;
import sacADos.Objet;


public class SacADos{
	private int dimension;
	private int[] budgets;
	private List<Objet> objets;


	public SacADos(int dimension, int[] budgets, List<Objet> objets){
		this.dimension = dimension;
		this.budgets = budgets;
		this.objets = objets;
	}


	public void afficherSacADos(){
		System.out.println("Sac : ");
		System.out.println("Dimension : " + this.dimension);
		for (int i = 1; i <= this.budgets.length; i++){
			System.out.println("Budget " + i + " = " + budgets[i-1]);
		}
		for (Objet o: this.objets){
			System.out.println("\nObjet : ");
			o.afficherObjet();
		}
	}
}
