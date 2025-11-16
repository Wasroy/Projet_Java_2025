package equipe;
import java.util.*;

public class Projet {
	private String titre;
	private String description="";
	private Secteur secteur;
	private float benefice=0;
	Map<String, Float> cout;
	
	public Projet(String t, String d, Secteur s, float b) { //constructeur
        this.titre = t;
        this.description = d;
        this.secteur = s;
        this.benefice = b;
        this.cout = new HashMap<>();
        this.cout.put("ECONOMIE", 0f); //0f pour indiquer que c'est un float sinon par default c'est un int
        this.cout.put("SOCIAL", 0f);
        this.cout.put("ENVIRONNEMENT", 0f);
    }
	
	//getters et setters 
    public void setCout(String specialite, float valeur) {
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

	public float getCout(String s) {
		return cout.get(s);
	}

	public float getBenefice() {
		return benefice;
	}

	public void setBenefice(float b) {
		this.benefice=b;
	}
	@Override
	public String toString() {
	    return "Projet : " + titre +
	           "\n  Description : " + description +
	           "\n  Secteur : " + secteur +
	           "\n  Bénéfice : " + benefice +
	           "\n  Coûts : " + cout + "\n";
	}
}
