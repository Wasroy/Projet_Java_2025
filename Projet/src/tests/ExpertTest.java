package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import equipe.*;


public class ExpertTest {
	
	private Expert expertUnderTest;
	
	@BeforeEach
	public void initExpert() {
		expertUnderTest=new Expert(null,null,0,null);
	}
	
	@AfterEach
	public void undefExpert() {
		expertUnderTest=null;
	}
	
	@Test
	public void testProposerProjet() {
		expertUnderTest.proposerProjet();		
	}

}
