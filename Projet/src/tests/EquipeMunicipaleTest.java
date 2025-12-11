package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import equipe.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class EquipeMunicipaleTest {
    private EquipeMunicipale equipeUnderTest;

    @BeforeEach
    public void initEquipeMunicipale() {    
        equipeUnderTest = new EquipeMunicipale();
    }
    @AfterEach
    public void undefEquipeMunicipale() {    
        equipeUnderTest = null;
    }
    
    @Test
    public void testCycle() {
    		Secteur[] s1 = {Secteur.CULTURE, Secteur.ATTRACTIVITE};
    		Secteur[] s2 = {Secteur.SANTE, Secteur.EDUCATION};
    		Projet p1 = new Projet("Projet1", "description du projet 1" , Secteur.SANTE, 0);
        Projet p2 = new Projet("Projet2", "description du projet 2", Secteur.EDUCATION,0);
        Expert ex1= new Expert("Einstein", "Albert", 63, s1 );
        	Expert ex2=new Expert("Gauss", "Carl", 89, s2);
        List<Expert> experts = new ArrayList<>();
        experts.add(ex1);
        experts.add(ex2);
        equipeUnderTest.setExperts(experts);
        Evaluateur eco=new Evaluateur("Newton","Isaac", 33, Specialisation.ECONOMIE);
        	Evaluateur soc=new Evaluateur("Kepler","Johannes",58,Specialisation.SOCIAL);
        	Evaluateur env=new Evaluateur("Copernic","Nicolas", 24, Specialisation.ENVIRONNEMENT);
        	Elu elu = new Elu("Descartes","René", 44);
        	equipeUnderTest.setEvaluateurs(eco, soc, env);
        equipeUnderTest.setElu(elu);
        equipeUnderTest.Cycle();
        List<Projet> complets = equipeUnderTest.getProjetsComplets();
        assertEquals(2, complets.size(), "Il devrait y avoir deux projets finalisés");
    }
    
    
}

