package tn.esprit.autoloc.service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.io.RapportFactory;
import tn.esprit.autoloc.io.RapportTexte;
import tn.esprit.autoloc.repository.IAgenceRepository;

@Service
@Primary
public class AgenceServiceImpl extends GenericServiceImpl<Agence, Long> implements IAgenceService {

    private final IVehiculeService vehiculeService;

    public AgenceServiceImpl(IAgenceRepository repository, IVehiculeService vehiculeService) {
        super(repository);
        this.vehiculeService = vehiculeService;
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
    public String simuler(Agence agence, int nbJours, VehiculeOffre offre) {
        String entete = RapportFactory.creer(RapportTexte.class).entete(agence);
        double total = agence.getVehicules().stream()
                .mapToDouble(v -> vehiculeService.calculTarif(v, nbJours, offre)).sum();
        return entete + "\nTotal tarif : " + total;
    }
}
