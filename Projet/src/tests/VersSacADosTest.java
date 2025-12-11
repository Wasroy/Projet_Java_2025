package tests;

import static org.junit.Assert.*;
import java.io.*;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import equipe.*;
import pont.MKP;
import pont.VersSacADos;
import sacADos.*;

public class VersSacADosTest {
	private VersSacADos VersSacADosUnderTest;
	
	@BeforeEach
	public void initVersSacADos() {
		 VersSacADosUnderTest=new VersSacADos();
	}
	
	@AfterEach
	public void undefVersSacADos() {
		VersSacADosUnderTest=null;
	}
	
	
	@Test
	public void testTransformation_OK() {
		Projet p = new Projet("Projet1", "Description du projet1", Secteur.CULTURE, 10000);
	    p.setCout(Specialisation.ECONOMIE, 20000);
	    p.setCout(Specialisation.SOCIAL, 30000);
	    p.setCout(Specialisation.ENVIRONNEMENT, 5000);
        Objet o = VersSacADosUnderTest.transformation(p);
	    assertEquals(10000, o.getUtilite());
	    assertArrayEquals(new int[]{20000, 30000, 5000}, o.getCouts());
	}
	
    @Test
    public void testTransformerAll_OK() {
        Projet p1 = new Projet("p1", "d1", Secteur.EDUCATION, 5);
        Projet p2 = new Projet("p2", "d2", Secteur.CULTURE, 7);
        Projet[] liste = {p1, p2};
        List<Objet> objets = VersSacADosUnderTest.transformerAll(liste);
        assertEquals(2, objets.size());
    }
    
    @Test
    public void testTraitementFicOK() throws Exception {
    		String tempDir = System.getProperty("user.dir");
    		//ici j'ai cherché comment obtenir le répertoire courant en java
    		// je me suis inspirée de cette page : https://codegym.cc/fr/groups/posts/fr.615.obtenir-le-repertoire-de-travail-actuel-en-java
        File f = new File(tempDir, "test.dat");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            bw.write("3 2 42\n");
            bw.write("1 2 3\n");
            bw.write("4 5 6\n");
            bw.write("7 8 9\n"); 
            bw.write("10 11\n"); // budgets
        }
        //je créer un fichier test

        MKP instance = VersSacADos.traitementFic(f.getAbsolutePath());
        assertNotNull(instance);
        assertEquals(3, instance.getn());
        assertEquals(2, instance.getk());
        assertEquals(42, instance.getopt());
    }
    
    @Test
    public void testFicToSacOK() throws Exception {
        int n = 2;
        int k = 2;
        int[] utilites = {5, 10};
        int[][] contraintes = {
                {1, 2},
                {3, 4}
        };
        int[] budgets = {10, 10};
        MKP inst = new MKP(n, k, 0, utilites, contraintes, budgets);
        SacADos s = VersSacADos.ficToSac(inst);
        assertEquals(2, s.getObjets().size());
        assertEquals(2, s.getDimension());
    }

}
