package sacADos;

public class Objet{
	public int utilite;
	public int[] couts;

	public Objet(int utilite, int[] couts){
		this.utilite = utilite;
		this.couts = couts;
	}
	
	//constructeur (1 par defaut aussi A VOIR)
	//maybe 1 meth pr changer les objets A VOIR)

	public void afficherObjet(){
		System.out.println("Utilite: " + this.utilite);
		for (int i = 1; i <= this.couts.length; i++){
			System.out.println("Cout " + i + " = " + couts[i-1]);
		}
	}
}