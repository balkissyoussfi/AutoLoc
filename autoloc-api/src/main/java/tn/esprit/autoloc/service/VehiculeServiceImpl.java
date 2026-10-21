package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;

@Service
public class VehiculeServiceImpl extends GenericServiceImpl<Vehicule, Long> implements IVehiculeService {

    public VehiculeServiceImpl(IVehiculeRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Vehicule e, boolean isNew) {
        return Utils.isNotEmpty(e.getImmatriculation()) && Utils.isNotEmpty(e.getMarque())
                && Utils.isNotEmpty(e.getModele()) && e.getCategorie() != null
                && e.getStatut() != null && e.getTarifJournalier() != null;
    }

    @Override protected String getEntityName() { return "Vehicule"; }
    @Override protected Long getId(Vehicule e) { return e.getIdVehicule(); }

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
}
