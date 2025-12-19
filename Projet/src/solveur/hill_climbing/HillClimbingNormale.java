package solveur.hill_climbing;
import java.util.ArrayList;
import java.util.List;
import sacADos.*;
import solveur.Solveur;

/**
 * classe qui implemente l'algo de hill climbing classique pour resoudre le sac a dos
 * on part d'une solution vide et on explore tous les voisins pour trouver une meilleure solution
 * on arrete quand aucun voisin n'est meilleur (optimum local atteint)
 */

public class HillClimbingNormale implements Solveur {
	private boolean[] selectionInitiale = null;

    /**
     * méthode pour résoudre le probème du sac à dos grâce à l'algo hill climbing
     * @param sac comme d'hab
     * @return la liste des objets bools selectionnés ou non
     */
    public List<Objet> resoudre(SacADos sac){
       
	    	SolutionHillClimbing solution =
	    		    (selectionInitiale != null)
	    		        ? new SolutionHillClimbing(sac, selectionInitiale.clone())
	    		        : solutionInitVide(sac);
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

    private int t = 1; //nombre max d'objets qu'on peut enlever et ajouter selon les consignes
    
    /**
     * @param solution la solution dont on veut les voisins
     * @return une liste de solutionsHillClimbing en gros tous les voisins possibles de la solution
     * en suivant bien la formule S' = (S \ E) ∪ A avec |E| <= t et |A| <= t
     */

    private List<SolutionHillClimbing> genererVoisins(SolutionHillClimbing solution) {

        List<SolutionHillClimbing> listeDesVoisins = new ArrayList<>();
        SacADos sac = solution.getSac();
        boolean[] selectionActuelle = solution.getSelection();
        int nbObjets = selectionActuelle.length;
        
        //on genere tous les voisins possibles en retirant entre 0 et t objets ETT en ajoutant entre 0 et t objets
        //on fait avec t=1 d'abord pour pas exploser en complexite, mais on peut mettre t=2 aussi (comme demandé)
        
        for (int nbRetrait=0; nbRetrait<=t; nbRetrait++) {

            for (int nbAjout=0; nbAjout<=t; nbAjout++) {
                
                //si on retire et ajoute 0 objets ça change rien
                if (nbRetrait == 0 && nbAjout == 0) {
                    continue;
                }
                
                //on genere toutes les combinaisons de retrait de nbRetrait objets
                List<List<Integer>> combinaisonsRetrait = genererCombinaisons(nbObjets, nbRetrait);
                

                //on genere toutes les combinaisons d'ajout de nbAjout objets
                List<List<Integer>> combinaisonsAjout = genererCombinaisons(nbObjets, nbAjout);
                
                for (List<Integer> indicesRetrait : combinaisonsRetrait) {
                    
                    //on verif que les indices a retirer sont bien dans la selection actuelle
                    boolean peutRetirer = true;

                    for (Integer indice : indicesRetrait) {

                        if (selectionActuelle[indice] == false) {
                            peutRetirer = false;

                            break;

                        }
                        
                    }
                    
                    if (peutRetirer == false) {
                        continue; //on peut pas retirer des objets qui sont pas deja dans le sac

                    }
                    
                    //pour chaque combinaison d'ajout

                    for (List<Integer> indicesAjout : combinaisonsAjout) {
                        
                        //on verif que les indices a ajouter ne sont pas deja dans la selection

                        boolean peutAjouter = true;

                        for (Integer indice : indicesAjout) {

                            if (selectionActuelle[indice] == true) {

                                peutAjouter = false;
                                break;

                            }

                        }
                        
                        //on verif aussi qu'on retire pas et ajoute pas le meme objet
                        boolean pasDeDoublon = true;

                        for (Integer indiceRet : indicesRetrait) {

                            for (Integer indiceAj : indicesAjout) {

                                if (indiceRet.equals(indiceAj)) {

                                    pasDeDoublon = false;

                                    break;

                                }

                            }


                            if (pasDeDoublon == false) { //plutot explicit
                                break;
                            }


                        }
                        
                        if (peutAjouter == false || pasDeDoublon == false) {
                            continue;
                        }
                        
                        //on avance dans les solutions
                        boolean[] nouvelleSelection = selectionActuelle.clone();
                        
                        //on retire les objets de E
                        for (Integer indice : indicesRetrait) {
                            nouvelleSelection[indice] = false;
                        }
                        
                        //on ajoute les objets de A

                        for (Integer indice : indicesAjout) {

                            nouvelleSelection[indice] = true;

                        }
                        
                        SolutionHillClimbing voisin = new SolutionHillClimbing(sac, nouvelleSelection);

                        listeDesVoisins.add(voisin);

                    }


                }


            }

        }
        


        return listeDesVoisins;

    }
    
    /**
     * genere toutes les combinaisons de k elements parmi n
     * @param n le nombre total d'elements
     * @param k le nombre d'elements a choisir
     * @return la liste de toutes les combinaisons (chaque combinaison est une liste d'indices)
     */
    private List<List<Integer>> genererCombinaisons(int n, int k) {
        
        List<List<Integer>> resultat = new ArrayList<>();
        
        if (k == 0) {
            //si on veut 0 elements, on retourne une liste avec une liste vide
            resultat.add(new ArrayList<>());
            return resultat;
        }
        

        //on peut pas choisir plus d'elements qu'il y en a
        if (k > n) {

            return resultat;
        }
        
        //on fait recursivement soit on prend le premier element, soit on le prend pas

        //cas 1: on prend le premier element (indice 0)
        if (n > 0 && k > 0) {

            List<List<Integer>> avecPremier = genererCombinaisons(n-1, k-1);

            for (List<Integer> combi : avecPremier) {

                List<Integer> nouvelle = new ArrayList<>();

                nouvelle.add(0); //on ajoute l'indice 0

                for (Integer i : combi) {

                    nouvelle.add(i+1); //on decale les autres indices de +1

                }

                resultat.add(nouvelle);
            }

        }
        
        //cas 2: on prend pas le premier element
        if (n > 0) {
            List<List<Integer>> sansPremier = genererCombinaisons(n-1, k);

            for (List<Integer> combi : sansPremier) {
                
                List<Integer> nouvelle = new ArrayList<>();

                for (Integer i : combi) {

                    nouvelle.add(i+1); //on decale tous les indices de +1

                }

                resultat.add(nouvelle);

            }


        }
        
        return resultat;

    }

    /**
     * @param voisins la liste des voisins à analyser
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
