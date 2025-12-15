/**
 * Les experts sont des employés qui vont proposer des projets
 */

package equipe;
import java.util.Random;

public class Expert extends Employe {
	Secteur[] secteurs; /** Liste des secteurs de l'expert (l'expert n'a pas le droit de proposer des projets qui ne touchent pas ses domaines d'expertise) */
	private final Random rand = new Random(); //j'ai mis final car techniquement ne pourra pas être modifié  

	/**
	 * constructeur d'un expert
	 * @param Nom Nom de l'expert
	 * @param Prenom Prénom de l'expert
	 * @param Age Âge de l'expert
	 * @param s Secteur de l'expert
	 */
	public Expert(String Nom, String Prenom, int Age, Secteur[] s){
		super(Nom, Prenom, Age);
		secteurs=s;
	}
	
	/**
	 * Méthode qui permet à l'expert de proposer un projet 
	 * @return Une idée de projet 
	 */
	public Projet proposerProjet() {
		Secteur s = secteurs[rand.nextInt(secteurs.length)]; //secteur parmi la liste de secteurs de l'expert
		String titre = "Projet " + s + " n°" + rand.nextInt(1000);
		String description = "Projet dans le secteur " + s;
		int benefice=0; //sera maj plus tard 
		return new Projet(titre, description, s, benefice);
	}
	
}

//j'ai choisi d'implémenter Expert en classe fille qui hérite de Employé car être expert est un cas particulier d'employé
