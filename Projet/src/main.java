import java.util.ArrayList;
import java.util.List;
import solveur.glouton.GloutonAjoutSolver;
import solveur.glouton.OrdreObjetsAjoutPremier;
import solveur.glouton.OrdreObjetsAjoutDeuxieme;
import sacADos.*;

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
		  
		  List<Objet> resultatGloutonAjoutDeuxieme = GloutonAjoutSolver.methodeGloutonneAjout(sac, new OrdreObjetsAjoutDeuxieme()); //marche aussi pour le 2e critere avec new OrdreObjetsAjoutDeuxieme()
		  System.out.println("--------------------------------");
        System.out.println("Liste des objets selectionnes par la methode gloutonne a ajout deuxieme :");
        for (Objet o : resultatGloutonAjoutDeuxieme) {
            o.afficherObjet();
        }

	//création de projets 
			Elu elu = new Elu("Dupont", "Jean", 50);

        Evaluateur evaluEco = new Evaluateur("Bertrand", "Eloi", 40, Specialisation.ECONOMIE);
        Evaluateur evaluSoc = new Evaluateur("Alembert", "Fabien", 32, Specialisation.SOCIAL);
        Evaluateur evaluEnv = new Evaluateur("Gauss", "Erwin", 22, Specialisation.ENVIRONNEMENT);
        Expert exp1 = new Expert("Martin", "Alice", 40, new Secteur[]{Secteur.SPORT,Secteur.SANTE});
        Expert exp2 = new Expert("Bernard", "Luc", 38, new Secteur[]{Secteur.EDUCATION,Secteur.CULTURE});

        EquipeMunicipale equipe = new EquipeMunicipale();

        equipe.setElu(elu);
        equipe.setEvaluateurs(evaluEco, evaluSoc, evaluEnv);

        equipe.setExperts(Arrays.asList(exp1, exp2));

        equipe.Cycle();

        System.out.println("Projets finalisés :");
        for (Projet p : equipe.getProjetsComplets()) {
            System.out.println(p);
        }

    }
