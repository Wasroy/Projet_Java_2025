package sacADos;

import java.util.ArrayList;
import java.util.List;
import solveur.glouton.GloutonAjoutSolver;
import solveur.glouton.OrdreObjetsAjoutPremier;

public class DemoSacGlouton {
    //Objet : valeur d'utilité, couts {1er, 2eme, 3eme, etc.}
    public static void main(String[] args) {
        Objet o1 = new Objet(12, new int[]{3, 5});
        Objet o2 = new Objet(7, new int[]{2, 4});
        Objet o3 = new Objet(10, new int[]{4, 6});
        Objet o4 = new Objet(15, new int[]{5, 7});
        Objet o5 = new Objet(10, new int[]{3, 5});
        
        List<Objet> objets = new ArrayList<>();
        objets.add(o1);
        objets.add(o2);
        objets.add(o3);
        objets.add(o4);
        objets.add(o5);


        int[] budgets = {10, 12};
        SacADos sac = new SacADos(2, budgets, objets);

        sac.afficherSacADos();

        List<Objet> resultatGloutonAjoutPremier = GloutonAjoutSolver.methodeGloutonneAjout(sac, new OrdreObjetsAjoutPremier()); //marche aussi pour le 2e critere avec new OrdreObjetsAjoutDeuxieme()
        
        System.out.println("--------------------------------");
        System.out.println("Liste des objets selectionnes par la methode gloutonne a ajout premier :");
        for (Objet o : resultatGloutonAjoutPremier) {
            o.afficherObjet();
        }
    }
}

