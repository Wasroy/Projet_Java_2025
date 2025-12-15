package solveur.hill_climbing;
import sacADos.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections; //pour trier 



public class SolutionSacADos() {

    private SacADos sac;
    private boolean[] selection;

    public SolutionSacADos(SacADos sac, boolean[] selection){
        this.sac=sac;
        this.selection = selection.clone(); //clone par sécurité comme d'hab
    }

    //getter
    public List<Objet> getSolutionsSacADos(){
        List<Objet> objetsDansSolu = new ArrayList<>();
        List<Objet> objetsDuSac = sac.getObjets();

        for (int i=0;i<selection.length;i++){
            
            if (selection[i]==true){
                objetsDansSolu.add(objetsDuSac.get(i));
            }

        }

        return objetsDansSolu;

    }


    //les métthodes :
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


    public int[] couts(){
        int dimension = sac.getDimension();
        int[] totalCouts = new int[dimension]; //besoin d'une liste car en effet il y a plusieurs couts distincts
        List<Objet> objetsDuSac = sac.getObjets();

        for (int i=0; i<selection.length;i++){

            if (selection[i]=true){
                Objet x = objetsDuSac.get(i);
                int[] coutsObj = x.getCouts();
                for (int d=0; d<dimension; d++){
                    totalCouts[d]+=coutsObj[d];
                }

            }

        }

        return totalCouts;


    }


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

    //copieur qui serviront dans les voisins

    public SolutionSacADos copier() {
        return new SolutionSacADos(sac,selection);
    }

    public boolean[] getSelection(){
        return selection.clone();
    }


}
