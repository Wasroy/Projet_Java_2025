package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import equipe.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        equipeUnderTest.cycle(5);
        List<Projet> complets = equipeUnderTest.getProjetsComplets();
        for (Projet p : complets) {
            assertNotNull(p.getBenefice(), "Le bénéfice doit être évalué");
            assertNotNull(p.getCout(Specialisation.ECONOMIE), "Le coût économique doit être évalué");
            assertNotNull(p.getCout(Specialisation.SOCIAL), "Le coût social doit être évalué");
            assertNotNull(p.getCout(Specialisation.ENVIRONNEMENT), "Le coût environnemental doit être évalué");
        }
        assertEquals(5, complets.size(), "Il devrait y avoir deux projets finalisés");
    }
    
    
}

