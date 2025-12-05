package pont;

import java.util.Arrays;

public class MKP {
    int n, k, opt;
    int[] utilites;
    int[][] contraintes; //matrice de contraintes
    int[] budgets;

    public MKP(int n, int k, int o, int[] u, int[][] c, int[] b) {
        this.n = n;
        this.k = k;
        this.opt = o;
        this.utilites = u;
        this.contraintes = c;
        this.budgets = b;
    }

    public int[] getBudgets(){
        return this.budgets;
    }

    @Override
    public String toString(){
        return ("n:"+this.n +'\n'+ "k :"+this.k+'\n'+"optimalite: " + this.opt + '\n' 
            +"utilités: " + Arrays.toString(this.utilites) + '\n' + "contraintes: " + Arrays.deepToString(this.contraintes) + '\n' + "budgets: " + Arrays.toString(this.budgets) 
        );
    }
    //j'ai utilisé deepToString car contraintes est un tableau deux dimensions donc Arrays.toString ne fonctionne pas alors que Arrays.deepToString gère automatiquement les tableaux multidimensionnels

}
