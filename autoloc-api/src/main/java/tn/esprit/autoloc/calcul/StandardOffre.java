package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

@Component("STANDARD")
public class StandardOffre implements IOffreStrategy {
    @Override
    public double calculer(double tarif, int nbJours) {
        return tarif * nbJours;
    }
}
