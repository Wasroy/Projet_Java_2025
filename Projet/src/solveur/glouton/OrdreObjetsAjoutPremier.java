package solveur.glouton;
import java.util.Comparator;
import sacADos.Objet;

/** premier critere d'ordre pour la methode gloutonne a ajout
*/

public class OrdreObjetsAjoutPremier implements Comparator<Objet> {

/** calcule la somme des couts d'un objet, la fonction getCoutTotal() de la classe objet aurait pu aussi etre utilisee
* @param o1 objet du sac a dos
* @return somme la somme des couts pour l'objet o1
*/
	
	public static int sommeCouts(Objet o1){ //refait propre mais on aurait pu utiliser la fct getCoutTotal() de la classe Objet
		int somme = 0;
		for (int i = 0; i < o1.getCouts().length; i++){
			somme += o1.getCouts()[i];
		}
		return somme;
	}

/** premier critere de comparaison entre les objets (choisir lequel est le plus interessant)
* on utilise le ratio utilite/cout total pour comparer
* @param o1 premier objet a comparer
* @param o2 deuxieme objet a comparer
* @return 0, 1 ou -1 selon l'objet qui est le plus interessant
*/
	
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
