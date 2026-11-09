package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

public interface IBasicAgenceService extends IGenericService<Agence, Long> {
    boolean hasVehicules(Long id);
}
