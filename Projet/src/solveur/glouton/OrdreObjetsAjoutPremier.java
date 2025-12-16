package solveur.glouton;
import java.util.Comparator;
import sacADos.Objet;

public class OrdreObjetsAjoutPremier implements Comparator<Objet> {
	
	public static int sommeCouts(Objet o1){ //refait propre mais on aurait pu utiliser la fct getCoutTotal() de la classe Objet
		int somme = 0;
		for (int i = 0; i < o1.getCouts().length; i++){
			somme += o1.getCouts()[i];
		}
		return somme;
	}

	@Override
	public int compare(Objet o1, Objet o2){

		int somme1 = sommeCouts(o1);

		int somme2 = sommeCouts(o2);
		
		if (somme1 == 0 || somme2 == 0) {
			//gerer le cas division par 0 : retourner un ordre par défaut
			if (somme1 == 0 && somme2 == 0){
				return 0;
			} 
			if (somme1 == 0) {
				return 1; //o1 ira a la fin
			}
			return -1; 
		}
		
		double f1 = (double) o1.getUtilite() / somme1;

		double f2 = (double) o2.getUtilite() / somme2;

		if (f1 > f2) {
			return -1;
		}
		else if (f1 == f2){
			return 0;
		}
		else{
			return 1;
		}


		
	}
}
