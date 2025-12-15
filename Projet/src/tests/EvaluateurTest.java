package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import equipe.*;

public class EvaluateurTest {
    private Evaluateur EvaluateurUnderTest;

    @BeforeEach
    public void initEvaluateur() {
        EvaluateurUnderTest = new Evaluateur(null, null, 0, null);
    }

    @AfterEach
    public void undefEvaluateur() {
        EvaluateurUnderTest = null;
    }

    @Test
    public void testEvaluerCout() {
        Projet projet = new Projet("Projet Test", "Description test", Secteur.SPORT, 0);
        EvaluateurUnderTest.evaluerCout(projet);
        int cout = projet.cout.get(EvaluateurUnderTest.Specialite);
        org.junit.Assert.assertTrue("Le coût évalué doit être entre 0 et 500000", cout >= 0 && cout <= 500000);
    }

}