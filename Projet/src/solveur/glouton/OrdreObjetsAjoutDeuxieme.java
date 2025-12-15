package solveur.glouton;
import sacADos.Objet;
import java.util.Comparator;
import java.lang.Exception;

public class OrdreObjetsAjoutDeuxieme implements Comparator<Objet> {
	
	public static int maxCouts(Objet o1){
		int max = o1.getCouts()[0];
		for (int i = 1; i < o1.getCouts().length; i++){
			if (o1.getCouts()[i] > max)
				max = o1.getCouts()[i];
		}
		return max;
	}

	@Override
	public int compare(Objet o1, Objet o2){		
		try{
			double f1 = (double) o1.getUtilite()/(maxCouts(o1));
		}
		catch(ArithmeticException e){
			System.out.println("Division par 0 impossible.");
		}
		try{
			double f2 = (double) o2.getUtilite()/(maxCouts(o2));
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
