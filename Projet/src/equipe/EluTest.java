package equipe;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
// using explicit assertions to avoid unused static imports


public class EluTest {
    private Elu eluUnderTest;

    @Before
    public void initElu() {
        eluUnderTest = new Elu(null, null, 0);
    }

    @After
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
