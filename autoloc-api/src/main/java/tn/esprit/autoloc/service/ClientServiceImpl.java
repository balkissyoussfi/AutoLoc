package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.IClientRepository;

@Service
public class ClientServiceImpl extends GenericServiceImpl<Client, Long> implements IClientService {

    public ClientServiceImpl(IClientRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Client e, boolean isNew) {
        return Utils.isNotEmpty(e.getNom()) && Utils.isNotEmpty(e.getPrenom())
                && Utils.isNotEmpty(e.getEmail()) && Utils.isNotEmpty(e.getNumPermis())
                && e.getDateInscription() != null;
    }

    @Override protected String getEntityName() { return "Client"; }
    @Override protected Long getId(Client e) { return e.getIdClient(); }

    @Override
    protected void setFields(Client source, Client target) {
        target.setNom(source.getNom());
        target.setPrenom(source.getPrenom());
        target.setEmail(source.getEmail());
        target.setTelephone(source.getTelephone());
        target.setNumPermis(source.getNumPermis());
        target.setDateInscription(source.getDateInscription());
    }
}
