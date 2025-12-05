package pont;
import equipe.Projet;
import equipe.Specialisation;
import sacADos.Objet;
import sacADos.SacADos;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Integer.parseInt;

/**
 * Permet de transformer une instance de MKP (Multi-dimensional Knapsack Problem) en un sac à dos ou un projet de l'équipe municipale en un objet pour le sac à dos
 * @author Romane FAYON
 */

//j'importe les classes car elles sont dans un autre package


public class VersSacADos {
    //j'ai besoin d'une liste de projets et des budgets de Dauphine City
    //int[] budgets = new int[]{}; //tableau avec budgets pour chaque secteur
    //comment avoir la liste des projets ??
    //Projet[] listeProjets = new Projet[]{};
    /*il faut transformer les projets en des objets valides pour le sac à dos :
     Un Objet possédera comme attributs une valeur int utilite et un tableau int[] couts*/

     /**
      * méthode qui transforme un projet de l'équipe municipale en un objet pour le sac à dos
      * @param p le projet de l'équipe municipale
      * @return un objet pour le sac à dos
      */
    public Objet transformation(Projet p){ //me renvoie un objet qui pourra être mis dans le sac à dos 
        int cout_eco=p.getCout(Specialisation.ECONOMIE);
        int cout_soc=p.getCout(Specialisation.SOCIAL);
        int cout_env=p.getCout(Specialisation.ENVIRONNEMENT);
        int[] couts=new int[]{cout_eco,cout_soc,cout_env};
        int utilite=p.getBenefice();
        Objet o=new Objet(utilite,couts);
        return o;
    }

    /**
     * méthode qui transforme une liste de projets de l'équipe municipale en une liste d'objets pour le sac à dos
     * @param listeProjets la liste de projets de l'équipe municipale
     * @return une liste d'objets pour le sac à dos
     */
    public List<Objet> transformerAll(Projet[] listeProjets){
        List<Objet> objets=new LinkedList<>();
        for (Projet p : listeProjets){
            objets.add(transformation(p));
        }
        return objets;
    }

    /**
     * méthode qui crée un sac à dos à partir d'une liste de projets de l'équipe municipale et des budgets
     * @param listeProjets la liste de projets de l'équipe municipale
     * @param budgets  les budgets pour chaque secteur
     * @return  un sac à dos valide 
     */
    public SacADos creerSAD(Projet[] listeProjets, int[] budgets){
        List<Objet> objets =transformerAll(listeProjets);
        int dimension=3; //j'ai mis 3 mais peut être 1 selon l'interprétation
        SacADos s = new SacADos(dimension, budgets, objets);
        return s;
    }

    /**
     * Méthode qui lit un fichier .dat et crée une instance de MKP
     * @param nomFic  le nom du fichier à lire
     * @return une instance de MKP
     * @throws IOException  en cas d'erreur de lecture du fichier
     */
    public static MKP traitementFic(String nomFic) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(nomFic))) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            //System.out.println(n);
            int k = Integer.parseInt(st.nextToken());
            //System.out.println(k);
            int opt = Integer.parseInt(st.nextToken());
            //System.out.println(opt);
            int[] utilites = new int[n];
            int cpt=0; //j'initialise mon compteur
            //lecture des utilités
            while (cpt<n){
                String line = br.readLine();
                st = new StringTokenizer(line);
                while (st.hasMoreTokens() && cpt < n) {
                    String token=st.nextToken().trim();
                    int t = parseInt(token);
                    utilites[cpt] = t;
                    //System.out.println(utilites[cpt]);
                    cpt++;
                }
            }
            //lecture des contraintes
            int[][] contraintes = new int[k][n];
            for (int c = 0; c < k; c++) {
                cpt = 0;
                while (cpt < n) {
                    String ligne = br.readLine();
                    st = new StringTokenizer(ligne);
                    while (st.hasMoreTokens() && cpt < n) {
                        String token=st.nextToken().trim();
                        int t = parseInt(token);
                        contraintes[c][cpt] = t;
                        //System.out.println(contraintes[c][cpt]);
                        cpt++;
                    }
                }
            }
            //lecture des budgets
            int[] budgets = new int[k];
            cpt=0;
            while (cpt<k){
                String line = br.readLine();
                st = new StringTokenizer(line);
                while (st.hasMoreTokens() && cpt < k) {
                    String token=st.nextToken().trim();
                    int t = parseInt(token);
                    budgets[cpt] = t;
                    //System.out.println(budgets[cpt]);
                    cpt++;
                }
            }

            return new MKP(n, k, opt, utilites, contraintes, budgets);
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * méthode qui transforme une instance de MKP en un sac à dos
     * @param instance l'instance de MKP
     * @return un sac à dos valide
     */
    public static SacADos ficToSac(MKP instance){ 
        int n = instance.n;
        int k = instance.k;
        List<Objet> objets = new LinkedList<>();
        //maintenant je veux créer tous les objets qui iront dans le sac à dos
        for (int i = 0; i < n; i++) {
            int[] couts = new int[k]; //tableau de taille nb de budgets qui est remplie dans la boucle suivante
            for (int l = 0; l < k; l++) { //pour chaque objet
                couts[l] = instance.contraintes[l][i]; //on remplie le tableau avec une boucle pour avoir contraintes sur chacun des k dimensions
            }
            int utilite = instance.utilites[i]; //on a direct l'utilité
            Objet o = new Objet(utilite, couts); // on crée l'objet qui ira dans le sac
            objets.add(o); //on ajoute l'objet au sac
        }
        int dimension = k; 
        int[] budgets = instance.budgets; //on a direct les budgets
        SacADos s = new SacADos(dimension, budgets, objets); //on crée notre sac à dos 
        return s;
    }
    public static void main(String args[]){
        String nomFic="C:/Users/utilisateur/OneDrive - Université Paris Sciences et Lettres/Documents/GitHub/gk/gk01.dat";
        try {
            MKP M=traitementFic(nomFic);
            System.out.println(M.toString());
            SacADos s=ficToSac(M);
            s.afficherSacADos();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
