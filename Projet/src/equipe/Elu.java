
/** Les élus sont un type d'employé qui s'occupent d'évaluer les bénéfices de projets proposés 
 * @author Romane FAYON 
 */

package equipe;
import java.util.Random;

public class Elu extends Employe {
	private Random rand = new Random();
    int min = 0;     // 2 000 €
    int max = 500000;    // 500 000 €
	//j'ai donné des bornes pour que ce soit plus réaliste
	
	public Elu(String Nom, String Prenom, int Age) {
		super(Nom, Prenom, Age);
	}
	
	/**
	 * 	méthode qui permet à l'élu d'évaluer (en euros) le bénéfice d'un projet 
	 * @param p le projet dont le benefice va être évalué
	 * @return le bénéfice du projet 
	 */
	public int EvaluerBenefice(Projet p) {
		int benefice =rand.nextInt(min,max);
		p.setBenefice(benefice);
		return benefice;
	}
}


//j'ai choisi d'implémenter élu sous forme de classe fille qui hérité de employé car être élu est un sous cas d'être employé
