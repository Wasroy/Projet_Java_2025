/**
 * Les évaluateurs sont des types d'employés qui vont évaluer le coût d'un projet dans UN secteur 
 * @author Romane FAYON
 */

package equipe;
import java.util.Random;


public class Evaluateur extends Employe{
	public Specialisation Specialite; /** Spécialité de l'évaluateur(parmi une liste prédéfinie) */
	private Random rand = new Random(); 
    int min = 0;     // 0 € /** Minimum du cout du budget */
    int max = 500000;    // 500 000 € /** Maximum du cout du budget */
	//j'ai donné des bornes pour que ce soit plus réaliste
	

	/**
     * constructeur d'un évaluateur
     * @param nom Nom de l'évaluateur
     * @param prenom Prénom de l'évaluateur
     * @param age Âge de l'évaluateur
     * @param specialite Spécialité parmi une liste prédéfinie
     */
	public Evaluateur(String Nom, String Prenom, int Age, Specialisation s) {	
		super (Nom, Prenom, Age);
		if (s == null) {
            throw new IllegalArgumentException("La spécialisation ne peut pas être nulle");
        }
		if (min >= max) {
            throw new IllegalStateException("Le coût minimum ne peut pas être supérieur au coût maximum");
        }
		Specialite=s;
		
	}
	
	/**
	 * Méthode qui permet à l'évaluateur d'évaluer le cout d'un projet dans sa spécialité
	 * @param p Projet qui va être évalué
	 */
	public void evaluerCout(Projet p) {
		if (p == null) {
            throw new IllegalArgumentException("Le projet évalué ne peut pas être nul");
        }
		int cout=rand.nextInt(min,max); 
		switch (Specialite) { //disjonction de cas sur Specialité, la structure du switch me paraissait adaptée 
	    	case ECONOMIE:
	    		p.setCout(Specialisation.ECONOMIE, cout);
	    		break;
	    	case SOCIAL:
	    		p.setCout(Specialisation.SOCIAL, cout);
	    		break;
	    	case ENVIRONNEMENT:
	    		p.setCout(Specialisation.ENVIRONNEMENT, cout);
	    		break;
		}
		// peut etre plus simple comme ca : p.setCout(specialite, cout)
	}
}
 

//j'ai choisi d'implémenter Evaluateur en classe fille qui hérite de Employé car être évaluateur est un cas particulier d'employé'