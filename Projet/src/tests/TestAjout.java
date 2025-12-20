package tests;
import solveur.glouton.*;
import sacADos.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

//import org.junit.Test; ou api et jupiter selon junit 4 ou 5??
//import static org.junit.Assert.*; ou api et jupiter selon junit 4 ou 5??


public class TestAjout{
	
	@Test
	void testAjout_liste_listeObjets(){
		//creation des instances de classes necessaires pour tester la methode (partie Arrange de l'AAA)
		List<Objet> objets1 = new ArrayList<Objet>();
		int[] couts1 = {1, 2};
		int[] couts2 = {5, 8};
		int[] couts3 = {2, 9};
		objets1.add(new Objet(1, couts1));
		objets1.add(new Objet(7, couts2));
		objets1.add(new Objet(4, couts3));
		int[] budgets1 = {3, 5};
		SacADos sac1 = new SacADos(2, budgets1, objets1);

		Comparator<Objet> ordre = new OrdreObjetsAjoutPremier(); //peut remplacer par OrdreObjetsAjoutDeuxieme() aussi
		objets1.sort(ordre);
		
		GloutonAjoutSolver g = new GloutonAjoutSolver();

		//on teste la methode (partie Act de l'AAA)
		List<Objet> resultat = GloutonAjoutSolver.methodeGloutonneAjout(sac1, ordre);

		//on compare les resultats th et pratiques (partie Assert de l'AAA)
		List<Objet> resultatTh = new ArrayList<Objet>();
		resultatTh.add(new Objet(1, couts1));
		assertEquals("Listes egales ?", resultatTh, resultat);
	}
	
}
