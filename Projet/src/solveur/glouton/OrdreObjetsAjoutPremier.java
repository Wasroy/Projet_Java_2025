package solveur.glouton;
import sacADos.Objet;
import java.util.Comparator;
import java.lang.Exception;

public class OrdreObjetsAjoutPremier implements Comparator<Objet> {
	
	public static int sommeCouts(Objet o1){ //refait propre mais on aurait pu utiliser la fct getCoutTotal() de la classe Objet
		int somme = 0;
		for (int i = 0; i < o1.getCouts().length; i++){
			somme += o1.getCouts()[i];
		}
		return somme;
	}

	@Override
	public int compare(Objet o1, Objet o2){ //on compare en coef du ratio utilite/cout total
		try{
			double f1 = (double) o1.getUtilite()/(sommeCouts(o1));
		}
		catch(ArithmeticException e){
			System.out.println("Division par 0 impossible.");
		}
		try{
			double f2 = (double) o2.getUtilite()/(sommeCouts(o2));
		}
		catch(ArithmeticException e){
			System.out.println("Division par 0 impossible.");
		}

		if (f1>f2)
			return -1;
		else if (f1 == f2)
			return 0;
		else
			return 1;
	}
}
