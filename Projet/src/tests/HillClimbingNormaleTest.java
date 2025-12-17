package tests;
import java.util.*;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sacADos.*;
import solveur.hill_climbing.HillClimbingNormale;

public class HillClimbingNormaleTest {
	private HillClimbingNormale hcn=new HillClimbingNormale();
	private SacADos sac;
	
	@BeforeEach
	public void initHcn() {
		hcn=new HillClimbingNormale();
        Objet o1 = new Objet(2, new int[]{5,4});
        Objet o2 = new Objet(3, new int[] {4,2});
        Objet o3 = new Objet(4, new int[] {3,17});
        List<Objet> objets = new ArrayList<>();
        objets.add(o1);
        objets.add(o2);
        objets.add(o3);

        //ici on est en dimension 2 car les objets ont 2 couts
        sac = new SacADos(2, new int[] {20,30}, objets);
	}
	
	@AfterEach
	public void undefHcn() {
		hcn=null;
	}
	
    /**
     * la solution retournée ne doit pas être nulle
     */
    @Test
    void testResoudreRetourNonNull() {
        List<Objet> solution = hcn.resoudre(sac);
        assertNotNull(solution);
    }
    
    @Test
    void testSolutionValide() {
        List<Objet> solution = hcn.resoudre(sac);
        int[] budgets = sac.getBudgets();
        int[] coutTotal = new int[budgets.length];
        // Somme des coûts de chaque objet dans chaque dimension
        for (Objet o : solution) {
            int[] coutsObjet = o.getCouts();
            for (int i = 0; i < budgets.length; i++) {
                coutTotal[i] += coutsObjet[i];
            }
        }
        // on vérifie que chaque dimension respecte le budget
        for (int i = 0; i < budgets.length; i++) {
            assertTrue(coutTotal[i] <= budgets[i]);
        }
    }
    
    /**
     *hill climbing doit améliorer l'utilité (par rapport à la solution vide) i.e l'utilité de la solution doit être STRICT posiitive
     */
    @Test
    void testAmeliorationParRapportSolutionVide() {
        List<Objet> solution = hcn.resoudre(sac);
        int utiliteSolution = 0;
        for (Objet o : solution) {
            utiliteSolution += o.getUtilite();
        }
        assertTrue(utiliteSolution > 0);
    }
    
    @Test
    void testObjetsAppartiennentAuSac() {
        List<Objet> solution = hcn.resoudre(sac);
        for (Objet o : solution) {
            assertTrue(sac.getObjets().contains(o));
        }
    }
    
    /**
     * Test sur un sac trop petit : aucun objet ne peut être pris
     */
    @Test
    void testSacTropPetit() {
        Objet lourd = new Objet(10, new int[]{100,100});
        List<Objet> objets = new ArrayList<>();
        objets.add(lourd);
        SacADos sacMinuscule = new SacADos(2,new int[] {1,1}, objets);
        List<Objet> solution = hcn.resoudre(sacMinuscule);
        assertTrue(solution.isEmpty());
    }

}
