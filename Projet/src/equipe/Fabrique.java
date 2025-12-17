package equipe;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * classe qui s'occupent créer des experts et des évaluateurs pour qu'après ils aillent travailler dans l'équipe
 */
public class Fabrique {

    /**
     * Crée une liste d'experts municipaux
     * @return liste d'experts
     */
	public static List<Expert> creerExperts(int nbExperts) {
	    List<Expert> experts = new ArrayList<>();
	    Random rand = new Random();
	    Secteur[] tousSecteurs = Secteur.values();
	    for (int i = 0; i < nbExperts; i++) {
	        Secteur s1 = tousSecteurs[rand.nextInt(tousSecteurs.length)]; //choix aléatoire des secteurs
	        Secteur s2 = tousSecteurs[rand.nextInt(tousSecteurs.length)];
	        Secteur[] secteurs = (s1 == s2)
	            ? new Secteur[]{s1}
	            : new Secteur[]{s1, s2};
	        /*si s1 et s2 sont identiques alors on crée un tableau contenant un seul secteur
	        *sinon on crée un tableau contenant les deux secteurs*/
	        experts.add(new Expert(
	            "Expert" + i,
	            "Municipal",
	            30 + rand.nextInt(30),
	            secteurs
	        ));
	    }
	    return experts;
	}
	
	public static Evaluateur[] creerEvaluateurs(){
		Evaluateur evaluEco = new Evaluateur("Pythagore", "Romane", 40, Specialisation.ECONOMIE);
		Evaluateur evaluSoc = new Evaluateur("Descartes", "Fabien", 32, Specialisation.SOCIAL);
		Evaluateur evaluEnv = new Evaluateur("Gauss", "Nathalie", 22, Specialisation.ENVIRONNEMENT);
		return new Evaluateur[]{evaluEco, evaluSoc, evaluEnv};
	}
}

