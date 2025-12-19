/**Simule une équipe municipale
 */

package equipe;
import java.util.*;

public class EquipeMunicipale {
    //gerer le cas si plusieurs experts du même secteur??
    private List<Expert> experts= new ArrayList<>(); /** Liste d'experts : ce sont eux qui vont proposer des projets. Il n'y a pas de restriction sur le nombre d'experts  */
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
    public void setEvaluateurs(Evaluateur... evaluateurs) { //permet de ne modifier que les évaluateurs fournis
        for (Evaluateur e : evaluateurs) {
            if (e == null) continue;
            switch (e.getSpecialisation()) {
                case ECONOMIE:
                    evaluEco = e;
                    break;
                case SOCIAL:
                    evaluSoc = e;
                    break;
                case ENVIRONNEMENT:
                    evaluEnv = e;
                    break;
                default:
                    throw new IllegalArgumentException(
                        "Évaluateur avec spécialité inconnue : " + e.getSpecialisation()
                    );
            }
        }
    }

    /**
     * getter qui permet de renvoyer la liste des projets finalisés i.e qui ont été évalués
     * @return la liste des projets évalués
     */
    public List<Projet> getProjetsComplets() {
        return projetsComplets;
    }
    
    public void equipeComplete(int nbProjets) {
		if (this.experts.size()<nbProjets) { //il manque des experts
			int manquant=nbProjets-this.experts.size();
			experts.addAll(Fabrique.creerExperts(manquant));
		}
		if (evaluEco==null||evaluSoc==null||evaluEnv==null) {
		    Evaluateur[] evaluateurs = Fabrique.creerEvaluateurs();
		    this.setEvaluateurs(evaluateurs[0], evaluateurs[1], evaluateurs[2]);
		}
	    if (this.elu==null) {
	    		this.setElu(new Elu(null, null, 45));
	    }
		//if il manque des evaluateurs : on en crée 
		//je suis partie du principe que la mairie n'engagerait jamais + de 3 évaluateurs
		//je suis aussi partie du principe qu'il n'y avait qu'un seul élu à la mairie
}

    /**
     * simule un cycle dans l'équipe municipale en creant automatiquement les experts et evaluateurs
     * étape 1 : les experts proposent des projets
     * étape 2 : pour chaque projet les évaluateurs et élu attribuent les valeurs (coûts et bénéfice)
     * cette méthode met à jour la liste des projets finalisés qui peuvent être soumis au vote 
     * @param nbProjets le nombre de projets a generer (= nombre d'experts crees)
     */  
    public void cycle(int nbProjets) {  
    		equipeComplete(nbProjets);
    	// 1. les experts proposent des projets
        for (Expert e : new  ArrayList<>(experts.subList(0,nbProjets))) {         //j'ai choisi un parcours for each car je n'ai pas besoin de modifier ma liste pendant le parcours 
        	//j'ai fait une subList car problème si on a plus d'experts dans l'équipe que de projets désirés et comme ca on garde en mémoire les experts qui n'interviennent pas dans l'équipe municipale pour ce projet mais vont peut être intervenir pour un autre
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
    
    /**
     * simule un cycle dans l'équipe municipale en utilisant les experts/evaluateurs/elu deja setups
     * utile pour les tests ou quand on veut controler precisement l'equipe
     * si les experts/evaluateurs/elu ne sont pas setups ca va planter donc faut les setup avant d'appeler cette methode
     */
}