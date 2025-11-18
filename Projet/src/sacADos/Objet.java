package sacADos;

public class Objet{
	private int utilite; //amelioration de l'encapsulation en rajoutant private
	private int[] couts;

	public Objet(int utilite, int[] couts){
		this.utilite = utilite;
		this.couts = couts;
	}

	public int getUtilite(){ //amelioration de l'encapsulation en rajoutant fct qui recup les valeurs
		return this.utilite;
	}
	
	public int[] getCouts(){
		return this.couts;
	}

	public int getCoutTotal(){
		int c=0;
		for (int i = 0; i < this.couts.length; i++) {
			c+=this.couts[i];
		}
		return c;
	}

	public void afficherObjet(){
		System.out.println("Utilite: " + this.getUtilite());
		for (int i = 1; i <= this.getCouts().length; i++){
			System.out.println("Cout " + i + " = " + this.getCouts()[i-1]);
		}
		System.out.println("Cout total = " + this.getCoutTotal());
	}
}
