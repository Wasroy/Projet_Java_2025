package equipe;
import java.util.*;

public class Projet {
	private final String titre; //le titre ne pourra pas changé sinon risque de confusion à la mairie donc final
	private String description=""; 
	private final Secteur secteur; //le secteur ne pourra pas changé donc final
	private float benefice=0;
	Map<Specialisation, Float> cout; //je cree un dico pour associer chaque specialisation à leur cout
	
	public Projet(String t, String d, Secteur s, float b) { //constructeur
        this.titre = t;
        this.description = d;
        this.secteur = s;
        this.benefice = b;
        this.cout = new HashMap<>();
        this.cout.put(Specialisation.ECONOMIE,0f); //0f pour indiquer que c'est un float sinon par default c'est un int
        this.cout.put(Specialisation.SOCIAL, 0f);
        this.cout.put(Specialisation.ENVIRONNEMENT, 0f);
    }
	
	//getters et setters 
    public void setCout(Specialisation specialite, float valeur) {
        this.cout.put(specialite, valeur);
    }
    
    public String getDescription() {
        return description;
    }

	public Secteur getSecteur() {
		return secteur;
	}

	public String getTitre() {
		return titre;
	}

	public float getCout(Specialisation s) {
		return cout.get(s);
	}

	public float getBenefice() {
		return benefice;
	}

	public void setBenefice(float b) {
		this.benefice=b;
	}

	@Override //je redefinis la méthode toString de Object pour l'affiche 
	public String toString() {
	    return "Projet : " + titre +
	           "\n  Description : " + description +
	           "\n  Secteur : " + secteur +
	           "\n  Bénéfice : " + benefice +
	           "\n  Coûts : " + cout + "\n";
	}
}
