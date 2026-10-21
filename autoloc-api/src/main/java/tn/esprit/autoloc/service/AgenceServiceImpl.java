package tn.esprit.autoloc.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.IAgenceRepository;

@Service
@Primary
public class AgenceServiceImpl extends GenericServiceImpl<Agence, Long> implements IAgenceService {

    public AgenceServiceImpl(IAgenceRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Agence a, boolean isNew) {
        return Utils.isNotEmpty(a.getNom()) && Utils.isNotEmpty(a.getVille()) && Utils.isNotEmpty(a.getAdresse());
    }

    @Override
    public boolean hasVehicules(Long id) {
        var vehicules = findById(id).getVehicules();
        return vehicules != null && !vehicules.isEmpty();
    }

    @Override protected String getEntityName() { return "Agence"; }
    @Override protected Long getId(Agence a) { return a.getIdAgence(); }

    @Override
    protected void setFields(Agence source, Agence target) {
        target.setNom(source.getNom());
        target.setVille(source.getVille());
        target.setAdresse(source.getAdresse());
        target.setTelephone(source.getTelephone());
    }
}
