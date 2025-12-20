package tests;
import solveur.glouton.*;
import sacADos.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import solveur.glouton.*;
import sacADos.*;

//import org.junit.Test; ou api et jupiter selon junit 4 ou 5??
//import static org.junit.Assert.*; ou api et jupiter selon junit 4 ou 5??


public class TestRetrait{
	
	@Test
	void testRetrait_liste_listeObjets(){
		//creation des instances de classes necessaires pour tester la methode (partie Arrange de l'AAA)
		List<Objet> objets1 = new ArrayList<Objet>();
		int[] couts1 = {10, 4, 10};
		int[] couts2 = {8, 7, 9};
		int[] couts3 = {1, 8, 6};
		objets1.add(new Objet(6, couts1));
		objets1.add(new Objet(12, couts2));
		objets1.add(new Objet(10, couts3));
		int[] budgets1 = {15, 15, 15};
		SacADos sac1 = new SacADos(3, budgets1, objets1);

		Comparator<Objet> ordre = new OrdreObjetsRetrait(sac1);
		objets1.sort(ordre);
		
		GloutonRetraitSolver g = new GloutonRetraitSolver();

		//on teste la methode (partie Act de l'AAA)
		List<Objet> resultat = g.methodeGloutonneRetrait(sac1, ordre);

		//on compare les resultats th et pratiques (partie Assert de l'AAA)
		List<Objet> resultatTh = new ArrayList<Objet>();
		resultatTh.add(new Objet(10, couts3));
		resultatTh.add(new Objet(12, couts2));
		assertEquals("Listes egales ?", resultatTh, resultat);
	}
	
}
