package equipe;

public class Main {
	public static void main(String[] args){
		Evaluateur E1= new Evaluateur("Robert","Dulac",54,Specialisation.SOCIAL);
		ProjetEnCours p1= new ProjetEnCours("D20", "Deforestation ambitieux", Secteur.SANTE, 500);
		E1.evaluerCout(p1);
		System.out.println(p1.Cout);
	}
}
