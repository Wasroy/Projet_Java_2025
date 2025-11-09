package equipe;

public class Evaluateur extends Employe{
	private Specialisation Specialite;
	
	public Evaluateur(String Nom, String Prenom, int Age, Specialisation S) {
		super (Nom, Prenom, Age);
		Specialite=S;
	}
	public void evaluerCout(ProjetEnCours p) {
		float cout; //préciser la spécialisation
		if ((p.getDescription()).contains("ambitieux")){
			cout=500000;
		}
		else if ((p.getDescription()).contains("metal")){
			cout=2000;
		}
		else {cout=1000;}
	
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
