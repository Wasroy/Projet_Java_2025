package equipe;
import java.util.Random;


public class Evaluateur extends Employe{
	private Specialisation Specialite; //
	private Random rand = new Random();
    float min = 0f;     // 10 000 € (j'ai mis des f pour que ca soit interpreté comme des float)
    float max = 500000f;    // 500 000 €
	
	public Evaluateur(String Nom, String Prenom, int Age, Specialisation S) {
		super (Nom, Prenom, Age);
		Specialite=S;
	}
	
	public void evaluerCout(Projet p) {
		float cout=min + rand.nextFloat() * (max - min); //de base le random donnera un resultat entre 0 et 1 c'est pour ca que j'ai fait cette petite manip
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
	}
     
}
 