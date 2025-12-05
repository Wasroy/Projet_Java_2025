/**
 * Un projet est un type de texte administrif 
 * @author Romane FAYON
 */

package equipe;
import java.util.*;

public class Projet {
	private final String titre; //le titre ne pourra pas changé sinon risque de confusion à la mairie donc final /** Nom du projet */
	private String description="";  /** décrit le projet (but, cause, conséquences...) */
	private final Secteur secteur; //le secteur ne pourra pas changé donc final /** secteur du projet i.e quel domaine il concerne */
	private int benefice=0;   /** bénéfice du projet */
	Map<Specialisation, Integer> cout; //je cree un dico pour associer chaque specialisation à leur cout /** relie chaque secteur à son cout */
	

	/**
	 * Constructeur d'un projet
	 * @param t Titre du projet 
	 * @param d Descrisption du projet 
	 * @param s Secteur du projet 
	 * @param b Bénéfice du projet
	 */
	public Projet(String t, String d, Secteur s, int b) { 
        this.titre = t;
        this.description = d;
        this.secteur = s;
        this.benefice = b;
        this.cout = new HashMap<>();
        this.cout.put(Specialisation.ECONOMIE,0); //0f pour indiquer que c'est un float sinon par default c'est un int
        this.cout.put(Specialisation.SOCIAL, 0);
        this.cout.put(Specialisation.ENVIRONNEMENT, 0);
    }
	
	//getters et setters 
	/**
	 * Setter qui permet de fixer le cout du projet (dépend de la spécialité)
	 * @param specialite Domaine évalué
	 * @param valeur Coût évalué du projet dans ce domaine
	 */
    public void setCout(Specialisation specialite, int valeur) {
        this.cout.put(specialite, valeur);
    }
    
	/**
	 * Getter qui permet d'accéder à la description d'un projet 
	 * @return Description du projet
	 */
    public String getDescription() {
        return description;
    }

	/**
	 * Getter qui permet d'accéder au secteur du projet 
	 * @return Secteur du projet 
	 */
	public Secteur getSecteur() {
		return secteur;
	}

	/**
	 * Getter qui permet d'accéder au titre du projet 
	 * @return Titre du projet
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * Getter qui permet d'accéder au cout du projet dans un domaine
	 * @param s Domaine en question
	 * @return Coût dans le domaine
	 */
	public int getCout(Specialisation s) {
		return cout.get(s);
	}

	/**
	 * Getter qui permet d'accéder au bénéfice du projet
	 * @return Bénéfice du projet 
	 */
	public int getBenefice() {
		return benefice;
	}

	/**
	 * Setter qui permet de modifier le bénéfice du projet après évaluation
	 * @param b Bénéfice évalué 
	 */
	public void setBenefice(int b) {
		this.benefice=b;
	}


	@Override //je redefinis la méthode toString de Object pour l'afficher
	/**
	 * Redéfinitin de la méthode toString pour afficher un projet 
	 * @return Affichage avec les caractéristiques du projet 
	 */
	public String toString() {
	    return "Projet : " + titre +
	           "\n  Description : " + description +
	           "\n  Secteur : " + secteur +
	           "\n  Benefice : " + benefice +
	           "\n  Couts : " + cout + "\n";
	}
}
