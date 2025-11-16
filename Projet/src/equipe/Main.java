package equipe;

import java.util.Arrays;

public class Main {
	public static void main(String[] args){
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
}
