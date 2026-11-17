package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.IEquipementRepository;
import tn.esprit.autoloc.domain.Equipement;

@Service
public class EquipementServiceImpl extends GenericServiceImpl<Equipement, Long> implements IEquipementService {

    public EquipementServiceImpl(IEquipementRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Equipement e, boolean isNew) {
        return Utils.isNotEmpty(e.getLibelle());
    }

    @Override protected String getEntityName() { return "Equipement"; }
    @Override protected Long getId(Equipement e) { return e.getIdEquipement(); }

    @Override
    protected void setFields(Equipement source, Equipement target) {
        target.setLibelle(source.getLibelle());
    }

    @Override @Transactional
    public void deleteById(Long id) {
        Equipement e = findById(id);
        e.getVehicules().forEach(v -> v.getEquipements().remove(e));
        repository.delete(e);
    }
}