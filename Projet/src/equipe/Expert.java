package equipe;
import java.util.Random;

public class Expert extends Employe {
	Secteur[] secteurs; //liste des secteurs de l'expert
	private Random rand = new Random();

	Expert(String Nom, String Prenom, int Age, Secteur[] s){
		super(Nom, Prenom, Age);
		secteurs=s;
	}
	
	public Projet proposerProjet() {
		Secteur s = secteurs[rand.nextInt(secteurs.length)];
		String titre = "Projet " + s + " n°" + rand.nextInt(1000);
		String description = "Projet dans le secteur " + s;
		float benefice=0f; //sera maj plus tard 
		return new Projet(titre, description, s, benefice);
	}
	
}
