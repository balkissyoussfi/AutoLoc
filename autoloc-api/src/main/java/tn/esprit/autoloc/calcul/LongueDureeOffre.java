package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

@Component("LONGUE_DUREE")
public class LongueDureeOffre implements IOffreStrategy {
    @Override
    public double calculer(double tarif, int nbJours) {
        double total = tarif * 0.75 * nbJours;
        return total > 2000 ? total * 0.95 : total;
    }
}
