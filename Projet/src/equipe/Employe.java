/** Caractérisent les employés de la mairie
 */

package equipe;

public abstract class Employe { 
	private String Nom; /**Nom de l'employé */
	private String Prenom; /** Prénom de l'employé */
	private int Age; /** Âge (en années) de l'employé */
	
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
	
	/**
	 * getter pour accéder au nom de l'employé
	 * @return le nom de l'employé
	 */
	public String getNom() {
		return this.Nom;
	}
	/**
	 * getter pour accéder au prénom de l'employé
	 * @return le prénom de l'employé
	 */
	public String getPrenom() {
		return this.Prenom;
	}
	
	/**
	 * getter pour accéder à l'âge de l'employé
	 * @return l'âge de l'employé 
	 */
	public int getAge() {
		return this.Age;
	}

}


/*j'ai choisi une classe abstraite car pas de réalité physique dans la mesure où chaque employé 
 * a une fonction plus précise (qui sera implémentée aussi)
*/
