package solveur.hill_climbing;
import java.util.ArrayList;
import java.util.List;
import sacADos.*;

/**
 * classe qui represente une solution pour le hill climbing
 * le but de hillclimbing est de partir d'une solution possible et de regarder si on peut changer 
 * certains objets, changer des choses proches de cette solution afin de l'améliorer
 * généralement on test les par exemples 10-100 solutions proche de celle de base et voir s'il y en a une meilleure
 * permet globalement d'optimiser potentiellement une solution de base et pour faire ça on va 
 * utiliser un tableau de boolean pour faire bouger la selection des objets/projets qu'on va garder
 *  l'utilisation d'un tableau facilite la manipulation des objets
 */


public class SolutionHillClimbing {

    /**Constructeur
    
    *@param sac le sac a dos contennt les objs dispo
    *@param selection tablleau de bools true si objets garde et false sinon
    *@throws IllegalArgumentExcepetion si sac ou selection est incohérent

    */

    private SacADos sac;
    private boolean[] selection; //dans hillclimbing on aura un tableau de boolean pour représenter les projets qu'on prends ou non

    public SolutionHillClimbing(SacADos sac, boolean[] selection){

        //gestion d'erreurs comme un pro
        if (sac == null) {
            throw new IllegalArgumentException("Le sac ne peut pas être null");
        }
        if (selection == null) {
            throw new IllegalArgumentException("La sélection ne peut pas être null");
        }
        if (selection.length != sac.getObjets().size()) {
            throw new IllegalArgumentException("La taille de la sélection doit correspondre au nombre d'objets");
        }



        this.sac = sac;
        this.selection = selection.clone();

    }



    /**
     * Getter
     * @return Liste des objets selectionnes    
    
    */
    public List<Objet> getObjetsSelectionnes(){
        List<Objet> objetsSelec = new ArrayList<>();
        List<Objet> objetsDuSac = sac.getObjets();

        for (int i=0;i<selection.length;i++){
            
            if (selection[i]==true){
                objetsSelec.add(objetsDuSac.get(i));
            }

        }

        return objetsSelec;

    }


    //les métthodes :

    /**
     * @return la somme des utilites pour cette solution
     * c'est ce qu'on va chercher a maximiser
     */
    public int utilite(){ 

        int total = 0;
        List<Objet> objetsDuSac = sac.getObjets();
        
        for (int i=0;i<selection.length;i++){
            
            if (selection[i]==true){
                total+=objetsDuSac.get(i).getUtilite(); //recup graces aux méthodes du sac et des obj leurs utilite
            }
        }

        return total;
    }

    /**
     * @return un tableau avec tous les couts (des divers dimensions) de cette solutions 
    
    */
    public int[] couts(){
        int dimension = sac.getDimension();
        int[] totalCouts = new int[dimension]; //besoin d'une liste car en effet il y a plusieurs couts distincts
        List<Objet> objetsDuSac = sac.getObjets();

        for (int i=0; i<selection.length;i++){

            if (selection[i]==true){
                Objet x = objetsDuSac.get(i);
                int[] coutsObj = x.getCouts();
                for (int d=0; d<dimension; d++){
                    totalCouts[d]+=coutsObj[d];
                }

            }

        }

        return totalCouts;


    }

    /**
     * La méthode primordiale
     * @return true si la solution est plausible ou false sinon
     */
    public boolean estValide(){ //verfie si les bugets sont respect

        int[] totalCouts = this.couts();
        int[] budgets = sac.getBudgets();
        int dimension = sac.getDimension();

        for (int d=0; d<dimension; d++){
            if (totalCouts[d]>budgets[d]){
                return false ; //on depasse au moins 1 couts et avec return dès qu'un cout est dépassé on arrete (on verif pas tout on gagne en complexite) 
            }

        }

        return true;

    }




    /** GETTER
     * utile pour l'algo afin de récuperer le sac lié à la solution
     * @return le sac de la solu
     */

    public SacADos getSac(){
        return sac;
    }

    //note : on a pas besoin d'un getter de la taille de la solution car .length du module Array fait l'affaire

    /**COPIEUR
     * qui servira dans les voisins quand on va les générer
     *@return créer une copie de la solution très utile pour les voisins car on ne voudra pas direct manipuler la solution de base
    */

    public SolutionHillClimbing copier() {
        return new SolutionHillClimbing(sac,selection);
    }


    /**
     * on va cloner pour eviter de manpipuler la vrai selection
     * @return le tableau booléans des objets selectionnes
     */
    //sert à afficher la selection des objets et surtout ne pas exposer direct le vrai tableau : bonne pratique de l'encapsulation
    public boolean[] getSelection(){
        return selection.clone(); 
    }


}
