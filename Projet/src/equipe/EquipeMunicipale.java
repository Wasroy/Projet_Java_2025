package equipe;
import java.util.*;

public class EquipeMunicipale {
    private List<Expert> experts;
    private Evaluateur evaluEco;
    private Evaluateur evaluSoc;
    private Evaluateur evaluEnv;
    private Elu elu;

    private List<Projet> projets= new ArrayList<>(); //projet à évaluer
    private List<Projet> projetsComplets = new ArrayList<>(); //projet finalises prêts à être votés
    
    
    //ajouter setElu, setEvaluateur, setExpert
    public void setExperts(List<Expert> experts) {
        this.experts = experts;
    }
    
    public void setElu(Elu elu) {
        this.elu = elu;
    }
    
    public void setEvaluateurs(Evaluateur eco, Evaluateur soc, Evaluateur env) {
        this.evaluEco = eco;
        this.evaluSoc = soc;
        this.evaluEnv = env;
    }
    
    public List<Projet> getProjetsComplets() {
        return projetsComplets;
    }

    
    public void Cycle() { //simule un cycle dans l'équipe muninicipale
    	
    	// 1. les experts proposent des projets
        for (Expert e : experts) {         //j'ai choisi un parcours for each car je n'ai pas besoin de modifier ma liste pendant le parcours 
        	 Projet p=e.proposerProjet(); 
        	 projets.add(p);
        }
        
     // 2. pour chaque projet les évaluateurs et élu attribuent les valeurs
    	for (Projet p : projets) {
    		elu.EvaluerBenefice(p);
    		evaluEco.evaluerCout(p);
    		evaluSoc.evaluerCout(p);
    		evaluEnv.evaluerCout(p);
    	    projetsComplets.add(p);
    	}

    	projets.clear(); //on remet la liste vide pour être prêt pour le prochain cycle
    }
}
