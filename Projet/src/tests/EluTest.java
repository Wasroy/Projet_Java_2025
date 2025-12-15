package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import equipe.*;

public class EluTest {
    private Elu eluUnderTest;

    @BeforeEach
    public void initElu() {
        eluUnderTest = new Elu(null, null, 0);
    }

    @AfterEach
    public void undefElu() {
        eluUnderTest = null;
    }
    

    @Test
    public void testEvaluerBenefice() {
        Projet projet = new Projet("Projet Test", "Description test", Secteur.SPORT, 0);
        int benefice = eluUnderTest.EvaluerBenefice(projet);
        org.junit.Assert.assertTrue("Le bénéfice évalué doit être >= 0", benefice >= 0);
    }
    
}
