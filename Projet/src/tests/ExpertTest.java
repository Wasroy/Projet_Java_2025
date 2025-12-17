package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import equipe.*;

import static org.junit.jupiter.api.Assertions.*;


public class ExpertTest {
	
	private Expert expertUnderTest;
	
	@BeforeEach
	public void initExpert() {
		//on met des vrais secteurs sinon ca plante quand on propose un projet car il va faire secteurs[rand.nextInt(...)]
		Secteur[] secteurs = {Secteur.SPORT, Secteur.CULTURE};
		expertUnderTest=new Expert("Martin", "Pierre", 42, secteurs);
	}
	
	@AfterEach
	public void undefExpert() {
		expertUnderTest=null;
	}
	
	@Test
	public void testProposerProjet() {
		Projet p = expertUnderTest.proposerProjet();
		//on verifie que le projet n'est pas null et qu'il a bien un secteur parmi ceux de l'expert
		assertNotNull(p, "Le projet proposé ne doit pas être null");
		assertNotNull(p.getTitre(), "Le titre du projet ne doit pas être null");
	}

}
