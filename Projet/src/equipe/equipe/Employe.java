package equipe;

//classe abstraite car pas de réalité physique 

public abstract class Employe { 
	String Nom;
	String Prenom;
	int Age;
	
	public Employe(String n, String p, int a){
		Nom=n;
		Prenom=p;
		Age=a;
	}

}
