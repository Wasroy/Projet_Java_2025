package sacADos;

/**
 *classe qui represente un objet du probleme du sac a dos multidimensionnel
 * Un objet a une utilite (ce qu'on cherche a maximiser) et plusieurs couts (contraintes)
 */
public class Objet{
	
	private int utilite; //amelioration de l'encapsulation en rajoutant private
	private int[] couts; //tableau des couts pour chaque dimension du probleme

	/**
	 * CONSTRUCTEUR
	 * @param utilite la valeur d'utilite de l'objet
	 * @param couts le tableau des couts pour chaque dimension (tous doivent etre positifs)
	 * @throws IllegalArgumentException si l'utilite ou un des couts est negatif
	 */
	public Objet(int utilite, int[] couts){
		if (utilite < 0){

			throw new IllegalArgumentException("L'utilite doit etre positive.");

		}
		this.utilite = utilite;

		for (int i = 0; i < couts.length; i++){

			if (couts[i] < 0){

				throw new IllegalArgumentException("tous les couts doivent etre positifs");
			}
		}
		this.couts = couts;
	}

	/**
	 * GETTER pour recuperer l'utilite de l'objet
	 * @return l'utilite de l'objet
	 */
	public int getUtilite(){ //amelioration de l'encapsulation en rajoutant fct qui recuperer les valeurs
		return this.utilite;
	}
	
	/**
	 * GETTER pour recuperer le tableau des couts
	 * @return le tableau des couts pour chaque dimension
	 */
	public int[] getCouts(){

		return this.couts;

	}


	/**
	 * GETTER & calcule la somme de tous les couts de l'objet
	 * tres utile pour certains criteres de tri des objets
	 * @return la somme de tous les couts
	 */
	public int getCoutTotal(){
		int c=0;

		for (int i = 0; i < this.couts.length; i++) {

			c+=this.couts[i];
		}

		return c;
	}

	/**
	 * affiche les caracteristiques de l'objet
	 * affiche l'utilite, chaque cout individuellement et le cout total
	 */
	public void afficherObjet(){
		System.out.println("Utilite: " + this.getUtilite());

		for (int i = 1; i <= this.getCouts().length; i++){

			System.out.println("Cout " + i + " = " + this.getCouts()[i-1]);

		}

		System.out.println("Cout total = " + this.getCoutTotal());

	}

	
}
