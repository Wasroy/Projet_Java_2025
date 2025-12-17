package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import equipe.*;

public class EvaluateurTest {
    private Evaluateur EvaluateurUnderTest;

    @BeforeEach
    public void initEvaluateur() {
        //on met une vraie specialisation sinon ca plante car le constructeur verifie que c'est pas null
        EvaluateurUnderTest = new Evaluateur("Dupont", "Jean", 35, Specialisation.ECONOMIE);
    }

    @AfterEach
    public void undefEvaluateur() {
        EvaluateurUnderTest = null;
    }

    @Test
    public void testEvaluerCout() {
        Projet projet = new Projet("Projet Test", "Description test", Secteur.SPORT, 0);
        EvaluateurUnderTest.evaluerCout(projet);
        //on utilise le getter pour recuperer la specialisation car Specialite est private
        int cout = projet.getCout(EvaluateurUnderTest.getSpecialisation());
        org.junit.Assert.assertTrue("Le coût évalué doit être entre 0 et 500000", cout >= 0 && cout <= 500000);
    }

}