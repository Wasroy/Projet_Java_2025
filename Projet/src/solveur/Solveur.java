package solveur;
import sacADos.*;
import java.util.List;

/**
 * interface pour les solveurs du probleme du sac a dos
 * permet d'avoir une structure commune entre les methodes gloutonnes et le hill climbing
 */

public interface Solveur {

    /**
     * resoud le probleme du sac a dos et retourne la liste des objets selectionnes
     * @param sac comme dhab
     * @return la liste des objets selectionnés pour la solution :wjwowo
     */
    List<Objet> resoudre(SacADos sac);

}

