/** Caractérisent les employés de la mairie
 * @author Romane FAYON
 */

package equipe;

public abstract class Employe { 
	String Nom; /**Nom de l'employé */
	String Prenom; /** Prénom de l'employé */
	int Age; /** Âge (en années) de l'employé */
	
	/**
	 * Constructeur d'un employé
	 * @param n Nom de l'employé
	 * @param p Prénom de l'employé
	 * @param a Âge de l'employé
	 */
	public Employe(String n, String p, int a){
		Nom=n;
		Prenom=p;
		Age=a;
	}

}


/*j'ai choisi une classe abstraite car pas de réalité physique dans la mesure où chaque employé 
 * a une fonction plus précise (qui sera implémentée aussi)
*/
