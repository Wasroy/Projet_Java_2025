package equipe;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import equipe.Elu;
import equipe.Evaluateur;
import equipe.Expert;
import equipe.Projet;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

public class EquipeMunicipaleTest {
    private List<Expert> expertsUnderTest; 
    private Evaluateur evaluEcoUnderTest;  
    private Evaluateur evaluSocUnderTest; 
    private Evaluateur evaluEnvUnderTest; 
    private Elu eluUnderTest; 
    private List<Projet> projetsUnderTest= new ArrayList<>(); 
    private List<Projet> projetsCompletsUnderTest = new ArrayList<>();

    @Before
    public void initEquipeMunicipale() {    
        expertsUnderTest = new ArrayList<>();
        evaluEcoUnderTest = new Evaluateur("Alfred", "Gauss", 40, Specialisation.ECONOMIE);
        evaluSocUnderTest = new Evaluateur("Schrodinger", "Erwan", 45, Specialisation.SOCIAL);
        evaluEnvUnderTest = new Evaluateur("Einstein", "Albert", 50, Specialisation.ENVIRONNEMENT);
        eluUnderTest = new Elu("Curie", "Marie", 55);
        projetsCompletsUnderTest = new ArrayList<>();
        projetsUnderTest = new ArrayList<>();
    }
    @After
    public void undefEquipeMunicipale() {    
        expertsUnderTest = null;
        evaluEcoUnderTest = null;
        evaluSocUnderTest = null;
        evaluEnvUnderTest = null;
        eluUnderTest = null;
        projetsUnderTest = null;
        projetsCompletsUnderTest = null;
    }


}
