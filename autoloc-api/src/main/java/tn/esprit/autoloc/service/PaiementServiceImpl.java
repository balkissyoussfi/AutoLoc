package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.repository.IPaiementRepository;

@Service
public class PaiementServiceImpl extends GenericServiceImpl<Paiement, Long> implements IPaiementService {

    private static final String LECTURE_SEULE = "Paiement est en lecture seule";

    public PaiementServiceImpl(IPaiementRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Paiement p, boolean isNew) {
        return false; // aucune écriture autorisée
    }

    @Override
    public Paiement create(Paiement entity) {
        throw new UnsupportedOperationException(LECTURE_SEULE);
    }

    @Override
    public Paiement update(Long id, Paiement entity) {
        throw new UnsupportedOperationException(LECTURE_SEULE);
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException(LECTURE_SEULE);
    }

    @Override protected String getEntityName() { return "Paiement"; }
    @Override protected Long getId(Paiement p) { return p.getIdPaiement(); }
    @Override protected void setFields(Paiement source, Paiement target) { /* lecture seule */ }
}
