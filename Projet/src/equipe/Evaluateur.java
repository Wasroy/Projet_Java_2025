package equipe;
import java.util.Random;


public class Evaluateur extends Employe{
	private Specialisation Specialite;
	private Random rand = new Random();
    float min = 0f;     // 10 000 €
    float max = 500000f;    // 500 000 €
	
	public Evaluateur(String Nom, String Prenom, int Age, Specialisation S) {
		super (Nom, Prenom, Age);
		Specialite=S;
	}
	
	public void evaluerCout(Projet p) {
		float cout=min + rand.nextFloat() * (max - min);
		switch (Specialite) {
	    	case ECONOMIE:
	    		p.setCout("ECONOMIE", cout);
	    		break;
	    	case SOCIAL:
	    		p.setCout("SOCIAL", cout);
	    		break;
	    	case ENVIRONNEMENT:
	    		p.setCout("ENVIRONNEMENT", cout);
	    		break;
		}
	}
     
}
