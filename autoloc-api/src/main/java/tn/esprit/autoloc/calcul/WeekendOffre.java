package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

@Component("WEEKEND")
public class WeekendOffre implements IOffreStrategy {
    @Override
    public double calculer(double tarif, int nbJours) {
        return tarif * 0.8 * nbJours;
    }
}
