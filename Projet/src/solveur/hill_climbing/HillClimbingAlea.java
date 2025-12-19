package solveur.hill_climbing;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;
import sacADos.*;

/**
 * variante aleatoire du hill climbing pour eviter d'explorer tous les voisins
 * au lieu de regarder tous les voisins on en pioche aleatoirement un certain nombre
 * ca permet de gagner en temps de calcul mais on risque de louper le meilleur voisin
 */

public class HillClimbingAlea {
    
    private Random random;
    private int nombreVoisins; //nombre de voisins qu'on va considerer a chaque iteration
    private boolean[] selectionInitiale = null;

    
    /**CONSTRUCTEUR 
     * pour la variante aleatoire du hill climbing
     * @param nombreVoisins le nb de voisins aleatoires qu'on va considerer
     */
    public HillClimbingAlea(int nombreVoisins) {
        //gestion d'erreur
        if (nombreVoisins <= 0) {
            throw new IllegalArgumentException("le nombre de voisins doit être strictement positif");
        }

        this.nombreVoisins = nombreVoisins;
        this.random = new Random();
    }
    
    /**
     * methode aléatoire dans la generation des solutions
     * @param sac comme d'hab
     * @return la liste des objets bools selectionnés ou non
     */
    public List<Objet> resoudre(SacADos sac) {
	    	SolutionHillClimbing solution =
	    		    (selectionInitiale != null)
	    		        ? new SolutionHillClimbing(sac, selectionInitiale.clone())
	    		        : solutionInitVide(sac);
        int nbObjets = sac.getObjets().size();
        
        boolean ameliorationPossible = true;
        
        while (ameliorationPossible == true) {
            
            //generer seulement un nombre limite de voisins aleatoires (pas tous comme dans la version normale)
            List<SolutionHillClimbing> listeDesVoisins = genererVoisinsAleatoires(solution, nbObjets);
            
            SolutionHillClimbing meilleur = trouverMeilleurVoisinValide(listeDesVoisins);
            
            //on verif si on peut améliorer la solution
            if (meilleur != null && meilleur.utilite() > solution.utilite()) {

                solution = meilleur; //on a trouvé un meilleur voisin donc on le prends
            }
            else { //aucun voisin n'est meilleur donc on arrete la boucle du hillclimbing

                ameliorationPossible = false;
            }
        }
        
        return solution.getObjetsSelectionnes();
    }
    
    /**
     * crée solu vide
     * @param sac le sac à dos
     * @return une solu avec tous les objets démarré comme non selectionnes
     */
    private SolutionHillClimbing solutionInitVide(SacADos sac) {

        int nbObjets = sac.getObjets().size();
        boolean[] selection = new boolean[nbObjets]; //de base tout sera faux dcp

        return new SolutionHillClimbing(sac, selection);

    }
    
    /**
     * @param solution la solution dont on veut les voisins
     * @param nbObjets le nombre total d'objets
     * @return une liste de solutionsHillClimbing mais seulement un nombre limite choisi aleatoirement
     */
    private List<SolutionHillClimbing> genererVoisinsAleatoires(SolutionHillClimbing solution, int nbObjets) {
        
        List<SolutionHillClimbing> listeDesVoisins = new ArrayList<>();
        SacADos sac = solution.getSac();
        
        //on cree une liste avec tous les indices possibles
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < nbObjets; i++) {
            indices.add(i);
        }
        
        //on melange aleatoirement cette liste d'indices
        Collections.shuffle(indices, random);
        
        //on prend les x premiers nombreVoisins indices et on genere les voisins correspondants
        int nbVoisinsAGenerer = Math.min(nombreVoisins, nbObjets);
        for (int j = 0; j < nbVoisinsAGenerer; j++) {
            int index = indices.get(j);
            boolean[] nouvelleSelection = solution.getSelection().clone(); //on copie par sécurité et pour pouvoir la manipuler
            
            //inverser la présence de l'objet dans le sac
            if (nouvelleSelection[index] == true) {

                nouvelleSelection[index] = false;
            }
            else {
                nouvelleSelection[index] = true;
            }
            
            //creer concrètement l'objet du voisin 
            SolutionHillClimbing voisin = new SolutionHillClimbing(sac, nouvelleSelection);
            listeDesVoisins.add(voisin);
        }
        
        return listeDesVoisins;
    }
    
    /**
     * @param listeDesVoisins liste des voisins à analyser
     * @return trouve le meilleur voisin valide parmi tous les voisins en gros celui qui a la meilleure utilite 
     * et qui est évidemment Valide/possible par rapport aux couts ou null si aucun ne fonctionne
     */
    private SolutionHillClimbing trouverMeilleurVoisinValide(List<SolutionHillClimbing> listeDesVoisins) {

        SolutionHillClimbing meilleur = null;
        int meilleurUtilite = 0; //on part du principe que les utilites sont toutes positives
        
        for (SolutionHillClimbing v : listeDesVoisins) {
            if (v.estValide()) { //seulement les solutions valides
                int util = v.utilite();
                if (util > meilleurUtilite) {
                    meilleurUtilite = util;
                    meilleur = v;
                }
            }
        }
        
        return meilleur;
    }

    public void setSolutionInitiale(List<Objet> solution, SacADos sac) {

        int nbObjets = sac.getObjets().size();
        boolean[] selection = new boolean[nbObjets];

        for (int i = 0; i < nbObjets; i++) {
            Objet obj = sac.getObjets().get(i);
            if (solution.contains(obj)) {
                selection[i] = true;
            }
        }
        this.selectionInitiale = selection;
    }


}