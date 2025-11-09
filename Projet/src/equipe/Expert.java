package equipe;

public class Expert extends Employe {
	Secteur[] Secteurs; //liste des secteurs de l'expert
	Expert(String Nom, String Prenom, int Age, Secteur[] s){
		super(Nom, Prenom, Age);
		Secteurs=s;
	}

}
