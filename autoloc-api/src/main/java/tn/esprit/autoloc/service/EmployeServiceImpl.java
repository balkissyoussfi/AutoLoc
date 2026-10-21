package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.IEmployeRepository;

@Service
public class EmployeServiceImpl extends GenericServiceImpl<Employe, Long> implements IEmployeService {

    public EmployeServiceImpl(IEmployeRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Employe e, boolean isNew) {
        return Utils.isNotEmpty(e.getNom()) && Utils.isNotEmpty(e.getPrenom()) && e.getRole() != null;
    }

    @Override protected String getEntityName() { return "Employe"; }
    @Override protected Long getId(Employe e) { return e.getIdEmploye(); }

    @Override
    protected void setFields(Employe source, Employe target) {
        target.setNom(source.getNom());
        target.setPrenom(source.getPrenom());
        target.setRole(source.getRole());
        target.setAgence(source.getAgence());
    }
}
