package solveur.glouton;
import sacADos.Objet;
import java.util.Comparator;

public class OrdreObjetsAjoutPremier implements Comparator<Objet> {
	
	public static int sommeCouts(Objet o1){
		int somme = 0;
		for (int i = 0; i < o1.couts.length; i++){
			somme += o1.couts[i];
		}
		return somme;
	}

	@Override
	public int compare(Objet o1, Objet o2){
		double f1 = (double) o1.utilite/(sommeCouts(o1));
		double f2 = (double) o2.utilite/(sommeCouts(o2));

		if (f1>f2)
			return -1;
		else if (f1 == f2)
			return 0;
		else
			return 1;
	}
}