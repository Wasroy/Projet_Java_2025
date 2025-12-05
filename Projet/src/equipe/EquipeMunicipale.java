/**Simule une équipe municipale
 * @author Romane FAYON
 */

package equipe;
import java.util.*;

public class EquipeMunicipale {
    //gerer le cas si plusieurs experts du même secteur??
    private List<Expert> experts; /** Liste d'experts : ce sont eux qui vont proposer des projets. Il n'y a pas de restriction sur le nombre d'experts  */
    private Evaluateur evaluEco; /** L'évaluateur du secteur économique : c'est lui qui va évaluer le cout économique d'un projet */
    private Evaluateur evaluSoc; /** L'évaluateur du secteur social : c'est lui qui va évaluer le cout social d'un projet */
    private Evaluateur evaluEnv; /** L'évaluateur du secteur environnement : c'est lui qui va évaluer le cout environnemental d'un projet */
    private Elu elu; /** l'élu est l'employé qui va  évaluer le bénéfice d'un projet */

    private List<Projet> projets= new ArrayList<>(); /** liste des projets qui vont devoir être évalués (bénéfice + les 3 coûts) */
    private List<Projet> projetsComplets = new ArrayList<>(); /**projet finalisés (i.e évalués) prêts à être soumis au vote*/
    
    /**
     * setter qui permet d'attribuer des experts à l'équipe municipale
     * @param experts est la liste d'experts qui va rejoindre l'équipe municipale
     */
    public void setExperts(List<Expert> experts) {
        this.experts = experts;
    }
    /**
     * setter qui permet d'attribuer un élu à l'équipe municipale
     * @param elu est l'élu en question
     */
    public void setElu(Elu elu) {
        this.elu = elu;
    }

    /**
     * setter qui permet d'attribuer les 3 évaluateurs (un pour chaque secteur) à l'équipe municipale
     * @param eco est l'évaluateur du secteur économique
     * @param soc est l'évaluateur du secteur social
     * @param env est l'évaluateur du secteur environnemental 
     */
    public void setEvaluateurs(Evaluateur eco, Evaluateur soc, Evaluateur env) {
        this.evaluEco = eco;
        this.evaluSoc = soc;
        this.evaluEnv = env;
    }
    /**
     * getter qui permet de renvoyer la liste des projets finalisés i.e qui ont été évalués
     * @return la liste des projets évalués
     */
    public List<Projet> getProjetsComplets() {
        return projetsComplets;
    }

    /**
     * simule un cycle dans l'équipe municipale
     * étape 1 : les experts proposent des projets
     * étape 2 : pour chaque projet les évaluateurs et élu attribuent les valeurs (coûts et bénéfice)
     * cette méthode met à jour la liste des projets finalisés qui peuvent être soumis au vote 
     */
    public void Cycle() { 
    	
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
