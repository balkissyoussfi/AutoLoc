package tn.esprit.autoloc.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.IAgenceRepository;

@Service
@Primary
public class AgenceServiceImpl extends GenericServiceImpl<Agence, Long> implements IAgenceService {

    private final IEmployeService employeService;

    public AgenceServiceImpl(IAgenceRepository repository, IEmployeService employeService) {
        super(repository);
        this.employeService = employeService;
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

    @Override
    @Transactional
    public void deleteById(Long id) {
        Agence agence = findById(id);
        agence.getVehicules().forEach(v -> v.setAgence(null));
        repository.delete(agence);
    }

    @Override
    @Transactional // à commenter pour les questions 15-16, à remettre pour la question 17
    public Agence updateAvecEmployes(Long id, Agence agence) {
        Agence a = update(id, agence);
        agence.getEmployes().forEach(e -> { a.addEmploye(e); employeService.create(e); });
        a.setEffectif(agence.getEmployes().size());
        return a;
    }
}