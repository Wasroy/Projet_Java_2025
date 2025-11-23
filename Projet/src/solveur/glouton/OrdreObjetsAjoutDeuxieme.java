package solveur.glouton;
import sacADos.Objet;
import java.util.Comparator;

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
		double f1 = (double) o1.getUtilite()/(maxCouts(o1));
		double f2 = (double) o2.getUtilite()/(maxCouts(o2));

		if (f1>f2)
			return -1;
		else if (f1 == f2)
			return 0;
		else
			return 1;
	}
}