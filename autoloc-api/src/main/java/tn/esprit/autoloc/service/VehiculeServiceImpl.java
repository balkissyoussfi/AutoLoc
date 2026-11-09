package tn.esprit.autoloc.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.calcul.IOffreStrategy;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;

@Service
public class VehiculeServiceImpl extends GenericServiceImpl<Vehicule, Long> implements IVehiculeService {

    private final Map<String, IOffreStrategy> offres;

    public VehiculeServiceImpl(IVehiculeRepository vehiculeRepository, Map<String, IOffreStrategy> offres) {
        super(vehiculeRepository);
        this.offres = offres;
    }

    @Override
    protected String getEntityName() {
        return "Vehicule";
    }

    @Override
    protected Long getId(Vehicule vehicule) {
        return vehicule.getIdVehicule();
    }

    @Override
    protected void setFields(Vehicule source, Vehicule target) {
        target.setImmatriculation(source.getImmatriculation());
        target.setMarque(source.getMarque());
        target.setModele(source.getModele());
        target.setCategorie(source.getCategorie());
        target.setTarifJournalier(source.getTarifJournalier());
        target.setStatut(source.getStatut());
        target.setAgence(source.getAgence());
    }

    @Override
    public boolean check(Vehicule vehicule, boolean isNew) {
        if (vehicule == null) {
            return false;
        }
        return Utils.isNotEmpty(vehicule.getImmatriculation())
                && Utils.isNotEmpty(vehicule.getMarque())
                && Utils.isNotEmpty(vehicule.getModele())
                && vehicule.getCategorie() != null
                && vehicule.getTarifJournalier() != null
                && vehicule.getStatut() != null;
    }

    @Override
    public double calculTarif(Vehicule vehicule, int nbJours, VehiculeOffre offre) {
        return offres.get(offre.name()).calculer(vehicule.getTarifJournalier().doubleValue(), nbJours);
    }
}
