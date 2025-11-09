package equipe;
import java.util.HashMap;
import java.util.Map;

public class ProjetEnCours {
	private String Titre;
	private String Description="";
	private Secteur Sect;
	private float Benefice=0;
	Map<String, Float> Cout;
	
	public ProjetEnCours(String t, String d, Secteur s, float b) { //constructeur
        this.Titre = t;
        this.Description = d;
        this.Sect = s;
        this.Benefice = b;
        this.Cout = new HashMap<>();
        this.Cout.put("ECONOMIE", 0f); //0f pour indiquer que c'est un float sinon par default c'est un int
        this.Cout.put("SOCIAL", 0f);
        this.Cout.put("ENVIRONNEMENT", 0f);
    }
	
    public void setCout(String specialite, float valeur) {
        this.Cout.put(specialite, valeur);
    }
    
    public String getDescription() {
        return Description;
    }
}
