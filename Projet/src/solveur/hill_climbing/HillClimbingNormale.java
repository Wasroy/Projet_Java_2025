package solveur.hill_climbing;
import java.util.ArrayList;
import java.util.List;
import sacADos.*;


public class HillClimbingNormale {

    /**
     * méthode pour résoudre le probème du sac à dos grâce à l'algo hill climbing
     * @param sac comme d'hab
     * @return la liste des objets bools selectionnés ou non
     */
    public List<Objet> resoudre(SacADos sac){
       
        SolutionHillClimbing solution = solutionInitVide(sac);
        
        boolean ameliorationPossible = true;
        
        while (ameliorationPossible==true) {
            
            List<SolutionHillClimbing> listeDesVoisins = genererVoisins(solution);
            
            SolutionHillClimbing meilleur = trouverMeilleurVoisinValide(listeDesVoisins);
            
            //on verif si on peut améliorer la solution
            if (meilleur != null && meilleur.utilite() > solution.utilite()) {

                solution = meilleur; //on a trouvé un meilleur voisin donc on le prends ça ressemble à un algo pour trouver le max d'une liste
            }
            else { //aucun voisin n'est meilleur donc on arrete la boucle du hillclimbing

                ameliorationPossible = false;
            }
        }
        
        return solution.getObjetsSelectionnes();
    }


    /**
     * Crée une solution initiale vide 
     * @param sac Le sac à dos
     * @return une solu avec tous les objets non selectionnes
     */
    private SolutionHillClimbing solutionInitVide(SacADos sac){
        int nbObjets = sac.getObjets().size();
        boolean[] selection = new boolean[nbObjets]; //de base tout sera faux dcp

        return new SolutionHillClimbing(sac, selection);
    }

    /**
     * @param solution la solution dont on veut les voisins
     * @return une liste de solutionsHillClimbing en gros tous les voisins possibles de la solution
     */

    private List<SolutionHillClimbing> genererVoisins(SolutionHillClimbing solution) {

        List<SolutionHillClimbing> listeDesVoisins = new ArrayList<>();
        SacADos sac = solution.getSac();
        
        
        for (int i = 0; i < solution.getSelection().length; i++) {
            
            boolean[] nouvelleSelection = solution.getSelection().clone(); //on copie par sécurité et pour pouvoir la manipuler
            
            //inverser la présence de l'objet dans le sac
            if (nouvelleSelection[i] == true) {

                nouvelleSelection[i] = false;
            }
            else {
                nouvelleSelection[i] = true;
            }
            
            //crée concrètement l'objet du voisin 
            SolutionHillClimbing voisin = new SolutionHillClimbing(sac, nouvelleSelection);
            listeDesVoisins.add(voisin);
        }
        
        return listeDesVoisins;
    }

    /**
     * @param voisins liste des voisins à analyser
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

}
