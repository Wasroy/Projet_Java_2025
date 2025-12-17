package pont;

/**
 * Classe qui modélise les fichiers MPK
 */

import java.util.Arrays;

public class MKP {
    private int n, k, opt;  //on met private pour l'encapsulation
    private int[] utilites;
    private int[][] contraintes; /**matrice de contraintes*/
    private int[] budgets; /**tableau des budgts à respecter*/
    
    /**
     * Constructeur de MPK
     * @param n le nombre d'objets
     * @param k le nombre de budgets
     * @param o la valeur optimale de la solution (0 si inconnue)
     * @param u tableau qui avec l'utilité de chaque objet
     * @param c une matrice avec les contraintes de tous les objets
     * @param b un tableau avec tous les budgets à respecter 
     */
    public MKP(int n, int k, int o, int[] u, int[][] c, int[] b) {
        this.n = n;
        this.k = k;
        this.opt = o;
        this.utilites = u;
        this.contraintes = c;
        this.budgets = b;
    }
    /**
     * getter qui permet d'accéder à la liste des budgets
     * @return la liste des budgets
     */
    public int[] getBudgets(){
        return this.budgets;
    }
    
    /**
     * getter qui permet d'accéder au nombre d'objets
     * @return le nombre d'objets
     */
    public int getn() {
    		return this.n;
    }
    
    /**
     * getter qui permet d'accéder au nombre de budgets 
     * @return le nombre de budgets
     */
    public int getk() {
		return this.k;
    }
    
    /**
     * getter qui permet d'accéder à la liste des optimalités 
     * @return la liste des optimalités 
     */
    public int getopt() {
		return this.opt;
    }
    
    /**
     * getter qui permet d'accéder aux utilites des objets
     * @return le tableau des utilites
     */
    public int[] getUtilites() {
    	return this.utilites;
    }
    
    /**
     * getter qui permet d'accéder à la matrice des contraintes
     * @return la matrice des contraintes (k lignes, n colonnes)
     */
    public int[][] getContraintes() {
    	return this.contraintes;
    }
    
    /**
     * redéfinition de la méthode toString pour afficher les caractéristiques d'un fichier MPK
     */
    @Override
    public String toString(){
        return ("n:"+this.n +'\n'+ "k :"+this.k+'\n'+"optimalite: " + this.opt + '\n' 
            +"utilités: " + Arrays.toString(this.utilites) + '\n' + "contraintes: " + Arrays.deepToString(this.contraintes) + '\n' + "budgets: " + Arrays.toString(this.budgets) 
        );
    }
    //j'ai utilisé deepToString car contraintes est un tableau deux dimensions donc Arrays.toString ne fonctionne pas alors que Arrays.deepToString gère automatiquement les tableaux multidimensionnels

}

//j'ai choisi de créer cette classe pour faciliter ma compréhension et en plus ca me paraissait cohérent dans la mesure où un fichier a des attributs particuliers, ce qui va bien avec le concept de classe 
