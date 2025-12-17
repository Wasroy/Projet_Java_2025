package equipe;

import java.util.Arrays;

public class EquipeDemo {
	
	public static void runDemo() {
		// creation de l'elu
		Elu elu = new Elu("Dupont", "Jean", 50);
		
		//creation des evaluateurs
		Evaluateur evaluEco = new Evaluateur("Pythagore", "Romane", 40, Specialisation.ECONOMIE);
		Evaluateur evaluSoc = new Evaluateur("Descartes", "Fabien", 32, Specialisation.SOCIAL);
		Evaluateur evaluEnv = new Evaluateur("Gauss", "Nathalie", 22, Specialisation.ENVIRONNEMENT);
		
		//creation des experts
		int n=2;
		Expert exp1 = new Expert("Platon", "Alice", 40, new Secteur[]{Secteur.SPORT, Secteur.SANTE});
		Expert exp2 = new Expert("Aristote", "William", 38, new Secteur[]{Secteur.EDUCATION, Secteur.CULTURE});
		
		//mise en place de l'équipe
		EquipeMunicipale equipe = new EquipeMunicipale();
		equipe.setElu(elu);
		equipe.setEvaluateurs(evaluEco, evaluSoc, evaluEnv);
		equipe.setExperts(Arrays.asList(exp1, exp2));
		
		//lancement du cycle de traitement des projets
		equipe.Cycle(n);
		
		//affichage des resultats
		System.out.println("Projets finalises :");
		for (Projet p : equipe.getProjetsComplets()) {
			System.out.println(p);
		}
	}
}

